package dev.guinness.client.gui.hud;

import java.util.ArrayList;
import dev.guinness.client.module.Module;
import net.minecraft.client.gui.ScaledResolution;
import dev.guinness.client.util.RenderUtil;
import net.minecraft.client.gui.GuiScreen;
import dev.guinness.client.util.ColorUtil;
import dev.guinness.client.util.MathUtil;
import dev.guinness.client.Guinness;
import java.util.function.Predicate;
import dev.guinness.client.module.ModuleManager;
import java.util.function.Supplier;
import java.util.List;
import dev.guinness.client.gui.GenericGuiWindow;

public abstract class HudTextFrame extends GenericGuiWindow
{
    public String title;
    public static List<HudTextFrame> hudFrames;
    public Supplier<List<String>> text;
    public HudTextFrame$Docking dockMode;
    
    public HudTextFrame(final String title, final int x, final int y, final Supplier text) {
        super(title, x, y);
        this.title = title;
        this.text = (Supplier<List<String>>)text;
    }
    
    public abstract void drawText();
    
    @Override
    public void draw(final int n, final int n2) {
        this.updateMousePos();
        if (!ModuleManager.getModule(this::lambda$draw$0).isEnabled()) {
            return;
        }
        final int width = (this.text.get().size() == 0) ? ((int)Guinness.CUSTOM_FONT.getStringWidth(this.title) + 4) : ((int)Guinness.CUSTOM_FONT.getStringWidth(MathUtil.getLongestString(this.text.get())) + 6);
        final int height = (this.text.get().size() == 0) ? 14 : (19 + this.text.get().size() * 10);
        GuiScreen.func_73734_a(this.getX(), this.getY(), this.getX() + width, this.getY() + 1, ColorUtil.BESTCOLOR(0, 255));
        GuiScreen.func_73734_a(this.getX() + 2, this.getY() + 2, this.getX() + width - 2, this.getY() + 14, -1155390942);
        GuiScreen.func_73734_a(this.getX() + 2, this.getY() + 15, this.getX() + width - 2, this.getY() + height - 2, -1155390942);
        GuiScreen.func_73734_a(this.getX(), this.getY() + height - 1, this.getX() + width, this.getY() + height, ColorUtil.BESTCOLOR(this.text.get().size() + 1, 255));
        RenderUtil.drawVerticalGradientBox(this.getX(), this.getY() + 1, 1, height - 1, ColorUtil.BESTCOLOR(0, 255), ColorUtil.BESTCOLOR(this.text.get().size() + 1, 255));
        RenderUtil.drawVerticalGradientBox(this.getX() + width - 1, this.getY() + 1, 1, height - 1, ColorUtil.BESTCOLOR(0, 255), ColorUtil.BESTCOLOR(this.text.get().size() + 1, 255));
        Guinness.CUSTOM_FONT.drawStringWithShadow(this.title, this.getX() + width / 2 - Guinness.CUSTOM_FONT.getStringWidth(this.title) / Float.intBitsToFloat(Float.floatToIntBits(0.5631696f) ^ 0x7F102BE2), (float)(this.getY() + 2), -1);
        this.setWidthAndHeight(width, height);
        this.drawText();
        this.setDockingStatus();
    }
    
    @Override
    public String getTitle() {
        return this.title;
    }
    
    public void setDockingStatus() {
        final int width = (this.text.get().size() == 0) ? ((int)Guinness.CUSTOM_FONT.getStringWidth(this.title) + 4) : ((int)Guinness.CUSTOM_FONT.getStringWidth(MathUtil.getLongestString(this.text.get())) + 6);
        final ScaledResolution sr = new ScaledResolution(HudTextFrame.mc);
        if (this.getX() < 40) {
            if (this.getY() < 40) {
                this.dockMode = HudTextFrame$Docking.TOPRIGHT;
                return;
            }
            this.dockMode = HudTextFrame$Docking.RIGHT;
        }
        else if (this.getX() + width > sr.getScaledWidth() - 40) {
            if (this.getY() < 40) {
                this.dockMode = HudTextFrame$Docking.TOPLEFT;
                return;
            }
            this.dockMode = HudTextFrame$Docking.LEFT;
        }
        else {
            this.dockMode = HudTextFrame$Docking.NONE;
        }
    }
    
    public List getText() {
        return this.text.get();
    }
    
    public boolean lambda$draw$0(final Module module) {
        return module.getName().equalsIgnoreCase(this.title);
    }
    
    static {
        (HudTextFrame.hudFrames = new ArrayList<HudTextFrame>()).add(new Arraylist());
        HudTextFrame.hudFrames.add(new Coordinates());
        HudTextFrame.hudFrames.add(new PotionEffects());
    }
}
