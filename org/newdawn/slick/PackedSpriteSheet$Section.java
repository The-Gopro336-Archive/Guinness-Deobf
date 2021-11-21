package org.newdawn.slick;

import java.io.IOException;
import java.io.BufferedReader;

private class Section
{
    public int x;
    public int y;
    public int width;
    public int height;
    public int tilesx;
    public int tilesy;
    public String name;
    
    public Section(final BufferedReader reader) throws IOException {
        this.name = reader.readLine().trim();
        this.x = Integer.parseInt(reader.readLine().trim());
        this.y = Integer.parseInt(reader.readLine().trim());
        this.width = Integer.parseInt(reader.readLine().trim());
        this.height = Integer.parseInt(reader.readLine().trim());
        this.tilesx = Integer.parseInt(reader.readLine().trim());
        this.tilesy = Integer.parseInt(reader.readLine().trim());
        reader.readLine().trim();
        reader.readLine().trim();
        this.tilesx = Math.max(1, this.tilesx);
        this.tilesy = Math.max(1, this.tilesy);
    }
}
