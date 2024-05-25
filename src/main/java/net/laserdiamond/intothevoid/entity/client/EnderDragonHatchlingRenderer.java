package net.laserdiamond.intothevoid.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.entity.itv.EnderDragonHatchlingEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

/**
 * Class responsible for rendering the Ender Dragon Hatchling
 */
public class EnderDragonHatchlingRenderer extends MobRenderer<EnderDragonHatchlingEntity, EnderDragonHatchlingModel<EnderDragonHatchlingEntity>> {
    public EnderDragonHatchlingRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new EnderDragonHatchlingModel<>(pContext.bakeLayer(ITVModelLayers.ENDER_DRAGON_HATCHLING)), 0.5F);
    }

    /**
     * The resource location of the texture for the entity
     * @param enderDragonHatchlingEntity The Ender Dragon Hatchling
     * @return A ResourceLocation mapping to the Ender Dragon Hatchling texture
     */
    @Override
    public ResourceLocation getTextureLocation(EnderDragonHatchlingEntity enderDragonHatchlingEntity) {
        return new ResourceLocation(IntoTheVoid.MODID, "textures/entity/hostile/ender_dragon_hatchling.png");
    }


    @Override
    public void render(EnderDragonHatchlingEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
