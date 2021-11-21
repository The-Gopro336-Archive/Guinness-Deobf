package org.spongepowered.asm.util;

class TokenHandle implements IToken
{
    final Token token;
    boolean array;
    char wildcard;
    
    TokenHandle(final ClassSignature this$0) {
        this(this$0, new Token());
    }
    
    TokenHandle(final Token token) {
        this.token = token;
    }
    
    @Override
    public IToken setArray(final boolean array) {
        this.array |= array;
        return this;
    }
    
    @Override
    public IToken setWildcard(final char wildcard) {
        if ("+-".indexOf(wildcard) > -1) {
            this.wildcard = wildcard;
        }
        return this;
    }
    
    @Override
    public String asBound() {
        return this.token.asBound();
    }
    
    @Override
    public String asType() {
        final StringBuilder sb = new StringBuilder();
        if (this.wildcard > '\0') {
            sb.append(this.wildcard);
        }
        if (this.array) {
            sb.append('[');
        }
        return sb.append(ClassSignature.this.getTypeVar(this)).toString();
    }
    
    @Override
    public Token asToken() {
        return this.token;
    }
    
    @Override
    public String toString() {
        return this.token.toString();
    }
    
    public TokenHandle clone() {
        return new TokenHandle(this.token);
    }
}
