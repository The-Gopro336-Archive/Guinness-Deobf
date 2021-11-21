package dev.guinness.client.mixin.mixins;

import org.spongepowered.asm.mixin.gen.Accessor;
import net.minecraft.util.Session;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ Session.class })
public interface ISession
{
    @Accessor("username")
    void setUsername(final String p0);
}
