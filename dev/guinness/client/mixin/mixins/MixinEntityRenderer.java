package dev.guinness.client.mixin.mixins;

import dev.guinness.client.module.Module;
import org.spongepowered.asm.mixin.injection.Redirect;
import java.util.ArrayList;
import dev.guinness.client.module.modules.misc.NoEntityTrace;
import java.util.List;
import com.google.common.base.Predicate;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.entity.Entity;
import net.minecraft.client.multiplayer.WorldClient;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import dev.guinness.client.module.ModuleManager;
import dev.guinness.client.module.modules.render.NoRender;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import dev.guinness.client.util.Wrapper;

@Mixin(value = { EntityRenderer.class }, priority = 10001)
public class MixinEntityRenderer implements Wrapper
{
    @Inject(method = { "hurtCameraEffect" }, at = { @At("HEAD") }, cancellable = true)
    public void hurtCameraEffect(final float ticks, final CallbackInfo info) {
        if (ModuleManager.getModule(m -> m.getClass().equals(NoRender.class)).isEnabled() && (boolean)NoRender.hurtcam.getValue()) {
            info.cancel();
        }
    }
    
    @Redirect(method = { "getMouseOver" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/WorldClient;getEntitiesInAABBexcluding(Lnet/minecraft/entity/Entity;Lnet/minecraft/util/math/AxisAlignedBB;Lcom/google/common/base/Predicate;)Ljava/util/List;"))
    public List<Entity> getEntitiesInAABBexcluding(final WorldClient worldClient, final Entity entityIn, final AxisAlignedBB boundingBox, final Predicate<? super Entity> predicate) {
        if (ModuleManager.getModule(m -> m.getClass().equals(NoEntityTrace.class)).isEnabled()) {
            return new ArrayList<Entity>();
        }
        return (List<Entity>)worldClient.func_175674_a(entityIn, boundingBox, predicate);
    }
}
