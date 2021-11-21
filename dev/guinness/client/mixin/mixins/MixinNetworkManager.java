package dev.guinness.client.mixin.mixins;

import dev.guinness.client.module.Module;
import dev.guinness.client.util.MessageUtil;
import io.netty.handler.codec.DecoderException;
import dev.guinness.client.module.ModuleManager;
import dev.guinness.client.module.modules.exploit.AntiPacketBan;
import dev.guinness.client.event.events.PacketEvent$PacketReceiveEvent;
import io.netty.channel.ChannelHandlerContext;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.common.MinecraftForge;
import dev.guinness.client.event.events.PacketEvent$PacketSendEvent;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.network.Packet;
import net.minecraft.network.NetworkManager;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ NetworkManager.class })
public class MixinNetworkManager
{
    @Inject(method = { "sendPacket(Lnet/minecraft/network/Packet;)V" }, at = { @At("HEAD") }, cancellable = true)
    public void onPacketSend(final Packet<?> packet, final CallbackInfo ci) {
        final PacketEvent$PacketSendEvent event = new PacketEvent$PacketSendEvent(packet);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled()) {
            ci.cancel();
        }
    }
    
    @Inject(method = { "channelRead0" }, at = { @At("HEAD") }, cancellable = true)
    public void onPacketReceive(final ChannelHandlerContext chc, final Packet<?> packet, final CallbackInfo ci) {
        final PacketEvent$PacketReceiveEvent event = new PacketEvent$PacketReceiveEvent(packet);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled()) {
            ci.cancel();
        }
    }
    
    @Inject(method = { "exceptionCaught" }, at = { @At("HEAD") }, cancellable = true)
    public void exceptionCaught(final ChannelHandlerContext p_exceptionCaught_1_, final Throwable p_exceptionCaught_2_, final CallbackInfo ci) {
        if (ModuleManager.getModule(m -> m.getClass().equals(AntiPacketBan.class)).isEnabled() && p_exceptionCaught_2_ instanceof DecoderException) {
            MessageUtil.sendClientMessage("Caught a packet larger than protocol maximum of 2097152!");
            ci.cancel();
        }
    }
}
