package org.newdawn.slick.font.effects;

import java.awt.geom.PathIterator;
import java.awt.geom.FlatteningPathIterator;
import java.awt.geom.AffineTransform;
import java.awt.BasicStroke;
import java.awt.geom.GeneralPath;
import java.awt.Shape;
import java.awt.Stroke;

private class WobbleStroke implements Stroke
{
    private static final float FLATNESS = 1.0f;
    
    @Override
    public Shape createStrokedShape(Shape shape) {
        final GeneralPath result = new GeneralPath();
        shape = new BasicStroke(OutlineWobbleEffect.this.getWidth(), 2, OutlineWobbleEffect.this.getJoin()).createStrokedShape(shape);
        final PathIterator it = new FlatteningPathIterator(shape.getPathIterator(null), 1.0);
        final float[] points = new float[6];
        float moveX = 0.0f;
        float moveY = 0.0f;
        float lastX = 0.0f;
        float lastY = 0.0f;
        float thisX = 0.0f;
        float thisY = 0.0f;
        int type = 0;
        float next = 0.0f;
        while (!it.isDone()) {
            type = it.currentSegment(points);
            switch (type) {
                case 0: {
                    lastX = (moveX = this.randomize(points[0]));
                    lastY = (moveY = this.randomize(points[1]));
                    result.moveTo(moveX, moveY);
                    next = 0.0f;
                    break;
                }
                case 4: {
                    points[0] = moveX;
                    points[1] = moveY;
                }
                case 1: {
                    thisX = this.randomize(points[0]);
                    thisY = this.randomize(points[1]);
                    final float dx = thisX - lastX;
                    final float dy = thisY - lastY;
                    final float distance = (float)Math.sqrt(dx * dx + dy * dy);
                    if (distance >= next) {
                        final float r = 1.0f / distance;
                        while (distance >= next) {
                            final float x = lastX + next * dx * r;
                            final float y = lastY + next * dy * r;
                            result.lineTo(this.randomize(x), this.randomize(y));
                            next += OutlineWobbleEffect.access$100(OutlineWobbleEffect.this);
                        }
                    }
                    next -= distance;
                    lastX = thisX;
                    lastY = thisY;
                    break;
                }
            }
            it.next();
        }
        return result;
    }
    
    private float randomize(final float x) {
        return x + (float)Math.random() * OutlineWobbleEffect.access$200(OutlineWobbleEffect.this) * 2.0f - 1.0f;
    }
}
