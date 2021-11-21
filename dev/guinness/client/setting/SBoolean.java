package dev.guinness.client.setting;

import java.util.function.BooleanSupplier;

public class SBoolean extends Setting
{
    public SBoolean(final String name, final boolean value) {
        super(name, (Object)value);
    }
    
    public SBoolean(final BooleanSupplier visibility, final String name, final boolean value) {
        super(visibility, name, (Object)value);
    }
}
