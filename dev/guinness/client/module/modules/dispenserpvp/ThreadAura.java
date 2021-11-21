package dev.guinness.client.module.modules.dispenserpvp;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import dev.guinness.client.event.events.TotemPopEvent;
import dev.guinness.client.util.RenderUtil;
import java.util.Iterator;
import net.minecraft.util.EnumHand;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.entity.Entity;
import dev.guinness.client.util.FriendUtil;
import dev.guinness.client.util.ModuleUtil;
import dev.guinness.client.module.Category;
import dev.guinness.client.setting.SBoolean;
import net.minecraft.entity.player.EntityPlayer;
import dev.guinness.client.setting.SSlider;
import dev.guinness.client.module.Module;

public class ThreadAura extends Module
{
    public static SSlider threads;
    public static EntityPlayer target;
    public static SSlider attackIterations;
    public static SBoolean renderTarget;
    
    public ThreadAura() {
        super("ThreadAura", Category.DISPENSERPVP, "Aura that runs in a seperate thread to rip through larpers");
    }
    
    @Override
    public void onUpdate() {
        if (!ModuleUtil.is32k(ThreadAura.mc.player.field_71071_by.getCurrentItem())) {
            return;
        }
        for (final EntityPlayer ep : ThreadAura.mc.world.field_73010_i) {
            if (ep == ThreadAura.mc.player) {
                continue;
            }
            if (FriendUtil.isFriend(ep.getName())) {
                continue;
            }
            if (ThreadAura.mc.player.func_70032_d(ep) <= Float.intBitsToFloat(Float.floatToIntBits(0.23242515f) ^ 0x7F4E00DC) && ep.func_110143_aJ() > Float.intBitsToFloat(Float.floatToIntBits(4.050985E36f) ^ 0x7C430C3F)) {
                ThreadAura.target = ep;
                ThreadAura.mc.player.connection.sendPacket((Packet)new CPacketUseEntity(ep));
                ThreadAura.mc.player.swingArm(EnumHand.MAIN_HAND);
            }
            else {
                ThreadAura.target = null;
            }
        }
    }
    
    @Override
    public void onRender3d() {
        if ((boolean)ThreadAura.renderTarget.getValue() && ThreadAura.target != null) {
            RenderUtil.drawCsgoEspOutline(ThreadAura.target);
        }
    }
    
    @SubscribeEvent
    public void onPop(final TotemPopEvent totemPopEvent) {
        if (ThreadAura.target == null) {
            return;
        }
        if (totemPopEvent.getPlayer() == ThreadAura.target) {
            for (int t = 0; t < ((Double)ThreadAura.threads.getValue()).intValue(); ++t) {
                new Thread(ThreadAura::lambda$onPop$0).start();
            }
        }
    }
    
    public static void lambda$onPop$0() {
        for (int i = 0; i < ((Double)ThreadAura.attackIterations.getValue()).intValue(); ++i) {
            ThreadAura.mc.player.connection.sendPacket((Packet)new CPacketUseEntity(ThreadAura.target));
            ThreadAura.mc.player.swingArm(EnumHand.MAIN_HAND);
        }
    }
    
    static {
        ThreadAura.renderTarget = new SBoolean("RenderTarget", true);
        ThreadAura.threads = new SSlider("Threads", Double.longBitsToDouble(Double.doubleToLongBits(5.425351563303174) ^ 0x7FE5B38F5C36C228L), Double.longBitsToDouble(Double.doubleToLongBits(0.3047174613440426) ^ 0x7FDB807DAABF8A27L), Double.longBitsToDouble(Double.doubleToLongBits(0.23294182437267252) ^ 0x7FE9D109A6C68DDCL), 0);
        ThreadAura.attackIterations = new SSlider("AttackIterations", Double.longBitsToDouble(Double.doubleToLongBits(5.132551029110262) ^ 0x7FE487BB74FC51A2L), Double.longBitsToDouble(Double.doubleToLongBits(1.937635913427586) ^ 0x7FEB008E83FBA007L), Double.longBitsToDouble(Double.doubleToLongBits(0.43474798613404547) ^ 0x7FE5D2E9379CA543L), 0);
    }
}
