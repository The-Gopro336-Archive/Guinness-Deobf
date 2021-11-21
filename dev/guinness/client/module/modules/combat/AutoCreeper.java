package dev.guinness.client.module.modules.combat;

import dev.guinness.client.util.FriendUtil;
import net.minecraft.entity.Entity;
import net.minecraft.client.entity.EntityPlayerSP;
import dev.guinness.client.util.ModuleUtil;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import dev.guinness.client.util.InventoryUtil;
import net.minecraft.init.Items;
import java.util.function.Predicate;
import net.minecraft.entity.player.EntityPlayer;
import dev.guinness.client.module.Category;
import dev.guinness.client.setting.SSlider;
import dev.guinness.client.module.Module;

public class AutoCreeper extends Module
{
    public static SSlider delay;
    
    public AutoCreeper() {
        super("AutoCreeper", Category.COMBAT, "Autoplace for creeper eggs in anarchypvp's cegg arena");
    }
    
    @Override
    public void onUpdate() {
        final EntityPlayer target = (EntityPlayer)AutoCreeper.mc.world.field_73010_i.stream().filter(AutoCreeper::lambda$onUpdate$0).filter(AutoCreeper::lambda$onUpdate$1).filter(AutoCreeper::lambda$onUpdate$2).filter(AutoCreeper::lambda$onUpdate$3).findFirst().orElse(null);
        final int egg = InventoryUtil.find(Items.SPAWN_EGG);
        if (target == null) {
            return;
        }
        if (egg == -1) {
            return;
        }
        if (AutoCreeper.mc.player.field_70173_aa % (double)AutoCreeper.delay.getValue() == Double.longBitsToDouble(Double.doubleToLongBits(1.3446632051089615E308) ^ 0x7FE7EF908F828243L)) {
            final EntityPlayerSP player = AutoCreeper.mc.player;
            ++player.field_70173_aa;
            AutoCreeper.mc.player.field_71071_by.currentItem = egg;
            final BlockPos targetblock = new BlockPos(target.func_174791_d());
            ModuleUtil.placeBlock(targetblock, EnumFacing.DOWN);
        }
    }
    
    public static boolean lambda$onUpdate$3(final EntityPlayer entityPlayer) {
        return entityPlayer.func_110143_aJ() > Float.intBitsToFloat(Float.floatToIntBits(5.3513044E37f) ^ 0x7E2108F3);
    }
    
    public static boolean lambda$onUpdate$2(final EntityPlayer entityPlayer) {
        return AutoCreeper.mc.player.func_70032_d(entityPlayer) <= Float.intBitsToFloat(Float.floatToIntBits(0.13637504f) ^ 0x7F0BA5E6);
    }
    
    public static boolean lambda$onUpdate$1(final EntityPlayer entityPlayer) {
        return !FriendUtil.isFriend(entityPlayer.getName());
    }
    
    public static boolean lambda$onUpdate$0(final EntityPlayer entityPlayer) {
        return entityPlayer != AutoCreeper.mc.player;
    }
    
    static {
        AutoCreeper.delay = new SSlider("Delay", Double.longBitsToDouble(Double.doubleToLongBits(6.613421468830263) ^ 0x7FEA7424C1ED293BL), Double.longBitsToDouble(Double.doubleToLongBits(0.08675328057251185) ^ 0x7FB6357686E130C7L), Double.longBitsToDouble(Double.doubleToLongBits(0.8679751438281754) ^ 0x7FDFC673CF0F73FFL), 0);
    }
}
