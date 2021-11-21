package org.spongepowered.asm.mixin.transformer;

import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Shadow;
import java.lang.annotation.Annotation;
import org.spongepowered.asm.util.Annotations;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.lib.tree.FieldNode;

class Field extends Member
{
    public Field(final Member member) {
        super(member);
    }
    
    public Field(final ClassInfo this$0, final FieldNode field) {
        this(this$0, field, false);
    }
    
    public Field(final FieldNode field, final boolean injected) {
        super(Type.FIELD, field.name, field.desc, field.access, injected);
        this.setUnique(Annotations.getVisible(field, Unique.class) != null);
        if (Annotations.getVisible(field, Shadow.class) != null) {
            final boolean decoratedFinal = Annotations.getVisible(field, Final.class) != null;
            final boolean decoratedMutable = Annotations.getVisible(field, Mutable.class) != null;
            this.setDecoratedFinal(decoratedFinal, decoratedMutable);
        }
    }
    
    public Field(final String name, final String desc, final int access) {
        super(Type.FIELD, name, desc, access, false);
    }
    
    public Field(final String name, final String desc, final int access, final boolean injected) {
        super(Type.FIELD, name, desc, access, injected);
    }
    
    @Override
    public ClassInfo getOwner() {
        return ClassInfo.this;
    }
    
    @Override
    public boolean equals(final Object obj) {
        return obj instanceof Field && super.equals(obj);
    }
    
    @Override
    protected String getDisplayFormat() {
        return "%s:%s";
    }
}
