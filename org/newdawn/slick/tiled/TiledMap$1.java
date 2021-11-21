package org.newdawn.slick.tiled;

import java.io.IOException;
import org.xml.sax.SAXException;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import org.xml.sax.InputSource;
import org.xml.sax.EntityResolver;

class TiledMap$1 implements EntityResolver {
    @Override
    public InputSource resolveEntity(final String publicId, final String systemId) throws SAXException, IOException {
        return new InputSource(new ByteArrayInputStream(new byte[0]));
    }
}