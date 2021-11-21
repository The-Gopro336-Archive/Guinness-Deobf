package org.spongepowered.asm.util;

class KeyValue implements IVariableWidthEntry
{
    private final String key;
    private final Object value;
    
    public KeyValue(final String key, final Object value) {
        this.key = key;
        this.value = value;
    }
    
    @Override
    public String toString() {
        return String.format(PrettyPrinter.this.kvFormat, this.key, this.value);
    }
    
    @Override
    public int getWidth() {
        return this.toString().length();
    }
}
