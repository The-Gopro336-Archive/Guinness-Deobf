package dev.guinness.client.mixin.mixins;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.common.MinecraftForge;
import dev.guinness.client.event.events.ModelBipedEvent;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.util.EnumHandSide;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelBiped;
import org.spongepowered.asm.mixin.Mixin;
import dev.guinness.client.util.Wrapper;
import net.minecraft.client.model.ModelBase;

@Mixin({ ModelBiped.class })
public abstract class MixinModelBiped extends ModelBase implements Wrapper
{
    @Shadow
    public ModelRenderer field_78116_c;
    @Shadow
    public ModelRenderer field_78115_e;
    @Shadow
    public ModelRenderer field_178723_h;
    @Shadow
    public ModelRenderer field_178724_i;
    @Shadow
    public ModelRenderer field_178721_j;
    @Shadow
    public ModelRenderer field_178722_k;
    @Shadow
    public ModelBiped.ArmPose field_187075_l;
    @Shadow
    public ModelBiped.ArmPose field_187076_m;
    @Shadow
    public ModelRenderer field_178720_f;
    
    @Shadow
    protected abstract EnumHandSide func_187072_a(final Entity p0);
    
    @Shadow
    protected abstract ModelRenderer func_187074_a(final EnumHandSide p0);
    
