package dev.guinness.client.mixin.mixins;

import org.spongepowered.asm.mixin.gen.Accessor;
import net.minecraft.network.play.client.CPacketPlayer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ CPacketPlayer.class })
public interface ICPacketPlayer
{
    @Accessor("onGround")
    void setOnGround(final boolean p0);
    
    @Accessor("yaw")
    void setYaw(final float p0);
    
    @Accessor("pitch")
    void setPitch(final float p0);
}
