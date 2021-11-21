package org.spongepowered.asm.mixin.transformer;

import java.util.Iterator;
import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.mixin.transformer.throwables.InvalidMixinException;
import java.util.List;

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
