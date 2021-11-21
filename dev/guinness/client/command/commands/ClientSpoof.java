package dev.guinness.client.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;
import dev.guinness.client.Guinness;
import dev.guinness.client.util.MessageUtil;
import dev.guinness.client.command.CommandManager;
import java.util.function.Consumer;
import dev.guinness.client.command.Command;

public class ClientSpoof extends Command
{
    public ClientSpoof() {
        super("ClientSpoof", ClientSpoof::lambda$new$0);
    }
    
    public static void lambda$new$0(final String[] array) {
        if (array.length == 0) {
            MessageUtil.sendErrorMessage("Usage: " + CommandManager.PREFIX + "clientspoof [client name]");
            return;
        }
        String newName = "";
        for (final String s : array) {
            newName = newName + s + " ";
        }
        Guinness.SPOOFNAME = newName.substring(0, newName.length() - 1);
        MessageUtil.sendClientMessage("Successfully set the client spoofname to " + ChatFormatting.BLUE + newName.substring(0, newName.length() - 1));
    }
}
