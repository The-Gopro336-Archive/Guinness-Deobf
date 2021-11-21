package org.spongepowered.asm.mixin;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.appender.AbstractAppender;

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
