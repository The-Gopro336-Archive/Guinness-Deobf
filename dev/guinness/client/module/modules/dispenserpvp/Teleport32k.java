package dev.guinness.client.module.modules.dispenserpvp;

import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.util.EnumHand;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import dev.guinness.client.util.MessageUtil;
import net.minecraft.item.Item;
import dev.guinness.client.util.InventoryUtil;
import net.minecraft.init.Items;
import java.util.function.Predicate;
import dev.guinness.client.module.ModuleManager;
import dev.guinness.client.module.Category;
import dev.guinness.client.setting.SMode;
import dev.guinness.client.setting.SSlider;
import net.minecraft.util.math.BlockPos;
import dev.guinness.client.module.Module;

public class Teleport32k extends Module
{
    public BlockPos startpos;
    public boolean hasbow;
    public boolean flag;
    public static Teleport32k INSTANCE;
    public static SSlider delay;
    public static SMode mode;
    
    public Teleport32k() {
        super("32kTeleport", Category.DISPENSERPVP, "Forces a rubberband in combination with longjump and blink");
        this.flag = false;
        this.hasbow = false;
        Teleport32k.INSTANCE = this;
    }
    
    @Override
    public void onEnable() {
        if (this.Null()) {
            return;
        }
        this.flag = false;
        this.hasbow = false;
        ModuleManager.getModule(Teleport32k::lambda$onEnable$0).enable();
        if (((String)Teleport32k.mode.getValue()).equalsIgnoreCase("Bow")) {
            final int bow = InventoryUtil.find(Items.BOW);
            if (bow == -1) {
                MessageUtil.sendClientMessage("No bow found!");
                return;
            }
            this.hasbow = true;
        }
    }
    
    @Override
    public void onUpdate() {
        if (((String)Teleport32k.mode.getValue()).equalsIgnoreCase("Bow") && this.hasbow) {
            final int bow = InventoryUtil.find(Items.BOW);
            Teleport32k.mc.player.field_71071_by.currentItem = bow;
            if (Teleport32k.mc.player.func_184612_cw() >= 5) {
                Teleport32k.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, Teleport32k.mc.player.func_174811_aO()));
                Teleport32k.mc.player.func_184597_cx();
                this.hasbow = false;
            }
            else {
                Teleport32k.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
            }
        }
    }
    
    @Override
    public void onDisable() {
        if (this.Null()) {
            return;
        }
        this.flag = true;
    }
    
    public static boolean lambda$onEnable$0(final Module module) {
        return module.getClass().equals(Teleport32kHelper.class);
    }
    
    static {
        Teleport32k.mode = new SMode("Mode", new String[] { "Bow", "Normal" });
        Teleport32k.delay = new SSlider("TP Delay", Double.longBitsToDouble(Double.doubleToLongBits(13.850131511763822) ^ 0x7FDBB3447000A639L), Double.longBitsToDouble(Double.doubleToLongBits(0.07172688390836611) ^ 0x7FB25CB16CA16347L), Double.longBitsToDouble(Double.doubleToLongBits(1.0742377270142953) ^ 0x7FE93013E61A7E0DL), 0);
    }
}
