package dev.guinness.client.module.modules.misc;

import net.minecraft.init.Items;
import dev.guinness.client.mixin.mixins.IMinecraft;
import dev.guinness.client.module.Category;
import dev.guinness.client.setting.SBoolean;
import dev.guinness.client.module.Module;

public class FastUse extends Module
{
    public static SBoolean everything;
    public static SBoolean xp;
    public static SBoolean crystals;
    
    public FastUse() {
        super("FastUse", Category.MISC, "Lets you use items faster");
    }
    
    @Override
    public void onUpdate() {
        if (FastUse.everything.getValue()) {
            ((IMinecraft)FastUse.mc).setRightClickDelayTimer(0);
        }
        if (FastUse.xp.getValue()) {
            if (FastUse.mc.player.func_184614_ca().getItem() == Items.EXPERIENCE_BOTTLE) {
                ((IMinecraft)FastUse.mc).setRightClickDelayTimer(0);
            }
        }
        if (FastUse.crystals.getValue()) {
            if (FastUse.mc.player.func_184614_ca().getItem() == Items.END_CRYSTAL) {
                ((IMinecraft)FastUse.mc).setRightClickDelayTimer(0);
            }
        }
    }
    
    static {
        FastUse.xp = new SBoolean("XP", true);
        FastUse.crystals = new SBoolean("Crystals", true);
        FastUse.everything = new SBoolean("Everything", false);
    }
}
