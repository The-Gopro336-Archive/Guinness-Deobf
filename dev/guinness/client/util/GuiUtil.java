package dev.guinness.client.util;

public class GuiUtil
{
    public static boolean rdown;
    public static int mouseX;
    public static int mouseY;
    public static boolean ldown;
    public static int keydown;
    public static boolean dragging;
    public static boolean lheld;
    
    public static boolean mouseOver(final int n, final int n2, final int n3, final int n4) {
        if (GuiUtil.mouseX >= n) {
            if (GuiUtil.mouseY >= n2) {
                if (GuiUtil.mouseX <= n3) {
                    if (GuiUtil.mouseY <= n4) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public static void updateMousePos(final int mouseX, final int mouseY) {
        GuiUtil.mouseX = mouseX;
        GuiUtil.mouseY = mouseY;
        GuiUtil.ldown = false;
        GuiUtil.rdown = false;
        GuiUtil.keydown = -1;
    }
    
    public static void updateLeftClick() {
        GuiUtil.ldown = true;
        GuiUtil.lheld = true;
    }
    
    public static void updateRightClick() {
        GuiUtil.rdown = true;
    }
    
    public static void updateMouseState() {
        GuiUtil.lheld = false;
    }
    
    public static void updateKeyState(final int keydown) {
        GuiUtil.keydown = keydown;
    }
}
