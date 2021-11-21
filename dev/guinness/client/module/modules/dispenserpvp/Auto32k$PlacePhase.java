package dev.guinness.client.module.modules.dispenserpvp;

public enum Auto32k$PlacePhase
{
    FINISHED("FINISHED", 5), 
    HOPPER("HOPPER", 3), 
    STARTING("STARTING", 0);
    
    public static Auto32k$PlacePhase[] $VALUES;
    
    REDSTONE("REDSTONE", 2), 
    HOPPERGUI("HOPPERGUI", 4), 
    DISPENSERGUI("DISPENSERGUI", 1);
    
    public Auto32k$PlacePhase(final String name, final int ordinal) {
    }
    
    static {
        Auto32k$PlacePhase.$VALUES = new Auto32k$PlacePhase[] { Auto32k$PlacePhase.STARTING, Auto32k$PlacePhase.DISPENSERGUI, Auto32k$PlacePhase.REDSTONE, Auto32k$PlacePhase.HOPPER, Auto32k$PlacePhase.HOPPERGUI, Auto32k$PlacePhase.FINISHED };
    }
}
