package dev.guinness.client.module.modules.dispenserpvp;

import dev.guinness.client.module.modules.exploit.SecretClose;
import net.minecraft.block.material.Material;
import java.util.function.Predicate;
import dev.guinness.client.module.ModuleManager;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.GuiHopper;
import net.minecraft.block.BlockShulkerBox;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.client.gui.inventory.GuiDispenser;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumHand;
import dev.guinness.client.util.ModuleUtil;
import net.minecraft.util.EnumFacing;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.block.Block;
import dev.guinness.client.util.InventoryUtil;
import net.minecraft.init.Blocks;
import dev.guinness.client.util.MessageUtil;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.MinecraftForge;
import dev.guinness.client.module.Category;
import dev.guinness.client.setting.SSlider;
import dev.guinness.client.setting.SMode;
import dev.guinness.client.setting.SBoolean;
import net.minecraft.util.math.BlockPos;
import dev.guinness.client.module.Module;

public class Manual32k extends Module
{
    public BlockPos target;
    public int redstone;
    public static int stage;
    public static BlockPos targetfront;
    public int hopper;
    public static SBoolean msg;
    public int shulker;
    public int dispenser;
    public int obsidian;
    public int tickdelay;
    public static SMode mode;
    public static SSlider timeout;
    public static SBoolean bypass;
    public static SBoolean autodisable;
    
    public Manual32k() {
        super("Manual32k", Category.DISPENSERPVP, "Places a 32k where you aim");
    }
    
    @Override
    public void onEnable() {
        if (this.Null()) {
            this.disable();
            return;
        }
        MinecraftForge.EVENT_BUS.register(this);
        Manual32k.targetfront = null;
        Manual32k.stage = -1;
        this.tickdelay = 0;
        if (Manual32k.msg.getValue()) {
            MessageUtil.sendClientMessage(TextFormatting.BLUE + "[Auto32k] " + TextFormatting.GREEN + "Enabled!");
        }
        this.hopper = InventoryUtil.find(Blocks.HOPPER);
        this.shulker = -1;
        for (int i = 0; i < 9; ++i) {
            final ItemStack stack = Manual32k.mc.player.field_71071_by.getStackInSlot(i);
            if (stack != ItemStack.EMPTY) {
                if (stack.getItem() instanceof ItemBlock) {
                    final Block block = ((ItemBlock)stack.getItem()).getBlock();
                    if (InventoryUtil.shulkers.contains(block)) {
                        this.shulker = i;
                    }
                }
            }
        }
        if (((String)Manual32k.mode.getValue()).equalsIgnoreCase("dispenser")) {
            this.obsidian = InventoryUtil.find(Blocks.OBSIDIAN);
            this.dispenser = InventoryUtil.find(Blocks.DISPENSER);
            this.redstone = InventoryUtil.find(Blocks.REDSTONE_BLOCK);
        }
        this.target = null;
        if (this.obsidian == -1) {
            if (((String)Manual32k.mode.getValue()).equalsIgnoreCase("dispenser")) {
                MessageUtil.sendClientMessage(TextFormatting.BLUE + "[Auto32k] " + TextFormatting.GRAY + "Missing Obsidian!");
                this.disable();
                return;
            }
        }
        if (this.dispenser == -1 && ((String)Manual32k.mode.getValue()).equalsIgnoreCase("dispenser")) {
            MessageUtil.sendClientMessage(TextFormatting.BLUE + "[Auto32k] " + TextFormatting.GRAY + "Missing Dispenser!");
            this.disable();
            return;
        }
        if (this.redstone == -1) {
            if (((String)Manual32k.mode.getValue()).equalsIgnoreCase("dispenser")) {
                MessageUtil.sendClientMessage(TextFormatting.BLUE + "[Auto32k] " + TextFormatting.GRAY + "Missing Redstone Block!");
                this.disable();
                return;
            }
        }
        if (this.hopper == -1) {
            MessageUtil.sendClientMessage(TextFormatting.BLUE + "[Auto32k] " + TextFormatting.GRAY + "Missing Hopper!");
            this.disable();
            return;
        }
        if (this.shulker == -1) {
            MessageUtil.sendClientMessage(TextFormatting.BLUE + "[Auto32k] " + TextFormatting.GRAY + "Missing Shulker Box!");
            this.disable();
            return;
        }
        if (Manual32k.mc.objectMouseOver == null || Manual32k.mc.objectMouseOver.getBlockPos() == null || Manual32k.mc.objectMouseOver.getBlockPos().up() == null) {
            MessageUtil.sendClientMessage(TextFormatting.BLUE + "[Auto32k] " + TextFormatting.GRAY + "Not a valid target!");
            this.disable();
            return;
        }
        this.target = Manual32k.mc.objectMouseOver.getBlockPos();
        Manual32k.targetfront = this.target.offset(Manual32k.mc.player.func_174811_aO().getOpposite());
        if (((String)Manual32k.mode.getValue()).equalsIgnoreCase("dispenser")) {
            Manual32k.stage = 0;
        }
        else if (((String)Manual32k.mode.getValue()).equalsIgnoreCase("hopper")) {
            Manual32k.stage = 5;
        }
    }
    
