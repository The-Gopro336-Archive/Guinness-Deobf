package org.spongepowered.asm.util;

import org.spongepowered.asm.lib.signature.SignatureVisitor;

class BoundElement extends TokenElement
{
    private final TokenElement type;
    private final boolean classBound;
    
    BoundElement(final TokenElement type, final boolean classBound) {
        this.type = type;
        this.classBound = classBound;
    }
    
    @Override
    public void visitClassType(final String name) {
        this.token = this.type.token.addBound(name, this.classBound);
    }
    
    @Override
    public void visitTypeArgument() {
        this.token.addTypeArgument('*');
    }
    
    @Override
    public SignatureVisitor visitTypeArgument(final char wildcard) {
        return new TypeArgElement(this, wildcard);
    }
}
