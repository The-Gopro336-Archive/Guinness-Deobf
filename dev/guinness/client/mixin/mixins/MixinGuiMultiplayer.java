package dev.guinness.client.mixin.mixins;

import dev.guinness.client.util.ClientUtil;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.GuiMultiplayer;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.client.gui.GuiScreen;

@Mixin({ GuiMultiplayer.class })
public class MixinGuiMultiplayer extends GuiScreen
{
    private GuiTextField nameField;
    
    @Inject(method = { "initGui" }, at = { @At("RETURN") })
    public void initGui(final CallbackInfo ci) {
        (this.nameField = new GuiTextField(69, this.mc.fontRenderer, 8, 8, 95, 15)).setText(this.mc.getSession().getUsername());
        this.nameField.setMaxStringLength(16);
    }
    
    @Inject(method = { "drawScreen" }, at = { @At("TAIL") })
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks, final CallbackInfo ci) {
        this.nameField.drawTextBox();
    }
    
    @Inject(method = { "mouseClicked" }, at = { @At("TAIL") })
    public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton, final CallbackInfo ci) {
        this.nameField.mouseClicked(mouseX, mouseY, mouseButton);
    }
    
    @Inject(method = { "keyTyped" }, at = { @At("TAIL") })
    public void keyTyped(final char typedChar, final int keyCode, final CallbackInfo ci) {
        this.nameField.textboxKeyTyped(typedChar, keyCode);
        if (keyCode == 28) {
            ClientUtil.setUsername(this.nameField.getText());
            this.nameField.setFocused(false);
        }
    }
}
