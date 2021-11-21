package org.spongepowered.tools.obfuscation;

import org.spongepowered.tools.obfuscation.mirror.TypeHandle;
import org.spongepowered.tools.obfuscation.interfaces.IObfuscationDataProvider;
import org.spongepowered.tools.obfuscation.mirror.AnnotationHandle;
import org.spongepowered.asm.obfuscation.mapping.IMapping;
import javax.lang.model.element.Element;

abstract static class AnnotatedElementShadow<E extends Element, M extends IMapping<M>> extends AnnotatedElement<E>
{
    private final boolean shouldRemap;
    private final ShadowElementName name;
    private final IMapping.Type type;
    
    protected AnnotatedElementShadow(final E element, final AnnotationHandle annotation, final boolean shouldRemap, final IMapping.Type type) {
        super(element, annotation);
        this.shouldRemap = shouldRemap;
        this.name = new ShadowElementName(element, annotation);
        this.type = type;
    }
    
    public boolean shouldRemap() {
        return this.shouldRemap;
    }
    
    public ShadowElementName getName() {
        return this.name;
    }
    
    public IMapping.Type getElementType() {
        return this.type;
    }
    
    @Override
    public String toString() {
        return this.getElementType().name().toLowerCase();
    }
    
    public ShadowElementName setObfuscatedName(final IMapping<?> name) {
        return this.setObfuscatedName(name.getSimpleName());
    }
    
    public ShadowElementName setObfuscatedName(final String name) {
        return this.getName().setObfuscatedName(name);
    }
    
    public ObfuscationData<M> getObfuscationData(final IObfuscationDataProvider provider, final TypeHandle owner) {
        return provider.getObfEntry((IMapping<M>)this.getMapping(owner, this.getName().toString(), this.getDesc()));
    }
    
    public abstract M getMapping(final TypeHandle p0, final String p1, final String p2);
    
    public abstract void addMapping(final ObfuscationType p0, final IMapping<?> p1);
}
