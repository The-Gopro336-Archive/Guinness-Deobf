package org.spongepowered.asm.util;

static class Column
{
    private final Table table;
    private Alignment align;
    private int minWidth;
    private int maxWidth;
    private int size;
    private String title;
    private String format;
    
    Column(final Table table) {
        this.align = Alignment.LEFT;
        this.minWidth = 1;
        this.maxWidth = Integer.MAX_VALUE;
        this.size = 0;
        this.title = "";
        this.format = "%s";
        this.table = table;
    }
    
    Column(final Table table, final String title) {
        this(table);
        this.title = title;
        this.minWidth = title.length();
        this.updateFormat();
    }
    
    Column(final Table table, final Alignment align, final int size, final String title) {
        this(table, title);
        this.align = align;
        this.size = size;
    }
    
    void setAlignment(final Alignment align) {
        this.align = align;
        this.updateFormat();
    }
    
    void setWidth(final int width) {
        if (width > this.size) {
            this.size = width;
            this.updateFormat();
        }
    }
    
    void setMinWidth(final int width) {
        if (width > this.minWidth) {
            this.minWidth = width;
            this.updateFormat();
        }
    }
    
    void setMaxWidth(final int width) {
        this.size = Math.min(this.size, this.maxWidth);
        this.maxWidth = Math.max(1, width);
        this.updateFormat();
    }
    
    void setTitle(final String title) {
        this.title = title;
        this.setWidth(title.length());
    }
    
    private void updateFormat() {
        final int width = Math.min(this.maxWidth, (this.size == 0) ? this.minWidth : this.size);
        this.format = "%" + ((this.align == Alignment.RIGHT) ? "" : "-") + width + "s";
        this.table.updateFormat();
    }
    
    int getMaxWidth() {
        return this.maxWidth;
    }
    
    String getTitle() {
        return this.title;
    }
    
    String getFormat() {
        return this.format;
    }
    
    @Override
    public String toString() {
        if (this.title.length() > this.maxWidth) {
            return this.title.substring(0, this.maxWidth);
        }
        return this.title;
    }
}
