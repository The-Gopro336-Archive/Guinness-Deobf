package dev.guinness.client.module.modules.misc;

import net.minecraft.entity.Entity;
import dev.guinness.client.util.MessageUtil;
import com.mojang.realmsclient.gui.ChatFormatting;
import dev.guinness.client.util.FriendUtil;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketClickWindow;
import net.minecraft.world.World;
import net.minecraft.util.EnumHand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Items;
import org.lwjgl.input.Mouse;
import net.minecraft.client.gui.inventory.GuiContainer;
import dev.guinness.client.module.Category;
import dev.guinness.client.setting.SMode;
import dev.guinness.client.module.Module;

public class MiddleClick extends Module
{
    public int expSlot;
    public static SMode mode;
    public boolean clickedOnce;
    public int pearlSlot;
    public boolean clickedMiddle;
    public boolean startedThrowing;
    
    public MiddleClick() {
        super("MiddleClick", Category.MISC, "Various modes for middleclick modules");
        this.clickedMiddle = false;
        this.clickedOnce = false;
        this.startedThrowing = false;
    }
    
    @Override
    public void onUpdate() {
        if (MiddleClick.mc.currentScreen instanceof GuiContainer) {
            return;
        }
        if (MiddleClick.mc.player.field_70173_aa % 12 == 0) {
            this.clickedMiddle = false;
        }
        if (!Mouse.isButtonDown(2)) {
            this.clickedOnce = false;
        }
        if (((String)MiddleClick.mode.getValue()).equalsIgnoreCase("pearl") && Mouse.isButtonDown(2) && !this.clickedMiddle) {
            this.clickedMiddle = true;
            this.findPearl();
            if (this.pearlSlot != -1) {
                final ItemStack pearl = new ItemStack(Items.ENDER_PEARL);
                MiddleClick.mc.playerController.windowClick(MiddleClick.mc.player.field_71069_bz.windowId, this.pearlSlot, 0, ClickType.PICKUP, (EntityPlayer)MiddleClick.mc.player);
                MiddleClick.mc.playerController.windowClick(MiddleClick.mc.player.field_71069_bz.windowId, 36 + MiddleClick.mc.player.field_71071_by.currentItem, 0, ClickType.PICKUP, (EntityPlayer)MiddleClick.mc.player);
                MiddleClick.mc.playerController.processRightClick((EntityPlayer)MiddleClick.mc.player, (World)MiddleClick.mc.world, EnumHand.MAIN_HAND);
                MiddleClick.mc.playerController.windowClick(MiddleClick.mc.player.field_71069_bz.windowId, 36 + MiddleClick.mc.player.field_71071_by.currentItem, 0, ClickType.PICKUP, (EntityPlayer)MiddleClick.mc.player);
                MiddleClick.mc.playerController.windowClick(MiddleClick.mc.player.field_71069_bz.windowId, this.pearlSlot, 0, ClickType.PICKUP, (EntityPlayer)MiddleClick.mc.player);
                MiddleClick.mc.playerController.windowClick(MiddleClick.mc.player.field_71069_bz.windowId, this.pearlSlot, 0, ClickType.PICKUP, (EntityPlayer)MiddleClick.mc.player);
                MiddleClick.mc.player.connection.sendPacket((Packet)new CPacketClickWindow(0, this.pearlSlot, 0, ClickType.PICKUP, pearl, (short)19));
                MiddleClick.mc.player.connection.sendPacket((Packet)new CPacketClickWindow(0, this.pearlSlot, 0, ClickType.PICKUP, pearl, (short)20));
            }
        }
        if (((String)MiddleClick.mode.getValue()).equalsIgnoreCase("exp")) {
            if (Mouse.isButtonDown(2)) {
                this.startedThrowing = true;
                if (!this.clickedOnce) {
                    this.clickedOnce = true;
                    if (MiddleClick.mc.player.field_71071_by.getStackInSlot(MiddleClick.mc.player.field_71071_by.currentItem).getItem() != Items.EXPERIENCE_BOTTLE) {
                        this.findEXP();
                        MiddleClick.mc.playerController.windowClick(MiddleClick.mc.player.field_71069_bz.windowId, this.expSlot, 0, ClickType.PICKUP, (EntityPlayer)MiddleClick.mc.player);
                        MiddleClick.mc.playerController.windowClick(MiddleClick.mc.player.field_71069_bz.windowId, 36 + MiddleClick.mc.player.field_71071_by.currentItem, 0, ClickType.PICKUP, (EntityPlayer)MiddleClick.mc.player);
                    }
                }
                if (this.clickedOnce) {
                    MiddleClick.mc.playerController.processRightClick((EntityPlayer)MiddleClick.mc.player, (World)MiddleClick.mc.world, EnumHand.MAIN_HAND);
                }
            }
            else if (!Mouse.isButtonDown(2) && this.startedThrowing) {
                this.startedThrowing = false;
                final ItemStack exp = new ItemStack(Items.EXPERIENCE_BOTTLE);
                MiddleClick.mc.playerController.windowClick(MiddleClick.mc.player.field_71069_bz.windowId, 36 + MiddleClick.mc.player.field_71071_by.currentItem, 0, ClickType.PICKUP, (EntityPlayer)MiddleClick.mc.player);
                MiddleClick.mc.playerController.windowClick(MiddleClick.mc.player.field_71069_bz.windowId, this.expSlot, 0, ClickType.PICKUP, (EntityPlayer)MiddleClick.mc.player);
                MiddleClick.mc.playerController.windowClick(MiddleClick.mc.player.field_71069_bz.windowId, this.expSlot, 0, ClickType.PICKUP, (EntityPlayer)MiddleClick.mc.player);
                MiddleClick.mc.player.connection.sendPacket((Packet)new CPacketClickWindow(0, this.expSlot, 0, ClickType.PICKUP, exp, (short)19));
                MiddleClick.mc.player.connection.sendPacket((Packet)new CPacketClickWindow(0, this.expSlot, 0, ClickType.PICKUP, exp, (short)20));
            }
        }
        if (((String)MiddleClick.mode.getValue()).equalsIgnoreCase("friend") && Mouse.isButtonDown(2) && !this.clickedMiddle) {
            this.clickedMiddle = true;
            final RayTraceResult rtr = MiddleClick.mc.objectMouseOver;
            if (rtr == null || rtr.typeOfHit != RayTraceResult.Type.ENTITY) {
                return;
            }
            if (!(rtr.entityHit instanceof EntityPlayer)) {
                return;
            }
            final Entity player = rtr.entityHit;
            if (FriendUtil.isFriend(player.getName())) {
                FriendUtil.delFriend(player.getName());
                MessageUtil.sendClientMessage("Successfully removed " + ChatFormatting.BLUE + player.getName() + ChatFormatting.GRAY + " from the friends list");
            }
            else {
                FriendUtil.addFriend(player.getName());
                MessageUtil.sendClientMessage("Successfully added " + ChatFormatting.BLUE + player.getName() + ChatFormatting.GRAY + " to the friends list");
            }
        }
    }
    
