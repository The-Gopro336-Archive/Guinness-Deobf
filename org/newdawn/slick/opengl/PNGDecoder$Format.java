package org.newdawn.slick.opengl;

public static class Format
{
    final int numComponents;
    final boolean hasAlpha;
    
    private Format(final int numComponents, final boolean hasAlpha) {
        this.numComponents = numComponents;
        this.hasAlpha = hasAlpha;
    }
    
    public int getNumComponents() {
        return this.numComponents;
    }
    
    public boolean isHasAlpha() {
        return this.hasAlpha;
    }
}
