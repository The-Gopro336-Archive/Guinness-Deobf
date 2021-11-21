package org.spongepowered.asm.mixin.transformer;

import org.spongepowered.asm.lib.tree.ClassNode;
import java.util.List;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.mixin.transformer.throwables.InvalidMixinException;
import org.spongepowered.asm.mixin.MixinEnvironment;
import java.util.ArrayList;
import java.util.Collection;

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
