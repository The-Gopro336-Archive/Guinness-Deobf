package dev.guinness.client.module.modules.render;

import dev.guinness.client.util.ModuleUtil;
import net.minecraft.nbt.NBTTagCompound;
import dev.guinness.client.util.ColorUtil;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.function.Predicate;
import java.util.List;
import net.minecraft.client.gui.GuiScreen;
import java.awt.Color;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.util.NonNullList;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.item.ItemStack;
import dev.guinness.client.module.Category;
import dev.guinness.client.setting.SMode;
import dev.guinness.client.module.Module;

public class ShulkerPeek extends Module
{
    public static SMode mode;
    
    public ShulkerPeek() {
        super("ShulkerPeek", Category.RENDER, "Allows you to see inside shulker boxes in your inventory");
    }
    
    public static void renderToolTip(final ItemStack itemStack, final int n, final int n2, final CallbackInfo callbackInfo) {
        final NBTTagCompound tagCompound = itemStack.getTagCompound();
        if (tagCompound != null && tagCompound.hasKey("BlockEntityTag", 10)) {
            final NBTTagCompound blockEntityTag = tagCompound.getCompoundTag("BlockEntityTag");
            if (blockEntityTag.hasKey("Items", 9)) {
                callbackInfo.cancel();
                final NonNullList<ItemStack> nonnulllist = (NonNullList<ItemStack>)NonNullList.withSize(27, (Object)ItemStack.EMPTY);
                ItemStackHelper.loadAllItems(blockEntityTag, (NonNullList)nonnulllist);
                GlStateManager.enableBlend();
                GlStateManager.disableRescaleNormal();
                RenderHelper.disableStandardItemLighting();
                GlStateManager.disableLighting();
                GlStateManager.disableDepth();
                final int x1 = n + 12;
                final int y1 = n2 - 12;
                final int height = 57;
                final int width = Math.max(144, ShulkerPeek.mc.fontRenderer.getStringWidth(itemStack.getDisplayName()) + 3);
                ShulkerPeek.mc.getRenderItem().zLevel = Float.intBitsToFloat(Float.floatToIntBits(0.04204594f) ^ 0x7EBA385D);
                GuiScreen.func_73734_a(x1 - 4, y1 - 9, x1 + width + 1, y1 + 4, new Color(0, 0, 0, 144).getRGB());
                GuiScreen.func_73734_a(x1 - 4, y1 + 5, x1 + width + 1, y1 + height + 3, new Color(0, 0, 0, 144).getRGB());
                final List<ItemStack> superweapons = nonnulllist.stream().filter(ShulkerPeek::lambda$renderToolTip$0).collect((Collector<? super Object, ?, List<ItemStack>>)Collectors.toList());
                if (((String)ShulkerPeek.mode.getValue()).equalsIgnoreCase("Always")) {
                    GuiScreen.func_73734_a(x1 - 5, y1 - 10, x1 - 6, y1 + height + 5, ColorUtil.BESTCOLOR(0, 255));
                    GuiScreen.func_73734_a(x1 + width + 2, y1 - 11, x1 + width + 3, y1 + height + 5, ColorUtil.BESTCOLOR(0, 255));
                    GuiScreen.func_73734_a(x1 - 6, y1 - 11, x1 + width + 2, y1 - 10, ColorUtil.BESTCOLOR(0, 255));
                    GuiScreen.func_73734_a(x1 - 5, y1 + height + 4, x1 + width + 2, y1 + height + 5, ColorUtil.BESTCOLOR(0, 255));
                }
                else if (superweapons.size() > 0) {
                    GuiScreen.func_73734_a(x1 - 5, y1 - 10, x1 - 6, y1 + height + 5, ColorUtil.BESTCOLOR(0, 255));
                    GuiScreen.func_73734_a(x1 + width + 2, y1 - 11, x1 + width + 3, y1 + height + 5, ColorUtil.BESTCOLOR(0, 255));
                    GuiScreen.func_73734_a(x1 - 6, y1 - 11, x1 + width + 2, y1 - 10, ColorUtil.BESTCOLOR(0, 255));
                    GuiScreen.func_73734_a(x1 - 5, y1 + height + 4, x1 + width + 2, y1 + height + 5, ColorUtil.BESTCOLOR(0, 255));
                }
                else {
                    GuiScreen.func_73734_a(x1 - 5, y1 - 10, x1 - 6, y1 + height + 5, new Color(0, 0, 0, 144).getRGB());
                    GuiScreen.func_73734_a(x1 + width + 2, y1 - 11, x1 + width + 3, y1 + height + 5, new Color(0, 0, 0, 144).getRGB());
                    GuiScreen.func_73734_a(x1 - 6, y1 - 11, x1 + width + 2, y1 - 10, new Color(0, 0, 0, 144).getRGB());
                    GuiScreen.func_73734_a(x1 - 5, y1 + height + 4, x1 + width + 2, y1 + height + 5, new Color(0, 0, 0, 144).getRGB());
                }
                ShulkerPeek.mc.fontRenderer.drawStringWithShadow(itemStack.getDisplayName(), (float)(x1 + width / 2 - ShulkerPeek.mc.fontRenderer.getStringWidth(itemStack.getDisplayName()) / 2), (float)(y1 - 6), new Color(255, 255, 255).getRGB());
                GlStateManager.enableBlend();
                GlStateManager.enableAlpha();
                GlStateManager.enableTexture2D();
                GlStateManager.enableLighting();
                GlStateManager.enableDepth();
                RenderHelper.enableGUIStandardItemLighting();
                for (int i = 0; i < nonnulllist.size(); ++i) {
                    final int iX = n + i % 9 * 16 + 11;
                    final int iY = n2 + i / 9 * 16 - 11 + 8;
                    final ItemStack stack = (ItemStack)nonnulllist.get(i);
                    ShulkerPeek.mc.getRenderItem().renderItemAndEffectIntoGUI(stack, iX, iY);
                    ShulkerPeek.mc.getRenderItem().renderItemOverlayIntoGUI(ShulkerPeek.mc.fontRenderer, stack, iX, iY, (String)null);
                }
                RenderHelper.disableStandardItemLighting();
                ShulkerPeek.mc.getRenderItem().zLevel = Float.intBitsToFloat(Float.floatToIntBits(2.490494E38f) ^ 0x7F3B5D2B);
                GlStateManager.enableLighting();
                GlStateManager.enableDepth();
                RenderHelper.enableStandardItemLighting();
                GlStateManager.enableRescaleNormal();
            }
        }
    }
    
    public static boolean lambda$renderToolTip$0(final ItemStack itemStack) {
        return ModuleUtil.is32k(itemStack);
    }
    
    static {
        ShulkerPeek.mode = new SMode("Glow Mode", new String[] { "Always", "32k Only" });
    }
}
