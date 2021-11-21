package dev.guinness.client.mixin.mixins;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import dev.guinness.client.util.RenderUtil;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.gui.GuiMainMenu;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.client.gui.GuiScreen;

@Mixin({ GuiMainMenu.class })
public class MixinGuiMainMenu extends GuiScreen
{
    @Inject(method = { "drawScreen" }, at = { @At("TAIL") })
    public void drawText(final int mouseX, final int mouseY, final float partialTicks, final CallbackInfo ci) {
        RenderUtil.drawHorizontalRainbowText("Guinness v0.4.2", 2, 2, true);
    }
}
