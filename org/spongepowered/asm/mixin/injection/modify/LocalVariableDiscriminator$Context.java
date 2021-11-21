package org.spongepowered.asm.mixin.injection.modify;

import org.spongepowered.asm.util.SignaturePrinter;
import java.util.Map;
import java.util.HashMap;
import org.spongepowered.asm.lib.tree.LocalVariableNode;
import org.spongepowered.asm.util.Locals;
import org.spongepowered.asm.util.Bytecode;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.mixin.injection.struct.Target;
import org.spongepowered.asm.util.PrettyPrinter;

public static class Context implements PrettyPrinter.IPrettyPrintable
{
    final Target target;
    final Type returnType;
    final AbstractInsnNode node;
    final int baseArgIndex;
    final Local[] locals;
    private final boolean isStatic;
    
    public Context(final Type returnType, final boolean argsOnly, final Target target, final AbstractInsnNode node) {
        this.isStatic = Bytecode.methodIsStatic(target.method);
        this.returnType = returnType;
        this.target = target;
        this.node = node;
        this.baseArgIndex = (this.isStatic ? 0 : 1);
        this.locals = this.initLocals(target, argsOnly, node);
        this.initOrdinals();
    }
    
    private Local[] initLocals(final Target target, final boolean argsOnly, final AbstractInsnNode node) {
        if (!argsOnly) {
            final LocalVariableNode[] locals = Locals.getLocalsAt(target.classNode, target.method, node);
            if (locals != null) {
                final Local[] lvt = new Local[locals.length];
                for (int l = 0; l < locals.length; ++l) {
                    if (locals[l] != null) {
                        lvt[l] = new Local(locals[l].name, Type.getType(locals[l].desc));
                    }
                }
                return lvt;
            }
        }
        final Local[] lvt2 = new Local[this.baseArgIndex + target.arguments.length];
        if (!this.isStatic) {
            lvt2[0] = new Local("this", Type.getType(target.classNode.name));
        }
        for (int local = this.baseArgIndex; local < lvt2.length; ++local) {
            final Type arg = target.arguments[local - this.baseArgIndex];
            lvt2[local] = new Local("arg" + local, arg);
        }
        return lvt2;
    }
    
    private void initOrdinals() {
        final Map<Type, Integer> ordinalMap = new HashMap<Type, Integer>();
        for (int l = 0; l < this.locals.length; ++l) {
            Integer ordinal = 0;
            if (this.locals[l] != null) {
                ordinal = ordinalMap.get(this.locals[l].type);
                ordinalMap.put(this.locals[l].type, ordinal = ((ordinal == null) ? 0 : (ordinal + 1)));
                this.locals[l].ord = ordinal;
            }
        }
    }
    
    @Override
    public void print(final PrettyPrinter printer) {
        printer.add("%5s  %7s  %30s  %-50s  %s", "INDEX", "ORDINAL", "TYPE", "NAME", "CANDIDATE");
        for (int l = this.baseArgIndex; l < this.locals.length; ++l) {
            final Local local = this.locals[l];
            if (local != null) {
                final Type localType = local.type;
                final String localName = local.name;
                final int ordinal = local.ord;
                final String candidate = this.returnType.equals(localType) ? "YES" : "-";
                printer.add("[%3d]    [%3d]  %30s  %-50s  %s", l, ordinal, SignaturePrinter.getTypeName(localType, false), localName, candidate);
            }
            else if (l > 0) {
                final Local prevLocal = this.locals[l - 1];
                final boolean isTop = prevLocal != null && prevLocal.type != null && prevLocal.type.getSize() > 1;
                printer.add("[%3d]           %30s", l, isTop ? "<top>" : "-");
            }
        }
    }
    
    public class Local
    {
        int ord;
        String name;
        Type type;
        
        public Local(final String name, final Type type) {
            this.ord = 0;
            this.name = name;
            this.type = type;
        }
        
        @Override
        public String toString() {
            return String.format("Local[ordinal=%d, name=%s, type=%s]", this.ord, this.name, this.type);
        }
    }
}
