package org.newdawn.slick.particles;

public class RandomValue implements Value
{
    private float value;
    
    private RandomValue(final float value) {
        this.value = value;
    }
    
    @Override
    public float getValue(final float time) {
        return (float)(Math.random() * this.value);
    }
    
    public void setValue(final float value) {
        this.value = value;
    }
    
    public float getValue() {
        return this.value;
    }
}
