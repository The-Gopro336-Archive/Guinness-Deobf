package dev.guinness.client.util;

import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.init.Blocks;
import java.util.List;
import dev.guinness.client.module.modules.combat.AutoCrystal;
import java.util.ArrayList;
import net.minecraft.util.math.BlockPos;
import java.util.function.Function;
import java.util.Comparator;
import java.util.function.Predicate;
import net.minecraft.init.MobEffects;
import net.minecraft.util.math.MathHelper;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.util.DamageSource;
import net.minecraft.util.CombatRules;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.world.Explosion;
import net.minecraft.util.math.Vec3d;
import net.minecraft.entity.player.EntityPlayer;

public class CrystalUtil implements Wrapper
{
    public static float calculateDamage(final double n, final double n2, final double n3, final EntityPlayer entityPlayer) {
        final double factor = (Double.longBitsToDouble(Double.doubleToLongBits(17.496900536567008) ^ 0x7FC17F34DFA1EB8FL) - entityPlayer.func_70011_f(n, n2, n3) / Double.longBitsToDouble(Double.doubleToLongBits(0.20515866996367635) ^ 0x7FE242A3A8FE0F7BL)) * CrystalUtil.mc.world.func_72842_a(new Vec3d(n, n2, n3), entityPlayer.func_174813_aQ());
        final float calculatedDamage = (float)(int)((factor * factor + factor) / Double.longBitsToDouble(Double.doubleToLongBits(0.625332918359871) ^ 0x7FE402BA2E2EF8E8L) * Double.longBitsToDouble(Double.doubleToLongBits(0.0803036381397808) ^ 0x7FA88EC77B8F67BFL) * Double.longBitsToDouble(Double.doubleToLongBits(0.9813301311792432) ^ 0x7FC7670E727FD0CFL) + Double.longBitsToDouble(Double.doubleToLongBits(6.063499989893787) ^ 0x7FE84106242F8F82L));
        double damage = Double.longBitsToDouble(Double.doubleToLongBits(11.31984278013721) ^ 0x7FD6A3C26ED11A21L);
        damage = getBlastReduction(entityPlayer, calculatedDamage * ((CrystalUtil.mc.world.func_175659_aa().getDifficultyId() == 0) ? Float.intBitsToFloat(Float.floatToIntBits(6.469459E36f) ^ 0x7C9BBF1F) : ((CrystalUtil.mc.world.func_175659_aa().getDifficultyId() == 2) ? Float.intBitsToFloat(Float.floatToIntBits(6.9400578f) ^ 0x7F5E14F4) : ((CrystalUtil.mc.world.func_175659_aa().getDifficultyId() == 1) ? Float.intBitsToFloat(Float.floatToIntBits(3.8398082f) ^ 0x7F75BF6B) : Float.intBitsToFloat(Float.floatToIntBits(7.7401643f) ^ 0x7F37AF6D)))), new Explosion(CrystalUtil.mc.world, null, n, n2, n3, Float.intBitsToFloat(Float.floatToIntBits(1.8739382f) ^ 0x7F2FDD35), false, true));
        return (float)damage;
    }
    
