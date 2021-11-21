package dev.guinness.client.module.modules.misc;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import com.mojang.authlib.GameProfile;
import dev.guinness.client.module.Category;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import dev.guinness.client.setting.SBoolean;
import dev.guinness.client.module.Module;

public class FakePlayer extends Module
{
    public static SBoolean inv;
    public EntityOtherPlayerMP fakePlayer;
    
    public FakePlayer() {
        super("FakePlayer", Category.MISC, "Spawns a fakeplayer for you to test things on");
    }
    
    @Override
    public void onEnable() {
        this.fakePlayer = null;
        this.fakePlayer = new EntityOtherPlayerMP(FakePlayer.mc.world, new GameProfile(FakePlayer.mc.player.func_110124_au(), "Guinness"));
        if (FakePlayer.inv.getValue()) {
            this.fakePlayer.field_71071_by.copyInventory(FakePlayer.mc.player.field_71071_by);
        }
        FakePlayer.mc.world.addEntityToWorld(this.fakePlayer.func_145782_y(), (Entity)this.fakePlayer);
        this.fakePlayer.func_184595_k(FakePlayer.mc.player.field_70165_t, FakePlayer.mc.player.field_70163_u, FakePlayer.mc.player.field_70161_v);
    }
    
    @Override
    public void onDisable() {
        if (this.fakePlayer != null) {
            FakePlayer.mc.world.removeEntity((Entity)this.fakePlayer);
        }
    }
    
    static {
        FakePlayer.inv = new SBoolean("Copy Inventory", true);
    }
}
