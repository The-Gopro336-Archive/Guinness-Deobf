package org.spongepowered.tools.obfuscation;

import java.util.Iterator;
import java.util.HashMap;
import org.spongepowered.tools.obfuscation.struct.InjectorRemap;
import java.util.Map;
import org.spongepowered.tools.obfuscation.mirror.AnnotationHandle;
import javax.lang.model.element.ExecutableElement;

static class AnnotatedElementInjectionPoint extends AnnotatedElement<ExecutableElement>
{
    private final AnnotationHandle at;
    private Map<String, String> args;
    private final InjectorRemap state;
    
    public AnnotatedElementInjectionPoint(final ExecutableElement element, final AnnotationHandle inject, final AnnotationHandle at, final InjectorRemap state) {
        super(element, inject);
        this.at = at;
        this.state = state;
    }
    
    public boolean shouldRemap() {
        return this.at.getBoolean("remap", this.state.shouldRemap());
    }
    
    public AnnotationHandle getAt() {
        return this.at;
    }
    
    public String getAtArg(final String key) {
        if (this.args == null) {
            this.args = new HashMap<String, String>();
            for (final String arg : this.at.getList("args")) {
                if (arg == null) {
                    continue;
                }
                final int eqPos = arg.indexOf(61);
                if (eqPos > -1) {
                    this.args.put(arg.substring(0, eqPos), arg.substring(eqPos + 1));
                }
                else {
                    this.args.put(arg, "");
                }
            }
        }
        return this.args.get(key);
    }
    
    public void notifyRemapped() {
        this.state.notifyRemapped();
    }
}
