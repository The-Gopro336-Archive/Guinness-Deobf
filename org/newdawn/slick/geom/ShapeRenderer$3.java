package org.newdawn.slick.geom;

import org.newdawn.slick.Image;

static final class ShapeRenderer$3 implements PointCallback {
    final /* synthetic */ float val$scaleX;
    final /* synthetic */ float val$scaleY;
    final /* synthetic */ Image val$image;
    
    @Override
    public float[] preRenderPoint(final Shape shape, float x, float y) {
        x -= shape.getMinX();
        y -= shape.getMinY();
        x /= shape.getMaxX() - shape.getMinX();
        y /= shape.getMaxY() - shape.getMinY();
        float tx = x * this.val$scaleX;
        float ty = y * this.val$scaleY;
        tx = this.val$image.getTextureOffsetX() + this.val$image.getTextureWidth() * tx;
        ty = this.val$image.getTextureOffsetY() + this.val$image.getTextureHeight() * ty;
        ShapeRenderer.access$000().glTexCoord2f(tx, ty);
        return null;
    }
}