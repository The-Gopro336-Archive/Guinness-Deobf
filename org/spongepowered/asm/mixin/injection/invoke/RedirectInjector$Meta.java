package org.spongepowered.asm.mixin.injection.invoke;

class Meta
{
    public static final String KEY = "redirector";
    final int priority;
    final boolean isFinal;
    final String name;
    final String desc;
    
    public Meta(final int priority, final boolean isFinal, final String name, final String desc) {
        this.priority = priority;
        this.isFinal = isFinal;
        this.name = name;
        this.desc = desc;
    }
    
    RedirectInjector getOwner() {
        return RedirectInjector.this;
    }
}
