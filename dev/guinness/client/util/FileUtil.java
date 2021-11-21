package dev.guinness.client.util;

import dev.guinness.client.command.CommandManager;
import dev.guinness.client.Guinness;
import dev.guinness.client.setting.SMode;
import dev.guinness.client.setting.SSlider;
import dev.guinness.client.setting.SBoolean;
import dev.guinness.client.setting.Setting;
import dev.guinness.client.module.Module;
import dev.guinness.client.module.ModuleManager;
import dev.guinness.client.gui.hud.HudTextFrame;
import java.util.List;
import java.nio.file.Files;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;
import dev.guinness.client.gui.module.ModuleFrame;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;

public class FileUtil
{
    public static File config;
    public static File guinness;
    
    public static void loadAll() {
        loadGui();
        loadHud();
        loadFriends();
        loadCommandPrefix();
        loadBinds(FileUtil.guinness);
        loadSettings(FileUtil.guinness);
        loadActiveModules(FileUtil.guinness);
    }
    
    public static void saveAll() {
        saveGui();
        saveHud();
        saveFriends();
        saveCommandPrefix();
        saveBinds(FileUtil.guinness);
        saveSettings(FileUtil.guinness);
        saveActiveModules(FileUtil.guinness);
    }
    
    public static void createDirectory() {
        FileUtil.guinness = new File("Guinness");
        if (!FileUtil.guinness.exists()) {
            FileUtil.guinness.mkdirs();
        }
        FileUtil.config = new File("Guinness/Configs");
        if (!FileUtil.config.exists()) {
            FileUtil.config.mkdir();
        }
    }
    
    public static void saveGui() {
        Exception ex;
        try {
            final File guiPos = new File(FileUtil.guinness.getAbsolutePath(), "ClickGui.txt");
            final BufferedWriter bw = new BufferedWriter(new FileWriter(guiPos));
            for (final ModuleFrame f : ModuleFrame.moduleFrames) {
                bw.write(f.getTitle() + ":x:" + f.getX() + ":y:" + f.getY());
                bw.write("\r\n");
            }
            bw.close();
            return;
        }
        catch (Exception e) {
            ex = e;
        }
        ex.printStackTrace();
    }
    
    public static void loadGui() {
        Exception ex;
        try {
            final File guiPos = new File(FileUtil.guinness.getAbsolutePath(), "ClickGui.txt");
            if (!guiPos.exists()) {
                guiPos.createNewFile();
                return;
            }
            final BufferedReader br = new BufferedReader(new FileReader(guiPos));
            final List<String> linezz = Files.readAllLines(guiPos.toPath());
            for (final String line : linezz) {
                final String[] regex = line.split(":");
                for (final ModuleFrame f : ModuleFrame.moduleFrames) {
                    if (f.getTitle().equalsIgnoreCase(regex[0])) {
                        f.setX(Integer.parseInt(regex[2]));
                        f.setY(Integer.parseInt(regex[4]));
                    }
                }
            }
            br.close();
            return;
        }
        catch (Exception e) {
            ex = e;
        }
        ex.printStackTrace();
    }
    
    public static void saveHud() {
        Exception ex;
        try {
            final File hudPos = new File(FileUtil.guinness.getAbsolutePath(), "Hud.txt");
            final BufferedWriter bw = new BufferedWriter(new FileWriter(hudPos));
            for (final HudTextFrame f : HudTextFrame.hudFrames) {
                bw.write(f.getTitle() + ":x:" + f.getX() + ":y:" + f.getY());
                bw.write("\r\n");
            }
            bw.close();
            return;
        }
        catch (Exception e) {
            ex = e;
        }
        ex.printStackTrace();
    }
    
    public static void loadHud() {
        Exception ex;
        try {
            final File hudPos = new File(FileUtil.guinness.getAbsolutePath(), "Hud.txt");
            if (!hudPos.exists()) {
                hudPos.createNewFile();
                return;
            }
            final BufferedReader br = new BufferedReader(new FileReader(hudPos));
            final List<String> linezz = Files.readAllLines(hudPos.toPath());
            for (final String line : linezz) {
                final String[] regex = line.split(":");
                for (final HudTextFrame f : HudTextFrame.hudFrames) {
                    if (f.getTitle().equalsIgnoreCase(regex[0])) {
                        f.setX(Integer.parseInt(regex[2]));
                        f.setY(Integer.parseInt(regex[4]));
                    }
                }
            }
            br.close();
            return;
        }
        catch (Exception e) {
            ex = e;
        }
        ex.printStackTrace();
    }
    
