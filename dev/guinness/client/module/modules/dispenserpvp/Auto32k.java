package dev.guinness.client.module.modules.dispenserpvp;

import dev.guinness.client.module.modules.exploit.SecretClose;
import dev.guinness.client.mixin.mixins.ICPacketPlayer;
import dev.guinness.client.event.events.PacketEvent$PacketSendEvent;
import dev.guinness.client.event.events.ModelBipedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import dev.guinness.client.util.RotationUtil;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.BlockAir;
import java.util.function.Predicate;
import dev.guinness.client.module.ModuleManager;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.GuiHopper;
import net.minecraft.block.BlockShulkerBox;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.client.gui.inventory.GuiDispenser;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import dev.guinness.client.util.ModuleUtil;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.block.Block;
import dev.guinness.client.util.InventoryUtil;
import net.minecraft.init.Blocks;
import dev.guinness.client.util.MessageUtil;
import dev.guinness.client.module.Category;
import dev.guinness.client.setting.SBoolean;
import dev.guinness.client.setting.SSlider;
import net.minecraft.util.EnumFacing;
import dev.guinness.client.setting.SMode;
import net.minecraft.util.math.BlockPos;
import dev.guinness.client.module.Module;

public class Auto32k extends Module
{
    public static BlockPos obbyPos;
    public static boolean placedDispenser;
    public static SMode placeMode;
    public static boolean skipping;
    public float pitch;
    public static int totalTicks;
    public EnumFacing direction;
    public static int phaseTicks;
    public static BlockPos dispenserPos;
    public static Auto32k INSTANCE;
    public static SSlider timeout;
    public static BlockPos shulkerPos;
    public static SBoolean autoRetry;
    public boolean dispenserFuckedUp;
    public static SBoolean secretClose;
    public static BlockPos rotationPos;
    public static boolean movedSword;
    public static SSlider ticks;
    public static int furnace;
    public static int hopper;
    public static BlockPos hopperPos;
    public static SBoolean sidePlace;
    public static int dispenser;
    public float yaw;
    public static int shulker;
    public static SBoolean rotate;
    public static boolean shulkerDispensed;
    public static boolean togglePitch;
    public static SMode rotateMode;
    public static SSlider range;
    public static BlockPos redstonePos;
    public static boolean isDispenser;
    public static BlockPos placeTarget;
    public static SMode placeType;
    public static int block;
    public static int redstone;
    public static SBoolean timeOut;
    public Auto32k$PlacePhase phase;
    public static boolean isRotating;
    
    public Auto32k() {
        super("Auto32k", Category.DISPENSERPVP, "Automatically dispenses a 32k");
        Auto32k.INSTANCE = this;
    }
    
    public void disableSaying(final String s) {
        MessageUtil.sendModuleErrorMessage(this, s);
        this.disable();
    }
    
