package org.spongepowered.asm.mixin.transformer;

import java.util.ArrayList;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.lib.tree.ClassNode;
import java.util.Iterator;
import java.util.List;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.mixin.transformer.throwables.InvalidMixinException;
import java.util.Collections;
import java.util.Collection;

abstract static class SubType
{
    protected final MixinInfo mixin;
    protected final String annotationType;
    protected final boolean targetMustBeInterface;
    protected boolean detached;
    
    SubType(final MixinInfo info, final String annotationType, final boolean targetMustBeInterface) {
        this.mixin = info;
        this.annotationType = annotationType;
        this.targetMustBeInterface = targetMustBeInterface;
    }
    
    Collection<String> getInterfaces() {
        return (Collection<String>)Collections.emptyList();
    }
    
    boolean isDetachedSuper() {
        return this.detached;
    }
    
    boolean isLoadable() {
        return false;
    }
    
    void validateTarget(final String targetName, final ClassInfo targetInfo) {
        final boolean targetIsInterface = targetInfo.isInterface();
        if (targetIsInterface != this.targetMustBeInterface) {
            final String not = targetIsInterface ? "" : "not ";
            throw new InvalidMixinException(this.mixin, this.annotationType + " target type mismatch: " + targetName + " is " + not + "an interface in " + this);
        }
    }
    
    abstract void validate(final State p0, final List<ClassInfo> p1);
    
    abstract MixinPreProcessorStandard createPreProcessor(final MixinClassNode p0);
    
    static SubType getTypeFor(final MixinInfo mixin) {
        if (!mixin.getClassInfo().isInterface()) {
            return new Standard(mixin);
        }
        boolean containsNonAccessorMethod = false;
        for (final ClassInfo.Method method : mixin.getClassInfo().getMethods()) {
            containsNonAccessorMethod |= !method.isAccessor();
        }
        if (containsNonAccessorMethod) {
            return new Interface(mixin);
        }
        return new Accessor(mixin);
    }
    
    static class Standard extends SubType
    {
        Standard(final MixinInfo info) {
            super(info, "@Mixin", false);
        }
        
        @Override
        void validate(final State state, final List<ClassInfo> targetClasses) {
            final ClassNode classNode = state.getClassNode();
            for (final ClassInfo targetClass : targetClasses) {
                if (classNode.superName.equals(targetClass.getSuperName())) {
                    continue;
                }
                if (!targetClass.hasSuperClass(classNode.superName, ClassInfo.Traversal.SUPER)) {
                    final ClassInfo superClass = ClassInfo.forName(classNode.superName);
                    if (superClass.isMixin()) {
                        for (final ClassInfo superTarget : superClass.getTargets()) {
                            if (targetClasses.contains(superTarget)) {
                                throw new InvalidMixinException(this.mixin, "Illegal hierarchy detected. Derived mixin " + this + " targets the same class " + superTarget.getClassName() + " as its superclass " + superClass.getClassName());
                            }
                        }
                    }
                    throw new InvalidMixinException(this.mixin, "Super class '" + classNode.superName.replace('/', '.') + "' of " + this.mixin.getName() + " was not found in the hierarchy of target class '" + targetClass + "'");
                }
                this.detached = true;
            }
        }
        
        @Override
        MixinPreProcessorStandard createPreProcessor(final MixinClassNode classNode) {
            return new MixinPreProcessorStandard(this.mixin, classNode);
        }
    }
    
    static class Interface extends SubType
    {
        Interface(final MixinInfo info) {
            super(info, "@Mixin", true);
        }
        
        @Override
        void validate(final State state, final List<ClassInfo> targetClasses) {
            if (!MixinEnvironment.getCompatibilityLevel().supportsMethodsInInterfaces()) {
                throw new InvalidMixinException(this.mixin, "Interface mixin not supported in current enviromnment");
            }
            final ClassNode classNode = state.getClassNode();
            if (!"java/lang/Object".equals(classNode.superName)) {
                throw new InvalidMixinException(this.mixin, "Super class of " + this + " is invalid, found " + classNode.superName.replace('/', '.'));
            }
        }
        
        @Override
        MixinPreProcessorStandard createPreProcessor(final MixinClassNode classNode) {
            return new MixinPreProcessorInterface(this.mixin, classNode);
        }
    }
    
    static class Accessor extends SubType
    {
        private final Collection<String> interfaces;
        
        Accessor(final MixinInfo info) {
            super(info, "@Mixin", false);
            (this.interfaces = new ArrayList<String>()).add(info.getClassRef());
        }
        
        @Override
        boolean isLoadable() {
            return true;
        }
        
        @Override
        Collection<String> getInterfaces() {
            return this.interfaces;
        }
        
        @Override
        void validateTarget(final String targetName, final ClassInfo targetInfo) {
            final boolean targetIsInterface = targetInfo.isInterface();
            if (targetIsInterface && !MixinEnvironment.getCompatibilityLevel().supportsMethodsInInterfaces()) {
                throw new InvalidMixinException(this.mixin, "Accessor mixin targetting an interface is not supported in current enviromnment");
            }
        }
        
        @Override
        void validate(final State state, final List<ClassInfo> targetClasses) {
            final ClassNode classNode = state.getClassNode();
            if (!"java/lang/Object".equals(classNode.superName)) {
                throw new InvalidMixinException(this.mixin, "Super class of " + this + " is invalid, found " + classNode.superName.replace('/', '.'));
            }
        }
        
        @Override
        MixinPreProcessorStandard createPreProcessor(final MixinClassNode classNode) {
            return new MixinPreProcessorAccessor(this.mixin, classNode);
        }
    }
}
