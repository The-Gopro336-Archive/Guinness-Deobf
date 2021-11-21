package dev.guinness.client.util;

import java.util.Arrays;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.block.Block;
import java.util.List;

public class InventoryUtil implements Wrapper
{
    public static List<Block> shulkers;
    
    public static int find(final Item obj) {
        for (int i = 0; i < 9; ++i) {
            final ItemStack is = InventoryUtil.mc.player.field_71071_by.getStackInSlot(i);
            if (is != ItemStack.EMPTY) {
                final Item it = is.getItem();
                if (it.equals(obj)) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    public static int findInv(final Item obj) {
        for (int i = 0; i < 36; ++i) {
            final Item slot = InventoryUtil.mc.player.field_71071_by.getStackInSlot(i).getItem();
            if (slot.equals(obj)) {
                if (i < 9) {
                    i += 36;
                }
                return i;
            }
        }
        return -1;
    }
    
    public static int find(final Block obj) {
        for (int i = 0; i < 9; ++i) {
            final ItemStack is = InventoryUtil.mc.player.field_71071_by.getStackInSlot(i);
            if (is != ItemStack.EMPTY) {
                final Item it = is.getItem();
                if (it instanceof ItemBlock) {
                    if (((ItemBlock)it).getBlock().equals(obj)) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }
    
    public static int findInv(final Block obj) {
        for (int i = 0; i < 36; ++i) {
            final ItemStack is = InventoryUtil.mc.player.field_71071_by.getStackInSlot(i);
            if (is != ItemStack.EMPTY) {
                final Item it = is.getItem();
                if (it instanceof ItemBlock) {
                    if (((ItemBlock)it).getBlock().equals(obj)) {
                        if (i < 9) {
                            i += 36;
                        }
                        return i;
                    }
                }
            }
        }
        return -1;
    }
    
    static {
        InventoryUtil.shulkers = Arrays.asList(Blocks.BLACK_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.SILVER_SHULKER_BOX, Blocks.WHITE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX);
    }
}