    @Override
    public void onEnable() {
        if (this.Null()) {
            return;
        }
        super.onEnable();
        this.resetRotation();
        if (Auto32kReset.INSTANCE.isEnabled()) {
            Auto32kReset.INSTANCE.disable();
        }
        this.phase = Auto32k$PlacePhase.STARTING;
        Auto32k.block = InventoryUtil.find(Blocks.OBSIDIAN);
        Auto32k.redstone = InventoryUtil.find(Blocks.REDSTONE_BLOCK);
        Auto32k.dispenser = InventoryUtil.find(Blocks.DISPENSER);
        Auto32k.hopper = InventoryUtil.find(Blocks.HOPPER);
        Auto32k.furnace = InventoryUtil.find(Blocks.FURNACE);
        Auto32k.phaseTicks = 0;
        Auto32k.totalTicks = 0;
        Auto32k.shulker = -1;
        for (int i = 0; i < 9; ++i) {
            final ItemStack stack = Auto32k.mc.player.field_71071_by.getStackInSlot(i);
            if (stack != ItemStack.EMPTY) {
                if (stack.getItem() instanceof ItemBlock) {
                    final Block block = ((ItemBlock)stack.getItem()).getBlock();
                    if (InventoryUtil.shulkers.contains(block)) {
                        Auto32k.shulker = i;
                    }
                }
            }
        }
        Auto32k.isDispenser = ((String)Auto32k.placeType.getValue()).equalsIgnoreCase("dispenser");
        Auto32k.placeTarget = null;
        Auto32k.obbyPos = null;
        Auto32k.dispenserPos = null;
        Auto32k.redstonePos = null;
        Auto32k.shulkerPos = null;
        Auto32k.hopperPos = null;
        Auto32k.rotationPos = null;
        Auto32k.movedSword = false;
        Auto32k.skipping = false;
        this.dispenserFuckedUp = false;
        Auto32k.shulkerDispensed = false;
        Auto32k.placedDispenser = false;
        Label_1061: {
            if (Auto32k.block == -1 && Auto32k.isDispenser) {
                this.disableSaying("Missing obsidian.");
            }
            else {
                if (Auto32k.redstone == -1) {
                    if (Auto32k.isDispenser) {
                        this.disableSaying("Missing redstone.");
                        break Label_1061;
                    }
                }
                if (Auto32k.dispenser == -1) {
                    if (Auto32k.isDispenser) {
                        this.disableSaying("Missing dispenser.");
                        break Label_1061;
                    }
                }
                if (Auto32k.hopper == -1) {
                    this.disableSaying("Missing hopper.");
                }
                else if (Auto32k.shulker == -1) {
                    this.disableSaying("Missing shulker.");
                }
            }
        }
        if (Auto32k.hopper != -1 && (Auto32k.dispenser != -1 || !Auto32k.isDispenser) && (Auto32k.redstone != -1 || !Auto32k.isDispenser)) {
            if (Auto32k.furnace != -1 || !((String)Auto32k.placeType.getValue()).equalsIgnoreCase("furnace")) {
                if (Auto32k.block != -1 || !Auto32k.isDispenser) {
                    if (Auto32k.shulker != -1) {
                        if (((String)Auto32k.placeMode.getValue()).equalsIgnoreCase("Aim")) {
                            this.beginAimPlacement();
                        }
                        else {
                            this.beginAutoPlacement();
                        }
                    }
                }
            }
        }
    }
    
    public void beginAimPlacement() {
        this.direction = Auto32k.mc.player.func_174811_aO().getOpposite();
        Auto32k.placeTarget = Auto32k.mc.player.func_174822_a(Double.longBitsToDouble(Double.doubleToLongBits(0.19300381349131676) ^ 0x7FDCB45955796817L), Auto32k.mc.getRenderPartialTicks()).getBlockPos();
        if (((String)Auto32k.placeType.getValue()).equalsIgnoreCase("hopper")) {
            if (ModuleUtil.canPlaceBlock(Auto32k.placeTarget.up()) && ModuleUtil.isBlockEmpty(Auto32k.placeTarget.add(0, 2, 0))) {
                this.putHopper32k();
            }
            else {
                this.disableSaying("Unable to place 32k.");
            }
        }
        else {
            if (this.cannotPlace(Auto32k.placeTarget) && this.cannotPlace(Auto32k.placeTarget.up())) {
                this.disableSaying("Unable to place 32k.");
                return;
            }
            if (this.canSkip(Auto32k.placeTarget)) {
                Auto32k.skipping = true;
            }
            else if (this.canSkip(Auto32k.placeTarget.up())) {
                Auto32k.placeTarget = Auto32k.placeTarget.up();
                Auto32k.skipping = true;
            }
            this.startDispenser32k();
        }
    }
    
