package dev.guinness.client.module.modules.misc;

import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Predicate;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.network.play.client.CPacketUseEntity;
import dev.guinness.client.event.events.PacketEvent$PacketSendEvent;
import dev.guinness.client.util.MessageUtil;
import java.nio.file.Files;
import java.io.File;
import dev.guinness.client.util.FileUtil;
import java.util.ArrayList;
import dev.guinness.client.module.Category;
import net.minecraft.entity.player.EntityPlayer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;
import dev.guinness.client.module.Module;

public class AutoEZ extends Module
{
    public List<String> ezMessages;
    public static ConcurrentHashMap<String, EntityPlayer> targets;
    public int timeout;
    
    public AutoEZ() {
        super("AutoEZ", Category.MISC, "Insults players in chat after killing them");
        this.ezMessages = new ArrayList<String>();
    }
    
    public boolean loadEZMessages() {
        while (true) {
            try {
                final File config = new File(FileUtil.guinness.getAbsolutePath(), "AutoEZ.txt");
                if (!config.exists()) {
                    config.createNewFile();
                }
                this.ezMessages = Files.readAllLines(config.toPath());
                return true;
            }
            catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }
    
    @Override
    public void onEnable() {
        if (this.loadEZMessages()) {
            MessageUtil.sendClientMessage("Successfully loaded EZ messages.");
        }
        else {
            MessageUtil.sendClientMessage("Failed to load EZ messages. Ensure that you have an AutoEZ.txt in your Guinness folder.");
        }
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent$PacketSendEvent packetEvent$PacketSendEvent) {
        if (packetEvent$PacketSendEvent.getPacket() instanceof CPacketUseEntity) {
            final CPacketUseEntity packet = (CPacketUseEntity)packetEvent$PacketSendEvent.getPacket();
            if (packet.getAction().equals(CPacketUseEntity.Action.ATTACK)) {
                final Entity target = packet.getEntityFromWorld((World)AutoEZ.mc.world);
                if (target instanceof EntityPlayer) {
                    AutoEZ.targets.put(target.getName(), (EntityPlayer)target);
                }
            }
        }
    }
    
    @Override
    public void onUpdate() {
        if (this.Null()) {
            return;
        }
        ++this.timeout;
        AutoEZ.mc.world.field_73010_i.stream().filter(AutoEZ::lambda$onUpdate$0).filter(AutoEZ::lambda$onUpdate$1).forEach(this::lambda$onUpdate$2);
        if (this.timeout > 20) {
            AutoEZ.targets.clear();
            this.timeout = 0;
        }
    }
    
    public void ez(final EntityPlayer entityPlayer) {
        final String name = entityPlayer.getName();
        if (this.ezMessages.size() == 0) {
            MessageUtil.sendModuleWarnMessage(this, "The AutoEZ.txt file is empty. No messages will be sent.");
            return;
        }
        final int rand = new Random().nextInt(this.ezMessages.size());
        AutoEZ.targets.remove(name);
        final String ezMessage = this.ezMessages.get(rand).replace("$name$", name);
        MessageUtil.sendPublicMessage(ezMessage);
    }
    
    public void lambda$onUpdate$2(final EntityPlayer entityPlayer) {
        this.ez(entityPlayer);
    }
    
    public static boolean lambda$onUpdate$1(final EntityPlayer entityPlayer) {
        return AutoEZ.targets.containsKey(entityPlayer.getName());
    }
    
    public static boolean lambda$onUpdate$0(final EntityPlayer entityPlayer) {
        return entityPlayer.func_110143_aJ() <= Float.intBitsToFloat(Float.floatToIntBits(1.0934065E38f) ^ 0x7EA4847D);
    }
    
    static {
        AutoEZ.targets = new ConcurrentHashMap<String, EntityPlayer>();
    }
}
