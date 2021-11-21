package org.spongepowered.asm.mixin.transformer;

import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.util.Bytecode;
import java.lang.annotation.Annotation;

enum SpecialMethod
{
    MERGE(true), 
    OVERWRITE(true, (Class<? extends Annotation>)Overwrite.class), 
    SHADOW(false, (Class<? extends Annotation>)Shadow.class), 
    ACCESSOR(false, (Class<? extends Annotation>)Accessor.class), 
    INVOKER(false, (Class<? extends Annotation>)Invoker.class);
    
    final boolean isOverwrite;
    final Class<? extends Annotation> annotation;
    final String description;
    
    private SpecialMethod(final boolean isOverwrite, final Class<? extends Annotation> type) {
        this.isOverwrite = isOverwrite;
        this.annotation = type;
        this.description = "@" + Bytecode.getSimpleName(type);
    }
    
    private SpecialMethod(final boolean isOverwrite) {
        this.isOverwrite = isOverwrite;
        this.annotation = null;
        this.description = "overwrite";
    }
    
    @Override
    public String toString() {
        return this.description;
    }
}
