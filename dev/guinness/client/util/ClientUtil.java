package dev.guinness.client.util;

import org.json.simple.parser.ParseException;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.JSONArray;
import org.apache.commons.io.IOUtils;
import java.net.URL;
import net.minecraft.client.gui.ScaledResolution;
import java.util.function.Predicate;
import dev.guinness.client.command.CommandManager;
import dev.guinness.client.command.Command;
import dev.guinness.client.mixin.mixins.ISession;

public class ClientUtil implements Wrapper
{
    public static void setUsername(final String username) {
        ((ISession)ClientUtil.mc.getSession()).setUsername(username);
    }
    
    public static String handleChatPrediction(final String s) {
        final Command possibleCommand = (Command)CommandManager.getCommands().stream().filter(ClientUtil::lambda$handleChatPrediction$0).findFirst().orElse(null);
        if (possibleCommand == null) {
            return null;
        }
        final String commandName = possibleCommand.getName().toLowerCase();
        final ScaledResolution sr = new ScaledResolution(ClientUtil.mc);
        ClientUtil.mc.fontRenderer.drawString(CommandManager.PREFIX + commandName, 4, sr.getScaledHeight() - 12, -570425345);
        return commandName;
    }
    
    public static String resolveName(String replace) {
        replace = replace.replace("-", "");
        final String url = "https://api.mojang.com/user/profiles/" + replace + "/names";
        Object o;
        try {
            final String nameJson = IOUtils.toString(new URL(url));
            if (nameJson != null && nameJson.length() > 0) {
                final JSONArray jsonArray = (JSONArray)JSONValue.parseWithException(nameJson);
                if (jsonArray != null) {
                    final JSONObject latestName = jsonArray.get(jsonArray.size() - 1);
                    if (latestName != null) {
                        return latestName.get("name").toString();
                    }
                }
            }
            return null;
        }
        catch (IOException | ParseException ex) {
            final Throwable t;
            final Exception e = (Exception)(o = t);
        }
        ((Throwable)o).printStackTrace();
        return null;
    }
    
    public static boolean lambda$handleChatPrediction$0(final String s, final Command command) {
        return command.getName().toLowerCase().startsWith(s.replaceAll(CommandManager.PREFIX, "").toLowerCase());
    }
}
