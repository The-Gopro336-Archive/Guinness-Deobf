package org.newdawn.slick.font;

import java.util.ListIterator;
import java.util.Iterator;

class GlyphPage$1 implements Iterator {
    final /* synthetic */ ListIterator val$iter;
    
    @Override
    public boolean hasNext() {
        return this.val$iter.hasPrevious();
    }
    
    @Override
    public Object next() {
        return this.val$iter.previous();
    }
    
    @Override
    public void remove() {
        this.val$iter.remove();
    }
}