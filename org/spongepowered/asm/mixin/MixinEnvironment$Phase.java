package org.spongepowered.asm.mixin;

import com.google.common.collect.ImmutableList;
import java.util.Iterator;
import java.util.List;

public static final class Phase
{
    static final Phase NOT_INITIALISED;
    public static final Phase PREINIT;
    public static final Phase INIT;
    public static final Phase DEFAULT;
    static final List<Phase> phases;
    final int ordinal;
    final String name;
    private MixinEnvironment environment;
    
    private Phase(final int ordinal, final String name) {
        this.ordinal = ordinal;
        this.name = name;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
    public static Phase forName(final String name) {
        for (final Phase phase : Phase.phases) {
            if (phase.name.equals(name)) {
                return phase;
            }
        }
        return null;
    }
    
    MixinEnvironment getEnvironment() {
        if (this.ordinal < 0) {
            throw new IllegalArgumentException("Cannot access the NOT_INITIALISED environment");
        }
        if (this.environment == null) {
            this.environment = new MixinEnvironment(this);
        }
        return this.environment;
    }
    
    static {
        NOT_INITIALISED = new Phase(-1, "NOT_INITIALISED");
        PREINIT = new Phase(0, "PREINIT");
        INIT = new Phase(1, "INIT");
        DEFAULT = new Phase(2, "DEFAULT");
        phases = ImmutableList.of(Phase.PREINIT, Phase.INIT, Phase.DEFAULT);
    }
}
