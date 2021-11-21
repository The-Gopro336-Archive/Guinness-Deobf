package dev.guinness.client.util;

import net.minecraft.network.INetHandler;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.Packet;

public class CPacketLarp implements Packet
{
    public String name;
    
    public CPacketLarp() {
    }
    
    public CPacketLarp(final String name) {
        this.name = name;
    }
    
    public void readPacketData(final PacketBuffer packetBuffer) {
        this.name = packetBuffer.readString(this.name.length());
    }
    
    public void writePacketData(final PacketBuffer packetBuffer) {
        packetBuffer.writeString(this.name);
    }
    
    public void processPacket(final INetHandlerPlayServer netHandlerPlayServer) {
        System.out.println("larp packet sent");
    }
    
    public String getName() {
        return this.name;
    }
    
    public void processPacket(final INetHandler netHandler) {
        this.processPacket((INetHandlerPlayServer)netHandler);
    }
}
