package dev.guinness.client.event;

import dev.guinness.client.command.Command;
import dev.guinness.client.event.events.PlayerConnectionEvent$JoinEvent;
import dev.guinness.client.event.events.PlayerConnectionEvent$LogoutEvent;
import dev.guinness.client.util.ClientUtil;
import org.lwjgl.input.Keyboard;
import dev.guinness.client.module.Module;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import java.util.Iterator;
import net.minecraft.network.play.server.SPacketPlayerListItem;
import net.minecraftforge.fml.common.eventhandler.Event;
import dev.guinness.client.event.events.TotemPopEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.server.SPacketEntityStatus;
import dev.guinness.client.event.events.PacketEvent$PacketReceiveEvent;
import org.apache.commons.lang3.ArrayUtils;
import dev.guinness.client.command.CommandManager;
import net.minecraft.network.play.client.CPacketChatMessage;
import dev.guinness.client.event.events.PacketEvent$PacketSendEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.function.Consumer;
import java.util.function.Predicate;
import dev.guinness.client.module.ModuleManager;
import dev.guinness.client.util.ColorUtil;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import dev.guinness.client.util.Wrapper;

public class ForgeEventHandler implements Wrapper
{
    public static ForgeEventHandler INSTANCE;
    
    @SubscribeEvent
    public void onTick(final TickEvent.ClientTickEvent clientTickEvent) {
        ColorUtil.updateColors();
        ModuleManager.getModules(ForgeEventHandler::lambda$onTick$0).forEach(ForgeEventHandler::lambda$onTick$1);
    }
    
    @SubscribeEvent
    public void onFastTick(final TickEvent tickEvent) {
        ModuleManager.getModules(ForgeEventHandler::lambda$onFastTick$2).forEach(ForgeEventHandler::lambda$onFastTick$3);
    }
    
    @SubscribeEvent
    public void onRender2d(final RenderGameOverlayEvent.Text text) {
        ModuleManager.getModules(ForgeEventHandler::lambda$onRender2d$4).forEach(ForgeEventHandler::lambda$onRender2d$5);
    }
    
    @SubscribeEvent
    public void onRender3d(final RenderWorldLastEvent renderWorldLastEvent) {
        ModuleManager.getModules(ForgeEventHandler::lambda$onRender3d$6).forEach(ForgeEventHandler::lambda$onRender3d$7);
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent$PacketSendEvent packetEvent$PacketSendEvent) {
        if (packetEvent$PacketSendEvent.getPacket() instanceof CPacketChatMessage) {
            final CPacketChatMessage packet = (CPacketChatMessage)packetEvent$PacketSendEvent.getPacket();
            if (packet.getMessage().startsWith(CommandManager.PREFIX)) {
                packetEvent$PacketSendEvent.setCanceled(true);
                final String commandName = packet.getMessage().split(CommandManager.PREFIX)[1].split(" ")[0];
                if (commandName == null) {
                    return;
                }
                final String[] args = ArrayUtils.remove(packet.getMessage().split(" "), 0);
                if (args == null) {
                    return;
                }
                CommandManager.getCommands().stream().filter(ForgeEventHandler::lambda$onPacketSend$8).forEach(ForgeEventHandler::lambda$onPacketSend$9);
            }
        }
    }
    
