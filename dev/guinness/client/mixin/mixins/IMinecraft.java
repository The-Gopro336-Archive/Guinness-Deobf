package dev.guinness.client.mixin.mixins;

import org.spongepowered.asm.mixin.gen.Accessor;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ Minecraft.class })
public interface IMinecraft
{
    @Accessor("rightClickDelayTimer")
    void setRightClickDelayTimer(final int p0);
}
