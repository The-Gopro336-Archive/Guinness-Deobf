package dev.guinness.client.module.modules.client;

import net.minecraft.client.gui.GuiScreen;
import dev.guinness.client.Guinness;
import dev.guinness.client.module.Category;
import dev.guinness.client.setting.SBoolean;
import dev.guinness.client.setting.SMode;
import dev.guinness.client.module.Module;

public class ClickGui extends Module
{
    public static SMode theme;
    public static SBoolean clickSound;
    
    public ClickGui() {
        super("ClickGui", Category.CLIENT, "The ClickGUI");
    }
    
    @Override
    public void onEnable() {
        ClickGui.mc.displayGuiScreen((GuiScreen)Guinness.guinnessClickGui);
        this.disable();
    }
    
    static {
        ClickGui.theme = new SMode("Theme", new String[] { "Guinness" });
        ClickGui.clickSound = new SBoolean("Click Sound", true);
    }
}
