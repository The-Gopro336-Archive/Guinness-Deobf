package org.newdawn.slick.geom;

import org.newdawn.slick.Image;
import org.newdawn.slick.ShapeFill;

static final class ShapeRenderer$5 implements PointCallback {
    final /* synthetic */ ShapeFill val$fill;
    final /* synthetic */ float[] val$center;
    final /* synthetic */ float val$scaleX;
    final /* synthetic */ float val$scaleY;
    final /* synthetic */ Image val$image;
    
    @Override
    public float[] preRenderPoint(final Shape shape, float x, float y) {
        this.val$fill.colorAt(shape, x - this.val$center[0], y - this.val$center[1]).bind();
        final Vector2f offset = this.val$fill.getOffsetAt(shape, x, y);
        x += offset.x;
        y += offset.y;
        float tx = x * this.val$scaleX;
        float ty = y * this.val$scaleY;
        tx = this.val$image.getTextureOffsetX() + this.val$image.getTextureWidth() * tx;
        ty = this.val$image.getTextureOffsetY() + this.val$image.getTextureHeight() * ty;
        ShapeRenderer.access$000().glTexCoord2f(tx, ty);
        return new float[] { offset.x + x, offset.y + y };
    }
}