package org.spongepowered.asm.mixin.gen;

import java.util.Set;

enum AccessorInfo$AccessorType$3
{
    @Override
    AccessorGenerator getGenerator(final AccessorInfo info) {
        return new AccessorGeneratorMethodProxy(info);
    }
}