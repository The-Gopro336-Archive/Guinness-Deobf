package dev.guinness.client.mixin.mixins;

import dev.guinness.client.module.Module;
import dev.guinness.client.module.ModuleManager;
import dev.guinness.client.module.modules.exploit.Reach;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.common.MinecraftForge;
import dev.guinness.client.event.events.DamageBlockEvent;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import org.spongepowered.asm.mixin.Mixin;
import dev.guinness.client.util.Wrapper;

@Mixin({ PlayerControllerMP.class })
public class MixinPlayerControllerMP implements Wrapper
{
    @Inject(method = { "onPlayerDamageBlock" }, at = { @At("HEAD") }, cancellable = true)
    public void onPlayerDamageBlock(final BlockPos posBlock, final EnumFacing directionFacing, final CallbackInfoReturnable<Boolean> info) {
        final DamageBlockEvent event = new DamageBlockEvent(posBlock, directionFacing);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled()) {
            info.setReturnValue(false);
            info.cancel();
        }
    }
    
    @Inject(method = { "getBlockReachDistance" }, at = { @At("HEAD") }, cancellable = true)
    public void getBlockReachDistance(final CallbackInfoReturnable<Float> ci) {
        if (ModuleManager.getModule(m -> m.getClass().equals(Reach.class)).isEnabled()) {
            ci.setReturnValue(((Double)Reach.reach.getValue()).floatValue());
            ci.cancel();
        }
    }
}
