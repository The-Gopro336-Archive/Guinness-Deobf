package org.newdawn.slick.opengl;

import org.newdawn.slick.util.Log;
import java.nio.ByteBuffer;

private class ReloadData
{
    private int srcPixelFormat;
    private int componentCount;
    private int minFilter;
    private int magFilter;
    private ByteBuffer textureBuffer;
    
    public int reload() {
        Log.error("Reloading texture: " + TextureImpl.access$600(TextureImpl.this));
        return InternalTextureLoader.get().reload(TextureImpl.this, this.srcPixelFormat, this.componentCount, this.minFilter, this.magFilter, this.textureBuffer);
    }
}
