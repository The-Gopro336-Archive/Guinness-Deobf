package org.spongepowered.tools.obfuscation.interfaces;

import javax.lang.model.type.TypeMirror;
import org.spongepowered.tools.obfuscation.mirror.TypeHandle;

public interface ITypeHandleProvider
{
    TypeHandle getTypeHandle(final String p0);
    
    TypeHandle getSimulatedHandle(final String p0, final TypeMirror p1);
}
