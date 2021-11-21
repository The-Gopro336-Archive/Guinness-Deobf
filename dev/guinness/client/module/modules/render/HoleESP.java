package dev.guinness.client.module.modules.render;

import dev.guinness.client.util.RenderUtil;
import java.awt.Color;
import java.util.function.Consumer;
import dev.guinness.client.util.ModuleUtil;
import java.util.ArrayList;
import dev.guinness.client.module.Category;
import dev.guinness.client.setting.SBoolean;
import net.minecraft.util.math.BlockPos;
import java.util.List;
import dev.guinness.client.setting.SSlider;
import dev.guinness.client.module.Module;

public class HoleESP extends Module
{
    public static SSlider bedGreen;
    public static SSlider obsGreen;
    public static SSlider mixedRed;
    public static SSlider obsRed;
    public List<BlockPos> onlyBedrockHoles;
    public static SSlider bedBlue;
    public static SSlider obsBlue;
    public static SSlider mixedGreen;
    public static SSlider bedRed;
    public static SBoolean topOutline;
    public List<BlockPos> mixedHoles;
    public List<BlockPos> onlyObsidianHoles;
    public static SSlider range;
    public static SSlider mixedBlue;
    
    public HoleESP() {
        super("HoleESP", Category.RENDER, "Outlines safeholes in obsidian and bedrock");
        this.onlyBedrockHoles = new ArrayList<BlockPos>();
        this.onlyObsidianHoles = new ArrayList<BlockPos>();
        this.mixedHoles = new ArrayList<BlockPos>();
    }
    
    @Override
    public void onUpdate() {
        this.onlyBedrockHoles.clear();
        this.onlyObsidianHoles.clear();
        this.mixedHoles.clear();
        this.onlyBedrockHoles = (List<BlockPos>)ModuleUtil.findSafeBedrockHoles(((Double)HoleESP.range.getValue()).intValue());
        this.onlyObsidianHoles = (List<BlockPos>)ModuleUtil.findSafeObsidianHoles(((Double)HoleESP.range.getValue()).intValue());
        this.mixedHoles = (List<BlockPos>)ModuleUtil.findSafeMixedHoles(((Double)HoleESP.range.getValue()).intValue());
    }
    
    @Override
    public void onRender3d() {
        this.onlyBedrockHoles.forEach(HoleESP::lambda$onRender3d$0);
        this.onlyObsidianHoles.forEach(HoleESP::lambda$onRender3d$1);
        this.mixedHoles.forEach(HoleESP::lambda$onRender3d$2);
    }
    
    public static void lambda$onRender3d$2(final BlockPos blockPos) {
        RenderUtil.drawHoleESPHole(blockPos, Double.longBitsToDouble(Double.doubleToLongBits(1.857021197491317E307) ^ 0x7FBA71E25289536FL), new Color(((Double)HoleESP.mixedRed.getValue()).intValue(), ((Double)HoleESP.mixedGreen.getValue()).intValue(), ((Double)HoleESP.mixedBlue.getValue()).intValue()), (boolean)HoleESP.topOutline.getValue());
    }
    
    public static void lambda$onRender3d$1(final BlockPos blockPos) {
        RenderUtil.drawHoleESPHole(blockPos, Double.longBitsToDouble(Double.doubleToLongBits(1.6177478821870226E308) ^ 0x7FECCBFF8D8DC915L), new Color(((Double)HoleESP.obsRed.getValue()).intValue(), ((Double)HoleESP.obsGreen.getValue()).intValue(), ((Double)HoleESP.obsBlue.getValue()).intValue()), (boolean)HoleESP.topOutline.getValue());
    }
    
    public static void lambda$onRender3d$0(final BlockPos blockPos) {
        RenderUtil.drawHoleESPHole(blockPos, Double.longBitsToDouble(Double.doubleToLongBits(7.860529083801251E307) ^ 0x7FDBFC02743AF3B9L), new Color(((Double)HoleESP.bedRed.getValue()).intValue(), ((Double)HoleESP.bedGreen.getValue()).intValue(), ((Double)HoleESP.bedBlue.getValue()).intValue()), (boolean)HoleESP.topOutline.getValue());
    }
    
