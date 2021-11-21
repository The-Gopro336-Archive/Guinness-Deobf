package dev.guinness.client.module.modules.combat;

import net.minecraft.util.EnumHand;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSword;
import dev.guinness.client.util.ModuleUtil;
import dev.guinness.client.module.Category;
import dev.guinness.client.setting.SSlider;
import dev.guinness.client.setting.SBoolean;
import dev.guinness.client.module.Module;

public class KillAura extends Module
{
    public static SBoolean swordOnly;
    public static SSlider range;
    
    public KillAura() {
        super("KillAura", Category.COMBAT, "Automatically attacks players");
    }
    
    @Override
    public void onUpdate() {
        final EntityPlayer target = ModuleUtil.getClosestEnemy((double)KillAura.range.getValue());
        if (target != null) {
            if (KillAura.mc.player.func_184825_o(Float.intBitsToFloat(Float.floatToIntBits(1.6273623E38f) ^ 0x7EF4DBB7)) >= Float.intBitsToFloat(Float.floatToIntBits(35.54788f) ^ 0x7D8E3107)) {
                if (!(boolean)KillAura.swordOnly.getValue() || KillAura.mc.player.func_184614_ca().getItem() instanceof ItemSword) {
                    KillAura.mc.playerController.attackEntity((EntityPlayer)KillAura.mc.player, (Entity)target);
                    KillAura.mc.player.swingArm(EnumHand.MAIN_HAND);
                }
            }
        }
    }
    
    static {
        KillAura.swordOnly = new SBoolean("Sword Only", true);
        KillAura.range = new SSlider("Range", Double.longBitsToDouble(Double.doubleToLongBits(0.9632392245900788) ^ 0x7FE6D2DB10FAD759L), Double.longBitsToDouble(Double.doubleToLongBits(1.7094095158432892) ^ 0x7FE359BDCAE04A39L), Double.longBitsToDouble(Double.doubleToLongBits(1.794840202376591) ^ 0x7FE4B7AA5C2C0E41L), 1);
    }
}
