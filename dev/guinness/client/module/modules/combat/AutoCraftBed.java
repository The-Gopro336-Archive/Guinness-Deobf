package dev.guinness.client.module.modules.combat;

import net.minecraft.network.play.client.CPacketClickWindow;
import net.minecraft.inventory.ClickType;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumHand;
import dev.guinness.client.util.ModuleUtil;
import net.minecraft.util.EnumFacing;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import dev.guinness.client.util.MessageUtil;
import dev.guinness.client.util.InventoryUtil;
import net.minecraft.init.Blocks;
import dev.guinness.client.module.Category;
import net.minecraft.util.math.BlockPos;
import dev.guinness.client.module.Module;

public class AutoCraftBed extends Module
{
    public AutoCraftBed$Stage stage;
    public BlockPos placeTarget;
    public int wood;
    public int wool;
    public int craftingtable;
    
    public AutoCraftBed() {
        super("AutoCraftBed", Category.COMBAT, "Automatically crafts beds for you");
    }
    
    @Override
    public void onEnable() {
        this.stage = null;
        this.craftingtable = InventoryUtil.find(Blocks.CRAFTING_TABLE);
        this.wood = InventoryUtil.findInv(Blocks.PLANKS);
        this.wool = InventoryUtil.findInv(Blocks.WOOL);
        if (this.craftingtable == -1) {
            MessageUtil.sendModuleErrorMessage(this, "No crafting tables in hotbar!");
            this.disable();
            return;
        }
        if (this.wood == -1) {
            MessageUtil.sendModuleErrorMessage(this, "Not enough wood!");
            this.disable();
            return;
        }
        if (this.wool == -1) {
            MessageUtil.sendModuleErrorMessage(this, "Not enough wool!");
            this.disable();
            return;
        }
        if (AutoCraftBed.mc.objectMouseOver.getBlockPos() != null && AutoCraftBed.mc.objectMouseOver.getBlockPos().add(0, 1, 0) != null) {
            this.placeTarget = AutoCraftBed.mc.objectMouseOver.getBlockPos().add(0, 1, 0);
            this.wood = -1;
            this.wool = -1;
            for (int i = 0; i < 36; ++i) {
                final ItemStack is = AutoCraftBed.mc.player.field_71071_by.getStackInSlot(i);
                if (is != ItemStack.EMPTY) {
                    final Item it = is.getItem();
                    if (it instanceof ItemBlock) {
                        if (((ItemBlock)it).getBlock().equals(Blocks.PLANKS)) {
                            this.wood = i;
                        }
                    }
                }
            }
            for (int i = 0; i < 36; ++i) {
                final ItemStack is = AutoCraftBed.mc.player.field_71071_by.getStackInSlot(i);
                if (is != ItemStack.EMPTY) {
                    final Item it = is.getItem();
                    if (it instanceof ItemBlock) {
                        if (((ItemBlock)it).getBlock().equals(Blocks.WOOL)) {
                            this.wool = i;
                        }
                    }
                }
            }
            this.stage = AutoCraftBed$Stage.PLACING;
            return;
        }
        MessageUtil.sendModuleErrorMessage(this, "objectMouseOver is null!");
        this.disable();
    }
    
    @Override
    public void onUpdate() {
        if (this.stage.equals(AutoCraftBed$Stage.PLACING)) {
            final int oldslot = AutoCraftBed.mc.player.field_71071_by.currentItem;
            AutoCraftBed.mc.player.field_71071_by.currentItem = this.craftingtable;
            ModuleUtil.placeBlock(this.placeTarget, EnumFacing.DOWN);
            AutoCraftBed.mc.player.field_71071_by.currentItem = oldslot;
            this.stage = AutoCraftBed$Stage.OPENING;
            return;
        }
        if (this.stage.equals(AutoCraftBed$Stage.OPENING)) {
            AutoCraftBed.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(this.placeTarget, EnumFacing.DOWN, EnumHand.MAIN_HAND, Float.intBitsToFloat(Float.floatToIntBits(1.9205412E38f) ^ 0x7F107C47), Float.intBitsToFloat(Float.floatToIntBits(3.315825E38f) ^ 0x7F797477), Float.intBitsToFloat(Float.floatToIntBits(4.263871E37f) ^ 0x7E004FAB)));
            if (AutoCraftBed.mc.currentScreen instanceof GuiCrafting) {
                this.stage = AutoCraftBed$Stage.CRAFTING;
            }
        }
        if (this.stage.equals(AutoCraftBed$Stage.CRAFTING)) {
            if (!(AutoCraftBed.mc.currentScreen instanceof GuiCrafting)) {
                this.disable();
                return;
            }
            if (!AutoCraftBed.mc.player.field_71071_by.getItemStack().getItem().getUnlocalizedName().equals("tile.wood")) {
                AutoCraftBed.mc.player.connection.sendPacket((Packet)new CPacketClickWindow(AutoCraftBed.mc.player.field_71070_bA.windowId, this.wood + 1, 0, ClickType.PICKUP, AutoCraftBed.mc.player.field_71071_by.getStackInSlot(this.wood + 1), AutoCraftBed.mc.player.field_71070_bA.getNextTransactionID(AutoCraftBed.mc.player.field_71071_by)));
                return;
            }
            this.stage = AutoCraftBed$Stage.WOOD;
        }
        if (this.stage.equals(AutoCraftBed$Stage.WOOD)) {
            if (!(AutoCraftBed.mc.currentScreen instanceof GuiCrafting)) {
                this.disable();
                return;
            }
            if (AutoCraftBed.mc.player.field_71071_by.getItemStack().getItem().getUnlocalizedName().equals("tile.wood") && AutoCraftBed.mc.player.field_71071_by.getItemStack().getCount() >= 2) {
                return;
            }
            if (!AutoCraftBed.mc.player.field_71071_by.getItemStack().getItem().getUnlocalizedName().equals("tile.cloth")) {
                AutoCraftBed.mc.player.connection.sendPacket((Packet)new CPacketClickWindow(AutoCraftBed.mc.player.field_71070_bA.windowId, this.wool + 1, 0, ClickType.PICKUP, AutoCraftBed.mc.player.field_71071_by.getStackInSlot(this.wool + 1), AutoCraftBed.mc.player.field_71070_bA.getNextTransactionID(AutoCraftBed.mc.player.field_71071_by)));
                return;
            }
            this.stage = AutoCraftBed$Stage.WOOL;
        }
        if (this.stage.equals(AutoCraftBed$Stage.WOOL)) {
            if (!(AutoCraftBed.mc.currentScreen instanceof GuiCrafting)) {
                this.disable();
                return;
            }
            if (AutoCraftBed.mc.player.field_71071_by.getItemStack().getItem().getUnlocalizedName().equals("tile.wood")) {
                if (AutoCraftBed.mc.player.field_71071_by.getItemStack().getCount() >= 2) {
                    return;
                }
            }
            this.stage = AutoCraftBed$Stage.FINISHED;
        }
        if (this.stage.equals(AutoCraftBed$Stage.FINISHED)) {
            this.disable();
        }
    }
}
