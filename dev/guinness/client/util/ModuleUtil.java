package dev.guinness.client.util;

import java.util.Arrays;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.math.MathHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3i;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.EnumFacing;
import java.util.Iterator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.init.Blocks;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockObsidian;
import java.util.ArrayList;
import net.minecraft.util.math.Vec3d;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.function.Function;
import java.util.Comparator;
import java.util.function.Predicate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.block.Block;
import java.util.List;

public class ModuleUtil implements Wrapper
{
    public static List<Block> emptyBlocks;
    public static List<Block> rightclickableBlocks;
    
    public static EntityPlayer getClosestEnemy(final double n) {
        return (EntityPlayer)ModuleUtil.mc.world.field_73010_i.stream().filter(ModuleUtil::lambda$getClosestEnemy$0).filter(ModuleUtil::lambda$getClosestEnemy$1).filter(ModuleUtil::lambda$getClosestEnemy$2).filter(ModuleUtil::lambda$getClosestEnemy$3).sorted(Comparator.comparing((Function<? super T, ? extends Comparable>)ModuleUtil::lambda$getClosestEnemy$4)).findFirst().orElse(null);
    }
    
    public static EntityPlayer getClosestEnemyWithLowestHealth(final double n) {
        return (EntityPlayer)ModuleUtil.mc.world.field_73010_i.stream().filter(ModuleUtil::lambda$getClosestEnemyWithLowestHealth$5).filter(ModuleUtil::lambda$getClosestEnemyWithLowestHealth$6).filter(ModuleUtil::lambda$getClosestEnemyWithLowestHealth$7).filter(ModuleUtil::lambda$getClosestEnemyWithLowestHealth$8).sorted(ModuleUtil::lambda$getClosestEnemyWithLowestHealth$9).findFirst().orElse(null);
    }
    
    public static EntityPlayer getClosestEnemyExcludingOwnBlockPos(final double n) {
        return (EntityPlayer)ModuleUtil.mc.world.field_73010_i.stream().filter(ModuleUtil::lambda$getClosestEnemyExcludingOwnBlockPos$10).filter(ModuleUtil::lambda$getClosestEnemyExcludingOwnBlockPos$11).filter(ModuleUtil::lambda$getClosestEnemyExcludingOwnBlockPos$12).filter(ModuleUtil::lambda$getClosestEnemyExcludingOwnBlockPos$13).filter(ModuleUtil::lambda$getClosestEnemyExcludingOwnBlockPos$14).sorted(Comparator.comparing((Function<? super T, ? extends Comparable>)ModuleUtil::lambda$getClosestEnemyExcludingOwnBlockPos$15)).findFirst().orElse(null);
    }
    
    public static EntityPlayer getClosestEnemyWithLowestHealthExcludingOwnBlockPos(final double n) {
        return (EntityPlayer)ModuleUtil.mc.world.field_73010_i.stream().filter(ModuleUtil::lambda$getClosestEnemyWithLowestHealthExcludingOwnBlockPos$16).filter(ModuleUtil::lambda$getClosestEnemyWithLowestHealthExcludingOwnBlockPos$17).filter(ModuleUtil::lambda$getClosestEnemyWithLowestHealthExcludingOwnBlockPos$18).filter(ModuleUtil::lambda$getClosestEnemyWithLowestHealthExcludingOwnBlockPos$19).filter(ModuleUtil::lambda$getClosestEnemyWithLowestHealthExcludingOwnBlockPos$20).sorted(ModuleUtil::lambda$getClosestEnemyWithLowestHealthExcludingOwnBlockPos$21).findFirst().orElse(null);
    }
    
    public static EntityPlayer getClosestEnemy(final double n, final Predicate predicate) {
        return (EntityPlayer)ModuleUtil.mc.world.field_73010_i.stream().filter(ModuleUtil::lambda$getClosestEnemy$22).filter(ModuleUtil::lambda$getClosestEnemy$23).filter(ModuleUtil::lambda$getClosestEnemy$24).filter(ModuleUtil::lambda$getClosestEnemy$25).filter(predicate).sorted(Comparator.comparing((Function<? super T, ? extends Comparable>)ModuleUtil::lambda$getClosestEnemy$26)).findFirst().orElse(null);
    }
    
    public static EntityPlayer getClosestEnemyWithLowestHealth(final double n, final Predicate predicate) {
        return (EntityPlayer)ModuleUtil.mc.world.field_73010_i.stream().filter(ModuleUtil::lambda$getClosestEnemyWithLowestHealth$27).filter(ModuleUtil::lambda$getClosestEnemyWithLowestHealth$28).filter(ModuleUtil::lambda$getClosestEnemyWithLowestHealth$29).filter(ModuleUtil::lambda$getClosestEnemyWithLowestHealth$30).filter(predicate).sorted(ModuleUtil::lambda$getClosestEnemyWithLowestHealth$31).findFirst().orElse(null);
    }
    
