package dev.guinness.client.module.modules.combat;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.world.World;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.network.play.client.CPacketUseEntity;
import dev.guinness.client.event.events.PacketEvent$PacketSendEvent;
import dev.guinness.client.module.Category;
import dev.guinness.client.setting.SBoolean;
import dev.guinness.client.module.Module;

public class Criticals extends Module
{
    public static SBoolean crystals;
    
    public Criticals() {
        super("Criticals", Category.COMBAT, "Always performs critical hits");
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent$PacketSendEvent packetEvent$PacketSendEvent) {
        if (packetEvent$PacketSendEvent.getPacket() instanceof CPacketUseEntity) {
            if (((CPacketUseEntity)packetEvent$PacketSendEvent.getPacket()).getAction() == CPacketUseEntity.Action.ATTACK) {
                if (Criticals.mc.player.field_70122_E) {
                    if (((CPacketUseEntity)packetEvent$PacketSendEvent.getPacket()).getEntityFromWorld((World)Criticals.mc.world) instanceof EntityEnderCrystal) {
                        if (!(boolean)Criticals.crystals.getValue()) {
                            return;
                        }
                    }
                    Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.field_70165_t, Criticals.mc.player.field_70163_u + Double.longBitsToDouble(Double.doubleToLongBits(222.41091140162692) ^ 0x7FD254BF8FAAF151L), Criticals.mc.player.field_70161_v, false));
                    Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.field_70165_t, Criticals.mc.player.field_70163_u, Criticals.mc.player.field_70161_v, false));
                }
            }
        }
    }
    
    static {
        Criticals.crystals = new SBoolean("Crystals", false);
    }
}
