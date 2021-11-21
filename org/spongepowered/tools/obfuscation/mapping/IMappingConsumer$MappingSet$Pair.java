package org.spongepowered.tools.obfuscation.mapping;

import com.google.common.base.Objects;
import org.spongepowered.asm.obfuscation.mapping.IMapping;

public static class Pair<TMapping extends IMapping<TMapping>>
{
    public final TMapping from;
    public final TMapping to;
    
    public Pair(final TMapping from, final TMapping to) {
        this.from = from;
        this.to = to;
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof Pair)) {
            return false;
        }
        final Pair<TMapping> other = (Pair<TMapping>)obj;
        return Objects.equal(this.from, other.from) && Objects.equal(this.to, other.to);
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(this.from, this.to);
    }
    
    @Override
    public String toString() {
        return String.format("%s -> %s", this.from, this.to);
    }
}
