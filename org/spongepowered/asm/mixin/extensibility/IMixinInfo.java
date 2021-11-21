package org.spongepowered.asm.mixin.extensibility;

import org.spongepowered.asm.mixin.MixinEnvironment;
import java.util.List;
import org.spongepowered.asm.lib.tree.ClassNode;

public interface IMixinInfo
{
    IMixinConfig getConfig();
    
    String getName();
    
    String getClassName();
    
    String getClassRef();
    
    byte[] getClassBytes();
    
    boolean isDetachedSuper();
    
    ClassNode getClassNode(final int p0);
    
    List<String> getTargetClasses();
    
    int getPriority();
    
    MixinEnvironment.Phase getPhase();
}
