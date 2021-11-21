package org.spongepowered.asm.mixin.injection;

import java.util.LinkedHashSet;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import java.util.Collection;
import org.spongepowered.asm.lib.tree.InsnList;

static final class Union extends CompositeInjectionPoint
{
    public Union(final InjectionPoint... points) {
        super(points);
    }
    
    @Override
    public boolean find(final String desc, final InsnList insns, final Collection<AbstractInsnNode> nodes) {
        final LinkedHashSet<AbstractInsnNode> allNodes = new LinkedHashSet<AbstractInsnNode>();
        for (int i = 0; i < this.components.length; ++i) {
            this.components[i].find(desc, insns, allNodes);
        }
        nodes.addAll(allNodes);
        return allNodes.size() > 0;
    }
}
