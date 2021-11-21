package org.newdawn.slick.geom;

private class Point
{
    private float x;
    private float y;
    private float[] array;
    
    public Point(final float x, final float y) {
        this.x = x;
        this.y = y;
        this.array = new float[] { x, y };
    }
    
    public float getX() {
        return this.x;
    }
    
    public float getY() {
        return this.y;
    }
    
    public float[] toArray() {
        return this.array;
    }
    
    @Override
    public int hashCode() {
        return (int)(this.x * this.y * 31.0f);
    }
    
    @Override
    public boolean equals(final Object other) {
        if (other instanceof Point) {
            final Point p = (Point)other;
            return p.x == this.x && p.y == this.y;
        }
        return false;
    }
}
