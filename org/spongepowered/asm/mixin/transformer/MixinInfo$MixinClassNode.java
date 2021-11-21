package org.spongepowered.asm.mixin.transformer;

import org.spongepowered.asm.lib.MethodVisitor;
import org.spongepowered.asm.lib.tree.MethodNode;
import java.util.List;
import org.spongepowered.asm.lib.tree.ClassNode;

class MixinClassNode extends ClassNode
{
    public final List<MixinMethodNode> mixinMethods;
    
    public MixinClassNode(final MixinInfo this$0, final MixinInfo mixin) {
        this(this$0, 327680);
    }
    
    public MixinClassNode(final int api) {
        super(api);
        this.mixinMethods = (List<MixinMethodNode>)this.methods;
    }
    
    public MixinInfo getMixin() {
        return MixinInfo.this;
    }
    
    @Override
    public MethodVisitor visitMethod(final int access, final String name, final String desc, final String signature, final String[] exceptions) {
        final MethodNode method = new MixinMethodNode(access, name, desc, signature, exceptions);
        this.methods.add(method);
        return method;
    }
}
