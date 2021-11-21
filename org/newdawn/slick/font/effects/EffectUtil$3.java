package org.newdawn.slick.font.effects;

import javax.swing.JComponent;
import javax.swing.SpinnerModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

static final class EffectUtil$3 extends DefaultValue {
    final /* synthetic */ float val$currentValue;
    final /* synthetic */ float val$min;
    final /* synthetic */ float val$max;
    final /* synthetic */ String val$description;
    
    @Override
    public void showDialog() {
        final JSpinner spinner = new JSpinner(new SpinnerNumberModel(this.val$currentValue, this.val$min, this.val$max, 0.10000000149011612));
        if (this.showValueDialog(spinner, this.val$description)) {
            this.value = String.valueOf(((Double)spinner.getValue()).floatValue());
        }
    }
    
    @Override
    public Object getObject() {
        return Float.valueOf(this.value);
    }
}