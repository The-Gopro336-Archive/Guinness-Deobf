package org.spongepowered.asm.util;

import java.util.Iterator;
import com.google.common.base.Strings;
import java.util.ArrayList;
import java.util.List;

static class Table implements IVariableWidthEntry
{
    final List<Column> columns;
    final List<Row> rows;
    String format;
    int colSpacing;
    boolean addHeader;
    
    Table() {
        this.columns = new ArrayList<Column>();
        this.rows = new ArrayList<Row>();
        this.format = "%s";
        this.colSpacing = 2;
        this.addHeader = true;
    }
    
    void headerAdded() {
        this.addHeader = false;
    }
    
    void setColSpacing(final int spacing) {
        this.colSpacing = Math.max(0, spacing);
        this.updateFormat();
    }
    
    Table grow(final int size) {
        while (this.columns.size() < size) {
            this.columns.add(new Column(this));
        }
        this.updateFormat();
        return this;
    }
    
    Column add(final Column column) {
        this.columns.add(column);
        return column;
    }
    
    Row add(final Row row) {
        this.rows.add(row);
        return row;
    }
    
    Column addColumn(final String title) {
        return this.add(new Column(this, title));
    }
    
    Column addColumn(final Alignment align, final int size, final String title) {
        return this.add(new Column(this, align, size, title));
    }
    
    Row addRow(final Object... args) {
        return this.add(new Row(this, args));
    }
    
    void updateFormat() {
        final String spacing = Strings.repeat(" ", this.colSpacing);
        final StringBuilder format = new StringBuilder();
        boolean addSpacing = false;
        for (final Column column : this.columns) {
            if (addSpacing) {
                format.append(spacing);
            }
            addSpacing = true;
            format.append(column.getFormat());
        }
        this.format = format.toString();
    }
    
    String getFormat() {
        return this.format;
    }
    
    Object[] getTitles() {
        final List<Object> titles = new ArrayList<Object>();
        for (final Column column : this.columns) {
            titles.add(column.getTitle());
        }
        return titles.toArray();
    }
    
    @Override
    public String toString() {
        boolean nonEmpty = false;
        final String[] titles = new String[this.columns.size()];
        for (int col = 0; col < this.columns.size(); ++col) {
            titles[col] = this.columns.get(col).toString();
            nonEmpty |= !titles[col].isEmpty();
        }
        return nonEmpty ? String.format(this.format, (Object[])titles) : null;
    }
    
    @Override
    public int getWidth() {
        final String str = this.toString();
        return (str != null) ? str.length() : 0;
    }
}
