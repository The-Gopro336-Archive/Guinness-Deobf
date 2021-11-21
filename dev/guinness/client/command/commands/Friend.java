package dev.guinness.client.command.commands;

import dev.guinness.client.command.CommandManager;
import dev.guinness.client.util.FileUtil;
import dev.guinness.client.util.MessageUtil;
import com.mojang.realmsclient.gui.ChatFormatting;
import dev.guinness.client.util.FriendUtil;
import java.util.function.Consumer;
import dev.guinness.client.command.Command;

public class Friend extends Command
{
    public Friend() {
        super("Friend", Friend::lambda$new$0);
    }
    
    public static void lambda$new$0(final String[] array) {
        if (array.length != 0) {
            if (array.length <= 2) {
                if (array.length == 1) {
                    if (FriendUtil.isFriend(array[0])) {
                        MessageUtil.sendClientMessage(ChatFormatting.BLUE + array[0] + ChatFormatting.GRAY + " is a friend.");
                    }
                    else {
                        MessageUtil.sendClientMessage(ChatFormatting.BLUE + array[0] + ChatFormatting.GRAY + " is not a friend.");
                    }
                }
                if (array.length == 2 && array[0].equalsIgnoreCase("add")) {
                    if (!FriendUtil.isFriend(array[1])) {
                        FriendUtil.addFriend(array[1]);
                        FileUtil.saveFriends();
                        MessageUtil.sendClientMessage("Successfully added " + ChatFormatting.BLUE + array[1] + ChatFormatting.GRAY + " as a friend.");
                    }
                    else if (FriendUtil.isFriend(array[1])) {
                        MessageUtil.sendClientMessage(ChatFormatting.BLUE + array[1] + ChatFormatting.GRAY + " is already a friend.");
                    }
                }
                if (array.length == 2) {
                    if (array[0].equalsIgnoreCase("del")) {
                        if (FriendUtil.isFriend(array[1])) {
                            FriendUtil.delFriend(array[1]);
                            FileUtil.saveFriends();
                            MessageUtil.sendClientMessage("Successfully removed " + ChatFormatting.BLUE + array[1] + ChatFormatting.GRAY + " from the friends list.");
                        }
                        else if (!FriendUtil.isFriend(array[1])) {
                            MessageUtil.sendClientMessage(ChatFormatting.BLUE + array[1] + ChatFormatting.GRAY + " is not a friend.");
                        }
                    }
                }
                return;
            }
        }
        MessageUtil.sendClientMessage("Usage: " + CommandManager.PREFIX + "friend add [name] or " + CommandManager.PREFIX + "friend del [name]");
    }
}
