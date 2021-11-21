package org.newdawn.slick;

private class Frame
{
    public Image image;
    public int duration;
    public int x;
    public int y;
    
    public Frame(final Image image, final int duration) {
        this.x = -1;
        this.y = -1;
        this.image = image;
        this.duration = duration;
    }
    
    public Frame(final int duration, final int x, final int y) {
        this.x = -1;
        this.y = -1;
        this.image = Animation.access$000(Animation.this).getSubImage(x, y);
        this.duration = duration;
        this.x = x;
        this.y = y;
    }
}
