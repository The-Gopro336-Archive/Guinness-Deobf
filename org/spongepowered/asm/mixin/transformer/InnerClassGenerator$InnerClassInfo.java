package org.spongepowered.asm.mixin.transformer;

import java.io.IOException;
import org.spongepowered.asm.service.MixinService;
import org.spongepowered.asm.lib.commons.Remapper;

static class InnerClassInfo extends Remapper
{
    private final String name;
    private final String originalName;
    private final MixinInfo owner;
    private final MixinTargetContext target;
    private final String ownerName;
    private final String targetName;
    
    InnerClassInfo(final String name, final String originalName, final MixinInfo owner, final MixinTargetContext target) {
        this.name = name;
        this.originalName = originalName;
        this.owner = owner;
        this.ownerName = owner.getClassRef();
        this.target = target;
        this.targetName = target.getTargetClassRef();
    }
    
    String getName() {
        return this.name;
    }
    
    String getOriginalName() {
        return this.originalName;
    }
    
    MixinInfo getOwner() {
        return this.owner;
    }
    
    MixinTargetContext getTarget() {
        return this.target;
    }
    
    String getOwnerName() {
        return this.ownerName;
    }
    
    String getTargetName() {
        return this.targetName;
    }
    
    byte[] getClassBytes() throws ClassNotFoundException, IOException {
        return MixinService.getService().getBytecodeProvider().getClassBytes(this.originalName, true);
    }
    
    @Override
    public String mapMethodName(final String owner, final String name, final String desc) {
        if (this.ownerName.equalsIgnoreCase(owner)) {
            final ClassInfo.Method method = this.owner.getClassInfo().findMethod(name, desc, 10);
            if (method != null) {
                return method.getName();
            }
        }
        return super.mapMethodName(owner, name, desc);
    }
    
    @Override
    public String map(final String key) {
        if (this.originalName.equals(key)) {
            return this.name;
        }
        if (this.ownerName.equals(key)) {
            return this.targetName;
        }
        return key;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}
