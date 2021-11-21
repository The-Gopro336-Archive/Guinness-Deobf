package org.spongepowered.tools.obfuscation.service;

import java.util.Collection;
import java.util.Set;

public interface IObfuscationService
{
    Set<String> getSupportedOptions();
    
    Collection<ObfuscationTypeDescriptor> getObfuscationTypes();
}
