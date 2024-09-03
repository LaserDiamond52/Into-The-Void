package net.laserdiamond.intothevoid.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.entity.itv.WatcherBossEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class WatcherBossRenderer extends MobRenderer<WatcherBossEntity, WatcherBossModel> {

    public WatcherBossRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new WatcherBossModel(pContext.bakeLayer(ITVModelLayers.WATCHER_BOSS)), 0.5F);
        this.addLayer(new WatcherBossPortalRenderer(this));
    }

    /**
     * The resource location of the texture for the entity
     * @param watcherBossEntity The Watcher Boss mob
     * @return A ResourceLocation mapping to the Watcher Boss
     */
    @Override
    public ResourceLocation getTextureLocation(WatcherBossEntity watcherBossEntity) {
        return new ResourceLocation(IntoTheVoid.MODID, "textures/entity/hostile/watcher_boss.png");
    }

    @Override
    public void render(WatcherBossEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.scale(1.5F, 1.5F, 1.5F);
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }

    static class WatcherBossPortalRenderer extends RenderLayer<WatcherBossEntity, WatcherBossModel>
    {
        private static final RenderType PORTAL_RENDER_TYPE = RenderType.endPortal();

        public WatcherBossPortalRenderer(RenderLayerParent<WatcherBossEntity, WatcherBossModel> pRenderer) {
            super(pRenderer);
        }

        @Override
        public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, WatcherBossEntity watcherBossEntity, float v, float v1, float v2, float v3, float v4, float v5) {
            poseStack.translate(0, 1.5, 0);
            this.getParentModel().portalRenderToBuffer(poseStack, multiBufferSource.getBuffer(PORTAL_RENDER_TYPE), 15728640, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
}
