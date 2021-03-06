package org.spongepowered.asm.mixin.injection;

public enum Condition
{
    LESS_THAN_ZERO(new int[] { 155, 156 }), 
    LESS_THAN_OR_EQUAL_TO_ZERO(new int[] { 158, 157 }), 
    GREATER_THAN_OR_EQUAL_TO_ZERO(Condition.LESS_THAN_ZERO), 
    GREATER_THAN_ZERO(Condition.LESS_THAN_OR_EQUAL_TO_ZERO);
    
    private final int[] opcodes;
    private final Condition equivalence;
    
    private Condition(final int[] opcodes) {
        this(null, opcodes);
    }
    
    private Condition(final Condition equivalence) {
        this(equivalence, equivalence.opcodes);
    }
    
    private Condition(final Condition equivalence, final int[] opcodes) {
        this.equivalence = ((equivalence != null) ? equivalence : this);
        this.opcodes = opcodes;
    }
    
    public Condition getEquivalentCondition() {
        return this.equivalence;
    }
    
    public int[] getOpcodes() {
        return this.opcodes;
    }
}
