package org.newdawn.slick;

import org.newdawn.slick.util.Log;
import org.lwjgl.opengl.Display;
import java.security.PrivilegedAction;

static final class AppGameContainer$1 implements PrivilegedAction {
    @Override
    public Object run() {
        try {
            Display.getDisplayMode();
        }
        catch (Exception e) {
            Log.error(e);
        }
        return null;
    }
}