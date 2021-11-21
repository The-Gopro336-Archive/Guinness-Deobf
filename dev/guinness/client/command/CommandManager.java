package dev.guinness.client.command;

import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.function.Predicate;
import dev.guinness.client.command.commands.Toggle;
import dev.guinness.client.command.commands.Suffix;
import dev.guinness.client.command.commands.Prefix;
import dev.guinness.client.command.commands.Help;
import dev.guinness.client.command.commands.Friend;
import dev.guinness.client.command.commands.Config;
import dev.guinness.client.command.commands.Commands;
import dev.guinness.client.command.commands.ClientSpoof;
import dev.guinness.client.command.commands.Bind;
import dev.guinness.client.command.commands.Backdoor;
import java.util.ArrayList;
import java.util.List;

public class CommandManager
{
    public static String PREFIX;
    public static CommandManager INSTANCE;
    public List<Command> commands;
    
    public CommandManager() {
        (this.commands = new ArrayList<Command>()).add(new Backdoor());
        this.commands.add(new Bind());
        this.commands.add(new ClientSpoof());
        this.commands.add(new Commands());
        this.commands.add(new Config());
        this.commands.add(new Friend());
        this.commands.add(new Help());
        this.commands.add(new Prefix());
        this.commands.add(new Suffix());
        this.commands.add(new Toggle());
    }
    
    public static CommandManager getInstance() {
        return CommandManager.INSTANCE;
    }
    
    public static Command getCommand(final Predicate predicate) {
        return (Command)getCommands().stream().filter(predicate).findFirst().orElse(null);
    }
    
    public static List getModules(final Predicate predicate) {
        return (List)getCommands().stream().filter(predicate).collect(Collectors.toList());
    }
    
    public static List getCommands() {
        return getInstance().commands;
    }
    
    static {
        CommandManager.PREFIX = ",";
        CommandManager.INSTANCE = new CommandManager();
    }
}
