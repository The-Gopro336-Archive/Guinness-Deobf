package org.spongepowered.tools.obfuscation;

import org.spongepowered.tools.obfuscation.mirror.TypeHandle;
import org.spongepowered.asm.obfuscation.mapping.IMapping;
import org.spongepowered.tools.obfuscation.mirror.AnnotationHandle;
import org.spongepowered.asm.obfuscation.mapping.common.MappingField;
import javax.lang.model.element.VariableElement;

class AnnotatedElementShadowField extends AnnotatedElementShadow<VariableElement, MappingField>
{
    public AnnotatedElementShadowField(final VariableElement element, final AnnotationHandle annotation, final boolean shouldRemap) {
        super(element, annotation, shouldRemap, IMapping.Type.FIELD);
    }
    
    @Override
    public MappingField getMapping(final TypeHandle owner, final String name, final String desc) {
        return new MappingField(owner.getName(), name, desc);
    }
    
    @Override
    public void addMapping(final ObfuscationType type, final IMapping<?> remapped) {
        AnnotatedMixinElementHandlerShadow.this.addFieldMapping(type, this.setObfuscatedName(remapped), this.getDesc(), remapped.getDesc());
    }
}
