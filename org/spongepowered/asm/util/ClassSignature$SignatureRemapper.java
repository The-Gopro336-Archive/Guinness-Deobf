package org.spongepowered.asm.util;

import java.util.HashSet;
import java.util.Set;
import org.spongepowered.asm.lib.signature.SignatureWriter;

class SignatureRemapper extends SignatureWriter
{
    private final Set<String> localTypeVars;
    
    SignatureRemapper() {
        this.localTypeVars = new HashSet<String>();
    }
    
    @Override
    public void visitFormalTypeParameter(final String name) {
        this.localTypeVars.add(name);
        super.visitFormalTypeParameter(name);
    }
    
    @Override
    public void visitTypeVariable(final String name) {
        if (!this.localTypeVars.contains(name)) {
            final TypeVar typeVar = ClassSignature.this.getTypeVar(name);
            if (typeVar != null) {
                super.visitTypeVariable(typeVar.toString());
                return;
            }
        }
        super.visitTypeVariable(name);
    }
}
