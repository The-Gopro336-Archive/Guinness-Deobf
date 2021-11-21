package dev.guinness.client.mixin.mixins;

import org.spongepowered.asm.mixin.gen.Accessor;
import net.minecraft.util.Timer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ Timer.class })
public interface ITimer
{
    @Accessor("tickLength")
    void setTickLength(final float p0);
}
