package dev.guinness.client.command;

import java.util.function.Consumer;
import dev.guinness.client.util.Wrapper;

public class Command implements Wrapper
{
    public String name;
    public Consumer<String[]> action;
    
    public Command(final String name, final Consumer action) {
        this.name = name;
        this.action = (Consumer<String[]>)action;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Consumer getAction() {
        return this.action;
    }
}
