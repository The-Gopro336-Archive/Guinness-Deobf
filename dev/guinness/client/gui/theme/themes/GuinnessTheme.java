package dev.guinness.client.gui.theme.themes;

import dev.guinness.client.util.MathUtil;
import dev.guinness.client.setting.SMode;
import dev.guinness.client.setting.SSlider;
import dev.guinness.client.setting.SBoolean;
import dev.guinness.client.setting.Setting;
import java.util.Iterator;
import dev.guinness.client.util.RenderUtil;
import dev.guinness.client.Guinness;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.init.SoundEvents;
import dev.guinness.client.module.modules.client.ClickGui;
import dev.guinness.client.util.GuiUtil;
import dev.guinness.client.module.Module;
import java.util.List;
import net.minecraft.client.gui.GuiScreen;
import dev.guinness.client.util.ColorUtil;
import dev.guinness.client.util.Wrapper;
import dev.guinness.client.gui.theme.Theme;

public class GuinnessTheme extends Theme implements Wrapper
{
    public static int height;
    public static int width;
    public static int boost;
    public static String name;
    
    public GuinnessTheme() {
        super("Guinness", 120, 15);
    }
    
    @Override
    public void drawTitles(final int n, final int n2) {
        GuiScreen.func_73734_a(n, n2, n + 120, n2 + 15 - 1, ColorUtil.BESTCOLOR(0, 180));
        GuiScreen.func_73734_a(n, n2 + 15 - 1, n + 120, n2 + 15, -14606047);
    }
    
