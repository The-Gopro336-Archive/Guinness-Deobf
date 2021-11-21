package org.spongepowered.asm.mixin.transformer;

abstract static class Member
{
    private final Type type;
    private final String memberName;
    private final String memberDesc;
    private final boolean isInjected;
    private final int modifiers;
    private String currentName;
    private String currentDesc;
    private boolean decoratedFinal;
    private boolean decoratedMutable;
    private boolean unique;
    
    protected Member(final Member member) {
        this(member.type, member.memberName, member.memberDesc, member.modifiers, member.isInjected);
        this.currentName = member.currentName;
        this.currentDesc = member.currentDesc;
        this.unique = member.unique;
    }
    
    protected Member(final Type type, final String name, final String desc, final int access) {
        this(type, name, desc, access, false);
    }
    
    protected Member(final Type type, final String name, final String desc, final int access, final boolean injected) {
        this.type = type;
        this.memberName = name;
        this.memberDesc = desc;
        this.isInjected = injected;
        this.currentName = name;
        this.currentDesc = desc;
        this.modifiers = access;
    }
    
    public String getOriginalName() {
        return this.memberName;
    }
    
    public String getName() {
        return this.currentName;
    }
    
    public String getOriginalDesc() {
        return this.memberDesc;
    }
    
    public String getDesc() {
        return this.currentDesc;
    }
    
    public boolean isInjected() {
        return this.isInjected;
    }
    
    public boolean isRenamed() {
        return !this.currentName.equals(this.memberName);
    }
    
    public boolean isRemapped() {
        return !this.currentDesc.equals(this.memberDesc);
    }
    
    public boolean isPrivate() {
        return (this.modifiers & 0x2) != 0x0;
    }
    
    public boolean isStatic() {
        return (this.modifiers & 0x8) != 0x0;
    }
    
    public boolean isAbstract() {
        return (this.modifiers & 0x400) != 0x0;
    }
    
    public boolean isFinal() {
        return (this.modifiers & 0x10) != 0x0;
    }
    
    public boolean isSynthetic() {
        return (this.modifiers & 0x1000) != 0x0;
    }
    
    public boolean isUnique() {
        return this.unique;
    }
    
    public void setUnique(final boolean unique) {
        this.unique = unique;
    }
    
    public boolean isDecoratedFinal() {
        return this.decoratedFinal;
    }
    
    public boolean isDecoratedMutable() {
        return this.decoratedMutable;
    }
    
    public void setDecoratedFinal(final boolean decoratedFinal, final boolean decoratedMutable) {
        this.decoratedFinal = decoratedFinal;
        this.decoratedMutable = decoratedMutable;
    }
    
    public boolean matchesFlags(final int flags) {
        return ((~this.modifiers | (flags & 0x2)) & 0x2) != 0x0 && ((~this.modifiers | (flags & 0x8)) & 0x8) != 0x0;
    }
    
    public abstract ClassInfo getOwner();
    
    public ClassInfo getImplementor() {
        return this.getOwner();
    }
    
    public int getAccess() {
        return this.modifiers;
    }
    
    public String renameTo(final String name) {
        return this.currentName = name;
    }
    
    public String remapTo(final String desc) {
        return this.currentDesc = desc;
    }
    
    public boolean equals(final String name, final String desc) {
        return (this.memberName.equals(name) || this.currentName.equals(name)) && (this.memberDesc.equals(desc) || this.currentDesc.equals(desc));
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof Member)) {
            return false;
        }
        final Member other = (Member)obj;
        return (other.memberName.equals(this.memberName) || other.currentName.equals(this.currentName)) && (other.memberDesc.equals(this.memberDesc) || other.currentDesc.equals(this.currentDesc));
    }
    
    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
    
    @Override
    public String toString() {
        return String.format(this.getDisplayFormat(), this.memberName, this.memberDesc);
    }
    
    protected String getDisplayFormat() {
        return "%s%s";
    }
    
    enum Type
    {
        METHOD, 
        FIELD;
    }
}
