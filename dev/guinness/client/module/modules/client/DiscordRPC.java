package dev.guinness.client.module.modules.client;

import dev.guinness.client.GuinnessRPC;
import dev.guinness.client.module.Category;
import dev.guinness.client.setting.SBoolean;
import dev.guinness.client.module.Module;

public class DiscordRPC extends Module
{
    public static SBoolean ip;
    
    public DiscordRPC() {
        super("DiscordRPC", Category.CLIENT, "Shows that you are playing Guinness on discord");
    }
    
    @Override
    public void onEnable() {
        GuinnessRPC.start();
    }
    
    @Override
    public void onDisable() {
        GuinnessRPC.shutdown();
    }
    
    static {
        DiscordRPC.ip = new SBoolean("Show Server IP", false);
    }
}
