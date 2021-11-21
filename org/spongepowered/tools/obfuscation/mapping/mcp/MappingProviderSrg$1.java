package org.spongepowered.tools.obfuscation.mapping.mcp;

import java.io.IOException;
import org.spongepowered.asm.obfuscation.mapping.common.MappingMethod;
import org.spongepowered.asm.mixin.throwables.MixinException;
import org.spongepowered.asm.obfuscation.mapping.mcp.MappingFieldSrg;
import com.google.common.base.Strings;
import java.io.File;
import com.google.common.collect.BiMap;
import com.google.common.io.LineProcessor;

class MappingProviderSrg$1 implements LineProcessor<String> {
    final /* synthetic */ BiMap val$packageMap;
    final /* synthetic */ BiMap val$classMap;
    final /* synthetic */ BiMap val$fieldMap;
    final /* synthetic */ BiMap val$methodMap;
    final /* synthetic */ File val$input;
    
    @Override
    public String getResult() {
        return null;
    }
    
    @Override
    public boolean processLine(final String line) throws IOException {
        if (Strings.isNullOrEmpty(line) || line.startsWith("#")) {
            return true;
        }
        final String type = line.substring(0, 2);
        final String[] args = line.substring(4).split(" ");
        if (type.equals("PK")) {
            this.val$packageMap.forcePut(args[0], args[1]);
        }
        else if (type.equals("CL")) {
            this.val$classMap.forcePut(args[0], args[1]);
        }
        else if (type.equals("FD")) {
            this.val$fieldMap.forcePut(new MappingFieldSrg(args[0]).copy(), new MappingFieldSrg(args[1]).copy());
        }
        else {
            if (!type.equals("MD")) {
                throw new MixinException("Invalid SRG file: " + this.val$input);
            }
            this.val$methodMap.forcePut(new MappingMethod(args[0], args[1]), new MappingMethod(args[2], args[3]));
        }
        return true;
    }
}