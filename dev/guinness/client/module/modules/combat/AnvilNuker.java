package dev.guinness.client.module.modules.combat;

import net.minecraft.block.BlockAnvil;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.util.EnumHand;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import dev.guinness.client.util.ModuleUtil;
import dev.guinness.client.module.Category;
import dev.guinness.client.setting.SBoolean;
import dev.guinness.client.setting.SMode;
import net.minecraft.util.math.BlockPos;
import dev.guinness.client.setting.SSlider;
import dev.guinness.client.module.Module;

public class AnvilNuker extends Module
{
    public static SSlider range;
    public boolean mining;
    public int prevSlot;
    public BlockPos pos;
    public static SMode mode;
    public static SBoolean rotate;
    
    public AnvilNuker() {
        super("AnvilNuker", Category.DISPENSERPVP, "Automatically mines anvils around you.");
        this.prevSlot = -1;
        this.mining = false;
    }
    
    @Override
    public void onUpdate() {
        if (this.Null()) {
            return;
        }
        final BlockPos pos = this.findAnvils();
        if (pos != null) {
            if (!this.mining) {
                this.prevSlot = AnvilNuker.mc.player.field_71071_by.currentItem;
                this.mining = true;
            }
            if (AnvilNuker.rotate.getValue()) {
                ModuleUtil.rotateClient(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
            }
            int newSlot = -1;
            while (true) {
                for (int i = 0; i < 9; ++i) {
                    final ItemStack stack = AnvilNuker.mc.player.field_71071_by.getStackInSlot(i);
                    if (stack != ItemStack.EMPTY) {
                        if (stack.getItem() instanceof ItemPickaxe) {
                            newSlot = i;
                            if (newSlot != -1) {
                                AnvilNuker.mc.player.field_71071_by.currentItem = newSlot;
                            }
                            if (!((String)AnvilNuker.mode.getValue()).equalsIgnoreCase("Packet")) {
                                AnvilNuker.mc.playerController.onPlayerDamageBlock(pos, AnvilNuker.mc.player.func_174811_aO());
                                AnvilNuker.mc.player.swingArm(EnumHand.MAIN_HAND);
                            }
                            else {
                                AnvilNuker.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, pos, AnvilNuker.mc.player.func_174811_aO()));
                                AnvilNuker.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, pos, AnvilNuker.mc.player.func_174811_aO()));
                            }
                            return;
                        }
                    }
                }
                continue;
            }
        }
        if (this.prevSlot != -1) {
            AnvilNuker.mc.player.field_71071_by.currentItem = this.prevSlot;
            this.prevSlot = -1;
            this.mining = false;
        }
    }
    
    public BlockPos findAnvils() {
        this.pos = null;
        for (int r = ((Double)AnvilNuker.range.getValue()).intValue(), x = -r; x < r; ++x) {
            for (int y = -r; y < r; ++y) {
                for (int z = -r; z < r; ++z) {
                    if (x != 0 || y != 0 || z != 0) {
                        final BlockPos anvil = new BlockPos(AnvilNuker.mc.player.func_174791_d()).add(x, y, z);
                        if (AnvilNuker.mc.world.func_180495_p(anvil).getBlock() instanceof BlockAnvil) {
                            this.pos = anvil;
                        }
                    }
                }
            }
        }
        return this.pos;
    }
    
    static {
        AnvilNuker.rotate = new SBoolean("Rotate", false);
        AnvilNuker.mode = new SMode("Break Mode", new String[] { "Packet", "Vanilla" });
        AnvilNuker.range = new SSlider("Range", Double.longBitsToDouble(Double.doubleToLongBits(6.904505582081193) ^ 0x7FEB9E36B6185A49L), Double.longBitsToDouble(Double.doubleToLongBits(0.061629456262791975) ^ 0x7FB78DE5663CE787L), Double.longBitsToDouble(Double.doubleToLongBits(0.858635873109073) ^ 0x7FC979F1F045A0C3L), 1);
    }
}
