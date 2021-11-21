package org.newdawn.slick;

import java.nio.ByteBuffer;
import org.newdawn.slick.opengl.LoadableImageData;
import org.newdawn.slick.opengl.ImageData;

class BigImage$2 implements ImageData {
    final /* synthetic */ LoadableImageData val$data;
    final /* synthetic */ int val$imageHeight;
    final /* synthetic */ int val$imageWidth;
    final /* synthetic */ ByteBuffer val$subBuffer;
    final /* synthetic */ int val$ySize;
    final /* synthetic */ int val$xSize;
    
    @Override
    public int getDepth() {
        return this.val$data.getDepth();
    }
    
    @Override
    public int getHeight() {
        return this.val$imageHeight;
    }
    
    @Override
    public int getWidth() {
        return this.val$imageWidth;
    }
    
    @Override
    public ByteBuffer getImageBufferData() {
        return this.val$subBuffer;
    }
    
    @Override
    public int getTexHeight() {
        return this.val$ySize;
    }
    
    @Override
    public int getTexWidth() {
        return this.val$xSize;
    }
}