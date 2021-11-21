package dev.guinness.client.module.modules.movement;

import dev.guinness.client.module.modules.combat.Burrow;
import net.minecraft.entity.Entity;
import java.util.function.Predicate;
import dev.guinness.client.module.ModuleManager;
import dev.guinness.client.module.Category;
import dev.guinness.client.setting.SSlider;
import dev.guinness.client.module.Module;

public class FastFall extends Module
{
    public static SSlider height;
    
    public FastFall() {
        super("FastFall", Category.MOVEMENT, "Makes you fall faster over holes");
    }
    
    @Override
    public void onUpdate() {
        if (FastFall.mc.player.isSneaking()) {
            return;
        }
        if (ModuleManager.getModule(FastFall::lambda$onUpdate$0).isEnabled()) {
            return;
        }
        if (FastFall.mc.player != null) {
            if (FastFall.mc.player.field_70122_E) {
                if (!FastFall.mc.player.func_70090_H() && !FastFall.mc.player.func_70617_f_()) {
                    for (double y = Double.longBitsToDouble(Double.doubleToLongBits(2.256612996591667E307) ^ 0x7FC0114F67C70047L); y < (double)FastFall.height.getValue() + Double.longBitsToDouble(Double.doubleToLongBits(497.27985300225913) ^ 0x7F9F147A4724463FL); y += Double.longBitsToDouble(Double.doubleToLongBits(237.45136125610438) ^ 0x7FE9D490CA8720B0L)) {
                        if (!FastFall.mc.world.func_184144_a(FastFall.mc.player, FastFall.mc.player.func_174813_aQ().offset(Double.longBitsToDouble(Double.doubleToLongBits(2.609823141002201E307) ^ 0x7FC295227E7E2967L), -y, Double.longBitsToDouble(Double.doubleToLongBits(1.5743986476160107E308) ^ 0x7FEC0675393AA128L))).isEmpty()) {
                            FastFall.mc.player.field_70181_x = Double.longBitsToDouble(Double.doubleToLongBits(-0.15699324125205977) ^ 0x7FE0185AC26F70F6L);
                            break;
                        }
                    }
                }
            }
        }
    }
    
    public static boolean lambda$onUpdate$0(final Module module) {
        return module.getClass().equals(Burrow.class);
    }
    
    static {
        FastFall.height = new SSlider("Height", Double.longBitsToDouble(Double.doubleToLongBits(6.3311668489263715) ^ 0x7FE9531D6706A1EFL), Double.longBitsToDouble(Double.doubleToLongBits(0.31572406903177225) ^ 0x7FDC34D2B9C34BE9L), Double.longBitsToDouble(Double.doubleToLongBits(1.823668242196834) ^ 0x7FE92DBEC02FD3CDL), 0);
    }
}
