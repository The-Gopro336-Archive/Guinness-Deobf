package dev.guinness.client.mixin.mixins;

import dev.guinness.client.module.Module;
import org.spongepowered.asm.mixin.Overwrite;
import net.minecraft.client.renderer.GlStateManager;
import dev.guinness.client.module.ModuleManager;
import dev.guinness.client.module.modules.render.NoCluster;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.entity.RenderManager;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import dev.guinness.client.util.Wrapper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.EntityLivingBase;

@Mixin(value = { RenderLivingBase.class }, priority = 10001)
public abstract class MixinRenderLivingBase<T extends EntityLivingBase> extends Render<T> implements Wrapper
{
    @Shadow
    protected ModelBase field_77045_g;
    
    protected MixinRenderLivingBase() {
        super(null);
    }
    
    @Overwrite
    protected void func_77036_a(final T entitylivingbaseIn, final float limbSwing, final float limbSwingAmount, final float ageInTicks, final float netHeadYaw, final float headPitch, final float scaleFactor) {
        if (MixinRenderLivingBase.mc.player.func_70032_d(entitylivingbaseIn) < 1.0f && entitylivingbaseIn != MixinRenderLivingBase.mc.player && ModuleManager.getModule(m -> m.getClass().equals(NoCluster.class)).isEnabled()) {
            GlStateManager.enableBlendProfile(GlStateManager.Profile.TRANSPARENT_MODEL);
        }
        if (!this.bindEntityTexture((Entity)entitylivingbaseIn)) {
            return;
        }
        this.field_77045_g.render((Entity)entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
    }
}
