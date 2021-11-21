package dev.guinness.client.gui.module;

import java.util.ArrayList;
import dev.guinness.client.module.Module;
import dev.guinness.client.Guinness;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.init.SoundEvents;
import dev.guinness.client.util.GuiUtil;
import java.util.function.Predicate;
import dev.guinness.client.module.ModuleManager;
import dev.guinness.client.gui.theme.Theme;
import dev.guinness.client.module.modules.client.ClickGui;
import java.util.List;
import dev.guinness.client.module.Category;
import dev.guinness.client.gui.GenericGuiWindow;

public class ModuleFrame extends GenericGuiWindow
{
    public boolean opened;
    public Category category;
    public static List<ModuleFrame> moduleFrames;
    
    public ModuleFrame(final String title, final int x, final int y, final Category category) {
        super(title, x, y);
        this.opened = true;
        this.category = category;
    }
    
    public static void init() {
        ModuleFrame.moduleFrames.add(new ModuleFrame("Client", 12, 52, Category.CLIENT));
        ModuleFrame.moduleFrames.add(new ModuleFrame("Combat", 135, 52, Category.COMBAT));
        ModuleFrame.moduleFrames.add(new ModuleFrame("Exploit", 258, 52, Category.EXPLOIT));
        ModuleFrame.moduleFrames.add(new ModuleFrame("Misc", 381, 52, Category.MISC));
        ModuleFrame.moduleFrames.add(new ModuleFrame("Movement", 504, 52, Category.MOVEMENT));
        ModuleFrame.moduleFrames.add(new ModuleFrame("Render", 627, 52, Category.RENDER));
        ModuleFrame.moduleFrames.add(new ModuleFrame("32k", 750, 52, Category.DISPENSERPVP));
        ModuleFrame.moduleFrames.add(new ModuleFrame("Hud", 250, 250, Category.HUD));
    }
    
    @Override
    public void draw(final int n, final int n2) {
        this.updateMousePos();
        final String currentTheme = (String)ClickGui.theme.getValue();
        final Theme current = Theme.getThemeByName(currentTheme);
        current.drawTitles(this.getX(), this.getY());
        if (this.opened) {
            current.drawModules(ModuleManager.getModules(this::lambda$draw$0), this.getX(), this.getY());
        }
        if (GuiUtil.mouseOver(this.getX(), this.getY(), this.getX() + current.getThemeWidth(), this.getY() + current.getThemeHeight()) && GuiUtil.rdown) {
            this.opened = !this.opened;
            if (ClickGui.clickSound.getValue()) {
                ModuleFrame.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getRecord(SoundEvents.UI_BUTTON_CLICK, Float.intBitsToFloat(Float.floatToIntBits(8.032281f) ^ 0x7E808439), Float.intBitsToFloat(Float.floatToIntBits(238.37262f) ^ 0x7EA293A9)));
            }
        }
        Guinness.LARGE_FONT.drawStringWithShadow(this.category.getName(), this.getX() + current.getThemeWidth() / 2 - Guinness.LARGE_FONT.getStringWidth(this.category.getName()) / Float.intBitsToFloat(Float.floatToIntBits(0.48033014f) ^ 0x7EF5EDD5), (float)(this.getY() + 2), -1);
        this.setWidthAndHeight(current.getThemeWidth(), current.getThemeHeight());
    }
    
    public Category getCategory() {
        return this.category;
    }
    
    public boolean lambda$draw$0(final Module module) {
        return module.getCategory().equals(this.category);
    }
    
    static {
        ModuleFrame.moduleFrames = new ArrayList<ModuleFrame>();
    }
}
