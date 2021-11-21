package dev.guinness.client.gui.hud;

import java.util.ArrayList;
import dev.guinness.client.module.Category;
import dev.guinness.client.setting.SBoolean;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import dev.guinness.client.util.RenderUtil;
import net.minecraft.client.gui.GuiScreen;
import dev.guinness.client.gui.GuinnessHudGui;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import dev.guinness.client.util.ColorUtil;
import net.minecraft.client.gui.ScaledResolution;
import dev.guinness.client.util.MathUtil;
import dev.guinness.client.Guinness;
import java.util.Iterator;
import java.util.Comparator;
import dev.guinness.client.module.Module;
import java.util.function.Predicate;
import dev.guinness.client.module.ModuleManager;
import net.minecraftforge.common.MinecraftForge;
import java.util.function.Supplier;
import java.util.List;

public class Arraylist extends HudTextFrame
{
    public static List<String> text;
    
    public Arraylist() {
        super("Arraylist", 100, 80, Arraylist::lambda$new$0);
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    public void getActiveModules() {
        Arraylist.text.clear();
        final List<Module> activeMods = (List<Module>)ModuleManager.getModules(Arraylist::lambda$getActiveModules$1);
        activeMods.removeIf(Arraylist::lambda$getActiveModules$2);
        activeMods.removeIf(Arraylist::lambda$getActiveModules$3);
        activeMods.removeIf(Arraylist::lambda$getActiveModules$4);
        for (final Module m : activeMods) {
            Arraylist.text.add(m.getName());
        }
        Arraylist.text.sort(Arraylist::lambda$getActiveModules$5);
    }
    
    @Override
    public void drawText() {
        this.getActiveModules();
        final int width = (this.getText().size() == 0) ? ((int)Guinness.CUSTOM_FONT.getStringWidth(this.getTitle()) + 4) : ((int)Guinness.CUSTOM_FONT.getStringWidth(MathUtil.getLongestString(this.getText())) + 6);
        int boost = 0;
        final ScaledResolution sr = new ScaledResolution(Arraylist.mc);
        if (this.getX() - width / 2 - 3 <= sr.getScaledWidth() / 2) {
            for (int i = 0; i < Arraylist.text.size(); ++i) {
                Guinness.CUSTOM_FONT.drawStringWithShadow(Arraylist.text.get(i), (float)(this.getX() + 3), (float)(this.getY() + 16 + boost * 10), ColorUtil.BESTCOLOR(boost, 255));
                ++boost;
            }
        }
        else if (this.getX() - width / 2 - 3 > sr.getScaledWidth() / 2) {
            for (int i = 0; i < Arraylist.text.size(); ++i) {
                Guinness.CUSTOM_FONT.drawStringWithShadow(Arraylist.text.get(i), this.getX() + width - Guinness.CUSTOM_FONT.getStringWidth(Arraylist.text.get(i)) - Float.intBitsToFloat(Float.floatToIntBits(0.6952738f) ^ 0x7F71FD77), (float)(this.getY() + 16 + boost * 10), ColorUtil.BESTCOLOR(boost, 255));
                ++boost;
            }
        }
    }
    
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Text text) {
        if (Arraylist.mc.currentScreen instanceof GuinnessHudGui) {
            return;
        }
        if (!ModuleManager.getModule(Arraylist::lambda$onRender$6).isEnabled()) {
            return;
        }
        this.getActiveModules();
        if (Arraylist.text.size() == 0) {
            return;
        }
        final int width = (this.getText().size() == 0) ? ((int)Guinness.CUSTOM_FONT.getStringWidth(this.getTitle()) + 4) : ((int)Guinness.CUSTOM_FONT.getStringWidth(MathUtil.getLongestString(this.getText())) + 6);
        int boost = 0;
        final ScaledResolution sr = new ScaledResolution(Arraylist.mc);
        if (this.dockMode == null) {
            this.setDockingStatus();
        }
        switch (Arraylist$1.$SwitchMap$dev$guinness$client$gui$hud$HudTextFrame$Docking[this.dockMode.ordinal()]) {
            case 1: {
                for (int i = 0; i < Arraylist.text.size(); ++i) {
                    if (dev.guinness.client.module.modules.hud.Arraylist.rects.getValue()) {
                        GuiScreen.func_73734_a(0, boost * 10, (int)Guinness.CUSTOM_FONT.getStringWidth(Arraylist.text.get(i)) + 4, 10 + boost * 10, -1439353547);
                        GuiScreen.func_73734_a((int)Guinness.CUSTOM_FONT.getStringWidth(Arraylist.text.get(i)) + 4, boost * 10, (int)Guinness.CUSTOM_FONT.getStringWidth(Arraylist.text.get(i)) + 5, 10 + boost * 10, ColorUtil.BESTCOLOR(boost, 255));
                    }
                    Guinness.CUSTOM_FONT.drawStringWithShadow(Arraylist.text.get(i), Float.intBitsToFloat(Float.floatToIntBits(5.5565877f) ^ 0x7F31CF91), (float)(boost * 10), ColorUtil.BESTCOLOR(boost, 255));
                    ++boost;
                    if (dev.guinness.client.module.modules.hud.Arraylist.roundoff.getValue()) {
                        String nextMod;
                        try {
                            nextMod = Arraylist.text.get(i + 1);
                        }
                        catch (IndexOutOfBoundsException e) {
                            nextMod = null;
                        }
                        if (nextMod != null) {
                            final int difference = (int)(Guinness.CUSTOM_FONT.getStringWidth(Arraylist.text.get(i)) - Guinness.CUSTOM_FONT.getStringWidth(nextMod));
                            GuiScreen.func_73734_a((int)Guinness.CUSTOM_FONT.getStringWidth(nextMod) + 5, boost * 10, (int)Guinness.CUSTOM_FONT.getStringWidth(nextMod) + difference + 5, boost * 10 + 1, ColorUtil.BESTCOLOR(boost, 255));
                        }
                    }
                }
                if (!(boolean)dev.guinness.client.module.modules.hud.Arraylist.roundoff.getValue()) {
                    break;
                }
                if (Arraylist.text.size() == 0) {
                    return;
                }
                GuiScreen.func_73734_a(0, boost * 10, (int)Guinness.CUSTOM_FONT.getStringWidth(Arraylist.text.get(Arraylist.text.size() - 1)) + 5, boost * 10 + 1, ColorUtil.BESTCOLOR(boost, 255));
                break;
            }
            case 2: {
                for (int i = 0; i < Arraylist.text.size(); ++i) {
                    if (dev.guinness.client.module.modules.hud.Arraylist.rects.getValue()) {
                        GuiScreen.func_73734_a(0, this.getY() + 16 + boost * 10, (int)Guinness.CUSTOM_FONT.getStringWidth(Arraylist.text.get(i)) + 4, this.getY() + 26 + boost * 10, -1439353547);
                        GuiScreen.func_73734_a((int)Guinness.CUSTOM_FONT.getStringWidth(Arraylist.text.get(i)) + 4, this.getY() + 16 + boost * 10, (int)Guinness.CUSTOM_FONT.getStringWidth(Arraylist.text.get(i)) + 5, this.getY() + 26 + boost * 10, ColorUtil.BESTCOLOR(boost, 255));
                    }
                    Guinness.CUSTOM_FONT.drawStringWithShadow(Arraylist.text.get(i), Float.intBitsToFloat(Float.floatToIntBits(5.8642473f) ^ 0x7F3BA7EA), (float)(this.getY() + 16 + boost * 10), ColorUtil.BESTCOLOR(boost, 255));
                    ++boost;
                    if (dev.guinness.client.module.modules.hud.Arraylist.roundoff.getValue()) {
                        String nextMod;
                        try {
                            nextMod = Arraylist.text.get(i + 1);
                        }
                        catch (IndexOutOfBoundsException e) {
                            nextMod = null;
                        }
                        if (nextMod != null) {
                            final int difference = (int)(Guinness.CUSTOM_FONT.getStringWidth(Arraylist.text.get(i)) - Guinness.CUSTOM_FONT.getStringWidth(nextMod));
                            GuiScreen.func_73734_a((int)Guinness.CUSTOM_FONT.getStringWidth(nextMod) + 5, this.getY() + 16 + boost * 10, (int)Guinness.CUSTOM_FONT.getStringWidth(nextMod) + difference + 5, this.getY() + 17 + boost * 10, ColorUtil.BESTCOLOR(boost, 255));
                        }
                    }
                }
                if (!(boolean)dev.guinness.client.module.modules.hud.Arraylist.roundoff.getValue()) {
                    break;
                }
                if (Arraylist.text.size() == 0) {
                    return;
                }
                GuiScreen.func_73734_a(0, this.getY() + 15, (int)Guinness.CUSTOM_FONT.getStringWidth(Arraylist.text.get(0)) + 5, this.getY() + 16, ColorUtil.BESTCOLOR(0, 255));
                GuiScreen.func_73734_a(0, this.getY() + 16 + boost * 10, (int)Guinness.CUSTOM_FONT.getStringWidth(Arraylist.text.get(Arraylist.text.size() - 1)) + 5, this.getY() + 17 + boost * 10, ColorUtil.BESTCOLOR(boost, 255));
                break;
            }
            case 3: {
                for (int i = 0; i < Arraylist.text.size(); ++i) {
                    if (dev.guinness.client.module.modules.hud.Arraylist.rects.getValue()) {
                        GuiScreen.func_73734_a((int)(sr.getScaledWidth() - Guinness.CUSTOM_FONT.getStringWidth(Arraylist.text.get(i)) - Float.intBitsToFloat(Float.floatToIntBits(0.038427945f) ^ 0x7D1D669F)), boost * 10, sr.getScaledWidth(), 10 + boost * 10, -1439353547);
                        GuiScreen.func_73734_a((int)(sr.getScaledWidth() - Guinness.CUSTOM_FONT.getStringWidth(Arraylist.text.get(i)) - Float.intBitsToFloat(Float.floatToIntBits(0.07879292f) ^ 0x7DE15E2F)), boost * 10, (int)(sr.getScaledWidth() - Guinness.CUSTOM_FONT.getStringWidth(Arraylist.text.get(i)) - Float.intBitsToFloat(Float.floatToIntBits(0.037196215f) ^ 0x7D185B0F)), 10 + boost * 10, ColorUtil.BESTCOLOR(boost, 255));
                    }
                    Guinness.CUSTOM_FONT.drawStringWithShadow(Arraylist.text.get(i), sr.getScaledWidth() - Guinness.CUSTOM_FONT.getStringWidth(Arraylist.text.get(i)) - Float.intBitsToFloat(Float.floatToIntBits(4.8233776f) ^ 0x7F1A591C), (float)(boost * 10), ColorUtil.BESTCOLOR(boost, 255));
                    ++boost;
                }
                break;
            }
            case 4: {
                for (int i = 0; i < Arraylist.text.size(); ++i) {
                    if (dev.guinness.client.module.modules.hud.Arraylist.rects.getValue()) {
                        GuiScreen.func_73734_a((int)(sr.getScaledWidth() - Guinness.CUSTOM_FONT.getStringWidth(Arraylist.text.get(i)) - Float.intBitsToFloat(Float.floatToIntBits(0.47245732f) ^ 0x7EF1E5ED)), this.getY() + 16 + boost * 10, sr.getScaledWidth(), this.getY() + 26 + boost * 10, -1439353547);
                        GuiScreen.func_73734_a((int)(sr.getScaledWidth() - Guinness.CUSTOM_FONT.getStringWidth(Arraylist.text.get(i)) - Float.intBitsToFloat(Float.floatToIntBits(0.6239458f) ^ 0x7F5FBAE9)), this.getY() + 16 + boost * 10, (int)(sr.getScaledWidth() - Guinness.CUSTOM_FONT.getStringWidth(Arraylist.text.get(i)) - Float.intBitsToFloat(Float.floatToIntBits(0.2576593f) ^ 0x7E83EBEB)), this.getY() + 26 + boost * 10, ColorUtil.BESTCOLOR(boost, 255));
                    }
                    Guinness.CUSTOM_FONT.drawStringWithShadow(Arraylist.text.get(i), sr.getScaledWidth() - Guinness.CUSTOM_FONT.getStringWidth(Arraylist.text.get(i)) - Float.intBitsToFloat(Float.floatToIntBits(5.1769648f) ^ 0x7F25A9B2), (float)(this.getY() + 16 + boost * 10), ColorUtil.BESTCOLOR(boost, 255));
                    ++boost;
                }
                break;
            }
            default: {
                if (this.getX() - width / 2 - 3 <= sr.getScaledWidth() / 2) {
                    for (int i = 0; i < Arraylist.text.size(); ++i) {
                        if (dev.guinness.client.module.modules.hud.Arraylist.rects.getValue()) {
                            GuiScreen.func_73734_a(this.getX() + 2, this.getY() + 16 + boost * 10, this.getX() + (int)Guinness.CUSTOM_FONT.getStringWidth(Arraylist.text.get(i)) + 4, this.getY() + 26 + boost * 10, -1439353547);
                            GuiScreen.func_73734_a(this.getX() + (int)Guinness.CUSTOM_FONT.getStringWidth(Arraylist.text.get(i)) + 4, this.getY() + 16 + boost * 10, this.getX() + (int)Guinness.CUSTOM_FONT.getStringWidth(Arraylist.text.get(i)) + 5, this.getY() + 26 + boost * 10, ColorUtil.BESTCOLOR(boost, 255));
                        }
                        Guinness.CUSTOM_FONT.drawStringWithShadow(Arraylist.text.get(i), (float)(this.getX() + 3), (float)(this.getY() + 16 + boost * 10), ColorUtil.BESTCOLOR(boost, 255));
                        ++boost;
                        if (dev.guinness.client.module.modules.hud.Arraylist.roundoff.getValue()) {
                            String nextMod;
                            try {
                                nextMod = Arraylist.text.get(i + 1);
                            }
                            catch (IndexOutOfBoundsException e) {
                                nextMod = null;
                            }
                            if (nextMod != null) {
                                final int difference = (int)(Guinness.CUSTOM_FONT.getStringWidth(Arraylist.text.get(i)) - Guinness.CUSTOM_FONT.getStringWidth(nextMod));
                                GuiScreen.func_73734_a(this.getX() + (int)Guinness.CUSTOM_FONT.getStringWidth(nextMod) + 5, this.getY() + 16 + boost * 10, this.getX() + (int)Guinness.CUSTOM_FONT.getStringWidth(nextMod) + difference + 5, this.getY() + 17 + boost * 10, ColorUtil.BESTCOLOR(boost, 255));
                            }
                        }
                    }
                    if (!(boolean)dev.guinness.client.module.modules.hud.Arraylist.roundoff.getValue()) {
                        break;
                    }
                    if (Arraylist.text.size() == 0) {
                        return;
                    }
                    GuiScreen.func_73734_a(this.getX() + 2, this.getY() + 15, this.getX() + (int)Guinness.CUSTOM_FONT.getStringWidth(Arraylist.text.get(0)) + 5, this.getY() + 16, ColorUtil.BESTCOLOR(0, 255));
                    GuiScreen.func_73734_a(this.getX() + 2, this.getY() + 16 + boost * 10, this.getX() + (int)Guinness.CUSTOM_FONT.getStringWidth(Arraylist.text.get(Arraylist.text.size() - 1)) + 5, this.getY() + 17 + boost * 10, ColorUtil.BESTCOLOR(boost, 255));
                    RenderUtil.drawVerticalGradientBox(this.getX() + 1, this.getY() + 15, 1, boost * 10 + 2, ColorUtil.BESTCOLOR(0, 255), ColorUtil.BESTCOLOR(boost, 255));
                    break;
                }
                else {
                    if (this.getX() - width / 2 - 3 > sr.getScaledWidth() / 2) {
                        for (int i = 0; i < Arraylist.text.size(); ++i) {
                            Guinness.CUSTOM_FONT.drawStringWithShadow(Arraylist.text.get(i), this.getX() + width - Guinness.CUSTOM_FONT.getStringWidth(Arraylist.text.get(i)) - Float.intBitsToFloat(Float.floatToIntBits(0.83432513f) ^ 0x7F559655), (float)(this.getY() + 16 + boost * 10), ColorUtil.BESTCOLOR(boost, 255));
                            ++boost;
                        }
                        break;
                    }
                    break;
                }
                break;
            }
        }
    }
    
    public static boolean lambda$onRender$6(final Module module) {
        return module.getName().equalsIgnoreCase("Arraylist");
    }
    
    public static int lambda$getActiveModules$5(final String s, final String s2) {
        return Integer.compare((int)Guinness.CUSTOM_FONT.getStringWidth(s2), (int)Guinness.CUSTOM_FONT.getStringWidth(s));
    }
    
    public static boolean lambda$getActiveModules$4(final Module module) {
        return !(boolean)((SBoolean)module.getSettingByName("Drawn")).getValue();
    }
    
    public static boolean lambda$getActiveModules$3(final Module module) {
        return module.getCategory().equals(Category.HIDDEN);
    }
    
    public static boolean lambda$getActiveModules$2(final Module module) {
        return module.getCategory().equals(Category.HUD);
    }
    
    public static boolean lambda$getActiveModules$1(final Module module) {
        return module.isEnabled();
    }
    
    public static List lambda$new$0() {
        return Arraylist.text;
    }
    
    static {
        Arraylist.text = new ArrayList<String>();
    }
}
