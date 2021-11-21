package org.spongepowered.asm.mixin.injection.modify;

import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import java.util.Collection;
import org.spongepowered.asm.lib.tree.VarInsnNode;

static class SearchState
{
    private final boolean print;
    private final int targetOrdinal;
    private int ordinal;
    private boolean pendingCheck;
    private boolean found;
    private VarInsnNode varNode;
    
    SearchState(final int targetOrdinal, final boolean print) {
        this.ordinal = 0;
        this.pendingCheck = false;
        this.found = false;
        this.targetOrdinal = targetOrdinal;
        this.print = print;
    }
    
    boolean success() {
        return this.found;
    }
    
    boolean isPendingCheck() {
        return this.pendingCheck;
    }
    
    void setPendingCheck() {
        this.pendingCheck = true;
    }
    
    void register(final VarInsnNode node) {
        this.varNode = node;
    }
    
    void check(final Collection<AbstractInsnNode> nodes, final AbstractInsnNode insn, final int local) {
        this.pendingCheck = false;
        if (local != this.varNode.var && (local > -2 || !this.print)) {
            return;
        }
        if (this.targetOrdinal == -1 || this.targetOrdinal == this.ordinal) {
            nodes.add(insn);
            this.found = true;
        }
        ++this.ordinal;
        this.varNode = null;
    }
}
