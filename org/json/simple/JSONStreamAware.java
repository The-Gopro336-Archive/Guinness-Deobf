package org.json.simple;

import java.io.IOException;
import java.io.Writer;

public interface JSONStreamAware
{
    void writeJSONString(final Writer p0) throws IOException;
}
