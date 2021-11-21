package dev.guinness.client.command.commands;

import dev.guinness.client.command.CommandManager;
import dev.guinness.client.util.MessageUtil;
import dev.guinness.client.Guinness;
import java.util.function.Consumer;
import dev.guinness.client.command.Command;

public class Help extends Command
{
    public Help() {
        super("Help", Help::lambda$new$0);
    }
    
    public static void lambda$new$0(final String[] array) {
        MessageUtil.sendClientMessageNoDeletion("Welcome to " + Guinness.SPOOFNAME + "!");
        MessageUtil.sendClientMessageNoDeletion("Try " + CommandManager.PREFIX + "commands to get started.");
    }
}
