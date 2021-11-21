package org.spongepowered.tools.agent;

import java.util.Iterator;
import org.spongepowered.asm.service.IMixinService;
import java.lang.instrument.ClassDefinition;
import org.spongepowered.asm.service.MixinService;
import org.spongepowered.asm.mixin.transformer.throwables.MixinReloadException;
import java.lang.instrument.IllegalClassFormatException;
import java.util.List;
import java.security.ProtectionDomain;
import java.lang.instrument.ClassFileTransformer;

class Transformer implements ClassFileTransformer
{
    @Override
    public byte[] transform(final ClassLoader loader, final String className, final Class<?> classBeingRedefined, final ProtectionDomain domain, final byte[] classfileBuffer) throws IllegalClassFormatException {
        if (classBeingRedefined == null) {
            return null;
        }
        final byte[] mixinBytecode = MixinAgent.classLoader.getFakeMixinBytecode(classBeingRedefined);
        if (mixinBytecode != null) {
            final List<String> targets = this.reloadMixin(className, classfileBuffer);
            if (targets == null || !this.reApplyMixins(targets)) {
                return MixinAgent.ERROR_BYTECODE;
            }
            return mixinBytecode;
        }
        else {
            try {
                MixinAgent.logger.info("Redefining class " + className);
                return MixinAgent.this.classTransformer.transformClassBytes(null, className, classfileBuffer);
            }
            catch (Throwable th) {
                MixinAgent.logger.error("Error while re-transforming class " + className, th);
                return MixinAgent.ERROR_BYTECODE;
            }
        }
    }
    
    private List<String> reloadMixin(final String className, final byte[] classfileBuffer) {
        MixinAgent.logger.info("Redefining mixin {}", new Object[] { className });
        try {
            return MixinAgent.this.classTransformer.reload(className.replace('/', '.'), classfileBuffer);
        }
        catch (MixinReloadException e) {
            MixinAgent.logger.error("Mixin {} cannot be reloaded, needs a restart to be applied: {} ", new Object[] { e.getMixinInfo(), e.getMessage() });
        }
        catch (Throwable th) {
            MixinAgent.logger.error("Error while finding targets for mixin " + className, th);
        }
        return null;
    }
    
    private boolean reApplyMixins(final List<String> targets) {
        final IMixinService service = MixinService.getService();
        for (final String target : targets) {
            final String targetName = target.replace('/', '.');
            MixinAgent.logger.debug("Re-transforming target class {}", new Object[] { target });
            try {
                final Class<?> targetClass = service.getClassProvider().findClass(targetName);
                byte[] targetBytecode = MixinAgent.classLoader.getOriginalTargetBytecode(targetName);
                if (targetBytecode == null) {
                    MixinAgent.logger.error("Target class {} bytecode is not registered", new Object[] { targetName });
                    return false;
                }
                targetBytecode = MixinAgent.this.classTransformer.transformClassBytes(null, targetName, targetBytecode);
                MixinAgent.instrumentation.redefineClasses(new ClassDefinition(targetClass, targetBytecode));
            }
            catch (Throwable th) {
                MixinAgent.logger.error("Error while re-transforming target class " + target, th);
                return false;
            }
        }
        return true;
    }
}
