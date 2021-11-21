package dev.guinness.client.module.modules.combat;

import net.minecraft.world.World;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.Vec3i;
import net.minecraft.block.BlockObsidian;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.Item;
import dev.guinness.client.util.ModuleUtil;
import net.minecraft.init.Items;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import dev.guinness.client.module.Category;
import dev.guinness.client.setting.SMode;
import dev.guinness.client.setting.SSlider;
import dev.guinness.client.setting.SBoolean;
import dev.guinness.client.module.Module;

public class Offhand extends Module
{
    public static SBoolean stopInGUI;
    public static SSlider minHealth;
    public static SSlider holeHealth;
    public static SBoolean swordGap;
    public static SMode mode;
    
    public Offhand() {
        super("Offhand", Category.COMBAT, "Various modes for offhand");
    }
    
    @Override
    public void onUpdate() {
        if (Offhand.stopInGUI.getValue()) {
            if (Offhand.mc.currentScreen != null) {
                return;
            }
        }
        final int itemSlot = this.getItemSlot();
        if (itemSlot == -1) {
            return;
        }
        Offhand.mc.playerController.windowClick(Offhand.mc.player.field_71069_bz.windowId, itemSlot, 0, ClickType.PICKUP, (EntityPlayer)Offhand.mc.player);
        Offhand.mc.playerController.windowClick(Offhand.mc.player.field_71069_bz.windowId, 45, 0, ClickType.PICKUP, (EntityPlayer)Offhand.mc.player);
        Offhand.mc.playerController.windowClick(Offhand.mc.player.field_71069_bz.windowId, itemSlot, 0, ClickType.PICKUP, (EntityPlayer)Offhand.mc.player);
    }
    
    public int getItemSlot() {
        Item itemToSearch = Items.TOTEM_OF_UNDYING;
        Label_1187: {
            if (Offhand.mc.player.func_110143_aJ() + Offhand.mc.player.func_110139_bj() > (double)Offhand.minHealth.getValue()) {
                if (Offhand.swordGap.getValue()) {
                    if (Offhand.mc.player.func_184614_ca().getItem() == Items.DIAMOND_SWORD) {
                        if (!ModuleUtil.is32k(Offhand.mc.player.func_184614_ca())) {
                            itemToSearch = Items.GOLDEN_APPLE;
                            break Label_1187;
                        }
                    }
                }
                final String s = (String)Offhand.mode.getValue();
                switch (s) {
                    case "Crystal": {
                        itemToSearch = Items.END_CRYSTAL;
                        break;
                    }
                    case "Gapple": {
                        itemToSearch = Items.GOLDEN_APPLE;
                        break;
                    }
                    case "Bed": {
                        itemToSearch = Items.BED;
                        break;
                    }
                }
            }
            else if (Offhand.mc.player.func_110143_aJ() + Offhand.mc.player.func_110139_bj() > (double)Offhand.holeHealth.getValue() && this.isInHole(Offhand.mc.player)) {
                if (Offhand.swordGap.getValue()) {
                    if (Offhand.mc.player.func_184614_ca().getItem() == Items.DIAMOND_SWORD) {
                        if (!ModuleUtil.is32k(Offhand.mc.player.func_184614_ca())) {
                            itemToSearch = Items.GOLDEN_APPLE;
                            break Label_1187;
                        }
                    }
                }
                final String s2 = (String)Offhand.mode.getValue();
                switch (s2) {
                    case "Crystal": {
                        itemToSearch = Items.END_CRYSTAL;
                        break;
                    }
                    case "Gapple": {
                        itemToSearch = Items.GOLDEN_APPLE;
                        break;
                    }
                    case "Bed": {
                        itemToSearch = Items.BED;
                        break;
                    }
                }
            }
            else {
                itemToSearch = Items.TOTEM_OF_UNDYING;
            }
        }
        if (Offhand.mc.player.func_184592_cb().getItem().equals(itemToSearch)) {
            return -1;
        }
        for (int i = 9; i < 36; ++i) {
            if (Offhand.mc.player.field_71071_by.getStackInSlot(i).getItem() == itemToSearch) {
                return (i < 9) ? (i + 36) : i;
            }
        }
        return -1;
    }
    
