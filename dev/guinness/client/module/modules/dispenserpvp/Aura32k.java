package dev.guinness.client.module.modules.dispenserpvp;

import java.util.Iterator;
import net.minecraft.util.EnumHand;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.entity.Entity;
import dev.guinness.client.util.FriendUtil;
import net.minecraft.entity.player.EntityPlayer;
import dev.guinness.client.util.ModuleUtil;
import dev.guinness.client.module.Category;
import dev.guinness.client.module.Module;

public class Aura32k extends Module
{
    public Aura32k() {
        super("32kAura", Category.DISPENSERPVP, "High CPS killaura for 32k weapons");
    }
    
    @Override
    public void onUpdate() {
        if (!ModuleUtil.is32k(Aura32k.mc.player.field_71071_by.getCurrentItem())) {
            return;
        }
        for (final EntityPlayer ep : Aura32k.mc.world.field_73010_i) {
            if (ep == Aura32k.mc.player) {
                continue;
            }
            if (FriendUtil.isFriend(ep.getName())) {
                continue;
            }
            if (Aura32k.mc.player.func_70032_d(ep) > Float.intBitsToFloat(Float.floatToIntBits(0.14756651f) ^ 0x7F371BAD)) {
                continue;
            }
            if (ep.func_110143_aJ() <= Float.intBitsToFloat(Float.floatToIntBits(6.259559E37f) ^ 0x7E3C5DE3)) {
                continue;
            }
            Aura32k.mc.player.connection.sendPacket((Packet)new CPacketUseEntity(ep));
            Aura32k.mc.player.swingArm(EnumHand.MAIN_HAND);
        }
    }
}
