package org.spongepowered.asm.mixin.transformer;

import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.mixin.transformer.throwables.InvalidMixinException;
import org.spongepowered.asm.mixin.MixinEnvironment;
import java.util.List;

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
