package dev.guinness.client.module.modules.combat;

import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.network.Packet;
import net.minecraft.util.math.BlockPos;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.item.ItemBow;
import dev.guinness.client.module.Category;
import dev.guinness.client.module.Module;

public class BowSpam extends Module
{
    public BowSpam() {
        super("BowSpam", Category.COMBAT, "Auto spams bows while holding right click");
    }
    
    @Override
    public void onUpdate() {
        if (BowSpam.mc.player.func_184614_ca().getItem() instanceof ItemBow) {
            if (BowSpam.mc.player.isHandActive()) {
                if (BowSpam.mc.player.func_184612_cw() >= 3) {
                    BowSpam.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, BowSpam.mc.player.func_174811_aO()));
                    BowSpam.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItem(BowSpam.mc.player.getActiveHand()));
                    BowSpam.mc.player.func_184597_cx();
                }
            }
        }
    }
}
