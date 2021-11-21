package org.spongepowered.asm.util;

import org.spongepowered.asm.lib.tree.AnnotationNode;
import com.google.common.base.Function;

static final class Annotations$1 implements Function<AnnotationNode, String> {
    @Override
    public String apply(final AnnotationNode input) {
        return input.desc;
    }
}