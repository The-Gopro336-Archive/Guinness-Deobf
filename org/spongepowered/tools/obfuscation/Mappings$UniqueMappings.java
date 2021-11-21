package org.spongepowered.tools.obfuscation;

import org.spongepowered.asm.obfuscation.mapping.IMapping;
import java.util.HashMap;
import org.spongepowered.asm.obfuscation.mapping.common.MappingMethod;
import org.spongepowered.asm.obfuscation.mapping.common.MappingField;
import java.util.Map;
import org.spongepowered.tools.obfuscation.mapping.IMappingConsumer;

static class UniqueMappings implements IMappingConsumer
{
    private final IMappingConsumer mappings;
    private final Map<ObfuscationType, Map<MappingField, MappingField>> fields;
    private final Map<ObfuscationType, Map<MappingMethod, MappingMethod>> methods;
    
    public UniqueMappings(final IMappingConsumer mappings) {
        this.fields = new HashMap<ObfuscationType, Map<MappingField, MappingField>>();
        this.methods = new HashMap<ObfuscationType, Map<MappingMethod, MappingMethod>>();
        this.mappings = mappings;
    }
    
    @Override
    public void clear() {
        this.clearMaps();
        this.mappings.clear();
    }
    
    protected void clearMaps() {
        this.fields.clear();
        this.methods.clear();
    }
    
    @Override
    public void addFieldMapping(final ObfuscationType type, final MappingField from, final MappingField to) {
        if (!this.checkForExistingMapping(type, from, to, this.fields)) {
            this.mappings.addFieldMapping(type, from, to);
        }
    }
    
    @Override
    public void addMethodMapping(final ObfuscationType type, final MappingMethod from, final MappingMethod to) {
        if (!this.checkForExistingMapping(type, from, to, this.methods)) {
            this.mappings.addMethodMapping(type, from, to);
        }
    }
    
    private <TMapping extends IMapping<TMapping>> boolean checkForExistingMapping(final ObfuscationType type, final TMapping from, final TMapping to, final Map<ObfuscationType, Map<TMapping, TMapping>> mappings) throws MappingConflictException {
        Map<TMapping, TMapping> existingMappings = mappings.get(type);
        if (existingMappings == null) {
            existingMappings = new HashMap<TMapping, TMapping>();
            mappings.put(type, existingMappings);
        }
        final TMapping existing = existingMappings.get(from);
        if (existing == null) {
            existingMappings.put(from, to);
            return false;
        }
        if (existing.equals(to)) {
            return true;
        }
        throw new MappingConflictException(existing, to);
    }
    
    @Override
    public MappingSet<MappingField> getFieldMappings(final ObfuscationType type) {
        return this.mappings.getFieldMappings(type);
    }
    
    @Override
    public MappingSet<MappingMethod> getMethodMappings(final ObfuscationType type) {
        return this.mappings.getMethodMappings(type);
    }
}
