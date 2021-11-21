package org.newdawn.slick;

import java.io.IOException;
import java.io.OutputStream;

private class NullOutputStream extends OutputStream
{
    @Override
    public void write(final int b) throws IOException {
    }
}
