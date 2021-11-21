package dev.guinness.client.mixin.mixins;

import dev.guinness.client.module.Module;
import dev.guinness.client.event.events.PlayerMoveEvent$PostMove;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.Redirect;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.common.MinecraftForge;
import dev.guinness.client.event.events.PlayerMoveEvent$PreMove;
import net.minecraft.entity.MoverType;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import dev.guinness.client.module.ModuleManager;
import dev.guinness.client.module.modules.movement.NoPush;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import com.mojang.authlib.GameProfile;
import net.minecraft.world.World;
import net.minecraft.client.entity.EntityPlayerSP;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.client.entity.AbstractClientPlayer;

@Mixin({ EntityPlayerSP.class })
public abstract class MixinEntityPlayerSP extends AbstractClientPlayer
{
    public MixinEntityPlayerSP(final World worldIn, final GameProfile playerProfile) {
        super(worldIn, playerProfile);
    }
    
    @Inject(method = { "pushOutOfBlocks(DDD)Z" }, at = { @At("HEAD") }, cancellable = true)
    public void pushOutOfBlocks(final double x, final double y, final double z, final CallbackInfoReturnable<Boolean> cir) {
        if (ModuleManager.getModule(m -> m.getClass().equals(NoPush.class)).isEnabled()) {
            cir.setReturnValue(false);
        }
    }
    
    @Redirect(method = { "move" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/AbstractClientPlayer;move(Lnet/minecraft/entity/MoverType;DDD)V"))
    public void preMove(final AbstractClientPlayer player, final MoverType moverType, final double x, final double y, final double z) {
        final PlayerMoveEvent$PreMove event = new PlayerMoveEvent$PreMove(x, y, z);
        MinecraftForge.EVENT_BUS.post(event);
        if (!event.isCanceled()) {
            super.func_70091_d(moverType, event.getX(), event.getY(), event.getZ());
        }
    }
    
    @Inject(method = { "move" }, at = { @At("RETURN") })
    public void postMove(final MoverType moverType, final double x, final double y, final double z, final CallbackInfo ci) {
        final PlayerMoveEvent$PostMove event = new PlayerMoveEvent$PostMove(x, y, z);
        MinecraftForge.EVENT_BUS.post(event);
    }
}
