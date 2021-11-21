package dev.guinness.client.module.modules.combat;

public enum AutoCity$Stage
{
    MINING("MINING", 0);
    
    public static AutoCity$Stage[] $VALUES;
    
    WAITING("WAITING", 1);
    
    public AutoCity$Stage(final String name, final int ordinal) {
    }
    
    static {
        AutoCity$Stage.$VALUES = new AutoCity$Stage[] { AutoCity$Stage.MINING, AutoCity$Stage.WAITING };
    }
}
