package org.spongepowered.asm.util;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

static class Token implements IToken
{
    static final String SYMBOLS = "+-*";
    private final boolean inner;
    private boolean array;
    private char symbol;
    private String type;
    private List<Token> classBound;
    private List<Token> ifaceBound;
    private List<IToken> signature;
    private List<IToken> suffix;
    private Token tail;
    
    Token() {
        this(false);
    }
    
    Token(final String type) {
        this(type, false);
    }
    
    Token(final char symbol) {
        this();
        this.symbol = symbol;
    }
    
    Token(final boolean inner) {
        this(null, inner);
    }
    
    Token(final String type, final boolean inner) {
        this.symbol = '\0';
        this.inner = inner;
        this.type = type;
    }
    
    Token setSymbol(final char symbol) {
        if (this.symbol == '\0' && "+-*".indexOf(symbol) > -1) {
            this.symbol = symbol;
        }
        return this;
    }
    
    Token setType(final String type) {
        if (this.type == null) {
            this.type = type;
        }
        return this;
    }
    
    boolean hasClassBound() {
        return this.classBound != null;
    }
    
    boolean hasInterfaceBound() {
        return this.ifaceBound != null;
    }
    
    @Override
    public IToken setArray(final boolean array) {
        this.array |= array;
        return this;
    }
    
    @Override
    public IToken setWildcard(final char wildcard) {
        if ("+-".indexOf(wildcard) == -1) {
            return this;
        }
        return this.setSymbol(wildcard);
    }
    
    private List<Token> getClassBound() {
        if (this.classBound == null) {
            this.classBound = new ArrayList<Token>();
        }
        return this.classBound;
    }
    
    private List<Token> getIfaceBound() {
        if (this.ifaceBound == null) {
            this.ifaceBound = new ArrayList<Token>();
        }
        return this.ifaceBound;
    }
    
    private List<IToken> getSignature() {
        if (this.signature == null) {
            this.signature = new ArrayList<IToken>();
        }
        return this.signature;
    }
    
    private List<IToken> getSuffix() {
        if (this.suffix == null) {
            this.suffix = new ArrayList<IToken>();
        }
        return this.suffix;
    }
    
    IToken addTypeArgument(final char symbol) {
        if (this.tail != null) {
            return this.tail.addTypeArgument(symbol);
        }
        final Token token = new Token(symbol);
        this.getSignature().add(token);
        return token;
    }
    
    IToken addTypeArgument(final String name) {
        if (this.tail != null) {
            return this.tail.addTypeArgument(name);
        }
        final Token token = new Token(name);
        this.getSignature().add(token);
        return token;
    }
    
    IToken addTypeArgument(final Token token) {
        if (this.tail != null) {
            return this.tail.addTypeArgument(token);
        }
        this.getSignature().add(token);
        return token;
    }
    
    IToken addTypeArgument(final TokenHandle token) {
        if (this.tail != null) {
            return this.tail.addTypeArgument(token);
        }
        final TokenHandle handle = token.clone();
        this.getSignature().add(handle);
        return handle;
    }
    
    Token addBound(final String bound, final boolean classBound) {
        if (classBound) {
            return this.addClassBound(bound);
        }
        return this.addInterfaceBound(bound);
    }
    
    Token addClassBound(final String bound) {
        final Token token = new Token(bound);
        this.getClassBound().add(token);
        return token;
    }
    
    Token addInterfaceBound(final String bound) {
        final Token token = new Token(bound);
        this.getIfaceBound().add(token);
        return token;
    }
    
    Token addInnerClass(final String name) {
        this.tail = new Token(name, true);
        this.getSuffix().add(this.tail);
        return this.tail;
    }
    
    @Override
    public String toString() {
        return this.asType();
    }
    
    @Override
    public String asBound() {
        final StringBuilder sb = new StringBuilder();
        if (this.type != null) {
            sb.append(this.type);
        }
        if (this.classBound != null) {
            for (final Token token : this.classBound) {
                sb.append(token.asType());
            }
        }
        if (this.ifaceBound != null) {
            for (final Token token : this.ifaceBound) {
                sb.append(':').append(token.asType());
            }
        }
        return sb.toString();
    }
    
    @Override
    public String asType() {
        return this.asType(false);
    }
    
    public String asType(final boolean raw) {
        final StringBuilder sb = new StringBuilder();
        if (this.array) {
            sb.append('[');
        }
        if (this.symbol != '\0') {
            sb.append(this.symbol);
        }
        if (this.type == null) {
            return sb.toString();
        }
        if (!this.inner) {
            sb.append('L');
        }
        sb.append(this.type);
        if (!raw) {
            if (this.signature != null) {
                sb.append('<');
                for (final IToken token : this.signature) {
                    sb.append(token.asType());
                }
                sb.append('>');
            }
            if (this.suffix != null) {
                for (final IToken token : this.suffix) {
                    sb.append('.').append(token.asType());
                }
            }
        }
        if (!this.inner) {
            sb.append(';');
        }
        return sb.toString();
    }
    
    boolean isRaw() {
        return this.signature == null;
    }
    
    String getClassType() {
        return (this.type != null) ? this.type : "java/lang/Object";
    }
    
    @Override
    public Token asToken() {
        return this;
    }
}
