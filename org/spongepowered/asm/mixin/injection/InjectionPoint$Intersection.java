package org.spongepowered.asm.mixin.injection;

import java.lang.reflect.Array;
import java.util.ArrayList;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import java.util.Collection;
import org.spongepowered.asm.lib.tree.InsnList;

static final class Intersection extends CompositeInjectionPoint
{
    public Intersection(final InjectionPoint... points) {
        super(points);
    }
    
    @Override
    public boolean find(final String desc, final InsnList insns, final Collection<AbstractInsnNode> nodes) {
        boolean found = false;
        final ArrayList<AbstractInsnNode>[] allNodes = (ArrayList<AbstractInsnNode>[])Array.newInstance(ArrayList.class, this.components.length);
        for (int i = 0; i < this.components.length; ++i) {
            allNodes[i] = new ArrayList<AbstractInsnNode>();
            this.components[i].find(desc, insns, allNodes[i]);
        }
        final ArrayList<AbstractInsnNode> alpha = allNodes[0];
        for (int nodeIndex = 0; nodeIndex < alpha.size(); ++nodeIndex) {
            final AbstractInsnNode node = alpha.get(nodeIndex);
            final boolean in = true;
            for (int b = 1; b < allNodes.length && allNodes[b].contains(node); ++b) {}
            if (in) {
                nodes.add(node);
                found = true;
            }
        }
        return found;
    }
}
