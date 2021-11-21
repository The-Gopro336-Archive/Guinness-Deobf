package dev.guinness.client.mixin.mixins;

import dev.guinness.client.module.Module;
import dev.guinness.client.module.modules.render.NoRender;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import dev.guinness.client.module.ModuleManager;
import dev.guinness.client.module.modules.render.ShulkerPeek;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.item.ItemStack;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ GuiScreen.class })
public class MixinGuiScreen
{
    @Inject(method = { "renderToolTip" }, at = { @At("HEAD") }, cancellable = true)
    public void renderToolTip(final ItemStack itemStack, final int x, final int y, final CallbackInfo ci) {
        if (ModuleManager.getModule(m -> m.getClass().equals(ShulkerPeek.class)).isEnabled()) {
            ShulkerPeek.renderToolTip(itemStack, x, y, ci);
        }
    }
    
    @Inject(method = { "drawDefaultBackground" }, at = { @At("HEAD") }, cancellable = true)
    public void drawDefaultBackground(final CallbackInfo ci) {
        if (Minecraft.getMinecraft().player != null && Minecraft.getMinecraft().world != null && ModuleManager.getModule(m -> m.getClass().equals(NoRender.class)).isEnabled() && (boolean)NoRender.guitint.getValue()) {
            ci.cancel();
        }
    }
}
