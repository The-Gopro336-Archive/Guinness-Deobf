package org.spongepowered.asm.util.perf;

class SubSection extends LiveSection
{
    private final String baseName;
    private final Section root;
    
    SubSection(final String name, final int cursor, final String baseName, final Section root) {
        super(name, cursor);
        this.baseName = baseName;
        this.root = root;
    }
    
    @Override
    Section invalidate() {
        this.root.invalidate();
        return super.invalidate();
    }
    
    @Override
    public String getBaseName() {
        return this.baseName;
    }
    
    @Override
    public void setInfo(final String info) {
        this.root.setInfo(info);
        super.setInfo(info);
    }
    
    @Override
    Section getDelegate() {
        return this.root;
    }
    
    @Override
    Section start() {
        this.root.start();
        return super.start();
    }
    
    @Override
    public Section end() {
        this.root.stop();
        return super.end();
    }
    
    @Override
    public Section next(final String name) {
        super.stop();
        return this.root.next(name);
    }
}
