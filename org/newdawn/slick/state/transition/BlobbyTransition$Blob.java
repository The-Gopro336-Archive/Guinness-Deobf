package org.newdawn.slick.state.transition;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.GameContainer;

private class Blob
{
    private float x;
    private float y;
    private float growSpeed;
    private float rad;
    
    public Blob(final GameContainer container) {
        this.x = (float)(Math.random() * container.getWidth());
        this.y = (float)(Math.random() * container.getWidth());
        this.growSpeed = (float)(1.0 + Math.random() * 1.0);
    }
    
    public void update(final int delta) {
        this.rad += this.growSpeed * delta * 0.4f;
    }
    
    public void render(final Graphics g) {
        g.fillOval(this.x - this.rad, this.y - this.rad, this.rad * 2.0f, this.rad * 2.0f);
    }
}
