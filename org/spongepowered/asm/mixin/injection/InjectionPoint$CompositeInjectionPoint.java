package org.spongepowered.asm.mixin.injection;

import com.google.common.base.Joiner;

abstract static class CompositeInjectionPoint extends InjectionPoint
{
    protected final InjectionPoint[] components;
    
    protected CompositeInjectionPoint(final InjectionPoint... components) {
        if (components == null || components.length < 2) {
            throw new IllegalArgumentException("Must supply two or more component injection points for composite point!");
        }
        this.components = components;
    }
    
    @Override
    public String toString() {
        return "CompositeInjectionPoint(" + this.getClass().getSimpleName() + ")[" + Joiner.on(',').join(this.components) + "]";
    }
}
