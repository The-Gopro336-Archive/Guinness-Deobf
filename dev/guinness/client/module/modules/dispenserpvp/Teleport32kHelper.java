package dev.guinness.client.module.modules.dispenserpvp;

import dev.guinness.client.module.Category;
import dev.guinness.client.module.Module;

public class Teleport32kHelper extends Module
{
    public int ticks;
    
    public Teleport32kHelper() {
        super("Teleport32kHelper", Category.HIDDEN, "Helper for 32kTeleport");
        this.ticks = 0;
    }
    
    @Override
    public void onUpdate() {
        if (Teleport32k.INSTANCE.flag) {
            ++this.ticks;
            if (this.ticks == (double)Teleport32k.delay.getValue()) {
                ++this.ticks;
                Teleport32kHelper.mc.player.field_70181_x = Double.longBitsToDouble(Double.doubleToLongBits(0.199591864206608) ^ 0x7FD98C39E8A853B3L);
                this.disable();
            }
        }
    }
    
    @Override
    public void onDisable() {
        this.ticks = 0;
        Teleport32k.INSTANCE.flag = false;
    }
}
