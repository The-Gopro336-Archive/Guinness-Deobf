package org.spongepowered.asm.mixin;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Logger;

static class MixinLogWatcher
{
    static MixinAppender appender;
    static Logger log;
    static Level oldLevel;
    
    static void begin() {
        final org.apache.logging.log4j.Logger fmlLog = LogManager.getLogger("FML");
        if (!(fmlLog instanceof Logger)) {
            return;
        }
        MixinLogWatcher.log = (Logger)fmlLog;
        MixinLogWatcher.oldLevel = MixinLogWatcher.log.getLevel();
        MixinLogWatcher.appender.start();
        MixinLogWatcher.log.addAppender((Appender)MixinLogWatcher.appender);
        MixinLogWatcher.log.setLevel(Level.ALL);
    }
    
    static void end() {
        if (MixinLogWatcher.log != null) {
            MixinLogWatcher.log.removeAppender((Appender)MixinLogWatcher.appender);
        }
    }
    
    static {
        MixinLogWatcher.appender = new MixinAppender();
        MixinLogWatcher.oldLevel = null;
    }
    
    static class MixinAppender extends AbstractAppender
    {
        MixinAppender() {
            super("MixinLogWatcherAppender", (Filter)null, (Layout)null);
        }
        
        public void append(final LogEvent event) {
            if (event.getLevel() != Level.DEBUG || !"Validating minecraft".equals(event.getMessage().getFormattedMessage())) {
                return;
            }
            MixinEnvironment.gotoPhase(Phase.INIT);
            if (MixinLogWatcher.log.getLevel() == Level.ALL) {
                MixinLogWatcher.log.setLevel(MixinLogWatcher.oldLevel);
            }
        }
    }
}
