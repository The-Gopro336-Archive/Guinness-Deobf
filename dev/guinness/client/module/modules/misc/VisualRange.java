package dev.guinness.client.module.modules.misc;

import net.minecraft.entity.Entity;
import java.util.Iterator;
import dev.guinness.client.util.MessageUtil;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.entity.player.EntityPlayer;
import java.util.ArrayList;
import dev.guinness.client.module.Category;
import java.util.List;
import dev.guinness.client.module.Module;

public class VisualRange extends Module
{
    public List<String> players;
    public List<String> oldPlayers;
    
    public VisualRange() {
        super("VisualRange", Category.MISC, "Tells you in chat when someone enters your visual range");
    }
    
    @Override
    public void onEnable() {
        this.players = new ArrayList<String>();
        this.oldPlayers = new ArrayList<String>();
    }
    
    @Override
    public void onUpdate() {
        final List<String> newPlayers = new ArrayList<String>();
        final List<EntityPlayer> playerEntities = (List<EntityPlayer>)VisualRange.mc.world.field_73010_i;
        for (final Entity e : playerEntities) {
            if (e.getName().equals(VisualRange.mc.player.func_70005_c_())) {
                continue;
            }
            newPlayers.add(e.getName());
        }
        if (newPlayers.size() > 0) {
            for (final String name : newPlayers) {
                if (!this.players.contains(name)) {
                    MessageUtil.sendClientMessage(ChatFormatting.BLUE + name + ChatFormatting.GRAY + " has just entered visual range.");
                    this.players.add(name);
                }
            }
        }
        if (this.players.size() > 0) {
            for (final String name : this.players) {
                if (!newPlayers.contains(name)) {
                    this.oldPlayers.add(name);
                    MessageUtil.sendClientMessage(ChatFormatting.BLUE + name + ChatFormatting.GRAY + " has just left visual range.");
                }
            }
        }
        if (this.oldPlayers.size() > 0) {
            for (final String name : this.oldPlayers) {
                this.players.remove(name);
            }
            this.oldPlayers.clear();
        }
    }
}
