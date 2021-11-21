package dev.guinness.client.module.modules.combat;

import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Iterator;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.network.play.server.SPacketSoundEffect;
import dev.guinness.client.event.events.PacketEvent$PacketReceiveEvent;
import dev.guinness.client.util.ModuleUtil;
import net.minecraft.util.EnumFacing;
import dev.guinness.client.util.CrystalUtil;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.entity.Entity;
import java.util.function.Predicate;
import net.minecraft.entity.item.EntityEnderCrystal;
import dev.guinness.client.module.Category;
import net.minecraft.util.math.BlockPos;
import dev.guinness.client.setting.SBoolean;
import dev.guinness.client.setting.SSlider;
import net.minecraft.entity.player.EntityPlayer;
import dev.guinness.client.module.Module;

public class AutoCrystal extends Module
{
    public EntityPlayer target;
    public static SSlider maxSelfDamage;
    public static SBoolean stopMining;
    public static SBoolean one13;
    public static SSlider breakRange;
    public static SSlider placeRange;
    public static SBoolean stopEating;
    public BlockPos targettedPos;
    public static SSlider minDamage;
    public static SSlider wallBreakRange;
    
    public AutoCrystal() {
        super("AutoCrystal", Category.COMBAT, "BROKENBROKENBROKENBROKEN AAAAAAAA");
    }
    
    @Override
    public void onUpdate() {
        this.placeCrystals();
        this.breakCrystals();
    }
    
    public void breakCrystals() {
        final EntityEnderCrystal cry = (EntityEnderCrystal)AutoCrystal.mc.world.field_72996_f.stream().filter(AutoCrystal::lambda$breakCrystals$0).findFirst().orElse(null);
        if (cry != null) {
            if (this.shouldAttack(cry, AutoCrystal.mc.player.func_70685_l(cry))) {
                AutoCrystal.mc.player.connection.sendPacket((Packet)new CPacketUseEntity(cry));
            }
        }
    }
    
    public void placeCrystals() {
        this.target = CrystalUtil.getTarget((double)AutoCrystal.placeRange.getValue(), (boolean)AutoCrystal.one13.getValue());
        if (this.target != null) {
            this.targettedPos = CrystalUtil.getBestCrystalPosAroundPlayer(this.target, (boolean)AutoCrystal.one13.getValue());
            if (this.targettedPos != null) {
                ModuleUtil.placeBlock(this.targettedPos.add(0, 1, 0), EnumFacing.DOWN);
            }
        }
    }
    
    public boolean shouldAttack(final EntityEnderCrystal entityEnderCrystal, final boolean b) {
        final EntityPlayer ape = CrystalUtil.getTarget((double)AutoCrystal.breakRange.getValue(), (boolean)AutoCrystal.one13.getValue());
        if (ape == null) {
            return false;
        }
        if (AutoCrystal.mc.player.func_70032_d(entityEnderCrystal) <= (double)(b ? AutoCrystal.breakRange.getValue() : ((Double)AutoCrystal.wallBreakRange.getValue()))) {
            if (CrystalUtil.calculateDamage(entityEnderCrystal.field_70165_t, entityEnderCrystal.field_70163_u, entityEnderCrystal.field_70161_v, AutoCrystal.mc.player) <= (double)AutoCrystal.maxSelfDamage.getValue()) {
                if (CrystalUtil.calculateDamage(entityEnderCrystal.field_70165_t, entityEnderCrystal.field_70163_u, entityEnderCrystal.field_70161_v, ape) >= (double)AutoCrystal.minDamage.getValue()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onPacketReceived(final PacketEvent$PacketReceiveEvent packetEvent$PacketReceiveEvent) {
        if (packetEvent$PacketReceiveEvent.getPacket() instanceof SPacketSoundEffect) {
            final SPacketSoundEffect packet = (SPacketSoundEffect)packetEvent$PacketReceiveEvent.getPacket();
            if (packet.getCategory().equals(SoundCategory.BLOCKS)) {
                if (packet.getSound() == SoundEvents.ENTITY_GENERIC_EXPLODE) {
                    for (final Entity entity : AutoCrystal.mc.world.field_72996_f) {
                        if (entity instanceof EntityEnderCrystal) {
                            if (entity.getDistance(packet.getX(), packet.getY(), packet.getZ()) > Double.longBitsToDouble(Double.doubleToLongBits(0.23452177157348622) ^ 0x7FD604CF358DD669L)) {
                                continue;
                            }
                            entity.setDead();
                        }
                    }
                }
            }
        }
    }
    
    public static boolean lambda$breakCrystals$0(final Entity entity) {
        return entity instanceof EntityEnderCrystal;
    }
    
    static {
        AutoCrystal.one13 = new SBoolean("1.13+", false);
        AutoCrystal.stopEating = new SBoolean("Stop While Eating", true);
        AutoCrystal.stopMining = new SBoolean("Stop While Mining", true);
        AutoCrystal.placeRange = new SSlider("Place Range", Double.longBitsToDouble(Double.doubleToLongBits(0.545245009096519) ^ 0x7FE972A5A94C0E83L), Double.longBitsToDouble(Double.doubleToLongBits(0.49612544626649696) ^ 0x7FC9C084F19B6483L), Double.longBitsToDouble(Double.doubleToLongBits(1.465873547510367) ^ 0x7FEF7437D22A0E74L), 1);
        AutoCrystal.breakRange = new SSlider("Break Range", Double.longBitsToDouble(Double.doubleToLongBits(0.3025406510164245) ^ 0x7FDB5CD37674DEB3L), Double.longBitsToDouble(Double.doubleToLongBits(1.059137342374587) ^ 0x7FE6F239FF778A0FL), Double.longBitsToDouble(Double.doubleToLongBits(0.05511807571465715) ^ 0x7FB4386FB939CE97L), 1);
        AutoCrystal.wallBreakRange = new SSlider("Wall Break Range", Double.longBitsToDouble(Double.doubleToLongBits(10.20926479216954) ^ 0x7FD46B24C13D2513L), Double.longBitsToDouble(Double.doubleToLongBits(0.4978833056166287) ^ 0x7FD7DD51F0B6423FL), Double.longBitsToDouble(Double.doubleToLongBits(0.16040337841298727) ^ 0x7FD488191039D111L), 1);
        AutoCrystal.maxSelfDamage = new SSlider("Max Self Damage", Double.longBitsToDouble(Double.doubleToLongBits(6.231448291024248) ^ 0x7FE8ED00C7E2A82DL), Double.longBitsToDouble(Double.doubleToLongBits(0.018293518460727878) ^ 0x7FB2BB893E120557L), Double.longBitsToDouble(Double.doubleToLongBits(0.712542397247777) ^ 0x7FD2CD25B6A62A21L), 1);
        AutoCrystal.minDamage = new SSlider("Min Damage", Double.longBitsToDouble(Double.doubleToLongBits(337.2035096872932) ^ 0x7F851341935FB57FL), Double.longBitsToDouble(Double.doubleToLongBits(1.5769332867439967) ^ 0x7FD93B1E65E8A0CBL), Double.longBitsToDouble(Double.doubleToLongBits(0.7347196836952584) ^ 0x7FD782D2DAA65AEDL), 1);
    }
}
