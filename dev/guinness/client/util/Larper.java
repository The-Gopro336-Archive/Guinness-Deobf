package dev.guinness.client.util;

public class Larper
{
    public int logoutTimes;
    public String name;
    public int pops;
    
    public Larper(final String name) {
        this.pops = 0;
        this.logoutTimes = 0;
        this.name = name;
    }
    
    public void addPop() {
        ++this.pops;
    }
    
    public void clearPops() {
        this.pops = 0;
    }
    
    public void addLogout() {
        ++this.logoutTimes;
    }
    
    public void clearLogouts() {
        this.logoutTimes = 0;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getPops() {
        return this.pops;
    }
    
    public boolean hasPops() {
        return this.pops > 0;
    }
    
    public int getLogoutTimes() {
        return this.logoutTimes;
    }
    
    public boolean hasLogouts() {
        return this.logoutTimes > 0;
    }
}
