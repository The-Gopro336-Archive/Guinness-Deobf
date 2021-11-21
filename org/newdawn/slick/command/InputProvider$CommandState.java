package org.newdawn.slick.command;

private class CommandState
{
    private boolean down;
    private boolean pressed;
    
    public boolean isPressed() {
        if (this.pressed) {
            this.pressed = false;
            return true;
        }
        return false;
    }
    
    public boolean isDown() {
        return this.down;
    }
}
