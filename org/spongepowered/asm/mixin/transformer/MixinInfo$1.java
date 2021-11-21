package org.spongepowered.asm.mixin.transformer;

import org.spongepowered.asm.lib.Type;
import com.google.common.base.Function;

class MixinInfo$1 implements Function<Type, String> {
    @Override
    public String apply(final Type input) {
        return input.getClassName();
    }
}