package org.spongepowered.asm.mixin;

public enum Remap
{
    ALL, 
    FORCE(true), 
    ONLY_PREFIXED, 
    NONE;
    
    private final boolean forceRemap;
    
    private Remap() {
        this(false);
    }
    
    private Remap(final boolean forceRemap) {
        this.forceRemap = forceRemap;
    }
    
    public boolean forceRemap() {
        return this.forceRemap;
    }
}
