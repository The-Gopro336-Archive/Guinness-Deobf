package dev.guinness.client;

import org.apache.logging.log4j.LogManager;
import dev.guinness.client.setting.Setting;
import dev.guinness.client.setting.SBoolean;
import java.lang.reflect.Field;
import java.util.function.Predicate;
import java.util.Arrays;
import dev.guinness.client.module.Module;
import java.util.function.Consumer;
import dev.guinness.client.module.ModuleManager;
import dev.guinness.client.event.ForgeEventHandler;
import net.minecraftforge.common.MinecraftForge;
import dev.guinness.client.util.MathUtil;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import dev.guinness.client.util.FileUtil;
import dev.guinness.client.gui.module.ModuleFrame;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.lwjgl.opengl.Display;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
import dev.guinness.client.gui.GuinnessGui;
import dev.guinness.client.gui.GuinnessHudGui;
import dev.guinness.client.util.GuinnessFont;
import net.minecraftforge.fml.common.Mod;

@Mod(modid = "guinness", name = "Guinness", version = "0.4.2", acceptedMinecraftVersions = "[1.12.2]")
public class Guinness
{
    public static GuinnessFont LARGE_FONT;
    public static String SPOOFNAME;
    public static GuinnessHudGui guinnessHudGui;
    @Mod.Instance("guinness")
    public static Guinness INSTANCE;
    public static GuinnessFont SMALL_FONT;
    public static GuinnessGui guinnessClickGui;
    public static Logger LOGGER;
    public static GuinnessFont HUGE_FONT;
    public static String VERSION;
    public long startTime;
    public static GuinnessFont CUSTOM_FONT;
    public static String NAME;
    public static String MODID;
    
    @Mod.EventHandler
    public void preInit(final FMLPreInitializationEvent fmlPreInitializationEvent) {
        this.startTime = System.currentTimeMillis();
        Display.setTitle("Guinness v0.4.2");
    }
    
    @Mod.EventHandler
    public void init(final FMLInitializationEvent fmlInitializationEvent) {
        this.initMod();
        this.initSettings();
        ModuleFrame.init();
        Runtime.getRuntime().addShutdownHook(new Thread(FileUtil::saveAll));
    }
    
    @Mod.EventHandler
    public void postInit(final FMLPostInitializationEvent fmlPostInitializationEvent) {
        this.initFilesystem();
        final long endTime = (System.currentTimeMillis() - this.startTime) / ((long)1421342688 ^ 0x54B7F408L);
        Guinness.LOGGER.info("Guinness initialization complete in " + MathUtil.roundDouble((double)endTime, 3) + "s");
    }
    
    public void initMod() {
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(ForgeEventHandler.INSTANCE);
    }
    
    public void initSettings() {
        ModuleManager.getModules().forEach(Guinness::lambda$initSettings$2);
    }
    
    public void initFilesystem() {
        FileUtil.createDirectory();
        FileUtil.loadAll();
    }
    
    public static void lambda$initSettings$2(final Module module) {
        Arrays.stream(module.getClass().getDeclaredFields()).filter(Guinness::lambda$null$0).forEach(Guinness::lambda$null$1);
        module.addSetting(new SBoolean("Drawn", true));
    }
    
    public static void lambda$null$1(final Module module, final Field field) {
        field.setAccessible(true);
        IllegalAccessException ex;
        try {
            module.addSetting((Setting)field.get(module.getClass()));
            return;
        }
        catch (IllegalAccessException e) {
            ex = e;
        }
        ex.printStackTrace();
    }
    
    public static boolean lambda$null$0(final Field field) {
        return Setting.class.isAssignableFrom(field.getType());
    }
    
    static {
        Guinness.MODID = "guinness";
        Guinness.NAME = "Guinness";
        Guinness.VERSION = "0.4.2";
        Guinness.SPOOFNAME = "Guinness";
        Guinness.LOGGER = LogManager.getLogger("Guinness");
        Guinness.SMALL_FONT = new GuinnessFont("ProductSans", Float.intBitsToFloat(Float.floatToIntBits(1.1483968f) ^ 0x7E32FEAB));
        Guinness.CUSTOM_FONT = new GuinnessFont("ProductSans", Float.intBitsToFloat(Float.floatToIntBits(0.028795598f) ^ 0x7D5BE4BF));
        Guinness.LARGE_FONT = new GuinnessFont("ProductSans", Float.intBitsToFloat(Float.floatToIntBits(0.45496625f) ^ 0x7F28F156));
        Guinness.HUGE_FONT = new GuinnessFont("ProductSans", Float.intBitsToFloat(Float.floatToIntBits(0.03646287f) ^ 0x7F655A17));
        Guinness.guinnessClickGui = new GuinnessGui();
        Guinness.guinnessHudGui = new GuinnessHudGui();
    }
}
