package dev.guinness.client.module.modules.client;

import dev.guinness.client.event.events.ModuleToggleEvent$Disable;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import dev.guinness.client.util.MessageUtil;
import net.minecraft.util.text.TextFormatting;
import dev.guinness.client.event.events.ModuleToggleEvent$Enable;
import dev.guinness.client.module.Category;
import dev.guinness.client.module.Module;

public class Notify extends Module
{
    public Notify() {
        super("Notify", Category.CLIENT, "Notifies in chat when modules are enabled");
    }
    
    @SubscribeEvent
    public void onToggle(final ModuleToggleEvent$Enable moduleToggleEvent$Enable) {
        MessageUtil.sendClientMessage(moduleToggleEvent$Enable.getModule().getName() + " " + TextFormatting.GREEN + "enabled");
    }
    
    @SubscribeEvent
    public void onToggle(final ModuleToggleEvent$Disable moduleToggleEvent$Disable) {
        MessageUtil.sendClientMessage(moduleToggleEvent$Disable.getModule().getName() + " " + TextFormatting.RED + "disabled");
    }
}
