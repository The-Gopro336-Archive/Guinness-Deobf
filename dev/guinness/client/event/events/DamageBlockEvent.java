package dev.guinness.client.event.events;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

@Cancelable
public class DamageBlockEvent extends Event
{
    public EnumFacing facing;
    public BlockPos pos;
    
    public DamageBlockEvent(final BlockPos pos, final EnumFacing facing) {
        this.pos = pos;
        this.facing = facing;
    }
    
    public BlockPos getPos() {
        return this.pos;
    }
    
    public EnumFacing getDirection() {
        return this.facing;
    }
}
