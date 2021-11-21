package dev.guinness.client.command.commands;

import dev.guinness.client.command.CommandManager;
import dev.guinness.client.util.MessageUtil;
import com.mojang.realmsclient.gui.ChatFormatting;
import dev.guinness.client.util.FileUtil;
import java.util.function.Consumer;
import dev.guinness.client.command.Command;

public class Config extends Command
{
    public Config() {
        super("Config", Config::lambda$new$0);
    }
    
    public static void lambda$new$0(final String[] array) {
        if (array.length != 0) {
            if (array.length <= 2) {
                if (array.length != 1) {
                    if (array.length == 2) {
                        if (array[0].equalsIgnoreCase("save")) {
                            FileUtil.saveConfig(array[1]);
                            MessageUtil.sendClientMessage("Successfully saved config " + ChatFormatting.BLUE + array[1]);
                        }
                    }
                    if (array.length == 2 && array[0].equalsIgnoreCase("load")) {
                        if (FileUtil.loadConfig(array[1])) {
                            MessageUtil.sendClientMessage("Successfully loaded config " + ChatFormatting.BLUE + array[1]);
                        }
                        else {
                            MessageUtil.sendErrorMessage("Could not find a config with the name " + ChatFormatting.BLUE + array[1]);
                        }
                    }
                    return;
                }
            }
        }
        MessageUtil.sendErrorMessage("Usage: " + CommandManager.PREFIX + "config save [name] or " + CommandManager.PREFIX + "config load [name]");
    }
}
