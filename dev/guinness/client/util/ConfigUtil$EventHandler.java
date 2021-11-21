package dev.guinness.client.util;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.common.config.Config$Type;
import net.minecraftforge.fml.client.event.ConfigChangedEvent$OnConfigChangedEvent;
import net.minecraftforge.fml.common.Mod$EventBusSubscriber;

@Mod$EventBusSubscriber
public class ConfigUtil$EventHandler
{
    @SubscribeEvent
    public static void configChanged(final ConfigChangedEvent$OnConfigChangedEvent configChangedEvent$OnConfigChangedEvent) {
        if (configChangedEvent$OnConfigChangedEvent.getModID().equals("guinness")) {
            ConfigManager.sync("guinness", Config$Type.INSTANCE);
        }
    }
}
