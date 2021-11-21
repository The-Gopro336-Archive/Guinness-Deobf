package org.spongepowered.asm.launch;

import net.minecraft.launchwrapper.LaunchClassLoader;
import java.io.File;
import java.util.List;
import net.minecraft.launchwrapper.ITweaker;

public class MixinTweaker implements ITweaker
{
    public MixinTweaker() {
        MixinBootstrap.start();
    }
    
    public final void acceptOptions(final List<String> args, final File gameDir, final File assetsDir, final String profile) {
        MixinBootstrap.doInit(args);
    }
    
    @Override
    public final void injectIntoClassLoader(final LaunchClassLoader classLoader) {
        MixinBootstrap.inject();
    }
    
    public String getLaunchTarget() {
        return MixinBootstrap.getPlatform().getLaunchTarget();
    }
    
    public String[] getLaunchArguments() {
        return new String[0];
    }
}
