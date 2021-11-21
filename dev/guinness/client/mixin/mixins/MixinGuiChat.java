package dev.guinness.client.mixin.mixins;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.lwjgl.input.Keyboard;
import dev.guinness.client.util.ClientUtil;
import dev.guinness.client.util.RenderUtil;
import dev.guinness.client.command.CommandManager;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.GuiChat;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ GuiChat.class })
public class MixinGuiChat
{
    @Shadow
    protected GuiTextField field_146415_a;
    
    @Inject(method = { "drawScreen" }, at = { @At("HEAD") })
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks, final CallbackInfo ci) {
        if (this.field_146415_a.getText().startsWith(CommandManager.PREFIX)) {
            String commandName = null;
            RenderUtil.drawChatboxOutline();
            commandName = ClientUtil.handleChatPrediction(this.field_146415_a.getText());
            if (Keyboard.isKeyDown(15) && commandName != null) {
                this.field_146415_a.setText(CommandManager.PREFIX + commandName);
            }
        }
    }
}
