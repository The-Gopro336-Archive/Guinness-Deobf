package dev.guinness.client.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;
import dev.guinness.client.util.FileUtil;
import dev.guinness.client.util.MessageUtil;
import dev.guinness.client.command.CommandManager;
import java.util.function.Consumer;
import dev.guinness.client.command.Command;

public class Prefix extends Command
{
    public Prefix() {
        super("Prefix", Prefix::lambda$new$0);
    }
    
    public static void lambda$new$0(final String[] array) {
        if (array.length == 0) {
            MessageUtil.sendErrorMessage("Usage: " + CommandManager.PREFIX + "prefix [prefix]");
            return;
        }
        if (array.length > 1) {
            MessageUtil.sendErrorMessage("Usage: " + CommandManager.PREFIX + "prefix [prefix]");
            return;
        }
        if (array.length == 1) {
            CommandManager.PREFIX = array[0];
            FileUtil.saveCommandPrefix();
            MessageUtil.sendClientMessage("Successfully changed the command prefix to " + ChatFormatting.BLUE + CommandManager.PREFIX);
        }
    }
}
