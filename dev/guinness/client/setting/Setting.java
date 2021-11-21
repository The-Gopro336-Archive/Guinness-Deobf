package dev.guinness.client.setting;

import java.util.Arrays;
import java.util.function.BooleanSupplier;
import java.util.List;

public class Setting
{
    public value;
    public List<String> modes;
    public boolean opened;
    public int scale;
    public max;
    public BooleanSupplier vis;
    public min;
    public String name;
    public int modeIndex;
    
    public Setting(final String name, final Object value) {
        this.modeIndex = 0;
        this.opened = false;
        this.name = name;
        this.value = value;
    }
    
    public Setting(final BooleanSupplier visibility, final String name, final Object value) {
        this(name, value);
        this.vis = visibility;
    }
    
    public Setting(final String name, final Object min, final Object value, final Object max) {
        this.modeIndex = 0;
        this.opened = false;
        this.name = name;
        this.min = min;
        this.value = value;
        this.max = max;
    }
    
    public Setting(final BooleanSupplier visibility, final String name, final Object min, final Object value, final Object max) {
        this(name, min, value, max);
        this.vis = visibility;
    }
    
    public Setting(final String name, final Object min, final Object value, final Object max, final int scale) {
        this.modeIndex = 0;
        this.opened = false;
        this.name = name;
        this.min = min;
        this.value = value;
        this.max = max;
        this.scale = scale;
    }
    
    public Setting(final BooleanSupplier visibility, final String name, final Object min, final Object value, final Object max, final int scale) {
        this(name, min, value, max, scale);
        this.vis = visibility;
    }
    
    public Setting(final String name, final String... modes) {
        this.modeIndex = 0;
        this.opened = false;
        this.name = name;
        this.modes = Arrays.asList(modes);
        this.value = modes[this.modeIndex];
    }
    
    public Setting(final BooleanSupplier visibility, final String name, final String... modes) {
        this(name, modes);
        this.vis = visibility;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Object getValue() {
        return this.value;
    }
    
    public Object getMinValue() {
        return this.min;
    }
    
    public Object getMaxValue() {
        return this.max;
    }
    
    public int getRoundingScale() {
        return this.scale;
    }
    
    public int getNextMode() {
        return this.modeIndex + 1;
    }
    
    public String getMode(final int n) {
        return this.modes.get(n);
    }
    
    public List getAllModes() {
        return this.modes;
    }
    
    public void setValue(final Object value) {
        this.value = value;
    }
    
    public void setMode(final int n) {
        this.modeIndex = ((n > this.modes.size() - 1) ? 0 : n);
        this.setValue(this.modes.get(this.modeIndex));
    }
    
    public boolean isVisible() {
        return this.vis == null || this.vis.getAsBoolean();
    }
    
    public boolean isOpened() {
        return this.opened;
    }
    
    public void setOpened(final boolean opened) {
        this.opened = opened;
    }
}
