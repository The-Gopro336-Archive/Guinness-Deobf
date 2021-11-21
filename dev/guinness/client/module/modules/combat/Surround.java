package dev.guinness.client.module.modules.combat;

import net.minecraft.util.EnumFacing;
import dev.guinness.client.util.MessageUtil;
import dev.guinness.client.util.InventoryUtil;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.Vec3d;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import dev.guinness.client.util.ModuleUtil;
import dev.guinness.client.module.Category;
import net.minecraft.util.math.BlockPos;
import dev.guinness.client.module.Module;

public class Surround extends Module
{
    public BlockPos[] pos;
    
    public Surround() {
        super("Surround", Category.COMBAT, "Automatically places obsidian at your feet");
        this.pos = new BlockPos[] { new BlockPos(0, -1, 0), new BlockPos(1, 0, 0), new BlockPos(-1, 0, 0), new BlockPos(0, 0, 1), new BlockPos(0, 0, -1) };
    }
    
    @Override
    public void onEnable() {
        final Vec3d center = ModuleUtil.getCenter(Surround.mc.player.field_70165_t, Surround.mc.player.field_70163_u, Surround.mc.player.field_70161_v);
        Surround.mc.player.field_70159_w = Double.longBitsToDouble(Double.doubleToLongBits(1.0778163468252307E308) ^ 0x7FE32F8E7ADABAB2L);
        Surround.mc.player.field_70179_y = Double.longBitsToDouble(Double.doubleToLongBits(1.7150846326640401E308) ^ 0x7FEE878E9CE6F522L);
        Surround.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(center.x, center.y, center.z, true));
        Surround.mc.player.func_70107_b(center.x, center.y, center.z);
    }
    
    @Override
    public void onUpdate() {
        final int obsidian = InventoryUtil.find(Blocks.OBSIDIAN);
        if (obsidian == -1) {
            this.disable();
            MessageUtil.sendModuleErrorMessage(this, "No obsidian!");
            return;
        }
        final int delay = 0;
        for (final BlockPos block : this.pos) {
            final BlockPos surround = new BlockPos(Surround.mc.player.func_174791_d()).add(block.func_177958_n(), block.func_177956_o(), block.func_177952_p());
            if (Surround.mc.world.func_180495_p(surround).func_185904_a().isReplaceable()) {
                final int oldSlot = Surround.mc.player.field_71071_by.currentItem;
                Surround.mc.player.field_71071_by.currentItem = obsidian;
                ModuleUtil.placeBlock(surround.add(0, 1, 0), EnumFacing.DOWN);
                Surround.mc.player.field_71071_by.currentItem = oldSlot;
                return;
            }
        }
    }
}
