package org.spongepowered.asm.mixin;

import org.spongepowered.asm.util.JavaVersion;

public enum CompatibilityLevel
{
    JAVA_6(6, 50, false), 
    JAVA_7(7, 51, false) {
        @Override
        boolean isSupported() {
            return JavaVersion.current() >= 1.7;
        }
    }, 
    JAVA_8(8, 52, true) {
        @Override
        boolean isSupported() {
            return JavaVersion.current() >= 1.8;
        }
    }, 
    JAVA_9(9, 53, true) {
        @Override
        boolean isSupported() {
            return false;
        }
    };
    
    private static final int CLASS_V1_9 = 53;
    private final int ver;
    private final int classVersion;
    private final boolean supportsMethodsInInterfaces;
    private CompatibilityLevel maxCompatibleLevel;
    
    private CompatibilityLevel(final int ver, final int classVersion, final boolean resolveMethodsInInterfaces) {
        this.ver = ver;
        this.classVersion = classVersion;
        this.supportsMethodsInInterfaces = resolveMethodsInInterfaces;
    }
    
    private void setMaxCompatibleLevel(final CompatibilityLevel maxCompatibleLevel) {
        this.maxCompatibleLevel = maxCompatibleLevel;
    }
    
    boolean isSupported() {
        return true;
    }
    
    public int classVersion() {
        return this.classVersion;
    }
    
    public boolean supportsMethodsInInterfaces() {
        return this.supportsMethodsInInterfaces;
    }
    
    public boolean isAtLeast(final CompatibilityLevel level) {
        return level == null || this.ver >= level.ver;
    }
    
    public boolean canElevateTo(final CompatibilityLevel level) {
        return level == null || this.maxCompatibleLevel == null || level.ver <= this.maxCompatibleLevel.ver;
    }
    
    public boolean canSupport(final CompatibilityLevel level) {
        return level == null || level.canElevateTo(this);
    }
}
