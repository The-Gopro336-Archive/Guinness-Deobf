package org.newdawn.slick.font.effects;

import java.awt.EventQueue;
import javax.swing.JSpinner;
import java.awt.Component;
import javax.swing.JComponent;

private abstract static class DefaultValue implements ConfigurableEffect.Value
{
    String value;
    String name;
    
    public DefaultValue(final String name, final String value) {
        this.value = value;
        this.name = name;
    }
    
    @Override
    public void setString(final String value) {
        this.value = value;
    }
    
    @Override
    public String getString() {
        return this.value;
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    
    @Override
    public String toString() {
        if (this.value == null) {
            return "";
        }
        return this.value.toString();
    }
    
    public boolean showValueDialog(final JComponent component, final String description) {
        final ValueDialog dialog = new ValueDialog(component, this.name, description);
        dialog.setTitle(this.name);
        dialog.setLocationRelativeTo(null);
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JComponent focusComponent = component;
                if (focusComponent instanceof JSpinner) {
                    focusComponent = ((JSpinner.DefaultEditor)((JSpinner)component).getEditor()).getTextField();
                }
                focusComponent.requestFocusInWindow();
            }
        });
        dialog.setVisible(true);
        return dialog.okPressed;
    }
}