    @SubscribeEvent
    public void onTotemPop(final PacketEvent$PacketReceiveEvent packetEvent$PacketReceiveEvent) {
        final SPacketEntityStatus[] packet = { null };
        final Entity[] entity = { null };
        if (packetEvent$PacketReceiveEvent.getPacket() instanceof SPacketEntityStatus) {
            packet[0] = (SPacketEntityStatus)packetEvent$PacketReceiveEvent.getPacket();
            if (packet[0].getOpCode() == 35) {
                entity[0] = packet[0].getEntity((World)ForgeEventHandler.mc.world);
                if (!(entity[0] instanceof EntityPlayer)) {
                    return;
                }
                if (!entity[0].getName().equalsIgnoreCase(ForgeEventHandler.mc.player.func_70005_c_())) {
                    MinecraftForge.EVENT_BUS.post(new TotemPopEvent((EntityPlayer)entity[0]));
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onConnectionEvent(final PacketEvent$PacketReceiveEvent packetEvent$PacketReceiveEvent) {
        if (packetEvent$PacketReceiveEvent.getPacket() instanceof SPacketPlayerListItem) {
            final SPacketPlayerListItem packet = (SPacketPlayerListItem)packetEvent$PacketReceiveEvent.getPacket();
            if (packet.getAction() == SPacketPlayerListItem.Action.ADD_PLAYER) {
                for (final SPacketPlayerListItem.AddPlayerData playerData : packet.getEntries()) {
                    if (playerData.getProfile().getId() != ForgeEventHandler.mc.session.getProfile().getId()) {
                        new Thread(ForgeEventHandler::lambda$onConnectionEvent$10).start();
                    }
                }
            }
            if (packet.getAction() == SPacketPlayerListItem.Action.REMOVE_PLAYER) {
                for (final SPacketPlayerListItem.AddPlayerData playerData : packet.getEntries()) {
                    if (playerData.getProfile().getId() != ForgeEventHandler.mc.session.getProfile().getId()) {
                        new Thread(ForgeEventHandler::lambda$onConnectionEvent$11).start();
                    }
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onKeyInput(final InputEvent.KeyInputEvent keyInputEvent) {
        if (ForgeEventHandler.mc.currentScreen == null) {
            ModuleManager.getModules().forEach(ForgeEventHandler::lambda$onKeyInput$12);
        }
    }
    
    public static void lambda$onKeyInput$12(final Module module) {
        try {
            if (Keyboard.isKeyDown(module.getKeybind().getKeyCode()) && !Keyboard.isKeyDown(0) && !module.isKeyDown()) {
                module.setKeyDown(true);
                module.toggle();
            }
            else if (!Keyboard.isKeyDown(module.getKeybind().getKeyCode())) {
                if (module.isKeyDown()) {
                    module.setKeyDown(false);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public static void lambda$onConnectionEvent$11(final SPacketPlayerListItem.AddPlayerData addPlayerData) {
        final String name = ClientUtil.resolveName(addPlayerData.getProfile().getId().toString());
        if (name != null && ForgeEventHandler.mc.player != null && ForgeEventHandler.mc.player.field_70173_aa >= 1000) {
            MinecraftForge.EVENT_BUS.post(new PlayerConnectionEvent$LogoutEvent(name));
        }
    }
    
    public static void lambda$onConnectionEvent$10(final SPacketPlayerListItem.AddPlayerData addPlayerData) {
        final String name = ClientUtil.resolveName(addPlayerData.getProfile().getId().toString());
        if (name != null) {
            if (ForgeEventHandler.mc.player != null) {
                if (ForgeEventHandler.mc.player.field_70173_aa >= 1000) {
                    MinecraftForge.EVENT_BUS.post(new PlayerConnectionEvent$JoinEvent(name));
                }
            }
        }
    }
    
    public static void lambda$onPacketSend$9(final String[] array, final Command command) {
        Exception ex;
        try {
            command.getAction().accept(array);
            return;
        }
        catch (Exception e) {
            ex = e;
        }
        ex.printStackTrace();
    }
    
    public static boolean lambda$onPacketSend$8(final String anotherString, final Command command) {
        return command.getName().equalsIgnoreCase(anotherString);
    }
    
    public static void lambda$onRender3d$7(final Module module) {
        if (ForgeEventHandler.mc.world != null) {
            if (ForgeEventHandler.mc.player != null) {
                Exception ex;
                try {
                    module.onRender3d();
                    return;
                }
                catch (Exception e) {
                    ex = e;
                }
                ex.printStackTrace();
            }
        }
    }
    
    public static boolean lambda$onRender3d$6(final Module module) {
        return module.isEnabled();
    }
    
    public static void lambda$onRender2d$5(final Module module) {
        if (ForgeEventHandler.mc.world != null) {
            if (ForgeEventHandler.mc.player != null) {
                Exception ex;
                try {
                    module.onRender2d();
                    return;
                }
                catch (Exception e) {
                    ex = e;
                }
                ex.printStackTrace();
            }
        }
    }
    
    public static boolean lambda$onRender2d$4(final Module module) {
        return module.isEnabled();
    }
    
    public static void lambda$onFastTick$3(final Module module) {
        if (ForgeEventHandler.mc.world != null && ForgeEventHandler.mc.player != null) {
            Exception ex;
            try {
                module.onFastUpdate();
                return;
            }
            catch (Exception e) {
                ex = e;
            }
            ex.printStackTrace();
        }
    }
    
    public static boolean lambda$onFastTick$2(final Module module) {
        return module.isEnabled();
    }
    
    public static void lambda$onTick$1(final Module module) {
        if (ForgeEventHandler.mc.world != null && ForgeEventHandler.mc.player != null) {
            Exception ex;
            try {
                module.onUpdate();
                return;
            }
            catch (Exception e) {
                ex = e;
            }
            ex.printStackTrace();
        }
    }
    
    public static boolean lambda$onTick$0(final Module module) {
        return module.isEnabled();
    }
    
    static {
        ForgeEventHandler.INSTANCE = new ForgeEventHandler();
    }
}
