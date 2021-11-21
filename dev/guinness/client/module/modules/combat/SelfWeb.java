package dev.guinness.client.module.modules.combat;

import net.minecraft.util.EnumHand;
import dev.guinness.client.util.ModuleUtil;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import dev.guinness.client.util.MessageUtil;
import dev.guinness.client.util.InventoryUtil;
import net.minecraft.init.Blocks;
import dev.guinness.client.module.Category;
import dev.guinness.client.module.Module;

public class SelfWeb extends Module
{
    public int web;
    
    public SelfWeb() {
        super("SelfWeb", Category.COMBAT, "Autoplaces a web at your feet");
    }
    
    @Override
    public void onEnable() {
        this.web = InventoryUtil.find(Blocks.WEB);
        if (this.web == -1) {
            MessageUtil.sendModuleErrorMessage(this, "No webs!");
            this.disable();
            return;
        }
        final int oldSlot = SelfWeb.mc.player.field_71071_by.currentItem;
        SelfWeb.mc.player.field_71071_by.currentItem = this.web;
        ModuleUtil.placeBlock(new BlockPos(SelfWeb.mc.player.func_174791_d()), EnumFacing.DOWN);
        SelfWeb.mc.player.swingArm(EnumHand.MAIN_HAND);
        SelfWeb.mc.player.field_71071_by.currentItem = oldSlot;
        this.disable();
    }
}
