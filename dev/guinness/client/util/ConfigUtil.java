package dev.guinness.client.util;

import net.minecraftforge.common.config.Config;

@Config(modid = "guinness")
@Config.LangKey("guinnes.config.title")
public class ConfigUtil
{
    public static boolean ExampleBoolean;
    
    static {
        ConfigUtil.ExampleBoolean = true;
    }
}
