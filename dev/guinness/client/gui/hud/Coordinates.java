package dev.guinness.client.gui.hud;

import java.util.ArrayList;
import dev.guinness.client.module.Module;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.function.Predicate;
import dev.guinness.client.module.ModuleManager;
import dev.guinness.client.gui.GuinnessHudGui;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import dev.guinness.client.util.RenderUtil;
import net.minecraft.client.gui.ScaledResolution;
import dev.guinness.client.Guinness;
import net.minecraft.client.entity.EntityPlayerSP;
import dev.guinness.client.util.MathUtil;
import net.minecraftforge.common.MinecraftForge;
import java.util.function.Supplier;
import java.util.List;

public class Coordinates extends HudTextFrame
{
    public static List<String> text;
    
    public Coordinates() {
        super("Coordinates", 200, 180, Coordinates::lambda$new$0);
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    public String getRegCoords() {
        final double playerX = MathUtil.roundDouble(Coordinates.mc.player.field_70165_t, 1);
        final double playerY = MathUtil.roundDouble(Coordinates.mc.player.field_70163_u, 1);
        final double playerZ = MathUtil.roundDouble(Coordinates.mc.player.field_70161_v, 1);
        final double playerNX = (Coordinates.mc.player.field_71093_bK == 0) ? MathUtil.roundDouble(Coordinates.mc.player.field_70165_t / Double.longBitsToDouble(Double.doubleToLongBits(0.22468550681591637) ^ 0x7FECC27EA3D46C00L), 1) : MathUtil.roundDouble(Coordinates.mc.player.field_70165_t * Double.longBitsToDouble(Double.doubleToLongBits(1.7449935759523025) ^ 0x7FDBEB7E62471FABL), 1);
        final double playerNZ = (Coordinates.mc.player.field_71093_bK == 0) ? MathUtil.roundDouble(Coordinates.mc.player.field_70161_v / Double.longBitsToDouble(Double.doubleToLongBits(0.2019856809155119) ^ 0x7FE9DAAAB2E56DDAL), 1) : MathUtil.roundDouble(Coordinates.mc.player.field_70161_v * Double.longBitsToDouble(Double.doubleToLongBits(0.0012012371947985598) ^ 0x7F73AE5A9DD9797FL), 1);
        return playerX + ", " + playerY + ", " + playerZ + " (" + playerNX + ", " + playerY + ", " + playerNZ + ")";
    }
    
    public String getDirect() {
        String direc = Coordinates.mc.player.func_174811_aO().getName();
        String direct = "";
        if (direc.equalsIgnoreCase("north")) {
            direc = "North";
            direct = "-Z";
        }
        if (direc.equalsIgnoreCase("east")) {
            direc = "East";
            direct = "+X";
        }
        if (direc.equalsIgnoreCase("south")) {
            direc = "South";
            direct = "+Z";
        }
        if (direc.equalsIgnoreCase("west")) {
            direc = "West";
            direct = "-X";
        }
        return direc + " " + direct;
    }
    
    public String getRotation() {
        float field_70177_z;
        if (Coordinates.mc.player.field_70177_z <= Float.intBitsToFloat(Float.floatToIntBits(-0.057400923f) ^ 0x7E5F1D3B)) {
            final EntityPlayerSP player = Coordinates.mc.player;
            field_70177_z = (player.field_70177_z += Float.intBitsToFloat(Float.floatToIntBits(0.11815154f) ^ 0x7E45F96F));
        }
        else {
            field_70177_z = Coordinates.mc.player.field_70177_z;
        }
        float yaw = field_70177_z;
        if (yaw > Float.intBitsToFloat(Float.floatToIntBits(0.014097207f) ^ 0x7F52F7F9)) {
            yaw -= Float.intBitsToFloat(Float.floatToIntBits(0.025999593f) ^ 0x7F60FD19);
        }
        yaw = (float)MathUtil.roundDouble(yaw, 1);
        float field_70125_A;
        if (Coordinates.mc.player.field_70125_A <= Float.intBitsToFloat(Float.floatToIntBits(-0.011150887f) ^ 0x7F02B236)) {
            final EntityPlayerSP player2 = Coordinates.mc.player;
            field_70125_A = (player2.field_70125_A += Float.intBitsToFloat(Float.floatToIntBits(0.8289661f) ^ 0x7CE0371F));
        }
        else {
            field_70125_A = Coordinates.mc.player.field_70125_A;
        }
        float pitch = field_70125_A;
        if (pitch > Float.intBitsToFloat(Float.floatToIntBits(0.015201033f) ^ 0x7F4D0DC1)) {
            pitch -= Float.intBitsToFloat(Float.floatToIntBits(0.06124946f) ^ 0x7ECEE0B7);
        }
        pitch = (float)MathUtil.roundDouble(pitch, 1);
        return "[" + yaw + ", " + pitch + "]";
    }
    
    public void setCoords() {
        Coordinates.text.clear();
        Coordinates.text.add("XYZ  " + this.getRegCoords() + " ");
        Coordinates.text.add(this.getDirect() + " " + this.getRotation());
    }
    
    @Override
    public void drawText() {
        this.setCoords();
        final int width = (this.getText().size() == 0) ? ((int)Guinness.CUSTOM_FONT.getStringWidth(this.getTitle()) + 4) : ((int)Guinness.CUSTOM_FONT.getStringWidth(MathUtil.getLongestString(this.getText())) + 6);
        final ScaledResolution sr = new ScaledResolution(Coordinates.mc);
        if (this.getX() + width / 2 <= sr.getScaledWidth() / 2) {
            RenderUtil.drawHorizontalRainbowText("XYZ ", this.getX() + 3, this.getY() + 16, true);
            Guinness.CUSTOM_FONT.drawStringWithShadow(this.getRegCoords(), (float)(this.getX() + 25), (float)(this.getY() + 16), -4473925);
            RenderUtil.drawHorizontalRainbowText(this.getDirect() + " ", this.getX() + 3, this.getY() + 26, true);
            Guinness.CUSTOM_FONT.drawString(this.getRotation(), this.getX() + Guinness.CUSTOM_FONT.getStringWidth(this.getDirect() + " ") + Float.intBitsToFloat(Float.floatToIntBits(1.0171849f) ^ 0x7F42331D), (float)(this.getY() + 24), -4473925);
        }
        else if (this.getX() + width / 2 > sr.getScaledWidth() / 2) {
            RenderUtil.drawHorizontalRainbowText("XYZ ", this.getX() + width - 23, this.getY() + 16, true);
            Guinness.CUSTOM_FONT.drawStringWithShadow(this.getRegCoords(), this.getX() + width - 25 - Guinness.CUSTOM_FONT.getStringWidth(this.getRegCoords()) - Float.intBitsToFloat(Float.floatToIntBits(1.9187037f) ^ 0x7F759815), (float)(this.getY() + 16), -4473925);
            RenderUtil.drawHorizontalRainbowText(this.getDirect() + " ", (int)(this.getX() + width - Guinness.CUSTOM_FONT.getStringWidth(this.getDirect())) - 5, this.getY() + 26, true);
            Guinness.CUSTOM_FONT.drawStringWithShadow(this.getRotation(), this.getX() + width - Guinness.CUSTOM_FONT.getStringWidth(this.getDirect() + " ") - Guinness.CUSTOM_FONT.getStringWidth(this.getRotation()) - Float.intBitsToFloat(Float.floatToIntBits(0.036675867f) ^ 0x7DD6396F), (float)(this.getY() + 26), -4473925);
        }
    }
    
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Text text) {
        if (Coordinates.mc.currentScreen instanceof GuinnessHudGui) {
            return;
        }
        if (!ModuleManager.getModule(Coordinates::lambda$onRender$1).isEnabled()) {
            return;
        }
        if (this.dockMode == null) {
            this.setDockingStatus();
        }
        final int width = (this.getText().size() == 0) ? ((int)Guinness.CUSTOM_FONT.getStringWidth(this.getTitle()) + 4) : ((int)Guinness.CUSTOM_FONT.getStringWidth(MathUtil.getLongestString(this.getText())) + 6);
        final ScaledResolution sr = new ScaledResolution(Coordinates.mc);
        switch (Coordinates$1.$SwitchMap$dev$guinness$client$gui$hud$HudTextFrame$Docking[this.dockMode.ordinal()]) {
            case 1: {
                RenderUtil.drawHorizontalRainbowText("XYZ ", 1, 0, true);
                Guinness.CUSTOM_FONT.drawStringWithShadow(this.getRegCoords(), Float.intBitsToFloat(Float.floatToIntBits(0.37944213f) ^ 0x7F72463D), Float.intBitsToFloat(Float.floatToIntBits(1.8928222E38f) ^ 0x7F0E666E), -4473925);
                RenderUtil.drawHorizontalRainbowText(this.getDirect() + " ", 1, 11, true);
                Guinness.CUSTOM_FONT.drawStringWithShadow(this.getRotation(), Float.intBitsToFloat(Float.floatToIntBits(7.3484683f) ^ 0x7F6B26A7) + Guinness.CUSTOM_FONT.getStringWidth(this.getDirect()) + Float.intBitsToFloat(Float.floatToIntBits(0.28019366f) ^ 0x7E4F758B), Float.intBitsToFloat(Float.floatToIntBits(0.010511323f) ^ 0x7D1C37AF), -4473925);
                break;
            }
            case 2: {
                RenderUtil.drawHorizontalRainbowText("XYZ ", 1, this.getY() + 16, true);
                Guinness.CUSTOM_FONT.drawStringWithShadow(this.getRegCoords(), Float.intBitsToFloat(Float.floatToIntBits(0.01217348f) ^ 0x7DF77347), (float)(this.getY() + 16), -4473925);
                RenderUtil.drawHorizontalRainbowText(this.getDirect() + " ", 1, this.getY() + 26, true);
                Guinness.CUSTOM_FONT.drawStringWithShadow(this.getRotation(), Float.intBitsToFloat(Float.floatToIntBits(5.439063f) ^ 0x7F2E0CCE) + Guinness.CUSTOM_FONT.getStringWidth(this.getDirect()) + Float.intBitsToFloat(Float.floatToIntBits(0.029479621f) ^ 0x7C317F3F), (float)(this.getY() + 26), -4473925);
                break;
            }
            case 3: {
                RenderUtil.drawHorizontalRainbowText("XYZ ", sr.getScaledWidth() - 22, 0, true);
                Guinness.CUSTOM_FONT.drawStringWithShadow(this.getRegCoords(), sr.getScaledWidth() - 25 - Guinness.CUSTOM_FONT.getStringWidth(this.getRegCoords()), Float.intBitsToFloat(Float.floatToIntBits(1.5628218E38f) ^ 0x7EEB25B5), -4473925);
                RenderUtil.drawHorizontalRainbowText(this.getDirect() + " ", (int)(sr.getScaledWidth() - Guinness.CUSTOM_FONT.getStringWidth(this.getDirect())) - 2, 11, true);
                Guinness.CUSTOM_FONT.drawStringWithShadow(this.getRotation(), sr.getScaledWidth() - Guinness.CUSTOM_FONT.getStringWidth(this.getDirect() + " ") - Guinness.CUSTOM_FONT.getStringWidth(this.getRotation()) - Float.intBitsToFloat(Float.floatToIntBits(0.05146524f) ^ 0x7DD2CD37), Float.intBitsToFloat(Float.floatToIntBits(0.012461617f) ^ 0x7D7C2BCF), -4473925);
                break;
            }
            case 4: {
                RenderUtil.drawHorizontalRainbowText("XYZ ", sr.getScaledWidth() - 22, this.getY() + 16, true);
                Guinness.CUSTOM_FONT.drawStringWithShadow(this.getRegCoords(), sr.getScaledWidth() - 25 - Guinness.CUSTOM_FONT.getStringWidth(this.getRegCoords()), (float)(this.getY() + 16), -4473925);
                RenderUtil.drawHorizontalRainbowText(this.getDirect() + " ", (int)(sr.getScaledWidth() - Guinness.CUSTOM_FONT.getStringWidth(this.getDirect())) - 5, this.getY() + 26, true);
                Guinness.CUSTOM_FONT.drawStringWithShadow(this.getRotation(), sr.getScaledWidth() - Guinness.CUSTOM_FONT.getStringWidth(this.getDirect() + " ") - Guinness.CUSTOM_FONT.getStringWidth(this.getRotation()) - Float.intBitsToFloat(Float.floatToIntBits(0.24392666f) ^ 0x7F79C7E9), (float)(this.getY() + 26), -4473925);
                break;
            }
            default: {
                if (this.getX() + width / 2 <= sr.getScaledWidth() / 2) {
                    RenderUtil.drawHorizontalRainbowText("XYZ ", this.getX() + 3, this.getY() + 16, true);
                    Guinness.CUSTOM_FONT.drawStringWithShadow(this.getRegCoords(), (float)(this.getX() + 25), (float)(this.getY() + 16), -4473925);
                    RenderUtil.drawHorizontalRainbowText(this.getDirect() + " ", this.getX() + 3, this.getY() + 26, true);
                    Guinness.CUSTOM_FONT.drawString(this.getRotation(), this.getX() + Guinness.CUSTOM_FONT.getStringWidth(this.getDirect() + " ") + Float.intBitsToFloat(Float.floatToIntBits(1.113195f) ^ 0x7F4E7D2C), (float)(this.getY() + 24), -4473925);
                    break;
                }
                if (this.getX() + width / 2 > sr.getScaledWidth() / 2) {
                    RenderUtil.drawHorizontalRainbowText("XYZ ", this.getX() + width - 22, this.getY() + 16, true);
                    Guinness.CUSTOM_FONT.drawStringWithShadow(this.getRegCoords(), this.getX() + width - 25 - Guinness.CUSTOM_FONT.getStringWidth(this.getRegCoords()), (float)(this.getY() + 16), -4473925);
                    RenderUtil.drawHorizontalRainbowText(this.getDirect() + " ", (int)(this.getX() + width - Guinness.CUSTOM_FONT.getStringWidth(this.getDirect())) - 5, this.getY() + 26, true);
                    Guinness.CUSTOM_FONT.drawStringWithShadow(this.getRotation(), this.getX() + width - Guinness.CUSTOM_FONT.getStringWidth(this.getDirect() + " ") - Guinness.CUSTOM_FONT.getStringWidth(this.getRotation()) - Float.intBitsToFloat(Float.floatToIntBits(0.49315378f) ^ 0x7E3C7EA7), (float)(this.getY() + 26), -4473925);
                    break;
                }
                break;
            }
        }
    }
    
    public static boolean lambda$onRender$1(final Module module) {
        return module.getName().equalsIgnoreCase("Coordinates");
    }
    
    public static List lambda$new$0() {
        return Coordinates.text;
    }
    
    static {
        Coordinates.text = new ArrayList<String>();
    }
}
