package org.spongepowered.tools.obfuscation;

import org.spongepowered.tools.obfuscation.mirror.TypeHandle;
import org.spongepowered.asm.obfuscation.mapping.IMapping;
import org.spongepowered.tools.obfuscation.mirror.AnnotationHandle;
import org.spongepowered.asm.obfuscation.mapping.common.MappingMethod;
import javax.lang.model.element.ExecutableElement;

class AnnotatedElementShadowMethod extends AnnotatedElementShadow<ExecutableElement, MappingMethod>
{
    public AnnotatedElementShadowMethod(final ExecutableElement element, final AnnotationHandle annotation, final boolean shouldRemap) {
        super(element, annotation, shouldRemap, IMapping.Type.METHOD);
    }
    
    @Override
    public MappingMethod getMapping(final TypeHandle owner, final String name, final String desc) {
        return owner.getMappingMethod(name, desc);
    }
    
    @Override
    public void addMapping(final ObfuscationType type, final IMapping<?> remapped) {
        AnnotatedMixinElementHandlerShadow.this.addMethodMapping(type, this.setObfuscatedName(remapped), this.getDesc(), remapped.getDesc());
    }
}
