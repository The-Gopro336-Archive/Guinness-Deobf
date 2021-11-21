package org.newdawn.slick.particles;

public class SimpleValue implements Value
{
    private float value;
    private float next;
    
    private SimpleValue(final float value) {
        this.value = value;
    }
    
    @Override
    public float getValue(final float time) {
        return this.value;
    }
    
    public void setValue(final float value) {
        this.value = value;
    }
}
