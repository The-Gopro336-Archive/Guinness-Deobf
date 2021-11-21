package org.newdawn.slick.font.effects;

import javax.swing.JComponent;
import javax.swing.JCheckBox;

static final class EffectUtil$4 extends DefaultValue {
    final /* synthetic */ boolean val$currentValue;
    final /* synthetic */ String val$description;
    
    @Override
    public void showDialog() {
        final JCheckBox checkBox = new JCheckBox();
        checkBox.setSelected(this.val$currentValue);
        if (this.showValueDialog(checkBox, this.val$description)) {
            this.value = String.valueOf(checkBox.isSelected());
        }
    }
    
    @Override
    public Object getObject() {
        return Boolean.valueOf(this.value);
    }
}