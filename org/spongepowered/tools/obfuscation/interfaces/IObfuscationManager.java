package org.spongepowered.tools.obfuscation.interfaces;

import org.spongepowered.tools.obfuscation.ObfuscationEnvironment;
import java.util.List;
import org.spongepowered.tools.obfuscation.mapping.IMappingConsumer;

public interface IObfuscationManager
{
    void init();
    
    IObfuscationDataProvider getDataProvider();
    
    IReferenceManager getReferenceManager();
    
    IMappingConsumer createMappingConsumer();
    
    List<ObfuscationEnvironment> getEnvironments();
    
    void writeMappings();
    
    void writeReferences();
}
