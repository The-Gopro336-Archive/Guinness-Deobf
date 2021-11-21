package dev.guinness.client.module.modules.combat;

import net.minecraft.util.math.AxisAlignedBB;
import java.util.function.Function;
import java.util.Comparator;
import net.minecraft.block.BlockObsidian;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import net.minecraft.entity.player.EntityPlayer;
import dev.guinness.client.util.ModuleUtil;
import dev.guinness.client.util.RenderUtil;
import java.awt.Color;
import dev.guinness.client.util.ColorUtil;
import net.minecraft.block.BlockAir;
import net.minecraft.util.EnumHand;
import net.minecraft.network.Packet;
import net.minecraft.util.EnumFacing;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import dev.guinness.client.util.MessageUtil;
import dev.guinness.client.module.Category;
import net.minecraft.util.math.BlockPos;
import dev.guinness.client.setting.SSlider;
import dev.guinness.client.module.Module;

public class AutoCity extends Module
{
    public static SSlider range;
    public AutoCity$Stage stage;
    public BlockPos cityPos;
    
    public AutoCity() {
        super("AutoCity", Category.COMBAT, "Automatically mines the city block of a target using packets");
    }
    
    @Override
    public void onEnable() {
        this.cityPos = null;
        this.stage = null;
        this.setCityBlock();
        if (this.cityPos == null) {
            MessageUtil.sendModuleErrorMessage(this, "No places to city!");
            this.disable();
            return;
        }
        this.stage = AutoCity$Stage.MINING;
    }
    
    @Override
    public void onUpdate() {
        if (this.stage.equals(AutoCity$Stage.MINING)) {
            AutoCity.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, this.cityPos, EnumFacing.DOWN));
            AutoCity.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, this.cityPos, EnumFacing.DOWN));
            if (!AutoCity.mc.player.field_82175_bq) {
                AutoCity.mc.player.swingArm(EnumHand.MAIN_HAND);
            }
            this.stage = AutoCity$Stage.WAITING;
            return;
        }
        if (this.stage.equals(AutoCity$Stage.WAITING)) {
            if (AutoCity.mc.world.func_180495_p(this.cityPos).getBlock() instanceof BlockAir) {
                this.disable();
            }
        }
    }
    
    @Override
    public void onRender3d() {
        if (this.cityPos != null) {
            RenderUtil.drawBlockOutline(this.cityPos, Double.longBitsToDouble(Double.doubleToLongBits(1.8383375801331424E307) ^ 0x7FBA2DC595023EBFL), new Color(ColorUtil.BESTCOLOR(0, 255)), new Color(ColorUtil.BESTCOLOR(0, 255)));
            RenderUtil.drawFilledBox(this.cityPos, Double.longBitsToDouble(Double.doubleToLongBits(6.668938299725268E307) ^ 0x7FD7BE015F4EAA25L), new Color(ColorUtil.BESTCOLOR(0, 120)));
        }
    }
    
    public void setCityBlock() {
        final List<EntityPlayer> possibleTargets = (List<EntityPlayer>)ModuleUtil.getAllEnemiesInRange((double)AutoCity.range.getValue());
        final EntityPlayer possibleTarget = possibleTargets.stream().filter(AutoCity::lambda$setCityBlock$0).filter(this::lambda$setCityBlock$1).findFirst().orElse(null);
        if (possibleTarget != null) {
            this.cityPos = this.targetCityBlock(possibleTarget);
        }
    }
    
    public BlockPos targetCityBlock(final EntityPlayer entityPlayer) {
        final BlockPos[] surroundPos = { new BlockPos(-1, 0, 0), new BlockPos(1, 0, 0), new BlockPos(0, 0, 1), new BlockPos(0, 0, -1) };
        final List<BlockPos> potentialPos = new ArrayList<BlockPos>();
        for (final BlockPos pos : surroundPos) {
            final BlockPos checkPos = new BlockPos(entityPlayer.func_174791_d()).add(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
            if (AutoCity.mc.world.func_180495_p(checkPos).getBlock() instanceof BlockObsidian) {
                potentialPos.add(checkPos);
            }
        }
        if (potentialPos.size() == 0) {
            return null;
        }
        potentialPos.sort(Comparator.comparing((Function<? super BlockPos, ? extends Comparable>)AutoCity::lambda$targetCityBlock$2));
        return potentialPos.get(0);
    }
    
    public static Double lambda$targetCityBlock$2(final BlockPos blockPos) {
        return AutoCity.mc.player.func_70011_f(blockPos.func_177958_n(), blockPos.func_177956_o(), blockPos.func_177952_p());
    }
    
    public boolean lambda$setCityBlock$1(final EntityPlayer entityPlayer) {
        return this.targetCityBlock(entityPlayer) != null;
    }
    
    public static boolean lambda$setCityBlock$0(final EntityPlayer entityPlayer) {
        return !new AxisAlignedBB(new BlockPos(AutoCity.mc.player.func_174791_d())).intersects(new AxisAlignedBB(new BlockPos(entityPlayer.func_174791_d())));
    }
    
    static {
        AutoCity.range = new SSlider("Range", Double.longBitsToDouble(Double.doubleToLongBits(35.32407427079175) ^ 0x7FB1A97B44054347L), Double.longBitsToDouble(Double.doubleToLongBits(0.088059811218953) ^ 0x7FAE8B167946FF8FL), Double.longBitsToDouble(Double.doubleToLongBits(0.19637766694734574) ^ 0x7FE922E7449A108DL), 1);
    }
}
