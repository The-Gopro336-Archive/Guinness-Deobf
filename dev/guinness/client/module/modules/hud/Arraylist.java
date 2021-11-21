package dev.guinness.client.module.modules.hud;

import dev.guinness.client.module.Category;
import dev.guinness.client.setting.SBoolean;
import dev.guinness.client.module.Module;

public class Arraylist extends Module
{
    public static SBoolean rects;
    public static SBoolean roundoff;
    
    public Arraylist() {
        super("Arraylist", Category.HUD, "Displays active modules on your screen");
    }
    
    public static boolean lambda$static$0() {
        return (boolean)Arraylist.rects.getValue();
    }
    
    static {
        Arraylist.rects = new SBoolean("Rectangle Background", false);
        Arraylist.roundoff = new SBoolean(Arraylist::lambda$static$0, "Round Off", true);
    }
}
