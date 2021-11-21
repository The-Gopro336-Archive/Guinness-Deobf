package dev.guinness.client.module.modules.render;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import dev.guinness.client.module.Category;
import dev.guinness.client.setting.SSlider;
import dev.guinness.client.module.Module;

public class SkyColor extends Module
{
    public static SSlider g;
    public static SSlider b;
    public static SSlider r;
    
    public SkyColor() {
        super("SkyColor", Category.RENDER, "Changes the color of the sky. May cause fog if you don't restart your game");
    }
    
    @SubscribeEvent
    public void colorEvent(final EntityViewRenderEvent.FogColors fogColors) {
        fogColors.setRed((float)((double)SkyColor.r.getValue() / Double.longBitsToDouble(Double.doubleToLongBits(0.09299643739304643) ^ 0x7FD82E9D513F65B9L)));
        fogColors.setGreen((float)((double)SkyColor.g.getValue() / Double.longBitsToDouble(Double.doubleToLongBits(0.10269850140021596) ^ 0x7FD5AA72F0DCB50FL)));
        fogColors.setBlue((float)((double)SkyColor.b.getValue() / Double.longBitsToDouble(Double.doubleToLongBits(0.4604856300750932) ^ 0x7FB29898B85CD4A7L)));
    }
    
    @SubscribeEvent
    public void fogEvent(final EntityViewRenderEvent.FogDensity fogDensity) {
        fogDensity.setDensity(Float.intBitsToFloat(Float.floatToIntBits(1.3958015E38f) ^ 0x7ED20451));
        fogDensity.setCanceled(true);
    }
    
    static {
        SkyColor.r = new SSlider("R", Double.longBitsToDouble(Double.doubleToLongBits(1.6090101713801668E308) ^ 0x7FECA42E51852E37L), Double.longBitsToDouble(Double.doubleToLongBits(0.07355286323649583) ^ 0x7FD8945C4620BEBBL), Double.longBitsToDouble(Double.doubleToLongBits(0.014811722663943603) ^ 0x7FE1B59BC38331C5L), 1);
        SkyColor.g = new SSlider("G", Double.longBitsToDouble(Double.doubleToLongBits(1.7126153388583899E308) ^ 0x7FEE7C4DFC3D33C4L), Double.longBitsToDouble(Double.doubleToLongBits(0.1112606885266051) ^ 0x7FD63B949A8D5DB9L), Double.longBitsToDouble(Double.doubleToLongBits(0.013867449969087797) ^ 0x7FE38689A0C4E966L), 1);
        SkyColor.b = new SSlider("B", Double.longBitsToDouble(Double.doubleToLongBits(1.664888023576281E308) ^ 0x7FEDA2D046CFB4B6L), Double.longBitsToDouble(Double.doubleToLongBits(0.013489651675872965) ^ 0x7FE1E076664229A3L), Double.longBitsToDouble(Double.doubleToLongBits(0.008179294450928666) ^ 0x7FEF204E515E65F4L), 1);
    }
}
