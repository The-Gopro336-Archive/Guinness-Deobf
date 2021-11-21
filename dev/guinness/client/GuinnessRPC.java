package dev.guinness.client;

import net.minecraft.client.gui.GuiMainMenu;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import club.minnced.discord.rpc.DiscordEventHandlers;
import dev.guinness.client.util.Wrapper;

public class GuinnessRPC implements Wrapper
{
    public static Thread thread;
    public static DiscordEventHandlers HANDLERS;
    public static DiscordRichPresence PRESENCE;
    public static DiscordRPC RPC;
    
    public static void start() {
        GuinnessRPC.RPC.Discord_Initialize("811386383478030337", GuinnessRPC.HANDLERS, true, "");
        GuinnessRPC.PRESENCE.startTimestamp = System.currentTimeMillis() / ((long)(-998435980) ^ 0xFFFFFFFFC47D109CL);
        updatePresence();
        GuinnessRPC.RPC.Discord_UpdatePresence(GuinnessRPC.PRESENCE);
        (GuinnessRPC.thread = new Thread(GuinnessRPC::lambda$start$0)).start();
    }
    
    public static void updatePresence() {
        if (GuinnessRPC.mc.isSingleplayer()) {
            GuinnessRPC.PRESENCE.details = "Playing Singleplayer";
        }
        else if (GuinnessRPC.mc.currentServerData != null) {
            if (dev.guinness.client.module.modules.client.DiscordRPC.ip.getValue()) {
                GuinnessRPC.PRESENCE.details = "Playing on " + GuinnessRPC.mc.currentServerData.serverIP;
            }
            else {
                GuinnessRPC.PRESENCE.details = "Playing Multiplayer";
            }
        }
        else if (GuinnessRPC.mc.currentScreen instanceof GuiMainMenu) {
            GuinnessRPC.PRESENCE.details = "In the main menu";
        }
        GuinnessRPC.PRESENCE.state = Guinness.SPOOFNAME + " v" + "0.4.2";
        GuinnessRPC.PRESENCE.largeImageText = Guinness.SPOOFNAME + " v" + "0.4.2";
        GuinnessRPC.PRESENCE.largeImageKey = "guinness";
    }
    
    public static void shutdown() {
        if (GuinnessRPC.thread != null && !GuinnessRPC.thread.isInterrupted()) {
            GuinnessRPC.thread.interrupt();
        }
        GuinnessRPC.RPC.Discord_Shutdown();
    }
    
    public static void lambda$start$0() {
        while (!Thread.currentThread().isInterrupted()) {
            updatePresence();
            GuinnessRPC.RPC.Discord_UpdatePresence(GuinnessRPC.PRESENCE);
            try {
                Thread.sleep((long)1693140832 ^ 0x64EB40B0L);
            }
            catch (Exception ex) {}
        }
    }
    
    static {
        GuinnessRPC.RPC = DiscordRPC.INSTANCE;
        GuinnessRPC.HANDLERS = new DiscordEventHandlers();
        GuinnessRPC.PRESENCE = new DiscordRichPresence();
    }
}
