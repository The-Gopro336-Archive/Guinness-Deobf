package dev.guinness.client.module.modules.combat;

public enum AutoCraftBed$Stage
{
    CRAFTING("CRAFTING", 2), 
    WOOD("WOOD", 3), 
    WOOL("WOOL", 4), 
    OPENING("OPENING", 1), 
    PLACING("PLACING", 0);
    
    public static AutoCraftBed$Stage[] $VALUES;
    
    FINISHED("FINISHED", 5);
    
    public AutoCraftBed$Stage(final String name, final int ordinal) {
    }
    
    static {
        AutoCraftBed$Stage.$VALUES = new AutoCraftBed$Stage[] { AutoCraftBed$Stage.PLACING, AutoCraftBed$Stage.OPENING, AutoCraftBed$Stage.CRAFTING, AutoCraftBed$Stage.WOOD, AutoCraftBed$Stage.WOOL, AutoCraftBed$Stage.FINISHED };
    }
}