    public void beginAutoPlacement() {
        final int range = ((Double)Auto32k.range.getValue()).intValue();
        for (int tries = 1; tries <= 4; ++tries) {
            for (int y = -1; y < ((Auto32k.mc.player.field_70160_al || Auto32k.mc.player.func_184613_cA()) ? 2 : 1); ++y) {
                for (int x = -range; x < range; ++x) {
                    for (int z = -range; z < range; ++z) {
                        Auto32k.placeTarget = new BlockPos(Auto32k.mc.player.field_70165_t + x, Auto32k.mc.player.field_70163_u + y, Auto32k.mc.player.field_70161_v + z);
                        if (!((String)Auto32k.placeType.getValue()).equalsIgnoreCase("hopper") && !((String)Auto32k.placeType.getValue()).equalsIgnoreCase("furnace")) {
                            this.direction = ((tries == 1) ? EnumFacing.SOUTH : ((tries == 2) ? EnumFacing.EAST : ((tries == 3) ? EnumFacing.NORTH : EnumFacing.WEST)));
                            if (!this.cannotPlace(Auto32k.placeTarget)) {
                                if (this.canSkip(Auto32k.placeTarget)) {
                                    Auto32k.skipping = true;
                                }
                                this.startDispenser32k();
                                return;
                            }
                        }
                        else if (ModuleUtil.canPlaceBlock(Auto32k.placeTarget.up()) && ModuleUtil.isBlockEmpty(Auto32k.placeTarget.add(0, 2, 0))) {
                            this.putHopper32k();
                            return;
                        }
                    }
                }
            }
        }
        this.disableSaying("No viable placetargets.");
    }
    
