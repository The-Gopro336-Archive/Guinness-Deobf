package org.spongepowered.asm.mixin.injection;

public enum Selector
{
    FIRST, 
    LAST, 
    ONE;
    
    public static final Selector DEFAULT;
    
    static {
        DEFAULT = Selector.FIRST;
    }
}
