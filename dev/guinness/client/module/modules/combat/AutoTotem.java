package dev.guinness.client.module.modules.combat;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import dev.guinness.client.util.InventoryUtil;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.init.Items;
import dev.guinness.client.module.Category;
import dev.guinness.client.module.Module;

public class AutoTotem extends Module
{
    public AutoTotem() {
        super("AutoTotem", Category.COMBAT, "Automatically equips totems into your offhand");
    }
    
    @Override
    public void onFastUpdate() {
        if (AutoTotem.mc.player.func_184592_cb().getItem() == Items.TOTEM_OF_UNDYING) {
            return;
        }
        if (AutoTotem.mc.currentScreen instanceof GuiContainer) {
            return;
        }
        final int totem = InventoryUtil.findInv(Items.TOTEM_OF_UNDYING);
        if (totem != -1) {
            AutoTotem.mc.playerController.windowClick(AutoTotem.mc.player.field_71069_bz.windowId, totem, 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.player);
            AutoTotem.mc.playerController.windowClick(AutoTotem.mc.player.field_71069_bz.windowId, 45, 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.player);
            AutoTotem.mc.playerController.windowClick(AutoTotem.mc.player.field_71069_bz.windowId, totem, 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.player);
        }
    }
}
