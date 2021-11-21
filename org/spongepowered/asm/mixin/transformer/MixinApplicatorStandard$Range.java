package org.spongepowered.asm.mixin.transformer;

class Range
{
    final int start;
    final int end;
    final int marker;
    
    Range(final int start, final int end, final int marker) {
        this.start = start;
        this.end = end;
        this.marker = marker;
    }
    
    boolean isValid() {
        return this.start != 0 && this.end != 0 && this.end >= this.start;
    }
    
    boolean contains(final int value) {
        return value >= this.start && value <= this.end;
    }
    
    boolean excludes(final int value) {
        return value < this.start || value > this.end;
    }
    
    @Override
    public String toString() {
        return String.format("Range[%d-%d,%d,valid=%s)", this.start, this.end, this.marker, this.isValid());
    }
}
