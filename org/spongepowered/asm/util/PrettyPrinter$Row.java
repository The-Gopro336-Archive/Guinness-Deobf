package org.spongepowered.asm.util;

static class Row implements IVariableWidthEntry
{
    final Table table;
    final String[] args;
    
    public Row(final Table table, final Object... args) {
        this.table = table.grow(args.length);
        this.args = new String[args.length];
        for (int i = 0; i < args.length; ++i) {
            this.args[i] = args[i].toString();
            this.table.columns.get(i).setMinWidth(this.args[i].length());
        }
    }
    
    @Override
    public String toString() {
        final Object[] args = new Object[this.table.columns.size()];
        for (int col = 0; col < args.length; ++col) {
            final Column column = this.table.columns.get(col);
            if (col >= this.args.length) {
                args[col] = "";
            }
            else {
                args[col] = ((this.args[col].length() > column.getMaxWidth()) ? this.args[col].substring(0, column.getMaxWidth()) : this.args[col]);
            }
        }
        return String.format(this.table.format, args);
    }
    
    @Override
    public int getWidth() {
        return this.toString().length();
    }
}
