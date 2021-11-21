package org.spongepowered.asm.mixin;

import org.spongepowered.asm.mixin.extensibility.IEnvironmentTokenProvider;

static class TokenProviderWrapper implements Comparable<TokenProviderWrapper>
{
    private static int nextOrder;
    private final int priority;
    private final int order;
    private final IEnvironmentTokenProvider provider;
    private final MixinEnvironment environment;
    
    public TokenProviderWrapper(final IEnvironmentTokenProvider provider, final MixinEnvironment environment) {
        this.provider = provider;
        this.environment = environment;
        this.order = TokenProviderWrapper.nextOrder++;
        this.priority = provider.getPriority();
    }
    
    @Override
    public int compareTo(final TokenProviderWrapper other) {
        if (other == null) {
            return 0;
        }
        if (other.priority == this.priority) {
            return other.order - this.order;
        }
        return other.priority - this.priority;
    }
    
    public IEnvironmentTokenProvider getProvider() {
        return this.provider;
    }
    
    Integer getToken(final String token) {
        return this.provider.getToken(token, this.environment);
    }
    
    static {
        TokenProviderWrapper.nextOrder = 0;
    }
}
