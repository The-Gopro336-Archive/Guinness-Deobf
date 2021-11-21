package dev.guinness.client.module.modules.combat;

import dev.guinness.client.util.FriendUtil;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.Iterator;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;
import dev.guinness.client.event.events.PlayerConnectionEvent$LogoutEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import dev.guinness.client.util.MessageUtil;
import com.mojang.realmsclient.gui.ChatFormatting;
import dev.guinness.client.event.events.TotemPopEvent;
import java.util.ArrayList;
import dev.guinness.client.module.Category;
import dev.guinness.client.setting.SMode;
import dev.guinness.client.setting.SBoolean;
import dev.guinness.client.util.Larper;
import java.util.List;
import dev.guinness.client.module.Module;

public class TotemPopCounter extends Module
{
    public List<Larper> larpers;
    public static SBoolean pubLog;
    public static SMode mode;
    public static SBoolean pubDeath;
    
    public TotemPopCounter() {
        super("TotemPopCounter", Category.COMBAT, "Counts totem pops, deaths, and logouts either client-side or publicly");
        this.larpers = new ArrayList<Larper>();
    }
    
    @Override
    public void onUpdate() {
        Label_0130: {
            if (TotemPopCounter.mc.player.func_110143_aJ() > Float.intBitsToFloat(Float.floatToIntBits(1.1384666E38f) ^ 0x7EAB4C23)) {
                if (TotemPopCounter.mc.getConnection() != null) {
                    break Label_0130;
                }
            }
            this.larpers.clear();
        }
        this.updateLarpers();
        this.checkForLarperDeath();
    }
    
    @SubscribeEvent
    public void onLarperPop(final TotemPopEvent totemPopEvent) {
        final Larper larper = this.getLarperByName(totemPopEvent.getPlayer().getName());
        if (larper == null) {
            return;
        }
        larper.addPop();
        final String s = (String)TotemPopCounter.mode.getValue();
        switch (s) {
            case "Client": {
                MessageUtil.sendClientMessage(ChatFormatting.DARK_AQUA + larper.getName() + ChatFormatting.DARK_RED + " popped " + ChatFormatting.GOLD + larper.getPops() + ChatFormatting.DARK_RED + ((larper.getPops() == 1) ? " totem" : " totems"));
                break;
            }
            case "Public": {
                MessageUtil.sendPublicMessage(larper.getName() + " popped " + larper.getPops() + ((larper.getPops() == 1) ? " totem" : " totems"));
                break;
            }
        }
    }
    
    @SubscribeEvent
    public void onLarperLog(final PlayerConnectionEvent$LogoutEvent playerConnectionEvent$LogoutEvent) {
        final Larper larper = this.getLarperByName(playerConnectionEvent$LogoutEvent.getName());
        if (larper == null) {
            return;
        }
        larper.addLogout();
        if (!larper.hasPops()) {
            return;
        }
        final String s = (String)TotemPopCounter.mode.getValue();
        switch (s) {
            case "Client": {
                if (TotemPopCounter.pubLog.getValue()) {
                    MessageUtil.sendPublicMessage(larper.getName() + " logged out after popping " + larper.getPops() + ((larper.getPops() == 1) ? " totem" : " totems"));
                    break;
                }
                MessageUtil.sendClientMessage(ChatFormatting.DARK_AQUA + larper.getName() + ChatFormatting.DARK_RED + " logged out after popping " + ChatFormatting.GOLD + larper.getPops() + ChatFormatting.DARK_RED + ((larper.getPops() == 1) ? " totem" : " totems"));
                break;
            }
            case "Public": {
                MessageUtil.sendPublicMessage(larper.getName() + " logged out after popping " + larper.getPops() + ((larper.getPops() == 1) ? " totem" : " totems"));
                break;
            }
        }
    }
    
    public boolean doesLarperHaveElytraOn(final EntityPlayer entityPlayer) {
        for (final ItemStack item : entityPlayer.getArmorInventoryList()) {
            if (item.getItem().getUnlocalizedName().equalsIgnoreCase("item.elytra")) {
                return true;
            }
        }
        return false;
    }
    
    public void checkForLarperDeath() {
        TotemPopCounter.mc.world.field_73010_i.stream().filter(TotemPopCounter::lambda$checkForLarperDeath$2).filter(TotemPopCounter::lambda$checkForLarperDeath$3).filter(TotemPopCounter::lambda$checkForLarperDeath$4).filter(this::lambda$checkForLarperDeath$5).forEach(this::lambda$checkForLarperDeath$6);
    }
    
    public void updateLarpers() {
        TotemPopCounter.mc.world.field_73010_i.stream().filter(TotemPopCounter::lambda$updateLarpers$7).filter(TotemPopCounter::lambda$updateLarpers$8).filter(TotemPopCounter::lambda$updateLarpers$9).filter(this::lambda$updateLarpers$10).forEach(this::lambda$updateLarpers$11);
    }
    
    public boolean doesLarperListContain(final EntityPlayer entityPlayer) {
        final Larper larp = this.larpers.stream().filter(TotemPopCounter::lambda$doesLarperListContain$12).findFirst().orElse(null);
        return larp != null;
    }
    
    public Larper getLarperByName(final String s) {
        return this.larpers.stream().filter(TotemPopCounter::lambda$getLarperByName$13).findFirst().orElse(null);
    }
    
