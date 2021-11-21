package dev.guinness.client.mixin.mixins;

import org.spongepowered.asm.mixin.gen.Accessor;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ EntityPlayer.class })
public interface IEntityPlayer
{
    @Accessor("itemStackMainHand")
    ItemStack getItemStackMainHand();
}
