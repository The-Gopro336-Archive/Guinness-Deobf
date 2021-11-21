package org.newdawn.slick.particles;

import org.newdawn.slick.geom.Vector2f;
import java.util.ArrayList;

public class LinearInterpolator implements Value
{
    private ArrayList curve;
    private boolean active;
    private int min;
    private int max;
    
    public LinearInterpolator(final ArrayList curve, final int min, final int max) {
        this.curve = curve;
        this.min = min;
        this.max = max;
        this.active = false;
    }
    
    public void setCurve(final ArrayList curve) {
        this.curve = curve;
    }
    
    public ArrayList getCurve() {
        return this.curve;
    }
    
    @Override
    public float getValue(final float t) {
        Vector2f p0 = this.curve.get(0);
        for (int i = 1; i < this.curve.size(); ++i) {
            final Vector2f p2 = this.curve.get(i);
            if (t >= p0.getX() && t <= p2.getX()) {
                final float st = (t - p0.getX()) / (p2.getX() - p0.getX());
                final float r = p0.getY() + st * (p2.getY() - p0.getY());
                return r;
            }
            p0 = p2;
        }
        return 0.0f;
    }
    
    public boolean isActive() {
        return this.active;
    }
    
    public void setActive(final boolean active) {
        this.active = active;
    }
    
    public int getMax() {
        return this.max;
    }
    
    public void setMax(final int max) {
        this.max = max;
    }
    
    public int getMin() {
        return this.min;
    }
    
    public void setMin(final int min) {
        this.min = min;
    }
}
