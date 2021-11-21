package org.newdawn.slick.geom;

static final class ShapeRenderer$6 implements PointCallback {
    final /* synthetic */ TexCoordGenerator val$gen;
    
    @Override
    public float[] preRenderPoint(final Shape shape, final float x, final float y) {
        final Vector2f tex = this.val$gen.getCoordFor(x, y);
        ShapeRenderer.access$000().glTexCoord2f(tex.x, tex.y);
        return new float[] { x, y };
    }
}