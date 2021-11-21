package dev.guinness.client.gui;

import dev.guinness.client.module.Category;
import dev.guinness.client.util.FileUtil;
import java.util.function.Consumer;
import dev.guinness.client.util.GuiUtil;
import java.util.function.Predicate;
import dev.guinness.client.gui.module.ModuleFrame;
import dev.guinness.client.Guinness;
import dev.guinness.client.util.RenderUtil;
import dev.guinness.client.util.ColorUtil;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.GuiScreen;

public class GuinnessGui extends GuiScreen
{
    public void drawScreen(final int n, final int n2, final float n3) {
        super.drawScreen(n, n2, n3);
        this.drawDefaultBackground();
        final ScaledResolution sr = new ScaledResolution(this.mc);
        GuiScreen.func_73734_a(0, 0, sr.getScaledWidth(), 40, -1441656302);
        RenderUtil.drawHorizontalGradientBox(0, 0, sr.getScaledWidth(), 1, ColorUtil.rainbow((long)(-336514152) ^ 0xFFFFFFFFEBF13398L, 255), ColorUtil.rainbow((long)(-1137353742) ^ 0xFFFFFFFFBC355BFDL, 255));
        Guinness.HUGE_FONT.drawStringWithShadow(Guinness.SPOOFNAME + " v" + "0.4.2", Float.intBitsToFloat(Float.floatToIntBits(0.8772063f) ^ 0x7F209098), Float.intBitsToFloat(Float.floatToIntBits(0.73892367f) ^ 0x7F7D2A1A), -1);
        ModuleFrame.moduleFrames.stream().filter((Predicate<? super Object>)GuinnessGui::lambda$drawScreen$0).forEach(GuinnessGui::lambda$drawScreen$1);
        GuiUtil.updateMousePos(n, n2);
    }
    
    public void mouseClicked(final int n, final int n2, final int n3) {
        super.mouseClicked(n, n2, n3);
        if (n3 == 0) {
            ModuleFrame.moduleFrames.stream().filter((Predicate<? super Object>)GuinnessGui::lambda$mouseClicked$2).filter((Predicate<? super Object>)GuinnessGui::lambda$mouseClicked$3).reduce(GuinnessGui::lambda$mouseClicked$4).ifPresent((Consumer<? super Object>)GuinnessGui::lambda$mouseClicked$5);
            GuiUtil.updateLeftClick();
        }
        else if (n3 == 1) {
            GuiUtil.updateRightClick();
        }
    }
    
    public void mouseReleased(final int n, final int n2, final int n3) {
        super.mouseReleased(n, n2, n3);
        if (n3 == 0) {
            ModuleFrame.moduleFrames.stream().filter((Predicate<? super Object>)GuinnessGui::lambda$mouseReleased$6).forEach(GenericGuiWindow::onLeftMouseRelease);
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
    
    public static boolean lambda$mouseReleased$6(final ModuleFrame moduleFrame) {
        return !moduleFrame.getCategory().equals(Category.HUD);
    }
    
    public static void lambda$mouseClicked$5(final ModuleFrame moduleFrame) {
        moduleFrame.onLeftClick();
    }
    
    public static ModuleFrame lambda$mouseClicked$4(final ModuleFrame moduleFrame, final ModuleFrame moduleFrame2) {
        return moduleFrame2;
    }
    
    public static boolean lambda$mouseClicked$3(final ModuleFrame moduleFrame) {
        return GuiUtil.mouseOver(moduleFrame.getX(), moduleFrame.getY(), moduleFrame.getX() + moduleFrame.getWidth(), moduleFrame.getY() + moduleFrame.getHeight());
    }
    
    public static boolean lambda$mouseClicked$2(final ModuleFrame moduleFrame) {
        return !moduleFrame.getCategory().equals(Category.HUD);
    }
    
    public static void lambda$drawScreen$1(final int n, final int n2, final ModuleFrame moduleFrame) {
        moduleFrame.draw(n, n2);
    }
    
    public static boolean lambda$drawScreen$0(final ModuleFrame moduleFrame) {
        return !moduleFrame.getCategory().equals(Category.HUD);
    }
}
