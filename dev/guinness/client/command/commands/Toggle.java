package dev.guinness.client.command.commands;

import dev.guinness.client.module.Module;
import com.mojang.realmsclient.gui.ChatFormatting;
import dev.guinness.client.module.ModuleManager;
import dev.guinness.client.util.MessageUtil;
import dev.guinness.client.command.CommandManager;
import java.util.function.Consumer;
import dev.guinness.client.command.Command;

public class Toggle extends Command
{
    public Toggle() {
        super("Toggle", Toggle::lambda$new$1);
    }
    
    public static void lambda$new$1(final String[] array) {
        if (array.length == 0) {
            MessageUtil.sendClientMessage("Usage: " + CommandManager.PREFIX + "toggle [module]");
            return;
        }
        if (array.length == 1) {
            final Module m = ModuleManager.getModule(Toggle::lambda$null$0);
            if (m != null) {
                m.toggle();
                MessageUtil.sendClientMessage("Toggled " + ChatFormatting.BLUE + m.getName() + (m.isEnabled() ? (ChatFormatting.GREEN + " on") : (ChatFormatting.RED + " off")));
            }
            else {
                MessageUtil.sendErrorMessage("Couldn't find a module by the name of " + ChatFormatting.BLUE + array[0] + ChatFormatting.GRAY + "!");
            }
        }
    }
    
    public static boolean lambda$null$0(final String[] array, final Module module) {
        return module.getName().equalsIgnoreCase(array[0]);
    }
}
