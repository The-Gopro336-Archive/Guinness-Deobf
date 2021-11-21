package org.spongepowered.asm.lib.util;

import org.spongepowered.asm.lib.Label;
import java.util.Map;

public interface ASMifiable
{
    void asmify(final StringBuffer p0, final String p1, final Map<Label, String> p2);
}
