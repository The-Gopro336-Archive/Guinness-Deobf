package dev.guinness.client.mixin.mixins;

import dev.guinness.client.module.Module;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import dev.guinness.client.module.ModuleManager;
import dev.guinness.client.module.modules.exploit.LiquidInteract;
import net.minecraft.block.properties.IProperty;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.BlockLiquid;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ BlockLiquid.class })
public class MixinBlockLiquid
{
    @Inject(method = { "canCollideCheck" }, at = { @At("HEAD") }, cancellable = true)
    public void canCollideCheck(final IBlockState blockState, final boolean hitIfLiquid, final CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue((hitIfLiquid && (int)blockState.getValue((IProperty)BlockLiquid.LEVEL) == 0) || ModuleManager.getModule(m -> m.getClass().equals(LiquidInteract.class)).isEnabled());
    }
}
