package dev.guinness.client.event.events;

import net.minecraftforge.fml.common.eventhandler.Event;

public class PlayerConnectionEvent$JoinEvent extends Event
{
    public String name;
    
    public PlayerConnectionEvent$JoinEvent(final String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
}