    public void findPearl() {
        if (MiddleClick.mc.currentScreen != null) {
            if (MiddleClick.mc.currentScreen instanceof GuiContainer) {
                return;
            }
        }
        if (MiddleClick.mc.player.field_71071_by.getStackInSlot(MiddleClick.mc.player.field_71071_by.currentItem).getItem() != Items.ENDER_PEARL) {
            for (int i = 9; i < 36; ++i) {
                if (MiddleClick.mc.player.field_71071_by.getStackInSlot(i).getItem() == Items.ENDER_PEARL) {
                    this.pearlSlot = i;
                    break;
                }
            }
        }
    }
    
    public void findEXP() {
        if ((MiddleClick.mc.currentScreen == null || !(MiddleClick.mc.currentScreen instanceof GuiContainer)) && MiddleClick.mc.player.field_71071_by.getStackInSlot(0).getItem() != Items.EXPERIENCE_BOTTLE) {
            for (int i = 9; i < 36; ++i) {
                if (MiddleClick.mc.player.field_71071_by.getStackInSlot(i).getItem() == Items.EXPERIENCE_BOTTLE) {
                    this.expSlot = i;
                    break;
                }
            }
        }
    }
    
    static {
        MiddleClick.mode = new SMode("Mode", new String[] { "Pearl", "EXP", "Friend" });
    }
}
