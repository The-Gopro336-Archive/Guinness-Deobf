package dev.guinness.client.module.modules.render;

import net.minecraft.client.settings.GameSettings;
import dev.guinness.client.module.Category;
import dev.guinness.client.module.Module;

public class Fullbright extends Module
{
    public Fullbright() {
        super("Fullbright", Category.RENDER, "Brightens your game");
    }
    
    @Override
    public void onUpdate() {
        if (Fullbright.mc.gameSettings.gammaSetting < Float.intBitsToFloat(Float.floatToIntBits(0.09755649f) ^ 0x7F67CBB2)) {
            final GameSettings gameSettings = Fullbright.mc.gameSettings;
            gameSettings.gammaSetting += Float.intBitsToFloat(Float.floatToIntBits(7.6262593f) ^ 0x7F740A51);
        }
    }
    
    @Override
    public void onDisable() {
        Fullbright.mc.gameSettings.gammaSetting = Float.intBitsToFloat(Float.floatToIntBits(4.7014747f) ^ 0x7F16727B);
    }
}
