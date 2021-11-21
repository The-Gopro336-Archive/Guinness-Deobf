package org.spongepowered.asm.util;

import org.spongepowered.asm.lib.signature.SignatureVisitor;

abstract class SignatureElement extends SignatureVisitor
{
    public SignatureElement() {
        super(327680);
    }
}
