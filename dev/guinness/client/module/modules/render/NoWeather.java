package dev.guinness.client.module.modules.render;

import dev.guinness.client.module.Category;
import dev.guinness.client.module.Module;

public class NoWeather extends Module
{
    public NoWeather() {
        super("NoWeather", Category.RENDER, "Stops rain, snow, and thunder");
    }
    
    @Override
    public void onUpdate() {
        if (NoWeather.mc.world.func_72896_J()) {
            NoWeather.mc.world.func_72894_k(Float.intBitsToFloat(Float.floatToIntBits(1.6180032E38f) ^ 0x7EF37337));
        }
    }
}