    @Override
    public void drawModules(final List list, final int n, final int n2) {
        GuinnessTheme.boost = 0;
        GuiScreen.func_73734_a(n - 1, n2 - 1, n + 120 + 1, n2, ColorUtil.BESTCOLOR(0, 255));
        for (final Module m : list) {
            int baseColor = -1441722095;
            int topColor = ColorUtil.BESTCOLOR(GuinnessTheme.boost, 190);
            int bottomColor = ColorUtil.BESTCOLOR(GuinnessTheme.boost + 1, 255);
            if (GuiUtil.mouseOver(n + 2, n2 + 15 + GuinnessTheme.boost * 15 + 1, n + 120 - 2, n2 + 30 + GuinnessTheme.boost * 15)) {
                if (GuiUtil.ldown) {
                    m.toggle();
                    if (ClickGui.clickSound.getValue()) {
                        GuinnessTheme.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getRecord(SoundEvents.UI_BUTTON_CLICK, Float.intBitsToFloat(Float.floatToIntBits(44.351254f) ^ 0x7DB167AF), Float.intBitsToFloat(Float.floatToIntBits(3.9281354f) ^ 0x7DB7AA5F)));
                    }
                }
                if (GuiUtil.rdown) {
                    m.setOpen(!m.isOpened());
                    if (ClickGui.clickSound.getValue()) {
                        GuinnessTheme.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getRecord(SoundEvents.UI_BUTTON_CLICK, Float.intBitsToFloat(Float.floatToIntBits(20.579374f) ^ 0x7E24A28F), Float.intBitsToFloat(Float.floatToIntBits(2.124867f) ^ 0x7DCB311F)));
                    }
                }
                baseColor = -1439485133;
                topColor = (ColorUtil.BESTCOLOR(GuinnessTheme.boost, 190) | 0x40000000);
                bottomColor = (ColorUtil.BESTCOLOR(GuinnessTheme.boost + 1, 190) | 0x40000000);
                final ScaledResolution sr = new ScaledResolution(GuinnessTheme.mc);
                Guinness.CUSTOM_FONT.drawStringWithShadow(m.getDescription(), sr.getScaledWidth() / 2 - Guinness.CUSTOM_FONT.getStringWidth(m.getDescription()) / Float.intBitsToFloat(Float.floatToIntBits(0.34469405f) ^ 0x7EB07BBD), Float.intBitsToFloat(Float.floatToIntBits(0.7930724f) ^ 0x7E3B06CB), -1);
            }
            GuiScreen.func_73734_a(n + 1, n2 + 15 + GuinnessTheme.boost * 15, n + 120 - 1, n2 + 30 + GuinnessTheme.boost * 15, 1140850688);
            GuiScreen.func_73734_a(n + 2, n2 + 15 + GuinnessTheme.boost * 15 + 1, n + 120 - 2, n2 + 30 + GuinnessTheme.boost * 15, baseColor);
            if (m.isEnabled()) {
                RenderUtil.drawVerticalGradientBox(n + 2, n2 + 15 + GuinnessTheme.boost * 15 + 1, 116, 14, topColor, bottomColor);
            }
            Guinness.CUSTOM_FONT.drawStringWithShadow(m.getName(), (float)(n + 5), (float)(n2 + 15 + 3 + GuinnessTheme.boost * 15), -1);
            if (m.getSettings().size() > 1) {
                Guinness.CUSTOM_FONT.drawString("...", (float)(n + 120 - 14), (float)(n2 + 15 - 2 + GuinnessTheme.boost * 15), -1, false);
            }
            if (m.isOpened()) {
                if (m.hasSettings()) {
                    drawDropdown(m, n, n2);
                }
                ++GuinnessTheme.boost;
                renderKeybind(m, GuiUtil.keydown, n, n2);
            }
            ++GuinnessTheme.boost;
        }
        RenderUtil.drawVerticalGradientBox(n - 1, n2, 1, GuinnessTheme.boost * 15 + 15 + 2, ColorUtil.BESTCOLOR(0, 255), ColorUtil.BESTCOLOR(GuinnessTheme.boost, 255));
        RenderUtil.drawVerticalGradientBox(n + 120, n2, 1, GuinnessTheme.boost * 15 + 15 + 2, ColorUtil.BESTCOLOR(0, 255), ColorUtil.BESTCOLOR(GuinnessTheme.boost, 255));
        GuiScreen.func_73734_a(n, n2 + 15, n + 1, n2 + GuinnessTheme.boost * 15 + 15 + 2, -14606047);
        GuiScreen.func_73734_a(n + 120 - 1, n2 + 15, n + 120, n2 + GuinnessTheme.boost * 15 + 15 + 2, -14606047);
        GuiScreen.func_73734_a(n - 1, n2 + 15 + GuinnessTheme.boost * 15 + 2, n + 120 + 1, n2 + 15 + GuinnessTheme.boost * 15 + 3, ColorUtil.BESTCOLOR(GuinnessTheme.boost, 255));
        GuiScreen.func_73734_a(n + 1, n2 + 15 + GuinnessTheme.boost * 15, n + 120 - 1, n2 + 15 + GuinnessTheme.boost * 15 + 1, 1140850688);
        GuiScreen.func_73734_a(n, n2 + 15 + GuinnessTheme.boost * 15 + 1, n + 120, n2 + 15 + GuinnessTheme.boost * 15 + 2, -14606047);
    }
    
    public static void drawDropdown(final Module module, final int n, final int n2) {
        for (final Setting<?> s : module.getSettings()) {
            if (!s.isVisible()) {
                continue;
            }
            ++GuinnessTheme.boost;
            if (s instanceof SBoolean) {
                final SBoolean b = (SBoolean)s;
                renderBoolean(b, n, n2);
            }
            if (s instanceof SSlider) {
                final SSlider sd = (SSlider)s;
                renderSlider(sd, n, n2);
            }
            if (!(s instanceof SMode)) {
                continue;
            }
            final SMode sm = (SMode)s;
            renderMode(sm, n, n2);
        }
    }
    
    public static void renderBoolean(final SBoolean sBoolean, final int n, final int n2) {
        int baseColor = -1441722095;
        int topColor = ColorUtil.BESTCOLOR(GuinnessTheme.boost, 190);
        int bottomColor = ColorUtil.BESTCOLOR(GuinnessTheme.boost + 1, 190);
        if (GuiUtil.mouseOver(n + 2, n2 + 15 + GuinnessTheme.boost * 15 + 1, n + 120 - 2, n2 + 15 + 15 + GuinnessTheme.boost * 15)) {
            if (GuiUtil.ldown) {
                sBoolean.setValue(!(boolean)sBoolean.getValue());
                if (ClickGui.clickSound.getValue()) {
                    GuinnessTheme.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getRecord(SoundEvents.UI_BUTTON_CLICK, Float.intBitsToFloat(Float.floatToIntBits(4.52434f) ^ 0x7F10C765), Float.intBitsToFloat(Float.floatToIntBits(246.98502f) ^ 0x7EBA30E7)));
                }
            }
            if (GuiUtil.rdown) {
                sBoolean.setOpened(!sBoolean.isOpened());
            }
            baseColor = -1439485133;
            topColor = (ColorUtil.BESTCOLOR(GuinnessTheme.boost, 190) | 0x40000000);
            bottomColor = (ColorUtil.BESTCOLOR(GuinnessTheme.boost + 1, 190) | 0x40000000);
        }
        GuiScreen.func_73734_a(n + 1, n2 + 15 + GuinnessTheme.boost * 15, n + 120 - 1, n2 + 30 + GuinnessTheme.boost * 15, 1140850688);
        RenderUtil.drawVerticalGradientBox(n + 2, n2 + 15 + GuinnessTheme.boost * 15, 1, 15, topColor, bottomColor);
        GuiScreen.func_73734_a(n + 4, n2 + 15 + GuinnessTheme.boost * 15 + 1, n + 120 - 2, n2 + 15 + 15 + GuinnessTheme.boost * 15, baseColor);
        if (sBoolean.getValue()) {
            RenderUtil.drawVerticalGradientBox(n + 4, n2 + 15 + GuinnessTheme.boost * 15 + 1, 114, 14, topColor, bottomColor);
        }
        Guinness.SMALL_FONT.drawStringWithShadow(sBoolean.getName(), (float)(n + 8), (float)(n2 + 15 + 4 + GuinnessTheme.boost * 15), -1);
    }
    
    public static void renderMode(final SMode sMode, final int n, final int n2) {
        int color = -1441722095;
        int topColor = ColorUtil.BESTCOLOR(GuinnessTheme.boost, 190);
        int bottomColor = ColorUtil.BESTCOLOR(GuinnessTheme.boost + 1, 190);
        if (GuiUtil.mouseOver(n + 2, n2 + 15 + GuinnessTheme.boost * 15 + 1, n + 120 - 2, n2 + 15 + 15 + GuinnessTheme.boost * 15)) {
            if (GuiUtil.ldown) {
                sMode.setMode(sMode.getNextMode());
                if (ClickGui.clickSound.getValue()) {
                    GuinnessTheme.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getRecord(SoundEvents.UI_BUTTON_CLICK, Float.intBitsToFloat(Float.floatToIntBits(8.367415f) ^ 0x7E85E0EF), Float.intBitsToFloat(Float.floatToIntBits(151.672f) ^ 0x7EDB60C5)));
                }
            }
            if (GuiUtil.rdown) {
                sMode.setOpened(sMode.isOpened());
            }
            color = -1439485133;
            topColor = (ColorUtil.BESTCOLOR(GuinnessTheme.boost, 190) | 0x40000000);
            bottomColor = (ColorUtil.BESTCOLOR(GuinnessTheme.boost + 1, 190) | 0x40000000);
        }
        GuiScreen.func_73734_a(n + 1, n2 + 15 + GuinnessTheme.boost * 15, n + 120 - 1, n2 + 30 + GuinnessTheme.boost * 15, 1140850688);
        RenderUtil.drawVerticalGradientBox(n + 2, n2 + 15 + GuinnessTheme.boost * 15, 1, 15, topColor, bottomColor);
        GuiScreen.func_73734_a(n, n2 + 15 + GuinnessTheme.boost * 15, n + 1, n2 + 15 + 15 + GuinnessTheme.boost * 15, -14606047);
        GuiScreen.func_73734_a(n + 4, n2 + 15 + GuinnessTheme.boost * 15 + 1, n + 120 - 2, n2 + 15 + 15 + GuinnessTheme.boost * 15, color);
        Guinness.SMALL_FONT.drawStringWithShadow(sMode.getName(), (float)(n + 8), (float)(n2 + 15 + 4 + GuinnessTheme.boost * 15), -1);
        Guinness.SMALL_FONT.drawStringWithShadow((String)sMode.getValue(), n + 120 - Guinness.SMALL_FONT.getStringWidth((String)sMode.getValue()) - Float.intBitsToFloat(Float.floatToIntBits(1.7150394f) ^ 0x7F1B8669), (float)(n2 + 15 + 4 + GuinnessTheme.boost * 15), -9013642);
    }
    
    public static void renderSlider(final SSlider sSlider, final int n, final int n2) {
        int color = -1441722095;
        int topColor = ColorUtil.BESTCOLOR(GuinnessTheme.boost, 190);
        int bottomColor = ColorUtil.BESTCOLOR(GuinnessTheme.boost + 1, 190);
        int pixAdd = (int)((n - 3 + 120 - (n + 3)) * ((double)sSlider.getValue() - (double)sSlider.getMinValue()) / ((double)sSlider.getMaxValue() - (double)sSlider.getMinValue())) + 5;
        if (GuiUtil.mouseOver(n + 2, n2 + 15 + GuinnessTheme.boost * 15 + 1, n + 120 - 2, n2 + 15 + 15 + GuinnessTheme.boost * 15)) {
            if (GuiUtil.lheld) {
                final int percentError = (GuiUtil.mouseX - (n + 3)) * 100 / (n - 3 + 120 - n - 3);
                sSlider.setValue(MathUtil.roundDouble(percentError * (((double)sSlider.getMaxValue() - (double)sSlider.getMinValue()) / Double.longBitsToDouble(Double.doubleToLongBits(0.014228622543799805) ^ 0x7FD423E563F1261FL)) + (double)sSlider.getMinValue(), sSlider.getRoundingScale()));
            }
            if (GuiUtil.ldown) {
                if (ClickGui.clickSound.getValue()) {
                    GuinnessTheme.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getRecord(SoundEvents.UI_BUTTON_CLICK, Float.intBitsToFloat(Float.floatToIntBits(4.1897078f) ^ 0x7F061216), Float.intBitsToFloat(Float.floatToIntBits(103.00921f) ^ 0x7F02C87A)));
                }
            }
            color = -1439485133;
            topColor = (ColorUtil.BESTCOLOR(GuinnessTheme.boost, 190) | 0x40000000);
            bottomColor = (ColorUtil.BESTCOLOR(GuinnessTheme.boost + 1, 190) | 0x40000000);
        }
        if (pixAdd < 6) {
            pixAdd = 5;
        }
        if (pixAdd > n + 120 - n - 3) {
            pixAdd = 117;
        }
        GuiScreen.func_73734_a(n + 1, n2 + 15 + GuinnessTheme.boost * 15, n + 120 - 1, n2 + 30 + GuinnessTheme.boost * 15, 1140850688);
        RenderUtil.drawVerticalGradientBox(n + 2, n2 + 15 + GuinnessTheme.boost * 15, 1, 15, topColor, bottomColor);
        GuiScreen.func_73734_a(n + 4, n2 + 15 + GuinnessTheme.boost * 15 + 1, n + 120 - 2, n2 + 15 + 15 + GuinnessTheme.boost * 15, color);
        RenderUtil.drawVerticalGradientBox(n + 5, n2 + 15 + GuinnessTheme.boost * 15 + 2, pixAdd - 5, 12, topColor, bottomColor);
        Guinness.SMALL_FONT.drawStringWithShadow(sSlider.getName(), (float)(n + 8), (float)(n2 + 15 + 3 + GuinnessTheme.boost * 15), -1);
        Guinness.SMALL_FONT.drawStringWithShadow(String.valueOf(sSlider.getValue()), n + 120 - Guinness.SMALL_FONT.getStringWidth(((Double)sSlider.getValue()).toString()) - Float.intBitsToFloat(Float.floatToIntBits(0.30223462f) ^ 0x7E5ABE7F), (float)(n2 + 15 + 3 + GuinnessTheme.boost * 15), -9013642);
    }
    
    public static void renderKeybind(final Module module, final int n, final int n2, final int n3) {
        int color = -1441722095;
        int topColor = ColorUtil.BESTCOLOR(GuinnessTheme.boost, 190);
        int bottomColor = ColorUtil.BESTCOLOR(GuinnessTheme.boost + 1, 190);
        if (GuiUtil.mouseOver(n2 + 2, n3 + 15 + GuinnessTheme.boost * 15 + 1, n2 + 120 - 2, n3 + 15 + 15 + GuinnessTheme.boost * 15)) {
            if (GuiUtil.ldown) {
                module.setBinding(true);
                if (ClickGui.clickSound.getValue()) {
                    GuinnessTheme.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getRecord(SoundEvents.UI_BUTTON_CLICK, Float.intBitsToFloat(Float.floatToIntBits(6.8811493f) ^ 0x7F5C3260), Float.intBitsToFloat(Float.floatToIntBits(107.76578f) ^ 0x7F1B44D9)));
                }
            }
            color = -1439485133;
            topColor = (ColorUtil.BESTCOLOR(GuinnessTheme.boost, 190) | 0x40000000);
            bottomColor = (ColorUtil.BESTCOLOR(GuinnessTheme.boost + 1, 190) | 0x40000000);
        }
        if (module.isBinding() && n != -1 && n != 1 && n != 211) {
            module.getKeybind().setKeyCode((n == 211 || n == 14) ? 0 : n);
            module.setBinding(false);
        }
        if (module.isBinding()) {
            if (n == 1) {
                module.setBinding(false);
            }
        }
        GuiScreen.func_73734_a(n2 + 1, n3 + 15 + GuinnessTheme.boost * 15, n2 + 120 - 1, n3 + 30 + GuinnessTheme.boost * 15, 1140850688);
        RenderUtil.drawVerticalGradientBox(n2 + 2, n3 + 15 + GuinnessTheme.boost * 15, 1, 15, topColor, bottomColor);
        GuiScreen.func_73734_a(n2, n3 + 15 + GuinnessTheme.boost * 15, n2 + 1, n3 + 15 + 15 + GuinnessTheme.boost * 15, -14606047);
        GuiScreen.func_73734_a(n2 + 4, n3 + 15 + GuinnessTheme.boost * 15 + 1, n2 + 120 - 2, n3 + 15 + 15 + GuinnessTheme.boost * 15, color);
        if (!module.isBinding()) {
            Guinness.SMALL_FONT.drawStringWithShadow("Keybind", (float)(n2 + 8), (float)(n3 + 15 + 4 + GuinnessTheme.boost * 15), -1);
            Guinness.SMALL_FONT.drawStringWithShadow(module.getKeybind().getDisplayName().equalsIgnoreCase("NONE") ? "None" : module.getKeybind().getDisplayName(), n2 + 120 - Guinness.SMALL_FONT.getStringWidth(module.getKeybind().getDisplayName()) - Float.intBitsToFloat(Float.floatToIntBits(1.5061003f) ^ 0x7F40C7E5), (float)(n3 + 15 + 4 + GuinnessTheme.boost * 15), -9013642);
        }
        else {
            Guinness.SMALL_FONT.drawStringWithShadow("Listening...", (float)(n2 + 4), (float)(n3 + 15 + 4 + GuinnessTheme.boost * 15), -9013642);
        }
    }
    
    static {
        GuinnessTheme.name = "Guinness";
        GuinnessTheme.height = 15;
        GuinnessTheme.width = 120;
        GuinnessTheme.boost = 0;
    }
}
