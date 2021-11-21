package org.spongepowered.asm.mixin.injection.code;

import java.util.Collections;
import java.util.HashSet;
import org.spongepowered.asm.mixin.injection.InjectionPoint;
import java.util.Set;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;

public static final class TargetNode
{
    final AbstractInsnNode insn;
    final Set<InjectionPoint> nominators;
    
    TargetNode(final AbstractInsnNode insn) {
        this.nominators = new HashSet<InjectionPoint>();
        this.insn = insn;
    }
    
    public AbstractInsnNode getNode() {
        return this.insn;
    }
    
    public Set<InjectionPoint> getNominators() {
        return Collections.unmodifiableSet((Set<? extends InjectionPoint>)this.nominators);
    }
    
    @Override
    public boolean equals(final Object obj) {
        return obj != null && obj.getClass() == TargetNode.class && ((TargetNode)obj).insn == this.insn;
    }
    
    @Override
    public int hashCode() {
        return this.insn.hashCode();
    }
}
