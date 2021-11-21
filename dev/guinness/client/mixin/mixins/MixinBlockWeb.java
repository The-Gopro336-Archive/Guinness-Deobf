package dev.guinness.client.mixin.mixins;

import dev.guinness.client.module.Module;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import dev.guinness.client.module.ModuleManager;
import dev.guinness.client.module.modules.movement.NoWeb;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.entity.Entity;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.block.BlockWeb;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ BlockWeb.class })
public class MixinBlockWeb
{
    @Inject(method = { "onEntityCollidedWithBlock" }, at = { @At("HEAD") }, cancellable = true)
    public void onEntityCollidedWithBlock(final World worldIn, final BlockPos pos, final IBlockState state, final Entity entityIn, final CallbackInfo ci) {
        if (ModuleManager.getModule(m -> m.getClass().equals(NoWeb.class)).isEnabled()) {
            ci.cancel();
        }
    }
}
