package org.spongepowered.tools.obfuscation;

import org.spongepowered.tools.obfuscation.mirror.AnnotationHandle;
import javax.lang.model.element.ExecutableElement;

static class AnnotatedElementOverwrite extends AnnotatedElement<ExecutableElement>
{
    private final boolean shouldRemap;
    
    public AnnotatedElementOverwrite(final ExecutableElement element, final AnnotationHandle annotation, final boolean shouldRemap) {
        super(element, annotation);
        this.shouldRemap = shouldRemap;
    }
    
    public boolean shouldRemap() {
        return this.shouldRemap;
    }
}
