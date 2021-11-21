package org.spongepowered.tools.obfuscation;

import javax.lang.model.element.Element;
import org.spongepowered.asm.mixin.gen.AccessorInfo;
import org.spongepowered.tools.obfuscation.mirror.TypeUtils;
import org.spongepowered.tools.obfuscation.mirror.AnnotationHandle;
import javax.lang.model.element.ExecutableElement;

static class AnnotatedElementInvoker extends AnnotatedElementAccessor
{
    public AnnotatedElementInvoker(final ExecutableElement element, final AnnotationHandle annotation, final boolean shouldRemap) {
        super(element, annotation, shouldRemap);
    }
    
    @Override
    public String getAccessorDesc() {
        return TypeUtils.getDescriptor(this.getElement());
    }
    
    @Override
    public AccessorInfo.AccessorType getAccessorType() {
        return AccessorInfo.AccessorType.METHOD_PROXY;
    }
    
    @Override
    public String getTargetTypeName() {
        return TypeUtils.getJavaSignature(((AnnotatedElement<Element>)this).getElement());
    }
}
