package dev.guinness.client.util;

import java.awt.Color;
import dev.guinness.client.module.modules.client.Colors;

public class ColorUtil
{
    public static boolean RAINBOWONLY;
    public static boolean RAINBOW;
    public static boolean GRADIENT;
    
    public static void updateColors() {
        if (((String)Colors.colorMode.getValue()).equalsIgnoreCase("Rainbow")) {
            if (Colors.gradient.getValue()) {
                ColorUtil.GRADIENT = true;
                ColorUtil.RAINBOWONLY = true;
                return;
            }
        }
        if (((String)Colors.colorMode.getValue()).equalsIgnoreCase("Rainbow")) {
            if (!(boolean)Colors.gradient.getValue()) {
                ColorUtil.GRADIENT = false;
                ColorUtil.RAINBOWONLY = true;
                return;
            }
        }
        if ((!((String)Colors.colorMode.getValue()).equalsIgnoreCase("Rainbow") && !(boolean)Colors.gradient.getValue()) || (!((String)Colors.colorMode.getValue()).equalsIgnoreCase("Rainbow") && (boolean)Colors.gradient.getValue())) {
            ColorUtil.GRADIENT = false;
            ColorUtil.RAINBOWONLY = false;
        }
    }
    
    public static int rainbow(final long n, final int n2) {
        final float hue = (float)((System.currentTimeMillis() * ((double)Colors.speed.getValue() / Double.longBitsToDouble(Double.doubleToLongBits(0.1945486318633371) ^ 0x7FECE6F835AAD366L)) + n * ((long)(-1942993408) ^ 0xFFFFFFFF8C3047F4L)) % (Double.longBitsToDouble(Double.doubleToLongBits(3.110081473398666E-4) ^ 0x7FE92DD9D255502FL) / ((double)Colors.difference.getValue() / Double.longBitsToDouble(Double.doubleToLongBits(0.009576666126914051) ^ 0x7FDA9CEE5E8EF6E3L))) / (Double.longBitsToDouble(Double.doubleToLongBits(4.4598247589669275E-4) ^ 0x7FE07658260861DEL) / ((double)Colors.difference.getValue() / Double.longBitsToDouble(Double.doubleToLongBits(0.36770462035546114) ^ 0x7FE38878F5C0F282L))));
        final int rgb = Color.HSBtoRGB(hue, ((Double)Colors.saturation.getValue()).floatValue(), ((Double)Colors.brightness.getValue()).floatValue());
        final int red = rgb >> 16 & 0xFF;
        final int green = rgb >> 8 & 0xFF;
        final int blue = rgb & 0xFF;
        final int color = toRGBA(red, green, blue, n2);
        return color;
    }
    
    public static int toRGBA(final int n, final int n2, final int n3, final int n4) {
        return (n << 16) + (n2 << 8) + (n3 << 0) + (n4 << 24);
    }
    
    public static int BESTCOLOR(final int n, final int n2) {
        return ColorUtil.RAINBOWONLY ? (ColorUtil.GRADIENT ? rainbow(n, n2) : rainbow((long)742087092 ^ 0x2C3B59B5L, n2)) : toRGBA(((Double)Colors.r.getValue()).intValue(), ((Double)Colors.g.getValue()).intValue(), ((Double)Colors.b.getValue()).intValue(), n2);
    }
}
