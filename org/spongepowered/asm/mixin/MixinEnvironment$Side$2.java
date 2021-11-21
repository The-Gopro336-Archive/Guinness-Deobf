package org.spongepowered.asm.mixin;

import org.spongepowered.asm.service.MixinService;

enum MixinEnvironment$Side$2
{
    @Override
    protected boolean detect() {
        final String sideName = MixinService.getService().getSideName();
        return "CLIENT".equals(sideName);
    }
}