package dev.guinness.client.mixin.mixins;

import dev.guinness.client.module.Module;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import dev.guinness.client.module.ModuleManager;
import dev.guinness.client.module.modules.exploit.NoSwing;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.util.EnumHand;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ EntityLivingBase.class })
public class MixinEntityLivingBase
{
    @Inject(method = { "swingArm" }, at = { @At("HEAD") }, cancellable = true)
    public void swingArm(final EnumHand hand, final CallbackInfo ci) {
        if (ModuleManager.getModule(m -> m.getClass().equals(NoSwing.class)).isEnabled() && (((String)NoSwing.mode.getValue()).equalsIgnoreCase("Clientside") || ((String)NoSwing.mode.getValue()).equalsIgnoreCase("Both"))) {
            ci.cancel();
        }
    }
}
