package dev.guinness.client.module.modules.render;

import dev.guinness.client.module.Category;
import dev.guinness.client.setting.SBoolean;
import dev.guinness.client.setting.SSlider;
import dev.guinness.client.module.Module;

public class ViewModel extends Module
{
    public static SSlider zR;
    public static SSlider xR;
    public static SSlider yR;
    public static SBoolean cancelEating;
    
    public ViewModel() {
        super("ViewModel", Category.RENDER, "Allows you to change your arm's position");
    }
    
    static {
        ViewModel.cancelEating = new SBoolean("CancelEating", false);
        ViewModel.xR = new SSlider("XRight", Double.longBitsToDouble(Double.doubleToLongBits(-0.9926622222146867) ^ 0x7FEFC3E3908C605FL), Double.longBitsToDouble(Double.doubleToLongBits(-14.979322034616947) ^ 0x7FE98F88F533C950L), Double.longBitsToDouble(Double.doubleToLongBits(0.07714077932922679) ^ 0x7FB3BF7F84682FF7L), 2);
        ViewModel.yR = new SSlider("YRight", Double.longBitsToDouble(Double.doubleToLongBits(-0.9787513680386892) ^ 0x7FEF51EE63948698L), Double.longBitsToDouble(Double.doubleToLongBits(112.13716885939785) ^ 0x7FE87226184B5D56L), Double.longBitsToDouble(Double.doubleToLongBits(0.6232282534766334) ^ 0x7FE3F17C60D402C6L), 2);
        ViewModel.zR = new SSlider("ZRight", Double.longBitsToDouble(Double.doubleToLongBits(-0.8190227175273247) ^ 0x7FEA356F214EBFBDL), Double.longBitsToDouble(Double.doubleToLongBits(4.3605527085385225E307) ^ 0x7FCF0C549987672FL), Double.longBitsToDouble(Double.doubleToLongBits(0.30585905856468826) ^ 0x7FD39331DF6E1FB3L), 2);
    }
}
