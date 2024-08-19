package net.laserdiamond.intothevoid.entity.client;// Made with Blockbench 4.10.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.laserdiamond.intothevoid.entity.itv.WatcherMinionEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public class WatcherMinionModel extends HierarchicalModel<WatcherMinionEntity>
{

	private final ModelPart watcher;
	private final ModelPart head;
	private final ModelPart eye;

	public WatcherMinionModel(ModelPart root) {
		this.watcher = root.getChild("watcher");
		this.head = watcher.getChild("head");
		this.eye = head.getChild("eye");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition watcher = partdefinition.addOrReplaceChild("watcher", CubeListBuilder.create(), PartPose.offset(0.0F, 9.0F, 0.0F));

		PartDefinition head = watcher.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 28).addBox(-8.0F, -14.0F, -6.0F, 16.0F, 12.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-6.0F, -14.0F, -8.0F, 12.0F, 12.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(44, 40).addBox(-6.0F, -16.0F, -6.0F, 12.0F, 16.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition eye = head.addOrReplaceChild("eye", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -2.0F, -8.25F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(WatcherMinionEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.yRot = netHeadYaw * 0.017453292F;
		this.head.xRot = headPitch * 0.017453292F;

		Entity cameraEntity = Minecraft.getInstance().getCameraEntity();
		if (entity.hasActiveAttackTarget())
		{
			cameraEntity = entity.getActiveAttackTarget();
		}

		if (cameraEntity != null)
		{
			Vec3 ceEyePos = ((Entity) cameraEntity).getEyePosition(0.0F);
			Vec3 mobEyePos = entity.getEyePosition();

			Vec3 mobViewVec = entity.getViewVector(0.0F);
			mobViewVec = new Vec3(mobViewVec.x, 0.0, mobViewVec.z);
			Vec3 eyeYRot = (new Vec3(mobEyePos.x - ceEyePos.x, 0.0, mobEyePos.z - ceEyePos.z)).normalize().yRot(1.5707964F);
			double dot = mobViewVec.dot(eyeYRot);
			this.eye.x = Mth.sqrt((float) Math.abs(dot)) * 2.0F * (float) Math.signum(dot);
		}

		this.eye.visible = true;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		this.root().render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return watcher;
	}
}