package dev.guinness.client.module.modules.combat;

import java.util.function.Predicate;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumHand;
import dev.guinness.client.util.ModuleUtil;
import net.minecraft.util.EnumFacing;
import dev.guinness.client.util.MessageUtil;
import dev.guinness.client.util.InventoryUtil;
import net.minecraft.init.Blocks;
import dev.guinness.client.util.RenderUtil;
import java.awt.Color;
import dev.guinness.client.util.ColorUtil;
import dev.guinness.client.module.Category;
import net.minecraft.entity.player.EntityPlayer;
import dev.guinness.client.setting.SBoolean;
import dev.guinness.client.setting.SMode;
import dev.guinness.client.setting.SSlider;
import net.minecraft.util.math.BlockPos;
import dev.guinness.client.module.Module;

public class AnchorAura extends Module
{
    public BlockPos placeTarget;
    public static SSlider delay;
    public boolean shouldRender;
    public static SSlider range;
    public static SMode priority;
    public static SBoolean antiSuicide;
    public EntityPlayer bestTarget;
    
    public AnchorAura() {
        super("AnchorAura", Category.COMBAT, "Auto place and break for anchors on meteorpvp");
        this.shouldRender = false;
    }
    
    @Override
    public void onUpdate() {
        if (AnchorAura.mc.player.field_70173_aa % (double)AnchorAura.delay.getValue() == Double.longBitsToDouble(Double.doubleToLongBits(5.173135733009796E307) ^ 0x7FD26ABED7E275C5L)) {
            this.doAnchorAura();
        }
    }
    
    @Override
    public void onRender3d() {
        if (this.bestTarget != null) {
            if (this.placeTarget != null) {
                if (this.shouldRender) {
                    RenderUtil.drawBlockOutline(this.placeTarget, Double.longBitsToDouble(Double.doubleToLongBits(4.816870754236574E307) ^ 0x7FD1260C8EB7AEFDL), new Color(ColorUtil.BESTCOLOR(0, 255)), new Color(ColorUtil.BESTCOLOR(0, 255)));
                }
            }
        }
    }
    
    public void doAnchorAura() {
        this.setBestTarget();
        if (this.bestTarget == null) {
            return;
        }
        this.setPlaceTarget();
        if (this.placeTarget == null) {
            return;
        }
        final int glowstone = InventoryUtil.find(Blocks.GLOWSTONE);
        final int anchor = InventoryUtil.find(Blocks.OBSIDIAN);
        if (anchor == -1 || glowstone == -1) {
            MessageUtil.sendModuleErrorMessage(this, "Missing materials!");
            this.disable();
            return;
        }
        this.shouldRender = true;
        AnchorAura.mc.player.field_71071_by.currentItem = anchor;
        ModuleUtil.placeBlock(this.placeTarget.add(0, 1, 0), EnumFacing.DOWN);
        AnchorAura.mc.player.field_71071_by.currentItem = glowstone;
        ModuleUtil.placeBlock(this.placeTarget, EnumFacing.UP);
        AnchorAura.mc.player.field_71071_by.currentItem = anchor;
        AnchorAura.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(this.placeTarget, EnumFacing.UP, EnumHand.MAIN_HAND, Float.intBitsToFloat(Float.floatToIntBits(3.0201997E38f) ^ 0x7F6336EE), Float.intBitsToFloat(Float.floatToIntBits(4.484002E37f) ^ 0x7E06EF7F), Float.intBitsToFloat(Float.floatToIntBits(2.7664945E38f) ^ 0x7F5020BF)));
        this.shouldRender = false;
    }
    
    public void setPlaceTarget() {
        if (this.bestTarget == null) {
            return;
        }
        this.placeTarget = new BlockPos(this.bestTarget.func_174791_d()).add(0, 2, 0);
    }
    
    public void setBestTarget() {
        final String s = (String)AnchorAura.priority.getValue();
        switch (s) {
            case "Distance": {
                if (!(boolean)AnchorAura.antiSuicide.getValue()) {
                    this.bestTarget = ModuleUtil.getClosestEnemy((double)AnchorAura.range.getValue(), AnchorAura::lambda$setBestTarget$0);
                    break;
                }
                this.bestTarget = ModuleUtil.getClosestEnemyExcludingOwnBlockPos((double)AnchorAura.range.getValue(), AnchorAura::lambda$setBestTarget$1);
                break;
            }
            case "Health": {
                if (!(boolean)AnchorAura.antiSuicide.getValue()) {
                    this.bestTarget = ModuleUtil.getClosestEnemyWithLowestHealth((double)AnchorAura.range.getValue(), AnchorAura::lambda$setBestTarget$2);
                    break;
                }
                this.bestTarget = ModuleUtil.getClosestEnemyWithLowestHealthExcludingOwnBlockPos((double)AnchorAura.range.getValue(), AnchorAura::lambda$setBestTarget$3);
                break;
            }
        }
    }
    
    public static boolean lambda$setBestTarget$3(final EntityPlayer entityPlayer) {
        return !ModuleUtil.isPlayerTrapped(entityPlayer) && !ModuleUtil.isPlayerUnBeddable(entityPlayer);
    }
    
    public static boolean lambda$setBestTarget$2(final EntityPlayer entityPlayer) {
        return !ModuleUtil.isPlayerTrapped(entityPlayer) && !ModuleUtil.isPlayerUnBeddable(entityPlayer);
    }
    
    public static boolean lambda$setBestTarget$1(final EntityPlayer entityPlayer) {
        if (!ModuleUtil.isPlayerTrapped(entityPlayer)) {
            if (!ModuleUtil.isPlayerUnBeddable(entityPlayer)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean lambda$setBestTarget$0(final EntityPlayer entityPlayer) {
        return !ModuleUtil.isPlayerTrapped(entityPlayer) && !ModuleUtil.isPlayerUnBeddable(entityPlayer);
    }
    
    static {
        AnchorAura.priority = new SMode("Priority", new String[] { "Health", "Distance" });
        AnchorAura.antiSuicide = new SBoolean("AntiSuicide", true);
        AnchorAura.range = new SSlider("Range", Double.longBitsToDouble(Double.doubleToLongBits(0.11991509928918075) ^ 0x7FBEB2C185BE5E27L), Double.longBitsToDouble(Double.doubleToLongBits(0.0779586423859746) ^ 0x7FABF518FB7D028FL), Double.longBitsToDouble(Double.doubleToLongBits(1.6659506966442785) ^ 0x7FD8A7BBEAED5E97L), 1);
        AnchorAura.delay = new SSlider("Delay", Double.longBitsToDouble(Double.doubleToLongBits(0.6592661260368605) ^ 0x7FED18B546560BB7L), Double.longBitsToDouble(Double.doubleToLongBits(0.2244163665943378) ^ 0x7FE4B9ACED9ADBA3L), Double.longBitsToDouble(Double.doubleToLongBits(0.31408805122077144) ^ 0x7FE01A04C503AF7EL), 0);
    }
}
