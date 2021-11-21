package org.newdawn.slick;

import java.awt.GridLayout;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.awt.Component;
import java.awt.Label;
import java.awt.Font;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.TextArea;
import java.awt.Panel;

public class ConsolePanel extends Panel
{
    TextArea textArea;
    
    public ConsolePanel(final Exception e) {
        this.textArea = new TextArea();
        this.setLayout(new BorderLayout());
        this.setBackground(Color.black);
        this.setForeground(Color.white);
        final Font consoleFont = new Font("Arial", 1, 14);
        final Label slickLabel = new Label("SLICK CONSOLE", 1);
        slickLabel.setFont(consoleFont);
        this.add(slickLabel, "First");
        final StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        this.textArea.setText(sw.toString());
        this.textArea.setEditable(false);
        this.add(this.textArea, "Center");
        this.add(new Panel(), "Before");
        this.add(new Panel(), "After");
        final Panel bottomPanel = new Panel();
        bottomPanel.setLayout(new GridLayout(0, 1));
        final Label infoLabel1 = new Label("An error occured while running the applet.", 1);
        final Label infoLabel2 = new Label("Plese contact support to resolve this issue.", 1);
        infoLabel1.setFont(consoleFont);
        infoLabel2.setFont(consoleFont);
        bottomPanel.add(infoLabel1);
        bottomPanel.add(infoLabel2);
        this.add(bottomPanel, "Last");
    }
}
