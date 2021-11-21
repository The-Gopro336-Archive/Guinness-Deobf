package org.newdawn.slick;

import java.util.Map;
import java.util.LinkedHashMap;

class AngelCodeFont$1 extends LinkedHashMap {
    @Override
    protected boolean removeEldestEntry(final Map.Entry eldest) {
        AngelCodeFont.access$002(AngelCodeFont.this, eldest.getValue());
        AngelCodeFont.access$102(AngelCodeFont.this, AngelCodeFont.access$000(AngelCodeFont.this).id);
        return false;
    }
}