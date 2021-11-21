package org.newdawn.slick.opengl;

import org.newdawn.slick.util.Log;
import java.security.PrivilegedAction;

static final class ImageDataFactory$1 implements PrivilegedAction {
    @Override
    public Object run() {
        final String val = System.getProperty("org.newdawn.slick.pngloader");
        if ("false".equalsIgnoreCase(val)) {
            ImageDataFactory.access$002(false);
        }
        Log.info("Use Java PNG Loader = " + ImageDataFactory.access$000());
        return null;
    }
}