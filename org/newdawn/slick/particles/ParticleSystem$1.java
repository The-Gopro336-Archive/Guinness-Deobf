package org.newdawn.slick.particles;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.Log;
import org.newdawn.slick.Image;
import java.security.PrivilegedAction;

class ParticleSystem$1 implements PrivilegedAction {
    @Override
    public Object run() {
        try {
            if (ParticleSystem.access$000(ParticleSystem.this) != null) {
                ParticleSystem.access$102(ParticleSystem.this, new Image(ParticleSystem.access$200(ParticleSystem.this), ParticleSystem.access$000(ParticleSystem.this)));
            }
            else {
                ParticleSystem.access$102(ParticleSystem.this, new Image(ParticleSystem.access$200(ParticleSystem.this)));
            }
        }
        catch (SlickException e) {
            Log.error(e);
            ParticleSystem.access$202(ParticleSystem.this, null);
        }
        return null;
    }
}