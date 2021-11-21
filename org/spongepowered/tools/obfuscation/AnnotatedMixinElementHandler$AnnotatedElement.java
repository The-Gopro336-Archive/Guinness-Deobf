package org.spongepowered.tools.obfuscation;

import javax.tools.Diagnostic;
import javax.annotation.processing.Messager;
import org.spongepowered.tools.obfuscation.mirror.TypeUtils;
import org.spongepowered.tools.obfuscation.mirror.AnnotationHandle;
import javax.lang.model.element.Element;

abstract static class AnnotatedElement<E extends Element>
{
    protected final E element;
    protected final AnnotationHandle annotation;
    private final String desc;
    
    public AnnotatedElement(final E element, final AnnotationHandle annotation) {
        this.element = element;
        this.annotation = annotation;
        this.desc = TypeUtils.getDescriptor(element);
    }
    
    public E getElement() {
        return this.element;
    }
    
    public AnnotationHandle getAnnotation() {
        return this.annotation;
    }
    
    public String getSimpleName() {
        return this.getElement().getSimpleName().toString();
    }
    
    public String getDesc() {
        return this.desc;
    }
    
    public final void printMessage(final Messager messager, final Diagnostic.Kind kind, final CharSequence msg) {
        messager.printMessage(kind, msg, this.element, this.annotation.asMirror());
    }
}
