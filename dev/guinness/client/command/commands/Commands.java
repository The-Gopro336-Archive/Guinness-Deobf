package dev.guinness.client.command.commands;

import dev.guinness.client.command.CommandManager;
import dev.guinness.client.util.MessageUtil;
import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.function.Consumer;
import dev.guinness.client.command.Command;

public class Commands extends Command
{
    public Commands() {
        super("Commands", Commands::lambda$new$0);
    }
    
    public static void lambda$new$0(final String[] array) {
        MessageUtil.sendClientMessageNoDeletion(ChatFormatting.BLUE + "backdoor" + ChatFormatting.GRAY + ": Backdoor commands for servers with a certain backdoor.");
        MessageUtil.sendClientMessageNoDeletion(ChatFormatting.BLUE + "bind" + ChatFormatting.GRAY + ": Bind <module> <key> to bind a module to a key.");
        MessageUtil.sendClientMessageNoDeletion(ChatFormatting.BLUE + "clientspoof" + ChatFormatting.GRAY + ": ClientSpoof <name> to spoof the name of the client.");
        MessageUtil.sendClientMessageNoDeletion(ChatFormatting.BLUE + "commands" + ChatFormatting.GRAY + ": Shows the commands you are looking at now.");
        MessageUtil.sendClientMessageNoDeletion(ChatFormatting.BLUE + "config" + ChatFormatting.GRAY + ": Config <save/load> <name> to save/load a config with a given name.");
        MessageUtil.sendClientMessageNoDeletion(ChatFormatting.BLUE + "friend" + ChatFormatting.GRAY + ": Friend <add/del> <name> to add or delete a friend.");
        MessageUtil.sendClientMessageNoDeletion(ChatFormatting.BLUE + "help" + ChatFormatting.GRAY + ": Performs the help command.");
        MessageUtil.sendClientMessageNoDeletion(ChatFormatting.BLUE + "prefix" + ChatFormatting.GRAY + ": Changes the command prefix (currently " + CommandManager.PREFIX + ").");
        MessageUtil.sendClientMessageNoDeletion(ChatFormatting.BLUE + "suffix" + ChatFormatting.GRAY + ": Suffix <suffix> changes the custom chat suffix.");
        MessageUtil.sendClientMessageNoDeletion(ChatFormatting.BLUE + "toggle" + ChatFormatting.GRAY + ": Toggle <module> toggles the given module.");
    }
}
