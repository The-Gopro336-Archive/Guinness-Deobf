package dev.guinness.client.mixin.mixins;

import dev.guinness.client.module.Module;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraft.block.Block;
import net.minecraft.block.BlockShulkerBox;
import dev.guinness.client.module.ModuleManager;
import dev.guinness.client.module.modules.exploit.Shulkerception;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.SlotShulkerBox;
import org.spongepowered.asm.mixin.Mixin;
import dev.guinness.client.util.Wrapper;

@Mixin({ SlotShulkerBox.class })
public class MixinSlotShulkerBox implements Wrapper
{
    @Inject(method = { "isItemValid" }, at = { @At("HEAD") }, cancellable = true)
    public void isItemValid(final ItemStack stack, final CallbackInfoReturnable<Boolean> ci) {
        if (ModuleManager.getModule(m -> m.getClass().equals(Shulkerception.class)).isEnabled() && Block.getBlockFromItem(stack.getItem()) instanceof BlockShulkerBox) {
            ci.setReturnValue(MixinSlotShulkerBox.mc.player.field_71075_bZ.isCreativeMode);
        }
    }
}