    public static boolean lambda$getLarperByName$13(final String anotherString, final Larper larper) {
        return larper.getName().equalsIgnoreCase(anotherString);
    }
    
    public static boolean lambda$doesLarperListContain$12(final EntityPlayer entityPlayer, final Larper larper) {
        return larper.getName().equalsIgnoreCase(entityPlayer.getName());
    }
    
    public void lambda$updateLarpers$11(final EntityPlayer entityPlayer) {
        this.larpers.add(new Larper(entityPlayer.getName()));
    }
    
    public boolean lambda$updateLarpers$10(final EntityPlayer entityPlayer) {
        return !this.doesLarperListContain(entityPlayer);
    }
    
    public static boolean lambda$updateLarpers$9(final EntityPlayer entityPlayer) {
        return entityPlayer.func_110143_aJ() > Float.intBitsToFloat(Float.floatToIntBits(2.5726673E38f) ^ 0x7F418BC5);
    }
    
    public static boolean lambda$updateLarpers$8(final EntityPlayer entityPlayer) {
        return !FriendUtil.isFriend(entityPlayer.getName());
    }
    
    public static boolean lambda$updateLarpers$7(final EntityPlayer entityPlayer) {
        return entityPlayer != TotemPopCounter.mc.player;
    }
    
    public void lambda$checkForLarperDeath$6(final EntityPlayer entityPlayer) {
        final Larper larper = this.getLarperByName(entityPlayer.getName());
        if (larper == null) {
            return;
        }
        if (larper.hasPops()) {
            final String s = (String)TotemPopCounter.mode.getValue();
            switch (s) {
                case "Client": {
                    if (TotemPopCounter.pubDeath.getValue()) {
                        if (larper.hasLogouts()) {
                            MessageUtil.sendPublicMessage(larper.getName() + " died after popping " + larper.getPops() + ((larper.getPops() == 1) ? " totem" : " totems") + " and logging " + larper.getLogoutTimes() + ((larper.getLogoutTimes() == 1) ? " time" : " times"));
                            break;
                        }
                        MessageUtil.sendPublicMessage(larper.getName() + " died after popping " + larper.getPops() + ((larper.getPops() == 1) ? " totem" : " totems"));
                        break;
                    }
                    else {
                        if (larper.hasLogouts()) {
                            MessageUtil.sendClientMessage(ChatFormatting.DARK_AQUA + larper.getName() + ChatFormatting.DARK_RED + " died after popping " + ChatFormatting.GOLD + larper.getPops() + ChatFormatting.DARK_RED + ((larper.getPops() == 1) ? " totem" : " totems") + " and logging " + ChatFormatting.GOLD + larper.getLogoutTimes() + ChatFormatting.DARK_RED + ((larper.getLogoutTimes() == 1) ? " time" : " times"));
                            break;
                        }
                        MessageUtil.sendClientMessage(ChatFormatting.DARK_AQUA + larper.getName() + ChatFormatting.DARK_RED + " died after popping " + ChatFormatting.GOLD + larper.getPops() + ChatFormatting.DARK_RED + ((larper.getPops() == 1) ? " totem" : " totems"));
                        break;
                    }
                    break;
                }
                case "Public": {
                    if (larper.hasLogouts()) {
                        MessageUtil.sendPublicMessage(larper.getName() + " died after popping " + larper.getPops() + ((larper.getPops() == 1) ? " totem" : " totems") + " and logging " + larper.getLogoutTimes() + ((larper.getLogoutTimes() == 1) ? " time" : " times"));
                        break;
                    }
                    MessageUtil.sendPublicMessage(larper.getName() + " died after popping " + larper.getPops() + ((larper.getPops() == 1) ? " totem" : " totems"));
                    break;
                }
            }
        }
        this.larpers.remove(larper);
    }
    
    public boolean lambda$checkForLarperDeath$5(final EntityPlayer entityPlayer) {
        return this.doesLarperListContain(entityPlayer);
    }
    
    public static boolean lambda$checkForLarperDeath$4(final EntityPlayer entityPlayer) {
        return entityPlayer.func_110143_aJ() <= Float.intBitsToFloat(Float.floatToIntBits(8.0977394E37f) ^ 0x7E73AEB7);
    }
    
    public static boolean lambda$checkForLarperDeath$3(final EntityPlayer entityPlayer) {
        return !FriendUtil.isFriend(entityPlayer.getName());
    }
    
    public static boolean lambda$checkForLarperDeath$2(final EntityPlayer entityPlayer) {
        return entityPlayer != TotemPopCounter.mc.player;
    }
    
    public static boolean lambda$static$1() {
        return ((String)TotemPopCounter.mode.getValue()).equalsIgnoreCase("Client");
    }
    
    public static boolean lambda$static$0() {
        return ((String)TotemPopCounter.mode.getValue()).equalsIgnoreCase("Client");
    }
    
    static {
        TotemPopCounter.mode = new SMode("Mode", new String[] { "Client", "Public" });
        TotemPopCounter.pubDeath = new SBoolean(TotemPopCounter::lambda$static$0, "Public Death Msg", false);
        TotemPopCounter.pubLog = new SBoolean(TotemPopCounter::lambda$static$1, "Public Log Msg", false);
    }
}
