package dev.guinness.client.module.modules.misc;

import net.minecraft.potion.PotionEffect;
import java.util.Objects;
import dev.guinness.client.module.Category;
import dev.guinness.client.setting.SMode;
import dev.guinness.client.setting.SBoolean;
import dev.guinness.client.module.Module;

public class Potion extends Module
{
    public static SBoolean nv;
    public static SBoolean naus;
    public static SBoolean haste;
    public static SMode mode;
    public static SBoolean gl;
    public static SBoolean bl;
    public static SBoolean blind;
    public static SBoolean fat;
    public static SBoolean lev;
    
    public Potion() {
        super("Potion", Category.MISC, "Cancels or applies certain potion effects");
    }
    
    @Override
    public void onUpdate() {
        if (((String)Potion.mode.getValue()).equalsIgnoreCase("cancel")) {
            if (Potion.mc.player.func_70644_a(Objects.requireNonNull(net.minecraft.potion.Potion.getPotionFromResourceLocation("levitation")))) {
                if (Potion.lev.getValue()) {
                    Potion.mc.player.removeActivePotionEffect(net.minecraft.potion.Potion.getPotionFromResourceLocation("levitation"));
                }
            }
            if (Potion.mc.player.func_70644_a(Objects.requireNonNull(net.minecraft.potion.Potion.getPotionFromResourceLocation("haste")))) {
                if (Potion.haste.getValue()) {
                    Potion.mc.player.removeActivePotionEffect(net.minecraft.potion.Potion.getPotionFromResourceLocation("haste"));
                }
            }
            if (Potion.mc.player.func_70644_a(Objects.requireNonNull(net.minecraft.potion.Potion.getPotionFromResourceLocation("blindness")))) {
                if (Potion.blind.getValue()) {
                    Potion.mc.player.removeActivePotionEffect(net.minecraft.potion.Potion.getPotionFromResourceLocation("blindness"));
                }
            }
            if (Potion.mc.player.func_70644_a(Objects.requireNonNull(net.minecraft.potion.Potion.getPotionFromResourceLocation("mining_fatigue")))) {
                if (Potion.fat.getValue()) {
                    Potion.mc.player.removeActivePotionEffect(net.minecraft.potion.Potion.getPotionFromResourceLocation("mining_mining_fatigue"));
                }
            }
            if (Potion.mc.player.func_70644_a(Objects.requireNonNull(net.minecraft.potion.Potion.getPotionFromResourceLocation("nausea")))) {
                if (Potion.naus.getValue()) {
                    Potion.mc.player.removeActivePotionEffect(net.minecraft.potion.Potion.getPotionFromResourceLocation("nausea"));
                }
            }
            if (Potion.mc.player.func_70644_a(Objects.requireNonNull(net.minecraft.potion.Potion.getPotionById(16)))) {
                if (Potion.nv.getValue()) {
                    Potion.mc.player.removeActivePotionEffect(net.minecraft.potion.Potion.getPotionById(16));
                }
            }
            if (Potion.mc.player.func_70644_a(Objects.requireNonNull(net.minecraft.potion.Potion.getPotionFromResourceLocation("luck")))) {
                if (Potion.gl.getValue()) {
                    Potion.mc.player.removeActivePotionEffect(net.minecraft.potion.Potion.getPotionFromResourceLocation("luck"));
                }
            }
            if (Potion.mc.player.func_70644_a(Objects.requireNonNull(net.minecraft.potion.Potion.getPotionFromResourceLocation("badluck")))) {
                if (Potion.bl.getValue()) {
                    Potion.mc.player.removeActivePotionEffect(net.minecraft.potion.Potion.getPotionFromResourceLocation("unluck"));
                }
            }
        }
        if (((String)Potion.mode.getValue()).equalsIgnoreCase("apply")) {
            if (Potion.lev.getValue()) {
                Potion.mc.player.func_70690_d(new PotionEffect(net.minecraft.potion.Potion.getPotionFromResourceLocation("levitation"), 200000, 4));
            }
            if (!(boolean)Potion.lev.getValue()) {
                if (Potion.mc.player.func_70644_a(Objects.requireNonNull(net.minecraft.potion.Potion.getPotionFromResourceLocation("levitation")))) {
                    Potion.mc.player.removeActivePotionEffect(net.minecraft.potion.Potion.getPotionFromResourceLocation("levitation"));
                }
            }
            if (Potion.haste.getValue()) {
                Potion.mc.player.func_70690_d(new PotionEffect(net.minecraft.potion.Potion.getPotionFromResourceLocation("haste"), 200000, 1));
            }
            if (!(boolean)Potion.haste.getValue()) {
                if (Potion.mc.player.func_70644_a(Objects.requireNonNull(net.minecraft.potion.Potion.getPotionFromResourceLocation("haste")))) {
                    Potion.mc.player.removeActivePotionEffect(net.minecraft.potion.Potion.getPotionFromResourceLocation("haste"));
                }
            }
            if (Potion.blind.getValue()) {
                Potion.mc.player.func_70690_d(new PotionEffect(net.minecraft.potion.Potion.getPotionFromResourceLocation("blindness"), 200000, 2));
            }
            if (!(boolean)Potion.blind.getValue()) {
                if (Potion.mc.player.func_70644_a(Objects.requireNonNull(net.minecraft.potion.Potion.getPotionFromResourceLocation("blindness")))) {
                    Potion.mc.player.removeActivePotionEffect(net.minecraft.potion.Potion.getPotionFromResourceLocation("blindness"));
                }
            }
            if (Potion.fat.getValue()) {
                Potion.mc.player.func_70690_d(new PotionEffect(net.minecraft.potion.Potion.getPotionFromResourceLocation("mining_fatigue"), 200000, 1));
            }
            if (!(boolean)Potion.fat.getValue() && Potion.mc.player.func_70644_a(Objects.requireNonNull(net.minecraft.potion.Potion.getPotionFromResourceLocation("mining_fatigue")))) {
                Potion.mc.player.removeActivePotionEffect(net.minecraft.potion.Potion.getPotionFromResourceLocation("mining_fatigue"));
            }
            if (Potion.naus.getValue()) {
                Potion.mc.player.func_70690_d(new PotionEffect(net.minecraft.potion.Potion.getPotionFromResourceLocation("nausea"), 200000, 1));
            }
            if (!(boolean)Potion.naus.getValue() && Potion.mc.player.func_70644_a(Objects.requireNonNull(net.minecraft.potion.Potion.getPotionFromResourceLocation("nausea")))) {
                Potion.mc.player.removeActivePotionEffect(net.minecraft.potion.Potion.getPotionFromResourceLocation("nausea"));
            }
            if (Potion.nv.getValue()) {
                Potion.mc.player.func_70690_d(new PotionEffect(net.minecraft.potion.Potion.getPotionById(16), 200000, 0));
            }
            if (!(boolean)Potion.nv.getValue()) {
                if (Potion.mc.player.func_70644_a(Objects.requireNonNull(net.minecraft.potion.Potion.getPotionFromResourceLocation("night_vision")))) {
                    Potion.mc.player.removeActivePotionEffect(net.minecraft.potion.Potion.getPotionById(16));
                }
            }
            if (Potion.gl.getValue()) {
                Potion.mc.player.func_70690_d(new PotionEffect(net.minecraft.potion.Potion.getPotionFromResourceLocation("luck"), 200000, 2));
            }
            if (!(boolean)Potion.gl.getValue() && Potion.mc.player.func_70644_a(Objects.requireNonNull(net.minecraft.potion.Potion.getPotionFromResourceLocation("luck")))) {
                Potion.mc.player.removeActivePotionEffect(net.minecraft.potion.Potion.getPotionFromResourceLocation("luck"));
            }
            if (Potion.bl.getValue()) {
                Potion.mc.player.func_70690_d(new PotionEffect(net.minecraft.potion.Potion.getPotionFromResourceLocation("unluck"), 200000, 2));
            }
            if (!(boolean)Potion.bl.getValue()) {
                if (Potion.mc.player.func_70644_a(Objects.requireNonNull(net.minecraft.potion.Potion.getPotionFromResourceLocation("unluck")))) {
                    Potion.mc.player.removeActivePotionEffect(net.minecraft.potion.Potion.getPotionFromResourceLocation("unluck"));
                }
            }
        }
    }
    