    @Override
    public void onUpdate() {
        if (this.Null()) {
            return;
        }
        ++this.tickdelay;
        if (Manual32k.stage == 0) {
            if (Manual32k.mc.world.func_180495_p(Manual32k.targetfront).func_185904_a().isReplaceable()) {
                Manual32k.mc.player.field_71071_by.currentItem = this.dispenser;
                ModuleUtil.placeBlock(this.target.add(0, 1, 0), EnumFacing.DOWN, true);
                Manual32k.mc.player.field_71071_by.currentItem = this.shulker;
                Manual32k.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(this.target.add(0, 1, 0), EnumFacing.DOWN, EnumHand.MAIN_HAND, Float.intBitsToFloat(Float.floatToIntBits(1.4598103E38f) ^ 0x7EDBA5D9), Float.intBitsToFloat(Float.floatToIntBits(1.8464406E38f) ^ 0x7F0AE927), Float.intBitsToFloat(Float.floatToIntBits(2.3622866E38f) ^ 0x7F31B7FC)));
                this.target = this.target.add(0, -1, 0);
                Manual32k.targetfront = Manual32k.targetfront.add(0, -1, 0);
                Manual32k.stage = 1;
                return;
            }
            if (!Manual32k.mc.world.func_180495_p(Manual32k.targetfront).func_185904_a().isReplaceable()) {
                Manual32k.mc.player.field_71071_by.currentItem = this.obsidian;
                ModuleUtil.placeBlock(this.target.add(0, 1, 0), EnumFacing.DOWN, true);
                Manual32k.mc.player.field_71071_by.currentItem = this.dispenser;
                ModuleUtil.placeBlock(this.target.add(0, 2, 0), EnumFacing.DOWN, true);
                Manual32k.mc.player.field_71071_by.currentItem = this.shulker;
                Manual32k.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(this.target.add(0, 2, 0), EnumFacing.DOWN, EnumHand.MAIN_HAND, Float.intBitsToFloat(Float.floatToIntBits(2.5178906E38f) ^ 0x7F3D6CCF), Float.intBitsToFloat(Float.floatToIntBits(1.707817E38f) ^ 0x7F007B5C), Float.intBitsToFloat(Float.floatToIntBits(1.8566009E38f) ^ 0x7F0BACD5)));
                Manual32k.stage = 1;
                return;
            }
        }
        if (Manual32k.stage == 1) {
            if (!(Manual32k.mc.currentScreen instanceof GuiDispenser)) {
                if (this.tickdelay > (double)Manual32k.timeout.getValue() && (boolean)Manual32k.autodisable.getValue()) {
                    MessageUtil.sendClientMessage("Tick Timeout!");
                    this.disable();
                }
                return;
            }
            else {
                Manual32k.mc.playerController.windowClick(Manual32k.mc.player.field_71070_bA.windowId, 4, this.shulker, ClickType.SWAP, (EntityPlayer)Manual32k.mc.player);
                Manual32k.mc.player.closeScreen();
                final BlockPos attempt1 = this.target.add(0, 3, 0);
                final BlockPos attempt2 = this.target.add(0, 2, 0).offset(Manual32k.mc.player.func_174811_aO().rotateY());
                final BlockPos attempt3 = this.target.add(0, 2, 0).offset(Manual32k.mc.player.func_174811_aO().rotateYCCW());
                final BlockPos attempt4 = this.target.add(0, 2, 0).offset(Manual32k.mc.player.func_174811_aO());
                final EnumFacing towardplayer = Manual32k.mc.player.func_174811_aO().getOpposite();
                final EnumFacing rightside = Manual32k.mc.player.func_174811_aO().rotateY().getOpposite();
                final EnumFacing leftside = Manual32k.mc.player.func_174811_aO().rotateYCCW().getOpposite();
                final Material block1 = Manual32k.mc.world.func_180495_p(attempt1).func_185904_a();
                final Material block2 = Manual32k.mc.world.func_180495_p(attempt2).func_185904_a();
                final Material block3 = Manual32k.mc.world.func_180495_p(attempt3).func_185904_a();
                final Material block4 = Manual32k.mc.world.func_180495_p(attempt4).func_185904_a();
                Manual32k.mc.player.field_71071_by.currentItem = this.redstone;
                if (block1.isReplaceable()) {
                    ModuleUtil.placeBlock(attempt1, EnumFacing.DOWN, true);
                    Manual32k.stage = 2;
                }
                if (block2.isReplaceable() && !block1.isReplaceable()) {
                    ModuleUtil.placeBlock(attempt2, rightside, true);
                    Manual32k.stage = 2;
                }
                if (block3.isReplaceable()) {
                    if (!block1.isReplaceable()) {
                        if (!block2.isReplaceable()) {
                            ModuleUtil.placeBlock(attempt3, leftside, true);
                            Manual32k.stage = 2;
                        }
                    }
                }
                if (block4.isReplaceable() && !block1.isReplaceable() && !block2.isReplaceable() && !block3.isReplaceable()) {
                    ModuleUtil.placeBlock(attempt4, towardplayer, true);
                    Manual32k.stage = 2;
                }
                if (!block1.isReplaceable() && !block2.isReplaceable() && !block3.isReplaceable() && !block4.isReplaceable()) {
                    MessageUtil.sendClientMessage("No viable redstone place targets!");
                    this.disable();
                    return;
                }
            }
        }
        if (Manual32k.stage == 2) {
            Manual32k.mc.player.field_71071_by.currentItem = this.hopper;
            if (!(Manual32k.mc.world.func_180495_p(Manual32k.targetfront.add(0, 2, 0)).getBlock() instanceof BlockShulkerBox)) {
                if (this.tickdelay > (double)Manual32k.timeout.getValue()) {
                    this.disable();
                }
                return;
            }
            else {
                ModuleUtil.placeBlock(Manual32k.targetfront.add(0, 1, 0), Manual32k.mc.player.func_174811_aO(), false);
                Manual32k.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(Manual32k.targetfront.add(0, 1, 0), EnumFacing.DOWN, EnumHand.MAIN_HAND, Float.intBitsToFloat(Float.floatToIntBits(3.2959972E38f) ^ 0x7F77F699), Float.intBitsToFloat(Float.floatToIntBits(1.1577955E38f) ^ 0x7EAE34A9), Float.intBitsToFloat(Float.floatToIntBits(3.247007E38f) ^ 0x7F744715)));
                Manual32k.mc.player.field_71071_by.currentItem = this.shulker;
                Manual32k.stage = 3;
            }
        }
        if (Manual32k.stage == 3) {
            if (this.tickdelay > (double)Manual32k.timeout.getValue()) {
                this.disable();
                return;
            }
            if (!(Manual32k.mc.currentScreen instanceof GuiHopper)) {
                return;
            }
            if (((GuiContainer)Manual32k.mc.currentScreen).inventorySlots.getSlot(0).getStack().isEmpty()) {
                return;
            }
            Manual32k.mc.playerController.windowClick(Manual32k.mc.player.field_71070_bA.windowId, 0, Manual32k.mc.player.field_71071_by.currentItem, ClickType.SWAP, (EntityPlayer)Manual32k.mc.player);
            Manual32k.stage = 4;
        }
        if (Manual32k.stage == 4) {
            if (!(Manual32k.mc.currentScreen instanceof GuiHopper)) {
                this.disable();
                return;
            }
            if (Manual32k.bypass.getValue()) {
                if (!ModuleManager.getModule(Manual32k::lambda$onUpdate$1).isEnabled()) {
                    ModuleManager.getModule(Manual32k::lambda$onUpdate$2).enable();
                }
                Manual32k.mc.player.closeScreen();
                if (!ModuleManager.getModule(Manual32k::lambda$onUpdate$3).isEnabled()) {
                    ModuleManager.getModule(Manual32k::lambda$onUpdate$4).enable();
                }
                this.disable();
            }
        }
        if (Manual32k.stage == 5) {
            Manual32k.mc.player.field_71071_by.currentItem = this.hopper;
            if (Manual32k.mc.world.func_180495_p(this.target).func_185904_a().isReplaceable()) {
                ModuleUtil.placeBlock(this.target, EnumFacing.DOWN, true);
            }
            else {
                ModuleUtil.placeBlock(this.target = this.target.add(0, 1, 0), EnumFacing.DOWN, true);
            }
            Manual32k.stage = 6;
        }
        if (Manual32k.stage == 6) {
            Manual32k.mc.player.field_71071_by.currentItem = this.shulker;
            ModuleUtil.placeBlock(this.target.add(0, 1, 0), EnumFacing.DOWN, false);
            Manual32k.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(this.target, EnumFacing.DOWN, EnumHand.MAIN_HAND, Float.intBitsToFloat(Float.floatToIntBits(1.3574728E38f) ^ 0x7ECC3FF3), Float.intBitsToFloat(Float.floatToIntBits(2.7638736E37f) ^ 0x7DA65837), Float.intBitsToFloat(Float.floatToIntBits(3.1492177E38f) ^ 0x7F6CEBBA)));
            if (!(Manual32k.mc.currentScreen instanceof GuiHopper) && this.tickdelay > (double)Manual32k.timeout.getValue()) {
                this.disable();
                return;
            }
            Manual32k.stage = 7;
        }
        if (Manual32k.stage == 7) {
            if (this.tickdelay > (double)Manual32k.timeout.getValue()) {
                this.disable();
                return;
            }
            if (!(Manual32k.mc.currentScreen instanceof GuiHopper)) {
                return;
            }
            if (((GuiContainer)Manual32k.mc.currentScreen).inventorySlots.getSlot(0).getStack().isEmpty()) {
                return;
            }
            Manual32k.mc.playerController.windowClick(Manual32k.mc.player.field_71070_bA.windowId, 0, Manual32k.mc.player.field_71071_by.currentItem, ClickType.SWAP, (EntityPlayer)Manual32k.mc.player);
            Manual32k.stage = 4;
        }
    }
    
    @Override
    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister(this);
        if (this.Null()) {
            return;
        }
        if (Manual32k.msg.getValue()) {
            MessageUtil.sendClientMessage(TextFormatting.BLUE + "[Auto32k] " + TextFormatting.RED + "Disabled!");
        }
        Manual32k.stage = -1;
        this.tickdelay = 0;
    }
    
    public static boolean lambda$onUpdate$4(final Module module) {
        return module.getClass().equals(SecretClose.class);
    }
    
    public static boolean lambda$onUpdate$3(final Module module) {
        return module.getClass().equals(SecretClose.class);
    }
    
    public static boolean lambda$onUpdate$2(final Module module) {
        return module.getClass().equals(SecretClose.class);
    }
    
    public static boolean lambda$onUpdate$1(final Module module) {
        return module.getClass().equals(SecretClose.class);
    }
    
    public static boolean lambda$static$0() {
        return (boolean)Manual32k.autodisable.getValue();
    }
    
    static {
        Manual32k.mode = new SMode("Mode", new String[] { "Dispenser", "Hopper" });
        Manual32k.bypass = new SBoolean("Illegal Bypass", false);
        Manual32k.msg = new SBoolean("Info Messages", true);
        Manual32k.autodisable = new SBoolean("Auto-Disable", true);
        Manual32k.timeout = new SSlider(Manual32k::lambda$static$0, "Disable Ticks", Double.longBitsToDouble(Double.doubleToLongBits(1.6472800328902195) ^ 0x7FC45B424EC9E073L), Double.longBitsToDouble(Double.doubleToLongBits(0.09344364034283094) ^ 0x7FE66BEC234AA941L), Double.longBitsToDouble(Double.doubleToLongBits(0.06663936550068583) ^ 0x7FE80F4707739E70L), 0);
    }
}
