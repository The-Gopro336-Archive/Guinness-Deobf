package dev.guinness.client.module.modules.render;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.Entity;
import java.util.function.Consumer;
import java.util.function.Predicate;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import dev.guinness.client.module.Category;
import dev.guinness.client.setting.SBoolean;
import dev.guinness.client.module.Module;

public class NoRender extends Module
{
    public static SBoolean witherSkulls;
    public static SBoolean fire;
    public static SBoolean portal;
    public static SBoolean items;
    public static SBoolean pumpkin;
    public static SBoolean guitint;
    public static SBoolean hurtcam;
    
    public NoRender() {
        super("NoRender", Category.RENDER, "Stops certain things from rendering");
    }
    
    @SubscribeEvent
    public void onRenderBlockOverlay(final RenderBlockOverlayEvent renderBlockOverlayEvent) {
        if (NoRender.fire.getValue()) {
            if (renderBlockOverlayEvent.getOverlayType() == RenderBlockOverlayEvent.OverlayType.FIRE) {
                renderBlockOverlayEvent.setCanceled(true);
            }
        }
    }
    
    @Override
    public void onUpdate() {
        if (NoRender.items.getValue()) {
            NoRender.mc.world.field_72996_f.stream().filter(NoRender::lambda$onUpdate$0).forEach(NoRender::lambda$onUpdate$1);
        }
    }
    
    public static void lambda$onUpdate$1(final Entity entity) {
        entity.setDead();
    }
    
    public static boolean lambda$onUpdate$0(final Entity entity) {
        return entity instanceof EntityItem;
    }
    
    static {
        NoRender.hurtcam = new SBoolean("HurtCam", true);
        NoRender.guitint = new SBoolean("Gui Tint", true);
        NoRender.portal = new SBoolean("Portal", true);
        NoRender.pumpkin = new SBoolean("Pumpkin", true);
        NoRender.items = new SBoolean("Items", false);
        NoRender.fire = new SBoolean("Fire", true);
        NoRender.witherSkulls = new SBoolean("WitherSkulls", true);
    }
}
