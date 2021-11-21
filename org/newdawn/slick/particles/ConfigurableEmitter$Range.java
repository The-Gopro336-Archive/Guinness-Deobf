package org.newdawn.slick.particles;

public class Range
{
    private float max;
    private float min;
    private boolean enabled;
    
    private Range(final float min, final float max) {
        this.enabled = false;
        this.min = min;
        this.max = max;
    }
    
    public float random() {
        return (float)(this.min + Math.random() * (this.max - this.min));
    }
    
    public boolean isEnabled() {
        return this.enabled;
    }
    
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }
    
    public float getMax() {
        return this.max;
    }
    
    public void setMax(final float max) {
        this.max = max;
    }
    
    public float getMin() {
        return this.min;
    }
    
    public void setMin(final float min) {
        this.min = min;
    }
}
