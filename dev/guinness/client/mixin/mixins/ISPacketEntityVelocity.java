package dev.guinness.client.mixin.mixins;

import org.spongepowered.asm.mixin.gen.Accessor;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ SPacketEntityVelocity.class })
public interface ISPacketEntityVelocity
{
    @Accessor("motionX")
    void setMotionX(final int p0);
    
    @Accessor("motionY")
    void setMotionY(final int p0);
    
    @Accessor("motionZ")
    void setMotionZ(final int p0);
    
    @Accessor("motionX")
    int getMotionX();
    
    @Accessor("motionY")
    int getMotionY();
    
    @Accessor("motionZ")
    int getMotionZ();
}
