package dev.guinness.client.module.modules.misc;

import net.minecraft.client.gui.GuiGameOver;
import dev.guinness.client.module.Category;
import dev.guinness.client.module.Module;

public class AutoRespawn extends Module
{
    public AutoRespawn() {
        super("AutoRespawn", Category.MISC, "Automatically respawns you upon death");
    }
    
    @Override
    public void onUpdate() {
        if (AutoRespawn.mc.currentScreen instanceof GuiGameOver) {
            if (AutoRespawn.mc.player.func_110143_aJ() <= Float.intBitsToFloat(Float.floatToIntBits(3.0060551E38f) ^ 0x7F622684)) {
                AutoRespawn.mc.player.respawnPlayer();
            }
        }
    }
}
