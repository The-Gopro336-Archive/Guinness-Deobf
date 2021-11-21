package org.newdawn.slick.tests.states;

import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.transition.CrossStateTransition;

class TestState1$1 extends CrossStateTransition {
    final /* synthetic */ long val$start;
    
    @Override
    public boolean isComplete() {
        return System.currentTimeMillis() - this.val$start > 2000L;
    }
    
    @Override
    public void init(final GameState firstState, final GameState secondState) {
    }
}