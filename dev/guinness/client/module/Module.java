package dev.guinness.client.module;

import dev.guinness.client.event.events.ModuleToggleEvent$Disable;
import net.minecraftforge.fml.common.eventhandler.Event;
import dev.guinness.client.event.events.ModuleToggleEvent$Enable;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import java.util.ArrayList;
import dev.guinness.client.setting.Setting;
import java.util.List;
import net.minecraft.client.settings.KeyBinding;
import dev.guinness.client.util.Wrapper;

public abstract class Module implements Wrapper
{
    public boolean isKeyDown;
    public String description;
    public boolean enabled;
    public String name;
    public boolean opened;
    public boolean isBinding;
    public KeyBinding key;
    public List<Setting<?>> settingsList;
    public Category category;
    
    public Module(final String name, final Category category, final String description) {
        this.opened = false;
        this.enabled = false;
        this.isKeyDown = false;
        this.isBinding = false;
        this.settingsList = new ArrayList<Setting<?>>();
        this.name = name;
        this.category = category;
        this.description = description;
        ClientRegistry.registerKeyBinding(this.key = new KeyBinding(name, 0, "Guinness"));
    }
    
    public void toggle() {
        if (this.isEnabled()) {
            this.disable();
            return;
        }
        if (!this.isEnabled()) {
            this.enable();
        }
    }
    
    public void enable() {
        if (this.isEnabled()) {
            return;
        }
        MinecraftForge.EVENT_BUS.register(this);
        if (!this.Null()) {
            final ModuleToggleEvent$Enable event = new ModuleToggleEvent$Enable(this);
            MinecraftForge.EVENT_BUS.post(event);
        }
        this.enabled = true;
        if (this.Null()) {
            return;
        }
        Exception ex;
        try {
            this.onEnable();
            return;
        }
        catch (Exception e) {
            ex = e;
        }
        ex.printStackTrace();
    }
    
    public void disable() {
        if (!this.isEnabled()) {
            return;
        }
        if (!this.Null()) {
            final ModuleToggleEvent$Disable event = new ModuleToggleEvent$Disable(this);
            MinecraftForge.EVENT_BUS.post(event);
        }
        MinecraftForge.EVENT_BUS.unregister(this);
        this.enabled = false;
        if (this.Null()) {
            return;
        }
        Exception ex;
        try {
            this.onDisable();
            return;
        }
        catch (Exception e) {
            ex = e;
        }
        ex.printStackTrace();
    }
    
    public void onEnable() {
    }
    
    public void onUpdate() {
    }
    
    public void onFastUpdate() {
    }
    
    public void onDisable() {
    }
    
    public void onRender2d() {
    }
    
    public void onRender3d() {
    }
    
    public String getName() {
        return this.name;
    }
    
    public boolean isEnabled() {
        return this.enabled;
    }
    
    public Category getCategory() {
        return this.category;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public KeyBinding getKeybind() {
        return this.key;
    }
    
    public List getSettings() {
        return this.settingsList;
    }
    
    public boolean isOpened() {
        return this.opened;
    }
    
    public boolean isBinding() {
        return this.isBinding;
    }
    
    public boolean isKeyDown() {
        return this.isKeyDown;
    }
    
    public boolean hasSettings() {
        return this.settingsList.size() > 0;
    }
    
    public Setting getSettingByName(final String s) {
        return this.settingsList.stream().filter(Module::lambda$getSettingByName$0).findFirst().orElse(null);
    }
    
    public void addSetting(final Setting setting) {
        this.settingsList.add((Setting<?>)setting);
    }
    
    public void setKeybind(final KeyBinding key) {
        this.key = key;
    }
    
    public void setOpen(final boolean opened) {
        this.opened = opened;
    }
    
    public void setBinding(final boolean isBinding) {
        this.isBinding = isBinding;
    }
    
    public void setKeyDown(final boolean isKeyDown) {
        this.isKeyDown = isKeyDown;
    }
    
    public boolean Null() {
        return Module.mc.player == null || Module.mc.world == null;
    }
    
    public static boolean lambda$getSettingByName$0(final String anotherString, final Setting setting) {
        return setting.getName().equalsIgnoreCase(anotherString);
    }
}