    public void startDispenser32k() {
        Auto32k.mc.player.setSprinting(false);
        if (!Auto32k.skipping) {
            Auto32k.placeTarget = Auto32k.placeTarget.up();
            Auto32k.obbyPos = Auto32k.placeTarget;
            Auto32k.rotationPos = Auto32k.obbyPos;
            ModuleUtil.placeBlock(Auto32k.obbyPos, Auto32k.block);
        }
        Auto32k.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(this.direction.equals(EnumFacing.EAST) ? Float.intBitsToFloat(Float.floatToIntBits(0.12302002f) ^ 0x7F4FF1EC) : (this.direction.equals(EnumFacing.NORTH) ? Float.intBitsToFloat(Float.floatToIntBits(3.0312816E38f) ^ 0x7F640C5C) : (this.direction.equals(EnumFacing.WEST) ? Float.intBitsToFloat(Float.floatToIntBits(-0.07586811f) ^ 0x7F2F60BD) : Float.intBitsToFloat(Float.floatToIntBits(0.2843401f) ^ 0x7DA59507))), Float.intBitsToFloat(Float.floatToIntBits(6.726608E37f) ^ 0x7E4A6BE7), Auto32k.mc.player.field_70122_E));
        Auto32k.dispenserPos = Auto32k.placeTarget.up();
        Auto32k.redstonePos = this.redstoneTarget(Auto32k.dispenserPos);
        Auto32k.hopperPos = Auto32k.placeTarget.offset(this.direction);
        Auto32k.shulkerPos = Auto32k.hopperPos.up();
        Auto32k.rotationPos = Auto32k.dispenserPos;
        ModuleUtil.placeBlock(Auto32k.dispenserPos, Auto32k.dispenser);
        Auto32k.placedDispenser = true;
        Auto32k.mc.player.func_70095_a(false);
        ModuleUtil.openBlock(Auto32k.dispenserPos);
        this.phase = Auto32k$PlacePhase.DISPENSERGUI;
    }
    
    public void putHopper32k() {
        Auto32k.hopperPos = Auto32k.placeTarget.up();
        Auto32k.shulkerPos = Auto32k.hopperPos.up();
        Auto32k.rotationPos = Auto32k.hopperPos;
        ModuleUtil.placeBlock(Auto32k.hopperPos, Auto32k.hopper);
        Auto32k.rotationPos = Auto32k.shulkerPos;
        if (((String)Auto32k.placeType.getValue()).equalsIgnoreCase("Furnace")) {
            ModuleUtil.placeBlock(Auto32k.shulkerPos, Auto32k.furnace);
        }
        else {
            ModuleUtil.placeBlock(Auto32k.shulkerPos, Auto32k.shulker);
        }
        Auto32k.rotationPos = Auto32k.hopperPos;
        Auto32k.mc.player.func_70095_a(false);
        ModuleUtil.openBlock(Auto32k.hopperPos);
        this.phase = Auto32k$PlacePhase.HOPPERGUI;
    }
    
    public boolean cannotSkip(final BlockPos blockPos) {
        if (ModuleUtil.canPlaceBlock(blockPos) && ModuleUtil.isBlockEmpty(blockPos.up()) && !ModuleUtil.canPlaceBlock(blockPos.up())) {
            if (this.redstoneTarget(blockPos.up()) != null) {
                if (ModuleUtil.isBlockEmpty(blockPos.offset(this.direction)) && ModuleUtil.isBlockEmpty(blockPos.offset(this.direction).up())) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean canSkip(final BlockPos blockPos) {
        if (ModuleUtil.canPlaceBlock(blockPos.up())) {
            if (this.redstoneTarget(blockPos.up()) != null) {
                if (ModuleUtil.isBlockEmpty(blockPos.offset(this.direction))) {
                    if (ModuleUtil.isBlockEmpty(blockPos.offset(this.direction).up())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public boolean cannotPlace(final BlockPos blockPos) {
        return !this.cannotSkip(blockPos.up()) && !this.canSkip(blockPos);
    }
    
    @Override
    public void onUpdate() {
        if (this.safetyCheck()) {
            Auto32k.mc.player.closeScreen();
            return;
        }
        if (Auto32k.placeTarget == null) {
            return;
        }
        ++Auto32k.phaseTicks;
        ++Auto32k.totalTicks;
        if (((String)Auto32k.placeType.getValue()).equalsIgnoreCase("dispenser")) {
            if (this.phase == Auto32k$PlacePhase.DISPENSERGUI) {
                if (Auto32k.mc.currentScreen instanceof GuiDispenser) {
                    if (this.dispenserFuckedUp) {
                        final boolean autoRetry = (boolean)Auto32k.autoRetry.getValue();
                        Auto32k.mc.player.closeScreen();
                        this.disableSaying("Dispenser fucked up" + (autoRetry ? ", retrying." : "."));
                        if (!Auto32kReset.INSTANCE.isEnabled()) {
                            if (autoRetry) {
                                Auto32kReset.INSTANCE.enable();
                            }
                        }
                        return;
                    }
                    Auto32k.mc.playerController.windowClick(Auto32k.mc.player.field_71070_bA.windowId, 4, Auto32k.shulker, ClickType.SWAP, (EntityPlayer)Auto32k.mc.player);
                    Auto32k.mc.player.closeScreen();
                    this.phase = Auto32k$PlacePhase.REDSTONE;
                    Auto32k.phaseTicks = 0;
                    return;
                }
            }
            if (this.phase == Auto32k$PlacePhase.REDSTONE) {
                if (Auto32k.redstonePos == null) {
                    this.disableSaying("No viable redstone placements.");
                }
                else {
                    if (!Auto32k.mc.world.func_72872_a(EntityPlayer.class, new AxisAlignedBB(Auto32k.redstonePos)).isEmpty()) {
                        MessageUtil.sendClientMessage(TextFormatting.AQUA + "[Auto32k] " + TextFormatting.RED + "MOOOOOOOOOOOOOOOOOOVE");
                        return;
                    }
                    Auto32k.rotationPos = Auto32k.redstonePos;
                    ModuleUtil.placeBlock(Auto32k.redstonePos, Auto32k.redstone);
                    this.phase = Auto32k$PlacePhase.HOPPER;
                    Auto32k.phaseTicks = 0;
                    Auto32k.rotationPos = Auto32k.hopperPos;
                }
                return;
            }
            if (this.phase == Auto32k$PlacePhase.HOPPER) {
                if (Auto32k.mc.world.func_180495_p(Auto32k.shulkerPos).getBlock() instanceof BlockShulkerBox) {
                    Auto32k.shulkerDispensed = true;
                    ModuleUtil.placeBlock(Auto32k.hopperPos, Auto32k.hopper);
                    ModuleUtil.openBlock(Auto32k.hopperPos);
                    this.phase = Auto32k$PlacePhase.HOPPERGUI;
                    Auto32k.phaseTicks = 0;
                    Auto32k.mc.player.field_71071_by.currentItem = Auto32k.shulker;
                    return;
                }
            }
        }
        if (this.phase == Auto32k$PlacePhase.HOPPERGUI) {
            if (!Auto32k.movedSword && Auto32k.mc.currentScreen instanceof GuiHopper) {
                if (((GuiContainer)Auto32k.mc.currentScreen).inventorySlots.getSlot(0).getStack().isEmpty()) {
                    return;
                }
                Auto32k.mc.playerController.windowClick(Auto32k.mc.player.field_71070_bA.windowId, 0, Auto32k.shulker, ClickType.SWAP, (EntityPlayer)Auto32k.mc.player);
                Auto32k.movedSword = true;
                if (Auto32k.rotate.getValue()) {
                    this.resetRotation();
                }
                return;
            }
            else if (Auto32k.movedSword) {
                if (Auto32k.secretClose.getValue()) {
                    final Module secretClose = ModuleManager.getModule(Auto32k::lambda$onUpdate$3);
                    secretClose.enable();
                    Auto32k.mc.player.closeScreen();
                }
                this.phase = Auto32k$PlacePhase.FINISHED;
                return;
            }
        }
        if (this.phase == Auto32k$PlacePhase.FINISHED) {
            Auto32k.phaseTicks = 0;
            if (!(Auto32k.mc.currentScreen instanceof GuiHopper)) {
                this.disable();
                if (((String)Auto32k.rotateMode.getValue()).equalsIgnoreCase("client")) {
                    if (Auto32k.rotate.getValue()) {
                        Auto32k.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(Auto32k.mc.player.field_71109_bG, Auto32k.mc.player.field_70726_aT, Auto32k.mc.player.field_70122_E));
                    }
                }
            }
        }
    }
    
    public boolean safetyCheck() {
        if (this.Null()) {
            return true;
        }
        if (Auto32k.mc.player.func_70011_f(Auto32k.placeTarget.func_177958_n(), Auto32k.placeTarget.func_177956_o(), Auto32k.placeTarget.func_177952_p()) > Double.longBitsToDouble(Double.doubleToLongBits(0.13632629298030968) ^ 0x7FE17323D4F7B95EL)) {
            return true;
        }
        if (Auto32k.phaseTicks > (double)Auto32k.timeout.getValue() && (boolean)Auto32k.timeOut.getValue()) {
            this.disableSaying("Timed out.");
            return true;
        }
        if (Auto32k.totalTicks > (double)Auto32k.timeout.getValue() * Double.longBitsToDouble(Double.doubleToLongBits(0.5086075515157057) ^ 0x7FE0468358084661L) && (boolean)Auto32k.timeOut.getValue()) {
            this.disableSaying("Timed out.");
            return true;
        }
        if (Auto32k.placedDispenser && !Auto32k.shulkerDispensed && Auto32k.mc.world.func_180495_p(Auto32k.dispenserPos).getBlock() instanceof BlockAir) {
            this.disableSaying("Dispenser was destroyed.");
            return true;
        }
        if (Auto32k.isDispenser && (Auto32k.dispenserPos.offset((EnumFacing)Auto32k.mc.world.func_180495_p(Auto32k.dispenserPos).getValue((IProperty)PropertyDirection.create("facing"))).equals(Auto32k.dispenserPos.down()) || Auto32k.dispenserPos.offset((EnumFacing)Auto32k.mc.world.func_180495_p(Auto32k.dispenserPos).getValue((IProperty)PropertyDirection.create("facing"))).equals(Auto32k.dispenserPos.up()))) {
            this.dispenserFuckedUp = true;
        }
        return false;
    }
    
    public BlockPos redstoneTarget(final BlockPos blockPos) {
        final EnumFacing[] facings = { EnumFacing.UP, EnumFacing.NORTH, EnumFacing.SOUTH, EnumFacing.WEST, EnumFacing.EAST };
        final EnumFacing[] sideFacings = { EnumFacing.SOUTH, EnumFacing.NORTH, EnumFacing.WEST, EnumFacing.EAST, EnumFacing.UP };
        for (final EnumFacing f : Auto32k.sidePlace.getValue() ? sideFacings : facings) {
            if (!f.equals(this.direction)) {
                if (Auto32k.mc.world.func_180495_p(blockPos.offset(f)).func_185904_a().isReplaceable() && Auto32k.mc.world.func_72872_a(EntityPlayer.class, new AxisAlignedBB(blockPos.offset(f))).isEmpty()) {
                    if (Auto32k.mc.player.func_70011_f(blockPos.offset(f).func_177958_n(), blockPos.offset(f).func_177956_o(), blockPos.offset(f).func_177952_p()) <= Double.longBitsToDouble(Double.doubleToLongBits(0.1980176245762341) ^ 0x7FDD58A43ACB1375L)) {
                        return blockPos.offset(f);
                    }
                }
            }
        }
        return null;
    }
    
    @Override
    public void onDisable() {
        super.onDisable();
    }
    
    public void setRotations(final BlockPos blockPos) {
        final double[] rots = RotationUtil.calculateLookAt(blockPos.func_177958_n(), blockPos.func_177956_o(), blockPos.func_177952_p(), Auto32k.mc.player);
        if (((String)Auto32k.rotateMode.getValue()).equalsIgnoreCase("client")) {
            if (this.phase != Auto32k$PlacePhase.FINISHED) {
                if (this.phase != Auto32k$PlacePhase.HOPPERGUI) {
                    RotationUtil.RotateClient(blockPos.func_177958_n(), blockPos.func_177956_o(), blockPos.func_177952_p());
                    return;
                }
            }
        }
        this.yaw = (float)rots[0];
        this.pitch = (float)rots[1];
        Auto32k.isRotating = true;
    }
    
    public void resetRotation() {
        this.yaw = Auto32k.mc.player.field_71109_bG;
        this.pitch = Auto32k.mc.player.field_70726_aT;
        Auto32k.isRotating = false;
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent tickEvent) {
        if (this.Null()) {
            return;
        }
        if (Auto32k.rotate.getValue()) {
            if (Auto32k.rotationPos != null) {
                this.setRotations(Auto32k.rotationPos);
            }
        }
        if (!Auto32k.isRotating) {
            return;
        }
        if (Auto32k.togglePitch) {
            final EntityPlayerSP player = Auto32k.mc.player;
            player.field_70125_A += Float.intBitsToFloat(Float.floatToIntBits(80354.22f) ^ 0x7E4D460B);
            Auto32k.togglePitch = false;
        }
        else {
            final EntityPlayerSP player2 = Auto32k.mc.player;
            player2.field_70125_A -= Float.intBitsToFloat(Float.floatToIntBits(25395.58f) ^ 0x7F17D03E);
            Auto32k.togglePitch = true;
        }
    }
    
    @SubscribeEvent
    public void renderRotations(final ModelBipedEvent modelBipedEvent) {
        if (Auto32k.mc.player == null || Auto32k.mc.world == null) {
            return;
        }
        if (Auto32k.isRotating) {
            modelBipedEvent.headYaw = this.yaw + (Auto32k.mc.player.func_174811_aO().equals(EnumFacing.EAST) ? 90 : (Auto32k.mc.player.func_174811_aO().equals(EnumFacing.SOUTH) ? 0 : (Auto32k.mc.player.func_174811_aO().equals(EnumFacing.WEST) ? -90 : 180)));
            modelBipedEvent.headPitch = this.pitch + Float.intBitsToFloat(Float.floatToIntBits(0.010269954f) ^ 0x7D08434F);
        }
    }
    
    @SubscribeEvent
    public void spoofRotations(final PacketEvent$PacketSendEvent packetEvent$PacketSendEvent) {
        if (Auto32k.mc.player == null || Auto32k.mc.world == null) {
            return;
        }
        if (packetEvent$PacketSendEvent.getPacket() instanceof CPacketPlayer) {
            if (Auto32k.isRotating && (boolean)Auto32k.rotate.getValue() && ((String)Auto32k.rotateMode.getValue()).equalsIgnoreCase("server")) {
                final CPacketPlayer packet = (CPacketPlayer)packetEvent$PacketSendEvent.getPacket();
                ((ICPacketPlayer)packet).setYaw(this.yaw);
                ((ICPacketPlayer)packet).setPitch(this.pitch);
            }
        }
    }
    
    public static boolean lambda$onUpdate$3(final Module module) {
        return module.getClass().equals(SecretClose.class);
    }
    
    public static boolean lambda$static$2() {
        return (boolean)Auto32k.rotate.getValue();
    }
    
    public static boolean lambda$static$1() {
        return (boolean)Auto32k.autoRetry.getValue();
    }
    
    public static boolean lambda$static$0() {
        return (boolean)Auto32k.timeOut.getValue();
    }
    
    static {
        Auto32k.placeMode = new SMode("PlaceMode", new String[] { "Auto", "Aim" });
        Auto32k.placeType = new SMode("PlaceType", new String[] { "Dispenser", "Hopper", "Furnace" });
        Auto32k.range = new SSlider("Range", Double.longBitsToDouble(Double.doubleToLongBits(1518.2711141101897) ^ 0x7F67B9159EEFF2FFL), Double.longBitsToDouble(Double.doubleToLongBits(0.0338158552000467) ^ 0x7FA9504FD057481FL), Double.longBitsToDouble(Double.doubleToLongBits(0.5890234208891156) ^ 0x7FEAD947A5297F58L), 0);
        Auto32k.timeOut = new SBoolean("Timeout", true);
        Auto32k.timeout = new SSlider(Auto32k::lambda$static$0, "Timeout Ticks", Double.longBitsToDouble(Double.doubleToLongBits(0.0488882779478421) ^ 0x7FE707E265E331DDL), Double.longBitsToDouble(Double.doubleToLongBits(0.08555070244579666) ^ 0x7FE1E6A69D278EE0L), Double.longBitsToDouble(Double.doubleToLongBits(0.09587093583268588) ^ 0x7FE18AFF6609CE0EL), 0);
        Auto32k.secretClose = new SBoolean("SecretClose", false);
        Auto32k.autoRetry = new SBoolean("AutoRetry", true);
        Auto32k.ticks = new SSlider(Auto32k::lambda$static$1, "Ticks", Double.longBitsToDouble(Double.doubleToLongBits(0.14564773509183093) ^ 0x7FE6A495C17A5A74L), Double.longBitsToDouble(Double.doubleToLongBits(0.4326376628495859) ^ 0x7FEFB055E13D3CF5L), Double.longBitsToDouble(Double.doubleToLongBits(0.37822196834310584) ^ 0x7FE634C9EA2A648CL), 0);
        Auto32k.rotate = new SBoolean("Rotate", false);
        Auto32k.rotateMode = new SMode(Auto32k::lambda$static$2, "RotateMode", new String[] { "Server", "Client" });
        Auto32k.sidePlace = new SBoolean("ChinesePlace", false);
        Auto32k.block = -1;
        Auto32k.redstone = -1;
        Auto32k.dispenser = -1;
        Auto32k.hopper = -1;
        Auto32k.shulker = -1;
        Auto32k.furnace = -1;
        Auto32k.phaseTicks = 0;
        Auto32k.togglePitch = false;
    }
}
