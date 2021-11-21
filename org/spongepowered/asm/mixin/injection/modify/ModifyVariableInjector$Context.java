package org.spongepowered.asm.mixin.injection.modify;

import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.mixin.injection.struct.Target;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.InsnList;

static class Context extends LocalVariableDiscriminator.Context
{
    final InsnList insns;
    
    public Context(final Type returnType, final boolean argsOnly, final Target target, final AbstractInsnNode node) {
        super(returnType, argsOnly, target, node);
        this.insns = new InsnList();
    }
}
