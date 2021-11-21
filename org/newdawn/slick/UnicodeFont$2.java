package org.newdawn.slick;

import java.util.Map;
import java.util.LinkedHashMap;

class UnicodeFont$2 extends LinkedHashMap {
    @Override
    protected boolean removeEldestEntry(final Map.Entry eldest) {
        final DisplayList displayList = eldest.getValue();
        if (displayList != null) {
            UnicodeFont.access$002(UnicodeFont.this, displayList.id);
        }
        return this.size() > 200;
    }
}