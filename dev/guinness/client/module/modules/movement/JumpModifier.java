package dev.guinness.client.module.modules.movement;

import dev.guinness.client.module.Category;
import dev.guinness.client.module.Module;

public class JumpModifier extends Module
{
    public float oldModifier;
    
    public JumpModifier() {
        super("JumpModifier", Category.MOVEMENT, "LArp");
    }
    
    @Override
    public void onEnable() {
        this.oldModifier = JumpModifier.mc.player.field_70747_aH;
    }
    
    @Override
    public void onUpdate() {
        JumpModifier.mc.player.field_70747_aH = Float.intBitsToFloat(Float.floatToIntBits(4.043393f) ^ 0x7DCDAFB7);
    }
    
    @Override
    public void onDisable() {
        JumpModifier.mc.player.field_70747_aH = this.oldModifier;
    }
}
