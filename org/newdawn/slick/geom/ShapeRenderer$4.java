package org.newdawn.slick.geom;

import org.newdawn.slick.ShapeFill;

static final class ShapeRenderer$4 implements PointCallback {
    final /* synthetic */ ShapeFill val$fill;
    
    @Override
    public float[] preRenderPoint(final Shape shape, final float x, final float y) {
        this.val$fill.colorAt(shape, x, y).bind();
        final Vector2f offset = this.val$fill.getOffsetAt(shape, x, y);
        return new float[] { offset.x + x, offset.y + y };
    }
}