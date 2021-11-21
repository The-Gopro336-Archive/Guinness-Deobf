package org.spongepowered.asm.mixin;

public enum Option
{
    DEBUG_ALL("debug"), 
    DEBUG_EXPORT(Option.DEBUG_ALL, "export"), 
    DEBUG_EXPORT_FILTER(Option.DEBUG_EXPORT, "filter", false), 
    DEBUG_EXPORT_DECOMPILE(Option.DEBUG_EXPORT, Inherit.ALLOW_OVERRIDE, "decompile"), 
    DEBUG_EXPORT_DECOMPILE_THREADED(Option.DEBUG_EXPORT_DECOMPILE, Inherit.ALLOW_OVERRIDE, "async"), 
    DEBUG_EXPORT_DECOMPILE_MERGESIGNATURES(Option.DEBUG_EXPORT_DECOMPILE, Inherit.ALLOW_OVERRIDE, "mergeGenericSignatures"), 
    DEBUG_VERIFY(Option.DEBUG_ALL, "verify"), 
    DEBUG_VERBOSE(Option.DEBUG_ALL, "verbose"), 
    DEBUG_INJECTORS(Option.DEBUG_ALL, "countInjections"), 
    DEBUG_STRICT(Option.DEBUG_ALL, Inherit.INDEPENDENT, "strict"), 
    DEBUG_UNIQUE(Option.DEBUG_STRICT, "unique"), 
    DEBUG_TARGETS(Option.DEBUG_STRICT, "targets"), 
    DEBUG_PROFILER(Option.DEBUG_ALL, Inherit.ALLOW_OVERRIDE, "profiler"), 
    DUMP_TARGET_ON_FAILURE("dumpTargetOnFailure"), 
    CHECK_ALL("checks"), 
    CHECK_IMPLEMENTS(Option.CHECK_ALL, "interfaces"), 
    CHECK_IMPLEMENTS_STRICT(Option.CHECK_IMPLEMENTS, Inherit.ALLOW_OVERRIDE, "strict"), 
    IGNORE_CONSTRAINTS("ignoreConstraints"), 
    HOT_SWAP("hotSwap"), 
    ENVIRONMENT(Inherit.ALWAYS_FALSE, "env"), 
    OBFUSCATION_TYPE(Option.ENVIRONMENT, Inherit.ALWAYS_FALSE, "obf"), 
    DISABLE_REFMAP(Option.ENVIRONMENT, Inherit.INDEPENDENT, "disableRefMap"), 
    REFMAP_REMAP(Option.ENVIRONMENT, Inherit.INDEPENDENT, "remapRefMap"), 
    REFMAP_REMAP_RESOURCE(Option.ENVIRONMENT, Inherit.INDEPENDENT, "refMapRemappingFile", ""), 
    REFMAP_REMAP_SOURCE_ENV(Option.ENVIRONMENT, Inherit.INDEPENDENT, "refMapRemappingEnv", "searge"), 
    REFMAP_REMAP_ALLOW_PERMISSIVE(Option.ENVIRONMENT, Inherit.INDEPENDENT, "allowPermissiveMatch", true, "true"), 
    IGNORE_REQUIRED(Option.ENVIRONMENT, Inherit.INDEPENDENT, "ignoreRequired"), 
    DEFAULT_COMPATIBILITY_LEVEL(Option.ENVIRONMENT, Inherit.INDEPENDENT, "compatLevel"), 
    SHIFT_BY_VIOLATION_BEHAVIOUR(Option.ENVIRONMENT, Inherit.INDEPENDENT, "shiftByViolation", "warn"), 
    INITIALISER_INJECTION_MODE("initialiserInjectionMode", "default");
    
    private static final String PREFIX = "mixin";
    final Option parent;
    final Inherit inheritance;
    final String property;
    final String defaultValue;
    final boolean isFlag;
    final int depth;
    
    private Option(final String property) {
        this(null, property, true);
    }
    
    private Option(final Inherit inheritance, final String property) {
        this(null, inheritance, property, true);
    }
    
    private Option(final String property, final boolean flag) {
        this(null, property, flag);
    }
    
    private Option(final String property, final String defaultStringValue) {
        this(null, Inherit.INDEPENDENT, property, false, defaultStringValue);
    }
    
    private Option(final Option parent, final String property) {
        this(parent, Inherit.INHERIT, property, true);
    }
    
    private Option(final Option parent, final Inherit inheritance, final String property) {
        this(parent, inheritance, property, true);
    }
    
    private Option(final Option parent, final String property, final boolean isFlag) {
        this(parent, Inherit.INHERIT, property, isFlag, null);
    }
    
    private Option(final Option parent, final Inherit inheritance, final String property, final boolean isFlag) {
        this(parent, inheritance, property, isFlag, null);
    }
    
    private Option(final Option parent, final String property, final String defaultStringValue) {
        this(parent, Inherit.INHERIT, property, false, defaultStringValue);
    }
    
    private Option(final Option parent, final Inherit inheritance, final String property, final String defaultStringValue) {
        this(parent, inheritance, property, false, defaultStringValue);
    }
    
    private Option(Option parent, final Inherit inheritance, final String property, final boolean isFlag, final String defaultStringValue) {
        this.parent = parent;
        this.inheritance = inheritance;
        this.property = ((parent != null) ? parent.property : "mixin") + "." + property;
        this.defaultValue = defaultStringValue;
        this.isFlag = isFlag;
        int depth;
        for (depth = 0; parent != null; parent = parent.parent, ++depth) {}
        this.depth = depth;
    }
    
    Option getParent() {
        return this.parent;
    }
    
    String getProperty() {
        return this.property;
    }
    
    @Override
    public String toString() {
        return this.isFlag ? String.valueOf(this.getBooleanValue()) : this.getStringValue();
    }
    
    private boolean getLocalBooleanValue(final boolean defaultValue) {
        return Boolean.parseBoolean(System.getProperty(this.property, Boolean.toString(defaultValue)));
    }
    
    private boolean getInheritedBooleanValue() {
        return this.parent != null && this.parent.getBooleanValue();
    }
    
    final boolean getBooleanValue() {
        if (this.inheritance == Inherit.ALWAYS_FALSE) {
            return false;
        }
        final boolean local = this.getLocalBooleanValue(false);
        if (this.inheritance == Inherit.INDEPENDENT) {
            return local;
        }
        final boolean inherited = local || this.getInheritedBooleanValue();
        return (this.inheritance == Inherit.INHERIT) ? inherited : this.getLocalBooleanValue(inherited);
    }
    
    final String getStringValue() {
        return (this.inheritance == Inherit.INDEPENDENT || this.parent == null || this.parent.getBooleanValue()) ? System.getProperty(this.property, this.defaultValue) : this.defaultValue;
    }
    
     <E extends Enum<E>> E getEnumValue(final E defaultValue) {
        final String value = System.getProperty(this.property, defaultValue.name());
        try {
            return Enum.valueOf(defaultValue.getClass(), value.toUpperCase());
        }
        catch (IllegalArgumentException ex) {
            return defaultValue;
        }
    }
    
    private enum Inherit
    {
        INHERIT, 
        ALLOW_OVERRIDE, 
        INDEPENDENT, 
        ALWAYS_FALSE;
    }
}
