package dev.guinness.client.mixin.mixins;

import dev.guinness.client.module.Module;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import dev.guinness.client.module.ModuleManager;
import dev.guinness.client.module.modules.render.NoRender;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.client.renderer.entity.RenderWitherSkull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ RenderWitherSkull.class })
public class MixinRenderWitherSkull
{
    @Inject(method = { "doRender" }, at = { @At("HEAD") }, cancellable = true)
    public void doRender(final EntityWitherSkull entity, final double x, final double y, final double z, final float entityYaw, final float partialTicks, final CallbackInfo ci) {
        if (ModuleManager.getModule(m -> m.getClass().equals(NoRender.class)).isEnabled() && (boolean)NoRender.witherSkulls.getValue()) {
            ci.cancel();
        }
    }
}
