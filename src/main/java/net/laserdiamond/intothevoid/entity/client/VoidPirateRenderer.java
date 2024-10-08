package net.laserdiamond.intothevoid.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.entity.itv.VoidPirateEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

/**
 * Class responsible for rendering the Void Pirate
 */
public class VoidPirateRenderer extends MobRenderer<VoidPirateEntity, VoidPirateModel> {

    public VoidPirateRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new VoidPirateModel(pContext.bakeLayer(ITVModelLayers.VOID_PIRATE)), 0.25F);
    }

    /**
     * The resource location of the texture for the entity
     * @param voidPirateEntity The Void Pirate
     * @return A ResourceLocation mapping to the Void Pirate
     */
    @Override
    public ResourceLocation getTextureLocation(VoidPirateEntity voidPirateEntity) {
        return new ResourceLocation(IntoTheVoid.MODID, "textures/entity/hostile/void_pirate.png");
    }

    @Override
    public void render(VoidPirateEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
