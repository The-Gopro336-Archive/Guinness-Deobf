package dev.guinness.client.mixin.mixins;

import org.spongepowered.asm.mixin.gen.Accessor;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CPacketCustomPayload;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ CPacketCustomPayload.class })
public interface ICPacketCustomPayload
{
    @Accessor("data")
    void setPacketData(final PacketBuffer p0);
}
