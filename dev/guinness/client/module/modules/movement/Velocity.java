package dev.guinness.client.module.modules.movement;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import dev.guinness.client.mixin.mixins.ISPacketExplosion;
import net.minecraft.network.play.server.SPacketExplosion;
import dev.guinness.client.mixin.mixins.ISPacketEntityVelocity;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import dev.guinness.client.event.events.PacketEvent$PacketReceiveEvent;
import dev.guinness.client.module.Category;
import dev.guinness.client.setting.SSlider;
import dev.guinness.client.module.Module;

public class Velocity extends Module
{
    public static SSlider h;
    public static SSlider v;
    
    public Velocity() {
        super("Velocity", Category.MOVEMENT, "Modifies any knockback that you take");
    }
    
    @SubscribeEvent
    public void onPacketReceive(final PacketEvent$PacketReceiveEvent packetEvent$PacketReceiveEvent) {
        if (packetEvent$PacketReceiveEvent.getPacket() instanceof SPacketEntityVelocity) {
            final SPacketEntityVelocity packet = (SPacketEntityVelocity)packetEvent$PacketReceiveEvent.getPacket();
            if ((double)Velocity.h.getValue() + (double)Velocity.v.getValue() == Double.longBitsToDouble(Double.doubleToLongBits(1.5517016067231624E308) ^ 0x7FEB9F0752065DD9L)) {
                packetEvent$PacketReceiveEvent.setCanceled(true);
            }
            ((ISPacketEntityVelocity)packet).setMotionX((int)(((ISPacketEntityVelocity)packet).getMotionX() / 100 * (double)Velocity.h.getValue()));
            ((ISPacketEntityVelocity)packet).setMotionZ((int)(((ISPacketEntityVelocity)packet).getMotionZ() / 100 * (double)Velocity.h.getValue()));
            ((ISPacketEntityVelocity)packet).setMotionY((int)(((ISPacketEntityVelocity)packet).getMotionY() / 100 * (double)Velocity.v.getValue()));
        }
        if (packetEvent$PacketReceiveEvent.getPacket() instanceof SPacketExplosion) {
            final SPacketExplosion packet2 = (SPacketExplosion)packetEvent$PacketReceiveEvent.getPacket();
            if ((double)Velocity.h.getValue() + (double)Velocity.v.getValue() == Double.longBitsToDouble(Double.doubleToLongBits(3.857517190957645E307) ^ 0x7FCB776839DD7AA3L)) {
                packetEvent$PacketReceiveEvent.setCanceled(true);
            }
            ((ISPacketExplosion)packet2).setMotionX((float)(int)(((ISPacketExplosion)packet2).getMotionX() / Float.intBitsToFloat(Float.floatToIntBits(0.11086116f) ^ 0x7F2B0B2D) * (double)Velocity.h.getValue()));
            ((ISPacketExplosion)packet2).setMotionZ((float)(int)(((ISPacketExplosion)packet2).getMotionZ() / Float.intBitsToFloat(Float.floatToIntBits(0.112380676f) ^ 0x7F2E27D7) * (double)Velocity.h.getValue()));
            ((ISPacketExplosion)packet2).setMotionY((float)(int)(((ISPacketExplosion)packet2).getMotionY() / Float.intBitsToFloat(Float.floatToIntBits(0.011809223f) ^ 0x7E897B79) * (double)Velocity.v.getValue()));
        }
    }
    
    static {
        Velocity.h = new SSlider("Horizontal %", Double.longBitsToDouble(Double.doubleToLongBits(7.839259284184216E307) ^ 0x7FDBE89FDF19AF79L), Double.longBitsToDouble(Double.doubleToLongBits(1.0751325543652264E308) ^ 0x7FE323539F60DED2L), Double.longBitsToDouble(Double.doubleToLongBits(0.08921565597429412) ^ 0x7FEFD6D654B36656L), 2);
        Velocity.v = new SSlider("Vertical %", Double.longBitsToDouble(Double.doubleToLongBits(1.4450221292434694E308) ^ 0x7FE9B8E539F8376BL), Double.longBitsToDouble(Double.doubleToLongBits(4.430131979529628E306) ^ 0x7F993C206691FC3FL), Double.longBitsToDouble(Double.doubleToLongBits(0.07807622249819762) ^ 0x7FEAFCCDA639982CL), 2);
    }
}
