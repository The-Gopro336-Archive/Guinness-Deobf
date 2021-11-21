package org.newdawn.slick.geom;

import java.util.ArrayList;

private class PointList
{
    private ArrayList points;
    
    public PointList() {
        this.points = new ArrayList();
    }
    
    public boolean contains(final Point p) {
        return this.points.contains(p);
    }
    
    public void add(final Point point) {
        this.points.add(point);
    }
    
    public void remove(final Point point) {
        this.points.remove(point);
    }
    
    public int size() {
        return this.points.size();
    }
    
    public Point get(final int i) {
        return this.points.get(i);
    }
    
    public void clear() {
        this.points.clear();
    }
}
