package org.newdawn.slick.font.effects;

import javax.swing.JSpinner;
import javax.swing.JComponent;

class EffectUtil$DefaultValue$1 implements Runnable {
    final /* synthetic */ JComponent val$component;
    
    @Override
    public void run() {
        JComponent focusComponent = this.val$component;
        if (focusComponent instanceof JSpinner) {
            focusComponent = ((JSpinner.DefaultEditor)((JSpinner)this.val$component).getEditor()).getTextField();
        }
        focusComponent.requestFocusInWindow();
    }
}