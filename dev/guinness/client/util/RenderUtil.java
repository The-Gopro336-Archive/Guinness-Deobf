package dev.guinness.client.util;

import dev.guinness.client.Guinness;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.math.Vec3d;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.AxisAlignedBB;
import java.awt.Color;
import net.minecraft.util.math.BlockPos;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.BufferBuilder;

public class RenderUtil implements Wrapper
{
    public static BufferBuilder bufferbuilder;
    public static Tessellator tessellator;
    
    public static void glSetup2d() {
        GL11.glDisable(3553);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glShadeModel(7425);
        GL11.glBegin(7);
    }
    
    public static void glShutdown2d() {
        GL11.glEnd();
        GL11.glEnable(3553);
    }
    
    public static void glSetup3d() {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(Float.intBitsToFloat(Float.floatToIntBits(0.699028f) ^ 0x7F32F380));
    }
    
    public static void glShutdown3d() {
        GL11.glDisable(2848);
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
    
    public static void drawClawBlockOutline(final BlockPos blockPos, final double n) {
        glSetup3d();
        final Color color = new Color(ColorUtil.BESTCOLOR(0, 255));
        final AxisAlignedBB box = new AxisAlignedBB(blockPos.func_177958_n() - RenderUtil.mc.getRenderManager().viewerPosX, blockPos.func_177956_o() - RenderUtil.mc.getRenderManager().viewerPosY, blockPos.func_177952_p() - RenderUtil.mc.getRenderManager().viewerPosZ, blockPos.func_177958_n() + 1 - RenderUtil.mc.getRenderManager().viewerPosX, blockPos.func_177956_o() + 1 - RenderUtil.mc.getRenderManager().viewerPosY, blockPos.func_177952_p() + 1 - RenderUtil.mc.getRenderManager().viewerPosZ);
        drawClawOutline(RenderUtil.bufferbuilder, box.minX, box.minY, box.minZ, box.maxX, box.maxY + n, box.maxZ, color.getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.012080385f) ^ 0x7F3AECCF), color.getGreen() / Float.intBitsToFloat(Float.floatToIntBits(0.014073535f) ^ 0x7F1994AF), color.getBlue() / Float.intBitsToFloat(Float.floatToIntBits(0.009253345f) ^ 0x7F689B58), color.getAlpha() / Float.intBitsToFloat(Float.floatToIntBits(0.009359575f) ^ 0x7F6658E7));
        glShutdown3d();
    }
    
    public static void drawClawOutline(final BufferBuilder bufferBuilder, final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final float n7, final float n8, final float n9, final float n10) {
        RenderUtil.bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos(n, n2, n3).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(1.4465896E38f) ^ 0x7ED9A89B)).endVertex();
        bufferBuilder.pos(n, n2, n6 - Double.longBitsToDouble(Double.doubleToLongBits(68.83679631822288) ^ 0x7FB8AC178BBC9217L)).color(n7, n8, n9, n10).endVertex();
        bufferBuilder.pos(n, n2, n6).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(8.549613E37f) ^ 0x7E80A3E9)).endVertex();
        bufferBuilder.pos(n, n2, n3 + Double.longBitsToDouble(Double.doubleToLongBits(2.5609060969632202) ^ 0x7FEDE525CC6D3581L)).color(n7, n8, n9, n10).endVertex();
        bufferBuilder.pos(n4, n2, n3).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(2.1343232E38f) ^ 0x7F209192)).endVertex();
        bufferBuilder.pos(n4, n2, n6 - Double.longBitsToDouble(Double.doubleToLongBits(61.05710414801846) ^ 0x7FA71ED6A9C983DFL)).color(n7, n8, n9, n10).endVertex();
        bufferBuilder.pos(n4, n2, n6).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(1.7202183E38f) ^ 0x7F016A33)).endVertex();
        bufferBuilder.pos(n4, n2, n3 + Double.longBitsToDouble(Double.doubleToLongBits(19.843993134848223) ^ 0x7FDA419676B9A127L)).color(n7, n8, n9, n10).endVertex();
        bufferBuilder.pos(n, n2, n3).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(2.0393168E38f) ^ 0x7F196BD0)).endVertex();
        bufferBuilder.pos(n4 - Double.longBitsToDouble(Double.doubleToLongBits(3.0726232738268386) ^ 0x7FE10D221B4987B2L), n2, n3).color(n7, n8, n9, n10).endVertex();
        bufferBuilder.pos(n, n2, n6).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(1.4595499E38f) ^ 0x7EDB9BD1)).endVertex();
        bufferBuilder.pos(n4 - Double.longBitsToDouble(Double.doubleToLongBits(3.2167725574228725) ^ 0x7FE0226AD9BFF019L), n2, n6).color(n7, n8, n9, n10).endVertex();
        bufferBuilder.pos(n4, n2, n3).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(1.8745654E38f) ^ 0x7F0D06D1)).endVertex();
        bufferBuilder.pos(n + Double.longBitsToDouble(Double.doubleToLongBits(3.61487177874831) ^ 0x7FE572D87CBE313BL), n2, n3).color(n7, n8, n9, n10).endVertex();
        bufferBuilder.pos(n4, n2, n6).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(3.3556462E38f) ^ 0x7F7C7365)).endVertex();
        bufferBuilder.pos(n + Double.longBitsToDouble(Double.doubleToLongBits(13.83145322087564) ^ 0x7FC2302DA51638BFL), n2, n6).color(n7, n8, n9, n10).endVertex();
        bufferBuilder.pos(n, n2, n3).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(2.40857E38f) ^ 0x7F35335F)).endVertex();
        bufferBuilder.pos(n, n2 + Double.longBitsToDouble(Double.doubleToLongBits(4.593557691808267) ^ 0x7FDBC6540FF3F5D1L), n3).color(n7, n8, n9, n10).endVertex();
        bufferBuilder.pos(n, n2, n6).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(1.6907272E38f) ^ 0x7EFE6471)).endVertex();
        bufferBuilder.pos(n, n2 + Double.longBitsToDouble(Double.doubleToLongBits(5.864661275197167) ^ 0x7FDEECF05A75C239L), n6).color(n7, n8, n9, n10).endVertex();
        bufferBuilder.pos(n4, n2, n3).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(1.2621404E38f) ^ 0x7EBDE7E1)).endVertex();
        bufferBuilder.pos(n4, n2 + Double.longBitsToDouble(Double.doubleToLongBits(14.819840140867797) ^ 0x7FE43A5B8FD86ACAL), n3).color(n7, n8, n9, n10).endVertex();
        bufferBuilder.pos(n4, n2, n6).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(1.4593856E38f) ^ 0x7EDB957D)).endVertex();
        bufferBuilder.pos(n4, n2 + Double.longBitsToDouble(Double.doubleToLongBits(13.360463647660781) ^ 0x7FE32117296DAAB3L), n6).color(n7, n8, n9, n10).endVertex();
        bufferBuilder.pos(n, n5, n3).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(3.1868393E38f) ^ 0x7F6FC04B)).endVertex();
        bufferBuilder.pos(n, n5, n6 - Double.longBitsToDouble(Double.doubleToLongBits(339.52005721221605) ^ 0x7F9CA1CBBE1B71DFL)).color(n7, n8, n9, n10).endVertex();
        bufferBuilder.pos(n, n5, n6).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(7.952637E37f) ^ 0x7E6F50E3)).endVertex();
        bufferBuilder.pos(n, n5, n3 + Double.longBitsToDouble(Double.doubleToLongBits(234.98552442301585) ^ 0x7F84C610F31A51FFL)).color(n7, n8, n9, n10).endVertex();
        bufferBuilder.pos(n4, n5, n3).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(1.6692181E38f) ^ 0x7EFB27F1)).endVertex();
        bufferBuilder.pos(n4, n5, n6 - Double.longBitsToDouble(Double.doubleToLongBits(2.0605552430814233) ^ 0x7FE9E59DFABC8F04L)).color(n7, n8, n9, n10).endVertex();
        bufferBuilder.pos(n4, n5, n6).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(3.1604298E38f) ^ 0x7F6DC3AA)).endVertex();
        bufferBuilder.pos(n4, n5, n3 + Double.longBitsToDouble(Double.doubleToLongBits(2.4627790740757) ^ 0x7FEA2A5C1A7AF4BFL)).color(n7, n8, n9, n10).endVertex();
        bufferBuilder.pos(n, n5, n3).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(2.288599E38f) ^ 0x7F2C2CD0)).endVertex();
        bufferBuilder.pos(n4 - Double.longBitsToDouble(Double.doubleToLongBits(3.826273549227532) ^ 0x7FE705ACD7E242CFL), n5, n3).color(n7, n8, n9, n10).endVertex();
        bufferBuilder.pos(n, n5, n6).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(3.0317483E38f) ^ 0x7F641559)).endVertex();
        bufferBuilder.pos(n4 - Double.longBitsToDouble(Double.doubleToLongBits(2.154436272883189) ^ 0x7FE8A5D08C33523BL), n5, n6).color(n7, n8, n9, n10).endVertex();
        bufferBuilder.pos(n4, n5, n3).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(2.1770088E38f) ^ 0x7F23C7AA)).endVertex();
        bufferBuilder.pos(n + Double.longBitsToDouble(Double.doubleToLongBits(2.775799166211038) ^ 0x7FEFAD4FA8E08628L), n5, n3).color(n7, n8, n9, n10).endVertex();
        bufferBuilder.pos(n4, n5, n6).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(2.5104754E36f) ^ 0x7BF1BFFF)).endVertex();
        bufferBuilder.pos(n + Double.longBitsToDouble(Double.doubleToLongBits(51.00594256171977) ^ 0x7FA0195B204B874FL), n5, n6).color(n7, n8, n9, n10).endVertex();
        bufferBuilder.pos(n, n5, n3).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(1.9333067E38f) ^ 0x7F117222)).endVertex();
        bufferBuilder.pos(n, n5 - Double.longBitsToDouble(Double.doubleToLongBits(60.156428469835454) ^ 0x7F878D9C4084943FL), n3).color(n7, n8, n9, n10).endVertex();
        bufferBuilder.pos(n, n5, n6).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(2.1721895E38f) ^ 0x7F236AD9)).endVertex();
        bufferBuilder.pos(n, n5 - Double.longBitsToDouble(Double.doubleToLongBits(12.595978703475609) ^ 0x7FE0A8BD87788D02L), n6).color(n7, n8, n9, n10).endVertex();
        bufferBuilder.pos(n4, n5, n3).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(5.6964846E37f) ^ 0x7E2B6C1F)).endVertex();
        bufferBuilder.pos(n4, n5 - Double.longBitsToDouble(Double.doubleToLongBits(10.531591756983019) ^ 0x7FEC89B552EFB8F2L), n3).color(n7, n8, n9, n10).endVertex();
        bufferBuilder.pos(n4, n5, n6).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(3.1800167E38f) ^ 0x7F6F3CE5)).endVertex();
        bufferBuilder.pos(n4, n5 - Double.longBitsToDouble(Double.doubleToLongBits(15.539226252869973) ^ 0x7FE68D8CEF3BE8A6L), n6).color(n7, n8, n9, n10).endVertex();
        RenderUtil.tessellator.draw();
    }
    
    public static void drawBlockOutline(final BlockPos blockPos, final double n, final Color color, final Color color2) {
        glSetup3d();
        final AxisAlignedBB box = new AxisAlignedBB(blockPos.func_177958_n() - RenderUtil.mc.getRenderManager().viewerPosX, blockPos.func_177956_o() - RenderUtil.mc.getRenderManager().viewerPosY, blockPos.func_177952_p() - RenderUtil.mc.getRenderManager().viewerPosZ, blockPos.func_177958_n() + 1 - RenderUtil.mc.getRenderManager().viewerPosX, blockPos.func_177956_o() + 1 - RenderUtil.mc.getRenderManager().viewerPosY, blockPos.func_177952_p() + 1 - RenderUtil.mc.getRenderManager().viewerPosZ);
        drawGradientBlockOutline(RenderUtil.bufferbuilder, box.minX, box.minY, box.minZ, box.maxX, box.maxY + n, box.maxZ, color.getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.22063015f) ^ 0x7D1EECDF), color.getGreen() / Float.intBitsToFloat(Float.floatToIntBits(0.1053757f) ^ 0x7EA8CF37), color.getBlue() / Float.intBitsToFloat(Float.floatToIntBits(0.011668635f) ^ 0x7F402DCD), color.getAlpha() / Float.intBitsToFloat(Float.floatToIntBits(0.06348453f) ^ 0x7EFD042D), color2);
        glShutdown3d();
    }
    
    public static void drawBlockOutline(final BufferBuilder bufferBuilder, final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final float n7, final float n8, final float n9, final float n10) {
        RenderUtil.bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos(n, n2, n3).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(2.8656554E38f) ^ 0x7F579684)).endVertex();
        bufferBuilder.pos(n, n2, n3).color(n7, n8, n9, n10).endVertex();
        bufferBuilder.pos(n4, n2, n3).color(n7, n8, n9, n10).endVertex();
        bufferBuilder.pos(n4, n2, n6).color(n7, n8, n9, n10).endVertex();
        bufferBuilder.pos(n, n2, n6).color(n7, n8, n9, n10).endVertex();
        bufferBuilder.pos(n, n2, n3).color(n7, n8, n9, n10).endVertex();
        bufferBuilder.pos(n, n5, n3).color(n7, n8, n9, n10).endVertex();
        bufferBuilder.pos(n4, n5, n3).color(n7, n8, n9, n10).endVertex();
        bufferBuilder.pos(n4, n5, n6).color(n7, n8, n9, n10).endVertex();
        bufferBuilder.pos(n, n5, n6).color(n7, n8, n9, n10).endVertex();
        bufferBuilder.pos(n, n5, n3).color(n7, n8, n9, n10).endVertex();
        bufferBuilder.pos(n, n5, n6).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(3.102999E38f) ^ 0x7F697196)).endVertex();
        bufferBuilder.pos(n, n2, n6).color(n7, n8, n9, n10).endVertex();
        bufferBuilder.pos(n4, n5, n6).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(2.712419E37f) ^ 0x7DA33F6F)).endVertex();
        bufferBuilder.pos(n4, n2, n6).color(n7, n8, n9, n10).endVertex();
        bufferBuilder.pos(n4, n5, n3).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(1.6323183E38f) ^ 0x7EF59A9D)).endVertex();
        bufferBuilder.pos(n4, n2, n3).color(n7, n8, n9, n10).endVertex();
        bufferBuilder.pos(n4, n2, n3).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(2.3960505E38f) ^ 0x7F344241)).endVertex();
        RenderUtil.tessellator.draw();
    }
    
    public static void drawFilledBox(final BlockPos blockPos, final double n, final Color color) {
        glSetup3d();
        final AxisAlignedBB box = new AxisAlignedBB(blockPos.func_177958_n() - RenderUtil.mc.getRenderManager().viewerPosX, blockPos.func_177956_o() - RenderUtil.mc.getRenderManager().viewerPosY, blockPos.func_177952_p() - RenderUtil.mc.getRenderManager().viewerPosZ, blockPos.func_177958_n() + 1 - RenderUtil.mc.getRenderManager().viewerPosX, blockPos.func_177956_o() + 1 - RenderUtil.mc.getRenderManager().viewerPosY, blockPos.func_177952_p() + 1 - RenderUtil.mc.getRenderManager().viewerPosZ);
        drawFilledBox(RenderUtil.bufferbuilder, box.minX, box.minY, box.minZ, box.maxX, box.maxY + n, box.maxZ, color.getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.0100453375f) ^ 0x7F5B9533), color.getGreen() / Float.intBitsToFloat(Float.floatToIntBits(0.032454383f) ^ 0x7E7BEEE3), color.getBlue() / Float.intBitsToFloat(Float.floatToIntBits(0.01423936f) ^ 0x7F164C34), color.getAlpha() / Float.intBitsToFloat(Float.floatToIntBits(0.0104791f) ^ 0x7F54B088));
        glShutdown3d();
    }
    
    public static void drawFilledBox(final BufferBuilder bufferBuilder, final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final float n7, final float n8, final float n9, final float n10) {
        RenderUtil.bufferbuilder.begin(5, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos(n, n2, n3).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(19.645512f) ^ 0x7F51E6CF)).endVertex();
        bufferBuilder.pos(n, n2, n3).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(29.922989f) ^ 0x7F23AE85)).endVertex();
        bufferBuilder.pos(n, n2, n3).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(21.820066f) ^ 0x7F6243B2)).endVertex();
        bufferBuilder.pos(n, n2, n6).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(27.773441f) ^ 0x7F12FCCF)).endVertex();
        bufferBuilder.pos(n, n5, n3).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(2.7722783f) ^ 0x7EFDA1CF)).endVertex();
        bufferBuilder.pos(n, n5, n6).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(3.2931037f) ^ 0x7E9E0EFB)).endVertex();
        bufferBuilder.pos(n, n5, n6).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(3.856803f) ^ 0x7EBA1911)).endVertex();
        bufferBuilder.pos(n, n2, n6).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(31.58366f) ^ 0x7F30679B)).endVertex();
        bufferBuilder.pos(n4, n5, n6).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(7.4622087f) ^ 0x7E2206A7)).endVertex();
        bufferBuilder.pos(n4, n2, n6).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(27.435452f) ^ 0x7F17B703)).endVertex();
        bufferBuilder.pos(n4, n2, n6).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(25.449125f) ^ 0x7F075B02)).endVertex();
        bufferBuilder.pos(n4, n2, n3).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(191.48343f) ^ 0x7DF3B70F)).endVertex();
        bufferBuilder.pos(n4, n5, n6).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(27.862658f) ^ 0x7F122A74)).endVertex();
        bufferBuilder.pos(n4, n5, n3).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(24.682598f) ^ 0x7F09B93B)).endVertex();
        bufferBuilder.pos(n4, n5, n3).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(3.363255f) ^ 0x7E9BF35F)).endVertex();
        bufferBuilder.pos(n4, n2, n3).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(24.718199f) ^ 0x7F097212)).endVertex();
        bufferBuilder.pos(n, n5, n3).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(21.978615f) ^ 0x7F6318F9)).endVertex();
        bufferBuilder.pos(n, n2, n3).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(28.950665f) ^ 0x7F2B563B)).endVertex();
        bufferBuilder.pos(n, n2, n3).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(3.199305f) ^ 0x7E800DA7)).endVertex();
        bufferBuilder.pos(n4, n2, n3).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(27.936306f) ^ 0x7F13B143)).endVertex();
        bufferBuilder.pos(n, n2, n6).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(37.381294f) ^ 0x7CD94ABF)).endVertex();
        bufferBuilder.pos(n4, n2, n6).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(30.96202f) ^ 0x7F3B7EF5)).endVertex();
        bufferBuilder.pos(n4, n2, n6).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(2.750433f) ^ 0x7EFCCBD5)).endVertex();
        bufferBuilder.pos(n, n5, n3).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(2.171876f) ^ 0x7EC7CCC9)).endVertex();
        bufferBuilder.pos(n, n5, n3).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(17.19586f) ^ 0x7F455DD2)).endVertex();
        bufferBuilder.pos(n, n5, n6).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(27.98527f) ^ 0x7F132D18)).endVertex();
        bufferBuilder.pos(n4, n5, n3).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(282.71246f) ^ 0x7D4197FF)).endVertex();
        bufferBuilder.pos(n4, n5, n6).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(2.6755047f) ^ 0x7EE7F7B5)).endVertex();
        bufferBuilder.pos(n4, n5, n6).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(5.8546457f) ^ 0x7E77958F)).endVertex();
        bufferBuilder.pos(n4, n5, n6).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(18.097149f) ^ 0x7F5C0A3B)).endVertex();
        RenderUtil.tessellator.draw();
    }
    
    public static void drawGradientBlockOutline(final BufferBuilder bufferBuilder, final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final float n7, final float n8, final float n9, final float n10, final Color color) {
        GlStateManager.disableCull();
        GlStateManager.disableAlpha();
        GlStateManager.shadeModel(7425);
        RenderUtil.bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        final float red2 = color.getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.04762843f) ^ 0x7E3C1607);
        final float green2 = color.getGreen() / Float.intBitsToFloat(Float.floatToIntBits(0.049416717f) ^ 0x7E35692F);
        final float blue2 = color.getBlue() / Float.intBitsToFloat(Float.floatToIntBits(0.25677177f) ^ 0x7DFC7797);
        bufferBuilder.pos(n, n2, n3).color(n7, n8, n9, n10).endVertex();
        bufferBuilder.pos(n4, n2, n3).color(red2, green2, blue2, n10).endVertex();
        bufferBuilder.pos(n4, n2, n6).color(red2, green2, blue2, n10).endVertex();
        bufferBuilder.pos(n, n2, n6).color(n7, n8, n9, n10).endVertex();
        bufferBuilder.pos(n, n2, n3).color(n7, n8, n9, n10).endVertex();
        bufferBuilder.pos(n, n5, n3).color(n7, n8, n9, n10).endVertex();
        bufferBuilder.pos(n, n5, n6).color(n7, n8, n9, n10).endVertex();
        bufferBuilder.pos(n, n2, n6).color(n7, n8, n9, n10).endVertex();
        bufferBuilder.pos(n4, n2, n6).color(red2, green2, blue2, n10).endVertex();
        bufferBuilder.pos(n4, n5, n6).color(red2, green2, blue2, n10).endVertex();
        bufferBuilder.pos(n4, n5, n3).color(red2, green2, blue2, n10).endVertex();
        bufferBuilder.pos(n4, n2, n3).color(red2, green2, blue2, n10).endVertex();
        bufferBuilder.pos(n4, n5, n3).color(red2, green2, blue2, n10).endVertex();
        bufferBuilder.pos(n, n5, n3).color(n7, n8, n9, n10).endVertex();
        bufferBuilder.pos(n, n5, n6).color(n7, n8, n9, n10).endVertex();
        bufferBuilder.pos(n4, n5, n6).color(red2, green2, blue2, n10).endVertex();
        RenderUtil.tessellator.draw();
        GlStateManager.enableCull();
        GlStateManager.enableAlpha();
        GlStateManager.shadeModel(7424);
    }
    
    public static void drawHoleESPHole(final BlockPos blockPos, final double n, final Color color, final boolean b) {
        glSetup3d();
        final AxisAlignedBB box = new AxisAlignedBB(blockPos.func_177958_n() - RenderUtil.mc.getRenderManager().viewerPosX, blockPos.func_177956_o() - RenderUtil.mc.getRenderManager().viewerPosY, blockPos.func_177952_p() - RenderUtil.mc.getRenderManager().viewerPosZ, blockPos.func_177958_n() + 1 - RenderUtil.mc.getRenderManager().viewerPosX, blockPos.func_177956_o() + 1 - RenderUtil.mc.getRenderManager().viewerPosY, blockPos.func_177952_p() + 1 - RenderUtil.mc.getRenderManager().viewerPosZ);
        renderHoleESPHole(RenderUtil.bufferbuilder, box.minX, box.minY, box.minZ, box.maxX, box.maxY + n, box.maxZ, color.getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.01518768f) ^ 0x7F07D5BF), color.getGreen() / Float.intBitsToFloat(Float.floatToIntBits(0.0087534655f) ^ 0x7F706AB2), color.getBlue() / Float.intBitsToFloat(Float.floatToIntBits(0.08626182f) ^ 0x7ECFAA09), color.getAlpha() / Float.intBitsToFloat(Float.floatToIntBits(0.05634178f) ^ 0x7E19C6A3), b);
        glShutdown3d();
    }
    
    public static void renderHoleESPHole(final BufferBuilder bufferBuilder, final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final float n7, final float n8, final float n9, final float n10, final boolean b) {
        GlStateManager.disableCull();
        GlStateManager.disableAlpha();
        GlStateManager.shadeModel(7425);
        if (b) {
            RenderUtil.bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
            bufferBuilder.pos(n, n5, n3).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos(n4, n5, n3).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos(n4, n5, n6).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos(n, n5, n6).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos(n, n5, n3).color(n7, n8, n9, n10).endVertex();
            RenderUtil.tessellator.draw();
        }
        RenderUtil.bufferbuilder.begin(5, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos(n, n5, n3).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(25.38067f) ^ 0x7F07C750)).endVertex();
        bufferBuilder.pos(n4, n5, n3).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(6.263715f) ^ 0x7E04BC97)).endVertex();
        bufferBuilder.pos(n, n2 + Double.longBitsToDouble(Double.doubleToLongBits(38.257038926970694) ^ 0x7F9013D595FFA29FL), n3).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(2.4565756E38f) ^ 0x7F38CFED)).endVertex();
        bufferBuilder.pos(n4, n2 + Double.longBitsToDouble(Double.doubleToLongBits(18.911563930545675) ^ 0x7FE1DA6F73C6DB19L), n3).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(5.9651616E37f) ^ 0x7E3381EF)).endVertex();
        bufferBuilder.pos(n4, n5, n3).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(1.9599905E36f) ^ 0x7BBCBD7F)).endVertex();
        bufferBuilder.pos(n4, n5, n3).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(23.35512f) ^ 0x7F761B84)).endVertex();
        bufferBuilder.pos(n4, n5, n6).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(103.02675f) ^ 0x7C02C17F)).endVertex();
        bufferBuilder.pos(n4, n2 + Double.longBitsToDouble(Double.doubleToLongBits(4.5659844445165305) ^ 0x7FC170A25E2FCA23L), n3).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(5.601265E37f) ^ 0x7E288E93)).endVertex();
        bufferBuilder.pos(n4, n2 + Double.longBitsToDouble(Double.doubleToLongBits(4.004750338315116) ^ 0x7FC337EE76FDE66FL), n6).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(2.5571022E38f) ^ 0x7F405FFF)).endVertex();
        bufferBuilder.pos(n4, n5, n6).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(2.6813147E38f) ^ 0x7F49B83E)).endVertex();
        bufferBuilder.pos(n4, n5, n6).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(25.017513f) ^ 0x7F04EF13)).endVertex();
        bufferBuilder.pos(n, n5, n6).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(2.3357892f) ^ 0x7ED9B15F)).endVertex();
        bufferBuilder.pos(n4, n2 + Double.longBitsToDouble(Double.doubleToLongBits(24.472536215032036) ^ 0x7FEB4BCB11168FCFL), n6).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(9.297241E37f) ^ 0x7E8BE3AB)).endVertex();
        bufferBuilder.pos(n, n2 + Double.longBitsToDouble(Double.doubleToLongBits(2.36204006888328) ^ 0x7FD1D646704EBCDFL), n6).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(2.2470697E38f) ^ 0x7F290CFD)).endVertex();
        bufferBuilder.pos(n, n5, n6).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(1.1176844E38f) ^ 0x7EA82BA3)).endVertex();
        bufferBuilder.pos(n, n5, n6).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(2.65207f) ^ 0x7EE57749)).endVertex();
        bufferBuilder.pos(n, n5, n3).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(116.78847f) ^ 0x7C255F7F)).endVertex();
        bufferBuilder.pos(n, n2 + Double.longBitsToDouble(Double.doubleToLongBits(4.732372547088385) ^ 0x7FC1DEC0229BDD5FL), n6).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(3.081437E38f) ^ 0x7F67D251)).endVertex();
        bufferBuilder.pos(n, n2 + Double.longBitsToDouble(Double.doubleToLongBits(20.49557923518642) ^ 0x7FE74DED74EC80FDL), n3).color(n7, n8, n9, Float.intBitsToFloat(Float.floatToIntBits(2.5846124E38f) ^ 0x7F4271D3)).endVertex();
        RenderUtil.tessellator.draw();
        GlStateManager.enableCull();
        GlStateManager.enableAlpha();
        GlStateManager.shadeModel(7424);
    }
    
    public static void drawCircle(final BlockPos blockPos, final double n, final double n2, final Color color, final Color color2) {
        glSetup3d();
        final AxisAlignedBB box = new AxisAlignedBB(blockPos.func_177958_n() - RenderUtil.mc.getRenderManager().viewerPosX, blockPos.func_177956_o() - RenderUtil.mc.getRenderManager().viewerPosY, blockPos.func_177952_p() - RenderUtil.mc.getRenderManager().viewerPosZ, blockPos.func_177958_n() + 1 - RenderUtil.mc.getRenderManager().viewerPosX, blockPos.func_177956_o() + 1 - RenderUtil.mc.getRenderManager().viewerPosY, blockPos.func_177952_p() + 1 - RenderUtil.mc.getRenderManager().viewerPosZ);
        renderCircle(RenderUtil.bufferbuilder, box, n, n2, color, color2);
        glShutdown3d();
    }
    
    public static void renderCircle(final BufferBuilder bufferBuilder, final AxisAlignedBB axisAlignedBB, final double n, final double n2, final Color color, final Color color2) {
        final float red = color.getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.09405973f) ^ 0x7EBFA263);
        final float green = color.getGreen() / Float.intBitsToFloat(Float.floatToIntBits(0.00881383f) ^ 0x7F6F67E2);
        final float blue = color.getBlue() / Float.intBitsToFloat(Float.floatToIntBits(0.008068395f) ^ 0x7F7B314D);
        final float red2 = color2.getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.0103932405f) ^ 0x7F554869);
        final float green2 = color2.getGreen() / Float.intBitsToFloat(Float.floatToIntBits(0.0101895705f) ^ 0x7F59F228);
        final float blue2 = color2.getBlue() / Float.intBitsToFloat(Float.floatToIntBits(0.04023848f) ^ 0x7E5BD11B);
        GlStateManager.disableCull();
        GlStateManager.disableAlpha();
        GlStateManager.shadeModel(7425);
        RenderUtil.bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos(axisAlignedBB.maxX - Double.longBitsToDouble(Double.doubleToLongBits(3.3258652357410368) ^ 0x7FEA9B5F3B9349E6L), axisAlignedBB.minY, axisAlignedBB.maxZ - Double.longBitsToDouble(Double.doubleToLongBits(3.0713387425351564) ^ 0x7FE8921A0BF10295L) + n).color(red, green, blue, Float.intBitsToFloat(Float.floatToIntBits(12.126821f) ^ 0x7EC20775)).endVertex();
        for (int i = 0; i < 361; ++i) {
            final Color c = new Color(ColorUtil.BESTCOLOR(i / 6, 255));
            bufferBuilder.pos(axisAlignedBB.maxX - Double.longBitsToDouble(Double.doubleToLongBits(3.4943924475908426) ^ 0x7FEBF484070E5625L) + Math.sin(Math.toRadians(i)) * n, axisAlignedBB.minY, axisAlignedBB.maxZ - Double.longBitsToDouble(Double.doubleToLongBits(2.180035505873708) ^ 0x7FE170B6748EC569L) + Math.cos(Math.toRadians(i)) * n).color(c.getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.09911574f) ^ 0x7EB5FD31), c.getGreen() / Float.intBitsToFloat(Float.floatToIntBits(0.011103338f) ^ 0x7F4AEAC6), c.getBlue() / Float.intBitsToFloat(Float.floatToIntBits(0.011012881f) ^ 0x7F4B6F5F), Float.intBitsToFloat(Float.floatToIntBits(13.719199f) ^ 0x7EDB81D7)).endVertex();
        }
        RenderUtil.tessellator.draw();
        RenderUtil.bufferbuilder.begin(5, DefaultVertexFormats.POSITION_COLOR);
        for (int i = 0; i < 360; ++i) {
            final Color c = new Color(ColorUtil.BESTCOLOR(i / 6, 255));
            bufferBuilder.pos(axisAlignedBB.maxX - Double.longBitsToDouble(Double.doubleToLongBits(21.168408199457257) ^ 0x7FD52B1CCCBD0C13L) + Math.sin(Math.toRadians(i)) * n, axisAlignedBB.minY, axisAlignedBB.maxZ - Double.longBitsToDouble(Double.doubleToLongBits(17.860148308328778) ^ 0x7FD1DC32ADF5FB59L) + Math.cos(Math.toRadians(i)) * n).color(c.getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.009253376f) ^ 0x7F689B79), c.getGreen() / Float.intBitsToFloat(Float.floatToIntBits(0.015045835f) ^ 0x7F0982CE), c.getBlue() / Float.intBitsToFloat(Float.floatToIntBits(0.10712188f) ^ 0x7EA462B7), Float.intBitsToFloat(Float.floatToIntBits(8.495716f) ^ 0x7E34DD47)).endVertex();
            bufferBuilder.pos(axisAlignedBB.maxX - Double.longBitsToDouble(Double.doubleToLongBits(12.511052483536039) ^ 0x7FC905A8ABCEA75BL) + Math.sin(Math.toRadians(i)) * n, axisAlignedBB.minY + n2, axisAlignedBB.maxZ - Double.longBitsToDouble(Double.doubleToLongBits(22.499547648576335) ^ 0x7FD67FE25ACD6DD7L) + Math.cos(Math.toRadians(i)) * n).color(c.getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.11554704f) ^ 0x7E93A3ED), c.getGreen() / Float.intBitsToFloat(Float.floatToIntBits(0.4653487f) ^ 0x7D91422F), c.getBlue() / Float.intBitsToFloat(Float.floatToIntBits(0.011744561f) ^ 0x7F3F6C42), Float.intBitsToFloat(Float.floatToIntBits(3.2699992E38f) ^ 0x7F7601E5)).endVertex();
            bufferBuilder.pos(axisAlignedBB.maxX - Double.longBitsToDouble(Double.doubleToLongBits(43.823459124280355) ^ 0x7FA5E9671BCC303FL) + Math.sin(Math.toRadians(i + 1)) * n, axisAlignedBB.minY, axisAlignedBB.maxZ - Double.longBitsToDouble(Double.doubleToLongBits(3.8602556055677395) ^ 0x7FEEE1CDB0E0E9B6L) + Math.cos(Math.toRadians(i + 1)) * n).color(c.getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.010982755f) ^ 0x7F4CF103), c.getGreen() / Float.intBitsToFloat(Float.floatToIntBits(0.11211056f) ^ 0x7E9A9A39), c.getBlue() / Float.intBitsToFloat(Float.floatToIntBits(0.010084451f) ^ 0x7F5A3941), Float.intBitsToFloat(Float.floatToIntBits(357.41248f) ^ 0x7C8187FF)).endVertex();
            bufferBuilder.pos(axisAlignedBB.maxX - Double.longBitsToDouble(Double.doubleToLongBits(18.12502371312355) ^ 0x7FD220018DD71713L) + Math.sin(Math.toRadians(i + 1)) * n, axisAlignedBB.minY + n2, axisAlignedBB.maxZ - Double.longBitsToDouble(Double.doubleToLongBits(31.04659279765212) ^ 0x7FDF0BED816E251FL) + Math.cos(Math.toRadians(i + 1)) * n).color(c.getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.1610062f) ^ 0x7D5BDECF), c.getGreen() / Float.intBitsToFloat(Float.floatToIntBits(0.012648355f) ^ 0x7F303B0C), c.getBlue() / Float.intBitsToFloat(Float.floatToIntBits(0.010003909f) ^ 0x7F5CE76F), Float.intBitsToFloat(Float.floatToIntBits(2.5075545E38f) ^ 0x7F3CA5BE)).endVertex();
        }
        RenderUtil.tessellator.draw();
        GlStateManager.enableCull();
        GlStateManager.enableAlpha();
        GlStateManager.shadeModel(7424);
    }
    
    public static void drawSphere(final BlockPos blockPos, final double n, final double n2, final Color color, final Color color2) {
        glSetup3d();
        final AxisAlignedBB box = new AxisAlignedBB(blockPos.func_177958_n() - RenderUtil.mc.getRenderManager().viewerPosX, blockPos.func_177956_o() - RenderUtil.mc.getRenderManager().viewerPosY, blockPos.func_177952_p() - RenderUtil.mc.getRenderManager().viewerPosZ, blockPos.func_177958_n() + 1 - RenderUtil.mc.getRenderManager().viewerPosX, blockPos.func_177956_o() + 1 - RenderUtil.mc.getRenderManager().viewerPosY, blockPos.func_177952_p() + 1 - RenderUtil.mc.getRenderManager().viewerPosZ);
        renderSphere(RenderUtil.bufferbuilder, box, n, color, color2);
        glShutdown3d();
    }
    
    public static void renderSphere(final BufferBuilder bufferBuilder, final AxisAlignedBB axisAlignedBB, final double n, final Color color, final Color color2) {
        final float red = color.getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.0117700165f) ^ 0x7F3FD707);
        final float green = color.getGreen() / Float.intBitsToFloat(Float.floatToIntBits(0.01153214f) ^ 0x7F43F14D);
        final float blue = color.getBlue() / Float.intBitsToFloat(Float.floatToIntBits(0.105236776f) ^ 0x7EA88661);
        final float red2 = color2.getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.009329689f) ^ 0x7F67DB8D);
        final float green2 = color2.getGreen() / Float.intBitsToFloat(Float.floatToIntBits(0.010404404f) ^ 0x7F55773C);
        final float blue2 = color2.getBlue() / Float.intBitsToFloat(Float.floatToIntBits(0.012667955f) ^ 0x7F308D41);
        GlStateManager.disableCull();
        GlStateManager.disableAlpha();
        GlStateManager.shadeModel(7425);
        RenderUtil.bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        for (double y = Double.longBitsToDouble(Double.doubleToLongBits(1.6462976724351733E308) ^ 0x7FED4E192754CF54L); y < Double.longBitsToDouble(Double.doubleToLongBits(0.012153733325414957) ^ 0x7FD1E40E79417597L); y += Double.longBitsToDouble(Double.doubleToLongBits(11.529684568113305) ^ 0x7FD70F32D0D27C2DL)) {
            for (int i = 0; i < 360; ++i) {
                bufferBuilder.pos(axisAlignedBB.maxX - Double.longBitsToDouble(Double.doubleToLongBits(2.9656469727433277) ^ 0x7FE7B9A51EBB4FDDL) + Math.sin(Math.toRadians(i)) * y / Double.longBitsToDouble(Double.doubleToLongBits(0.011998159014954142) ^ 0x7FD1927DA4A6C7B7L), axisAlignedBB.minY + y / Double.longBitsToDouble(Double.doubleToLongBits(0.09106894645492228) ^ 0x7FEE504B62B47F3AL), axisAlignedBB.maxZ - Double.longBitsToDouble(Double.doubleToLongBits(16.816717957356236) ^ 0x7FD0D1146D94E6A5L) + Math.cos(Math.toRadians(i)) * y / Double.longBitsToDouble(Double.doubleToLongBits(0.012829898117427364) ^ 0x7FD3468FC69A57A7L)).color(red, green, blue, Float.intBitsToFloat(Float.floatToIntBits(18.147259f) ^ 0x7EA21EA5)).endVertex();
            }
        }
        RenderUtil.tessellator.draw();
        GlStateManager.enableCull();
        GlStateManager.enableAlpha();
        GlStateManager.shadeModel(7424);
    }
    
    public static void drawCsgoEspOutline(final EntityPlayer entityPlayer) {
        glSetup3d();
        GlStateManager.pushMatrix();
        final Vec3d pos = getInterpolatedPos(entityPlayer, RenderUtil.mc.getRenderPartialTicks());
        GlStateManager.translate(pos.x - RenderUtil.mc.getRenderManager().viewerPosX, pos.y - RenderUtil.mc.getRenderManager().viewerPosY, pos.z - RenderUtil.mc.getRenderManager().viewerPosZ);
        GlStateManager.glNormal3f(Float.intBitsToFloat(Float.floatToIntBits(3.1116543E38f) ^ 0x7F6A1848), Float.intBitsToFloat(Float.floatToIntBits(6.771265f) ^ 0x7F58AE34), Float.intBitsToFloat(Float.floatToIntBits(1.5286468E38f) ^ 0x7EE60155));
        GlStateManager.rotate(-RenderUtil.mc.getRenderManager().playerViewY, Float.intBitsToFloat(Float.floatToIntBits(2.3873554E38f) ^ 0x7F339ACB), Float.intBitsToFloat(Float.floatToIntBits(8.088914f) ^ 0x7E816C31), Float.intBitsToFloat(Float.floatToIntBits(1.2995466E38f) ^ 0x7EC388B7));
        GL11.glColor4f(Float.intBitsToFloat(Float.floatToIntBits(7.689919f) ^ 0x7F7613D1), Float.intBitsToFloat(Float.floatToIntBits(13.545081f) ^ 0x7ED8B8A7), Float.intBitsToFloat(Float.floatToIntBits(7.363112f) ^ 0x7F6B9E9D), Float.intBitsToFloat(Float.floatToIntBits(23.446558f) ^ 0x7EBB928D));
        GL11.glLineWidth(Float.intBitsToFloat(Float.floatToIntBits(0.17337464f) ^ 0x7E71891F));
        GL11.glEnable(2848);
        final Color color = new Color(ColorUtil.BESTCOLOR(0, 255));
        if (FriendUtil.isFriend(entityPlayer.getName())) {
            GL11.glColor4f((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), Float.intBitsToFloat(Float.floatToIntBits(2.214966f) ^ 0x7F0DC201));
        }
        else {
            GL11.glColor4f(Float.intBitsToFloat(Float.floatToIntBits(4.628846f) ^ 0x7F141F82), Float.intBitsToFloat(Float.floatToIntBits(2.6493117E38f) ^ 0x7F474FE3), Float.intBitsToFloat(Float.floatToIntBits(3.1957224E38f) ^ 0x7F706B60), Float.intBitsToFloat(Float.floatToIntBits(2.2769835f) ^ 0x7F11BA19));
        }
        GL11.glBegin(2);
        GL11.glVertex2d(-entityPlayer.field_70130_N, Double.longBitsToDouble(Double.doubleToLongBits(9.38387862697851E307) ^ 0x7FE0B43010BC7D55L));
        GL11.glVertex2d(-entityPlayer.field_70130_N, entityPlayer.field_70131_O / Float.intBitsToFloat(Float.floatToIntBits(0.06978386f) ^ 0x7DCEEAD7));
        GL11.glVertex2d(-entityPlayer.field_70130_N, Double.longBitsToDouble(Double.doubleToLongBits(1.1192271284642068E308) ^ 0x7FE3EC437325E2C1L));
        GL11.glVertex2d(-entityPlayer.field_70130_N / Float.intBitsToFloat(Float.floatToIntBits(0.70235306f) ^ 0x7F73CD69) * Float.intBitsToFloat(Float.floatToIntBits(0.65637815f) ^ 0x7F280866), Double.longBitsToDouble(Double.doubleToLongBits(1.220881388550166E307) ^ 0x7FB162CC2EB47D7FL));
        GL11.glEnd();
        GL11.glBegin(2);
        GL11.glVertex2d(-entityPlayer.field_70130_N, entityPlayer.field_70131_O);
        GL11.glVertex2d(-entityPlayer.field_70130_N / Float.intBitsToFloat(Float.floatToIntBits(0.5627812f) ^ 0x7F50126E) * Float.intBitsToFloat(Float.floatToIntBits(0.99864423f) ^ 0x7F7FA726), entityPlayer.field_70131_O);
        GL11.glVertex2d(-entityPlayer.field_70130_N, entityPlayer.field_70131_O);
        GL11.glVertex2d(-entityPlayer.field_70130_N, entityPlayer.field_70131_O / Float.intBitsToFloat(Float.floatToIntBits(0.3563603f) ^ 0x7EF674DB) * Float.intBitsToFloat(Float.floatToIntBits(0.057225104f) ^ 0x7D6A64DF));
        GL11.glEnd();
        GL11.glBegin(2);
        GL11.glVertex2d(entityPlayer.field_70130_N, entityPlayer.field_70131_O);
        GL11.glVertex2d(entityPlayer.field_70130_N / Float.intBitsToFloat(Float.floatToIntBits(0.46742693f) ^ 0x7EAF5295) * Float.intBitsToFloat(Float.floatToIntBits(0.9880195f) ^ 0x7F7CEED9), entityPlayer.field_70131_O);
        GL11.glVertex2d(entityPlayer.field_70130_N, entityPlayer.field_70131_O);
        GL11.glVertex2d(entityPlayer.field_70130_N, entityPlayer.field_70131_O / Float.intBitsToFloat(Float.floatToIntBits(0.47151145f) ^ 0x7EB169F3) * Float.intBitsToFloat(Float.floatToIntBits(0.32136068f) ^ 0x7EA48963));
        GL11.glEnd();
        GL11.glBegin(2);
        GL11.glVertex2d(entityPlayer.field_70130_N, Double.longBitsToDouble(Double.doubleToLongBits(1.6410169535808085E308) ^ 0x7FED3608C6708DE8L));
        GL11.glVertex2d(entityPlayer.field_70130_N / Float.intBitsToFloat(Float.floatToIntBits(0.7496402f) ^ 0x7F7FE86C) * Float.intBitsToFloat(Float.floatToIntBits(0.11095845f) ^ 0x7DE33E2F), Double.longBitsToDouble(Double.doubleToLongBits(9.386468371863187E307) ^ 0x7FE0B55E2E0190ACL));
        GL11.glVertex2d(entityPlayer.field_70130_N, Double.longBitsToDouble(Double.doubleToLongBits(1.5778510337162215E308) ^ 0x7FEC1630B4DBF57BL));
        GL11.glVertex2d(entityPlayer.field_70130_N, entityPlayer.field_70131_O / Float.intBitsToFloat(Float.floatToIntBits(0.015891908f) ^ 0x7CC22FBF));
        GL11.glEnd();
        glShutdown3d();
        GlStateManager.popMatrix();
    }
    
    public static Vec3d getInterpolatedPos(final Entity entity, final float n) {
        return new Vec3d(entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ).add(getInterpolatedAmount(entity, n));
    }
    
    public static Vec3d getInterpolatedAmount(final Entity entity, final double n) {
        return getInterpolatedAmount(entity, n, n, n);
    }
    
    public static Vec3d getInterpolatedAmount(final Entity entity, final double n, final double n2, final double n3) {
        return new Vec3d((entity.posX - entity.lastTickPosX) * n, (entity.posY - entity.lastTickPosY) * n2, (entity.posZ - entity.lastTickPosZ) * n3);
    }
    
    public static void drawChatboxOutline() {
        final ScaledResolution sr = new ScaledResolution(RenderUtil.mc);
        drawHorizontalGradientBox(0, sr.getScaledHeight() - 16, sr.getScaledWidth(), 1, ColorUtil.rainbow((long)(-129846390) ^ 0xFFFFFFFFF842B38BL, 255), ColorUtil.rainbow((long)316467992 ^ 0x12DCEB17L, 255));
        drawHorizontalGradientBox(0, sr.getScaledHeight() - 1, sr.getScaledWidth(), 1, ColorUtil.rainbow((long)(-20572560) ^ 0xFFFFFFFFFEC61671L, 255), ColorUtil.rainbow((long)(-859004732) ^ 0xFFFFFFFFCCCCA0CBL, 255));
        GuiScreen.func_73734_a(0, sr.getScaledHeight() - 15, 1, sr.getScaledHeight() - 1, ColorUtil.rainbow((long)(-1809041022) ^ 0xFFFFFFFF942C3983L, 255));
        GuiScreen.func_73734_a(sr.getScaledWidth() - 1, sr.getScaledHeight() - 15, sr.getScaledWidth(), sr.getScaledHeight() - 1, ColorUtil.rainbow((long)(-782302518) ^ 0xFFFFFFFFD15F02C5L, 255));
    }
    
    public static void drawHorizontalGradientBox(final int n, final int n2, final int n3, final int n4, final int rgb, final int rgb2) {
        final Color left = new Color(rgb);
        final Color right = new Color(rgb2);
        glSetup2d();
        GL11.glColor4f(left.getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.12302939f) ^ 0x7E84F6D5), left.getGreen() / Float.intBitsToFloat(Float.floatToIntBits(0.09042757f) ^ 0x7EC63217), left.getBlue() / Float.intBitsToFloat(Float.floatToIntBits(0.10351778f) ^ 0x7EAB0121), left.getAlpha() / Float.intBitsToFloat(Float.floatToIntBits(0.28591534f) ^ 0x7DED637F));
        GL11.glVertex2f((float)n, (float)n2);
        GL11.glColor4f(left.getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.011889098f) ^ 0x7F3DCA7E), left.getGreen() / Float.intBitsToFloat(Float.floatToIntBits(0.010921111f) ^ 0x7F4DEE76), left.getBlue() / Float.intBitsToFloat(Float.floatToIntBits(0.007876345f) ^ 0x7F7E0BC9), left.getAlpha() / Float.intBitsToFloat(Float.floatToIntBits(0.19212435f) ^ 0x7D3BBC3F));
        GL11.glVertex2f((float)n, (float)(n2 + n4));
        GL11.glColor4f(right.getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.010253876f) ^ 0x7F58FFE0), right.getGreen() / Float.intBitsToFloat(Float.floatToIntBits(0.012329256f) ^ 0x7F3500A6), right.getBlue() / Float.intBitsToFloat(Float.floatToIntBits(0.009848915f) ^ 0x7F5E5D58), right.getAlpha() / Float.intBitsToFloat(Float.floatToIntBits(0.05600446f) ^ 0x7E1A64EF));
        GL11.glVertex2f((float)(n + n3), (float)(n2 + n4));
        GL11.glColor4f(right.getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.116985135f) ^ 0x7E9095E7), right.getGreen() / Float.intBitsToFloat(Float.floatToIntBits(0.008322305f) ^ 0x7F775A47), right.getBlue() / Float.intBitsToFloat(Float.floatToIntBits(0.009070598f) ^ 0x7F6B9CD8), right.getAlpha() / Float.intBitsToFloat(Float.floatToIntBits(0.015589201f) ^ 0x7F0069D9));
        GL11.glVertex2f((float)(n + n3), (float)n2);
        glShutdown2d();
    }
    
    public static void drawVerticalGradientBox(final int n, final int n2, final int n3, final int n4, final int rgb, final int rgb2) {
        final Color top = new Color(rgb);
        final Color bottom = new Color(rgb2);
        glSetup2d();
        GL11.glColor4f(top.getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.462738f) ^ 0x7D93EBFF), top.getGreen() / Float.intBitsToFloat(Float.floatToIntBits(0.124300964f) ^ 0x7E819181), top.getBlue() / Float.intBitsToFloat(Float.floatToIntBits(0.11750399f) ^ 0x7E8FA5EF), Float.intBitsToFloat(Float.floatToIntBits(22.113976f) ^ 0x7EFC25A1));
        GL11.glVertex2f((float)n, (float)n2);
        GL11.glColor4f(bottom.getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.009454656f) ^ 0x7F65E7B4), bottom.getGreen() / Float.intBitsToFloat(Float.floatToIntBits(0.01415562f) ^ 0x7F18ECF9), bottom.getBlue() / Float.intBitsToFloat(Float.floatToIntBits(0.05236497f) ^ 0x7E297CA7), Float.intBitsToFloat(Float.floatToIntBits(2.9109945f) ^ 0x7F768171));
        GL11.glVertex2f((float)n, (float)(n2 + n4));
        GL11.glColor4f(bottom.getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.09159619f) ^ 0x7EC496C9), bottom.getGreen() / Float.intBitsToFloat(Float.floatToIntBits(0.109520085f) ^ 0x7E9F4C11), bottom.getBlue() / Float.intBitsToFloat(Float.floatToIntBits(0.009679385f) ^ 0x7F619649), Float.intBitsToFloat(Float.floatToIntBits(28.121315f) ^ 0x7EAC34B9));
        GL11.glVertex2f((float)(n + n3), (float)(n2 + n4));
        GL11.glColor4f(top.getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.010146001f) ^ 0x7F593B6A), top.getGreen() / Float.intBitsToFloat(Float.floatToIntBits(0.3221726f) ^ 0x7DDBF3CF), top.getBlue() / Float.intBitsToFloat(Float.floatToIntBits(0.013012374f) ^ 0x7F2A31DA), Float.intBitsToFloat(Float.floatToIntBits(12.848116f) ^ 0x7E015D2F));
        GL11.glVertex2f((float)(n + n3), (float)n2);
        glShutdown2d();
    }
    
    public static void drawHorizontalRainbowText(final String s, final int n, final int n2, final boolean b) {
        final char[] charArray = s.toCharArray();
        float xAdd = (float)n;
        int horizBoost = 0;
        for (final char c : charArray) {
            if (b) {
                Guinness.CUSTOM_FONT.drawStringWithShadow(Character.toString(c), xAdd, (float)n2, ColorUtil.BESTCOLOR(horizBoost, 255));
                ++horizBoost;
                xAdd += (float)(Guinness.CUSTOM_FONT.getStringWidth(Character.toString(c)) + Double.longBitsToDouble(Double.doubleToLongBits(3.8649877786971203) ^ 0x7FEDD84D8554BFC3L));
            }
            else {
                RenderUtil.mc.fontRenderer.drawStringWithShadow(Character.toString(c), xAdd, (float)n2, ColorUtil.BESTCOLOR(horizBoost, 255));
                ++horizBoost;
                xAdd += RenderUtil.mc.fontRenderer.getStringWidth(Character.toString(c));
            }
        }
    }
    
    static {
        RenderUtil.tessellator = Tessellator.getInstance();
        RenderUtil.bufferbuilder = RenderUtil.tessellator.getBuffer();
    }
}
