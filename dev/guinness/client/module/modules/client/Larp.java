package dev.guinness.client.module.modules.client;

import net.minecraft.network.Packet;
import dev.guinness.client.util.CPacketLarp;
import dev.guinness.client.module.Category;
import dev.guinness.client.module.Module;

public class Larp extends Module
{
    public Larp() {
        super("Larp", Category.CLIENT, "larp");
    }
    
    @Override
    public void onEnable() {
        Larp.mc.player.connection.sendPacket((Packet)new CPacketLarp(Larp.mc.player.func_70005_c_()));
        this.disable();
    }
}
