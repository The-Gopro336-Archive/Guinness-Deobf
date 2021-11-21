package org.newdawn.slick;

public interface Renderable
{
    void draw(final float p0, final float p1);
    
    void draw(final float p0, final float p1, final Color p2);
    
    void draw(final float p0, final float p1, final float p2, final float p3);
    
    void draw(final float p0, final float p1, final float p2, final float p3, final Color p4);
}
