package dev.guinness.client.gui;

import dev.guinness.client.util.GuiUtil;
import net.minecraft.client.gui.ScaledResolution;
import dev.guinness.client.util.Wrapper;

public abstract class GenericGuiWindow implements Wrapper
{
    public boolean dragging;
    public int height;
    public String title;
    public int oldMouseX;
    public int width;
    public int y;
    public int oldMouseY;
    public int x;
    
    public GenericGuiWindow(final String title, final int x, final int y) {
        this.title = title;
        this.x = x;
        this.y = y;
    }
    
    public abstract void draw(final int p0, final int p1);
    
    public void setWidthAndHeight(final int width, final int height) {
        this.width = width;
        this.height = height;
    }
    
    public void updateMousePos() {
        final ScaledResolution sr = new ScaledResolution(GenericGuiWindow.mc);
        if (this.x < 0) {
            this.x = 0;
            return;
        }
        if (this.y < 0) {
            this.y = 0;
            return;
        }
        if (this.x + this.width > sr.getScaledWidth()) {
            this.x = sr.getScaledWidth() - this.width;
            return;
        }
        if (this.y + this.height > sr.getScaledHeight()) {
            this.y = sr.getScaledHeight() - this.height;
            return;
        }
        if (this.dragging) {
            this.x = GuiUtil.mouseX - (this.oldMouseX - this.x);
            this.y = GuiUtil.mouseY - (this.oldMouseY - this.y);
        }
        this.oldMouseX = GuiUtil.mouseX;
        this.oldMouseY = GuiUtil.mouseY;
    }
    
    public void onLeftClick() {
        if (GuiUtil.mouseOver(this.x, this.y, this.x + this.width, this.y + this.height)) {
            this.dragging = true;
        }
    }
    
    public void onLeftMouseRelease() {
        this.dragging = false;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public void setX(final int x) {
        this.x = x;
    }
    
    public void setY(final int y) {
        this.y = y;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }
}
