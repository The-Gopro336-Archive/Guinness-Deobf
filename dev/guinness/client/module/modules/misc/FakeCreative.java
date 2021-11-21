package dev.guinness.client.module.modules.misc;

import dev.guinness.client.module.Category;
import net.minecraft.world.GameType;
import dev.guinness.client.module.Module;

public class FakeCreative extends Module
{
    public GameType gameType;
    
    public FakeCreative() {
        super("FakeCreative", Category.MISC, "Spoofs your gamemode to creative");
    }
    
    @Override
    public void onEnable() {
        this.gameType = FakeCreative.mc.playerController.getCurrentGameType();
        FakeCreative.mc.playerController.setGameType(GameType.CREATIVE);
    }
    
    @Override
    public void onDisable() {
        FakeCreative.mc.playerController.setGameType(this.gameType);
    }
}
