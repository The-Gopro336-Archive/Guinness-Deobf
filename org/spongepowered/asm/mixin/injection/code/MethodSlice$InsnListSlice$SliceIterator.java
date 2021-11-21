package org.spongepowered.asm.mixin.injection.code;

import java.util.NoSuchElementException;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import java.util.ListIterator;

static class SliceIterator implements ListIterator<AbstractInsnNode>
{
    private final ListIterator<AbstractInsnNode> iter;
    private int start;
    private int end;
    private int index;
    
    public SliceIterator(final ListIterator<AbstractInsnNode> iter, final int start, final int end, final int index) {
        this.iter = iter;
        this.start = start;
        this.end = end;
        this.index = index;
    }
    
    @Override
    public boolean hasNext() {
        return this.index <= this.end && this.iter.hasNext();
    }
    
    @Override
    public AbstractInsnNode next() {
        if (this.index > this.end) {
            throw new NoSuchElementException();
        }
        ++this.index;
        return this.iter.next();
    }
    
    @Override
    public boolean hasPrevious() {
        return this.index > this.start;
    }
    
    @Override
    public AbstractInsnNode previous() {
        if (this.index <= this.start) {
            throw new NoSuchElementException();
        }
        --this.index;
        return this.iter.previous();
    }
    
    @Override
    public int nextIndex() {
        return this.index - this.start;
    }
    
    @Override
    public int previousIndex() {
        return this.index - this.start - 1;
    }
    
    @Override
    public void remove() {
        throw new UnsupportedOperationException("Cannot remove insn from slice");
    }
    
    @Override
    public void set(final AbstractInsnNode e) {
        throw new UnsupportedOperationException("Cannot set insn using slice");
    }
    
    @Override
    public void add(final AbstractInsnNode e) {
        throw new UnsupportedOperationException("Cannot add insn using slice");
    }
}
