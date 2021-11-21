package org.newdawn.slick;

private class CharDef
{
    public short id;
    public short x;
    public short y;
    public short width;
    public short height;
    public short xoffset;
    public short yoffset;
    public short xadvance;
    public Image image;
    public short dlIndex;
    public short[] kerning;
    
    public void init() {
        this.image = AngelCodeFont.access$400(AngelCodeFont.this).getSubImage(this.x, this.y, this.width, this.height);
    }
    
    @Override
    public String toString() {
        return "[CharDef id=" + this.id + " x=" + this.x + " y=" + this.y + "]";
    }
    
    public void draw(final float x, final float y) {
        this.image.drawEmbedded(x + this.xoffset, y + this.yoffset, this.width, this.height);
    }
    
    public int getKerning(final int otherCodePoint) {
        if (this.kerning == null) {
            return 0;
        }
        int low = 0;
        int high = this.kerning.length - 1;
        while (low <= high) {
            final int midIndex = low + high >>> 1;
            final int value = this.kerning[midIndex];
            final int foundCodePoint = value & 0xFF;
            if (foundCodePoint < otherCodePoint) {
                low = midIndex + 1;
            }
            else {
                if (foundCodePoint <= otherCodePoint) {
                    return value >> 8;
                }
                high = midIndex - 1;
            }
        }
        return 0;
    }
}
