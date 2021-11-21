package dev.guinness.client.setting;

import java.util.function.BooleanSupplier;

public class SMode extends Setting
{
    public SMode(final String name, final String... modes) {
        super(name, modes);
    }
    
    public SMode(final BooleanSupplier visibility, final String name, final String... modes) {
        super(visibility, name, modes);
    }
}
