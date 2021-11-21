package org.spongepowered.asm.util;

class CentredText
{
    private final Object centred;
    
    public CentredText(final Object centred) {
        this.centred = centred;
    }
    
    @Override
    public String toString() {
        final String text = this.centred.toString();
        return String.format("%" + ((PrettyPrinter.this.width - text.length()) / 2 + text.length()) + "s", text);
    }
}
