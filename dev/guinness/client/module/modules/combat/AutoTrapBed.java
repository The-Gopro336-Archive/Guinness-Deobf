package dev.guinness.client.module.modules.combat;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import dev.guinness.client.util.MessageUtil;
import dev.guinness.client.util.ModuleUtil;
import dev.guinness.client.util.InventoryUtil;
import net.minecraft.init.Blocks;
import dev.guinness.client.module.Category;
import dev.guinness.client.setting.SSlider;
import net.minecraft.entity.player.EntityPlayer;
import dev.guinness.client.setting.SMode;
import dev.guinness.client.setting.SBoolean;
import dev.guinness.client.module.Module;

public class AutoTrapBed extends Module
{
    public static SBoolean antistep;
    public static SMode mode;
    public EntityPlayer closestPlayer;
    public static SSlider range;
    
    public AutoTrapBed() {
        super("AutoTrapBed", Category.COMBAT, "Automatically places a block above a player to trap them (1.13+)");
    }
    
    @Override
    public void onEnable() {
        if (((String)AutoTrapBed.mode.getValue()).equalsIgnoreCase("Persistant")) {
            return;
        }
        final int currentSlot = AutoTrapBed.mc.player.field_71071_by.currentItem;
        final int obsidian = InventoryUtil.find(Blocks.OBSIDIAN);
        final EntityPlayer closestPlayer = ModuleUtil.getClosestEnemy((double)AutoTrapBed.range.getValue());
        if (closestPlayer == null) {
            MessageUtil.sendModuleErrorMessage(this, "No one to trap!");
            this.disable();
            return;
        }
        if (AutoTrapBed.antistep.getValue()) {
            return;
        }
        if (obsidian != -1) {
            final BlockPos singleOnly = new BlockPos(closestPlayer.func_174791_d()).add(0, 2, 0);
            if (!AutoTrapBed.mc.world.func_180495_p(singleOnly).func_185904_a().isReplaceable()) {
                return;
            }
            AutoTrapBed.mc.player.field_71071_by.currentItem = obsidian;
            ModuleUtil.placeBlock(singleOnly.add(0, 1, 0), EnumFacing.DOWN);
            AutoTrapBed.mc.player.field_71071_by.currentItem = currentSlot;
            this.disable();
        }
        else {
            MessageUtil.sendModuleErrorMessage(this, "No obsidian!");
            this.disable();
        }
    }
    
    @Override
    public void onUpdate() {
        this.closestPlayer = ModuleUtil.getClosestEnemy((double)AutoTrapBed.range.getValue());
        if (this.closestPlayer == null) {
            return;
        }
        final int obsidian = InventoryUtil.find(Blocks.OBSIDIAN);
        if (obsidian == -1) {
            MessageUtil.sendModuleErrorMessage(this, "No obsidian!");
            this.disable();
        }
        final BlockPos[] positions = { new BlockPos(this.closestPlayer.func_174791_d()).add(0, 2, 0), new BlockPos(this.closestPlayer.func_174791_d()).add(1, 2, 0), new BlockPos(this.closestPlayer.func_174791_d()).add(-1, 2, 0), new BlockPos(this.closestPlayer.func_174791_d()).add(0, 2, 1), new BlockPos(this.closestPlayer.func_174791_d()).add(0, 2, -1) };
        if (((String)AutoTrapBed.mode.getValue()).equals("Single")) {
            if (AutoTrapBed.antistep.getValue()) {
                int singledelay = 0;
                for (final BlockPos pos : positions) {
                    if (AutoTrapBed.mc.world.func_180495_p(pos).func_185904_a().isReplaceable()) {
                        final int currentSlot = AutoTrapBed.mc.player.field_71071_by.currentItem;
                        AutoTrapBed.mc.player.field_71071_by.currentItem = obsidian;
                        ModuleUtil.placeBlock(pos.add(0, 1, 0), EnumFacing.DOWN);
                        AutoTrapBed.mc.player.field_71071_by.currentItem = currentSlot;
                        if (++singledelay > 0) {
                            return;
                        }
                    }
                }
                if (singledelay == 0) {
                    this.disable();
                }
                return;
            }
        }
        final BlockPos singleOnly = new BlockPos(this.closestPlayer.func_174791_d()).add(0, 2, 0);
        int delay = 0;
        if (AutoTrapBed.antistep.getValue()) {
            for (final BlockPos pos2 : positions) {
                if (AutoTrapBed.mc.world.func_180495_p(pos2).func_185904_a().isReplaceable()) {
                    final int currentSlot2 = AutoTrapBed.mc.player.field_71071_by.currentItem;
                    AutoTrapBed.mc.player.field_71071_by.currentItem = obsidian;
                    ModuleUtil.placeBlock(pos2.add(0, 1, 0), EnumFacing.DOWN);
                    AutoTrapBed.mc.player.field_71071_by.currentItem = currentSlot2;
                    if (++delay > 0) {
                        return;
                    }
                }
            }
        }
        else {
            if (!AutoTrapBed.mc.world.func_180495_p(singleOnly).func_185904_a().isReplaceable()) {
                return;
            }
            final int currentSlot3 = AutoTrapBed.mc.player.field_71071_by.currentItem;
            AutoTrapBed.mc.player.field_71071_by.currentItem = obsidian;
            ModuleUtil.placeBlock(singleOnly.add(0, 1, 0), EnumFacing.DOWN);
            AutoTrapBed.mc.player.field_71071_by.currentItem = currentSlot3;
        }
    }
    
    static {
        AutoTrapBed.mode = new SMode("Mode", new String[] { "Single", "Persistant" });
        AutoTrapBed.antistep = new SBoolean("AntiStep", false);
        AutoTrapBed.range = new SSlider("Range", Double.longBitsToDouble(Double.doubleToLongBits(0.3431176428606107) ^ 0x7FD5F5A3B3B11565L), Double.longBitsToDouble(Double.doubleToLongBits(1.0410013561305866) ^ 0x7FE8A7F109BAC2A3L), Double.longBitsToDouble(Double.doubleToLongBits(0.1804381052257593) ^ 0x7FE718988872CBBFL), 1);
    }
}
