package net.laserdiamond.intothevoid.screen.Refinery;

import com.mojang.blaze3d.systems.RenderSystem;
import net.laserdiamond.intothevoid.IntoTheVoid;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.BrewingStandScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class RefineryScreen extends AbstractContainerScreen<RefineryMenu> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(IntoTheVoid.MODID, "textures/gui/refinery.png");
    public RefineryScreen(RefineryMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        renderWaterLevel(guiGraphics, x, y);
        renderProgressArrow(guiGraphics, x, y);
    }

    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y)
    {
        if (menu.isRefining())
        {
            guiGraphics.blit(TEXTURE, x + 80, y + 35, 176, 14, menu.getScaledProgress(), 24);
        }
    }

    private void renderWaterLevel(GuiGraphics guiGraphics, int x, int y)
    {
        int waterLevel = menu.getScaledWaterLevel();
        guiGraphics.blit(TEXTURE, x + 56, y + 36 + 14 - waterLevel, 176, 14 - waterLevel, 16, waterLevel);
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pGuiGraphics);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }
}
