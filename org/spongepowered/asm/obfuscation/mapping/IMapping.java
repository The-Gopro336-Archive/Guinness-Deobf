package org.spongepowered.asm.obfuscation.mapping;

public interface IMapping<TMapping>
{
    Type getType();
    
    TMapping move(final String p0);
    
    TMapping remap(final String p0);
    
    TMapping transform(final String p0);
    
    TMapping copy();
    
    String getName();
    
    String getSimpleName();
    
    String getOwner();
    
    String getDesc();
    
    TMapping getSuper();
    
    String serialise();
    
    public enum Type
    {
        FIELD, 
        METHOD, 
        CLASS, 
        PACKAGE;
    }
}
