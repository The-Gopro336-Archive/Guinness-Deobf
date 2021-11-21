package dev.guinness.client.module;

public enum Category
{
    CLIENT("CLIENT", 0, "Client"), 
    COMBAT("COMBAT", 1, "Combat"), 
    MOVEMENT("MOVEMENT", 4, "Movement"), 
    EXPLOIT("EXPLOIT", 2, "Exploit"), 
    MISC("MISC", 3, "Misc");
    
    public String name;
    
    HIDDEN("HIDDEN", 8, "Hidden"), 
    HUD("HUD", 7, "Hud");
    
    public static Category[] $VALUES;
    
    DISPENSERPVP("DISPENSERPVP", 6, "32k"), 
    RENDER("RENDER", 5, "Render");
    
    public Category(final String name2, final int ordinal, final String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    static {
        Category.$VALUES = new Category[] { Category.CLIENT, Category.COMBAT, Category.EXPLOIT, Category.MISC, Category.MOVEMENT, Category.RENDER, Category.DISPENSERPVP, Category.HUD, Category.HIDDEN };
    }
}
