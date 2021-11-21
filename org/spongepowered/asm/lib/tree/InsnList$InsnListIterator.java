package org.spongepowered.asm.lib.tree;

import java.util.NoSuchElementException;
import java.util.ListIterator;

private final class InsnListIterator implements ListIterator
{
    AbstractInsnNode next;
    AbstractInsnNode prev;
    AbstractInsnNode remove;
    
    InsnListIterator(final int index) {
        if (index == InsnList.this.size()) {
            this.next = null;
            this.prev = InsnList.this.getLast();
        }
        else {
            this.next = InsnList.this.get(index);
            this.prev = this.next.prev;
        }
    }
    
    public boolean hasNext() {
        return this.next != null;
    }
    
    public Object next() {
        if (this.next == null) {
            throw new NoSuchElementException();
        }
        final AbstractInsnNode result = this.next;
        this.prev = result;
        this.next = result.next;
        return this.remove = result;
    }
    
    public void remove() {
        if (this.remove != null) {
            if (this.remove == this.next) {
                this.next = this.next.next;
            }
            else {
                this.prev = this.prev.prev;
            }
            InsnList.this.remove(this.remove);
            this.remove = null;
            return;
        }
        throw new IllegalStateException();
    }
    
    public boolean hasPrevious() {
        return this.prev != null;
    }
    
    public Object previous() {
        final AbstractInsnNode result = this.prev;
        this.next = result;
        this.prev = result.prev;
        return this.remove = result;
    }
    
    public int nextIndex() {
        if (this.next == null) {
            return InsnList.this.size();
        }
        if (InsnList.this.cache == null) {
            InsnList.this.cache = InsnList.this.toArray();
        }
        return this.next.index;
    }
    
    public int previousIndex() {
        if (this.prev == null) {
            return -1;
        }
        if (InsnList.this.cache == null) {
            InsnList.this.cache = InsnList.this.toArray();
        }
        return this.prev.index;
    }
    
    public void add(final Object o) {
        if (this.next != null) {
            InsnList.this.insertBefore(this.next, (AbstractInsnNode)o);
        }
        else if (this.prev != null) {
            InsnList.this.insert(this.prev, (AbstractInsnNode)o);
        }
        else {
            InsnList.this.add((AbstractInsnNode)o);
        }
        this.prev = (AbstractInsnNode)o;
        this.remove = null;
    }
    
    public void set(final Object o) {
        if (this.remove != null) {
            InsnList.this.set(this.remove, (AbstractInsnNode)o);
            if (this.remove == this.prev) {
                this.prev = (AbstractInsnNode)o;
            }
            else {
                this.next = (AbstractInsnNode)o;
            }
            return;
        }
        throw new IllegalStateException();
    }
}
