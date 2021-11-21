package org.spongepowered.asm.mixin.transformer;

import com.google.gson.annotations.SerializedName;

static class OverwriteOptions
{
    @SerializedName("conformVisibility")
    boolean conformAccessModifiers;
    @SerializedName("requireAnnotations")
    boolean requireOverwriteAnnotations;
}
