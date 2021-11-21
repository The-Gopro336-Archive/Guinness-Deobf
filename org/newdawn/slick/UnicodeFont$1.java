package org.newdawn.slick;

import org.newdawn.slick.font.Glyph;
import java.util.Comparator;

static final class UnicodeFont$1 implements Comparator {
    @Override
    public int compare(final Object o1, final Object o2) {
        return ((Glyph)o1).getHeight() - ((Glyph)o2).getHeight();
    }
}