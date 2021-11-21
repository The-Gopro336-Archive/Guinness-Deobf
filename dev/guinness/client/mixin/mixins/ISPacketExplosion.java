package dev.guinness.client.mixin.mixins;

import org.spongepowered.asm.mixin.gen.Accessor;
import net.minecraft.network.play.server.SPacketExplosion;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ SPacketExplosion.class })
public interface ISPacketExplosion
{
    @Accessor("motionX")
    void setMotionX(final float p0);
    
    @Accessor("motionY")
    void setMotionY(final float p0);
    
    @Accessor("motionZ")
    void setMotionZ(final float p0);
    
    @Accessor("motionX")
    float getMotionX();
    
    @Accessor("motionY")
    float getMotionY();
    
    @Accessor("motionZ")
    float getMotionZ();
}
