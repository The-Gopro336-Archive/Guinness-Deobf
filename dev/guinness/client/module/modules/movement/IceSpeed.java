package dev.guinness.client.module.modules.movement;

import net.minecraft.init.Blocks;
import dev.guinness.client.module.Category;
import dev.guinness.client.module.Module;

public class IceSpeed extends Module
{
    public IceSpeed() {
        super("IceSpeed", Category.MOVEMENT, "Makes you move faster on ice");
    }
    
    @Override
    public void onUpdate() {
        Blocks.ICE.slipperiness = Float.intBitsToFloat(Float.floatToIntBits(28.795027f) ^ 0x7F31560A);
        Blocks.PACKED_ICE.slipperiness = Float.intBitsToFloat(Float.floatToIntBits(2.2629719f) ^ 0x7EC7DEB5);
        Blocks.FROSTED_ICE.slipperiness = Float.intBitsToFloat(Float.floatToIntBits(141.83719f) ^ 0x7DDADC6F);
    }
    
    @Override
    public void onDisable() {
        Blocks.ICE.slipperiness = Float.intBitsToFloat(Float.floatToIntBits(10.636447f) ^ 0x7E50CFAB);
        Blocks.PACKED_ICE.slipperiness = Float.intBitsToFloat(Float.floatToIntBits(22.89678f) ^ 0x7ECDCDD3);
        Blocks.FROSTED_ICE.slipperiness = Float.intBitsToFloat(Float.floatToIntBits(3.3256376f) ^ 0x7F2E3677);
    }
}
