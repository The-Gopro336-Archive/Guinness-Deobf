package org.newdawn.slick.util.pathfinding;

import java.util.LinkedList;
import java.util.List;

private class PriorityList
{
    private List list;
    
    private PriorityList() {
        this.list = new LinkedList();
    }
    
    public Object first() {
        return this.list.get(0);
    }
    
    public void clear() {
        this.list.clear();
    }
    
    public void add(final Object o) {
        for (int i = 0; i < this.list.size(); ++i) {
            if (this.list.get(i).compareTo(o) > 0) {
                this.list.add(i, o);
                break;
            }
        }
        if (!this.list.contains(o)) {
            this.list.add(o);
        }
    }
    
    public void remove(final Object o) {
        this.list.remove(o);
    }
    
    public int size() {
        return this.list.size();
    }
    
    public boolean contains(final Object o) {
        return this.list.contains(o);
    }
    
    @Override
    public String toString() {
        String temp = "{";
        for (int i = 0; i < this.size(); ++i) {
            temp = temp + this.list.get(i).toString() + ",";
        }
        temp += "}";
        return temp;
    }
}
