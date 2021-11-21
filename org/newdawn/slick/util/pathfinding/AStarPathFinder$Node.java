package org.newdawn.slick.util.pathfinding;

private class Node implements Comparable
{
    private int x;
    private int y;
    private float cost;
    private Node parent;
    private float heuristic;
    private int depth;
    private boolean open;
    private boolean closed;
    
    public Node(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
    
    public int setParent(final Node parent) {
        this.depth = parent.depth + 1;
        this.parent = parent;
        return this.depth;
    }
    
    @Override
    public int compareTo(final Object other) {
        final Node o = (Node)other;
        final float f = this.heuristic + this.cost;
        final float of = o.heuristic + o.cost;
        if (f < of) {
            return -1;
        }
        if (f > of) {
            return 1;
        }
        return 0;
    }
    
    public void setOpen(final boolean open) {
        this.open = open;
    }
    
    public boolean isOpen() {
        return this.open;
    }
    
    public void setClosed(final boolean closed) {
        this.closed = closed;
    }
    
    public boolean isClosed() {
        return this.closed;
    }
    
    public void reset() {
        this.closed = false;
        this.open = false;
        this.cost = 0.0f;
        this.depth = 0;
    }
    
    @Override
    public String toString() {
        return "[Node " + this.x + "," + this.y + "]";
    }
}
