package dev.guinness.client.mixin.mixins;

import dev.guinness.client.module.Module;
import dev.guinness.client.module.ModuleManager;
import dev.guinness.client.module.modules.render.ViewModel;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.common.MinecraftForge;
import dev.guinness.client.event.events.TransformSideFirstPersonEvent;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.util.EnumHandSide;
import net.minecraft.client.renderer.ItemRenderer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ ItemRenderer.class })
public class MixinItemRenderer
{
    @Inject(method = { "transformSideFirstPerson" }, at = { @At("HEAD") })
    public void transformSideFirstPerson(final EnumHandSide hand, final float p_187459_2_, final CallbackInfo ci) {
        final TransformSideFirstPersonEvent event = new TransformSideFirstPersonEvent(hand);
        MinecraftForge.EVENT_BUS.post(event);
    }
    
    @Inject(method = { "transformEatFirstPerson" }, at = { @At("HEAD") }, cancellable = true)
    public void transformEatFirstPerson(final float p_187454_1_, final EnumHandSide hand, final ItemStack stack, final CallbackInfo ci) {
        final TransformSideFirstPersonEvent event = new TransformSideFirstPersonEvent(hand);
        MinecraftForge.EVENT_BUS.post(event);
        if (ModuleManager.getModule(m -> m.getClass().equals(ViewModel.class)).isEnabled() && (boolean)ViewModel.cancelEating.getValue()) {
            ci.cancel();
        }
    }
    
    @Inject(method = { "transformFirstPerson" }, at = { @At("HEAD") })
    public void transformFirstPerson(final EnumHandSide hand, final float p_187453_2_, final CallbackInfo ci) {
        final TransformSideFirstPersonEvent event = new TransformSideFirstPersonEvent(hand);
        MinecraftForge.EVENT_BUS.post(event);
    }
}