    public static EntityPlayer getClosestEnemyExcludingOwnBlockPos(final double n, final Predicate predicate) {
        return (EntityPlayer)ModuleUtil.mc.world.field_73010_i.stream().filter(ModuleUtil::lambda$getClosestEnemyExcludingOwnBlockPos$32).filter(ModuleUtil::lambda$getClosestEnemyExcludingOwnBlockPos$33).filter(ModuleUtil::lambda$getClosestEnemyExcludingOwnBlockPos$34).filter(ModuleUtil::lambda$getClosestEnemyExcludingOwnBlockPos$35).filter(ModuleUtil::lambda$getClosestEnemyExcludingOwnBlockPos$36).filter(predicate).sorted(Comparator.comparing((Function<? super T, ? extends Comparable>)ModuleUtil::lambda$getClosestEnemyExcludingOwnBlockPos$37)).findFirst().orElse(null);
    }
    
    public static EntityPlayer getClosestEnemyWithLowestHealthExcludingOwnBlockPos(final double n, final Predicate predicate) {
        return (EntityPlayer)ModuleUtil.mc.world.field_73010_i.stream().filter(ModuleUtil::lambda$getClosestEnemyWithLowestHealthExcludingOwnBlockPos$38).filter(ModuleUtil::lambda$getClosestEnemyWithLowestHealthExcludingOwnBlockPos$39).filter(ModuleUtil::lambda$getClosestEnemyWithLowestHealthExcludingOwnBlockPos$40).filter(ModuleUtil::lambda$getClosestEnemyWithLowestHealthExcludingOwnBlockPos$41).filter(ModuleUtil::lambda$getClosestEnemyWithLowestHealthExcludingOwnBlockPos$42).filter(predicate).sorted(ModuleUtil::lambda$getClosestEnemyWithLowestHealthExcludingOwnBlockPos$43).findFirst().orElse(null);
    }
    
    public static List getAllEnemiesInRange(final double n) {
        return (List)ModuleUtil.mc.world.field_73010_i.stream().filter(ModuleUtil::lambda$getAllEnemiesInRange$44).filter(ModuleUtil::lambda$getAllEnemiesInRange$45).filter(ModuleUtil::lambda$getAllEnemiesInRange$46).filter(ModuleUtil::lambda$getAllEnemiesInRange$47).collect(Collectors.toList());
    }
    
