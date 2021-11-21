package dev.guinness.client.module.modules.dispenserpvp;

import dev.guinness.client.module.modules.exploit.XCarry;
import dev.guinness.client.util.RenderUtil;
import java.awt.Color;
import dev.guinness.client.util.ColorUtil;
import net.minecraft.network.play.server.SPacketOpenWindow;
import net.minecraft.network.play.server.SPacketCloseWindow;
import dev.guinness.client.event.events.PacketEvent$PacketReceiveEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.gui.inventory.GuiInventory;
import dev.guinness.client.module.modules.exploit.SecretClose;
import net.minecraft.client.gui.GuiHopper;
import java.util.function.Predicate;
import dev.guinness.client.module.ModuleManager;
import net.minecraft.network.play.client.CPacketCloseWindow;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import dev.guinness.client.event.events.PacketEvent$PacketSendEvent;
import dev.guinness.client.module.Category;
import net.minecraft.util.math.BlockPos;
import dev.guinness.client.setting.SSlider;
import dev.guinness.client.module.Module;

public class HopperRadius extends Module
{
    public static SSlider height;
    public BlockPos oldHopperPos;
    public double radius;
    public BlockPos hopperPos;
    public double wallHeight;
    
    public HopperRadius() {
        super("HopperRadius", Category.DISPENSERPVP, "Shows the radius around the hopper you are currently in");
    }
    
    @Override
    public void onUpdate() {
        if (this.hopperPos == null) {
            return;
        }
        if (!HopperRadius.mc.world.func_180495_p(this.hopperPos).getBlock().getLocalizedName().equalsIgnoreCase("Hopper") || HopperRadius.mc.player.func_174831_c(this.hopperPos) > Double.longBitsToDouble(Double.doubleToLongBits(0.925710585628769) ^ 0x7FBDDF6BCE5AC52FL)) {
            this.hopperPos = null;
        }
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent$PacketSendEvent packetEvent$PacketSendEvent) {
        if (packetEvent$PacketSendEvent.getPacket() instanceof CPacketPlayerTryUseItemOnBlock) {
            final CPacketPlayerTryUseItemOnBlock packet = (CPacketPlayerTryUseItemOnBlock)packetEvent$PacketSendEvent.getPacket();
            final BlockPos packetPos = packet.getPos();
            if (HopperRadius.mc.world.func_180495_p(packetPos).getBlock().getLocalizedName().equalsIgnoreCase("Hopper")) {
                this.wallHeight = Double.longBitsToDouble(Double.doubleToLongBits(2.226615095116189E307) ^ 0x7FBFB542DC55A837L);
                this.hopperPos = packetPos;
            }
        }
        if (packetEvent$PacketSendEvent.getPacket() instanceof CPacketCloseWindow) {
            if (ModuleManager.getModule(HopperRadius::lambda$onPacketSend$0).isEnabled() && (SecretClose.lastGui instanceof GuiHopper || HopperRadius.mc.currentScreen instanceof GuiHopper)) {
                return;
            }
            Label_0497: {
                if (ModuleManager.getModule(HopperRadius::lambda$onPacketSend$1).isEnabled()) {
                    if (!(SecretClose.lastGui instanceof GuiInventory)) {
                        if (!(HopperRadius.mc.currentScreen instanceof GuiInventory)) {
                            break Label_0497;
                        }
                    }
                    return;
                }
            }
            this.hopperPos = null;
        }
    }
    
    @SubscribeEvent
    public void onPacketReceive(final PacketEvent$PacketReceiveEvent packetEvent$PacketReceiveEvent) {
        if (!(packetEvent$PacketReceiveEvent.getPacket() instanceof SPacketCloseWindow)) {
            if (packetEvent$PacketReceiveEvent.getPacket() instanceof SPacketOpenWindow) {
                if (((SPacketOpenWindow)packetEvent$PacketReceiveEvent.getPacket()).getWindowTitle().getUnformattedText().equalsIgnoreCase("Item Hopper")) {
                    return;
                }
                this.hopperPos = null;
            }
        }
    }
    
    @Override
    public void onRender3d() {
        if (this.wallHeight < (double)HopperRadius.height.getValue()) {
            this.wallHeight += Double.longBitsToDouble(Double.doubleToLongBits(3751.2945874163925) ^ 0x7FD9347793877A0BL) * (double)HopperRadius.height.getValue();
        }
        else if (this.wallHeight > (double)HopperRadius.height.getValue()) {
            this.wallHeight -= Double.longBitsToDouble(Double.doubleToLongBits(114.68668265750983) ^ 0x7FD8D113DC7F3B77L);
        }
        if (this.hopperPos != null) {
            RenderUtil.drawCircle(this.hopperPos, Double.longBitsToDouble(Double.doubleToLongBits(0.14553988619673233) ^ 0x7FE2A10D0DBD4061L), this.wallHeight, new Color(ColorUtil.BESTCOLOR(0, 255)), new Color(ColorUtil.BESTCOLOR(10, 255)));
            this.oldHopperPos = this.hopperPos;
            this.radius = Double.longBitsToDouble(Double.doubleToLongBits(0.14070361133713452) ^ 0x7FE20293708FA091L);
            return;
        }
        if (this.hopperPos == null && this.oldHopperPos != null) {
            RenderUtil.drawCircle(this.oldHopperPos, this.radius, this.wallHeight, new Color(ColorUtil.BESTCOLOR(0, 255)), new Color(ColorUtil.BESTCOLOR(10, 255)));
            if (this.wallHeight > Double.longBitsToDouble(Double.doubleToLongBits(1.1989844897406259E308) ^ 0x7FE557B6C1188A7BL)) {
                this.wallHeight -= Double.longBitsToDouble(Double.doubleToLongBits(219.7551656050837) ^ 0x7FD2E1B3C896855BL);
                return;
            }
            if (this.radius > Double.longBitsToDouble(Double.doubleToLongBits(6.522680943073321E306) ^ 0x7FA293C429F2655FL)) {
                this.radius -= Double.longBitsToDouble(Double.doubleToLongBits(90.1592080629349) ^ 0x7FEF13A9EE9A7DBDL);
            }
            else {
                this.radius = Double.longBitsToDouble(Double.doubleToLongBits(7.96568863695466E307) ^ 0x7FDC5BD9D9AD2AC5L);
            }
        }
    }
    
    public static boolean lambda$onPacketSend$1(final Module module) {
        return module.getClass().equals(XCarry.class);
    }
    
    public static boolean lambda$onPacketSend$0(final Module module) {
        return module.getClass().equals(SecretClose.class);
    }
    
    static {
        HopperRadius.height = new SSlider("Height", Double.longBitsToDouble(Double.doubleToLongBits(1.5694679953511143E308) ^ 0x7FEBEFFD39BFCE2CL), Double.longBitsToDouble(Double.doubleToLongBits(20.233149527863684) ^ 0x7FE7089C9CCE73A8L), Double.longBitsToDouble(Double.doubleToLongBits(0.4370423320361358) ^ 0x7FEFF88066C40487L), 1);
    }
}
