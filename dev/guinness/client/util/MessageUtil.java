package dev.guinness.client.util;

import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketChatMessage;
import dev.guinness.client.module.Module;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import dev.guinness.client.Guinness;
import net.minecraft.util.text.TextFormatting;

public class MessageUtil implements Wrapper
{
    public static void sendClientMessage(final String str) {
        MessageUtil.mc.ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion((ITextComponent)new TextComponentString(TextFormatting.BLUE + "<" + Guinness.SPOOFNAME + "> " + TextFormatting.GRAY + str), 69420);
    }
    
    public static void sendClientMessageNoDeletion(final String str) {
        MessageUtil.mc.ingameGUI.getChatGUI().printChatMessage((ITextComponent)new TextComponentString(TextFormatting.BLUE + "<" + Guinness.SPOOFNAME + "> " + TextFormatting.GRAY + str));
    }
    
    public static void sendWarnMessage(final String str) {
        MessageUtil.mc.ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion((ITextComponent)new TextComponentString(TextFormatting.BLUE + "<" + Guinness.SPOOFNAME + "> " + ChatFormatting.YELLOW + "[WARN] " + TextFormatting.GRAY + str), 69420);
    }
    
    public static void sendErrorMessage(final String str) {
        MessageUtil.mc.ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion((ITextComponent)new TextComponentString(TextFormatting.BLUE + "<" + Guinness.SPOOFNAME + "> " + ChatFormatting.RED + "[ERROR] " + TextFormatting.GRAY + str), 69420);
    }
    
    public static void sendModuleMessage(final Module module, final String str) {
        MessageUtil.mc.ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion((ITextComponent)new TextComponentString(TextFormatting.BLUE + "<" + Guinness.SPOOFNAME + "> [" + module.getName() + "] " + TextFormatting.GRAY + str), 69420);
    }
    
    public static void sendModuleWarnMessage(final Module module, final String str) {
        MessageUtil.mc.ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion((ITextComponent)new TextComponentString(TextFormatting.BLUE + "<" + Guinness.SPOOFNAME + "> [" + module.getName() + "] " + ChatFormatting.YELLOW + "[WARN] " + TextFormatting.GRAY + str), 69420);
    }
    
    public static void sendModuleErrorMessage(final Module module, final String str) {
        MessageUtil.mc.ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion((ITextComponent)new TextComponentString(TextFormatting.BLUE + "<" + Guinness.SPOOFNAME + "> [" + module.getName() + "] " + ChatFormatting.RED + "[ERROR] " + TextFormatting.GRAY + str), 69420);
    }
    
    public static void sendPublicMessage(final String s) {
        MessageUtil.mc.player.connection.sendPacket((Packet)new CPacketChatMessage(s));
    }
    
    public static String toUnicode(final String s) {
        return s.toLowerCase().replace("a", "\u1d00").replace("b", "\u0299").replace("c", "\u1d04").replace("d", "\u1d05").replace("e", "\u1d07").replace("f", "\ua730").replace("g", "\u0262").replace("h", "\u029c").replace("i", "\u026a").replace("j", "\u1d0a").replace("k", "\u1d0b").replace("l", "\u029f").replace("m", "\u1d0d").replace("n", "\u0274").replace("o", "\u1d0f").replace("p", "\u1d18").replace("q", "\u01eb").replace("r", "\u0280").replace("s", "\ua731").replace("t", "\u1d1b").replace("u", "\u1d1c").replace("v", "\u1d20").replace("w", "\u1d21").replace("x", "\u02e3").replace("y", "\u028f").replace("z", "\u1d22");
    }
}
