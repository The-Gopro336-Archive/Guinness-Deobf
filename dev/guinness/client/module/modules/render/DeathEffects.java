package dev.guinness.client.module.modules.render;

import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.entity.effect.EntityLightningBolt;
import dev.guinness.client.util.RenderUtil;
import java.awt.Color;
import dev.guinness.client.util.ColorUtil;
import net.minecraft.util.math.BlockPos;
import java.util.function.Consumer;
import dev.guinness.client.module.Category;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.entity.player.EntityPlayer;
import dev.guinness.client.setting.SBoolean;
import dev.guinness.client.module.Module;

public class DeathEffects extends Module
{
    public double radius3;
    public double radius1;
    public static SBoolean lightning;
    public double radius2;
    public EntityPlayer guyWhoDied;
    public static SBoolean sphere;
    public boolean render1;
    public ConcurrentHashMap<EntityPlayer, Boolean> players;
    
    public DeathEffects() {
        super("DeathEffects", Category.RENDER, "Creates effects on player deaths");
        this.players = new ConcurrentHashMap<EntityPlayer, Boolean>();
        this.radius1 = Double.longBitsToDouble(Double.doubleToLongBits(1.1988102641002708E308) ^ 0x7FE556EB819763C0L);
        this.radius2 = Double.longBitsToDouble(Double.doubleToLongBits(8.839882857875549E307) ^ 0x7FDF7895440997CFL);
        this.radius3 = Double.longBitsToDouble(Double.doubleToLongBits(1.9255068050924775E307) ^ 0x7FBB6B8D72C895A7L);
    }
    
    @Override
    public void onEnable() {
        this.render1 = true;
    }
    
    @Override
    public void onDisable() {
        this.render1 = false;
        this.radius1 = Double.longBitsToDouble(Double.doubleToLongBits(1.6209752750833143E307) ^ 0x7FB7155D402FBE47L);
        this.radius2 = Double.longBitsToDouble(Double.doubleToLongBits(1.3429196469073915E308) ^ 0x7FE7E79E8FB0B093L);
        this.radius3 = Double.longBitsToDouble(Double.doubleToLongBits(1.631904780964945E308) ^ 0x7FED0C82B37FE189L);
    }
    
    @Override
    public void onUpdate() {
        this.players.clear();
        DeathEffects.mc.world.field_73010_i.forEach(this::lambda$onUpdate$0);
    }
    
    @Override
    public void onRender3d() {
        if (this.render1) {
            this.renderSphere1(this.guyWhoDied);
        }
        if (this.radius1 > Double.longBitsToDouble(Double.doubleToLongBits(0.7992238753083167) ^ 0x7FE9933DF2D43616L)) {
            this.renderSphere2(this.guyWhoDied);
        }
        if (this.radius2 > Double.longBitsToDouble(Double.doubleToLongBits(0.9502451017604436) ^ 0x7FEE68686A67D97AL)) {
            this.renderSphere3(this.guyWhoDied);
        }
        if (this.radius1 > Double.longBitsToDouble(Double.doubleToLongBits(0.038371808209669074) ^ 0x7FEDA5783AB2A4B9L)) {
            this.render1 = false;
            this.radius1 = Double.longBitsToDouble(Double.doubleToLongBits(2.476008644466022E307) ^ 0x7FC1A13864BBBF73L);
            this.radius2 = Double.longBitsToDouble(Double.doubleToLongBits(1.780764098344627E308) ^ 0x7FEFB2DAEEC24AC1L);
            this.radius3 = Double.longBitsToDouble(Double.doubleToLongBits(5.667308539648533E307) ^ 0x7FD42D2137FE244BL);
        }
    }
    
    public void renderSphere1(final EntityPlayer entityPlayer) {
        RenderUtil.drawCircle(new BlockPos(entityPlayer.func_174791_d()), this.radius1, Double.longBitsToDouble(Double.doubleToLongBits(1.3437634176465917E308) ^ 0x7FE7EB76E30F92E7L), new Color(ColorUtil.BESTCOLOR(0, 255)), new Color(ColorUtil.BESTCOLOR(10, 255)));
        this.radius1 += Double.longBitsToDouble(Double.doubleToLongBits(12.047918259207952) ^ 0x7FE181112461C241L);
    }
    
