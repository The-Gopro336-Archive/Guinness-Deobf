package org.spongepowered.asm.mixin.transformer;

import java.util.Iterator;
import org.spongepowered.asm.lib.tree.FrameNode;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import java.util.ArrayList;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.gen.Accessor;
import java.lang.annotation.Annotation;
import org.spongepowered.asm.util.Annotations;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.lib.tree.MethodNode;
import java.util.List;

public class Method extends Member
{
    private final List<FrameData> frames;
    private boolean isAccessor;
    
    public Method(final Member member) {
        super(member);
        this.frames = ((member instanceof Method) ? ((Method)member).frames : null);
    }
    
    public Method(final ClassInfo this$0, final MethodNode method) {
        this(this$0, method, false);
        this.setUnique(Annotations.getVisible(method, Unique.class) != null);
        this.isAccessor = (Annotations.getSingleVisible(method, Accessor.class, Invoker.class) != null);
    }
    
    public Method(final MethodNode method, final boolean injected) {
        super(Type.METHOD, method.name, method.desc, method.access, injected);
        this.frames = this.gatherFrames(method);
        this.setUnique(Annotations.getVisible(method, Unique.class) != null);
        this.isAccessor = (Annotations.getSingleVisible(method, Accessor.class, Invoker.class) != null);
    }
    
    public Method(final String name, final String desc) {
        super(Type.METHOD, name, desc, 1, false);
        this.frames = null;
    }
    
    public Method(final String name, final String desc, final int access) {
        super(Type.METHOD, name, desc, access, false);
        this.frames = null;
    }
    
    public Method(final String name, final String desc, final int access, final boolean injected) {
        super(Type.METHOD, name, desc, access, injected);
        this.frames = null;
    }
    
    private List<FrameData> gatherFrames(final MethodNode method) {
        final List<FrameData> frames = new ArrayList<FrameData>();
        for (final AbstractInsnNode insn : method.instructions) {
            if (insn instanceof FrameNode) {
                frames.add(new FrameData(method.instructions.indexOf(insn), (FrameNode)insn));
            }
        }
        return frames;
    }
    
    public List<FrameData> getFrames() {
        return this.frames;
    }
    
    @Override
    public ClassInfo getOwner() {
        return ClassInfo.this;
    }
    
    public boolean isAccessor() {
        return this.isAccessor;
    }
    
    @Override
    public boolean equals(final Object obj) {
        return obj instanceof Method && super.equals(obj);
    }
}
