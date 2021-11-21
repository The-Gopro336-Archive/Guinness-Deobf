package dev.guinness.client.module.modules.combat;

import net.minecraft.tileentity.TileEntityBed;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumHand;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.entity.item.EntityItem;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.network.play.server.SPacketAnimation;
import dev.guinness.client.event.events.PacketEvent$PacketReceiveEvent;
import net.minecraft.inventory.ClickType;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.Comparator;
import java.util.function.Predicate;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import dev.guinness.client.util.InventoryUtil;
import net.minecraft.init.Items;
import dev.guinness.client.util.RenderUtil;
import java.awt.Color;
import dev.guinness.client.util.ColorUtil;
import net.minecraft.util.EnumFacing;
import dev.guinness.client.util.ModuleUtil;
import net.minecraft.item.ItemBed;
import net.minecraft.client.entity.EntityPlayerSP;
import dev.guinness.client.module.Category;
import dev.guinness.client.setting.SSlider;
import dev.guinness.client.setting.SBoolean;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.player.EntityPlayer;
import dev.guinness.client.setting.SMode;
import dev.guinness.client.module.Module;

public class BedAura extends Module
{
    public static SMode renderMode;
    public EntityPlayer bestTarget;
    public BlockPos placeTarget;
    public static SBoolean gradient;
    public BlockPos[] bottomPos;
    public static SSlider range;
    public static SBoolean render;
    public static SBoolean swing;
    public BlockPos[] topPos;
    public static SBoolean antisuicide;
    public static SMode priority;
    public static SSlider delay;
    public static SSlider height;
    public BlockPos[] middlePos;
    
    public BedAura() {
        super("BedAura", Category.COMBAT, "Autoplace and break for beds on 1.13+ servers");
        this.bottomPos = new BlockPos[] { new BlockPos(1, 0, 0), new BlockPos(-1, 0, 0), new BlockPos(0, 0, 1), new BlockPos(0, 0, -1) };
        this.middlePos = new BlockPos[] { new BlockPos(1, 1, 0), new BlockPos(-1, 1, 0), new BlockPos(0, 1, 1), new BlockPos(0, 1, -1) };
        this.topPos = new BlockPos[] { new BlockPos(1, 2, 0), new BlockPos(-1, 2, 0), new BlockPos(0, 2, 1), new BlockPos(0, 2, -1) };
    }
    
    @Override
    public void onEnable() {
        BedAura.mc.player.field_70173_aa = (int)(Double.longBitsToDouble(Double.doubleToLongBits(0.006235131462745479) ^ 0x7FE61A025B800298L) + (double)BedAura.delay.getValue() / Double.longBitsToDouble(Double.doubleToLongBits(0.9633469874056768) ^ 0x7FEED3BD0FB370B0L));
        this.doBedAura();
    }
    
    @Override
    public void onUpdate() {
        if (this.bestTarget != null) {
            this.detonateBeds();
        }
        if (BedAura.mc.player.field_70173_aa % (double)BedAura.delay.getValue() != Double.longBitsToDouble(Double.doubleToLongBits(1.580611073827935E308) ^ 0x7FEC22C483442691L)) {
            return;
        }
        final EntityPlayerSP player = BedAura.mc.player;
        ++player.field_70173_aa;
        this.bestTarget = null;
        this.placeTarget = null;
        this.doBedAura();
    }
    
