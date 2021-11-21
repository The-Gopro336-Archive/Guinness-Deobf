package org.newdawn.slick.font.effects;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import javax.swing.JComponent;
import javax.swing.JDialog;

private static class ValueDialog extends JDialog
{
    public boolean okPressed;
    
    public ValueDialog(final JComponent component, final String name, final String description) {
        this.okPressed = false;
        this.setDefaultCloseOperation(2);
        this.setLayout(new GridBagLayout());
        this.setModal(true);
        if (component instanceof JSpinner) {
            ((JSpinner.DefaultEditor)((JSpinner)component).getEditor()).getTextField().setColumns(4);
        }
        final JPanel descriptionPanel = new JPanel();
        descriptionPanel.setLayout(new GridBagLayout());
        this.getContentPane().add(descriptionPanel, new GridBagConstraints(0, 0, 2, 1, 1.0, 0.0, 10, 1, new Insets(0, 0, 0, 0), 0, 0));
        descriptionPanel.setBackground(Color.white);
        descriptionPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        final JTextArea descriptionText = new JTextArea(description);
        descriptionPanel.add(descriptionText, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, 10, 1, new Insets(5, 5, 5, 5), 0, 0));
        descriptionText.setWrapStyleWord(true);
        descriptionText.setLineWrap(true);
        descriptionText.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        descriptionText.setEditable(false);
        final JPanel panel = new JPanel();
        this.getContentPane().add(panel, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, 10, 0, new Insets(5, 5, 0, 5), 0, 0));
        panel.add(new JLabel(name + ":"));
        panel.add(component);
        final JPanel buttonPanel = new JPanel();
        this.getContentPane().add(buttonPanel, new GridBagConstraints(0, 2, 2, 1, 0.0, 0.0, 13, 0, new Insets(0, 0, 0, 0), 0, 0));
        final JButton okButton = new JButton("OK");
        buttonPanel.add(okButton);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                ValueDialog.this.okPressed = true;
                ValueDialog.this.setVisible(false);
            }
        });
        final JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(cancelButton);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                ValueDialog.this.setVisible(false);
            }
        });
        this.setSize(new Dimension(320, 175));
    }
}
