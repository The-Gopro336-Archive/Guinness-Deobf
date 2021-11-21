package org.spongepowered.asm.mixin.struct;

import org.spongepowered.asm.lib.tree.LineNumberNode;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import java.util.Iterator;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.lib.tree.ClassNode;

public static class File
{
    public final int id;
    public final int lineOffset;
    public final int size;
    public final String sourceFileName;
    public final String sourceFilePath;
    
    public File(final int id, final int lineOffset, final int size, final String sourceFileName) {
        this(id, lineOffset, size, sourceFileName, null);
    }
    
    public File(final int id, final int lineOffset, final int size, final String sourceFileName, final String sourceFilePath) {
        this.id = id;
        this.lineOffset = lineOffset;
        this.size = size;
        this.sourceFileName = sourceFileName;
        this.sourceFilePath = sourceFilePath;
    }
    
    public void applyOffset(final ClassNode classNode) {
        for (final MethodNode method : classNode.methods) {
            this.applyOffset(method);
        }
    }
    
    public void applyOffset(final MethodNode method) {
        for (final AbstractInsnNode node : method.instructions) {
            if (node instanceof LineNumberNode) {
                final LineNumberNode lineNumberNode = (LineNumberNode)node;
                lineNumberNode.line += this.lineOffset - 1;
            }
        }
    }
    
    void appendFile(final StringBuilder sb) {
        if (this.sourceFilePath != null) {
            sb.append("+ ").append(this.id).append(" ").append(this.sourceFileName).append("\n");
            sb.append(this.sourceFilePath).append("\n");
        }
        else {
            sb.append(this.id).append(" ").append(this.sourceFileName).append("\n");
        }
    }
    
    public void appendLines(final StringBuilder sb) {
        sb.append("1#").append(this.id).append(",").append(this.size).append(":").append(this.lineOffset).append("\n");
    }
}
