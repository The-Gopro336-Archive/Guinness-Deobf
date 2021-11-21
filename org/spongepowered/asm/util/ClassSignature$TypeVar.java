package org.spongepowered.asm.util;

static class TypeVar implements Comparable<TypeVar>
{
    private final String originalName;
    private String currentName;
    
    TypeVar(final String name) {
        this.originalName = name;
        this.currentName = name;
    }
    
    @Override
    public int compareTo(final TypeVar other) {
        return this.currentName.compareTo(other.currentName);
    }
    
    @Override
    public String toString() {
        return this.currentName;
    }
    
    String getOriginalName() {
        return this.originalName;
    }
    
    void rename(final String name) {
        this.currentName = name;
    }
    
    public boolean matches(final String originalName) {
        return this.originalName.equals(originalName);
    }
    
    @Override
    public boolean equals(final Object obj) {
        return this.currentName.equals(obj);
    }
    
    @Override
    public int hashCode() {
        return this.currentName.hashCode();
    }
}
