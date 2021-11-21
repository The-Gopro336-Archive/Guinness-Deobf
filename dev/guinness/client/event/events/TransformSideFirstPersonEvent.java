package dev.guinness.client.event.events;

import net.minecraft.util.EnumHandSide;
import net.minecraftforge.fml.common.eventhandler.Event;

public class TransformSideFirstPersonEvent extends Event
{
    public EnumHandSide enumHandSide;
    
    public TransformSideFirstPersonEvent(final EnumHandSide enumHandSide) {
        this.enumHandSide = enumHandSide;
    }
    
    public EnumHandSide getEnumHandSide() {
        return this.enumHandSide;
    }
}
