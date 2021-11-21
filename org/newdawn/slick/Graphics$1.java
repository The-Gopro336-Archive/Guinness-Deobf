package org.newdawn.slick;

import org.newdawn.slick.util.Log;
import java.security.PrivilegedAction;

class Graphics$1 implements PrivilegedAction {
    @Override
    public Object run() {
        try {
            Graphics.DEFAULT_FONT = new AngelCodeFont("org/newdawn/slick/data/defaultfont.fnt", "org/newdawn/slick/data/defaultfont.png");
        }
        catch (SlickException e) {
            Log.error(e);
        }
        return null;
    }
}