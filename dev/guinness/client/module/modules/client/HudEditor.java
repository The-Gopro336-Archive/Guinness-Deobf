package dev.guinness.client.module.modules.client;

import net.minecraft.client.gui.GuiScreen;
import dev.guinness.client.Guinness;
import dev.guinness.client.module.Category;
import dev.guinness.client.module.Module;

public class HudEditor extends Module
{
    public HudEditor() {
        super("HudEditor", Category.CLIENT, "Opens the hud editor GUI");
    }
    
    @Override
    public void onEnable() {
        HudEditor.mc.displayGuiScreen((GuiScreen)Guinness.guinnessHudGui);
        this.disable();
    }
    
    @Override
    public void onUpdate() {
        this.disable();
    }
}
