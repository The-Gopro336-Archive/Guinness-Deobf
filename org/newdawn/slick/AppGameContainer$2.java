package org.newdawn.slick;

import org.newdawn.slick.util.Log;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.PixelFormat;
import java.security.PrivilegedAction;

class AppGameContainer$2 implements PrivilegedAction {
    @Override
    public Object run() {
        try {
            final PixelFormat format = new PixelFormat(8, 8, GameContainer.stencil ? 8 : 0, AppGameContainer.this.samples);
            AppGameContainer.access$000(AppGameContainer.this, format);
            AppGameContainer.this.supportsMultiSample = true;
        }
        catch (Exception e4) {
            Display.destroy();
            try {
                final PixelFormat format2 = new PixelFormat(8, 8, GameContainer.stencil ? 8 : 0);
                AppGameContainer.access$000(AppGameContainer.this, format2);
                AppGameContainer.this.alphaSupport = false;
            }
            catch (Exception e5) {
                Display.destroy();
                try {
                    AppGameContainer.access$000(AppGameContainer.this, (PixelFormat)new Object());
                }
                catch (Exception e3) {
                    Log.error(e3);
                }
            }
        }
        return null;
    }
}