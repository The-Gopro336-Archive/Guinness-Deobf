package org.newdawn.slick.font.effects;

import javax.swing.JComponent;
import javax.swing.SpinnerModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

static final class EffectUtil$2 extends DefaultValue {
    final /* synthetic */ int val$currentValue;
    final /* synthetic */ String val$description;
    
    @Override
    public void showDialog() {
        final JSpinner spinner = new JSpinner(new SpinnerNumberModel(this.val$currentValue, -32768, 32767, 1));
        if (this.showValueDialog(spinner, this.val$description)) {
            this.value = String.valueOf(spinner.getValue());
        }
    }
    
    @Override
    public Object getObject() {
        return Integer.valueOf(this.value);
    }
}