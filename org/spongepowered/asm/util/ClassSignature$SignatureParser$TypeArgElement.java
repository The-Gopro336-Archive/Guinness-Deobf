package org.spongepowered.asm.util;

import org.spongepowered.asm.lib.signature.SignatureVisitor;

class TypeArgElement extends TokenElement
{
    private final TokenElement type;
    private final char wildcard;
    
    TypeArgElement(final TokenElement type, final char wildcard) {
        this.type = type;
        this.wildcard = wildcard;
    }
    
    @Override
    public SignatureVisitor visitArrayType() {
        this.type.setArray();
        return this;
    }
    
    @Override
    public void visitBaseType(final char descriptor) {
        this.token = this.type.addTypeArgument(descriptor).asToken();
    }
    
    @Override
    public void visitTypeVariable(final String name) {
        final TokenHandle token = SignatureParser.this.this$0.getType(name);
        this.token = this.type.addTypeArgument(token).setWildcard(this.wildcard).asToken();
    }
    
    @Override
    public void visitClassType(final String name) {
        this.token = this.type.addTypeArgument(name).setWildcard(this.wildcard).asToken();
    }
    
    @Override
    public void visitTypeArgument() {
        this.token.addTypeArgument('*');
    }
    
    @Override
    public SignatureVisitor visitTypeArgument(final char wildcard) {
        return new TypeArgElement(this, wildcard);
    }
    
    @Override
    public void visitEnd() {
    }
}
