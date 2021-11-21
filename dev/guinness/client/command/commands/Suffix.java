package dev.guinness.client.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;
import dev.guinness.client.module.modules.misc.ChatSuffix;
import dev.guinness.client.util.MessageUtil;
import dev.guinness.client.command.CommandManager;
import java.util.function.Consumer;
import dev.guinness.client.command.Command;

public class Suffix extends Command
{
    public Suffix() {
        super("Suffix", Suffix::lambda$new$0);
    }
    
    public static void lambda$new$0(final String[] array) {
        if (array.length == 0) {
            MessageUtil.sendErrorMessage("Usage: " + CommandManager.PREFIX + "suffix [suffix]");
            return;
        }
        String newName = "";
        for (final String s : array) {
            newName = newName + s + " ";
        }
        ChatSuffix.SUFFIX = newName.substring(0, newName.length() - 1);
        MessageUtil.sendClientMessage("Successfully set the chat suffix to " + ChatFormatting.BLUE + newName.substring(0, newName.length() - 1));
    }
}