    public static float getBlastReduction(final EntityPlayer entityPlayer, float damageAfterAbsorb, final Explosion explosion) {
        damageAfterAbsorb = CombatRules.getDamageAfterAbsorb(damageAfterAbsorb, (float)entityPlayer.func_70658_aO(), (float)entityPlayer.func_110148_a(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue());
        damageAfterAbsorb *= Float.intBitsToFloat(Float.floatToIntBits(6.4983487f) ^ 0x7F4FF279) - MathHelper.clamp((float)EnchantmentHelper.getEnchantmentModifierDamage(entityPlayer.getArmorInventoryList(), DamageSource.causeExplosionDamage(explosion)), Float.intBitsToFloat(Float.floatToIntBits(2.3496458E38f) ^ 0x7F30C488), Float.intBitsToFloat(Float.floatToIntBits(0.45472208f) ^ 0x7F48D155)) / Float.intBitsToFloat(Float.floatToIntBits(0.027668653f) ^ 0x7D2AA95F);
        return entityPlayer.func_70644_a(MobEffects.RESISTANCE) ? (damageAfterAbsorb - damageAfterAbsorb / Float.intBitsToFloat(Float.floatToIntBits(1.0272629f) ^ 0x7F037D5A)) : damageAfterAbsorb;
    }
    
    public static EntityPlayer getTarget(final double n, final boolean b) {
        return (EntityPlayer)ModuleUtil.getAllEnemiesInRange(n).stream().filter(CrystalUtil::lambda$getTarget$0).sorted(Comparator.comparing((Function<? super T, ? extends Comparable>)CrystalUtil::lambda$getTarget$1)).findFirst().orElse(null);
    }
    
    public static BlockPos getBestCrystalPosAroundPlayer(final EntityPlayer entityPlayer, final boolean b) {
        final List<BlockPos> newList = new ArrayList<BlockPos>();
        for (int x = -5; x < 5; ++x) {
            for (int y = -2; y < 1; ++y) {
                for (int z = -5; z < 5; ++z) {
                    final BlockPos checkingPos = new BlockPos(entityPlayer.func_174791_d()).add(x, y, z);
                    if (!CrystalUtil.mc.world.func_180495_p(checkingPos).func_185904_a().isReplaceable()) {
                        if (CrystalUtil.mc.player.func_70011_f(checkingPos.func_177958_n(), checkingPos.func_177956_o(), checkingPos.func_177952_p()) <= (double)AutoCrystal.placeRange.getValue()) {
                            if (canPlaceCrystal(checkingPos, b)) {
                                newList.add(checkingPos);
                            }
                        }
                    }
                }
            }
        }
        newList.sort(CrystalUtil::lambda$getBestCrystalPosAroundPlayer$2);
        return (newList.size() == 0) ? null : newList.get(0);
    }
    
    public static boolean canPlaceCrystal(final BlockPos blockPos, final boolean b) {
        final BlockPos boost = blockPos.add(0, 1, 0);
        final BlockPos boost2 = blockPos.add(0, 2, 0);
        if (CrystalUtil.mc.world.func_180495_p(blockPos).getBlock() == Blocks.BEDROCK || CrystalUtil.mc.world.func_180495_p(blockPos).getBlock() == Blocks.OBSIDIAN) {
            if (CrystalUtil.mc.world.func_180495_p(boost).getBlock() == Blocks.AIR) {
                if (!b) {
                    if (CrystalUtil.mc.world.func_180495_p(boost2).getBlock() != Blocks.AIR) {
                        return false;
                    }
                }
                if (CrystalUtil.mc.world.func_175647_a(Entity.class, new AxisAlignedBB(boost), CrystalUtil::lambda$canPlaceCrystal$3).isEmpty()) {
                    if (CrystalUtil.mc.world.func_175647_a(Entity.class, new AxisAlignedBB(boost), CrystalUtil::lambda$canPlaceCrystal$4).isEmpty()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public static boolean lambda$canPlaceCrystal$4(final Entity entity) {
        return !(entity instanceof EntityEnderCrystal);
    }
    
    public static boolean lambda$canPlaceCrystal$3(final Entity entity) {
        return !(entity instanceof EntityEnderCrystal);
    }
    
    public static int lambda$getBestCrystalPosAroundPlayer$2(final EntityPlayer entityPlayer, final BlockPos blockPos, final BlockPos blockPos2) {
        return Float.compare(calculateDamage(blockPos.func_177958_n(), blockPos.func_177956_o(), blockPos.func_177952_p(), entityPlayer), calculateDamage(blockPos2.func_177958_n(), blockPos2.func_177956_o(), blockPos2.func_177952_p(), entityPlayer));
    }
    
    public static Float lambda$getTarget$1(final EntityPlayer entityPlayer) {
        return CrystalUtil.mc.player.func_70032_d(entityPlayer);
    }
    
    public static boolean lambda$getTarget$0(final boolean b, final EntityPlayer entityPlayer) {
        return getBestCrystalPosAroundPlayer(entityPlayer, b) != null;
    }
}
