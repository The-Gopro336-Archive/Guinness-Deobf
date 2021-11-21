package org.newdawn.slick.geom;

import org.newdawn.slick.Image;

static final class ShapeRenderer$2 implements PointCallback {
    final /* synthetic */ float val$scaleX;
    final /* synthetic */ float val$scaleY;
    final /* synthetic */ Image val$image;
    
    @Override
    public float[] preRenderPoint(final Shape shape, final float x, final float y) {
        float tx = x * this.val$scaleX;
        float ty = y * this.val$scaleY;
        tx = this.val$image.getTextureOffsetX() + this.val$image.getTextureWidth() * tx;
        ty = this.val$image.getTextureOffsetY() + this.val$image.getTextureHeight() * ty;
        ShapeRenderer.access$000().glTexCoord2f(tx, ty);
        return null;
    }
}