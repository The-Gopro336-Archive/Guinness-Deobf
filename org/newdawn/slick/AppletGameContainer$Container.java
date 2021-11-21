package org.newdawn.slick;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.ImageData;
import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.input.Cursor;
import org.newdawn.slick.opengl.CursorLoader;
import org.lwjgl.input.Mouse;
import java.applet.Applet;
import org.newdawn.slick.util.Log;

public class Container extends GameContainer
{
    public Container(final Game game) {
        super(game);
        this.width = AppletGameContainer.this.getWidth();
        this.height = AppletGameContainer.this.getHeight();
    }
    
    public void initApplet() throws SlickException {
        this.initSystem();
        this.enterOrtho();
        try {
            this.getInput().initControllers();
        }
        catch (SlickException e) {
            Log.info("Controllers not available");
        }
        catch (Throwable e2) {
            Log.info("Controllers not available");
        }
        this.game.init(this);
        this.getDelta();
    }
    
    public boolean isRunning() {
        return this.running;
    }
    
    public void stopApplet() {
        this.running = false;
    }
    
    @Override
    public int getScreenHeight() {
        return 0;
    }
    
    @Override
    public int getScreenWidth() {
        return 0;
    }
    
    public boolean supportsAlphaInBackBuffer() {
        return AppletGameContainer.this.alphaSupport;
    }
    
    @Override
    public boolean hasFocus() {
        return true;
    }
    
    public Applet getApplet() {
        return AppletGameContainer.this;
    }
    
    @Override
    public void setIcon(final String ref) throws SlickException {
    }
    
    @Override
    public void setMouseGrabbed(final boolean grabbed) {
        Mouse.setGrabbed(grabbed);
    }
    
    @Override
    public boolean isMouseGrabbed() {
        return Mouse.isGrabbed();
    }
    
    @Override
    public void setMouseCursor(final String ref, final int hotSpotX, final int hotSpotY) throws SlickException {
        try {
            final Cursor cursor = CursorLoader.get().getCursor(ref, hotSpotX, hotSpotY);
            Mouse.setNativeCursor(cursor);
        }
        catch (Throwable e) {
            Log.error("Failed to load and apply cursor.", e);
            throw new SlickException("Failed to set mouse cursor", e);
        }
    }
    
    private int get2Fold(final int fold) {
        int ret;
        for (ret = 2; ret < fold; ret *= 2) {}
        return ret;
    }
    
    @Override
    public void setMouseCursor(final Image image, final int hotSpotX, final int hotSpotY) throws SlickException {
        try {
            final Image temp = new Image(this.get2Fold(image.getWidth()), this.get2Fold(image.getHeight()));
            final Graphics g = temp.getGraphics();
            final ByteBuffer buffer = BufferUtils.createByteBuffer(temp.getWidth() * temp.getHeight() * 4);
            g.drawImage(image.getFlippedCopy(false, true), 0.0f, 0.0f);
            g.flush();
            g.getArea(0, 0, temp.getWidth(), temp.getHeight(), buffer);
            final Cursor cursor = CursorLoader.get().getCursor(buffer, hotSpotX, hotSpotY, temp.getWidth(), temp.getHeight());
            Mouse.setNativeCursor(cursor);
        }
        catch (Throwable e) {
            Log.error("Failed to load and apply cursor.", e);
            throw new SlickException("Failed to set mouse cursor", e);
        }
    }
    
    @Override
    public void setIcons(final String[] refs) throws SlickException {
    }
    
    @Override
    public void setMouseCursor(final ImageData data, final int hotSpotX, final int hotSpotY) throws SlickException {
        try {
            final Cursor cursor = CursorLoader.get().getCursor(data, hotSpotX, hotSpotY);
            Mouse.setNativeCursor(cursor);
        }
        catch (Throwable e) {
            Log.error("Failed to load and apply cursor.", e);
            throw new SlickException("Failed to set mouse cursor", e);
        }
    }
    
    @Override
    public void setMouseCursor(final Cursor cursor, final int hotSpotX, final int hotSpotY) throws SlickException {
        try {
            Mouse.setNativeCursor(cursor);
        }
        catch (Throwable e) {
            Log.error("Failed to load and apply cursor.", e);
            throw new SlickException("Failed to set mouse cursor", e);
        }
    }
    
    @Override
    public void setDefaultMouseCursor() {
    }
    
    @Override
    public boolean isFullscreen() {
        return Display.isFullscreen();
    }
    
    @Override
    public void setFullscreen(final boolean fullscreen) throws SlickException {
        if (fullscreen == this.isFullscreen()) {
            return;
        }
        try {
            if (fullscreen) {
                final int screenWidth = Display.getDisplayMode().getWidth();
                final int screenHeight = Display.getDisplayMode().getHeight();
                final float gameAspectRatio = this.width / (float)this.height;
                final float screenAspectRatio = screenWidth / (float)screenHeight;
                int newWidth;
                int newHeight;
                if (gameAspectRatio >= screenAspectRatio) {
                    newWidth = screenWidth;
                    newHeight = (int)(this.height / (this.width / (float)screenWidth));
                }
                else {
                    newWidth = (int)(this.width / (this.height / (float)screenHeight));
                    newHeight = screenHeight;
                }
                final int xoffset = (screenWidth - newWidth) / 2;
                final int yoffset = (screenHeight - newHeight) / 2;
                GL11.glViewport(xoffset, yoffset, newWidth, newHeight);
                this.enterOrtho();
                this.getInput().setOffset(-xoffset * (float)this.width / newWidth, -yoffset * (float)this.height / newHeight);
                this.getInput().setScale(this.width / (float)newWidth, this.height / (float)newHeight);
                this.width = screenWidth;
                this.height = screenHeight;
                Display.setFullscreen(true);
            }
            else {
                this.getInput().setOffset(0.0f, 0.0f);
                this.getInput().setScale(1.0f, 1.0f);
                this.width = AppletGameContainer.this.getWidth();
                this.height = AppletGameContainer.this.getHeight();
                GL11.glViewport(0, 0, this.width, this.height);
                this.enterOrtho();
                Display.setFullscreen(false);
            }
        }
        catch (LWJGLException e) {
            Log.error(e);
        }
    }
    
    public void runloop() throws Exception {
        while (this.running) {
            final int delta = this.getDelta();
            this.updateAndRender(delta);
            this.updateFPS();
            Display.update();
        }
        Display.destroy();
    }
}
