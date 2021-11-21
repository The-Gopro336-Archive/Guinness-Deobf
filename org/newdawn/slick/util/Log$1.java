package org.newdawn.slick.util;

import java.security.PrivilegedAction;

static final class Log$1 implements PrivilegedAction {
    @Override
    public Object run() {
        final String val = System.getProperty("org.newdawn.slick.forceVerboseLog");
        if (val != null && val.equalsIgnoreCase("true")) {
            Log.setForcedVerboseOn();
        }
        return null;
    }
}