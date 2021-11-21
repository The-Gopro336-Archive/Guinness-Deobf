package dev.guinness.client.gui.theme;

import dev.guinness.client.gui.theme.themes.GuinnessTheme;
import java.util.ArrayList;
import java.util.List;

public abstract class Theme
{
    public int width;
    public String name;
    public static List<Theme> themes;
    public int height;
    
    public Theme(final String name, final int width, final int height) {
        this.name = name;
        this.width = width;
        this.height = height;
    }
    
    public abstract void drawTitles(final int p0, final int p1);
    
    public abstract void drawModules(final List p0, final int p1, final int p2);
    
    public String getThemeName() {
        return this.name;
    }
    
    public int getThemeWidth() {
        return this.width;
    }
    
    public int getThemeHeight() {
        return this.height;
    }
    
    public static Theme getThemeByName(final String s) {
        return Theme.themes.stream().filter(Theme::lambda$getThemeByName$0).findFirst().orElse(null);
    }
    
    public static boolean lambda$getThemeByName$0(final String anotherString, final Theme theme) {
        return theme.getThemeName().equalsIgnoreCase(anotherString);
    }
    
    static {
        (Theme.themes = new ArrayList<Theme>()).add(new GuinnessTheme());
    }
}
