package org.newdawn.slick.font.effects;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JColorChooser;

static final class EffectUtil$1 extends DefaultValue {
    @Override
    public void showDialog() {
        final Color newColor = JColorChooser.showDialog(null, "Choose a color", EffectUtil.fromString(this.value));
        if (newColor != null) {
            this.value = EffectUtil.toString(newColor);
        }
    }
    
    @Override
    public Object getObject() {
        return EffectUtil.fromString(this.value);
    }
}