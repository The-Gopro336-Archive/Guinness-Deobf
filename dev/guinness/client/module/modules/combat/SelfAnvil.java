package dev.guinness.client.module.modules.combat;

import dev.guinness.client.util.ModuleUtil;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import dev.guinness.client.util.InventoryUtil;
import net.minecraft.init.Blocks;
import dev.guinness.client.module.Category;
import dev.guinness.client.module.Module;

public class SelfAnvil extends Module
{
    public SelfAnvil() {
        super("SelfAnvil", Category.COMBAT, "Places an anvil to fall over your head protecting you from beds (1.13+)");
    }
    
    @Override
    public void onEnable() {
        final int anvil = InventoryUtil.find(Blocks.ANVIL);
        if (anvil != -1 && SelfAnvil.mc.world.func_180495_p(new BlockPos(SelfAnvil.mc.player.func_174791_d())).func_185904_a().isReplaceable()) {
            final int oldSlot = SelfAnvil.mc.player.field_71071_by.currentItem;
            SelfAnvil.mc.player.field_71071_by.currentItem = anvil;
            ModuleUtil.placeBlock(new BlockPos(SelfAnvil.mc.player.func_174791_d()).add(0, 3, 0), EnumFacing.DOWN);
            SelfAnvil.mc.player.field_71071_by.currentItem = oldSlot;
            this.disable();
            return;
        }
        this.disable();
    }
}
