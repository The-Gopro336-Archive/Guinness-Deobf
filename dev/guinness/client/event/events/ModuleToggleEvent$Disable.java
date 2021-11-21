package dev.guinness.client.event.events;

import dev.guinness.client.module.Module;

public class ModuleToggleEvent$Disable extends ModuleToggleEvent
{
    public Module m;
    
    public ModuleToggleEvent$Disable(final Module m) {
        this.m = m;
    }
    
    public Module getModule() {
        return this.m;
    }
}
