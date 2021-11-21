package org.spongepowered.asm.mixin.injection.invoke.util;

import org.spongepowered.asm.lib.tree.analysis.Value;
import org.spongepowered.asm.lib.tree.analysis.AnalyzerException;
import org.spongepowered.asm.lib.tree.analysis.Interpreter;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.analysis.BasicValue;
import org.spongepowered.asm.lib.tree.analysis.Frame;

class PopFrame extends Frame<BasicValue>
{
    private AbstractInsnNode current;
    private AnalyzerState state;
    private int depth;
    
    public PopFrame(final int locals, final int stack) {
        super(locals, stack);
        this.state = AnalyzerState.SEARCH;
        this.depth = 0;
    }
    
    @Override
    public void execute(final AbstractInsnNode insn, final Interpreter<BasicValue> interpreter) throws AnalyzerException {
        super.execute(this.current = insn, interpreter);
    }
    
    @Override
    public void push(final BasicValue value) throws IndexOutOfBoundsException {
        if (this.current == PopAnalyzer.this.node && this.state == AnalyzerState.SEARCH) {
            this.state = AnalyzerState.ANALYSE;
            ++this.depth;
        }
        else if (this.state == AnalyzerState.ANALYSE) {
            ++this.depth;
        }
        super.push(value);
    }
    
    @Override
    public BasicValue pop() throws IndexOutOfBoundsException {
        if (this.state == AnalyzerState.ANALYSE && --this.depth == 0) {
            this.state = AnalyzerState.COMPLETE;
            throw new AnalysisResultException(this.current);
        }
        return super.pop();
    }
}
