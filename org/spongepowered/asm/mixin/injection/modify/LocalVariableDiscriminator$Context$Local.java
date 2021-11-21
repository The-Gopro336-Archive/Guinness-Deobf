package org.spongepowered.asm.mixin.injection.modify;

import org.spongepowered.asm.lib.Type;

public class Local
{
    int ord;
    String name;
    Type type;
    
    public Local(final String name, final Type type) {
        this.ord = 0;
        this.name = name;
        this.type = type;
    }
    
    @Override
    public String toString() {
        return String.format("Local[ordinal=%d, name=%s, type=%s]", this.ord, this.name, this.type);
    }
}