    public static void saveSettings(final File file) {
        Exception ex;
        try {
            final File settings = new File(file.getAbsolutePath(), "Settings.txt");
            final BufferedWriter bw = new BufferedWriter(new FileWriter(settings));
            for (final Module m : ModuleManager.getModules()) {
                bw.write(m.getName() + ":");
                for (final Setting<?> s : m.getSettings()) {
                    bw.write(s.getName() + "-" + s.getValue() + ":");
                }
                bw.write("\r\n");
            }
            bw.close();
            return;
        }
        catch (Exception e) {
            ex = e;
        }
        ex.printStackTrace();
    }
    
    public static void loadSettings(final File file) {
        Exception ex;
        try {
            final File settings = new File(file.getAbsolutePath(), "Settings.txt");
            if (!settings.exists()) {
                settings.createNewFile();
                return;
            }
            final BufferedReader br = new BufferedReader(new FileReader(settings));
            final List<String> linezz = Files.readAllLines(settings.toPath());
            for (final String line : linezz) {
                final String[] regex = line.split(":");
                final Module m = ModuleManager.getModule(FileUtil::lambda$loadSettings$0);
                for (int i = 1; i < regex.length; ++i) {
                    final String term = regex[i];
                    final String[] pair = term.split("-");
                    final Setting<?> s = (Setting<?>)m.getSettingByName(pair[0]);
                    if (s instanceof SBoolean) {
                        final SBoolean sb = (SBoolean)s;
                        sb.setValue(Boolean.parseBoolean(pair[1]));
                    }
                    if (s instanceof SSlider) {
                        final SSlider sd = (SSlider)s;
                        sd.setValue(Double.parseDouble(pair[1]));
                    }
                    if (s instanceof SMode) {
                        final SMode sm = (SMode)s;
                        sm.setValue(pair[1]);
                        final int index = MathUtil.getIndex(sm.getAllModes().toArray(new String[0]), pair[1]);
                        if (index == -1) {
                            Guinness.LOGGER.error("No mode found!");
                            sm.setValue(sm.getAllModes().get(0));
                            sm.modeIndex = 0;
                        }
                        else {
                            sm.modeIndex = index;
                        }
                    }
                }
            }
            br.close();
            return;
        }
        catch (Exception e) {
            ex = e;
        }
        ex.printStackTrace();
    }
    
    public static void saveActiveModules(final File file) {
        Exception ex;
        try {
            final File modules = new File(file.getAbsolutePath(), "ActiveModules.txt");
            final BufferedWriter bw = new BufferedWriter(new FileWriter(modules));
            for (final Module m : ModuleManager.getModules()) {
                bw.write(m.getName() + ":");
                if (m.isEnabled()) {
                    bw.write("true");
                }
                else {
                    bw.write("false");
                }
                bw.write("\r\n");
            }
            bw.close();
            return;
        }
        catch (Exception e) {
            ex = e;
        }
        ex.printStackTrace();
    }
    
    public static void loadActiveModules(final File file) {
        Exception ex;
        try {
            final File modules = new File(file.getAbsolutePath(), "ActiveModules.txt");
            if (!modules.exists()) {
                modules.createNewFile();
                return;
            }
            final BufferedReader br = new BufferedReader(new FileReader(modules));
            final List<String> linezz = Files.readAllLines(modules.toPath());
            for (final String line : linezz) {
                final String[] regex = line.split(":");
                final Module m = ModuleManager.getModule(FileUtil::lambda$loadActiveModules$1);
                if (Boolean.parseBoolean(regex[1])) {
                    m.enable();
                }
            }
            br.close();
            return;
        }
        catch (Exception e) {
            ex = e;
        }
        ex.printStackTrace();
    }
    
