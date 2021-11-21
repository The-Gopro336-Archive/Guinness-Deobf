package dev.guinness.client.module.modules.combat;

import java.util.Iterator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import dev.guinness.client.util.MessageUtil;
import dev.guinness.client.util.InventoryUtil;
import net.minecraft.init.Blocks;
import dev.guinness.client.util.ModuleUtil;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import dev.guinness.client.module.Category;
import net.minecraft.util.math.Vec3d;
import java.util.List;
import net.minecraft.util.math.BlockPos;
import dev.guinness.client.setting.SSlider;
import dev.guinness.client.module.Module;

public class AutoTrap extends Module
{
    public static SSlider delay;
    public BlockPos placeBlock;
    public boolean hasPlaced;
    public static SSlider range;
    public static SSlider blocksPerTick;
    public List<Vec3d> fullTrap;
    
    public AutoTrap() {
        super("AutoTrap", Category.COMBAT, "Automatically traps enemies in obsidian");
        this.fullTrap = new ArrayList<Vec3d>(Arrays.asList(new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(1.1080585284104477E308) ^ 0x7FE3B95E63C18D1FL), Double.longBitsToDouble(Double.doubleToLongBits(-6.767238230263063) ^ 0x7FEB11A6E60CE212L), Double.longBitsToDouble(Double.doubleToLongBits(-22.131312967900623) ^ 0x7FC6219DBA06AC83L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(15.774359491950909) ^ 0x7FDF8C78D8EA8D7BL), Double.longBitsToDouble(Double.doubleToLongBits(-4.956751207820242) ^ 0x7FE3D3B696AFFC5AL), Double.longBitsToDouble(Double.doubleToLongBits(8.768329932127292E307) ^ 0x7FDF375ED486239BL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(2.417987799125582E307) ^ 0x7FC13776057E0737L), Double.longBitsToDouble(Double.doubleToLongBits(-31.517906878842407) ^ 0x7FCF84958B930067L), Double.longBitsToDouble(Double.doubleToLongBits(47.7375912312313) ^ 0x7FB7DE6963B3FA37L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(-7.6560547776758) ^ 0x7FEE9FCCD2DA022AL), Double.longBitsToDouble(Double.doubleToLongBits(-6.661975035775613) ^ 0x7FEAA5DCC8A5B2E1L), Double.longBitsToDouble(Double.doubleToLongBits(1.0593470172239561E308) ^ 0x7FE2DB6489C63F85L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(2.716644783642314E307) ^ 0x7FC357D8DED81A33L), Double.longBitsToDouble(Double.doubleToLongBits(7.956874371992436E307) ^ 0x7FDC53D157938EB1L), Double.longBitsToDouble(Double.doubleToLongBits(-22.195837896258265) ^ 0x7FC632226EAFBF27L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(13.294521389852397) ^ 0x7FDA96CB81F2C6EBL), Double.longBitsToDouble(Double.doubleToLongBits(2.2839146528262458E306) ^ 0x7F8A04EB9006823FL), Double.longBitsToDouble(Double.doubleToLongBits(3.7709849665670577E307) ^ 0x7FCAD9AD89A0CBD7L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(1.1822336721087494E308) ^ 0x7FE50B6197EB66E9L), Double.longBitsToDouble(Double.doubleToLongBits(1.2417194156115714E308) ^ 0x7FE61A747197E8B2L), Double.longBitsToDouble(Double.doubleToLongBits(24.201339339794213) ^ 0x7FC8338AF997D07BL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(-6.80126630382574) ^ 0x7FEB347F276945FEL), Double.longBitsToDouble(Double.doubleToLongBits(1.2754182433308439E308) ^ 0x7FE6B404CE5C3D24L), Double.longBitsToDouble(Double.doubleToLongBits(3.103966114966597E307) ^ 0x7FC619D952F712FBL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(1.1264065437379186E308) ^ 0x7FE40CFACF033D83L), Double.longBitsToDouble(Double.doubleToLongBits(62.27082399299524) ^ 0x7FBF22AA5C50717FL), Double.longBitsToDouble(Double.doubleToLongBits(-6.589735423853453) ^ 0x7FEA5BE39A5AF91EL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(13.774848968717569) ^ 0x7FDB8CB90107F66BL), Double.longBitsToDouble(Double.doubleToLongBits(5.411371759377886) ^ 0x7FE5A53EA3741AA0L), Double.longBitsToDouble(Double.doubleToLongBits(9.792020257389555E307) ^ 0x7FE16E2D084074CEL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(7.153585164708022E307) ^ 0x7FD977B531AB6619L), Double.longBitsToDouble(Double.doubleToLongBits(4.735850826893672) ^ 0x7FE2F182E110F8B5L), Double.longBitsToDouble(Double.doubleToLongBits(22.78322397263994) ^ 0x7FC6C8815DC3EE87L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(-9.868006678490522) ^ 0x7FD3BC6B5F11A71BL), Double.longBitsToDouble(Double.doubleToLongBits(5.482733254193571) ^ 0x7FE5EE51A04DCFEAL), Double.longBitsToDouble(Double.doubleToLongBits(1.8501852163798186E307) ^ 0x7FBA58F68CC8DAA7L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(3.130640410579279E307) ^ 0x7FC64A78661EA02BL), Double.longBitsToDouble(Double.doubleToLongBits(0.29438450552329837) ^ 0x7FD2D7321BEAFD19L), Double.longBitsToDouble(Double.doubleToLongBits(-28.186630578237722) ^ 0x7FCC2FC70585F6EFL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(3.9461780509451625E307) ^ 0x7FCC1904341B4C3BL), Double.longBitsToDouble(Double.doubleToLongBits(0.2630101252714581) ^ 0x7FD0D5286BA3B299L), Double.longBitsToDouble(Double.doubleToLongBits(7.489519322470605) ^ 0x7FEDF5448DA315D7L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(3.985169416552035E307) ^ 0x7FCC6016D0A5242FL), Double.longBitsToDouble(Double.doubleToLongBits(0.09343205996648128) ^ 0x7FB7EB29D9F435EFL), Double.longBitsToDouble(Double.doubleToLongBits(6.127869132505821E306) ^ 0x7FA173E776CCDA6FL))));
    }
    
    @Override
    public void onEnable() {
        this.hasPlaced = false;
    }
    
    @Override
    public void onUpdate() {
        if (AutoTrap.mc.player.field_70173_aa % (double)AutoTrap.delay.getValue() != Double.longBitsToDouble(Double.doubleToLongBits(2.008874321299629E307) ^ 0x7FBC9B7951767DDFL)) {
            return;
        }
        if (this.hasPlaced) {
            this.disable();
        }
        int blocksPlaced = 0;
        final int oldSlot = AutoTrap.mc.player.field_71071_by.currentItem;
        final EntityPlayer target = ModuleUtil.getClosestEnemy((double)AutoTrap.range.getValue());
        if (target == null) {
            return;
        }
        for (final Vec3d autoTrapBox : this.getTrap()) {
            if (!AutoTrap.mc.world.func_180495_p(new BlockPos(autoTrapBox.add(target.func_174791_d()))).func_185904_a().isReplaceable()) {
                continue;
            }
            if (autoTrapBox.y == Double.longBitsToDouble(Double.doubleToLongBits(0.0535596057826817) ^ 0x7FAB6C2A2672507FL)) {
                if (autoTrapBox.x == Double.longBitsToDouble(Double.doubleToLongBits(1.219934582009041E307) ^ 0x7FB15F588FF5B577L)) {
                    if (autoTrapBox.z == Double.longBitsToDouble(Double.doubleToLongBits(1.2820676740552233E308) ^ 0x7FE6D251E5545C1EL)) {
                        final int obsidian = InventoryUtil.find(Blocks.OBSIDIAN);
                        if (obsidian == -1) {
                            MessageUtil.sendModuleErrorMessage(this, "No obsidian!");
                            this.disable();
                        }
                        AutoTrap.mc.player.field_71071_by.currentItem = obsidian;
                        ModuleUtil.placeBlock(new BlockPos(autoTrapBox.add(target.func_174791_d())).add(0, 0, 0), EnumFacing.SOUTH);
                        AutoTrap.mc.player.field_71071_by.currentItem = oldSlot;
                        ++blocksPlaced;
                    }
                }
            }
            if (blocksPlaced == (double)AutoTrap.blocksPerTick.getValue()) {
                return;
            }
            if (!AutoTrap.mc.world.func_180495_p(new BlockPos(autoTrapBox.add(target.func_174791_d()))).func_185904_a().isReplaceable()) {
                continue;
            }
            final int obsidian = InventoryUtil.find(Blocks.OBSIDIAN);
            if (obsidian == -1) {
                MessageUtil.sendModuleErrorMessage(this, "No obsidian!");
                this.disable();
            }
            AutoTrap.mc.player.field_71071_by.currentItem = obsidian;
            ModuleUtil.placeBlock(new BlockPos(autoTrapBox.add(target.func_174791_d())).add(0, 0, 0), EnumFacing.DOWN);
            AutoTrap.mc.player.field_71071_by.currentItem = oldSlot;
            this.placeBlock = new BlockPos(autoTrapBox.add(target.func_174791_d()));
            if (++blocksPlaced == (double)AutoTrap.blocksPerTick.getValue()) {
                return;
            }
        }
        if (blocksPlaced == 0) {
            this.hasPlaced = true;
        }
    }
    
    public List getTrap() {
        return this.fullTrap;
    }
    
    static {
        AutoTrap.delay = new SSlider("Delay", Double.longBitsToDouble(Double.doubleToLongBits(1.356164401421497E308) ^ 0x7FE823F99EE5DB88L), Double.longBitsToDouble(Double.doubleToLongBits(0.8430159284539516) ^ 0x7FE2F9FC8A56F002L), Double.longBitsToDouble(Double.doubleToLongBits(1.2847917311631876) ^ 0x7FEC8E81C638461AL), 0);
        AutoTrap.range = new SSlider("Range", Double.longBitsToDouble(Double.doubleToLongBits(3.317799242271535E307) ^ 0x7FC79F9EA9C48313L), Double.longBitsToDouble(Double.doubleToLongBits(0.22976306508131597) ^ 0x7FD168E0492D2E2DL), Double.longBitsToDouble(Double.doubleToLongBits(0.1929386959588406) ^ 0x7FECB23716A35780L), 0);
        AutoTrap.blocksPerTick = new SSlider("Blocks Per Tick", Double.longBitsToDouble(Double.doubleToLongBits(9.828923256915034E307) ^ 0x7FE17EFE0F82FB4CL), Double.longBitsToDouble(Double.doubleToLongBits(5.4659196129200796) ^ 0x7FE5DD1A07F03A15L), Double.longBitsToDouble(Double.doubleToLongBits(1.8042306102027887) ^ 0x7FE4DE20EA9435AAL), 0);
    }
}
