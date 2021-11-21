package org.spongepowered.asm.mixin;

import org.spongepowered.asm.util.JavaVersion;

enum MixinEnvironment$CompatibilityLevel$2
{
    @Override
    boolean isSupported() {
        return JavaVersion.current() >= 1.8;
    }
}