    @Override
    public void onDisable() {
        if (((String)Potion.mode.getValue()).equalsIgnoreCase("apply")) {
            if (Potion.lev.getValue()) {
                Potion.mc.player.removeActivePotionEffect(net.minecraft.potion.Potion.getPotionFromResourceLocation("levitation"));
            }
            if (Potion.haste.getValue()) {
                Potion.mc.player.removeActivePotionEffect(net.minecraft.potion.Potion.getPotionFromResourceLocation("haste"));
            }
            if (Potion.blind.getValue()) {
                Potion.mc.player.removeActivePotionEffect(net.minecraft.potion.Potion.getPotionFromResourceLocation("blindness"));
            }
            if (Potion.fat.getValue()) {
                Potion.mc.player.removeActivePotionEffect(net.minecraft.potion.Potion.getPotionFromResourceLocation("mining_fatigue"));
            }
            if (Potion.naus.getValue()) {
                Potion.mc.player.removeActivePotionEffect(net.minecraft.potion.Potion.getPotionFromResourceLocation("nausea"));
            }
            if (Potion.nv.getValue()) {
                Potion.mc.player.removeActivePotionEffect(net.minecraft.potion.Potion.getPotionById(16));
            }
            if (Potion.gl.getValue()) {
                Potion.mc.player.removeActivePotionEffect(net.minecraft.potion.Potion.getPotionFromResourceLocation("luck"));
            }
            if (Potion.bl.getValue()) {
                Potion.mc.player.removeActivePotionEffect(net.minecraft.potion.Potion.getPotionFromResourceLocation("unluck"));
            }
        }
    }
    
    static {
        Potion.mode = new SMode("Mode", new String[] { "Apply", "Cancel" });
        Potion.lev = new SBoolean("Levitation", false);
        Potion.haste = new SBoolean("Haste", false);
        Potion.fat = new SBoolean("Fatigue", false);
        Potion.blind = new SBoolean("Blindness", false);
        Potion.naus = new SBoolean("Nausea", false);
        Potion.nv = new SBoolean("Night Vision", false);
        Potion.gl = new SBoolean("Luck", false);
        Potion.bl = new SBoolean("Bad Luck", false);
    }
}
