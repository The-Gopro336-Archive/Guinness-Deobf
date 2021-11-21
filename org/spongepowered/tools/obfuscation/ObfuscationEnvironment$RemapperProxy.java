package org.spongepowered.tools.obfuscation;

import org.spongepowered.asm.util.ObfuscationUtil;

final class RemapperProxy implements ObfuscationUtil.IClassRemapper
{
    @Override
    public String map(final String typeName) {
        if (ObfuscationEnvironment.this.mappingProvider == null) {
            return null;
        }
        return ObfuscationEnvironment.this.mappingProvider.getClassMapping(typeName);
    }
    
    @Override
    public String unmap(final String typeName) {
        if (ObfuscationEnvironment.this.mappingProvider == null) {
            return null;
        }
        return ObfuscationEnvironment.this.mappingProvider.getClassMapping(typeName);
    }
}
