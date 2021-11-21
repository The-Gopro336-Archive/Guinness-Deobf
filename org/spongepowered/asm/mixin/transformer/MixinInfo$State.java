package org.spongepowered.asm.mixin.transformer;

import org.spongepowered.asm.lib.tree.InnerClassNode;
import org.spongepowered.asm.mixin.Implements;
import java.util.Collection;
import org.spongepowered.asm.lib.tree.AnnotationNode;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.lib.tree.FieldNode;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.mixin.transformer.throwables.InvalidMixinException;
import java.util.Iterator;
import java.lang.annotation.Annotation;
import org.spongepowered.asm.util.Annotations;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.lib.ClassVisitor;
import org.spongepowered.asm.lib.ClassReader;
import org.spongepowered.asm.lib.tree.ClassNode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class State
{
    private byte[] mixinBytes;
    private final ClassInfo classInfo;
    private boolean detachedSuper;
    private boolean unique;
    protected final Set<String> interfaces;
    protected final List<InterfaceInfo> softImplements;
    protected final Set<String> syntheticInnerClasses;
    protected final Set<String> innerClasses;
    protected MixinClassNode classNode;
    
    State(final MixinInfo this$0, final byte[] mixinBytes) {
        this(this$0, mixinBytes, null);
    }
    
    State(final byte[] mixinBytes, final ClassInfo classInfo) {
        this.interfaces = new HashSet<String>();
        this.softImplements = new ArrayList<InterfaceInfo>();
        this.syntheticInnerClasses = new HashSet<String>();
        this.innerClasses = new HashSet<String>();
        this.mixinBytes = mixinBytes;
        this.connect();
        this.classInfo = ((classInfo != null) ? classInfo : ClassInfo.fromClassNode(this.getClassNode()));
    }
    
    private void connect() {
        this.classNode = this.createClassNode(0);
    }
    
    private void complete() {
        this.classNode = null;
    }
    
    ClassInfo getClassInfo() {
        return this.classInfo;
    }
    
    byte[] getClassBytes() {
        return this.mixinBytes;
    }
    
    MixinClassNode getClassNode() {
        return this.classNode;
    }
    
    boolean isDetachedSuper() {
        return this.detachedSuper;
    }
    
    boolean isUnique() {
        return this.unique;
    }
    
    List<? extends InterfaceInfo> getSoftImplements() {
        return this.softImplements;
    }
    
    Set<String> getSyntheticInnerClasses() {
        return this.syntheticInnerClasses;
    }
    
    Set<String> getInnerClasses() {
        return this.innerClasses;
    }
    
    Set<String> getInterfaces() {
        return this.interfaces;
    }
    
    MixinClassNode createClassNode(final int flags) {
        final MixinClassNode classNode = new MixinClassNode(MixinInfo.this);
        final ClassReader classReader = new ClassReader(this.mixinBytes);
        classReader.accept(classNode, flags);
        return classNode;
    }
    
    void validate(final SubType type, final List<ClassInfo> targetClasses) {
        final MixinPreProcessorStandard preProcessor = type.createPreProcessor(this.getClassNode()).prepare();
        for (final ClassInfo target : targetClasses) {
            preProcessor.conform(target);
        }
        type.validate(this, targetClasses);
        this.detachedSuper = type.isDetachedSuper();
        this.unique = (Annotations.getVisible(this.getClassNode(), Unique.class) != null);
        this.validateInner();
        this.validateClassVersion();
        this.validateRemappables(targetClasses);
        this.readImplementations(type);
        this.readInnerClasses();
        this.validateChanges(type, targetClasses);
        this.complete();
    }
    
    private void validateInner() {
        if (!this.classInfo.isProbablyStatic()) {
            throw new InvalidMixinException(MixinInfo.this, "Inner class mixin must be declared static");
        }
    }
    
    private void validateClassVersion() {
        if (this.classNode.version > MixinEnvironment.getCompatibilityLevel().classVersion()) {
            String helpText = ".";
            for (final MixinEnvironment.CompatibilityLevel level : MixinEnvironment.CompatibilityLevel.values()) {
                if (level.classVersion() >= this.classNode.version) {
                    helpText = String.format(". Mixin requires compatibility level %s or above.", level.name());
                }
            }
            throw new InvalidMixinException(MixinInfo.this, "Unsupported mixin class version " + this.classNode.version + helpText);
        }
    }
    
    private void validateRemappables(final List<ClassInfo> targetClasses) {
        if (targetClasses.size() > 1) {
            for (final FieldNode field : this.classNode.fields) {
                this.validateRemappable(Shadow.class, field.name, Annotations.getVisible(field, Shadow.class));
            }
            for (final MethodNode method : this.classNode.methods) {
                this.validateRemappable(Shadow.class, method.name, Annotations.getVisible(method, Shadow.class));
                final AnnotationNode overwrite = Annotations.getVisible(method, Overwrite.class);
                if (overwrite != null && ((method.access & 0x8) == 0x0 || (method.access & 0x1) == 0x0)) {
                    throw new InvalidMixinException(MixinInfo.this, "Found @Overwrite annotation on " + method.name + " in " + MixinInfo.this);
                }
            }
        }
    }
    
    private void validateRemappable(final Class<Shadow> annotationClass, final String name, final AnnotationNode annotation) {
        if (annotation != null && Annotations.getValue(annotation, "remap", Boolean.TRUE)) {
            throw new InvalidMixinException(MixinInfo.this, "Found a remappable @" + annotationClass.getSimpleName() + " annotation on " + name + " in " + this);
        }
    }
    
    void readImplementations(final SubType type) {
        this.interfaces.addAll(this.classNode.interfaces);
        this.interfaces.addAll(type.getInterfaces());
        final AnnotationNode implementsAnnotation = Annotations.getInvisible(this.classNode, Implements.class);
        if (implementsAnnotation == null) {
            return;
        }
        final List<AnnotationNode> interfaces = Annotations.getValue(implementsAnnotation);
        if (interfaces == null) {
            return;
        }
        for (final AnnotationNode interfaceNode : interfaces) {
            final InterfaceInfo interfaceInfo = InterfaceInfo.fromAnnotation(MixinInfo.this, interfaceNode);
            this.softImplements.add(interfaceInfo);
            this.interfaces.add(interfaceInfo.getInternalName());
            if (!(this instanceof Reloaded)) {
                this.classInfo.addInterface(interfaceInfo.getInternalName());
            }
        }
    }
    
    void readInnerClasses() {
        for (final InnerClassNode inner : this.classNode.innerClasses) {
            final ClassInfo innerClass = ClassInfo.forName(inner.name);
            if ((inner.outerName != null && inner.outerName.equals(this.classInfo.getName())) || inner.name.startsWith(this.classNode.name + "$")) {
                if (innerClass.isProbablyStatic() && innerClass.isSynthetic()) {
                    this.syntheticInnerClasses.add(inner.name);
                }
                else {
                    this.innerClasses.add(inner.name);
                }
            }
        }
    }
    
    protected void validateChanges(final SubType type, final List<ClassInfo> targetClasses) {
        type.createPreProcessor(this.classNode).prepare();
    }
}