    public boolean isInHole(final EntityPlayer entityPlayer) {
        final BlockPos[] pos = { new BlockPos(1, 0, 0), new BlockPos(-1, 0, 0), new BlockPos(0, 0, 1), new BlockPos(0, 0, -1) };
        int count = 0;
        for (final BlockPos b : pos) {
            Label_0415: {
                if (!(Offhand.mc.world.func_180495_p(new BlockPos(entityPlayer.func_174791_d()).add((Vec3i)b)).getBlock() instanceof BlockObsidian)) {
                    if (Offhand.mc.world.func_180495_p(new BlockPos(entityPlayer.func_174791_d()).add((Vec3i)b)).getBlock() != Blocks.BEDROCK) {
                        break Label_0415;
                    }
                }
                ++count;
            }
        }
        return count == 4;
    }
    
    public static Offhand$BlockResistance getBlockResistance(final BlockPos blockPos) {
        if (Offhand.mc.world.func_175623_d(blockPos)) {
            return Offhand$BlockResistance.Blank;
        }
        if (Offhand.mc.world.func_180495_p(blockPos).getBlock().getBlockHardness(Offhand.mc.world.func_180495_p(blockPos), (World)Offhand.mc.world, blockPos) != Float.intBitsToFloat(Float.floatToIntBits(-12.682347f) ^ 0x7ECAEAE5) && !Offhand.mc.world.func_180495_p(blockPos).getBlock().equals(Blocks.OBSIDIAN) && !Offhand.mc.world.func_180495_p(blockPos).getBlock().equals(Blocks.ANVIL)) {
            if (!Offhand.mc.world.func_180495_p(blockPos).getBlock().equals(Blocks.ENCHANTING_TABLE)) {
                if (!Offhand.mc.world.func_180495_p(blockPos).getBlock().equals(Blocks.ENDER_CHEST)) {
                    return Offhand$BlockResistance.Breakable;
                }
            }
        }
        if (Offhand.mc.world.func_180495_p(blockPos).getBlock().equals(Blocks.OBSIDIAN) || Offhand.mc.world.func_180495_p(blockPos).getBlock().equals(Blocks.ANVIL) || Offhand.mc.world.func_180495_p(blockPos).getBlock().equals(Blocks.ENCHANTING_TABLE) || Offhand.mc.world.func_180495_p(blockPos).getBlock().equals(Blocks.ENDER_CHEST)) {
            return Offhand$BlockResistance.Resistant;
        }
        if (Offhand.mc.world.func_180495_p(blockPos).getBlock().equals(Blocks.BEDROCK)) {
            return Offhand$BlockResistance.Unbreakable;
        }
        return null;
    }
    
    static {
        Offhand.mode = new SMode("Mode", new String[] { "Totem", "Crystal", "Gapple" });
        Offhand.stopInGUI = new SBoolean("Stop in GUI", false);
        Offhand.swordGap = new SBoolean("SwordGap", false);
        Offhand.minHealth = new SSlider("Health", Double.longBitsToDouble(Double.doubleToLongBits(23.17565395611582) ^ 0x7FD72CF7A85CEE33L), Double.longBitsToDouble(Double.doubleToLongBits(0.9594873635520061) ^ 0x7FDCB41ED7EC3235L), Double.longBitsToDouble(Double.doubleToLongBits(0.024007426538054776) ^ 0x7FDA95671F5E4703L), 1);
        Offhand.holeHealth = new SSlider("HoleHealth", Double.longBitsToDouble(Double.doubleToLongBits(16.06827912815282) ^ 0x7FD0117ABDAE6A6FL), Double.longBitsToDouble(Double.doubleToLongBits(1.3466157067431714) ^ 0x7FED8BBCE94BDE9CL), Double.longBitsToDouble(Double.doubleToLongBits(0.053126383886362046) ^ 0x7FE93361A2B17E41L), 1);
    }
}
