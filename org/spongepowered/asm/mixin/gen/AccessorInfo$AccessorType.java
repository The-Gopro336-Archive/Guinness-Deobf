package org.spongepowered.asm.mixin.gen;

import com.google.common.collect.ImmutableSet;
import java.util.Set;

public enum AccessorType
{
    FIELD_GETTER((Set)ImmutableSet.of("get", "is")) {
        @Override
        AccessorGenerator getGenerator(final AccessorInfo info) {
            return new AccessorGeneratorFieldGetter(info);
        }
    }, 
    FIELD_SETTER((Set)ImmutableSet.of("set")) {
        @Override
        AccessorGenerator getGenerator(final AccessorInfo info) {
            return new AccessorGeneratorFieldSetter(info);
        }
    }, 
    METHOD_PROXY((Set)ImmutableSet.of("call", "invoke")) {
        @Override
        AccessorGenerator getGenerator(final AccessorInfo info) {
            return new AccessorGeneratorMethodProxy(info);
        }
    };
    
    private final Set<String> expectedPrefixes;
    
    private AccessorType(final Set<String> expectedPrefixes) {
        this.expectedPrefixes = expectedPrefixes;
    }
    
    public boolean isExpectedPrefix(final String prefix) {
        return this.expectedPrefixes.contains(prefix);
    }
    
    public String getExpectedPrefixes() {
        return this.expectedPrefixes.toString();
    }
    
    abstract AccessorGenerator getGenerator(final AccessorInfo p0);
}