    public static void saveFriends() {
        Exception ex;
        try {
            final File friends = new File(FileUtil.guinness.getAbsolutePath(), "Friends.txt");
            final BufferedWriter bw = new BufferedWriter(new FileWriter(friends));
            for (final String s : FriendUtil.getFriends()) {
                bw.write(s);
                bw.write("\r\n");
            }
            bw.close();
            return;
        }
        catch (Exception e) {
            ex = e;
        }
        ex.printStackTrace();
    }
    
    public static void loadFriends() {
        Exception ex;
        try {
            final File friends = new File(FileUtil.guinness.getAbsolutePath(), "Friends.txt");
            if (!friends.exists()) {
                friends.createNewFile();
                return;
            }
            final BufferedReader br = new BufferedReader(new FileReader(friends));
            final List<String> linezz = Files.readAllLines(friends.toPath());
            for (final String line : linezz) {
                FriendUtil.addFriend(line);
            }
            br.close();
            return;
        }
        catch (Exception e) {
            ex = e;
        }
        ex.printStackTrace();
    }
    
    public static void saveBinds(final File file) {
        Exception ex;
        try {
            final File binds = new File(file.getAbsolutePath(), "Keybinds.txt");
            final BufferedWriter bw = new BufferedWriter(new FileWriter(binds));
            for (final Module m : ModuleManager.getModules()) {
                bw.write(m.getName() + ":" + m.getKeybind().getKeyCode());
                bw.write("\r\n");
            }
            bw.close();
            return;
        }
        catch (Exception e) {
            ex = e;
        }
        ex.printStackTrace();
    }
    
    public static void loadBinds(final File file) {
        Exception ex;
        try {
            final File binds = new File(file.getAbsolutePath(), "Keybinds.txt");
            if (!binds.exists()) {
                binds.createNewFile();
                return;
            }
            final BufferedReader br = new BufferedReader(new FileReader(binds));
            final List<String> linezz = Files.readAllLines(binds.toPath());
            for (final String line : linezz) {
                final String[] regex = line.split(":");
                final Module m = ModuleManager.getModule(FileUtil::lambda$loadBinds$2);
                m.getKeybind().setKeyCode(Integer.parseInt(regex[1]));
            }
            br.close();
            return;
        }
        catch (Exception e) {
            ex = e;
        }
        ex.printStackTrace();
    }
    
    public static void saveCommandPrefix() {
        Exception ex;
        try {
            final File prefix = new File(FileUtil.guinness.getAbsolutePath(), "Prefix.txt");
            final BufferedWriter bw = new BufferedWriter(new FileWriter(prefix));
            bw.write(CommandManager.PREFIX);
            bw.close();
            return;
        }
        catch (Exception e) {
            ex = e;
        }
        ex.printStackTrace();
    }
    
    public static void loadCommandPrefix() {
        Exception ex;
        try {
            final File prefix = new File(FileUtil.guinness.getAbsolutePath(), "Prefix.txt");
            if (!prefix.exists()) {
                prefix.createNewFile();
                return;
            }
            final BufferedReader br = new BufferedReader(new FileReader(prefix));
            CommandManager.PREFIX = br.readLine();
            br.close();
            return;
        }
        catch (Exception e) {
            ex = e;
        }
        ex.printStackTrace();
    }
    
    public static void saveConfig(final String child) {
        Exception ex;
        try {
            final File config = new File(FileUtil.config.getAbsolutePath(), child);
            if (!config.exists()) {
                config.mkdir();
            }
            saveBinds(config);
            saveSettings(config);
            saveActiveModules(config);
            return;
        }
        catch (Exception e) {
            ex = e;
        }
        ex.printStackTrace();
    }
    
    public static boolean loadConfig(final String child) {
        while (true) {
            try {
                final File config = new File(FileUtil.config.getAbsolutePath(), child);
                loadBinds(config);
                loadSettings(config);
                loadActiveModules(config);
                return true;
            }
            catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }
    
    public static boolean lambda$loadBinds$2(final String[] array, final Module module) {
        return module.getName().equalsIgnoreCase(array[0]);
    }
    
    public static boolean lambda$loadActiveModules$1(final String[] array, final Module module) {
        return module.getName().equalsIgnoreCase(array[0]);
    }
    
    public static boolean lambda$loadSettings$0(final String[] array, final Module module) {
        return module.getName().equalsIgnoreCase(array[0]);
    }
}
