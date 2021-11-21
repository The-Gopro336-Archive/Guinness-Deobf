package org.spongepowered.asm.mixin.transformer;

import org.spongepowered.asm.lib.tree.ClassNode;
import java.util.Collection;
import java.util.HashSet;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.mixin.transformer.throwables.MixinReloadException;
import java.util.List;

class Reloaded extends State
{
    private final State previous;
    
    Reloaded(final State previous, final byte[] mixinBytes) {
        super(mixinBytes, previous.getClassInfo());
        this.previous = previous;
    }
    
    @Override
    protected void validateChanges(final SubType type, final List<ClassInfo> targetClasses) {
        if (!this.syntheticInnerClasses.equals(this.previous.syntheticInnerClasses)) {
            throw new MixinReloadException(MixinInfo.this, "Cannot change inner classes");
        }
        if (!this.interfaces.equals(this.previous.interfaces)) {
            throw new MixinReloadException(MixinInfo.this, "Cannot change interfaces");
        }
        if (!new HashSet(this.softImplements).equals(new HashSet(this.previous.softImplements))) {
            throw new MixinReloadException(MixinInfo.this, "Cannot change soft interfaces");
        }
        final List<ClassInfo> targets = MixinInfo.this.readTargetClasses(this.classNode, true);
        if (!new HashSet(targets).equals(new HashSet(targetClasses))) {
            throw new MixinReloadException(MixinInfo.this, "Cannot change target classes");
        }
        final int priority = MixinInfo.this.readPriority(this.classNode);
        if (priority != MixinInfo.this.getPriority()) {
            throw new MixinReloadException(MixinInfo.this, "Cannot change mixin priority");
        }
    }
}
