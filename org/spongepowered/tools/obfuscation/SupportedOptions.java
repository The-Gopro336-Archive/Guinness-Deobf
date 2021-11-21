package org.spongepowered.tools.obfuscation;

import org.spongepowered.tools.obfuscation.service.ObfuscationServices;
import com.google.common.collect.ImmutableSet;
import java.util.Set;

public final class SupportedOptions
{
    public static final String TOKENS = "tokens";
    public static final String OUT_REFMAP_FILE = "outRefMapFile";
    public static final String DISABLE_TARGET_VALIDATOR = "disableTargetValidator";
    public static final String DISABLE_TARGET_EXPORT = "disableTargetExport";
    public static final String DISABLE_OVERWRITE_CHECKER = "disableOverwriteChecker";
    public static final String OVERWRITE_ERROR_LEVEL = "overwriteErrorLevel";
    public static final String DEFAULT_OBFUSCATION_ENV = "defaultObfuscationEnv";
    public static final String DEPENDENCY_TARGETS_FILE = "dependencyTargetsFile";
    
    private SupportedOptions() {
    }
    
    public static Set<String> getAllOptions() {
        final ImmutableSet.Builder<String> options = ImmutableSet.builder();
        options.add("tokens", "outRefMapFile", "disableTargetValidator", "disableTargetExport", "disableOverwriteChecker", "overwriteErrorLevel", "defaultObfuscationEnv", "dependencyTargetsFile");
        options.addAll(ObfuscationServices.getInstance().getSupportedOptions());
        return options.build();
    }
}