    public void renderSphere2(final EntityPlayer entityPlayer) {
        RenderUtil.drawCircle(new BlockPos(entityPlayer.func_174791_d()), this.radius2, Double.longBitsToDouble(Double.doubleToLongBits(1.2607149788534843E308) ^ 0x7FE6710447619154L), new Color(ColorUtil.BESTCOLOR(0, 255)), new Color(ColorUtil.BESTCOLOR(10, 255)));
        this.radius2 += Double.longBitsToDouble(Double.doubleToLongBits(233.37566664759757) ^ 0x7FA4B59CEF962DBFL);
    }
    
    public void renderSphere3(final EntityPlayer entityPlayer) {
        RenderUtil.drawCircle(new BlockPos(entityPlayer.func_174791_d()), this.radius3, Double.longBitsToDouble(Double.doubleToLongBits(7.737060121666812E307) ^ 0x7FDB8B7B2E7241EBL), new Color(ColorUtil.BESTCOLOR(0, 255)), new Color(ColorUtil.BESTCOLOR(10, 255)));
        this.radius3 += Double.longBitsToDouble(Double.doubleToLongBits(15.104321233368143) ^ 0x7FE7ACF00E22A9F0L);
    }
    
    public void lambda$onUpdate$0(final EntityPlayer entityPlayer) {
        this.players.put(entityPlayer, entityPlayer.func_110143_aJ() <= Float.intBitsToFloat(Float.floatToIntBits(1.4377225E37f) ^ 0x7D2D0F3F));
        if (this.players.get(entityPlayer)) {
            if (DeathEffects.lightning.getValue()) {
                final EntityLightningBolt bolt = new EntityLightningBolt(DeathEffects.mc.world, entityPlayer.field_70165_t, entityPlayer.field_70163_u, entityPlayer.field_70161_v, true);
                DeathEffects.mc.world.spawnEntity((Entity)bolt);
                final ResourceLocation rl = new ResourceLocation("minecraft", "entity.lightning.thunder");
                final ResourceLocation rl2 = new ResourceLocation("minecraft", "entity.lightning.impact");
                final SoundEvent sound = new SoundEvent(rl);
                final SoundEvent sound2 = new SoundEvent(rl2);
                DeathEffects.mc.world.func_184133_a(DeathEffects.mc.player, new BlockPos(entityPlayer.field_70165_t, entityPlayer.field_70163_u, entityPlayer.field_70161_v), sound, SoundCategory.WEATHER, Float.intBitsToFloat(Float.floatToIntBits(5.1964397f) ^ 0x7F26493C), Float.intBitsToFloat(Float.floatToIntBits(13.678651f) ^ 0x7EDADBC1));
                DeathEffects.mc.world.func_184133_a(DeathEffects.mc.player, new BlockPos(entityPlayer.field_70165_t, entityPlayer.field_70163_u, entityPlayer.field_70161_v), sound2, SoundCategory.WEATHER, Float.intBitsToFloat(Float.floatToIntBits(7.520148f) ^ 0x7F70A50D), Float.intBitsToFloat(Float.floatToIntBits(5.243988f) ^ 0x7F27CEC0));
            }
            if (DeathEffects.sphere.getValue()) {
                this.guyWhoDied = entityPlayer;
                this.render1 = true;
                this.radius1 = Double.longBitsToDouble(Double.doubleToLongBits(9.95061793407031E307) ^ 0x7FE1B672B6624028L);
                this.radius2 = Double.longBitsToDouble(Double.doubleToLongBits(1.8216793267708318E307) ^ 0x7FB9F10B0A4E2107L);
                this.radius3 = Double.longBitsToDouble(Double.doubleToLongBits(1.337745005292289E308) ^ 0x7FE7D009EE2AE690L);
            }
            DeathEffects.mc.world.removeEntity((Entity)entityPlayer);
            this.players.remove(entityPlayer);
        }
    }
    
    static {
        DeathEffects.lightning = new SBoolean("Lightning", true);
        DeathEffects.sphere = new SBoolean("Circles", false);
    }
}
