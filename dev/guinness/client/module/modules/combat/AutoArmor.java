package dev.guinness.client.module.modules.combat;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Items;
import net.minecraft.item.ItemArmor;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import dev.guinness.client.module.Category;
import dev.guinness.client.setting.SSlider;
import dev.guinness.client.setting.SBoolean;
import dev.guinness.client.module.Module;

public class AutoArmor extends Module
{
    public static SBoolean elytra;
    public static SSlider delay;
    
    public AutoArmor() {
        super("AutoArmor", Category.COMBAT, "Automatically equips armor");
    }
    
    @Override
    public void onUpdate() {
        if (AutoArmor.mc.player.field_70173_aa % (double)AutoArmor.delay.getValue() == Double.longBitsToDouble(Double.doubleToLongBits(5.494566952466736E307) ^ 0x7FD38FB1DBEA2A4BL)) {
            return;
        }
        if (AutoArmor.mc.currentScreen instanceof GuiContainer && !(AutoArmor.mc.currentScreen instanceof InventoryEffectRenderer)) {
            return;
        }
        final int[] bestArmorSlots = new int[4];
        final int[] bestArmorValues = new int[4];
        for (int armorType = 0; armorType < 4; ++armorType) {
            final ItemStack oldArmor = AutoArmor.mc.player.field_71071_by.armorItemInSlot(armorType);
            if (oldArmor != null && oldArmor.getItem() instanceof ItemArmor) {
                bestArmorValues[armorType] = ((ItemArmor)oldArmor.getItem()).damageReduceAmount;
            }
            bestArmorSlots[armorType] = -1;
        }
        for (int slot = 0; slot < 36; ++slot) {
            final ItemStack stack = AutoArmor.mc.player.field_71071_by.getStackInSlot(slot);
            if (stack.getCount() <= 1) {
                if (stack != null) {
                    if (stack.getItem() instanceof ItemArmor) {
                        final ItemArmor armor = (ItemArmor)stack.getItem();
                        final int armorType2 = armor.armorType.ordinal() - 2;
                        if (armorType2 == 2) {
                            if (AutoArmor.mc.player.field_71071_by.armorItemInSlot(armorType2).getItem().equals(Items.ELYTRA)) {
                                if (AutoArmor.elytra.getValue()) {
                                    continue;
                                }
                            }
                        }
                        final int armorValue = armor.damageReduceAmount;
                        if (armorValue > bestArmorValues[armorType2]) {
                            bestArmorSlots[armorType2] = slot;
                            bestArmorValues[armorType2] = armorValue;
                        }
                    }
                }
            }
        }
        for (int armorType = 0; armorType < 4; ++armorType) {
            int slot2 = bestArmorSlots[armorType];
            if (slot2 != -1) {
                final ItemStack oldArmor2 = AutoArmor.mc.player.field_71071_by.armorItemInSlot(armorType);
                if (oldArmor2 == null || oldArmor2 != ItemStack.EMPTY || AutoArmor.mc.player.field_71071_by.getFirstEmptyStack() != -1) {
                    if (slot2 < 9) {
                        slot2 += 36;
                    }
                    AutoArmor.mc.playerController.windowClick(0, 8 - armorType, 0, ClickType.QUICK_MOVE, (EntityPlayer)AutoArmor.mc.player);
                    AutoArmor.mc.playerController.windowClick(0, slot2, 0, ClickType.QUICK_MOVE, (EntityPlayer)AutoArmor.mc.player);
                    break;
                }
            }
        }
    }
    
    static {
        AutoArmor.elytra = new SBoolean("Ignore Elytra", false);
        AutoArmor.delay = new SSlider("Delay", Double.longBitsToDouble(Double.doubleToLongBits(4.822234460112631) ^ 0x7FE349F7D48F4FD6L), Double.longBitsToDouble(Double.doubleToLongBits(0.1515763566379392) ^ 0x7FC366DAA34DAB03L), Double.longBitsToDouble(Double.doubleToLongBits(0.3016455830908062) ^ 0x7FC74E294696E73BL), 0);
    }
}
