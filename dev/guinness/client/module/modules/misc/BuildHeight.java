package dev.guinness.client.module.modules.misc;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.network.Packet;
import net.minecraft.util.EnumFacing;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import dev.guinness.client.event.events.PacketEvent$PacketSendEvent;
import dev.guinness.client.module.Category;
import dev.guinness.client.module.Module;

public class BuildHeight extends Module
{
    public BuildHeight() {
        super("BuildHeight", Category.MISC, "Allows you to interact with blocks at build height");
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent$PacketSendEvent packetEvent$PacketSendEvent) {
        if (!(packetEvent$PacketSendEvent.getPacket() instanceof CPacketPlayerTryUseItemOnBlock)) {
            return;
        }
        final CPacketPlayerTryUseItemOnBlock oldPacket = (CPacketPlayerTryUseItemOnBlock)packetEvent$PacketSendEvent.getPacket();
        if (oldPacket.getPos().func_177956_o() < 255) {
            return;
        }
        if (oldPacket.getDirection() != EnumFacing.UP) {
            return;
        }
        BuildHeight.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(oldPacket.getPos(), EnumFacing.DOWN, oldPacket.getHand(), oldPacket.getFacingX(), oldPacket.getFacingY(), oldPacket.getFacingZ()));
        packetEvent$PacketSendEvent.setCanceled(true);
    }
}
