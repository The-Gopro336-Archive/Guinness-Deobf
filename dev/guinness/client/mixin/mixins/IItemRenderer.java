package dev.guinness.client.mixin.mixins;

import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.gen.Accessor;
import net.minecraft.client.renderer.ItemRenderer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ ItemRenderer.class })
public interface IItemRenderer
{
    @Accessor("equippedProgressMainHand")
    void setEquippedProgressMainHand(final float p0);
    
    @Accessor("equippedProgressMainHand")
    float getEquippedProgressMainHand();
    
    @Accessor("prevEquippedProgressMainHand")
    void setPrevEquippedProgressMainHand(final float p0);
    
    @Accessor("prevEquippedProgressMainHand")
    float getPrevEquippedProgressMainHand();
    
    @Accessor("itemStackMainHand")
    void setItemStackMainHand(final ItemStack p0);
}
