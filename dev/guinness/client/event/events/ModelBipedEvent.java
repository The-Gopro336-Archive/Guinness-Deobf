package dev.guinness.client.event.events;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.eventhandler.Event;

public class ModelBipedEvent extends Event
{
    public Entity entity;
    public float headYaw;
    public float headPitch;
    
    public ModelBipedEvent(final float headYaw, final float headPitch, final Entity entity) {
        this.headYaw = headYaw;
        this.headPitch = headPitch;
        this.entity = entity;
    }
    
    public Entity getEntity() {
        return this.entity;
    }
    
    public float getPitch() {
        return this.headPitch;
    }
    
    public float getYaw() {
        return this.headYaw;
    }
}
