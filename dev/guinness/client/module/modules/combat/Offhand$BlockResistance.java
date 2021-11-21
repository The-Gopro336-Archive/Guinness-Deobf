package dev.guinness.client.module.modules.combat;

public enum Offhand$BlockResistance
{
    public static Offhand$BlockResistance[] $VALUES;
    
    Resistant("Resistant", 2), 
    Breakable("Breakable", 1), 
    Blank("Blank", 0), 
    Unbreakable("Unbreakable", 3);
    
    public Offhand$BlockResistance(final String name, final int ordinal) {
    }
    
    static {
        Offhand$BlockResistance.$VALUES = new Offhand$BlockResistance[] { Offhand$BlockResistance.Blank, Offhand$BlockResistance.Breakable, Offhand$BlockResistance.Resistant, Offhand$BlockResistance.Unbreakable };
    }
}
