package org.spongepowered.asm.mixin.transformer;

public class InterfaceMethod extends Method
{
    private final ClassInfo owner;
    
    public InterfaceMethod(final Member member) {
        super(member);
        this.owner = member.getOwner();
    }
    
    @Override
    public ClassInfo getOwner() {
        return this.owner;
    }
    
    @Override
    public ClassInfo getImplementor() {
        return ClassInfo.this;
    }
}
