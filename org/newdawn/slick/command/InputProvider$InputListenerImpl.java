package org.newdawn.slick.command;

import org.newdawn.slick.util.InputAdapter;

private class InputListenerImpl extends InputAdapter
{
    @Override
    public boolean isAcceptingInput() {
        return true;
    }
    
    @Override
    public void keyPressed(final int key, final char c) {
        final Command command = InputProvider.access$400(InputProvider.this).get(new KeyControl(key));
        if (command != null) {
            InputProvider.this.firePressed(command);
        }
    }
    
    @Override
    public void keyReleased(final int key, final char c) {
        final Command command = InputProvider.access$400(InputProvider.this).get(new KeyControl(key));
        if (command != null) {
            InputProvider.this.fireReleased(command);
        }
    }
    
    @Override
    public void mousePressed(final int button, final int x, final int y) {
        final Command command = InputProvider.access$400(InputProvider.this).get(new MouseButtonControl(button));
        if (command != null) {
            InputProvider.this.firePressed(command);
        }
    }
    
    @Override
    public void mouseReleased(final int button, final int x, final int y) {
        final Command command = InputProvider.access$400(InputProvider.this).get(new MouseButtonControl(button));
        if (command != null) {
            InputProvider.this.fireReleased(command);
        }
    }
    
    @Override
    public void controllerLeftPressed(final int controller) {
        final Command command = InputProvider.access$400(InputProvider.this).get(new ControllerDirectionControl(controller, ControllerDirectionControl.LEFT));
        if (command != null) {
            InputProvider.this.firePressed(command);
        }
    }
    
    @Override
    public void controllerLeftReleased(final int controller) {
        final Command command = InputProvider.access$400(InputProvider.this).get(new ControllerDirectionControl(controller, ControllerDirectionControl.LEFT));
        if (command != null) {
            InputProvider.this.fireReleased(command);
        }
    }
    
    @Override
    public void controllerRightPressed(final int controller) {
        final Command command = InputProvider.access$400(InputProvider.this).get(new ControllerDirectionControl(controller, ControllerDirectionControl.RIGHT));
        if (command != null) {
            InputProvider.this.firePressed(command);
        }
    }
    
    @Override
    public void controllerRightReleased(final int controller) {
        final Command command = InputProvider.access$400(InputProvider.this).get(new ControllerDirectionControl(controller, ControllerDirectionControl.RIGHT));
        if (command != null) {
            InputProvider.this.fireReleased(command);
        }
    }
    
    @Override
    public void controllerUpPressed(final int controller) {
        final Command command = InputProvider.access$400(InputProvider.this).get(new ControllerDirectionControl(controller, ControllerDirectionControl.UP));
        if (command != null) {
            InputProvider.this.firePressed(command);
        }
    }
    
    @Override
    public void controllerUpReleased(final int controller) {
        final Command command = InputProvider.access$400(InputProvider.this).get(new ControllerDirectionControl(controller, ControllerDirectionControl.UP));
        if (command != null) {
            InputProvider.this.fireReleased(command);
        }
    }
    
    @Override
    public void controllerDownPressed(final int controller) {
        final Command command = InputProvider.access$400(InputProvider.this).get(new ControllerDirectionControl(controller, ControllerDirectionControl.DOWN));
        if (command != null) {
            InputProvider.this.firePressed(command);
        }
    }
    
    @Override
    public void controllerDownReleased(final int controller) {
        final Command command = InputProvider.access$400(InputProvider.this).get(new ControllerDirectionControl(controller, ControllerDirectionControl.DOWN));
        if (command != null) {
            InputProvider.this.fireReleased(command);
        }
    }
    
    @Override
    public void controllerButtonPressed(final int controller, final int button) {
        final Command command = InputProvider.access$400(InputProvider.this).get(new ControllerButtonControl(controller, button));
        if (command != null) {
            InputProvider.this.firePressed(command);
        }
    }
    
    @Override
    public void controllerButtonReleased(final int controller, final int button) {
        final Command command = InputProvider.access$400(InputProvider.this).get(new ControllerButtonControl(controller, button));
        if (command != null) {
            InputProvider.this.fireReleased(command);
        }
    }
}
