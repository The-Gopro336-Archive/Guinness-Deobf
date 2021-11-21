package dev.guinness.client.gui.hud;

import net.minecraft.client.resources.I18n;
import net.minecraft.potion.PotionEffect;
import dev.guinness.client.module.Module;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.function.Predicate;
import dev.guinness.client.module.ModuleManager;
import dev.guinness.client.gui.GuinnessHudGui;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraft.client.gui.ScaledResolution;
import dev.guinness.client.util.MathUtil;
import dev.guinness.client.Guinness;
import java.util.function.Consumer;
import java.text.DecimalFormat;
import net.minecraftforge.common.MinecraftForge;
import java.util.ArrayList;
import java.util.function.Supplier;
import java.util.List;

public class PotionEffects extends HudTextFrame
{
    public List<String> potionDurations;
    public List<Integer> potionColors;
    public static List<String> test;
    public List<String> activePotions;
    
    public PotionEffects() {
        super("PotionEffects", 40, 60, PotionEffects::lambda$new$0);
        this.activePotions = new ArrayList<String>();
        this.potionColors = new ArrayList<Integer>();
        this.potionDurations = new ArrayList<String>();
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    public void getPotionEffects() {
        this.activePotions.clear();
        this.potionColors.clear();
        this.potionDurations.clear();
        final DecimalFormat formatter = new DecimalFormat("00");
        final DecimalFormat formatter2 = new DecimalFormat("0");
        PotionEffects.mc.player.func_70651_bq().forEach(this::lambda$getPotionEffects$1);
    }
    
    @Override
    public void drawText() {
        PotionEffects.test.clear();
        this.getPotionEffects();
        for (int i = 0; i < this.activePotions.size(); ++i) {
            PotionEffects.test.add(this.activePotions.get(i) + this.potionDurations.get(i) + 5);
        }
        final int width = (this.getText().size() == 0) ? ((int)Guinness.CUSTOM_FONT.getStringWidth(this.getTitle()) + 4) : ((int)Guinness.CUSTOM_FONT.getStringWidth(MathUtil.getLongestString(this.getText())) + 6);
        int boost = 0;
        final ScaledResolution sr = new ScaledResolution(PotionEffects.mc);
        if (this.getX() + width / 2 <= sr.getScaledWidth() / 2) {
            for (int j = 0; j < this.activePotions.size(); ++j) {
                Guinness.CUSTOM_FONT.drawStringWithShadow(this.activePotions.get(j), (float)(this.getX() + 3), (float)(this.getY() + 16 + boost * 10), this.potionColors.get(j));
                Guinness.CUSTOM_FONT.drawStringWithShadow(this.potionDurations.get(j), this.getX() + Guinness.CUSTOM_FONT.getStringWidth(this.activePotions.get(j)) + Float.intBitsToFloat(Float.floatToIntBits(1.2632611f) ^ 0x7F61B28A), (float)(this.getY() + 16 + boost * 10), -4473925);
                ++boost;
            }
        }
        else if (this.getX() + width / 2 > sr.getScaledWidth() / 2) {
            for (int j = 0; j < this.activePotions.size(); ++j) {
                Guinness.CUSTOM_FONT.drawStringWithShadow(this.activePotions.get(j), this.getX() + width - Guinness.CUSTOM_FONT.getStringWidth(this.activePotions.get(j) + this.potionDurations.get(j)) - Float.intBitsToFloat(Float.floatToIntBits(0.47855112f) ^ 0x7E1504A7), (float)(this.getY() + 16 + boost * 10), this.potionColors.get(j));
                Guinness.CUSTOM_FONT.drawStringWithShadow(this.potionDurations.get(j), this.getX() + width - Guinness.CUSTOM_FONT.getStringWidth(this.potionDurations.get(j)) - Float.intBitsToFloat(Float.floatToIntBits(0.17126893f) ^ 0x7EAF611F), (float)(this.getY() + 16 + boost * 10), -4473925);
                ++boost;
            }
        }
    }
    
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Text text) {
        if (PotionEffects.mc.currentScreen instanceof GuinnessHudGui) {
            return;
        }
        if (!ModuleManager.getModule(PotionEffects::lambda$onRender$2).isEnabled()) {
            return;
        }
        this.getPotionEffects();
        this.updateMousePos();
        PotionEffects.test.clear();
        for (int i = 0; i < this.activePotions.size(); ++i) {
            PotionEffects.test.add(this.activePotions.get(i) + this.potionDurations.get(i) + 5);
        }
        if (PotionEffects.test.size() == 0) {
            return;
        }
        final int width = (this.getText().size() == 0) ? ((int)Guinness.CUSTOM_FONT.getStringWidth(this.getTitle()) + 4) : ((int)Guinness.CUSTOM_FONT.getStringWidth(MathUtil.getLongestString(this.getText())) + 6);
        int boost = 0;
        final ScaledResolution sr = new ScaledResolution(PotionEffects.mc);
        if (this.dockMode == null) {
            this.setDockingStatus();
        }
        switch (PotionEffects$1.$SwitchMap$dev$guinness$client$gui$hud$HudTextFrame$Docking[this.dockMode.ordinal()]) {
            case 1: {
                for (int j = 0; j < this.activePotions.size(); ++j) {
                    Guinness.CUSTOM_FONT.drawStringWithShadow(this.activePotions.get(j), Float.intBitsToFloat(Float.floatToIntBits(12.200828f) ^ 0x7EC33697), (float)(boost * 10), this.potionColors.get(j));
                    Guinness.CUSTOM_FONT.drawStringWithShadow(this.potionDurations.get(j), Float.intBitsToFloat(Float.floatToIntBits(4.789249f) ^ 0x7F194187) + Guinness.CUSTOM_FONT.getStringWidth(this.activePotions.get(j)) + Float.intBitsToFloat(Float.floatToIntBits(1.9296551f) ^ 0x7F76FEF0), (float)(boost * 10), -4473925);
                    ++boost;
                }
                break;
            }
            case 2: {
                for (int j = 0; j < this.activePotions.size(); ++j) {
                    Guinness.CUSTOM_FONT.drawStringWithShadow(this.activePotions.get(j), Float.intBitsToFloat(Float.floatToIntBits(9.342185f) ^ 0x7E957997), (float)(this.getY() + 16 + boost * 10), this.potionColors.get(j));
                    Guinness.CUSTOM_FONT.drawStringWithShadow(this.potionDurations.get(j), Float.intBitsToFloat(Float.floatToIntBits(6.864842f) ^ 0x7F5BACC9) + Guinness.CUSTOM_FONT.getStringWidth(this.activePotions.get(j)) + Float.intBitsToFloat(Float.floatToIntBits(1.2739849f) ^ 0x7F2311F0), (float)(this.getY() + 16 + boost * 10), -4473925);
                    ++boost;
                }
                break;
            }
            case 3: {
                for (int j = 0; j < this.activePotions.size(); ++j) {
                    Guinness.CUSTOM_FONT.drawStringWithShadow(this.activePotions.get(j), sr.getScaledWidth() - Guinness.CUSTOM_FONT.getStringWidth(this.activePotions.get(j) + this.potionDurations.get(j)) - Float.intBitsToFloat(Float.floatToIntBits(1.9187171f) ^ 0x7F159886), (float)(boost * 10), this.potionColors.get(j));
                    Guinness.CUSTOM_FONT.drawStringWithShadow(this.potionDurations.get(j), sr.getScaledWidth() - Guinness.CUSTOM_FONT.getStringWidth(this.potionDurations.get(j)) - Float.intBitsToFloat(Float.floatToIntBits(1.1069635f) ^ 0x7F0DB0FB), (float)(boost * 10), -4473925);
                    ++boost;
                }
                break;
            }
            case 4: {
                for (int j = 0; j < this.activePotions.size(); ++j) {
                    Guinness.CUSTOM_FONT.drawStringWithShadow(this.activePotions.get(j), sr.getScaledWidth() - Guinness.CUSTOM_FONT.getStringWidth(this.activePotions.get(j) + this.potionDurations.get(j)) - Float.intBitsToFloat(Float.floatToIntBits(0.4849058f) ^ 0x7E184593), (float)(this.getY() + 16 + boost * 10), this.potionColors.get(j));
                    Guinness.CUSTOM_FONT.drawStringWithShadow(this.potionDurations.get(j), sr.getScaledWidth() - Guinness.CUSTOM_FONT.getStringWidth(this.potionDurations.get(j)) - Float.intBitsToFloat(Float.floatToIntBits(0.015592306f) ^ 0x7CFF76DF), (float)(this.getY() + 16 + boost * 10), -4473925);
                    ++boost;
                }
                break;
            }
            default: {
                if (this.getX() + width / 2 <= sr.getScaledWidth() / 2) {
                    for (int j = 0; j < this.activePotions.size(); ++j) {
                        Guinness.CUSTOM_FONT.drawStringWithShadow(this.activePotions.get(j), (float)(this.getX() + 3), (float)(this.getY() + 16 + boost * 10), this.potionColors.get(j));
                        Guinness.CUSTOM_FONT.drawStringWithShadow(this.potionDurations.get(j), this.getX() + Guinness.CUSTOM_FONT.getStringWidth(this.activePotions.get(j)) + Float.intBitsToFloat(Float.floatToIntBits(1.7642967f) ^ 0x7F21D479), (float)(this.getY() + 16 + boost * 10), -4473925);
                        ++boost;
                    }
                    break;
                }
                if (this.getX() + width / 2 > sr.getScaledWidth() / 2) {
                    for (int j = 0; j < this.activePotions.size(); ++j) {
                        Guinness.CUSTOM_FONT.drawStringWithShadow(this.activePotions.get(j), this.getX() + width - Guinness.CUSTOM_FONT.getStringWidth(this.activePotions.get(j) + this.potionDurations.get(j)) - Float.intBitsToFloat(Float.floatToIntBits(1.855877f) ^ 0x7F0D8D61), (float)(this.getY() + 16 + boost * 10), this.potionColors.get(j));
                        Guinness.CUSTOM_FONT.drawStringWithShadow(this.potionDurations.get(j), this.getX() + width - Guinness.CUSTOM_FONT.getStringWidth(this.potionDurations.get(j)) - Float.intBitsToFloat(Float.floatToIntBits(1.624269f) ^ 0x7F4FE80C), (float)(this.getY() + 16 + boost * 10), -4473925);
                        ++boost;
                    }
                    break;
                }
                break;
            }
        }
    }
    
    public static boolean lambda$onRender$2(final Module module) {
        return module.getName().equalsIgnoreCase("PotionEffects");
    }
    
    public void lambda$getPotionEffects$1(final DecimalFormat decimalFormat, final DecimalFormat decimalFormat2, final PotionEffect potionEffect) {
        final double duration = potionEffect.getDuration() / 20;
        final double seconds = duration % Double.longBitsToDouble(Double.doubleToLongBits(0.9363133125485702) ^ 0x7FA3F647560693BFL);
        final double minutes = duration / Double.longBitsToDouble(Double.doubleToLongBits(0.05028029775981975) ^ 0x7FE7BE56D505EC3AL);
        this.activePotions.add(I18n.format(potionEffect.getPotion().getName(), new Object[0]) + " " + Integer.toString(potionEffect.getAmplifier() + 1));
        this.potionDurations.add(decimalFormat.format(minutes) + ":" + decimalFormat2.format(seconds));
        this.potionColors.add(potionEffect.getPotion().getLiquidColor());
    }
    
    public static List lambda$new$0() {
        return PotionEffects.test;
    }
    
    static {
        PotionEffects.test = new ArrayList<String>();
    }
}
