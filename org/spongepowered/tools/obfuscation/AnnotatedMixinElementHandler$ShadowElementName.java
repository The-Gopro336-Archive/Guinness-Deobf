package org.spongepowered.tools.obfuscation;

import org.spongepowered.asm.obfuscation.mapping.IMapping;
import org.spongepowered.tools.obfuscation.mirror.AnnotationHandle;
import javax.lang.model.element.Element;

static class ShadowElementName extends AliasedElementName
{
    private final boolean hasPrefix;
    private final String prefix;
    private final String baseName;
    private String obfuscated;
    
    ShadowElementName(final Element element, final AnnotationHandle shadow) {
        super(element, shadow);
        this.prefix = shadow.getValue("prefix", "shadow$");
        boolean hasPrefix = false;
        String name = this.originalName;
        if (name.startsWith(this.prefix)) {
            hasPrefix = true;
            name = name.substring(this.prefix.length());
        }
        this.hasPrefix = hasPrefix;
        final String s = name;
        this.baseName = s;
        this.obfuscated = s;
    }
    
    @Override
    public String toString() {
        return this.baseName;
    }
    
    @Override
    public String baseName() {
        return this.baseName;
    }
    
    public ShadowElementName setObfuscatedName(final IMapping<?> name) {
        this.obfuscated = name.getName();
        return this;
    }
    
    public ShadowElementName setObfuscatedName(final String name) {
        this.obfuscated = name;
        return this;
    }
    
    @Override
    public boolean hasPrefix() {
        return this.hasPrefix;
    }
    
    public String prefix() {
        return this.hasPrefix ? this.prefix : "";
    }
    
    public String name() {
        return this.prefix(this.baseName);
    }
    
    public String obfuscated() {
        return this.prefix(this.obfuscated);
    }
    
    public String prefix(final String name) {
        return this.hasPrefix ? (this.prefix + name) : name;
    }
}
