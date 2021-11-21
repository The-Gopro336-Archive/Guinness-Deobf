package org.spongepowered.asm.mixin.transformer;

import org.spongepowered.asm.lib.MethodVisitor;
import org.spongepowered.asm.lib.FieldVisitor;
import org.spongepowered.asm.lib.ClassVisitor;

class MixinPostProcessor$1 extends ClassVisitor {
    @Override
    public void visit(final int version, final int access, final String name, final String signature, final String superName, final String[] interfaces) {
        super.visit(version, access | 0x1, name, signature, superName, interfaces);
    }
    
    @Override
    public FieldVisitor visitField(int access, final String name, final String desc, final String signature, final Object value) {
        if ((access & 0x6) == 0x0) {
            access |= 0x1;
        }
        return super.visitField(access, name, desc, signature, value);
    }
    
    @Override
    public MethodVisitor visitMethod(int access, final String name, final String desc, final String signature, final String[] exceptions) {
        if ((access & 0x6) == 0x0) {
            access |= 0x1;
        }
        return super.visitMethod(access, name, desc, signature, exceptions);
    }
}