package org.spongepowered.asm.mixin.transformer;

import org.spongepowered.asm.mixin.transformer.ext.IHotSwap;

class MixinTransformer$1 implements MixinConfig.IListener {
    final /* synthetic */ IHotSwap val$hotSwapper;
    
    @Override
    public void onPrepare(final MixinInfo mixin) {
        this.val$hotSwapper.registerMixinClass(mixin.getClassName());
    }
    
    @Override
    public void onInit(final MixinInfo mixin) {
    }
}