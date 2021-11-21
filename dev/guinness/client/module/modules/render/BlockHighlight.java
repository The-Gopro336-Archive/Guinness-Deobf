package dev.guinness.client.module.modules.render;

import java.awt.Color;
import dev.guinness.client.util.ColorUtil;
import dev.guinness.client.util.RenderUtil;
import dev.guinness.client.module.Category;
import dev.guinness.client.setting.SMode;
import dev.guinness.client.setting.SBoolean;
import dev.guinness.client.module.Module;

public class BlockHighlight extends Module
{
    public static SBoolean gradient;
    public static SMode mode;
    
    public BlockHighlight() {
        super("BlockHighlight", Category.RENDER, "Hightlights the block you are looking at");
    }
    
    @Override
    public void onRender3d() {
        if (BlockHighlight.mc.objectMouseOver.getBlockPos() != null) {
            if (!BlockHighlight.mc.world.func_180495_p(BlockHighlight.mc.objectMouseOver.getBlockPos()).func_185904_a().isReplaceable()) {
                final String s = (String)BlockHighlight.mode.getValue();
                switch (s) {
                    case "Claw": {
                        RenderUtil.drawClawBlockOutline(BlockHighlight.mc.objectMouseOver.getBlockPos(), Double.longBitsToDouble(Double.doubleToLongBits(1.1573072733218318E308) ^ 0x7FE499CAF78EFC3FL));
                        break;
                    }
                    case "Box": {
                        if (BlockHighlight.gradient.getValue()) {
                            RenderUtil.drawBlockOutline(BlockHighlight.mc.objectMouseOver.getBlockPos(), Double.longBitsToDouble(Double.doubleToLongBits(5.304898478089415E307) ^ 0x7FD2E2D52FF70DAFL), new Color(ColorUtil.BESTCOLOR(0, 255)), new Color(ColorUtil.BESTCOLOR(8, 255)));
                            break;
                        }
                        RenderUtil.drawBlockOutline(BlockHighlight.mc.objectMouseOver.getBlockPos(), Double.longBitsToDouble(Double.doubleToLongBits(9.853217261770756E306) ^ 0x7FAC1019D6D0CFBFL), new Color(ColorUtil.BESTCOLOR(0, 255)), new Color(ColorUtil.BESTCOLOR(0, 255)));
                        break;
                    }
                }
            }
        }
    }
    
    public static boolean lambda$static$0() {
        return ((String)BlockHighlight.mode.getValue()).equalsIgnoreCase("Box");
    }
    
    static {
        BlockHighlight.mode = new SMode("Mode", new String[] { "Claw", "Box" });
        BlockHighlight.gradient = new SBoolean(BlockHighlight::lambda$static$0, "Gradient", false);
    }
}
