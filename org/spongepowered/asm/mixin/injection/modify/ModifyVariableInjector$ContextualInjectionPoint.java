package org.spongepowered.asm.mixin.injection.modify;

import org.spongepowered.asm.mixin.injection.struct.Target;
import org.spongepowered.asm.mixin.injection.throwables.InvalidInjectionException;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import java.util.Collection;
import org.spongepowered.asm.lib.tree.InsnList;
import org.spongepowered.asm.mixin.refmap.IMixinContext;
import org.spongepowered.asm.mixin.injection.InjectionPoint;

abstract static class ContextualInjectionPoint extends InjectionPoint
{
    protected final IMixinContext context;
    
    ContextualInjectionPoint(final IMixinContext context) {
        this.context = context;
    }
    
    @Override
    public boolean find(final String desc, final InsnList insns, final Collection<AbstractInsnNode> nodes) {
        throw new InvalidInjectionException(this.context, this.getAtCode() + " injection point must be used in conjunction with @ModifyVariable");
    }
    
    abstract boolean find(final Target p0, final Collection<AbstractInsnNode> p1);
}