    @Override
    public void onRender3d() {
        if (!(boolean)BedAura.render.getValue()) {
            return;
        }
        if (this.placeTarget != null) {
            if (this.bestTarget != null) {
                if (BedAura.mc.player.func_174818_b(this.placeTarget) <= (double)BedAura.range.getValue() * (double)BedAura.range.getValue() && BedAura.mc.player.func_184614_ca().getItem() instanceof ItemBed) {
                    final EnumFacing bruh = EnumFacing.fromAngle((double)ModuleUtil.getBedPacket(this.placeTarget, new BlockPos(this.bestTarget.func_174791_d())));
                    Color colormid = null;
                    Color colorfinal = null;
                    final String string = bruh.toString();
                    switch (string) {
                        case "west": {
                            colormid = new Color(ColorUtil.BESTCOLOR(-5, 255));
                            colorfinal = new Color(ColorUtil.BESTCOLOR(0, 255));
                            break;
                        }
                        case "east": {
                            colormid = new Color(ColorUtil.BESTCOLOR(5, 255));
                            colorfinal = new Color(ColorUtil.BESTCOLOR(10, 255));
                            break;
                        }
                        case "north":
                        case "south": {
                            colormid = new Color(ColorUtil.BESTCOLOR(0, 255));
                            colorfinal = new Color(ColorUtil.BESTCOLOR(5, 255));
                            break;
                        }
                    }
                    final String s = (String)BedAura.renderMode.getValue();
                    switch (s) {
                        case "Claw": {
                            RenderUtil.drawClawBlockOutline(this.placeTarget, (double)BedAura.height.getValue() - Double.longBitsToDouble(Double.doubleToLongBits(5.941629815001518) ^ 0x7FE7C43A9B317AEEL));
                            RenderUtil.drawClawBlockOutline(this.placeTarget.offset(EnumFacing.fromAngle((double)ModuleUtil.getBedPacket(this.placeTarget, new BlockPos(this.bestTarget.func_174791_d())))), (double)BedAura.height.getValue() - Double.longBitsToDouble(Double.doubleToLongBits(11.336720631935625) ^ 0x7FD6AC66A58C1A9BL));
                            break;
                        }
                        case "Outline": {
                            if (BedAura.gradient.getValue()) {
                                RenderUtil.drawBlockOutline(this.placeTarget, (double)BedAura.height.getValue() - Double.longBitsToDouble(Double.doubleToLongBits(5.132482700191798) ^ 0x7FE487A98B82702DL), new Color(ColorUtil.BESTCOLOR(0, 255)), new Color(ColorUtil.BESTCOLOR(5, 255)));
                                RenderUtil.drawBlockOutline(this.placeTarget.offset(EnumFacing.fromAngle((double)ModuleUtil.getBedPacket(this.placeTarget, new BlockPos(this.bestTarget.func_174791_d())))), (double)BedAura.height.getValue() - Double.longBitsToDouble(Double.doubleToLongBits(5.149920719411959) ^ 0x7FE49984D12B77F8L), colormid, colorfinal);
                                break;
                            }
                            RenderUtil.drawBlockOutline(this.placeTarget, (double)BedAura.height.getValue() - Double.longBitsToDouble(Double.doubleToLongBits(6.673032578122799) ^ 0x7FEAB12F73C0B84EL), new Color(ColorUtil.BESTCOLOR(0, 255)), new Color(ColorUtil.BESTCOLOR(0, 255)));
                            RenderUtil.drawBlockOutline(this.placeTarget.offset(EnumFacing.fromAngle((double)ModuleUtil.getBedPacket(this.placeTarget, new BlockPos(this.bestTarget.func_174791_d())))), (double)BedAura.height.getValue() - Double.longBitsToDouble(Double.doubleToLongBits(6.732037014128654) ^ 0x7FEAED9B1C6C9384L), new Color(ColorUtil.BESTCOLOR(0, 255)), new Color(ColorUtil.BESTCOLOR(0, 255)));
                            break;
                        }
                    }
                }
            }
        }
    }
    
