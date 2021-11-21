package org.newdawn.slick.util.xml;

import org.newdawn.slick.SlickException;

public class SlickXMLException extends SlickException
{
    public SlickXMLException(final String message) {
        super(message);
    }
    
    public SlickXMLException(final String message, final Throwable e) {
        super(message, e);
    }
}
