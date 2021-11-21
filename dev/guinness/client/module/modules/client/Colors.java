package dev.guinness.client.module.modules.client;

import dev.guinness.client.module.Category;
import dev.guinness.client.setting.SMode;
import dev.guinness.client.setting.SBoolean;
import dev.guinness.client.setting.SSlider;
import dev.guinness.client.module.Module;

public class Colors extends Module
{
    public static SSlider b;
    public static SBoolean gradient;
    public static SSlider speed;
    public static SSlider saturation;
    public static SSlider g;
    public static SSlider r;
    public static SMode colorMode;
    public static SSlider brightness;
    public static SSlider difference;
    
    public Colors() {
        super("Colors", Category.CLIENT, "Settings for the global client colors");
    }
    
    @Override
    public void onEnable() {
        this.disable();
    }
    
    public static boolean lambda$static$7() {
        if (((String)Colors.colorMode.getValue()).equalsIgnoreCase("Rainbow")) {
            if (Colors.gradient.getValue()) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean lambda$static$6() {
        return ((String)Colors.colorMode.getValue()).equalsIgnoreCase("Rainbow");
    }
    
    public static boolean lambda$static$5() {
        return ((String)Colors.colorMode.getValue()).equalsIgnoreCase("Rainbow");
    }
    
    public static boolean lambda$static$4() {
        return ((String)Colors.colorMode.getValue()).equalsIgnoreCase("Rainbow");
    }
    
    public static boolean lambda$static$3() {
        return ((String)Colors.colorMode.getValue()).equalsIgnoreCase("Rainbow");
    }
    
    public static boolean lambda$static$2() {
        return ((String)Colors.colorMode.getValue()).equalsIgnoreCase("Static");
    }
    
    public static boolean lambda$static$1() {
        return ((String)Colors.colorMode.getValue()).equalsIgnoreCase("Static");
    }
    
    public static boolean lambda$static$0() {
        return ((String)Colors.colorMode.getValue()).equalsIgnoreCase("Static");
    }
    
    static {
        Colors.colorMode = new SMode("Color Mode", new String[] { "Rainbow", "Static" });
        Colors.r = new SSlider(Colors::lambda$static$0, "Red", Double.longBitsToDouble(Double.doubleToLongBits(1.4677229044213156E308) ^ 0x7FEA20577C659ACAL), Double.longBitsToDouble(Double.doubleToLongBits(0.06430018703629382) ^ 0x7FEE75FA207293A1L), Double.longBitsToDouble(Double.doubleToLongBits(0.010774691978038136) ^ 0x7FE9F10AAD5C7636L), 0);
        Colors.g = new SSlider(Colors::lambda$static$1, "Green", Double.longBitsToDouble(Double.doubleToLongBits(1.2263730315871948E308) ^ 0x7FE5D485AA970BDFL), Double.longBitsToDouble(Double.doubleToLongBits(5.414493988340931E307) ^ 0x7FD346B7901F8193L), Double.longBitsToDouble(Double.doubleToLongBits(0.009599750618176481) ^ 0x7FEC4908B7A37AA5L), 0);
        Colors.b = new SSlider(Colors::lambda$static$2, "Blue", Double.longBitsToDouble(Double.doubleToLongBits(5.470048291373218E307) ^ 0x7FD3795943F47029L), Double.longBitsToDouble(Double.doubleToLongBits(0.09573721170691775) ^ 0x7FD7623BE14A9BD1L), Double.longBitsToDouble(Double.doubleToLongBits(0.0769201308204026) ^ 0x7FDC5109A647156FL), 0);
        Colors.speed = new SSlider(Colors::lambda$static$3, "Speed", Double.longBitsToDouble(Double.doubleToLongBits(160.87850755905706) ^ 0x7FDD8585227BF791L), Double.longBitsToDouble(Double.doubleToLongBits(0.32656142855281983) ^ 0x7FEDE661E7F13DE1L), Double.longBitsToDouble(Double.doubleToLongBits(0.12256526329080387) ^ 0x7FE6606FE575AA71L), 1);
        Colors.saturation = new SSlider(Colors::lambda$static$4, "Saturation", Double.longBitsToDouble(Double.doubleToLongBits(88.69198700205632) ^ 0x7FD256A8C477D1C9L), Double.longBitsToDouble(Double.doubleToLongBits(2.142034642084924) ^ 0x7FE8BB7A976CA299L), Double.longBitsToDouble(Double.doubleToLongBits(6.153840651385328) ^ 0x7FE89D886759FB35L), 2);
        Colors.brightness = new SSlider(Colors::lambda$static$5, "Brightness", Double.longBitsToDouble(Double.doubleToLongBits(44.77658855318083) ^ 0x7FC21986075D3A73L), Double.longBitsToDouble(Double.doubleToLongBits(2.671600220784651) ^ 0x7FECC6F6765B94A3L), Double.longBitsToDouble(Double.doubleToLongBits(24.092635564564358) ^ 0x7FC817B6F6E0401BL), 2);
        Colors.gradient = new SBoolean(Colors::lambda$static$6, "Gradient", true);
        Colors.difference = new SSlider(Colors::lambda$static$7, "Difference", Double.longBitsToDouble(Double.doubleToLongBits(2.439496136143332) ^ 0x7FBA1D8F15423827L), Double.longBitsToDouble(Double.doubleToLongBits(0.46281910238594925) ^ 0x7FE49ED4032D8CF2L), Double.longBitsToDouble(Double.doubleToLongBits(0.11429955949106171) ^ 0x7FE442BC65F618D6L), 1);
    }
}
