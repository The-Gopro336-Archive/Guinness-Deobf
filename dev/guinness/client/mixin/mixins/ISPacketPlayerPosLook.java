package dev.guinness.client.mixin.mixins;

import org.spongepowered.asm.mixin.gen.Accessor;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ SPacketPlayerPosLook.class })
public interface ISPacketPlayerPosLook
{
    @Accessor("yaw")
    void setYaw(final float p0);
    
    @Accessor("yaw")
    float getYaw();
    
    @Accessor("pitch")
    void setPitch(final float p0);
    
    @Accessor("pitch")
    float getPitch();
    
    @Accessor("x")
    void setX(final double p0);
    
    @Accessor("y")
    void setY(final double p0);
    
    @Accessor("z")
    void setZ(final double p0);
}