    @Inject(method = { "setRotationAngles" }, at = { @At("HEAD") }, cancellable = true)
    public void setRotationAngles(final float limbSwing, final float limbSwingAmount, final float ageInTicks, final float netHeadYaw, final float headPitch, final float scaleFactor, final Entity entityIn, final CallbackInfo ci) {
        final ModelBipedEvent event = new ModelBipedEvent(netHeadYaw, headPitch, entityIn);
        MinecraftForge.EVENT_BUS.post(event);
        if (entityIn == MixinModelBiped.mc.player) {
            ci.cancel();
            this.field_78116_c.rotateAngleY = event.getYaw() * 0.017453292f;
            this.field_78116_c.rotateAngleX = event.getPitch() * 0.017453292f;
            this.field_78115_e.rotateAngleY = 0.0f;
            this.field_178723_h.rotationPointZ = 0.0f;
            this.field_178723_h.rotationPointX = -5.0f;
            this.field_178724_i.rotationPointZ = 0.0f;
            this.field_178724_i.rotationPointX = 5.0f;
            this.field_178723_h.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
            this.field_178724_i.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
            this.field_178723_h.rotateAngleZ = 0.0f;
            this.field_178724_i.rotateAngleZ = 0.0f;
            this.field_178721_j.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
            this.field_178722_k.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
            this.field_178721_j.rotateAngleY = 0.0f;
            this.field_178722_k.rotateAngleY = 0.0f;
            this.field_178721_j.rotateAngleZ = 0.0f;
            this.field_178722_k.rotateAngleZ = 0.0f;
            this.field_178723_h.rotateAngleY = 0.0f;
            this.field_178723_h.rotateAngleZ = 0.0f;
            switch (this.field_187075_l) {
                case EMPTY: {
                    this.field_178724_i.rotateAngleY = 0.0f;
                    break;
                }
                case BLOCK: {
                    this.field_178724_i.rotateAngleX = this.field_178724_i.rotateAngleX * 0.5f - 0.9424779f;
                    this.field_178724_i.rotateAngleY = 0.5235988f;
                    break;
                }
                case ITEM: {
                    this.field_178724_i.rotateAngleX = this.field_178724_i.rotateAngleX * 0.5f - 0.31415927f;
                    this.field_178724_i.rotateAngleY = 0.0f;
                    break;
                }
            }
            switch (this.field_187076_m) {
                case EMPTY: {
                    this.field_178723_h.rotateAngleY = 0.0f;
                    break;
                }
                case BLOCK: {
                    this.field_178723_h.rotateAngleX = this.field_178723_h.rotateAngleX * 0.5f - 0.9424779f;
                    this.field_178723_h.rotateAngleY = -0.5235988f;
                    break;
                }
                case ITEM: {
                    this.field_178723_h.rotateAngleX = this.field_178723_h.rotateAngleX * 0.5f - 0.31415927f;
                    this.field_178723_h.rotateAngleY = 0.0f;
                    break;
                }
            }
            if (this.swingProgress > 0.0f) {
                final EnumHandSide enumhandside = this.func_187072_a(entityIn);
                final ModelRenderer modelrenderer = this.func_187074_a(enumhandside);
                float f1 = this.swingProgress;
                this.field_78115_e.rotateAngleY = MathHelper.sin(MathHelper.sqrt(f1) * 6.2831855f) * 0.2f;
                if (enumhandside == EnumHandSide.LEFT) {
                    final ModelRenderer field_78115_e = this.field_78115_e;
                    field_78115_e.rotateAngleY *= -1.0f;
                }
                this.field_178723_h.rotationPointZ = MathHelper.sin(this.field_78115_e.rotateAngleY) * 5.0f;
                this.field_178723_h.rotationPointX = -MathHelper.cos(this.field_78115_e.rotateAngleY) * 5.0f;
                this.field_178724_i.rotationPointZ = -MathHelper.sin(this.field_78115_e.rotateAngleY) * 5.0f;
                this.field_178724_i.rotationPointX = MathHelper.cos(this.field_78115_e.rotateAngleY) * 5.0f;
                final ModelRenderer field_178723_h = this.field_178723_h;
                field_178723_h.rotateAngleY += this.field_78115_e.rotateAngleY;
                final ModelRenderer field_178724_i = this.field_178724_i;
                field_178724_i.rotateAngleY += this.field_78115_e.rotateAngleY;
                final ModelRenderer field_178724_i2 = this.field_178724_i;
                field_178724_i2.rotateAngleX += this.field_78115_e.rotateAngleY;
                f1 = 1.0f - this.swingProgress;
                f1 *= f1;
                f1 *= f1;
                f1 = 1.0f - f1;
                final float f2 = MathHelper.sin(f1 * 3.1415927f);
                final float f3 = MathHelper.sin(this.swingProgress * 3.1415927f) * -(this.field_78116_c.rotateAngleX - 0.7f) * 0.75f;
                modelrenderer.rotateAngleX -= (float)(f2 * 1.2 + f3);
                final ModelRenderer modelRenderer = modelrenderer;
                modelRenderer.rotateAngleY += this.field_78115_e.rotateAngleY * 2.0f;
                final ModelRenderer modelRenderer2 = modelrenderer;
                modelRenderer2.rotateAngleZ += MathHelper.sin(this.swingProgress * 3.1415927f) * -0.4f;
            }
            this.field_78115_e.rotateAngleX = 0.0f;
            this.field_178721_j.rotationPointZ = 0.1f;
            this.field_178722_k.rotationPointZ = 0.1f;
            this.field_178721_j.rotationPointY = 12.0f;
            this.field_178722_k.rotationPointY = 12.0f;
            this.field_78116_c.rotationPointY = 0.0f;
            final ModelRenderer field_178723_h2 = this.field_178723_h;
            field_178723_h2.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09f) * 0.05f + 0.05f;
            final ModelRenderer field_178724_i3 = this.field_178724_i;
            field_178724_i3.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09f) * 0.05f + 0.05f;
            final ModelRenderer field_178723_h3 = this.field_178723_h;
            field_178723_h3.rotateAngleX += MathHelper.sin(ageInTicks * 0.067f) * 0.05f;
            final ModelRenderer field_178724_i4 = this.field_178724_i;
            field_178724_i4.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067f) * 0.05f;
            if (this.field_187076_m == ModelBiped.ArmPose.BOW_AND_ARROW) {
                this.field_178723_h.rotateAngleY = -0.1f + this.field_78116_c.rotateAngleY;
                this.field_178724_i.rotateAngleY = 0.1f + this.field_78116_c.rotateAngleY + 0.4f;
                this.field_178723_h.rotateAngleX = -1.5707964f + this.field_78116_c.rotateAngleX;
                this.field_178724_i.rotateAngleX = -1.5707964f + this.field_78116_c.rotateAngleX;
            }
            else if (this.field_187075_l == ModelBiped.ArmPose.BOW_AND_ARROW) {
                this.field_178723_h.rotateAngleY = -0.1f + this.field_78116_c.rotateAngleY - 0.4f;
                this.field_178724_i.rotateAngleY = 0.1f + this.field_78116_c.rotateAngleY;
                this.field_178723_h.rotateAngleX = -1.5707964f + this.field_78116_c.rotateAngleX;
                this.field_178724_i.rotateAngleX = -1.5707964f + this.field_78116_c.rotateAngleX;
            }
            copyModelAngles(this.field_78116_c, this.field_178720_f);
        }
    }
}
