package dev.guinness.client.module.modules.render;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import dev.guinness.client.module.Category;
import dev.guinness.client.setting.SSlider;
import dev.guinness.client.module.Module;

public class CustomFOV extends Module
{
    public static SSlider fov;
    
    public CustomFOV() {
        super("CustomFOV", Category.RENDER, "Changes your FOV to a custom value");
    }
    
    @SubscribeEvent
    public void fovModifier(final EntityViewRenderEvent.FOVModifier fovModifier) {
        fovModifier.setFOV(((Double)CustomFOV.fov.getValue()).floatValue());
    }
    
    static {
        CustomFOV.fov = new SSlider("FOV", Double.longBitsToDouble(Double.doubleToLongBits(0.011473491460657447) ^ 0x7FD6FF69F4C173B9L), Double.longBitsToDouble(Double.doubleToLongBits(0.013508061896702677) ^ 0x7FEBEA1D60A123E3L), Double.longBitsToDouble(Double.doubleToLongBits(0.012581242129492277) ^ 0x7FEDC431BBE9EFB4L), 1);
    }
}
