package dev.guinness.client.gui.hud;

public enum HudTextFrame$Docking
{
    RIGHT("RIGHT", 1), 
    TOPLEFT("TOPLEFT", 2);
    
    public static HudTextFrame$Docking[] $VALUES;
    
    LEFT("LEFT", 3), 
    TOPRIGHT("TOPRIGHT", 0), 
    NONE("NONE", 4);
    
    public HudTextFrame$Docking(final String name, final int ordinal) {
    }
    
    static {
        HudTextFrame$Docking.$VALUES = new HudTextFrame$Docking[] { HudTextFrame$Docking.TOPRIGHT, HudTextFrame$Docking.RIGHT, HudTextFrame$Docking.TOPLEFT, HudTextFrame$Docking.LEFT, HudTextFrame$Docking.NONE };
    }
}
