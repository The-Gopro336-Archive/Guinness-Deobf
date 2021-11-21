package org.spongepowered.asm.util;

class SuperClassElement extends TokenElement
{
    @Override
    public void visitEnd() {
        SignatureParser.this.this$0.setSuperClass(this.token);
    }
}
