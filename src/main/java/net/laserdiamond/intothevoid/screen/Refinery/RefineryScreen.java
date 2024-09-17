package net.laserdiamond.intothevoid.screen.Refinery;

import com.mojang.blaze3d.systems.RenderSystem;
import net.laserdiamond.intothevoid.IntoTheVoid;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

/**
 * Class that represents the Refinery Screen for the Refinery Menu
 */
public class RefineryScreen extends AbstractContainerScreen<RefineryMenu> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(IntoTheVoid.MODID, "textures/gui/refinery.png");

    public RefineryScreen(RefineryMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
    }

    /**
     * Responsible for rendering the graphics of the menu
     * @param guiGraphics The GUI graphics of the screen
     * @param v
     * @param i The X coordinate of the cursor
     * @param i1 The Y coordinate of the cursor
     */
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

    /**
     * Responsible for rendering the progress arrow that indicates the recipe progress
     * @param guiGraphics The GUI graphics of the screen
     * @param x X Coordinate of where to draw the arrow on the screen
     * @param y Y Coordinate of where to draw the arrow on the screen
     */
    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y)
    {
        if (menu.isRefining())
        {
            guiGraphics.blit(TEXTURE, x + 80, y + 35, 176, 14, menu.getScaledProgress(), 24);
        }
    }

    /**
     * Responsible for rendering the water level indicating on the screen
     * @param guiGraphics The GUI graphics of the screen
     * @param x X Coordinate of where to draw the water texture on the screen
     * @param y Y Coordinate of where to draw the water texture on the screen
     */
    private void renderWaterLevel(GuiGraphics guiGraphics, int x, int y)
    {
        int waterLevel = menu.getScaledWaterLevel();
        guiGraphics.blit(TEXTURE, x + 56, y + 36 + 14 - waterLevel, 176, 14 - waterLevel, 16, waterLevel);
    }

    /**
     * Renders the screen
     * @param pGuiGraphics The GUI graphics of the screen
     * @param pMouseX The cursor's X coordinate
     * @param pMouseY The cursor's Y coordinate
     * @param pPartialTick
     */
    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pGuiGraphics);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }
}
