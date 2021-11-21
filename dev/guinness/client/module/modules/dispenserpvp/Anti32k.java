package dev.guinness.client.module.modules.dispenserpvp;

import dev.guinness.client.util.FriendUtil;
import net.minecraft.entity.Entity;
import dev.guinness.client.module.ModuleManager;
import dev.guinness.client.util.ModuleUtil;
import dev.guinness.client.mixin.mixins.IEntityPlayer;
import java.util.function.Predicate;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.gui.inventory.GuiDispenser;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.client.gui.GuiHopper;
import dev.guinness.client.util.InventoryUtil;
import net.minecraft.init.Items;
import dev.guinness.client.module.Category;
import dev.guinness.client.module.Module;

public class Anti32k extends Module
{
    public Anti32k() {
        super("Anti32k", Category.DISPENSERPVP, "Swaps a totem to your mainhand when an enemy with a 32k is in range");
    }
    
    @Override
    public void onUpdate() {
        this.swapTotem();
        this.findEnemies();
    }
    
    public void swapTotem() {
        if (Anti32k.mc.player.field_71071_by.getStackInSlot(0).getItem().equals(Items.TOTEM_OF_UNDYING)) {
            return;
        }
        final int totem = InventoryUtil.findInv(Items.TOTEM_OF_UNDYING);
        if (totem != -1 && totem != 0) {
            if (Anti32k.mc.currentScreen instanceof GuiHopper) {
                Anti32k.mc.playerController.windowClick(Anti32k.mc.player.field_71070_bA.windowId, totem - 4, 0, ClickType.SWAP, (EntityPlayer)Anti32k.mc.player);
            }
            else if (Anti32k.mc.currentScreen instanceof GuiDispenser) {
                Anti32k.mc.playerController.windowClick(Anti32k.mc.player.field_71070_bA.windowId, totem, 0, ClickType.SWAP, (EntityPlayer)Anti32k.mc.player);
            }
            else if (Anti32k.mc.currentScreen != null && !(Anti32k.mc.currentScreen instanceof GuiInventory)) {
                Anti32k.mc.playerController.windowClick(Anti32k.mc.player.field_71069_bz.windowId, totem, 0, ClickType.SWAP, (EntityPlayer)Anti32k.mc.player);
            }
            else {
                Anti32k.mc.playerController.windowClick(Anti32k.mc.player.field_71069_bz.windowId, totem, 0, ClickType.SWAP, (EntityPlayer)Anti32k.mc.player);
            }
        }
    }
    
    public void findEnemies() {
        final int badguys = (int)Anti32k.mc.world.field_73010_i.stream().filter(Anti32k::lambda$findEnemies$0).filter(Anti32k::lambda$findEnemies$1).filter(Anti32k::lambda$findEnemies$2).count();
        if (badguys == 0) {
            return;
        }
        if (ModuleUtil.is32k(((IEntityPlayer)Anti32k.mc.player).getItemStackMainHand())) {
            return;
        }
        if (ModuleManager.getModule(Anti32k::lambda$findEnemies$3).isEnabled()) {
            return;
        }
        Anti32k.mc.player.field_71071_by.currentItem = 0;
    }
    
    public static boolean lambda$findEnemies$3(final Module module) {
        return module.getClass().equals(Auto32k.class);
    }
    
    public static boolean lambda$findEnemies$2(final EntityPlayer entityPlayer) {
        return ModuleUtil.is32k(entityPlayer.func_184614_ca());
    }
    
    public static boolean lambda$findEnemies$1(final EntityPlayer entityPlayer) {
        return Anti32k.mc.player.func_70032_d(entityPlayer) <= Float.intBitsToFloat(Float.floatToIntBits(0.23872994f) ^ 0x7F74759F);
    }
    
    public static boolean lambda$findEnemies$0(final EntityPlayer entityPlayer) {
        return !FriendUtil.isFriend(entityPlayer.getName());
    }
}
