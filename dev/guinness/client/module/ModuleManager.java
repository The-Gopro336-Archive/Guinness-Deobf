package dev.guinness.client.module;

import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.function.Predicate;
import dev.guinness.client.module.modules.render.ViewModel;
import dev.guinness.client.module.modules.render.SkyColor;
import dev.guinness.client.module.modules.render.ShulkerPeek;
import dev.guinness.client.module.modules.render.PlayerESP;
import dev.guinness.client.module.modules.render.NoWeather;
import dev.guinness.client.module.modules.render.NoRender;
import dev.guinness.client.module.modules.render.NoCooldown;
import dev.guinness.client.module.modules.render.NoCluster;
import dev.guinness.client.module.modules.render.HoleESP;
import dev.guinness.client.module.modules.render.Fullbright;
import dev.guinness.client.module.modules.render.DeathEffects;
import dev.guinness.client.module.modules.render.CustomFOV;
import dev.guinness.client.module.modules.render.BlockHighlight;
import dev.guinness.client.module.modules.movement.Velocity;
import dev.guinness.client.module.modules.movement.NoWeb;
import dev.guinness.client.module.modules.movement.NoPush;
import dev.guinness.client.module.modules.movement.IceSpeed;
import dev.guinness.client.module.modules.movement.FastFall;
import dev.guinness.client.module.modules.misc.VisualRange;
import dev.guinness.client.module.modules.misc.Spammer;
import dev.guinness.client.module.modules.misc.Potion;
import dev.guinness.client.module.modules.misc.PortalChat;
import dev.guinness.client.module.modules.misc.NoEntityTrace;
import dev.guinness.client.module.modules.misc.MiddleClick;
import dev.guinness.client.module.modules.misc.FastUse;
import dev.guinness.client.module.modules.misc.FakePlayer;
import dev.guinness.client.module.modules.misc.FakeCreative;
import dev.guinness.client.module.modules.misc.ChatSuffix;
import dev.guinness.client.module.modules.misc.BuildHeight;
import dev.guinness.client.module.modules.misc.AutoRespawn;
import dev.guinness.client.module.modules.misc.AutoEZ;
import dev.guinness.client.module.modules.hud.PotionEffects;
import dev.guinness.client.module.modules.hud.Coordinates;
import dev.guinness.client.module.modules.hud.Arraylist;
import dev.guinness.client.module.modules.exploit.XCarry;
import dev.guinness.client.module.modules.exploit.Shulkerception;
import dev.guinness.client.module.modules.exploit.SecretClose;
import dev.guinness.client.module.modules.exploit.Reach;
import dev.guinness.client.module.modules.exploit.PortalGodMode;
import dev.guinness.client.module.modules.exploit.PacketMine;
import dev.guinness.client.module.modules.exploit.PacketFly;
import dev.guinness.client.module.modules.exploit.PacketCanceller;
import dev.guinness.client.module.modules.exploit.NoTurn;
import dev.guinness.client.module.modules.exploit.NoSwing;
import dev.guinness.client.module.modules.exploit.NoHandshake;
import dev.guinness.client.module.modules.exploit.NoBreakDelay;
import dev.guinness.client.module.modules.exploit.MountBypass;
import dev.guinness.client.module.modules.exploit.LiquidInteract;
import dev.guinness.client.module.modules.exploit.InstantMine;
import dev.guinness.client.module.modules.exploit.FuturePacketDumper;
import dev.guinness.client.module.modules.exploit.EntityDesync;
import dev.guinness.client.module.modules.exploit.EBackPack;
import dev.guinness.client.module.modules.exploit.CrystalRemover;
import dev.guinness.client.module.modules.exploit.Crasher;
import dev.guinness.client.module.modules.exploit.AntiPacketBan;
import dev.guinness.client.module.modules.exploit.AntiHunger;
import dev.guinness.client.module.modules.exploit.AntiCrash;
import dev.guinness.client.module.modules.dispenserpvp.Throw32k;
import dev.guinness.client.module.modules.dispenserpvp.ThreadAura;
import dev.guinness.client.module.modules.dispenserpvp.Teleport32kHelper;
import dev.guinness.client.module.modules.dispenserpvp.Teleport32k;
import dev.guinness.client.module.modules.dispenserpvp.Manual32k;
import dev.guinness.client.module.modules.dispenserpvp.Info32k;
import dev.guinness.client.module.modules.dispenserpvp.HopperRadius;
import dev.guinness.client.module.modules.dispenserpvp.HopperNuker;
import dev.guinness.client.module.modules.dispenserpvp.Block32k;
import dev.guinness.client.module.modules.dispenserpvp.Auto32kReset;
import dev.guinness.client.module.modules.dispenserpvp.Auto32k;
import dev.guinness.client.module.modules.dispenserpvp.Aura32k;
import dev.guinness.client.module.modules.dispenserpvp.Anti32k;
import dev.guinness.client.module.modules.combat.TotemPopCounter;
import dev.guinness.client.module.modules.combat.Surround;
import dev.guinness.client.module.modules.combat.SelfWeb;
import dev.guinness.client.module.modules.combat.SelfAnvil;
import dev.guinness.client.module.modules.combat.Offhand;
import dev.guinness.client.module.modules.combat.KillAura;
import dev.guinness.client.module.modules.combat.HoleFiller;
import dev.guinness.client.module.modules.combat.Criticals;
import dev.guinness.client.module.modules.combat.Burrow;
import dev.guinness.client.module.modules.combat.BowSpam;
import dev.guinness.client.module.modules.combat.BedAura;
import dev.guinness.client.module.modules.combat.AutoTrapBed;
import dev.guinness.client.module.modules.combat.AutoTrap;
import dev.guinness.client.module.modules.combat.AutoTotem;
import dev.guinness.client.module.modules.combat.AutoCreeper;
import dev.guinness.client.module.modules.combat.AutoCraftBed;
import dev.guinness.client.module.modules.combat.AutoCity;
import dev.guinness.client.module.modules.combat.AutoArmor;
import dev.guinness.client.module.modules.combat.AnvilNuker;
import dev.guinness.client.module.modules.combat.AnchorAura;
import dev.guinness.client.module.modules.client.Notify;
import dev.guinness.client.module.modules.client.Larp;
import dev.guinness.client.module.modules.client.HudEditor;
import dev.guinness.client.module.modules.client.DiscordRPC;
import dev.guinness.client.module.modules.client.Colors;
import dev.guinness.client.module.modules.client.ClickGui;
import java.util.ArrayList;
import java.util.List;
import dev.guinness.client.util.Wrapper;

