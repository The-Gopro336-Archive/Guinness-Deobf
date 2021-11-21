package dev.guinness.client.mixin.mixins;

import org.spongepowered.asm.mixin.gen.Accessor;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ PlayerControllerMP.class })
public interface IPlayerControllerMP
{
    @Accessor("isHittingBlock")
    boolean isHittingBlock();
    
    @Accessor("blockHitDelay")
    void setBlockHitDelay(final int p0);
}
