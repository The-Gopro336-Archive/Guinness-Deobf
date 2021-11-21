package org.spongepowered.asm.mixin.transformer;

import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.mixin.transformer.throwables.InvalidMixinException;
import org.spongepowered.asm.lib.AnnotationVisitor;
import org.spongepowered.asm.lib.commons.Remapper;
import org.spongepowered.asm.lib.ClassVisitor;
import org.spongepowered.asm.lib.commons.ClassRemapper;

static class InnerClassAdapter extends ClassRemapper
{
    private final InnerClassInfo info;
    
    public InnerClassAdapter(final ClassVisitor cv, final InnerClassInfo info) {
        super(327680, cv, info);
        this.info = info;
    }
    
    @Override
    public void visitSource(final String source, final String debug) {
        super.visitSource(source, debug);
        final AnnotationVisitor av = this.cv.visitAnnotation("Lorg/spongepowered/asm/mixin/transformer/meta/MixinInner;", false);
        av.visit("mixin", this.info.getOwner().toString());
        av.visit("name", this.info.getOriginalName().substring(this.info.getOriginalName().lastIndexOf(47) + 1));
        av.visitEnd();
    }
    
    @Override
    public void visitInnerClass(final String name, final String outerName, final String innerName, final int access) {
        if (name.startsWith(this.info.getOriginalName() + "$")) {
            throw new InvalidMixinException(this.info.getOwner(), "Found unsupported nested inner class " + name + " in " + this.info.getOriginalName());
        }
        super.visitInnerClass(name, outerName, innerName, access);
    }
}
