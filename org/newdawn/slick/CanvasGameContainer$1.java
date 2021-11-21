package org.newdawn.slick;

import org.lwjgl.LWJGLException;
import java.awt.Canvas;
import org.lwjgl.opengl.Display;

class CanvasGameContainer$1 implements Runnable {
    @Override
    public void run() {
        try {
            Input.disableControllers();
            try {
                Display.setParent((Canvas)CanvasGameContainer.this);
            }
            catch (LWJGLException e) {
                throw new SlickException("Failed to setParent of canvas", e);
            }
            CanvasGameContainer.this.container.setup();
            CanvasGameContainer.access$000(CanvasGameContainer.this);
        }
        catch (SlickException e2) {
            e2.printStackTrace();
            System.exit(0);
        }
    }
}