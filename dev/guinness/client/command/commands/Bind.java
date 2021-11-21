package dev.guinness.client.command.commands;

import dev.guinness.client.module.Module;
import dev.guinness.client.command.CommandManager;
import org.lwjgl.input.Keyboard;
import dev.guinness.client.util.FileUtil;
import dev.guinness.client.util.MessageUtil;
import com.mojang.realmsclient.gui.ChatFormatting;
import dev.guinness.client.module.ModuleManager;
import java.util.function.Consumer;
import dev.guinness.client.command.Command;

public class Bind extends Command
{
    public Bind() {
        super("Bind", Bind::lambda$new$2);
    }
    
    public static void lambda$new$2(final String[] array) {
        if (array.length != 0 && array.length <= 2) {
            if (array.length == 1) {
                final Module m = ModuleManager.getModule(Bind::lambda$null$0);
                if (m == null) {
                    MessageUtil.sendClientMessage("A module by the name of " + ChatFormatting.BLUE + array[0] + ChatFormatting.GRAY + " does not exist.");
                    return;
                }
                MessageUtil.sendClientMessage(ChatFormatting.BLUE + m.getName() + ChatFormatting.GRAY + " is currently bound to " + ChatFormatting.BLUE + m.getKeybind().getDisplayName());
            }
            if (array.length == 2) {
                final Module m = ModuleManager.getModule(Bind::lambda$null$1);
                if (m == null) {
                    MessageUtil.sendClientMessage("A module by the name of " + ChatFormatting.BLUE + array[0] + ChatFormatting.GRAY + " does not exist.");
                    return;
                }
                if (array[1].equalsIgnoreCase("none")) {
                    m.getKeybind().setKeyCode(0);
                    FileUtil.saveBinds(FileUtil.guinness);
                    MessageUtil.sendClientMessage("Successfully unbound " + ChatFormatting.BLUE + m.getName());
                    return;
                }
                m.getKeybind().setKeyCode(Keyboard.getKeyIndex(array[1].toUpperCase()));
                MessageUtil.sendClientMessage("Successfully bound " + ChatFormatting.BLUE + m.getName() + ChatFormatting.GRAY + " to " + ChatFormatting.BLUE + array[1].toUpperCase());
                FileUtil.saveBinds(FileUtil.guinness);
            }
            return;
        }
        MessageUtil.sendClientMessage("Usage: " + CommandManager.PREFIX + "bind [module] [key] or " + CommandManager.PREFIX + "bind [module] none");
    }
    
    public static boolean lambda$null$1(final String[] array, final Module module) {
        return module.getName().equalsIgnoreCase(array[0]);
    }
    
    public static boolean lambda$null$0(final String[] array, final Module module) {
        return module.getName().equalsIgnoreCase(array[0]);
    }
}
