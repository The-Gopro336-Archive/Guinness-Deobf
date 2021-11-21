package dev.guinness.client.module.modules.render;

import dev.guinness.client.util.RenderUtil;
import net.minecraft.entity.player.EntityPlayer;
import java.util.function.Consumer;
import java.util.function.Predicate;
import dev.guinness.client.module.Category;
import dev.guinness.client.setting.SMode;
import dev.guinness.client.module.Module;

public class PlayerESP extends Module
{
    public static SMode mode;
    
    public PlayerESP() {
        super("PlayerESP", Category.RENDER, "Highlights players in range");
    }
    
    @Override
    public void onRender3d() {
        PlayerESP.mc.world.field_73010_i.stream().filter(PlayerESP::lambda$onRender3d$0).filter(PlayerESP::lambda$onRender3d$1).forEach(PlayerESP::lambda$onRender3d$2);
    }
    
    @Override
    public void onDisable() {
        PlayerESP.mc.world.field_73010_i.stream().filter(PlayerESP::lambda$onDisable$3).forEach(PlayerESP::lambda$onDisable$4);
    }
    
    public static void lambda$onDisable$4(final EntityPlayer entityPlayer) {
        if (entityPlayer.func_184202_aL()) {
            entityPlayer.func_184195_f(false);
        }
    }
    
    public static boolean lambda$onDisable$3(final EntityPlayer entityPlayer) {
        return entityPlayer.func_110143_aJ() > Float.intBitsToFloat(Float.floatToIntBits(2.5619004E38f) ^ 0x7F40BC68);
    }
    
    public static void lambda$onRender3d$2(final EntityPlayer entityPlayer) {
        final String s = (String)PlayerESP.mode.getValue();
        switch (s) {
            case "Glow": {
                entityPlayer.func_184195_f(true);
                break;
            }
            case "CSGO": {
                if (entityPlayer.func_184202_aL()) {
                    entityPlayer.func_184195_f(false);
                }
                RenderUtil.drawCsgoEspOutline(entityPlayer);
                break;
            }
        }
    }
    
    public static boolean lambda$onRender3d$1(final EntityPlayer entityPlayer) {
        return entityPlayer != PlayerESP.mc.player;
    }
    
    public static boolean lambda$onRender3d$0(final EntityPlayer entityPlayer) {
        return entityPlayer.func_110143_aJ() > Float.intBitsToFloat(Float.floatToIntBits(2.3523028E38f) ^ 0x7F30F7B4);
    }
    
    static {
        PlayerESP.mode = new SMode("Mode", new String[] { "Glow", "CSGO" });
    }
}
