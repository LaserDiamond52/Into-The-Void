package net.laserdiamond.intothevoid.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.entity.itv.EvolvedEndermiteEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class EvolvedEndermiteRenderer extends MobRenderer<EvolvedEndermiteEntity, EvolvedEndermiteModel<EvolvedEndermiteEntity>> {

    public EvolvedEndermiteRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new EvolvedEndermiteModel<>(pContext.bakeLayer(ITVModelLayers.EVOLVED_ENDERMITE)), 0.75F);
    }

    @Override
    public ResourceLocation getTextureLocation(EvolvedEndermiteEntity evolvedEndermiteEntity) {
        return new ResourceLocation(IntoTheVoid.MODID, "textures/entity/hostile/evolved_endermite.png");
    }

    @Override
    public void render(EvolvedEndermiteEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.scale(2.0F, 2.0F, 2.0F);
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);

    }
}