public class ModuleManager implements Wrapper
{
    public List<Module> modules;
    public static ModuleManager INSTANCE;
    
    public ModuleManager() {
        this.modules = new ArrayList<Module>();
        this.add(new ClickGui());
        this.add(new Colors());
        this.add(new DiscordRPC());
        this.add(new HudEditor());
        this.add(new Larp());
        this.add(new Notify());
        this.add(new AnchorAura());
        this.add(new AnvilNuker());
        this.add(new AutoArmor());
        this.add(new AutoCity());
        this.add(new AutoCraftBed());
        this.add(new AutoCreeper());
        this.add(new AutoTotem());
        this.add(new AutoTrap());
        this.add(new AutoTrapBed());
        this.add(new BedAura());
        this.add(new BowSpam());
        this.add(new Burrow());
        this.add(new Criticals());
        this.add(new HoleFiller());
        this.add(new KillAura());
        this.add(new Offhand());
        this.add(new SelfAnvil());
        this.add(new SelfWeb());
        this.add(new Surround());
        this.add(new TotemPopCounter());
        this.add(new Anti32k());
        this.add(new Aura32k());
        this.add(new Auto32k());
        this.add(new Auto32kReset());
        this.add(new Block32k());
        this.add(new HopperNuker());
        this.add(new HopperRadius());
        this.add(new Info32k());
        this.add(new Manual32k());
        this.add(new Teleport32k());
        this.add(new Teleport32kHelper());
        this.add(new ThreadAura());
        this.add(new Throw32k());
        this.add(new AntiCrash());
        this.add(new AntiHunger());
        this.add(new AntiPacketBan());
        this.add(new Crasher());
        this.add(new CrystalRemover());
        this.add(new EBackPack());
        this.add(new EntityDesync());
        this.add(new FuturePacketDumper());
        this.add(new InstantMine());
        this.add(new LiquidInteract());
        this.add(new MountBypass());
        this.add(new NoBreakDelay());
        this.add(new NoHandshake());
        this.add(new NoSwing());
        this.add(new NoTurn());
        this.add(new PacketCanceller());
        this.add(new PacketFly());
        this.add(new PacketMine());
        this.add(new PortalGodMode());
        this.add(new Reach());
        this.add(new SecretClose());
        this.add(new Shulkerception());
        this.add(new XCarry());
        this.add(new Arraylist());
        this.add(new Coordinates());
        this.add(new PotionEffects());
        this.add(new AutoEZ());
        this.add(new AutoRespawn());
        this.add(new BuildHeight());
        this.add(new ChatSuffix());
        this.add(new FakeCreative());
        this.add(new FakePlayer());
        this.add(new FastUse());
        this.add(new MiddleClick());
        this.add(new NoEntityTrace());
        this.add(new PortalChat());
        this.add(new Potion());
        this.add(new Spammer());
        this.add(new VisualRange());
        this.add(new FastFall());
        this.add(new IceSpeed());
        this.add(new NoPush());
        this.add(new NoWeb());
        this.add(new Velocity());
        this.add(new BlockHighlight());
        this.add(new CustomFOV());
        this.add(new DeathEffects());
        this.add(new Fullbright());
        this.add(new HoleESP());
        this.add(new NoCluster());
        this.add(new NoCooldown());
        this.add(new NoRender());
        this.add(new NoWeather());
        this.add(new PlayerESP());
        this.add(new ShulkerPeek());
        this.add(new SkyColor());
        this.add(new ViewModel());
    }
    
    public static ModuleManager getInstance() {
        return ModuleManager.INSTANCE;
    }
    
    public void add(final Module module) {
        this.modules.add(module);
    }
    
    public static Module getModule(final Predicate predicate) {
        return (Module)getModules().stream().filter(predicate).findFirst().orElse(null);
    }
    
    public static List getModules(final Predicate predicate) {
        return (List)getModules().stream().filter(predicate).collect(Collectors.toList());
    }
    
    public static List getModules() {
        return getInstance().modules;
    }
    
    static {
        ModuleManager.INSTANCE = new ModuleManager();
    }
}
