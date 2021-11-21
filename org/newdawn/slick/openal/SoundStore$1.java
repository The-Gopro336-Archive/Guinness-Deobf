package org.newdawn.slick.openal;

import org.newdawn.slick.util.Log;
import org.lwjgl.openal.AL;
import java.security.PrivilegedAction;

class SoundStore$1 implements PrivilegedAction {
    @Override
    public Object run() {
        try {
            AL.create();
            SoundStore.access$002(SoundStore.this, true);
            SoundStore.access$102(SoundStore.this, true);
            SoundStore.access$202(SoundStore.this, true);
            Log.info("- Sound works");
        }
        catch (Exception e) {
            Log.error("Sound initialisation failure.");
            Log.error(e);
            SoundStore.access$002(SoundStore.this, false);
            SoundStore.access$102(SoundStore.this, false);
            SoundStore.access$202(SoundStore.this, false);
        }
        return null;
    }
}