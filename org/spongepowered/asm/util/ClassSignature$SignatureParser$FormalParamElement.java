package org.spongepowered.asm.util;

class FormalParamElement extends TokenElement
{
    private final TokenHandle handle;
    
    FormalParamElement(final String param) {
        this.handle = SignatureParser.this.this$0.getType(param);
        this.token = this.handle.asToken();
    }
}
