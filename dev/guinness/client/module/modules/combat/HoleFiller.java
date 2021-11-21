package dev.guinness.client.module.modules.combat;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.entity.Entity;
import dev.guinness.client.util.RenderUtil;
import java.awt.Color;
import dev.guinness.client.util.ColorUtil;
import java.util.Iterator;
import net.minecraft.util.EnumFacing;
import dev.guinness.client.util.MessageUtil;
import dev.guinness.client.util.InventoryUtil;
import net.minecraft.init.Blocks;
import java.util.function.Predicate;
import java.util.function.Consumer;
import dev.guinness.client.util.ModuleUtil;
import java.util.ArrayList;
import dev.guinness.client.module.Category;
import net.minecraft.util.math.BlockPos;
import java.util.List;
import dev.guinness.client.setting.SSlider;
import dev.guinness.client.module.Module;

public class HoleFiller extends Module
{
    public static SSlider range;
    public List<BlockPos> holesToFill;
    
    public HoleFiller() {
        super("HoleFiller", Category.COMBAT, "Fills safeholes around you with obsidian");
        this.holesToFill = new ArrayList<BlockPos>();
    }
    
    @Override
    public void onEnable() {
        this.holesToFill.clear();
        final List<BlockPos> bedrockHoles = (List<BlockPos>)ModuleUtil.findSafeBedrockHoles(((Double)HoleFiller.range.getValue()).intValue());
        final List<BlockPos> obbyHoles = (List<BlockPos>)ModuleUtil.findSafeObsidianHoles(((Double)HoleFiller.range.getValue()).intValue());
        final List<BlockPos> mixedHoles = (List<BlockPos>)ModuleUtil.findSafeMixedHoles(((Double)HoleFiller.range.getValue()).intValue());
        bedrockHoles.forEach(this::lambda$onEnable$0);
        obbyHoles.forEach(this::lambda$onEnable$1);
        mixedHoles.forEach(this::lambda$onEnable$2);
        this.holesToFill.removeIf(HoleFiller::lambda$onEnable$4);
    }
    
    @Override
    public void onRender3d() {
        this.holesToFill.forEach(HoleFiller::lambda$onRender3d$5);
    }
    
    @Override
    public void onUpdate() {
        final Iterator<BlockPos> iterator = this.holesToFill.iterator();
        if (!iterator.hasNext()) {
            this.disable();
            return;
        }
        final BlockPos pos = iterator.next();
        final int obsidian = InventoryUtil.find(Blocks.OBSIDIAN);
        if (obsidian == -1) {
            MessageUtil.sendModuleErrorMessage(this, "No obsidian!");
            this.disable();
            return;
        }
        final int oldSlot = HoleFiller.mc.player.field_71071_by.currentItem;
        HoleFiller.mc.player.field_71071_by.currentItem = obsidian;
        ModuleUtil.placeBlock(pos, EnumFacing.DOWN);
        HoleFiller.mc.player.field_71071_by.currentItem = oldSlot;
        this.holesToFill.remove(pos);
    }
    
    public static void lambda$onRender3d$5(final BlockPos blockPos) {
        RenderUtil.drawFilledBox(blockPos, Double.longBitsToDouble(Double.doubleToLongBits(2.839787436306831E307) ^ 0x7FC4384F26381F1BL), new Color(ColorUtil.BESTCOLOR(0, 255)));
    }
    
    public static boolean lambda$onEnable$4(final BlockPos blockPos) {
        return !HoleFiller.mc.world.func_175647_a(Entity.class, new AxisAlignedBB(blockPos), HoleFiller::lambda$null$3).isEmpty();
    }
    
    public static boolean lambda$null$3(final Entity entity) {
        return !(entity instanceof EntityItem);
    }
    
    public void lambda$onEnable$2(final BlockPos blockPos) {
        this.holesToFill.add(blockPos);
    }
    
    public void lambda$onEnable$1(final BlockPos blockPos) {
        this.holesToFill.add(blockPos);
    }
    
    public void lambda$onEnable$0(final BlockPos blockPos) {
        this.holesToFill.add(blockPos);
    }
    
    static {
        HoleFiller.range = new SSlider("Range", Double.longBitsToDouble(Double.doubleToLongBits(0.829184022661653) ^ 0x7FEA88ACEE76521BL), Double.longBitsToDouble(Double.doubleToLongBits(0.41672907855144076) ^ 0x7FCEABB070EAEBFBL), Double.longBitsToDouble(Double.doubleToLongBits(0.3042079307564211) ^ 0x7FCB78248A72171FL), 0);
    }
}
