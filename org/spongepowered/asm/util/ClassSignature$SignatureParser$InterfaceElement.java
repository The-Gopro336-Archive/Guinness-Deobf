package org.spongepowered.asm.util;

class InterfaceElement extends TokenElement
{
    @Override
    public void visitEnd() {
        SignatureParser.this.this$0.addInterface(this.token);
    }
}
