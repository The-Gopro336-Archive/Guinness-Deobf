package dev.guinness.client.module.modules.dispenserpvp;

import dev.guinness.client.util.FriendUtil;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.tileentity.TileEntity;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.function.Predicate;
import java.util.function.Consumer;
import dev.guinness.client.util.InventoryUtil;
import net.minecraft.init.Blocks;
import dev.guinness.client.util.ModuleUtil;
import dev.guinness.client.mixin.mixins.IEntityPlayer;
import java.util.ArrayList;
import dev.guinness.client.module.Category;
import net.minecraft.entity.player.EntityPlayer;
import java.util.List;
import dev.guinness.client.module.Module;

public class Block32k extends Module
{
    public List<EntityPlayer> badguys;
    
    public Block32k() {
        super("32kBlock", Category.DISPENSERPVP, "Blocks dispensers if another player is enabling auto32k");
        this.badguys = new ArrayList<EntityPlayer>();
    }
    
    @Override
    public void onUpdate() {
        if (this.Null()) {
            return;
        }
        this.findEnemies();
        if (this.badguys.size() == 0) {
            return;
        }
        if (ModuleUtil.is32k(((IEntityPlayer)Block32k.mc.player).getItemStackMainHand())) {
            return;
        }
        final int currentSlot = Block32k.mc.player.field_71071_by.currentItem;
        final int obs = InventoryUtil.find(Blocks.OBSIDIAN);
        if (obs != -1) {
            Block32k.mc.player.field_71071_by.currentItem = obs;
            this.badguys.forEach(this::lambda$onUpdate$0);
            Block32k.mc.player.field_71071_by.currentItem = currentSlot;
        }
    }
    
    public void findEnemies() {
        this.badguys = (List<EntityPlayer>)Block32k.mc.world.field_73010_i.stream().filter(Block32k::lambda$findEnemies$1).filter(Block32k::lambda$findEnemies$2).filter(Block32k::lambda$findEnemies$3).filter(Block32k::lambda$findEnemies$4).filter(Block32k::lambda$findEnemies$5).collect(Collectors.toList());
    }
    
    public void block32k(final EntityPlayer entityPlayer) {
        Block32k.mc.world.field_147482_g.stream().filter(Block32k::lambda$block32k$6).filter(Block32k::lambda$block32k$7).forEach(Block32k::lambda$block32k$8);
    }
    
    public static void lambda$block32k$8(final EntityPlayer entityPlayer, final TileEntity tileEntity) {
        if (Block32k.mc.world.func_180495_p(tileEntity.getPos().offset(EnumFacing.getDirectionFromEntityLiving(tileEntity.getPos(), (EntityLivingBase)entityPlayer))).func_185904_a().isReplaceable()) {
            ModuleUtil.placeBlock(tileEntity.getPos().offset(EnumFacing.getDirectionFromEntityLiving(tileEntity.getPos(), (EntityLivingBase)entityPlayer)), EnumFacing.getDirectionFromEntityLiving(tileEntity.getPos(), (EntityLivingBase)entityPlayer).getOpposite());
        }
    }
    
    public static boolean lambda$block32k$7(final TileEntity tileEntity) {
        return tileEntity.getDistanceSq(Block32k.mc.player.field_70165_t, Block32k.mc.player.field_70163_u, Block32k.mc.player.field_70161_v) <= Double.longBitsToDouble(Double.doubleToLongBits(0.012398492033458037) ^ 0x7FD964616E6D66D7L);
    }
    
    public static boolean lambda$block32k$6(final TileEntity tileEntity) {
        return tileEntity instanceof TileEntityDispenser;
    }
    
    public static boolean lambda$findEnemies$5(final EntityPlayer entityPlayer) {
        return ((ItemBlock)entityPlayer.func_184614_ca().getItem()).getBlock() == Blocks.DISPENSER;
    }
    
    public static boolean lambda$findEnemies$4(final EntityPlayer entityPlayer) {
        return entityPlayer.func_184614_ca().getItem() instanceof ItemBlock;
    }
    
    public static boolean lambda$findEnemies$3(final EntityPlayer entityPlayer) {
        return Block32k.mc.player.func_70032_d(entityPlayer) <= Float.intBitsToFloat(Float.floatToIntBits(0.6147248f) ^ 0x7E1D5E9B);
    }
    
    public static boolean lambda$findEnemies$2(final EntityPlayer entityPlayer) {
        return !FriendUtil.isFriend(entityPlayer.getName());
    }
    
    public static boolean lambda$findEnemies$1(final EntityPlayer entityPlayer) {
        return entityPlayer != Block32k.mc.player;
    }
    
    public void lambda$onUpdate$0(final EntityPlayer entityPlayer) {
        this.block32k(entityPlayer);
    }
}
