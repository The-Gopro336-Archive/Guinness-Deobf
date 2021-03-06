package org.spongepowered.asm.util.throwables;

import org.spongepowered.asm.lib.tree.MethodInsnNode;
import org.spongepowered.asm.util.Bytecode;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;

public enum Problem
{
    BAD_INSN("Conflicting opcodes %4$s and %5$s at offset %3$d in synthetic bridge method %1$s%2$s"), 
    BAD_LOAD("Conflicting variable access at offset %3$d in synthetic bridge method %1$s%2$s"), 
    BAD_CAST("Conflicting type cast at offset %3$d in synthetic bridge method %1$s%2$s"), 
    BAD_INVOKE_NAME("Conflicting synthetic bridge target method name in synthetic bridge method %1$s%2$s Existing:%6$s Incoming:%7$s"), 
    BAD_INVOKE_DESC("Conflicting synthetic bridge target method descriptor in synthetic bridge method %1$s%2$s Existing:%8$s Incoming:%9$s"), 
    BAD_LENGTH("Mismatched bridge method length for synthetic bridge method %1$s%2$s unexpected extra opcode at offset %3$d");
    
    private final String message;
    
    private Problem(final String message) {
        this.message = message;
    }
    
    String getMessage(final String name, final String desc, final int index, final AbstractInsnNode a, final AbstractInsnNode b) {
        return String.format(this.message, name, desc, index, Bytecode.getOpcodeName(a), Bytecode.getOpcodeName(a), getInsnName(a), getInsnName(b), getInsnDesc(a), getInsnDesc(b));
    }
    
    private static String getInsnName(final AbstractInsnNode node) {
        return (node instanceof MethodInsnNode) ? ((MethodInsnNode)node).name : "";
    }
    
    private static String getInsnDesc(final AbstractInsnNode node) {
        return (node instanceof MethodInsnNode) ? ((MethodInsnNode)node).desc : "";
    }
}
