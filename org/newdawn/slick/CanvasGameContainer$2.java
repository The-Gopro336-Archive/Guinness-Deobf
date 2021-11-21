package org.newdawn.slick;

class CanvasGameContainer$2 implements Runnable {
    @Override
    public void run() {
        try {
            CanvasGameContainer.this.container.gameLoop();
        }
        catch (SlickException e) {
            e.printStackTrace();
        }
        CanvasGameContainer.this.container.checkDimensions();
        CanvasGameContainer.access$000(CanvasGameContainer.this);
    }
}