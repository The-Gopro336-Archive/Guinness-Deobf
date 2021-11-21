package dev.guinness.client.module.modules.render;

import dev.guinness.client.mixin.mixins.IItemRenderer;
import dev.guinness.client.module.Category;
import dev.guinness.client.module.Module;

public class NoCooldown extends Module
{
    public NoCooldown() {
        super("NoCooldown", Category.RENDER, "Visually removes cooldown between swapping and swinging items");
    }
    
    @Override
    public void onUpdate() {
        ((IItemRenderer)NoCooldown.mc.itemRenderer).setEquippedProgressMainHand(Float.intBitsToFloat(Float.floatToIntBits(30.05892f) ^ 0x7E7078AB));
        ((IItemRenderer)NoCooldown.mc.itemRenderer).setItemStackMainHand(NoCooldown.mc.player.func_184614_ca());
    }
}
