package dev.guinness.client.mixin.mixins;

import dev.guinness.client.module.Module;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import dev.guinness.client.module.ModuleManager;
import dev.guinness.client.module.modules.movement.NoPush;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ EntityPlayer.class })
public class MixinEntityPlayer
{
    @Inject(method = { "isPushedByWater()Z" }, at = { @At("HEAD") }, cancellable = true)
    public void isPushedByWater(final CallbackInfoReturnable<Boolean> cir) {
        if (ModuleManager.getModule(m -> m.getClass().equals(NoPush.class)).isEnabled()) {
            cir.setReturnValue(false);
        }
    }
    
    @Inject(method = { "applyEntityCollision" }, at = { @At("HEAD") }, cancellable = true)
    public void applyEntityCollision(final Entity p, final CallbackInfo ci) {
        if (ModuleManager.getModule(m -> m.getClass().equals(NoPush.class)).isEnabled()) {
            ci.cancel();
        }
    }
}
