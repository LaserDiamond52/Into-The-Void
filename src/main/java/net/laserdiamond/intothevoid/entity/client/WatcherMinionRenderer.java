package net.laserdiamond.intothevoid.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.entity.itv.WatcherMinionEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class WatcherMinionRenderer extends MobRenderer<WatcherMinionEntity, WatcherMinionModel> {

    public static final ResourceLocation WATCHER_LOCATION = new ResourceLocation(IntoTheVoid.MODID, "textures/entity/hostile/watcher_minion.png");
    public static final ResourceLocation WATCHER_BEAM_LOCATION = new ResourceLocation(IntoTheVoid.MODID, "textures/entity/hostile/watcher_beam.png");
    public static final ResourceLocation GUARDIAN_BEAM_LOCATION = new ResourceLocation("textures/entity/guardian_beam.png");
    public static final RenderType BEAM_RENDER_TYPE = RenderType.entityCutoutNoCull(GUARDIAN_BEAM_LOCATION);

    public WatcherMinionRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new WatcherMinionModel(pContext.bakeLayer(ITVModelLayers.WATCHER_MINION)), 0.25F);
    }

    @Override
    public ResourceLocation getTextureLocation(WatcherMinionEntity watcherMinionEntity) {
        return WATCHER_LOCATION;
    }

    @Override
    public boolean shouldRender(WatcherMinionEntity pLivingEntity, Frustum pCamera, double pCamX, double pCamY, double pCamZ) {
        if (super.shouldRender(pLivingEntity, pCamera, pCamX, pCamY, pCamZ))
        {
            return true;
        } else
        {
            if (pLivingEntity.hasActiveAttackTarget())
            {
                LivingEntity activeTarget = pLivingEntity.getActiveAttackTarget();
                if (activeTarget != null)
                {
                    Vec3 activeTargetPos = this.getPosition(activeTarget, (double) activeTarget.getBbHeight() * 0.5, 1.0F);
                    Vec3 minionPos = this.getPosition(pLivingEntity, (double) pLivingEntity.getEyeHeight(), 1.0F);
                    return pCamera.isVisible(new AABB(minionPos.x, minionPos.y, minionPos.z, activeTargetPos.x, activeTargetPos.y, activeTargetPos.z));
                }
            }
            return false;
        }
    }

    @Override
    public void render(WatcherMinionEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
        guardianBeam(pEntity, pPartialTicks, pPoseStack, pBuffer);
    }

    private Vec3 getPosition(LivingEntity livingEntity, double pYOffset, float pPartialTick)
    {
        double x = Mth.lerp(((double) pPartialTick), livingEntity.xOld, livingEntity.getX());
        double y = Mth.lerp(((double) pPartialTick), livingEntity.yOld, livingEntity.getY()) + pYOffset;
        double z = Mth.lerp(((double) pPartialTick), livingEntity.zOld, livingEntity.getZ());
        return new Vec3(x, y, z);
    }

    private static void vertex(VertexConsumer consumer, Matrix4f pPose, Matrix3f pNormal, float x, float y, float z, int red, int green, int blue, float u, float v)
    {
        consumer.vertex(pPose, x, y, z).color(red, green, blue, 255).uv(u, v).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(pNormal,0.0F, 1.0F, 0.0F).endVertex();
    }

    private void guardianBeam(WatcherMinionEntity watcherMinionEntity, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer)
    {
        LivingEntity target = watcherMinionEntity.getActiveAttackTarget();
        if (target != null)
        {
            float attackAnimationScale = watcherMinionEntity.getAttackAnimationScale(pPartialTicks);
            float clientSideAttackTime = watcherMinionEntity.getClientSideAttackTime() + pPartialTicks;
            float $$9 = clientSideAttackTime * 0.5F % 1.0F;
            float minionEyeHeight = watcherMinionEntity.getEyeHeight();
            pPoseStack.pushPose();
            pPoseStack.translate(0.0F, minionEyeHeight, 0.0F);
            Vec3 targetPos = this.getPosition(target, ((double) target.getBbHeight()) * 0.5, pPartialTicks);
            Vec3 minionPos = this.getPosition(watcherMinionEntity, ((double) minionEyeHeight), pPartialTicks);
            Vec3 subtract = targetPos.subtract(minionPos);
            float length = (float) (subtract.length() + 1.0);
            subtract = subtract.normalize();
            float $$15 = (float) Math.acos(subtract.y);
            float $$16 = (float) Math.atan2(subtract.z, subtract.x);
            pPoseStack.mulPose(Axis.YP.rotationDegrees((1.5707964F - $$16) * 57.295776F));
            pPoseStack.mulPose(Axis.XP.rotationDegrees($$15 * 57.295776F));
            float $$18 = clientSideAttackTime * 0.05F * -1.5F;
            float $$19 = attackAnimationScale * attackAnimationScale;
            int $$20 = 64 + (int) ($$19 * 191.0F);
            int $$21 = 32 + (int) ($$19 * 191.0F);
            int $$22 = 128 - (int) ($$19 * 64.0F);
            float $$23 = 0.2F;
            float $$24 = 0.282F;
            float $$25 = Mth.cos($$18 + 2.3561945F) * $$24;
            float $$26 = Mth.sin($$18 + 2.3561945F) * $$24;
            float $$27 = Mth.cos($$18 + 0.7853982F) * $$24;
            float $$28 = Mth.sin($$18 + 0.7853982F) * $$24;
            float $$29 = Mth.cos($$18 + 3.926991F) * $$24;
            float $$30 = Mth.sin($$18 + 3.926991F) * $$24;
            float $$31 = Mth.cos($$18 + 5.4977875F) * $$24;
            float $$32 = Mth.sin($$18 + 5.4977875F) * $$24;
            float $$33 = Mth.cos($$18 + 3.1415927F) * $$23;
            float $$34 = Mth.sin($$18 + 3.1415927F) * $$23;
            float $$35 = Mth.cos($$18 + 0.0F) * $$23;
            float $$36 = Mth.sin($$18 + 0.0F) * $$23;
            float $$37 = Mth.cos($$18 + 1.5707964F) * $$23;
            float $$38 = Mth.sin($$18 + 1.5707964F) * $$23;
            float $$39 = Mth.cos($$18 + 4.712389F) * $$23;
            float $$40 = Mth.sin($$18 + 4.712389F) * $$23;
            float $$42 = 0.0F;
            float $$43 = 0.4999F;
            float v2 = -1.0F + $$9;
            float v1 = length * 2.5F + v2;
            VertexConsumer beamConsumerBuffer = pBuffer.getBuffer(BEAM_RENDER_TYPE);
            PoseStack.Pose last = pPoseStack.last();
            Matrix4f matrix4f = last.pose();
            Matrix3f matrix3f = last.normal();
            vertex(beamConsumerBuffer, matrix4f, matrix3f, $$33, length, $$34, $$20, $$21, $$22, $$43, v1);
            vertex(beamConsumerBuffer, matrix4f, matrix3f, $$33, 0.0F, $$34, $$20, $$21, $$22, $$43, v2);
            vertex(beamConsumerBuffer, matrix4f, matrix3f, $$35, 0.0F, $$36, $$20, $$21, $$22, $$42, v2);
            vertex(beamConsumerBuffer, matrix4f, matrix3f, $$35, length, $$36, $$20, $$21, $$22, $$42, v1);
            vertex(beamConsumerBuffer, matrix4f, matrix3f, $$37, length, $$38, $$20, $$21, $$22, $$43, v1);
            vertex(beamConsumerBuffer, matrix4f, matrix3f, $$37, 0.0F, $$38, $$20, $$21, $$22, $$43, v2);
            vertex(beamConsumerBuffer, matrix4f, matrix3f, $$39, 0.0F, $$40, $$20, $$21, $$22, $$42, v2);
            vertex(beamConsumerBuffer, matrix4f, matrix3f, $$39, length, $$40, $$20, $$21, $$22, $$42, v1);
            float v3 = 0.0F;
            if (watcherMinionEntity.tickCount % 2 == 0) {
                v3 = 0.5F;
            }

            vertex(beamConsumerBuffer, matrix4f, matrix3f, $$25, length, $$26, $$20, $$21, $$22, 0.5F, v3 + 0.5F);
            vertex(beamConsumerBuffer, matrix4f, matrix3f, $$27, length, $$28, $$20, $$21, $$22, 1.0F, v3 + 0.5F);
            vertex(beamConsumerBuffer, matrix4f, matrix3f, $$31, length, $$32, $$20, $$21, $$22, 1.0F, v3);
            vertex(beamConsumerBuffer, matrix4f, matrix3f, $$29, length, $$30, $$20, $$21, $$22, 0.5F, v3);
            pPoseStack.popPose();
        }
    }
}
