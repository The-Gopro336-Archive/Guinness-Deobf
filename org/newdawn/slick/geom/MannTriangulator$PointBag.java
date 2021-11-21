package org.newdawn.slick.geom;

import java.io.Serializable;

protected class PointBag implements Serializable
{
    protected Point first;
    protected PointBag next;
    
    public void clear() {
        if (this.first != null) {
            MannTriangulator.access$000(MannTriangulator.this, this.first);
            this.first = null;
        }
    }
    
    public void add(final Point p) {
        if (this.first != null) {
            this.first.insertBefore(p);
        }
        else {
            this.first = p;
            p.next = p;
            p.prev = p;
        }
    }
    
    public void computeAngles() {
        if (this.first == null) {
            return;
        }
        Point p = this.first;
        do {
            p.computeAngle();
        } while ((p = p.next) != this.first);
    }
    
    public boolean doesIntersectSegment(final Vector2f v1, final Vector2f v2) {
        final double dxA = v2.x - v1.x;
        final double dyA = v2.y - v1.y;
        Point p = this.first;
        while (true) {
            final Point n = p.next;
            if (p.pt != v1 && n.pt != v1 && p.pt != v2 && n.pt != v2) {
                final double dxB = n.pt.x - p.pt.x;
                final double dyB = n.pt.y - p.pt.y;
                final double d = dxA * dyB - dyA * dxB;
                if (Math.abs(d) > 1.0E-5) {
                    final double tmp1 = p.pt.x - v1.x;
                    final double tmp2 = p.pt.y - v1.y;
                    final double tA = (dyB * tmp1 - dxB * tmp2) / d;
                    final double tB = (dyA * tmp1 - dxA * tmp2) / d;
                    if (tA >= 0.0 && tA <= 1.0 && tB >= 0.0 && tB <= 1.0) {
                        return true;
                    }
                }
            }
            if (n == this.first) {
                return false;
            }
            p = n;
        }
    }
    
    public int countPoints() {
        if (this.first == null) {
            return 0;
        }
        int count = 0;
        Point p = this.first;
        do {
            ++count;
        } while ((p = p.next) != this.first);
        return count;
    }
    
    public boolean contains(final Vector2f point) {
        return this.first != null && (this.first.prev.pt.equals(point) || this.first.pt.equals(point));
    }
}
