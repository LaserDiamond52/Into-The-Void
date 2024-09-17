package net.laserdiamond.intothevoid.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.Entity;

/**
 * Abstract class used for the creation of entities of this mod that have the standard head rotation defined by the {@link HeadRotation} interface.
 * @param <T> The {@link Entity} type
 */
public abstract class ITVEntityCreatureModel<T extends Entity> extends HierarchicalModel<T> implements HeadRotation<T> {

    /**
     * Responsible for setting up the animations for the model
     * @param t The entity
     * @param limbSwing The limb swing (float)
     * @param limbSwingAmount The limb swing amount (float)
     * @param ageInTicks The age in ticks of the entity
     * @param netHeadYaw The head yaw
     * @param headPitch The head pitch
     */
    @Override
    public void setupAnim(T t, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
    {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.headRotation(t, netHeadYaw, headPitch);
    }

    @Override
    public void renderToBuffer(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha)
    {
        this.root().render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
    }
}
