package net.laserdiamond.intothevoid.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.entity.itv.WatcherMinionEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class WatcherMinionRenderer extends MobRenderer<WatcherMinionEntity, WatcherModel<WatcherMinionEntity>> {
    public WatcherMinionRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new WatcherModel<>(pContext.bakeLayer(ITVModelLayers.WATCHER_MINION)), 0.25F);
    }

    @Override
    public ResourceLocation getTextureLocation(WatcherMinionEntity watcherMinionEntity) {
        return new ResourceLocation(IntoTheVoid.MODID, "textures/entity/hostile/watcher_minion.png");
    }

    @Override
    public void render(WatcherMinionEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
