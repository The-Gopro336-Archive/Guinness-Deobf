package org.spongepowered.asm.mixin.struct;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

static class Stratum
{
    private static final String STRATUM_MARK = "*S";
    private static final String FILE_MARK = "*F";
    private static final String LINES_MARK = "*L";
    public final String name;
    private final Map<String, File> files;
    
    public Stratum(final String name) {
        this.files = new LinkedHashMap<String, File>();
        this.name = name;
    }
    
    public File addFile(final int lineOffset, final int size, final String sourceFileName, final String sourceFilePath) {
        File file = this.files.get(sourceFilePath);
        if (file == null) {
            file = new File(this.files.size() + 1, lineOffset, size, sourceFileName, sourceFilePath);
            this.files.put(sourceFilePath, file);
        }
        return file;
    }
    
    void appendTo(final StringBuilder sb) {
        sb.append("*S").append(" ").append(this.name).append("\n");
        sb.append("*F").append("\n");
        for (final File file : this.files.values()) {
            file.appendFile(sb);
        }
        sb.append("*L").append("\n");
        for (final File file : this.files.values()) {
            file.appendLines(sb);
        }
    }
}
