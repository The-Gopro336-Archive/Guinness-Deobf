package org.spongepowered.asm.mixin.transformer.ext.extensions;

import org.spongepowered.asm.mixin.throwables.MixinException;

public static class ValidationFailedException extends MixinException
{
    private static final long serialVersionUID = 1L;
    
    public ValidationFailedException(final String message, final Throwable cause) {
        super(message, cause);
    }
    
    public ValidationFailedException(final String message) {
        super(message);
    }
    
    public ValidationFailedException(final Throwable cause) {
        super(cause);
    }
}
