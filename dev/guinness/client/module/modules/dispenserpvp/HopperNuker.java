package dev.guinness.client.module.modules.dispenserpvp;

import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.math.Vec3i;
import net.minecraft.tileentity.TileEntity;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.Comparator;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.util.EnumHand;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import dev.guinness.client.util.ModuleUtil;
import java.util.function.Predicate;
import dev.guinness.client.module.ModuleManager;
import dev.guinness.client.module.Category;
import dev.guinness.client.setting.SSlider;
import net.minecraft.util.math.BlockPos;
import dev.guinness.client.setting.SMode;
import dev.guinness.client.setting.SBoolean;
import dev.guinness.client.module.Module;

public class HopperNuker extends Module
{
    public boolean mining;
    public int prevSlot;
    public static SBoolean rotate;
    public static SMode mode;
    public BlockPos pos;
    public static SSlider range;
    
    public HopperNuker() {
        super("HopperNuker", Category.DISPENSERPVP, "Automatically mines hoppers around you");
        this.prevSlot = -1;
        this.mining = false;
    }
    
    @Override
    public void onUpdate() {
        if (this.Null()) {
            return;
        }
        if (ModuleManager.getModule(HopperNuker::lambda$onUpdate$0).isEnabled()) {
            return;
        }
        final BlockPos pos = this.findHoppers();
        if (pos != null) {
            if (!this.mining) {
                this.prevSlot = HopperNuker.mc.player.field_71071_by.currentItem;
                this.mining = true;
            }
            if (HopperNuker.rotate.getValue()) {
                ModuleUtil.rotateClient(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
            }
            int newSlot = -1;
            for (int i = 0; i < 9; ++i) {
                final ItemStack stack = HopperNuker.mc.player.field_71071_by.getStackInSlot(i);
                if (stack != ItemStack.EMPTY) {
                    if (stack.getItem() instanceof ItemPickaxe) {
                        newSlot = i;
                        break;
                    }
                }
            }
            if (newSlot != -1) {
                HopperNuker.mc.player.field_71071_by.currentItem = newSlot;
            }
            if (!((String)HopperNuker.mode.getValue()).equalsIgnoreCase("Packet")) {
                HopperNuker.mc.playerController.onPlayerDamageBlock(pos, HopperNuker.mc.player.func_174811_aO());
                HopperNuker.mc.player.swingArm(EnumHand.MAIN_HAND);
            }
            else {
                HopperNuker.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, pos, HopperNuker.mc.player.func_174811_aO()));
                HopperNuker.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, pos, HopperNuker.mc.player.func_174811_aO()));
            }
        }
        else if (this.prevSlot != -1) {
            HopperNuker.mc.player.field_71071_by.currentItem = this.prevSlot;
            this.prevSlot = -1;
            this.mining = false;
        }
    }
    
    public BlockPos findHoppers() {
        this.pos = null;
        HopperNuker.mc.world.field_147482_g.stream().filter(HopperNuker::lambda$findHoppers$1).filter(HopperNuker::lambda$findHoppers$2).sorted(Comparator.comparing((Function<? super T, ? extends Comparable>)HopperNuker::lambda$findHoppers$3)).forEach(this::lambda$findHoppers$4);
        return this.pos;
    }
    
    public void lambda$findHoppers$4(final TileEntity tileEntity) {
        this.pos = new BlockPos(tileEntity.getPos());
    }
    
    public static Double lambda$findHoppers$3(final TileEntity tileEntity) {
        return HopperNuker.mc.player.func_70011_f(tileEntity.getPos().func_177958_n(), tileEntity.getPos().func_177956_o(), tileEntity.getPos().func_177952_p());
    }
    
    public static boolean lambda$findHoppers$2(final TileEntity tileEntity) {
        return HopperNuker.mc.player.func_70011_f(tileEntity.getPos().func_177958_n(), tileEntity.getPos().func_177956_o(), tileEntity.getPos().func_177952_p()) <= (double)HopperNuker.range.getValue();
    }
    
    public static boolean lambda$findHoppers$1(final TileEntity tileEntity) {
        return tileEntity instanceof TileEntityHopper;
    }
    
    public static boolean lambda$onUpdate$0(final Module module) {
        return module.getClass().equals(Auto32k.class);
    }
    
    static {
        HopperNuker.rotate = new SBoolean("Rotate", false);
        HopperNuker.mode = new SMode("Break Mode", new String[] { "Packet", "Vanilla" });
        HopperNuker.range = new SSlider("Range", Double.longBitsToDouble(Double.doubleToLongBits(15.766491683456875) ^ 0x7FDF8871991234DFL), Double.longBitsToDouble(Double.doubleToLongBits(0.21801978865183033) ^ 0x7FD3E8128B11FA0BL), Double.longBitsToDouble(Double.doubleToLongBits(1.8064178957329855) ^ 0x7FDEE71673915125L), 1);
    }
}