    static {
        HoleESP.topOutline = new SBoolean("Top Outline", true);
        HoleESP.range = new SSlider("Range", Double.longBitsToDouble(Double.doubleToLongBits(0.4946130281540005) ^ 0x7FDFA7BD67063495L), Double.longBitsToDouble(Double.doubleToLongBits(1.3130623339627525) ^ 0x7FED024DA65FABD6L), Double.longBitsToDouble(Double.doubleToLongBits(0.015651495011445773) ^ 0x7FB206F20CD4A697L), 0);
        HoleESP.obsRed = new SSlider("Obsidian Red", Double.longBitsToDouble(Double.doubleToLongBits(3.9898544292034303E307) ^ 0x7FCC68A0FDAEE1E7L), Double.longBitsToDouble(Double.doubleToLongBits(0.014757842703675752) ^ 0x7FE1D95C1E2CA541L), Double.longBitsToDouble(Double.doubleToLongBits(0.0100966533743118) ^ 0x7FEB4D8DE0556FD7L), 0);
        HoleESP.obsGreen = new SSlider("Obsidian Green", Double.longBitsToDouble(Double.doubleToLongBits(9.266038582328308E307) ^ 0x7FE07E7D164A01FEL), Double.longBitsToDouble(Double.doubleToLongBits(1.5337742392336683E308) ^ 0x7FEB4D559EDF2B59L), Double.longBitsToDouble(Double.doubleToLongBits(0.014283326502772361) ^ 0x7FE2A093A1A8F45EL), 0);
        HoleESP.obsBlue = new SSlider("Obsidian Blue", Double.longBitsToDouble(Double.doubleToLongBits(5.134338337959272E307) ^ 0x7FD24762CB42017BL), Double.longBitsToDouble(Double.doubleToLongBits(6.325322373161378E307) ^ 0x7FD684D64FC158CBL), Double.longBitsToDouble(Double.doubleToLongBits(0.014804592776363526) ^ 0x7FE1B1DECE70CD5BL), 0);
        HoleESP.bedRed = new SSlider("Bedrock Red", Double.longBitsToDouble(Double.doubleToLongBits(5.821465913551115E307) ^ 0x7FD4B9A095FB79C9L), Double.longBitsToDouble(Double.doubleToLongBits(1.0565921654109028E308) ^ 0x7FE2CED6C8D2D2DAL), Double.longBitsToDouble(Double.doubleToLongBits(0.11645415459911733) ^ 0x7FD22FF0817C8DAFL), 0);
        HoleESP.bedGreen = new SSlider("Bedrock Green", Double.longBitsToDouble(Double.doubleToLongBits(1.6802676405900132E308) ^ 0x7FEDE8E5D2A8D43EL), Double.longBitsToDouble(Double.doubleToLongBits(0.05705704636424321) ^ 0x7FC2D694C848145BL), Double.longBitsToDouble(Double.doubleToLongBits(0.009720069684991107) ^ 0x7FEC081DAB4B869AL), 0);
        HoleESP.bedBlue = new SSlider("Bedrock Blue", Double.longBitsToDouble(Double.doubleToLongBits(1.2552835021963609E308) ^ 0x7FE658440785B511L), Double.longBitsToDouble(Double.doubleToLongBits(3.671950974225025E306) ^ 0x7F94EA874D1FF17FL), Double.longBitsToDouble(Double.doubleToLongBits(0.08537857771248399) ^ 0x7FDA3B5ED70DDA5DL), 0);
        HoleESP.mixedRed = new SSlider("Mixed Red", Double.longBitsToDouble(Double.doubleToLongBits(6.939006708038181E307) ^ 0x7FD8B424A0961B77L), Double.longBitsToDouble(Double.doubleToLongBits(0.48760846848802725) ^ 0x7FB0D4FA265A2887L), Double.longBitsToDouble(Double.doubleToLongBits(0.05256727381089376) ^ 0x7FC50A1903B71FFFL), 0);
        HoleESP.mixedGreen = new SSlider("Mixed Green", Double.longBitsToDouble(Double.doubleToLongBits(3.922097282139496E307) ^ 0x7FCBED1F59CFB46BL), Double.longBitsToDouble(Double.doubleToLongBits(0.010672489242569394) ^ 0x7FEA3B75421C2793L), Double.longBitsToDouble(Double.doubleToLongBits(0.3293326327684553) ^ 0x7FBAF3C92DCFBFFFL), 0);
        HoleESP.mixedBlue = new SSlider("Mixed Blue", Double.longBitsToDouble(Double.doubleToLongBits(3.2084537112147356E307) ^ 0x7FC6D84E8F66EB53L), Double.longBitsToDouble(Double.doubleToLongBits(1.5117760825945986E308) ^ 0x7FEAE9170581BFDFL), Double.longBitsToDouble(Double.doubleToLongBits(0.011983374584103977) ^ 0x7FE76ABD4F79BDDFL), 0);
    }
}
