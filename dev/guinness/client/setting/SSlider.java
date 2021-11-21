package dev.guinness.client.setting;

import java.util.function.BooleanSupplier;

public class SSlider extends Setting
{
    public SSlider(final String name, final double min, final double value, final double max, final int scale) {
        super(name, (Object)min, value, max, scale);
    }
    
    public SSlider(final BooleanSupplier visibility, final String name, final double min, final double value, final double max, final int scale) {
        super(visibility, name, (Object)min, value, max, scale);
    }
}
