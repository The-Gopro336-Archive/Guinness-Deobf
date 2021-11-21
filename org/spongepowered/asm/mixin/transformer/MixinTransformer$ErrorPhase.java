package org.spongepowered.asm.mixin.transformer;

import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.extensibility.IMixinConfig;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.mixin.transformer.throwables.InvalidMixinException;
import org.spongepowered.asm.mixin.extensibility.IMixinErrorHandler;

enum ErrorPhase
{
    PREPARE {
        @Override
        IMixinErrorHandler.ErrorAction onError(final IMixinErrorHandler handler, final String context, final InvalidMixinException ex, final IMixinInfo mixin, final IMixinErrorHandler.ErrorAction action) {
            try {
                return handler.onPrepareError(mixin.getConfig(), ex, mixin, action);
            }
            catch (AbstractMethodError ame) {
                return action;
            }
        }
        
        @Override
        protected String getContext(final IMixinInfo mixin, final String context) {
            return String.format("preparing %s in %s", mixin.getName(), context);
        }
    }, 
    APPLY {
        @Override
        IMixinErrorHandler.ErrorAction onError(final IMixinErrorHandler handler, final String context, final InvalidMixinException ex, final IMixinInfo mixin, final IMixinErrorHandler.ErrorAction action) {
            try {
                return handler.onApplyError(context, ex, mixin, action);
            }
            catch (AbstractMethodError ame) {
                return action;
            }
        }
        
        @Override
        protected String getContext(final IMixinInfo mixin, final String context) {
            return String.format("%s -> %s", mixin, context);
        }
    };
    
    private final String text;
    
    private ErrorPhase() {
        this.text = this.name().toLowerCase();
    }
    
    abstract IMixinErrorHandler.ErrorAction onError(final IMixinErrorHandler p0, final String p1, final InvalidMixinException p2, final IMixinInfo p3, final IMixinErrorHandler.ErrorAction p4);
    
    protected abstract String getContext(final IMixinInfo p0, final String p1);
    
    public String getLogMessage(final String context, final InvalidMixinException ex, final IMixinInfo mixin) {
        return String.format("Mixin %s failed %s: %s %s", this.text, this.getContext(mixin, context), ex.getClass().getName(), ex.getMessage());
    }
    
    public String getErrorMessage(final IMixinInfo mixin, final IMixinConfig config, final MixinEnvironment.Phase phase) {
        return String.format("Mixin [%s] from phase [%s] in config [%s] FAILED during %s", mixin, phase, config, this.name());
    }
}
