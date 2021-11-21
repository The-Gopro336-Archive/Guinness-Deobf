package dev.guinness.client.mixin.mixins;

import dev.guinness.client.module.Module;
import dev.guinness.client.module.modules.render.NoRender;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import dev.guinness.client.module.ModuleManager;
import dev.guinness.client.module.modules.hud.PotionEffects;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.GuiIngame;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ GuiIngame.class })
public class MixinGuiIngame
{
    @Inject(method = { "renderPotionEffects" }, at = { @At("HEAD") }, cancellable = true)
    protected void renderPotionEffectsHook(final ScaledResolution scaledRes, final CallbackInfo ci) {
        if (ModuleManager.getModule(m -> m.getClass().equals(PotionEffects.class)).isEnabled()) {
            ci.cancel();
        }
    }
    
    @Inject(method = { "renderPortal" }, at = { @At("HEAD") }, cancellable = true)
    protected void renderPortalHook(final float n, final ScaledResolution scaledResolution, final CallbackInfo ci) {
        if (ModuleManager.getModule(m -> m.getClass().equals(NoRender.class)).isEnabled() && (boolean)NoRender.portal.getValue()) {
            ci.cancel();
        }
    }
    
    @Inject(method = { "renderPumpkinOverlay" }, at = { @At("HEAD") }, cancellable = true)
    protected void renderPumpkinOverlayHook(final ScaledResolution scaledRes, final CallbackInfo ci) {
        if (ModuleManager.getModule(m -> m.getClass().equals(NoRender.class)).isEnabled() && (boolean)NoRender.pumpkin.getValue()) {
            ci.cancel();
        }
    }
}
