package org.newdawn.slick;

import java.awt.Canvas;

class AppletGameContainer$2 extends Canvas {
    @Override
    public final void addNotify() {
        super.addNotify();
        AppletGameContainer.this.startLWJGL();
    }
    
    @Override
    public final void removeNotify() {
        AppletGameContainer.access$000(AppletGameContainer.this);
        super.removeNotify();
    }
}