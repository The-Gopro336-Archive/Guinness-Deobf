package org.spongepowered.asm.mixin.injection;

import java.util.ArrayList;
import java.util.List;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import java.util.Collection;
import org.spongepowered.asm.lib.tree.InsnList;

static final class Shift extends InjectionPoint
{
    private final InjectionPoint input;
    private final int shift;
    
    public Shift(final InjectionPoint input, final int shift) {
        if (input == null) {
            throw new IllegalArgumentException("Must supply an input injection point for SHIFT");
        }
        this.input = input;
        this.shift = shift;
    }
    
    @Override
    public String toString() {
        return "InjectionPoint(" + this.getClass().getSimpleName() + ")[" + this.input + "]";
    }
    
    @Override
    public boolean find(final String desc, final InsnList insns, final Collection<AbstractInsnNode> nodes) {
        final List<AbstractInsnNode> list = (nodes instanceof List) ? ((List)nodes) : new ArrayList<AbstractInsnNode>(nodes);
        this.input.find(desc, insns, nodes);
        for (int i = 0; i < list.size(); ++i) {
            list.set(i, insns.get(insns.indexOf(list.get(i)) + this.shift));
        }
        if (nodes != list) {
            nodes.clear();
            nodes.addAll(list);
        }
        return nodes.size() > 0;
    }
}
