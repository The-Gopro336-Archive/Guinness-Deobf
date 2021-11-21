package dev.guinness.client.module.modules.dispenserpvp;

import dev.guinness.client.module.Category;
import dev.guinness.client.module.Module;

public class Auto32kReset extends Module
{
    public int ticks;
    public static Auto32kReset INSTANCE;
    
    public Auto32kReset() {
        super("Auto32kReset", Category.HIDDEN, "");
        this.ticks = 0;
        (Auto32kReset.INSTANCE = this).disable();
    }
    
    @Override
    public void onUpdate() {
        if (Auto32k.INSTANCE.dispenserFuckedUp) {
            ++this.ticks;
            if (this.ticks == 20) {
                Auto32k.INSTANCE.dispenserFuckedUp = false;
                Auto32k.INSTANCE.enable();
            }
        }
    }
    
    @Override
    public void onDisable() {
        this.ticks = 0;
    }
}
