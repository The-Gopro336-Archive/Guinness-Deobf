package dev.guinness.client.module.modules.misc;

import net.minecraft.client.entity.EntityPlayerSP;
import java.nio.file.Files;
import dev.guinness.client.util.MessageUtil;
import java.io.File;
import dev.guinness.client.util.FileUtil;
import java.util.ArrayList;
import dev.guinness.client.module.Category;
import java.util.List;
import dev.guinness.client.setting.SSlider;
import dev.guinness.client.module.Module;

public class Spammer extends Module
{
    public static SSlider delay;
    public List<String> spammerLines;
    public int spammerIncrement;
    
    public Spammer() {
        super("Spammer", Category.MISC, "Spams chat with lines from a given file");
        this.spammerIncrement = 0;
        this.spammerLines = new ArrayList<String>();
    }
    
    @Override
    public void onEnable() {
        this.spammerLines.clear();
        this.spammerIncrement = 0;
        Exception ex;
        try {
            final File spammer = new File(FileUtil.guinness.getAbsolutePath(), "Spammer.txt");
            if (!spammer.exists()) {
                spammer.createNewFile();
                MessageUtil.sendClientMessage("Spammer file is empty! Go to your Guinness folder and fill it.");
                this.disable();
                return;
            }
            this.spammerLines = Files.readAllLines(spammer.toPath());
            if (this.spammerLines.size() == 0) {
                MessageUtil.sendClientMessage("Spammer file is empty! Go to your Guinness folder and fill it.");
                this.disable();
                return;
            }
            if (this.spammerLines.get(0).equalsIgnoreCase(" ")) {
                MessageUtil.sendClientMessage("Spammer file is empty! Go to your Guinness folder and fill it.");
                this.disable();
            }
            return;
        }
        catch (Exception e) {
            ex = e;
        }
        ex.printStackTrace();
    }
    
    @Override
    public void onUpdate() {
        if (this.spammerIncrement > this.spammerLines.size() - 1) {
            this.spammerIncrement = 0;
        }
        if (Spammer.mc.player.field_70173_aa % ((double)Spammer.delay.getValue() * Double.longBitsToDouble(Double.doubleToLongBits(0.33961321814255413) ^ 0x7FE1BC39144D8A97L)) == Double.longBitsToDouble(Double.doubleToLongBits(2.115976420107457E307) ^ 0x7FBE21EBCF7CBAC7L)) {
            final EntityPlayerSP player = Spammer.mc.player;
            ++player.field_70173_aa;
            MessageUtil.sendPublicMessage(this.spammerLines.get(this.spammerIncrement));
            ++this.spammerIncrement;
        }
    }
    
    static {
        Spammer.delay = new SSlider("Delay", Double.longBitsToDouble(Double.doubleToLongBits(33.92913388142488) ^ 0x7FB0F6EDDBE929A7L), Double.longBitsToDouble(Double.doubleToLongBits(0.20819549923990036) ^ 0x7FD2A6266E34732DL), Double.longBitsToDouble(Double.doubleToLongBits(0.2034881386501399) ^ 0x7FEE0BE63A50296FL), 0);
    }
}
