package org.spongepowered.asm.mixin.transformer.debug;

import java.util.jar.Manifest;
import com.google.common.io.Files;
import com.google.common.base.Charsets;
import org.jetbrains.java.decompiler.main.Fernflower;
import org.jetbrains.java.decompiler.util.InterpreterUtil;
import org.jetbrains.java.decompiler.main.extern.IBytecodeProvider;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import com.google.common.collect.ImmutableMap;
import org.apache.logging.log4j.Logger;
import java.io.File;
import java.util.Map;
import org.apache.logging.log4j.Level;
import org.jetbrains.java.decompiler.main.extern.IResultSaver;
import org.spongepowered.asm.mixin.transformer.ext.IDecompiler;
import org.jetbrains.java.decompiler.main.extern.IFernflowerLogger;

public class RuntimeDecompiler extends IFernflowerLogger implements IDecompiler, IResultSaver
{
    private static final Level[] SEVERITY_LEVELS;
    private final Map<String, Object> options;
    private final File outputPath;
    protected final Logger logger;
    
    public RuntimeDecompiler(final File outputPath) {
        this.options = (Map<String, Object>)ImmutableMap.builder().put("din", "0").put("rbr", "0").put("dgs", "1").put("asc", "1").put("den", "1").put("hdc", "1").put("ind", "    ").build();
        this.logger = LogManager.getLogger("fernflower");
        this.outputPath = outputPath;
        if (this.outputPath.exists()) {
            try {
                FileUtils.deleteDirectory(this.outputPath);
            }
            catch (IOException ex) {
                this.logger.warn("Error cleaning output directory: {}", new Object[] { ex.getMessage() });
            }
        }
    }
    
    @Override
    public void decompile(final File file) {
        try {
            final Fernflower fernflower = new Fernflower(new IBytecodeProvider() {
                private byte[] byteCode;
                
                @Override
                public byte[] getBytecode(final String externalPath, final String internalPath) throws IOException {
                    if (this.byteCode == null) {
                        this.byteCode = InterpreterUtil.getBytes(new File(externalPath));
                    }
                    return this.byteCode;
                }
            }, this, this.options, this);
            fernflower.getStructContext().addSpace(file, true);
            fernflower.decompileContext();
        }
        catch (Throwable ex) {
            this.logger.warn("Decompilation error while processing {}", new Object[] { file.getName() });
        }
    }
    
    @Override
    public void saveFolder(final String path) {
    }
    
    @Override
    public void saveClassFile(final String path, final String qualifiedName, final String entryName, final String content, final int[] mapping) {
        final File file = new File(this.outputPath, qualifiedName + ".java");
        file.getParentFile().mkdirs();
        try {
            this.logger.info("Writing {}", new Object[] { file.getAbsolutePath() });
            Files.write(content, file, Charsets.UTF_8);
        }
        catch (IOException ex) {
            this.writeMessage("Cannot write source file " + file, ex);
        }
    }
    
    @Override
    public void startReadingClass(final String className) {
        this.logger.info("Decompiling {}", new Object[] { className });
    }
    
    @Override
    public void writeMessage(final String message, final Severity severity) {
        this.logger.log(RuntimeDecompiler.SEVERITY_LEVELS[severity.ordinal()], message);
    }
    
    @Override
    public void writeMessage(final String message, final Throwable t) {
        this.logger.warn("{} {}: {}", new Object[] { message, t.getClass().getSimpleName(), t.getMessage() });
    }
    
    @Override
    public void writeMessage(final String message, final Severity severity, final Throwable t) {
        this.logger.log(RuntimeDecompiler.SEVERITY_LEVELS[severity.ordinal()], message, t);
    }
    
    @Override
    public void copyFile(final String source, final String path, final String entryName) {
    }
    
    @Override
    public void createArchive(final String path, final String archiveName, final Manifest manifest) {
    }
    
    @Override
    public void saveDirEntry(final String path, final String archiveName, final String entryName) {
    }
    
    @Override
    public void copyEntry(final String source, final String path, final String archiveName, final String entry) {
    }
    
    @Override
    public void saveClassEntry(final String path, final String archiveName, final String qualifiedName, final String entryName, final String content) {
    }
    
    @Override
    public void closeArchive(final String path, final String archiveName) {
    }
    
    static {
        SEVERITY_LEVELS = new Level[] { Level.TRACE, Level.INFO, Level.WARN, Level.ERROR };
    }
}
