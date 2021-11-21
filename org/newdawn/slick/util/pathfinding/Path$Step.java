package org.newdawn.slick.util.pathfinding;

import java.io.Serializable;

public class Step implements Serializable
{
    private int x;
    private int y;
    
    public Step(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    @Override
    public int hashCode() {
        return this.x * this.y;
    }
    
    @Override
    public boolean equals(final Object other) {
        if (other instanceof Step) {
            final Step o = (Step)other;
            return o.x == this.x && o.y == this.y;
        }
        return false;
    }
}
