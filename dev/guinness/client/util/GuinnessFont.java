package dev.guinness.client.util;

import net.minecraft.util.StringUtils;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.Minecraft;
import java.awt.Font;
import org.newdawn.slick.font.effects.ColorEffect;
import java.awt.Color;
import net.minecraft.client.gui.ScaledResolution;
import org.newdawn.slick.UnicodeFont;
import java.util.regex.Pattern;

public class GuinnessFont implements Wrapper
{
    public String name;
    public static Pattern colorPattern;
    public float aAFactor;
    public int height;
    public int scaleFactor;
    public float size;
    public UnicodeFont font;
    
    public GuinnessFont(final String name, final float size) {
        this.height = 9;
        this.scaleFactor = new ScaledResolution(GuinnessFont.mc).getScaleFactor();
        this.height = 9;
        this.name = name;
        this.size = size;
        final ScaledResolution sr = new ScaledResolution(GuinnessFont.mc);
        try {
            this.scaleFactor = sr.getScaleFactor();
            (this.font = new UnicodeFont(this.getFontByName(name).deriveFont(size * this.scaleFactor / Float.intBitsToFloat(Float.floatToIntBits(0.8060363f) ^ 0x7F4E5865)))).addAsciiGlyphs();
            this.font.getEffects().add(new ColorEffect(Color.WHITE));
            this.font.loadGlyphs();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        this.aAFactor = (float)sr.getScaleFactor();
    }
    
    public Font getFontByName(final String str) {
        return this.getFontFromInput("/assets/guinness/fonts/" + str + ".ttf");
    }
    
    public Font getFontFromInput(final String name) {
        return Font.createFont(0, GuinnessFont.class.getResourceAsStream(name));
    }
    
    public int drawString(final String input, float x, float y, final int value) {
        if (input == null) {
            return 0;
        }
        final ScaledResolution resolution = new ScaledResolution(Minecraft.getMinecraft());
        Label_0334: {
            Exception ex;
            try {
                if (resolution.getScaleFactor() != this.scaleFactor) {
                    this.scaleFactor = resolution.getScaleFactor();
                    (this.font = new UnicodeFont(this.getFontByName(this.name).deriveFont(this.size * this.scaleFactor / Float.intBitsToFloat(Float.floatToIntBits(0.05270439f) ^ 0x7D57E08F)))).addAsciiGlyphs();
                    this.font.getEffects().add(new ColorEffect(Color.WHITE));
                    this.font.loadGlyphs();
                }
                break Label_0334;
            }
            catch (Exception e) {
                ex = e;
            }
            ex.printStackTrace();
        }
        this.aAFactor = (float)resolution.getScaleFactor();
        GlStateManager.pushMatrix();
        GlStateManager.scale(Float.intBitsToFloat(Float.floatToIntBits(7.114549f) ^ 0x7F63AA63) / this.aAFactor, Float.intBitsToFloat(Float.floatToIntBits(12.681535f) ^ 0x7ECAE791) / this.aAFactor, Float.intBitsToFloat(Float.floatToIntBits(4.9279118f) ^ 0x7F1DB174) / this.aAFactor);
        x *= this.aAFactor;
        y *= this.aAFactor;
        final float originalX = x;
        final float red = (value >> 16 & 0xFF) / Float.intBitsToFloat(Float.floatToIntBits(0.0097143045f) ^ 0x7F6028BF);
        final float green = (value >> 8 & 0xFF) / Float.intBitsToFloat(Float.floatToIntBits(0.015493949f) ^ 0x7F02DA55);
        final float blue = (value & 0xFF) / Float.intBitsToFloat(Float.floatToIntBits(0.07875232f) ^ 0x7EDE48E5);
        final float alpha = (value >> 24 & 0xFF) / Float.intBitsToFloat(Float.floatToIntBits(0.012552184f) ^ 0x7F32A7AD);
        GlStateManager.color(red, green, blue, alpha);
        final char[] characters = input.toCharArray();
        GlStateManager.disableLighting();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.blendFunc(770, 771);
        final String[] parts = GuinnessFont.colorPattern.split(input);
        int index = 0;
        for (final String s : parts) {
            for (final String s2 : s.split("\n")) {
                for (final String s3 : s2.split("\r")) {
                    this.font.drawString(x, y, s3, new org.newdawn.slick.Color(value));
                    x += this.font.getWidth(s3);
                    index += s3.length();
                    if (index < characters.length && characters[index] == '\r') {
                        x = originalX;
                        ++index;
                    }
                }
                if (index < characters.length && characters[index] == '\n') {
                    x = originalX;
                    y += this.getHeight(s2) * Float.intBitsToFloat(Float.floatToIntBits(0.11263894f) ^ 0x7DE6AF3F);
                    ++index;
                }
            }
        }
        GlStateManager.color(Float.intBitsToFloat(Float.floatToIntBits(5.044266f) ^ 0x7F216AA1), Float.intBitsToFloat(Float.floatToIntBits(5.0125375f) ^ 0x7F2066B5), Float.intBitsToFloat(Float.floatToIntBits(7.219891f) ^ 0x7F670959), Float.intBitsToFloat(Float.floatToIntBits(5.519252f) ^ 0x7F309DB6));
        GlStateManager.popMatrix();
        return (int)x;
    }
    
    public int drawStringWithShadow(final String s, final float n, final float n2, final int n3) {
        this.drawString(StringUtils.stripControlCodes(s), n + Float.intBitsToFloat(Float.floatToIntBits(190.84471f) ^ 0x7C3ED83F), n2 + Float.intBitsToFloat(Float.floatToIntBits(20.597467f) ^ 0x7EA4C79D) - Float.intBitsToFloat(Float.floatToIntBits(0.301542f) ^ 0x7E9A63B7), 0);
        return this.drawString(s, n, n2 - Float.intBitsToFloat(Float.floatToIntBits(0.9108427f) ^ 0x7F692CFD), n3);
    }
    
    public int drawString(final String s, final float n, final float n2, final int n3, final boolean b) {
        if (b) {
            this.drawStringWithShadow(s, n, n2, n3);
        }
        else {
            this.drawString(s, n, n2, n3);
        }
        return this.drawString(s, n, n2, n3);
    }
    
    public float getHeight(final String text) {
        return this.font.getHeight(text) / Float.intBitsToFloat(Float.floatToIntBits(0.60316706f) ^ 0x7F1A6928);
    }
    
    public float getStringWidth(final String text) {
        return (float)(this.font.getWidth(text) / 2);
    }
    
    static {
        GuinnessFont.colorPattern = Pattern.compile("\u00ef¿½[0123456789abcdefklmnor]");
    }
}
