package dev.guinness.client.module.modules.combat;

import dev.guinness.client.util.ModuleUtil;
import net.minecraft.util.EnumFacing;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import dev.guinness.client.util.InventoryUtil;
import net.minecraft.init.Blocks;
import dev.guinness.client.mixin.mixins.ITimer;
import dev.guinness.client.module.Category;
import net.minecraft.util.math.BlockPos;
import dev.guinness.client.module.Module;

public class Burrow extends Module
{
    public int obsidian;
    public BlockPos oldPos;
    
    public Burrow() {
        super("Burrow", Category.COMBAT, "Instantly snaps you into a block.");
    }
    
    @Override
    public void onEnable() {
        ((ITimer)Burrow.mc.timer).setTickLength(Float.intBitsToFloat(Float.floatToIntBits(7.6539783f) ^ 0x7EB821A9));
        this.oldPos = null;
        this.oldPos = new BlockPos(Burrow.mc.player.func_174791_d());
        this.obsidian = InventoryUtil.find(Blocks.OBSIDIAN);
        if (this.obsidian == -1) {
            this.obsidian = InventoryUtil.find(Blocks.ENDER_CHEST);
        }
        if (Burrow.mc.world.func_180495_p(this.oldPos).func_185904_a().isReplaceable() && this.obsidian != -1) {
            Burrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Burrow.mc.player.field_70165_t, Burrow.mc.player.field_70163_u + Double.longBitsToDouble(Double.doubleToLongBits(193.2305168970483) ^ 0x7FB2C627C4F8BFCFL), Burrow.mc.player.field_70161_v, true));
            Burrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Burrow.mc.player.field_70165_t, Burrow.mc.player.field_70163_u + Double.longBitsToDouble(Double.doubleToLongBits(2.117699157332796) ^ 0x7FE8EB3A9909E02DL), Burrow.mc.player.field_70161_v, true));
            Burrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Burrow.mc.player.field_70165_t, Burrow.mc.player.field_70163_u + Double.longBitsToDouble(Double.doubleToLongBits(7.095940891250304) ^ 0x7FEC6746B41097A4L), Burrow.mc.player.field_70161_v, true));
            Burrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Burrow.mc.player.field_70165_t, Burrow.mc.player.field_70163_u + Double.longBitsToDouble(Double.doubleToLongBits(4.518640569720557) ^ 0x7FE0BB74AC41FD12L), Burrow.mc.player.field_70161_v, true));
            Burrow.mc.player.func_70107_b(Burrow.mc.player.field_70165_t, Burrow.mc.player.field_70163_u + Double.longBitsToDouble(Double.doubleToLongBits(13.886184696134357) ^ 0x7FD96DD82F1494D3L), Burrow.mc.player.field_70161_v);
            final int oldSlot = Burrow.mc.player.field_71071_by.currentItem;
            Burrow.mc.player.field_71071_by.currentItem = this.obsidian;
            ModuleUtil.placeBlock(this.oldPos, EnumFacing.DOWN);
            Burrow.mc.player.func_70107_b(Burrow.mc.player.field_70165_t, Burrow.mc.player.field_70163_u - Double.longBitsToDouble(Double.doubleToLongBits(41.39672932634363) ^ 0x7FB61AAA29F9AC27L), Burrow.mc.player.field_70161_v);
            Burrow.mc.player.field_71071_by.currentItem = oldSlot;
            Burrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Burrow.mc.player.field_70165_t, Burrow.mc.player.field_70163_u + Double.longBitsToDouble(Double.doubleToLongBits(1.8201520143493204) ^ 0x7FE11F57B7F60FC9L), Burrow.mc.player.field_70161_v, false));
            ((ITimer)Burrow.mc.timer).setTickLength(Float.intBitsToFloat(Float.floatToIntBits(0.054498866f) ^ 0x7F173A34));
            this.disable();
            return;
        }
        ((ITimer)Burrow.mc.timer).setTickLength(Float.intBitsToFloat(Float.floatToIntBits(0.010919384f) ^ 0x7E7AE737));
        this.disable();
    }
}
