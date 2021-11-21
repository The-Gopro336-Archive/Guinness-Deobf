package dev.guinness.client.module.modules.dispenserpvp;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import dev.guinness.client.util.ModuleUtil;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.network.play.client.CPacketCloseWindow;
import dev.guinness.client.event.events.PacketEvent$PacketSendEvent;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketClickWindow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.block.Block;
import dev.guinness.client.util.MessageUtil;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import dev.guinness.client.util.InventoryUtil;
import net.minecraft.init.Items;
import net.minecraftforge.common.MinecraftForge;
import dev.guinness.client.module.Category;
import dev.guinness.client.setting.SMode;
import dev.guinness.client.module.Module;

public class Throw32k extends Module
{
    public static SMode mode;
    public boolean ready2swap;
    public int sword;
    public int shulker;
    public int delay;
    
    public Throw32k() {
        super("32kThrow", Category.DISPENSERPVP, "Automatically throws out reverted swords in your hotbar and replaces them with shulkers");
        this.ready2swap = false;
    }
    
    @Override
    public void onEnable() {
        if (this.Null()) {
            return;
        }
        MinecraftForge.EVENT_BUS.register(this);
        if (((String)Throw32k.mode.getValue()).equalsIgnoreCase("Persistant")) {
            return;
        }
        this.sword = -1;
        this.sword = InventoryUtil.find(Items.DIAMOND_SWORD);
        this.shulker = -1;
        for (int i = 9; i < 36; ++i) {
            final ItemStack stack = Throw32k.mc.player.field_71071_by.getStackInSlot(i);
            if (stack != ItemStack.EMPTY) {
                if (stack.getItem() instanceof ItemBlock) {
                    final Block block = ((ItemBlock)stack.getItem()).getBlock();
                    if (InventoryUtil.shulkers.contains(block)) {
                        this.shulker = i;
                    }
                }
            }
        }
        if (this.sword == -1) {
            MessageUtil.sendModuleErrorMessage(this, "No sword to swap!");
            this.disable();
            return;
        }
        if (this.shulker == -1) {
            MessageUtil.sendModuleErrorMessage(this, "No shulkers left!");
            this.disable();
        }
    }
    
    @Override
    public void onUpdate() {
        if (this.Null()) {
            this.disable();
            return;
        }
        if (((String)Throw32k.mode.getValue()).equalsIgnoreCase("Single")) {
            Throw32k.mc.playerController.windowClick(Throw32k.mc.player.field_71069_bz.windowId, this.shulker, this.sword, ClickType.SWAP, (EntityPlayer)Throw32k.mc.player);
            Throw32k.mc.player.connection.sendPacket((Packet)new CPacketClickWindow(0, this.shulker, 0, ClickType.THROW, Throw32k.mc.player.field_71071_by.getStackInSlot(this.shulker), Throw32k.mc.player.field_71069_bz.getNextTransactionID(Throw32k.mc.player.field_71071_by)));
            this.disable();
        }
        if (this.ready2swap) {
            ++this.delay;
            if (this.delay >= 4) {
                this.ready2swap = false;
                this.delay = 0;
                Throw32k.mc.playerController.windowClick(Throw32k.mc.player.field_71069_bz.windowId, this.shulker, this.sword, ClickType.SWAP, (EntityPlayer)Throw32k.mc.player);
                Throw32k.mc.player.connection.sendPacket((Packet)new CPacketClickWindow(0, this.shulker, 0, ClickType.THROW, Throw32k.mc.player.field_71071_by.getStackInSlot(this.shulker), Throw32k.mc.player.field_71069_bz.getNextTransactionID(Throw32k.mc.player.field_71071_by)));
            }
        }
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent$PacketSendEvent packetEvent$PacketSendEvent) {
        if (((String)Throw32k.mode.getValue()).equalsIgnoreCase("Persistant")) {
            if (packetEvent$PacketSendEvent.getPacket() instanceof CPacketCloseWindow) {
                this.sword = -1;
                this.sword = InventoryUtil.find(Items.DIAMOND_SWORD);
                this.shulker = -1;
                for (int i = 9; i < 36; ++i) {
                    final ItemStack stack = Throw32k.mc.player.field_71071_by.getStackInSlot(i);
                    if (stack != ItemStack.EMPTY) {
                        if (stack.getItem() instanceof ItemBlock) {
                            final Block block = ((ItemBlock)stack.getItem()).getBlock();
                            if (InventoryUtil.shulkers.contains(block)) {
                                this.shulker = i;
                            }
                        }
                    }
                }
                if (this.sword == -1) {
                    return;
                }
                if (this.shulker == -1) {
                    MessageUtil.sendClientMessage(TextFormatting.BLUE + "[32kThrow]" + TextFormatting.RESET + "No shulkers left!");
                    return;
                }
                if (ModuleUtil.is32k(Throw32k.mc.player.field_71071_by.getStackInSlot(this.sword))) {
                    return;
                }
                this.ready2swap = true;
            }
        }
    }
    
    @Override
    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister(this);
        this.sword = -1;
        this.shulker = -1;
    }
    
    static {
        Throw32k.mode = new SMode("Mode", new String[] { "Single", "Persistant" });
    }
}
