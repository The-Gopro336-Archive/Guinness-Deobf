package org.spongepowered.asm.lib.tree;

import java.util.ArrayList;

class MethodNode$1 extends ArrayList<Object> {
    @Override
    public boolean add(final Object o) {
        MethodNode.this.annotationDefault = o;
        return super.add(o);
    }
}