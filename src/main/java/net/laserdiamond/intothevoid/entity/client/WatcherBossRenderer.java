package net.laserdiamond.intothevoid.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.entity.itv.WatcherBossEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class WatcherBossRenderer extends MobRenderer<WatcherBossEntity, WatcherBossModel> {
    public WatcherBossRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new WatcherBossModel(pContext.bakeLayer(ITVModelLayers.WATCHER_BOSS)), 0.5F);
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
}
