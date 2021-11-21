package dev.guinness.client.event.events;

import dev.guinness.client.module.Module;

public class ModuleToggleEvent$Enable extends ModuleToggleEvent
{
    public Module m;
    
    public ModuleToggleEvent$Enable(final Module m) {
        this.m = m;
    }
    
    public Module getModule() {
        return this.m;
    }
}
