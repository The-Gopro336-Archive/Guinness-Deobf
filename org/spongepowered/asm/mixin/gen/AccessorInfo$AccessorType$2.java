package org.spongepowered.asm.mixin.gen;

import java.util.Set;

enum AccessorInfo$AccessorType$2
{
    @Override
    AccessorGenerator getGenerator(final AccessorInfo info) {
        return new AccessorGeneratorFieldSetter(info);
    }
}