package org.newdawn.slick.font.effects;

import javax.swing.JComponent;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

static final class EffectUtil$5 extends DefaultValue {
    final /* synthetic */ String[][] val$options;
    final /* synthetic */ String val$currentValue;
    final /* synthetic */ String val$description;
    
    @Override
    public void showDialog() {
        int selectedIndex = -1;
        final DefaultComboBoxModel model = new DefaultComboBoxModel();
        for (int i = 0; i < this.val$options.length; ++i) {
            model.addElement(this.val$options[i][0]);
            if (this.getValue(i).equals(this.val$currentValue)) {
                selectedIndex = i;
            }
        }
        final JComboBox comboBox = new JComboBox(model);
        comboBox.setSelectedIndex(selectedIndex);
        if (this.showValueDialog(comboBox, this.val$description)) {
            this.value = this.getValue(comboBox.getSelectedIndex());
        }
    }
    
    private String getValue(final int i) {
        if (this.val$options[i].length == 1) {
            return this.val$options[i][0];
        }
        return this.val$options[i][1];
    }
    
    @Override
    public String toString() {
        for (int i = 0; i < this.val$options.length; ++i) {
            if (this.getValue(i).equals(this.value)) {
                return this.val$options[i][0].toString();
            }
        }
        return "";
    }
    
    @Override
    public Object getObject() {
        return this.value;
    }
}