package org.spongepowered.asm.util;

import com.google.common.base.Strings;

class HorizontalRule implements ISpecialEntry
{
    private final char[] hrChars;
    
    public HorizontalRule(final char... hrChars) {
        this.hrChars = hrChars;
    }
    
    @Override
    public String toString() {
        return Strings.repeat(new String(this.hrChars), PrettyPrinter.this.width + 2);
    }
}
