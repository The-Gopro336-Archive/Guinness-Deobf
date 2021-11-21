package org.spongepowered.asm.mixin.transformer.debug;

import java.io.IOException;
import org.jetbrains.java.decompiler.util.InterpreterUtil;
import java.io.File;
import org.jetbrains.java.decompiler.main.extern.IBytecodeProvider;

class RuntimeDecompiler$1 implements IBytecodeProvider {
    private byte[] byteCode;
    
    @Override
    public byte[] getBytecode(final String externalPath, final String internalPath) throws IOException {
        if (this.byteCode == null) {
            this.byteCode = InterpreterUtil.getBytes(new File(externalPath));
        }
        return this.byteCode;
    }
}