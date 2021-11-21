package dev.guinness.client.module.modules.dispenserpvp;

import net.minecraft.item.ItemStack;
import dev.guinness.client.util.ModuleUtil;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import dev.guinness.client.util.RenderUtil;
import dev.guinness.client.Guinness;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import dev.guinness.client.module.Category;
import dev.guinness.client.module.Module;

public class Info32k extends Module
{
    public Info32k() {
        super("32kInfo", Category.DISPENSERPVP, "Tells you if you have a 32k in your hotbar");
    }
    
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Text text) {
        if (this.Null()) {
            return;
        }
        if (text.getType().equals(RenderGameOverlayEvent.ElementType.TEXT)) {
            final ScaledResolution sr = new ScaledResolution(Info32k.mc);
            if (this.has32k()) {
                RenderUtil.drawHorizontalRainbowText("32k in hotbar!", (int)(sr.getScaledWidth() / 2 - Guinness.CUSTOM_FONT.getStringWidth("32k in hotbar!") / Float.intBitsToFloat(Float.floatToIntBits(0.07297813f) ^ 0x7D95758F)), 2, true);
            }
            else {
                Guinness.CUSTOM_FONT.drawStringWithShadow("No 32k in hotbar!", sr.getScaledWidth() / 2 - Guinness.CUSTOM_FONT.getStringWidth("No 32k in hotbar!") / Float.intBitsToFloat(Float.floatToIntBits(0.03649288f) ^ 0x7D15798F), Float.intBitsToFloat(Float.floatToIntBits(0.48312888f) ^ 0x7EF75CAB), -65536);
            }
        }
    }
    
    public boolean has32k() {
        for (int i = 0; i < 9; ++i) {
            final ItemStack threetwokay = Info32k.mc.player.field_71071_by.getStackInSlot(i);
            if (ModuleUtil.is32k(threetwokay)) {
                return true;
            }
        }
        return false;
    }
}
