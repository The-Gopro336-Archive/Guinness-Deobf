package dev.guinness.client.gui;

import dev.guinness.client.module.Category;
import dev.guinness.client.util.FileUtil;
import java.util.function.Consumer;
import dev.guinness.client.util.ColorUtil;
import dev.guinness.client.Guinness;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.init.SoundEvents;
import dev.guinness.client.module.modules.client.ClickGui;
import dev.guinness.client.util.GuiUtil;
import java.util.function.Predicate;
import dev.guinness.client.gui.module.ModuleFrame;
import dev.guinness.client.gui.hud.HudTextFrame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.GuiScreen;

public class GuinnessHudGui extends GuiScreen
{
    public void drawScreen(final int n, final int n2, final float n3) {
        super.drawScreen(n, n2, n3);
        this.drawDefaultBackground();
        final ScaledResolution sr = new ScaledResolution(this.mc);
        GuiScreen.func_73734_a(0, 0, 40, sr.getScaledHeight(), -1441656302);
        GuiScreen.func_73734_a(sr.getScaledWidth() - 40, 0, sr.getScaledWidth(), sr.getScaledHeight(), -1441656302);
        HudTextFrame.hudFrames.forEach(GuinnessHudGui::lambda$drawScreen$0);
        ModuleFrame.moduleFrames.stream().filter((Predicate<? super Object>)GuinnessHudGui::lambda$drawScreen$1).findFirst().ifPresent(GuinnessHudGui::lambda$drawScreen$2);
        final int x = sr.getScaledWidth() - 45 - 80;
        final int y = sr.getScaledHeight() - 25;
        final int width = sr.getScaledWidth() - 45;
        final int height = sr.getScaledHeight() - 5;
        int color = -1155390942;
        if (GuiUtil.mouseOver(x, y, width, height)) {
            color = -1153153980;
            if (GuiUtil.ldown) {
                if (ClickGui.clickSound.getValue()) {
                    this.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getRecord(SoundEvents.UI_BUTTON_CLICK, Float.intBitsToFloat(Float.floatToIntBits(13.439233f) ^ 0x7ED70719), Float.intBitsToFloat(Float.floatToIntBits(489.42584f) ^ 0x7E387A4F)));
                }
                this.mc.displayGuiScreen((GuiScreen)Guinness.guinnessClickGui);
            }
        }
        GuiScreen.func_73734_a(x, y, width, height, color);
        Guinness.LARGE_FONT.drawStringWithShadow("Back", x + 40 - Guinness.LARGE_FONT.getStringWidth("Back") / Float.intBitsToFloat(Float.floatToIntBits(0.41466996f) ^ 0x7ED44F9F), (float)(y + 4), -4473925);
        GuiScreen.func_73734_a(x - 1, y - 2, width + 1, y - 1, ColorUtil.BESTCOLOR(0, 255));
        GuiScreen.func_73734_a(x - 1, height + 1, width + 1, height + 2, ColorUtil.BESTCOLOR(0, 255));
        GuiScreen.func_73734_a(x - 2, y - 2, x - 1, height + 2, ColorUtil.BESTCOLOR(0, 255));
        GuiScreen.func_73734_a(width + 1, y - 2, width + 2, height + 2, ColorUtil.BESTCOLOR(0, 255));
        GuiUtil.updateMousePos(n, n2);
    }
    
    public void mouseClicked(final int n, final int n2, final int n3) {
        super.mouseClicked(n, n2, n3);
        if (n3 == 0) {
            ModuleFrame.moduleFrames.stream().filter((Predicate<? super Object>)GuinnessHudGui::lambda$mouseClicked$3).findFirst().ifPresent((Consumer<? super Object>)GuinnessHudGui::lambda$mouseClicked$4);
            HudTextFrame.hudFrames.forEach(GenericGuiWindow::onLeftClick);
            GuiUtil.updateLeftClick();
        }
        else if (n3 == 1) {
            GuiUtil.updateRightClick();
        }
    }
    
    public void mouseReleased(final int n, final int n2, final int n3) {
        super.mouseReleased(n, n2, n3);
        if (n3 == 0) {
            ModuleFrame.moduleFrames.stream().filter((Predicate<? super Object>)GuinnessHudGui::lambda$mouseReleased$5).findFirst().ifPresent((Consumer<? super Object>)GuinnessHudGui::lambda$mouseReleased$6);
            HudTextFrame.hudFrames.forEach(GenericGuiWindow::onLeftMouseRelease);
            GuiUtil.updateMouseState();
        }
    }
    
    public void keyTyped(final char c, final int n) {
        super.keyTyped(c, n);
        GuiUtil.updateKeyState(n);
    }
    
    public void onGuiClosed() {
        super.onGuiClosed();
        FileUtil.saveAll();
    }
    
    public boolean doesGuiPauseGame() {
        return false;
    }
    
    public static void lambda$mouseReleased$6(final ModuleFrame moduleFrame) {
        moduleFrame.onLeftMouseRelease();
    }
    
    public static boolean lambda$mouseReleased$5(final ModuleFrame moduleFrame) {
        return moduleFrame.getCategory().equals(Category.HUD);
    }
    
    public static void lambda$mouseClicked$4(final ModuleFrame moduleFrame) {
        moduleFrame.onLeftClick();
    }
    
    public static boolean lambda$mouseClicked$3(final ModuleFrame moduleFrame) {
        return moduleFrame.getCategory().equals(Category.HUD);
    }
    
    public static void lambda$drawScreen$2(final int n, final int n2, final ModuleFrame moduleFrame) {
        moduleFrame.draw(n, n2);
    }
    
    public static boolean lambda$drawScreen$1(final ModuleFrame moduleFrame) {
        return moduleFrame.getCategory().equals(Category.HUD);
    }
    
    public static void lambda$drawScreen$0(final int n, final int n2, final HudTextFrame hudTextFrame) {
        hudTextFrame.draw(n, n2);
    }
}
