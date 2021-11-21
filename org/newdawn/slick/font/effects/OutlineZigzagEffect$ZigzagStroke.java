package org.newdawn.slick.font.effects;

import java.awt.geom.PathIterator;
import java.awt.BasicStroke;
import java.awt.geom.FlatteningPathIterator;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.Shape;
import java.awt.Stroke;

private class ZigzagStroke implements Stroke
{
    private static final float FLATNESS = 1.0f;
    
    @Override
    public Shape createStrokedShape(final Shape shape) {
        final GeneralPath result = new GeneralPath();
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
        int phase = 0;
        while (!it.isDone()) {
            type = it.currentSegment(points);
            switch (type) {
                case 0: {
                    lastX = (moveX = points[0]);
                    lastY = (moveY = points[1]);
                    result.moveTo(moveX, moveY);
                    next = OutlineZigzagEffect.access$100(OutlineZigzagEffect.this) / 2.0f;
                    break;
                }
                case 4: {
                    points[0] = moveX;
                    points[1] = moveY;
                }
                case 1: {
                    thisX = points[0];
                    thisY = points[1];
                    final float dx = thisX - lastX;
                    final float dy = thisY - lastY;
                    final float distance = (float)Math.sqrt(dx * dx + dy * dy);
                    if (distance >= next) {
                        final float r = 1.0f / distance;
                        while (distance >= next) {
                            final float x = lastX + next * dx * r;
                            final float y = lastY + next * dy * r;
                            if ((phase & 0x1) == 0x0) {
                                result.lineTo(x + OutlineZigzagEffect.access$200(OutlineZigzagEffect.this) * dy * r, y - OutlineZigzagEffect.access$200(OutlineZigzagEffect.this) * dx * r);
                            }
                            else {
                                result.lineTo(x - OutlineZigzagEffect.access$200(OutlineZigzagEffect.this) * dy * r, y + OutlineZigzagEffect.access$200(OutlineZigzagEffect.this) * dx * r);
                            }
                            next += OutlineZigzagEffect.access$100(OutlineZigzagEffect.this);
                            ++phase;
                        }
                    }
                    next -= distance;
                    lastX = thisX;
                    lastY = thisY;
                    if (type == 4) {
                        result.closePath();
                        break;
                    }
                    break;
                }
            }
            it.next();
        }
        return new BasicStroke(OutlineZigzagEffect.this.getWidth(), 2, OutlineZigzagEffect.this.getJoin()).createStrokedShape(result);
    }
}
