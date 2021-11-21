package dev.guinness.client.module.modules.misc;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import dev.guinness.client.mixin.mixins.ICPacketChatMessage;
import dev.guinness.client.util.MessageUtil;
import net.minecraft.network.play.client.CPacketChatMessage;
import dev.guinness.client.event.events.PacketEvent$PacketSendEvent;
import dev.guinness.client.module.Category;
import dev.guinness.client.setting.SMode;
import dev.guinness.client.module.Module;

public class ChatSuffix extends Module
{
    public static String SUFFIX;
    public static SMode mode;
    
    public ChatSuffix() {
        super("ChatSuffix", Category.MISC, "Appends a suffix to your chat messages");
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent$PacketSendEvent packetEvent$PacketSendEvent) {
        if (packetEvent$PacketSendEvent.getPacket() instanceof CPacketChatMessage) {
            final CPacketChatMessage packet = (CPacketChatMessage)packetEvent$PacketSendEvent.getPacket();
            String message = packet.getMessage();
            if (message.startsWith("/")) {
                return;
            }
            if (message.startsWith("?")) {
                return;
            }
            if (message.startsWith("!")) {
                return;
            }
            if (((String)ChatSuffix.mode.getValue()).equalsIgnoreCase("Guinness")) {
                message = message + " \u23d0 " + MessageUtil.toUnicode("guinness");
                ((ICPacketChatMessage)packet).setMessage(message);
            }
            if (((String)ChatSuffix.mode.getValue()).equalsIgnoreCase("Custom") && ChatSuffix.SUFFIX != null) {
                message = message + " \u23d0 " + MessageUtil.toUnicode(ChatSuffix.SUFFIX);
                ((ICPacketChatMessage)packet).setMessage(message);
            }
        }
    }
    
    static {
        ChatSuffix.mode = new SMode("Mode", new String[] { "Guinness", "Custom" });
        ChatSuffix.SUFFIX = "";
    }
}
