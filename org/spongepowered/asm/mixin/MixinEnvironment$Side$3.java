package org.spongepowered.asm.mixin;

import org.spongepowered.asm.service.MixinService;

enum MixinEnvironment$Side$3
{
    @Override
    protected boolean detect() {
        final String sideName = MixinService.getService().getSideName();
        return "SERVER".equals(sideName) || "DEDICATEDSERVER".equals(sideName);
    }
}