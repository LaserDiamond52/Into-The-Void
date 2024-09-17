package net.laserdiamond.intothevoid.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;

/**
 * Abstract class used for the creation of humanoid entities of this mod that have the standard head rotation defined by the {@link HeadRotation} interface.
 * Humanoid entities are able to equip tools, weapons, armor, etc., similar to a player.
 * @param <T> The {@link LivingEntity} type
 */
public abstract class ITVEntityHumanoidCreatureModel<T extends LivingEntity> extends HumanoidModel<T> implements HeadRotation<T> {

    // TODO: This class should be inherited by the Void Pirate Model
    public ITVEntityHumanoidCreatureModel(ModelPart pRoot) {
        super(pRoot);
    }

    @Override
    public void renderToBuffer(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha) {
        this.root().render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
    }

    @Override
    public void setupAnim(T pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        super.setupAnim(pEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);

        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.headRotation(pEntity, pNetHeadYaw, pHeadPitch);

        final Item mainHandItem = pEntity.getMainHandItem().getItem();

        if (mainHandItem instanceof SwordItem)
        {
            AnimationUtils.animateZombieArms(this.leftArm, this.rightArm, true, this.attackTime, pAgeInTicks);
        } else if (mainHandItem instanceof BowItem)
        {
            // TODO: Bow arm animations
        } else if (mainHandItem instanceof CrossbowItem)
        {
            AnimationUtils.animateCrossbowHold(this.rightArm, this.leftArm, this.head, true);
        }
    }

    @Override
    public void translateToHand(HumanoidArm pSide, PoseStack pPoseStack) {
        //super.translateToHand(pSide, pPoseStack);
        this.getArm(pSide).translateAndRotate(pPoseStack);
    }

    /**
     * The root of the model
     * @return A {@link ModelPart} representing the root of the model
     */
    public abstract ModelPart root();
}
