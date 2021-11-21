package org.spongepowered.asm.launch;

public class MixinInitialisationError extends Error
{
    private static final long serialVersionUID = 1L;
    
    public MixinInitialisationError() {
    }
    
    public MixinInitialisationError(final String message) {
        super(message);
    }
    
    public MixinInitialisationError(final Throwable cause) {
        super(cause);
    }
    
    public MixinInitialisationError(final String message, final Throwable cause) {
        super(message, cause);
    }
}
