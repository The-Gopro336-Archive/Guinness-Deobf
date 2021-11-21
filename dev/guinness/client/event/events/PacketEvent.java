package dev.guinness.client.event.events;

import net.minecraft.network.Packet;
import net.minecraftforge.fml.common.eventhandler.Event;

public class PacketEvent extends Event
{
    public Packet<?> packet;
    
    public PacketEvent(final Packet packet) {
        this.packet = (Packet<?>)packet;
    }
    
    public Packet getPacket() {
        return this.packet;
    }
}
