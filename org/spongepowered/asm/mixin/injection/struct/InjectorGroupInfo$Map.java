package org.spongepowered.asm.mixin.injection.struct;

import org.spongepowered.asm.mixin.injection.throwables.InjectionValidationException;
import java.util.Iterator;
import org.spongepowered.asm.lib.tree.AnnotationNode;
import java.lang.annotation.Annotation;
import org.spongepowered.asm.util.Annotations;
import org.spongepowered.asm.mixin.injection.Group;
import org.spongepowered.asm.lib.tree.MethodNode;
import java.util.HashMap;

public static final class Map extends HashMap<String, InjectorGroupInfo>
{
    private static final long serialVersionUID = 1L;
    private static final InjectorGroupInfo NO_GROUP;
    
    @Override
    public InjectorGroupInfo get(final Object key) {
        return this.forName(key.toString());
    }
    
    public InjectorGroupInfo forName(final String name) {
        InjectorGroupInfo value = super.get(name);
        if (value == null) {
            value = new InjectorGroupInfo(name);
            this.put(name, value);
        }
        return value;
    }
    
    public InjectorGroupInfo parseGroup(final MethodNode method, final String defaultGroup) {
        return this.parseGroup(Annotations.getInvisible(method, Group.class), defaultGroup);
    }
    
    public InjectorGroupInfo parseGroup(final AnnotationNode annotation, final String defaultGroup) {
        if (annotation == null) {
            return Map.NO_GROUP;
        }
        String name = Annotations.getValue(annotation, "name");
        if (name == null || name.isEmpty()) {
            name = defaultGroup;
        }
        final InjectorGroupInfo groupInfo = this.forName(name);
        final Integer min = Annotations.getValue(annotation, "min");
        if (min != null && min != -1) {
            groupInfo.setMinRequired(min);
        }
        final Integer max = Annotations.getValue(annotation, "max");
        if (max != null && max != -1) {
            groupInfo.setMaxAllowed(max);
        }
        return groupInfo;
    }
    
    public void validateAll() throws InjectionValidationException {
        for (final InjectorGroupInfo group : ((HashMap<K, InjectorGroupInfo>)this).values()) {
            group.validate();
        }
    }
    
    static {
        NO_GROUP = new InjectorGroupInfo("NONE", true);
    }
}
