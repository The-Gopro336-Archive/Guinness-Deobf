package org.newdawn.slick;

import java.nio.ByteBuffer;
import org.newdawn.slick.opengl.LoadableImageData;
import org.newdawn.slick.opengl.ImageData;

class BigImage$1 implements ImageData {
    final /* synthetic */ LoadableImageData val$data;
    final /* synthetic */ int val$dataHeight;
    final /* synthetic */ ByteBuffer val$imageBuffer;
    final /* synthetic */ int val$dataWidth;
    
    @Override
    public int getDepth() {
        return this.val$data.getDepth();
    }
    
    @Override
    public int getHeight() {
        return this.val$dataHeight;
    }
    
    @Override
    public ByteBuffer getImageBufferData() {
        return this.val$imageBuffer;
    }
    
    @Override
    public int getTexHeight() {
        return this.val$dataHeight;
    }
    
    @Override
    public int getTexWidth() {
        return this.val$dataWidth;
    }
    
    @Override
    public int getWidth() {
        return this.val$dataWidth;
    }
}