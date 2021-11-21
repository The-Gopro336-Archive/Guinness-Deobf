package org.spongepowered.asm.mixin.transformer;

import java.util.List;
import com.google.gson.annotations.SerializedName;

static class InjectorOptions
{
    @SerializedName("defaultRequire")
    int defaultRequireValue;
    @SerializedName("defaultGroup")
    String defaultGroup;
    @SerializedName("injectionPoints")
    List<String> injectionPoints;
    @SerializedName("maxShiftBy")
    int maxShiftBy;
    
    InjectorOptions() {
        this.defaultRequireValue = 0;
        this.defaultGroup = "default";
        this.maxShiftBy = 0;
    }
}