    public void doBedAura() {
        this.setBestTarget();
        if (this.bestTarget == null) {
            return;
        }
        this.setPlaceTarget();
        if (this.placeTarget == null) {
            return;
        }
        if (BedAura.mc.player.func_174818_b(this.placeTarget) > (double)BedAura.range.getValue() * (double)BedAura.range.getValue()) {
            return;
        }
        this.swapBedFromInventory();
        final int bed = InventoryUtil.find(Items.BED);
        if (bed != -1) {
            BedAura.mc.player.field_71071_by.currentItem = bed;
            if (BedAura.mc.player.field_71071_by.getStackInSlot(BedAura.mc.player.field_71071_by.currentItem).getItem() instanceof ItemBed) {
                final float yaw = BedAura.mc.player.field_70177_z;
                final float pitch = BedAura.mc.player.field_70125_A;
                ModuleUtil.sendBedPacket(this.placeTarget, new BlockPos(this.bestTarget.func_174791_d()));
                ModuleUtil.placeBlock(this.placeTarget, EnumFacing.DOWN, false);
                BedAura.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(yaw, pitch, BedAura.mc.player.field_70122_E));
            }
        }
    }
    
    public void detonateBeds() {
        BedAura.mc.world.field_147482_g.stream().filter(BedAura::lambda$detonateBeds$3).filter(BedAura::lambda$detonateBeds$4).sorted(Comparator.comparing((Function<? super T, ? extends Comparable>)this::lambda$detonateBeds$5)).findFirst().ifPresent(BedAura::lambda$detonateBeds$6);
    }
    
    public void setPlaceTarget() {
        final List<BlockPos> bottomTargets = new ArrayList<BlockPos>();
        final List<BlockPos> middleTargets = new ArrayList<BlockPos>();
        final List<BlockPos> topTargets = new ArrayList<BlockPos>();
        for (final BlockPos block : this.bottomPos) {
            final BlockPos possiblePos = new BlockPos(this.bestTarget.func_174791_d()).add(block.func_177958_n(), block.func_177956_o(), block.func_177952_p());
            if (this.canPlaceBed(possiblePos)) {
                bottomTargets.add(possiblePos);
            }
        }
        for (final BlockPos block : this.middlePos) {
            final BlockPos possiblePos = new BlockPos(this.bestTarget.func_174791_d()).add(block.func_177958_n(), block.func_177956_o(), block.func_177952_p());
            if (this.canPlaceBed(possiblePos)) {
                middleTargets.add(possiblePos);
            }
        }
        for (final BlockPos block : this.topPos) {
            final BlockPos possiblePos = new BlockPos(this.bestTarget.func_174791_d()).add(block.func_177958_n(), block.func_177956_o(), block.func_177952_p());
            if (this.canPlaceBed(possiblePos)) {
                topTargets.add(possiblePos);
            }
        }
        bottomTargets.sort(Comparator.comparing((Function<? super BlockPos, ? extends Comparable>)BedAura::lambda$setPlaceTarget$7));
        middleTargets.sort(Comparator.comparing((Function<? super BlockPos, ? extends Comparable>)BedAura::lambda$setPlaceTarget$8));
        topTargets.sort(Comparator.comparing((Function<? super BlockPos, ? extends Comparable>)BedAura::lambda$setPlaceTarget$9));
        if (middleTargets.size() > 0) {
            this.placeTarget = middleTargets.get(0);
            return;
        }
        if (bottomTargets.size() > 0) {
            this.placeTarget = bottomTargets.get(0);
            return;
        }
        this.placeTarget = topTargets.get(0);
    }
    
    public boolean canPlaceBed(final BlockPos blockPos) {
        return (BedAura.mc.world.func_180495_p(blockPos).func_185904_a().isReplaceable() || BedAura.mc.world.func_180495_p(blockPos).getBlock() == Blocks.BED || BedAura.mc.world.func_180495_p(blockPos).getBlock() == Blocks.FIRE) && BedAura.mc.world.func_175647_a(Entity.class, new AxisAlignedBB(blockPos), BedAura::lambda$canPlaceBed$10).isEmpty();
    }
    
    public void setBestTarget() {
        final String s = (String)BedAura.priority.getValue();
        switch (s) {
            case "Distance": {
                if (!(boolean)BedAura.antisuicide.getValue()) {
                    this.bestTarget = ModuleUtil.getClosestEnemy((double)BedAura.range.getValue(), BedAura::lambda$setBestTarget$11);
                    break;
                }
                this.bestTarget = ModuleUtil.getClosestEnemyExcludingOwnBlockPos((double)BedAura.range.getValue(), BedAura::lambda$setBestTarget$12);
                break;
            }
            case "Health": {
                if (!(boolean)BedAura.antisuicide.getValue()) {
                    this.bestTarget = ModuleUtil.getClosestEnemyWithLowestHealth((double)BedAura.range.getValue(), BedAura::lambda$setBestTarget$13);
                    break;
                }
                this.bestTarget = ModuleUtil.getClosestEnemyWithLowestHealthExcludingOwnBlockPos((double)BedAura.range.getValue(), BedAura::lambda$setBestTarget$14);
                break;
            }
        }
    }
    
    public void swapBedFromInventory() {
        if ((BedAura.mc.currentScreen == null || BedAura.mc.currentScreen instanceof GuiInventory) && BedAura.mc.player.field_71071_by.getStackInSlot(0).getItem() != Items.BED) {
            for (int i = 9; i < 36; ++i) {
                if (BedAura.mc.player.field_71071_by.getStackInSlot(i).getItem() == Items.BED) {
                    BedAura.mc.playerController.windowClick(BedAura.mc.player.field_71069_bz.windowId, i, 0, ClickType.SWAP, (EntityPlayer)BedAura.mc.player);
                    break;
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onPacketReceive(final PacketEvent$PacketReceiveEvent packetEvent$PacketReceiveEvent) {
        packetEvent$PacketReceiveEvent.setCanceled(packetEvent$PacketReceiveEvent.getPacket() instanceof SPacketAnimation);
    }
    
    public static boolean lambda$setBestTarget$14(final EntityPlayer entityPlayer) {
        return !ModuleUtil.isPlayerTrapped(entityPlayer) && !ModuleUtil.isPlayerBurrowed(entityPlayer) && !ModuleUtil.isPlayerUnBeddable(entityPlayer);
    }
    
    public static boolean lambda$setBestTarget$13(final EntityPlayer entityPlayer) {
        if (!ModuleUtil.isPlayerTrapped(entityPlayer)) {
            if (!ModuleUtil.isPlayerBurrowed(entityPlayer)) {
                if (!ModuleUtil.isPlayerUnBeddable(entityPlayer)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean lambda$setBestTarget$12(final EntityPlayer entityPlayer) {
        return !ModuleUtil.isPlayerTrapped(entityPlayer) && !ModuleUtil.isPlayerBurrowed(entityPlayer) && !ModuleUtil.isPlayerUnBeddable(entityPlayer);
    }
    
    public static boolean lambda$setBestTarget$11(final EntityPlayer entityPlayer) {
        return !ModuleUtil.isPlayerTrapped(entityPlayer) && !ModuleUtil.isPlayerBurrowed(entityPlayer) && !ModuleUtil.isPlayerUnBeddable(entityPlayer);
    }
    
    public static boolean lambda$canPlaceBed$10(final Entity entity) {
        return !(entity instanceof EntityItem);
    }
    
    public static Double lambda$setPlaceTarget$9(final BlockPos blockPos) {
        return BedAura.mc.player.func_70011_f(blockPos.func_177958_n(), blockPos.func_177956_o(), blockPos.func_177952_p());
    }
    
    public static Double lambda$setPlaceTarget$8(final BlockPos blockPos) {
        return BedAura.mc.player.func_70011_f(blockPos.func_177958_n(), blockPos.func_177956_o(), blockPos.func_177952_p());
    }
    
    public static Double lambda$setPlaceTarget$7(final BlockPos blockPos) {
        return BedAura.mc.player.func_70011_f(blockPos.func_177958_n(), blockPos.func_177956_o(), blockPos.func_177952_p());
    }
    
    public static void lambda$detonateBeds$6(final TileEntity tileEntity) {
        if (BedAura.mc.player.field_71093_bK != 0) {
            BedAura.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(tileEntity.getPos(), EnumFacing.UP, BedAura.mc.player.func_184812_l_() ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, Float.intBitsToFloat(Float.floatToIntBits(2.1386943E38f) ^ 0x7F20E5C1), Float.intBitsToFloat(Float.floatToIntBits(2.3821173E38f) ^ 0x7F3335E9), Float.intBitsToFloat(Float.floatToIntBits(7.66085E37f) ^ 0x7E66890B)));
            if (BedAura.swing.getValue()) {
                if (!BedAura.mc.player.field_82175_bq) {
                    BedAura.mc.player.swingArm(EnumHand.MAIN_HAND);
                }
            }
        }
    }
    
    public Double lambda$detonateBeds$5(final TileEntity tileEntity) {
        return this.bestTarget.func_70011_f(tileEntity.getPos().func_177958_n(), tileEntity.getPos().func_177956_o(), tileEntity.getPos().func_177952_p());
    }
    
    public static boolean lambda$detonateBeds$4(final TileEntity tileEntity) {
        return BedAura.mc.player.func_70011_f(tileEntity.getPos().func_177958_n(), tileEntity.getPos().func_177956_o(), tileEntity.getPos().func_177952_p()) <= (double)BedAura.range.getValue();
    }
    
    public static boolean lambda$detonateBeds$3(final TileEntity tileEntity) {
        return tileEntity instanceof TileEntityBed;
    }
    
    public static boolean lambda$static$2() {
        return ((String)BedAura.renderMode.getValue()).equalsIgnoreCase("Outline") && (boolean)BedAura.render.getValue();
    }
    
    public static boolean lambda$static$1() {
        return (boolean)BedAura.render.getValue();
    }
    
    public static boolean lambda$static$0() {
        return (boolean)BedAura.render.getValue();
    }
    
    static {
        BedAura.priority = new SMode("Target Priority", new String[] { "Distance", "Health" });
        BedAura.antisuicide = new SBoolean("AntiSuicide", false);
        BedAura.swing = new SBoolean("Swing", true);
        BedAura.range = new SSlider("Range", Double.longBitsToDouble(Double.doubleToLongBits(5.967184293700703) ^ 0x7FE7DE658F3A98F7L), Double.longBitsToDouble(Double.doubleToLongBits(0.24858604657027775) ^ 0x7FD3D1AAE621717DL), Double.longBitsToDouble(Double.doubleToLongBits(0.025467075784115552) ^ 0x7FB8140A867859BFL), 1);
        BedAura.delay = new SSlider("Delay", Double.longBitsToDouble(Double.doubleToLongBits(0.013761865489987311) ^ 0x7F942F2E51B10ADFL), Double.longBitsToDouble(Double.doubleToLongBits(0.18168971106965828) ^ 0x7FEF419BC3882CF1L), Double.longBitsToDouble(Double.doubleToLongBits(0.40972570738069597) ^ 0x7FEE38F22C61F49DL), 0);
        BedAura.render = new SBoolean("Render", true);
        BedAura.renderMode = new SMode(BedAura::lambda$static$0, "Render Mode", new String[] { "Claw", "Outline" });
        BedAura.height = new SSlider(BedAura::lambda$static$1, "Box Height", Double.longBitsToDouble(Double.doubleToLongBits(228.33859606346928) ^ 0x7FE8F03480C7708EL), Double.longBitsToDouble(Double.doubleToLongBits(397.6243504957467) ^ 0x7F993278484A567FL), Double.longBitsToDouble(Double.doubleToLongBits(4.905241546975669) ^ 0x7FE39EF7A3DCF6BBL), 2);
        BedAura.gradient = new SBoolean(BedAura::lambda$static$2, "Gradient", true);
    }
}
