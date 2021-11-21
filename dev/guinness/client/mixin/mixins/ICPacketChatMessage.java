package dev.guinness.client.mixin.mixins;

import org.spongepowered.asm.mixin.gen.Accessor;
import net.minecraft.network.play.client.CPacketChatMessage;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ CPacketChatMessage.class })
public interface ICPacketChatMessage
{
    @Accessor("message")
    void setMessage(final String p0);
}
