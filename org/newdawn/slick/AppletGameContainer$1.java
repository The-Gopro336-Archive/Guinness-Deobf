package org.newdawn.slick;

import java.awt.Component;
import org.lwjgl.opengl.Display;

class AppletGameContainer$1 extends Thread {
    @Override
    public void run() {
        try {
            AppletGameContainer.this.canvas.start();
        }
        catch (Exception e) {
            e.printStackTrace();
            if (Display.isCreated()) {
                Display.destroy();
            }
            AppletGameContainer.this.displayParent.setVisible(false);
            AppletGameContainer.this.add(new ConsolePanel(e));
            AppletGameContainer.this.validate();
        }
    }
}