    public static boolean isPlayerTrapped(final EntityPlayer entityPlayer) {
        final BlockPos[] array;
        final BlockPos[] trappedPos = array = new BlockPos[] { new BlockPos(entityPlayer.func_174791_d()).add(0, -1, 0), new BlockPos(entityPlayer.func_174791_d()).add(1, 0, 0), new BlockPos(entityPlayer.func_174791_d()).add(-1, 0, 0), new BlockPos(entityPlayer.func_174791_d()).add(0, 0, 1), new BlockPos(entityPlayer.func_174791_d()).add(0, 0, -1), new BlockPos(entityPlayer.func_174791_d()).add(1, 1, 0), new BlockPos(entityPlayer.func_174791_d()).add(-1, 1, 0), new BlockPos(entityPlayer.func_174791_d()).add(0, 1, 1), new BlockPos(entityPlayer.func_174791_d()).add(0, 1, -1), new BlockPos(entityPlayer.func_174791_d()).add(0, 2, 0) };
        for (final BlockPos b : array) {
            if (ModuleUtil.mc.world.func_180495_p(b).func_185904_a().isReplaceable()) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isPlayerBurrowed(final EntityPlayer entityPlayer) {
        return ModuleUtil.mc.world.func_180495_p(new BlockPos(entityPlayer.func_174791_d())).getBlock().getExplosionResistance((Entity)null) > Float.intBitsToFloat(Float.floatToIntBits(0.18021916f) ^ 0x7F188B5F);
    }
    
    public static boolean isPlayerUnBeddable(final EntityPlayer entityPlayer) {
        final BlockPos[] array;
        final BlockPos[] surroundPos = array = new BlockPos[] { new BlockPos(entityPlayer.func_174791_d()).add(1, 0, 0), new BlockPos(entityPlayer.func_174791_d()).add(-1, 0, 0), new BlockPos(entityPlayer.func_174791_d()).add(0, 0, 1), new BlockPos(entityPlayer.func_174791_d()).add(0, 0, -1) };
        for (final BlockPos b : array) {
            if (ModuleUtil.mc.world.func_180495_p(b).func_185904_a().isReplaceable()) {
                return false;
            }
        }
        return !ModuleUtil.mc.world.func_180495_p(new BlockPos(entityPlayer.func_174791_d()).add(0, 1, 0)).func_185904_a().isReplaceable();
    }
    
    public static Vec3d getCenter(final double a, final double a2, final double a3) {
        return new Vec3d(Math.floor(a) + Double.longBitsToDouble(Double.doubleToLongBits(23.31466236007918) ^ 0x7FD7508DB661D27FL), Math.floor(a2), Math.floor(a3) + Double.longBitsToDouble(Double.doubleToLongBits(12.684443629142626) ^ 0x7FC95E6F65363F0BL));
    }
    
    public static List findSafeObsidianHoles(final int n) {
        final List<BlockPos> obbyHoles = new ArrayList<BlockPos>();
        for (int x = -n; x < n + 1; ++x) {
            for (int y = -n; y < n + 1; ++y) {
                for (int z = -n; z < n + 1; ++z) {
                    final BlockPos b = new BlockPos(ModuleUtil.mc.player.func_174791_d()).add(x, y, z);
                    if (isHoleObsidianSafe(b)) {
                        obbyHoles.add(b);
                    }
                }
            }
        }
        return obbyHoles;
    }
    
    public static List findSafeBedrockHoles(final int n) {
        final List<BlockPos> bedHoles = new ArrayList<BlockPos>();
        for (int x = -n; x < n + 1; ++x) {
            for (int y = -n; y < n + 1; ++y) {
                for (int z = -n; z < n + 1; ++z) {
                    final BlockPos b = new BlockPos(ModuleUtil.mc.player.func_174791_d()).add(x, y, z);
                    if (isHoleBedrockSafe(b)) {
                        bedHoles.add(b);
                    }
                }
            }
        }
        return bedHoles;
    }
    
    public static List findSafeMixedHoles(final int n) {
        final List<BlockPos> mixedHoles = new ArrayList<BlockPos>();
        for (int x = -n; x < n + 1; ++x) {
            for (int y = -n; y < n + 1; ++y) {
                for (int z = -n; z < n + 1; ++z) {
                    final BlockPos b = new BlockPos(ModuleUtil.mc.player.func_174791_d()).add(x, y, z);
                    if (isHoleSafeAndMixed(b)) {
                        mixedHoles.add(b);
                    }
                }
            }
        }
        return mixedHoles;
    }
    
    public static boolean isHoleObsidianSafe(final BlockPos blockPos) {
        final BlockPos[] surroundPos = { blockPos.add(1, 0, 0), blockPos.add(-1, 0, 0), blockPos.add(0, 0, 1), blockPos.add(0, 0, -1), blockPos.add(0, -1, 0) };
        final BlockPos[] airPos = { blockPos, blockPos.add(0, 1, 0), blockPos.add(0, 2, 0) };
        final BlockPos[] additionalPos = { blockPos.add(1, 1, 0), blockPos.add(-1, 1, 0), blockPos.add(0, 1, 1), blockPos.add(0, 1, -1) };
        for (final BlockPos b : surroundPos) {
            if (!(ModuleUtil.mc.world.func_180495_p(b).getBlock() instanceof BlockObsidian)) {
                return false;
            }
        }
        for (final BlockPos b : airPos) {
            if (!(ModuleUtil.mc.world.func_180495_p(b).getBlock() instanceof BlockAir)) {
                return false;
            }
        }
        int shit = 0;
        for (final BlockPos b2 : additionalPos) {
            if (!ModuleUtil.mc.world.func_180495_p(b2).func_185904_a().isReplaceable()) {
                ++shit;
            }
        }
        return shit != 4 || ModuleUtil.mc.world.func_180495_p(blockPos.add(0, 3, 0)).func_185904_a().isReplaceable();
    }
    
    public static boolean isHoleBedrockSafe(final BlockPos blockPos) {
        final BlockPos[] surroundPos = { blockPos.add(1, 0, 0), blockPos.add(-1, 0, 0), blockPos.add(0, 0, 1), blockPos.add(0, 0, -1), blockPos.add(0, -1, 0) };
        final BlockPos[] additionalPos = { blockPos.add(1, 1, 0), blockPos.add(-1, 1, 0), blockPos.add(0, 1, 1), blockPos.add(0, 1, -1) };
        final BlockPos[] airPos = { blockPos, blockPos.add(0, 1, 0), blockPos.add(0, 2, 0) };
        for (final BlockPos b : surroundPos) {
            if (!ModuleUtil.mc.world.func_180495_p(b).getBlock().equals(Blocks.BEDROCK)) {
                return false;
            }
        }
        for (final BlockPos b : airPos) {
            if (!(ModuleUtil.mc.world.func_180495_p(b).getBlock() instanceof BlockAir)) {
                return false;
            }
        }
        int shit = 0;
        for (final BlockPos b2 : additionalPos) {
            if (!ModuleUtil.mc.world.func_180495_p(b2).func_185904_a().isReplaceable()) {
                ++shit;
            }
        }
        if (shit == 4) {
            if (!ModuleUtil.mc.world.func_180495_p(blockPos.add(0, 3, 0)).func_185904_a().isReplaceable()) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isHoleSafeAndMixed(final BlockPos blockPos) {
        final BlockPos[] surroundPos = { blockPos.add(1, 0, 0), blockPos.add(-1, 0, 0), blockPos.add(0, 0, 1), blockPos.add(0, 0, -1), blockPos.add(0, -1, 0) };
        final BlockPos[] additionalPos = { blockPos.add(1, 1, 0), blockPos.add(-1, 1, 0), blockPos.add(0, 1, 1), blockPos.add(0, 1, -1) };
        final BlockPos[] airPos = { blockPos, blockPos.add(0, 1, 0), blockPos.add(0, 2, 0) };
        for (final BlockPos b : surroundPos) {
            if (!ModuleUtil.mc.world.func_180495_p(b).getBlock().equals(Blocks.BEDROCK) && !ModuleUtil.mc.world.func_180495_p(b).getBlock().equals(Blocks.OBSIDIAN)) {
                return false;
            }
        }
        for (final BlockPos b : airPos) {
            if (!(ModuleUtil.mc.world.func_180495_p(b).getBlock() instanceof BlockAir)) {
                return false;
            }
        }
        int shit = 0;
        for (final BlockPos b2 : additionalPos) {
            if (!ModuleUtil.mc.world.func_180495_p(b2).func_185904_a().isReplaceable()) {
                ++shit;
            }
        }
        if (shit == 4) {
            if (!ModuleUtil.mc.world.func_180495_p(blockPos.add(0, 3, 0)).func_185904_a().isReplaceable()) {
                return false;
            }
        }
        final List<Block> blocksAroundPos = new ArrayList<Block>();
        for (final BlockPos b3 : surroundPos) {
            blocksAroundPos.add(ModuleUtil.mc.world.func_180495_p(b3).getBlock());
        }
        return blocksAroundPos.contains(Blocks.OBSIDIAN) && blocksAroundPos.contains(Blocks.BEDROCK);
    }
    
    public static boolean isBlockEmpty(final BlockPos blockPos) {
        if (ModuleUtil.emptyBlocks.contains(ModuleUtil.mc.world.func_180495_p(blockPos).getBlock())) {
            final AxisAlignedBB box = new AxisAlignedBB(blockPos);
            for (final Entity e : ModuleUtil.mc.world.field_72996_f) {
                if (e instanceof EntityLivingBase && box.intersects(e.getEntityBoundingBox())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    public static boolean canPlaceBlock(final BlockPos blockPos) {
        if (isBlockEmpty(blockPos)) {
            final EnumFacing[] values;
            final EnumFacing[] facings = values = EnumFacing.values();
            for (final EnumFacing f : values) {
                if (!ModuleUtil.emptyBlocks.contains(ModuleUtil.mc.world.func_180495_p(blockPos.offset(f)).getBlock())) {
                    if (ModuleUtil.mc.player.func_174824_e(ModuleUtil.mc.getRenderPartialTicks()).distanceTo(new Vec3d(blockPos.func_177958_n() + Double.longBitsToDouble(Double.doubleToLongBits(3.355300705956062) ^ 0x7FEAD7A7E5829DB1L) + f.getFrontOffsetX() * Double.longBitsToDouble(Double.doubleToLongBits(3.8028349362654716) ^ 0x7FEE6C34B91AC585L), blockPos.func_177956_o() + Double.longBitsToDouble(Double.doubleToLongBits(24.734424233592687) ^ 0x7FD8BC033A00ABA3L) + f.getFrontOffsetY() * Double.longBitsToDouble(Double.doubleToLongBits(3.295540950308987) ^ 0x7FEA5D4492E1A59AL), blockPos.func_177952_p() + Double.longBitsToDouble(Double.doubleToLongBits(10.491274617913197) ^ 0x7FC4FB8858C2958BL) + f.getFrontOffsetZ() * Double.longBitsToDouble(Double.doubleToLongBits(109.21717605272146) ^ 0x7FBB4DE63662FA6FL))) <= Double.longBitsToDouble(Double.doubleToLongBits(1.6815268924336415) ^ 0x7FEBE788BE258D50L)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public static float sendBedPacket(final BlockPos blockPos, final BlockPos blockPos2) {
        final double diffX = blockPos2.func_177958_n() - blockPos.func_177958_n();
        final double diffY = blockPos2.func_177956_o() - (ModuleUtil.mc.player.field_70163_u + ModuleUtil.mc.player.func_70047_e());
        final double diffZ = blockPos2.func_177952_p() - blockPos.func_177952_p();
        final double diffXZ = Math.sqrt(diffX * diffX + diffZ * diffZ);
        final float yaw = (float)Math.toDegrees(Math.atan2(diffZ, diffX)) - Float.intBitsToFloat(Float.floatToIntBits(0.22088479f) ^ 0x7CD62F9F);
        final float pitch = (float)(-Math.toDegrees(Math.atan2(diffY, diffXZ)));
        ModuleUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(yaw, pitch, ModuleUtil.mc.player.field_70122_E));
        return yaw;
    }
    
    public static float getBedPacket(final BlockPos blockPos, final BlockPos blockPos2) {
        final double diffX = blockPos2.func_177958_n() - blockPos.func_177958_n();
        final double diffZ = blockPos2.func_177952_p() - blockPos.func_177952_p();
        final float yaw = (float)Math.toDegrees(Math.atan2(diffZ, diffX)) - Float.intBitsToFloat(Float.floatToIntBits(0.10789469f) ^ 0x7F68F7E4);
        return yaw;
    }
    
    public static void placeBlock(final BlockPos blockPos, final EnumFacing enumFacing) {
        final BlockPos adj = blockPos.offset(enumFacing);
        final EnumFacing opposite = enumFacing.getOpposite();
        ModuleUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction(ModuleUtil.mc.player, CPacketEntityAction.Action.START_SNEAKING));
        final Vec3d hitVec = new Vec3d(adj).addVector(Double.longBitsToDouble(Double.doubleToLongBits(2.537276508091276) ^ 0x7FE44C57A03949F4L), Double.longBitsToDouble(Double.doubleToLongBits(3.7821160154379263) ^ 0x7FEE41C60A9FDEABL), Double.longBitsToDouble(Double.doubleToLongBits(3.606639813027382) ^ 0x7FECDA65F96B3BB8L)).add(new Vec3d(opposite.getDirectionVec()).scale(Double.longBitsToDouble(Double.doubleToLongBits(30.081475399435757) ^ 0x7FDE14DB9260014FL)));
        ModuleUtil.mc.playerController.processRightClickBlock(ModuleUtil.mc.player, ModuleUtil.mc.world, adj, opposite, hitVec, EnumHand.MAIN_HAND);
        ModuleUtil.mc.player.swingArm(EnumHand.MAIN_HAND);
        ModuleUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction(ModuleUtil.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
    }
    
    public static void placeBlock(final BlockPos blockPos, final EnumFacing enumFacing, final boolean b) {
        final BlockPos adj = blockPos.offset(enumFacing);
        final EnumFacing opposite = enumFacing.getOpposite();
        ModuleUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction(ModuleUtil.mc.player, CPacketEntityAction.Action.START_SNEAKING));
        final Vec3d hitVec = new Vec3d(adj).addVector(Double.longBitsToDouble(Double.doubleToLongBits(16.38754870084008) ^ 0x7FD063366443B727L), Double.longBitsToDouble(Double.doubleToLongBits(23.98061988014231) ^ 0x7FD7FB09E78B04C7L), Double.longBitsToDouble(Double.doubleToLongBits(446.17607630769265) ^ 0x7F9BE2D13563F23FL)).add(new Vec3d(opposite.getDirectionVec()).scale(Double.longBitsToDouble(Double.doubleToLongBits(124.16788424340699) ^ 0x7FBF0ABE9D8DBC97L)));
        ModuleUtil.mc.playerController.processRightClickBlock(ModuleUtil.mc.player, ModuleUtil.mc.world, adj, opposite, hitVec, EnumHand.MAIN_HAND);
        if (b) {
            ModuleUtil.mc.player.swingArm(EnumHand.MAIN_HAND);
        }
        ModuleUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction(ModuleUtil.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
    }
    
    public static void placeBlock(final BlockPos blockPos, final int currentItem) {
        if (isBlockEmpty(blockPos)) {
            if (currentItem != ModuleUtil.mc.player.field_71071_by.currentItem) {
                ModuleUtil.mc.player.field_71071_by.currentItem = currentItem;
            }
            final EnumFacing[] values;
            final EnumFacing[] facings = values = EnumFacing.values();
            for (final EnumFacing f : values) {
                final Block neighborBlock = ModuleUtil.mc.world.func_180495_p(blockPos.offset(f)).getBlock();
                final Vec3d vec = new Vec3d(blockPos.func_177958_n() + Double.longBitsToDouble(Double.doubleToLongBits(24.84391999719043) ^ 0x7FD8D80B24145F91L) + f.getFrontOffsetX() * Double.longBitsToDouble(Double.doubleToLongBits(25.804433879706213) ^ 0x7FD9CDEF60F521F1L), blockPos.func_177956_o() + Double.longBitsToDouble(Double.doubleToLongBits(31.85579280715992) ^ 0x7FDFDB153CC6E765L) + f.getFrontOffsetY() * Double.longBitsToDouble(Double.doubleToLongBits(3.843876318233424) ^ 0x7FEEC0423A257399L), blockPos.func_177952_p() + Double.longBitsToDouble(Double.doubleToLongBits(3.1206447371100756) ^ 0x7FE8F7149682940EL) + f.getFrontOffsetZ() * Double.longBitsToDouble(Double.doubleToLongBits(39.13394912622493) ^ 0x7FA391253EB63B5FL));
                if (!ModuleUtil.emptyBlocks.contains(neighborBlock)) {
                    if (ModuleUtil.mc.player.func_174824_e(ModuleUtil.mc.getRenderPartialTicks()).distanceTo(vec) <= Double.longBitsToDouble(Double.doubleToLongBits(1.4487694414316052) ^ 0x7FE62E28DDA64973L)) {
                        if (ModuleUtil.rightclickableBlocks.contains(neighborBlock)) {
                            ModuleUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction(ModuleUtil.mc.player, CPacketEntityAction.Action.START_SNEAKING));
                        }
                        ModuleUtil.mc.playerController.processRightClickBlock(ModuleUtil.mc.player, ModuleUtil.mc.world, blockPos.offset(f), f.getOpposite(), new Vec3d(blockPos), EnumHand.MAIN_HAND);
                        if (ModuleUtil.rightclickableBlocks.contains(neighborBlock)) {
                            ModuleUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction(ModuleUtil.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
                        }
                        ModuleUtil.mc.player.swingArm(EnumHand.MAIN_HAND);
                        return;
                    }
                }
            }
        }
    }
    
    public static void openBlock(final BlockPos blockPos) {
        final EnumFacing[] facings = EnumFacing.values();
        int checkedFacings = 0;
        for (final EnumFacing f : facings) {
            final Block neighborBlock = ModuleUtil.mc.world.func_180495_p(blockPos.offset(f)).getBlock();
            if (ModuleUtil.emptyBlocks.contains(neighborBlock)) {
                ModuleUtil.mc.playerController.processRightClickBlock(ModuleUtil.mc.player, ModuleUtil.mc.world, blockPos, f.getOpposite(), new Vec3d(blockPos), EnumHand.MAIN_HAND);
                return;
            }
            ++checkedFacings;
        }
        if (checkedFacings == 6) {
            ModuleUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos, EnumFacing.DOWN, EnumHand.MAIN_HAND, Float.intBitsToFloat(Float.floatToIntBits(1.2073655E38f) ^ 0x7EB5AA07), Float.intBitsToFloat(Float.floatToIntBits(2.7395858E38f) ^ 0x7F4E1A81), Float.intBitsToFloat(Float.floatToIntBits(2.6831478E38f) ^ 0x7F49DB8C)));
        }
    }
    
    public static boolean is32k(final ItemStack itemStack) {
        if (itemStack.getTagCompound() == null) {
            return false;
        }
        final NBTTagList enchants = (NBTTagList)itemStack.getTagCompound().getTag("ench");
        if (enchants == null) {
            return false;
        }
        int i = 0;
        while (i < enchants.tagCount()) {
            final NBTTagCompound enchant = enchants.getCompoundTagAt(i);
            if (enchant.getInteger("id") == 16) {
                final int lvl = enchant.getInteger("lvl");
                if (lvl >= 50) {
                    return true;
                }
                return false;
            }
            else {
                ++i;
            }
        }
        return false;
    }
    
    public static void rotateClient(final double n, final double n2, final double n3) {
        final double diffX = n - ModuleUtil.mc.player.field_70165_t;
        final double diffY = n2 - (ModuleUtil.mc.player.field_70163_u + ModuleUtil.mc.player.func_70047_e());
        final double diffZ = n3 - ModuleUtil.mc.player.field_70161_v;
        final double diffXZ = Math.sqrt(diffX * diffX + diffZ * diffZ);
        final float yaw = (float)Math.toDegrees(Math.atan2(diffZ, diffX)) - Float.intBitsToFloat(Float.floatToIntBits(3.980845E-5f) ^ 0x7A92F7FF);
        final float pitch = (float)(-Math.toDegrees(Math.atan2(diffY, diffXZ)));
        final EntityPlayerSP player = ModuleUtil.mc.player;
        player.field_70177_z += MathHelper.wrapDegrees(yaw - ModuleUtil.mc.player.field_70177_z);
        final EntityPlayerSP player2 = ModuleUtil.mc.player;
        player2.field_70125_A += MathHelper.wrapDegrees(pitch - ModuleUtil.mc.player.field_70125_A);
    }
    
    public static boolean lambda$getAllEnemiesInRange$47(final EntityPlayer entityPlayer) {
        return entityPlayer.func_110143_aJ() > Float.intBitsToFloat(Float.floatToIntBits(1.6992131E38f) ^ 0x7EFFAB4F);
    }
    
    public static boolean lambda$getAllEnemiesInRange$46(final EntityPlayer entityPlayer) {
        return !FriendUtil.isFriend(entityPlayer.getName());
    }
    
    public static boolean lambda$getAllEnemiesInRange$45(final double n, final EntityPlayer entityPlayer) {
        return ModuleUtil.mc.player.func_70032_d(entityPlayer) <= n;
    }
    
    public static boolean lambda$getAllEnemiesInRange$44(final EntityPlayer entityPlayer) {
        return entityPlayer != ModuleUtil.mc.player;
    }
    
    public static int lambda$getClosestEnemyWithLowestHealthExcludingOwnBlockPos$43(final EntityPlayer entityPlayer, final EntityPlayer entityPlayer2) {
        return Float.compare(entityPlayer.func_110143_aJ(), entityPlayer2.func_110143_aJ());
    }
    
    public static boolean lambda$getClosestEnemyWithLowestHealthExcludingOwnBlockPos$42(final EntityPlayer entityPlayer) {
        return !new AxisAlignedBB(new BlockPos(ModuleUtil.mc.player.func_174791_d())).intersects(new AxisAlignedBB(new BlockPos(entityPlayer.func_174791_d())));
    }
    
    public static boolean lambda$getClosestEnemyWithLowestHealthExcludingOwnBlockPos$41(final EntityPlayer entityPlayer) {
        return entityPlayer.func_110143_aJ() > Float.intBitsToFloat(Float.floatToIntBits(3.7180494E37f) ^ 0x7DDFC59F);
    }
    
    public static boolean lambda$getClosestEnemyWithLowestHealthExcludingOwnBlockPos$40(final EntityPlayer entityPlayer) {
        return !FriendUtil.isFriend(entityPlayer.getName());
    }
    
    public static boolean lambda$getClosestEnemyWithLowestHealthExcludingOwnBlockPos$39(final double n, final EntityPlayer entityPlayer) {
        return ModuleUtil.mc.player.func_70032_d(entityPlayer) <= n;
    }
    
    public static boolean lambda$getClosestEnemyWithLowestHealthExcludingOwnBlockPos$38(final EntityPlayer entityPlayer) {
        return entityPlayer != ModuleUtil.mc.player;
    }
    
    public static Float lambda$getClosestEnemyExcludingOwnBlockPos$37(final EntityPlayer entityPlayer) {
        return ModuleUtil.mc.player.func_70032_d(entityPlayer);
    }
    
    public static boolean lambda$getClosestEnemyExcludingOwnBlockPos$36(final EntityPlayer entityPlayer) {
        return !new AxisAlignedBB(new BlockPos(ModuleUtil.mc.player.func_174791_d())).intersects(new AxisAlignedBB(new BlockPos(entityPlayer.func_174791_d())));
    }
    
    public static boolean lambda$getClosestEnemyExcludingOwnBlockPos$35(final EntityPlayer entityPlayer) {
        return entityPlayer.func_110143_aJ() > Float.intBitsToFloat(Float.floatToIntBits(3.367758E38f) ^ 0x7F7D5CA9);
    }
    
    public static boolean lambda$getClosestEnemyExcludingOwnBlockPos$34(final EntityPlayer entityPlayer) {
        return !FriendUtil.isFriend(entityPlayer.getName());
    }
    
    public static boolean lambda$getClosestEnemyExcludingOwnBlockPos$33(final double n, final EntityPlayer entityPlayer) {
        return ModuleUtil.mc.player.func_70032_d(entityPlayer) <= n;
    }
    
    public static boolean lambda$getClosestEnemyExcludingOwnBlockPos$32(final EntityPlayer entityPlayer) {
        return entityPlayer != ModuleUtil.mc.player;
    }
    
    public static int lambda$getClosestEnemyWithLowestHealth$31(final EntityPlayer entityPlayer, final EntityPlayer entityPlayer2) {
        return Float.compare(entityPlayer.func_110143_aJ(), entityPlayer2.func_110143_aJ());
    }
    
    public static boolean lambda$getClosestEnemyWithLowestHealth$30(final EntityPlayer entityPlayer) {
        return entityPlayer.func_110143_aJ() > Float.intBitsToFloat(Float.floatToIntBits(1.977884E38f) ^ 0x7F14CCA9);
    }
    
    public static boolean lambda$getClosestEnemyWithLowestHealth$29(final EntityPlayer entityPlayer) {
        return !FriendUtil.isFriend(entityPlayer.getName());
    }
    
    public static boolean lambda$getClosestEnemyWithLowestHealth$28(final double n, final EntityPlayer entityPlayer) {
        return ModuleUtil.mc.player.func_70032_d(entityPlayer) <= n;
    }
    
    public static boolean lambda$getClosestEnemyWithLowestHealth$27(final EntityPlayer entityPlayer) {
        return entityPlayer != ModuleUtil.mc.player;
    }
    
    public static Float lambda$getClosestEnemy$26(final EntityPlayer entityPlayer) {
        return ModuleUtil.mc.player.func_70032_d(entityPlayer);
    }
    
    public static boolean lambda$getClosestEnemy$25(final EntityPlayer entityPlayer) {
        return entityPlayer.func_110143_aJ() > Float.intBitsToFloat(Float.floatToIntBits(1.0586717E38f) ^ 0x7E9F4A8D);
    }
    
    public static boolean lambda$getClosestEnemy$24(final EntityPlayer entityPlayer) {
        return !FriendUtil.isFriend(entityPlayer.getName());
    }
    
    public static boolean lambda$getClosestEnemy$23(final double n, final EntityPlayer entityPlayer) {
        return ModuleUtil.mc.player.func_70032_d(entityPlayer) <= n;
    }
    
    public static boolean lambda$getClosestEnemy$22(final EntityPlayer entityPlayer) {
        return entityPlayer != ModuleUtil.mc.player;
    }
    
    public static int lambda$getClosestEnemyWithLowestHealthExcludingOwnBlockPos$21(final EntityPlayer entityPlayer, final EntityPlayer entityPlayer2) {
        return Float.compare(entityPlayer.func_110143_aJ(), entityPlayer2.func_110143_aJ());
    }
    
    public static boolean lambda$getClosestEnemyWithLowestHealthExcludingOwnBlockPos$20(final EntityPlayer entityPlayer) {
        return !new AxisAlignedBB(new BlockPos(ModuleUtil.mc.player.func_174791_d())).intersects(new AxisAlignedBB(new BlockPos(entityPlayer.func_174791_d())));
    }
    
    public static boolean lambda$getClosestEnemyWithLowestHealthExcludingOwnBlockPos$19(final EntityPlayer entityPlayer) {
        return entityPlayer.func_110143_aJ() > Float.intBitsToFloat(Float.floatToIntBits(3.0063693E38f) ^ 0x7F622C91);
    }
    
    public static boolean lambda$getClosestEnemyWithLowestHealthExcludingOwnBlockPos$18(final EntityPlayer entityPlayer) {
        return !FriendUtil.isFriend(entityPlayer.getName());
    }
    
    public static boolean lambda$getClosestEnemyWithLowestHealthExcludingOwnBlockPos$17(final double n, final EntityPlayer entityPlayer) {
        return ModuleUtil.mc.player.func_70032_d(entityPlayer) <= n;
    }
    
    public static boolean lambda$getClosestEnemyWithLowestHealthExcludingOwnBlockPos$16(final EntityPlayer entityPlayer) {
        return entityPlayer != ModuleUtil.mc.player;
    }
    
    public static Float lambda$getClosestEnemyExcludingOwnBlockPos$15(final EntityPlayer entityPlayer) {
        return ModuleUtil.mc.player.func_70032_d(entityPlayer);
    }
    
    public static boolean lambda$getClosestEnemyExcludingOwnBlockPos$14(final EntityPlayer entityPlayer) {
        return !new AxisAlignedBB(new BlockPos(ModuleUtil.mc.player.func_174791_d())).intersects(new AxisAlignedBB(new BlockPos(entityPlayer.func_174791_d())));
    }
    
    public static boolean lambda$getClosestEnemyExcludingOwnBlockPos$13(final EntityPlayer entityPlayer) {
        return entityPlayer.func_110143_aJ() > Float.intBitsToFloat(Float.floatToIntBits(7.3348733E37f) ^ 0x7E5CB9CF);
    }
    
    public static boolean lambda$getClosestEnemyExcludingOwnBlockPos$12(final EntityPlayer entityPlayer) {
        return !FriendUtil.isFriend(entityPlayer.getName());
    }
    
    public static boolean lambda$getClosestEnemyExcludingOwnBlockPos$11(final double n, final EntityPlayer entityPlayer) {
        return ModuleUtil.mc.player.func_70032_d(entityPlayer) <= n;
    }
    
    public static boolean lambda$getClosestEnemyExcludingOwnBlockPos$10(final EntityPlayer entityPlayer) {
        return entityPlayer != ModuleUtil.mc.player;
    }
    
    public static int lambda$getClosestEnemyWithLowestHealth$9(final EntityPlayer entityPlayer, final EntityPlayer entityPlayer2) {
        return Float.compare(entityPlayer.func_110143_aJ(), entityPlayer2.func_110143_aJ());
    }
    
    public static boolean lambda$getClosestEnemyWithLowestHealth$8(final EntityPlayer entityPlayer) {
        return entityPlayer.func_110143_aJ() > Float.intBitsToFloat(Float.floatToIntBits(2.5831545E38f) ^ 0x7F4255BF);
    }
    
    public static boolean lambda$getClosestEnemyWithLowestHealth$7(final EntityPlayer entityPlayer) {
        return !FriendUtil.isFriend(entityPlayer.getName());
    }
    
    public static boolean lambda$getClosestEnemyWithLowestHealth$6(final double n, final EntityPlayer entityPlayer) {
        return ModuleUtil.mc.player.func_70032_d(entityPlayer) <= n;
    }
    
    public static boolean lambda$getClosestEnemyWithLowestHealth$5(final EntityPlayer entityPlayer) {
        return entityPlayer != ModuleUtil.mc.player;
    }
    
    public static Float lambda$getClosestEnemy$4(final EntityPlayer entityPlayer) {
        return ModuleUtil.mc.player.func_70032_d(entityPlayer);
    }
    
    public static boolean lambda$getClosestEnemy$3(final EntityPlayer entityPlayer) {
        return entityPlayer.func_110143_aJ() > Float.intBitsToFloat(Float.floatToIntBits(3.0683797E38f) ^ 0x7F66D6D8);
    }
    
    public static boolean lambda$getClosestEnemy$2(final EntityPlayer entityPlayer) {
        return !FriendUtil.isFriend(entityPlayer.getName());
    }
    
    public static boolean lambda$getClosestEnemy$1(final double n, final EntityPlayer entityPlayer) {
        return ModuleUtil.mc.player.func_70032_d(entityPlayer) <= n;
    }
    
    public static boolean lambda$getClosestEnemy$0(final EntityPlayer entityPlayer) {
        return entityPlayer != ModuleUtil.mc.player;
    }
    
    static {
        ModuleUtil.emptyBlocks = Arrays.asList(Blocks.AIR, (Block)Blocks.FLOWING_LAVA, (Block)Blocks.LAVA, (Block)Blocks.FLOWING_WATER, (Block)Blocks.WATER, Blocks.VINE, Blocks.SNOW_LAYER, (Block)Blocks.TALLGRASS, (Block)Blocks.FIRE);
        ModuleUtil.rightclickableBlocks = Arrays.asList((Block)Blocks.CHEST, Blocks.TRAPPED_CHEST, Blocks.ENDER_CHEST, Blocks.WHITE_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.SILVER_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, Blocks.ANVIL, Blocks.WOODEN_BUTTON, Blocks.STONE_BUTTON, (Block)Blocks.UNPOWERED_COMPARATOR, (Block)Blocks.UNPOWERED_REPEATER, (Block)Blocks.POWERED_REPEATER, (Block)Blocks.POWERED_COMPARATOR, Blocks.OAK_FENCE_GATE, Blocks.SPRUCE_FENCE_GATE, Blocks.BIRCH_FENCE_GATE, Blocks.JUNGLE_FENCE_GATE, Blocks.DARK_OAK_FENCE_GATE, Blocks.ACACIA_FENCE_GATE, Blocks.BREWING_STAND, Blocks.DISPENSER, Blocks.DROPPER, Blocks.LEVER, Blocks.NOTEBLOCK, Blocks.JUKEBOX, (Block)Blocks.BEACON, Blocks.BED, Blocks.FURNACE, (Block)Blocks.OAK_DOOR, (Block)Blocks.SPRUCE_DOOR, (Block)Blocks.BIRCH_DOOR, (Block)Blocks.JUNGLE_DOOR, (Block)Blocks.ACACIA_DOOR, (Block)Blocks.DARK_OAK_DOOR, Blocks.CAKE, Blocks.ENCHANTING_TABLE, Blocks.DRAGON_EGG, Blocks.HOPPER, Blocks.REPEATING_COMMAND_BLOCK, Blocks.COMMAND_BLOCK, Blocks.CHAIN_COMMAND_BLOCK, Blocks.CRAFTING_TABLE);
    }
}
