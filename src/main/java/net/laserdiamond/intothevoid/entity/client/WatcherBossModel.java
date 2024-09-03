package net.laserdiamond.intothevoid.entity.client;// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.laserdiamond.intothevoid.entity.animations.ITVAnimationDefinitions;
import net.laserdiamond.intothevoid.entity.itv.WatcherBossEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.Mth;

public class WatcherBossModel extends HierarchicalModel<WatcherBossEntity> implements RotatingHead {
	private final ModelPart watcher;
	private final ModelPart head;
	private final ModelPart eye;
	private final ModelPart body;
	private final ModelPart tentacles;
	private final ModelPart tentacle1;
	private final ModelPart tentacle1_joint;
	private final ModelPart tentacle1_joint2;
	private final ModelPart tentacle1_joint3;
	private final ModelPart tentacle1_joint4;
	private final ModelPart tentacle2;
	private final ModelPart tentacle2_joint;
	private final ModelPart tentacle2_joint2;
	private final ModelPart tentacle2_joint3;
	private final ModelPart tentacle2_joint4;
	private final ModelPart tentacle3;
	private final ModelPart tentacle3_joint;
	private final ModelPart tentacle3_joint2;
	private final ModelPart tentacle3_joint3;
	private final ModelPart tentacle3_joint4;
	private final ModelPart tentacle4;
	private final ModelPart tentacle4_joint;
	private final ModelPart tentacle4_joint2;
	private final ModelPart tentacle4_joint3;
	private final ModelPart tentacle4_joint4;
	private final ModelPart tentacle5;
	private final ModelPart tentacle5_joint;
	private final ModelPart tentacle5_joint2;
	private final ModelPart tentacle5_joint3;
	private final ModelPart tentacle5_joint4;
	private final ModelPart tentacle6;
	private final ModelPart tentacle6_joint;
	private final ModelPart tentacle6_joint2;
	private final ModelPart tentacle6_joint3;
	private final ModelPart tentacle6_joint4;
	private final ModelPart tentacle7;
	private final ModelPart tentacle7_joint;
	private final ModelPart tentacle7_joint2;
	private final ModelPart tentacle7_joint3;
	private final ModelPart tentacle7_joint4;
	private final ModelPart tentacle8;
	private final ModelPart tentacle8_joint;
	private final ModelPart tentacle8_joint2;
	private final ModelPart tentacle8_joint3;
	private final ModelPart tentacle8_joint4;
	private final ModelPart portal;

	public WatcherBossModel(ModelPart root) {
		this.watcher = root.getChild("watcher");
		this.head = watcher.getChild("head");
		this.eye = head.getChild("eye");
		this.body = watcher.getChild("body");
		this.tentacles = body.getChild("tentacles");

		this.tentacle1 = tentacles.getChild("tentacle1");
		this.tentacle1_joint = tentacle1.getChild("tentacle1_joint");
		this.tentacle1_joint2 = tentacle1_joint.getChild("tentacle1_joint2");
		this.tentacle1_joint3 = tentacle1_joint2.getChild("tentacle1_joint3");
		this.tentacle1_joint4 = tentacle1_joint3.getChild("tentacle1_joint4");

		this.tentacle2 = tentacles.getChild("tentacle2");
		this.tentacle2_joint = tentacle2.getChild("tentacle2_joint");
		this.tentacle2_joint2 = tentacle2_joint.getChild("tentacle2_joint2");
		this.tentacle2_joint3 = tentacle2_joint2.getChild("tentacle2_joint3");
		this.tentacle2_joint4 = tentacle2_joint3.getChild("tentacle2_joint4");

		this.tentacle3 = tentacles.getChild("tentacle3");
		this.tentacle3_joint = tentacle3.getChild("tentacle3_joint");
		this.tentacle3_joint2 = tentacle3_joint.getChild("tentacle3_joint2");
		this.tentacle3_joint3 = tentacle3_joint2.getChild("tentacle3_joint3");
		this.tentacle3_joint4 = tentacle3_joint3.getChild("tentacle3_joint4");

		this.tentacle4 = tentacles.getChild("tentacle4");
		this.tentacle4_joint = tentacle4.getChild("tentacle4_joint");
		this.tentacle4_joint2 = tentacle4_joint.getChild("tentacle4_joint2");
		this.tentacle4_joint3 = tentacle4_joint2.getChild("tentacle4_joint3");
		this.tentacle4_joint4 = tentacle4_joint3.getChild("tentacle4_joint4");

		this.tentacle5 = tentacles.getChild("tentacle5");
		this.tentacle5_joint = tentacle5.getChild("tentacle5_joint");
		this.tentacle5_joint2 = tentacle5_joint.getChild("tentacle5_joint2");
		this.tentacle5_joint3 = tentacle5_joint2.getChild("tentacle5_joint3");
		this.tentacle5_joint4 = tentacle5_joint3.getChild("tentacle5_joint4");

		this.tentacle6 = tentacles.getChild("tentacle6");
		this.tentacle6_joint = tentacle6.getChild("tentacle6_joint");
		this.tentacle6_joint2 = tentacle6_joint.getChild("tentacle6_joint2");
		this.tentacle6_joint3 = tentacle6_joint2.getChild("tentacle6_joint3");
		this.tentacle6_joint4 = tentacle6_joint3.getChild("tentacle6_joint4");

		this.tentacle7 = tentacles.getChild("tentacle7");
		this.tentacle7_joint = tentacle7.getChild("tentacle7_joint");
		this.tentacle7_joint2 = tentacle7_joint.getChild("tentacle7_joint2");
		this.tentacle7_joint3 = tentacle7_joint2.getChild("tentacle7_joint3");
		this.tentacle7_joint4 = tentacle7_joint3.getChild("tentacle7_joint4");

		this.tentacle8 = tentacles.getChild("tentacle8");
		this.tentacle8_joint = tentacle8.getChild("tentacle8_joint");
		this.tentacle8_joint2 = tentacle8_joint.getChild("tentacle8_joint2");
		this.tentacle8_joint3 = tentacle8_joint2.getChild("tentacle8_joint3");
		this.tentacle8_joint4 = tentacle8_joint3.getChild("tentacle8_joint4");

		this.portal = body.getChild("portal");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition watcher = partdefinition.addOrReplaceChild("watcher", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head = watcher.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 36).addBox(-10.0F, -18.0F, -8.0F, 20.0F, 16.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-8.0F, -18.0F, -10.0F, 16.0F, 16.0F, 20.0F, new CubeDeformation(0.0F))
		.texOffs(56, 52).addBox(-8.0F, -20.0F, -8.0F, 16.0F, 20.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition eye = head.addOrReplaceChild("eye", CubeListBuilder.create().texOffs(80, 42).addBox(-3.0F, -5.0F, -10.25F, 6.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition body = watcher.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition tentacles = body.addOrReplaceChild("tentacles", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -10.0F, 18.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition tentacle1 = tentacles.addOrReplaceChild("tentacle1", CubeListBuilder.create().texOffs(0, 114).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 3.0F, 7.5F, 0.7854F, 0.0F, -2.3562F));

		PartDefinition tentacle1_joint = tentacle1.addOrReplaceChild("tentacle1_joint", CubeListBuilder.create().texOffs(12, 114).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 0.0F));

		PartDefinition tentacle1_joint2 = tentacle1_joint.addOrReplaceChild("tentacle1_joint2", CubeListBuilder.create().texOffs(24, 115).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 0.0F));

		PartDefinition tentacle1_joint3 = tentacle1_joint2.addOrReplaceChild("tentacle1_joint3", CubeListBuilder.create().texOffs(32, 115).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 0.0F));

		PartDefinition tentacle1_joint4 = tentacle1_joint3.addOrReplaceChild("tentacle1_joint4", CubeListBuilder.create().texOffs(40, 118).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 0.0F));

		PartDefinition tentacle2 = tentacles.addOrReplaceChild("tentacle2", CubeListBuilder.create().texOffs(0, 114).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 3.0F, 2.5F, 0.3927F, 0.0F, -2.3562F));

		PartDefinition tentacle2_joint = tentacle2.addOrReplaceChild("tentacle2_joint", CubeListBuilder.create().texOffs(12, 114).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 0.0F));

		PartDefinition tentacle2_joint2 = tentacle2_joint.addOrReplaceChild("tentacle2_joint2", CubeListBuilder.create().texOffs(24, 115).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 0.0F));

		PartDefinition tentacle2_joint3 = tentacle2_joint2.addOrReplaceChild("tentacle2_joint3", CubeListBuilder.create().texOffs(32, 115).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 0.0F));

		PartDefinition tentacle2_joint4 = tentacle2_joint3.addOrReplaceChild("tentacle2_joint4", CubeListBuilder.create().texOffs(40, 118).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 0.0F));

		PartDefinition tentacle3 = tentacles.addOrReplaceChild("tentacle3", CubeListBuilder.create().texOffs(0, 114).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 3.0F, -2.5F, -0.3927F, 0.0F, -2.3562F));

		PartDefinition tentacle3_joint = tentacle3.addOrReplaceChild("tentacle3_joint", CubeListBuilder.create().texOffs(12, 114).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 0.0F));

		PartDefinition tentacle3_joint2 = tentacle3_joint.addOrReplaceChild("tentacle3_joint2", CubeListBuilder.create().texOffs(24, 115).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 0.0F));

		PartDefinition tentacle3_joint3 = tentacle3_joint2.addOrReplaceChild("tentacle3_joint3", CubeListBuilder.create().texOffs(32, 115).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 0.0F));

		PartDefinition tentacle3_joint4 = tentacle3_joint3.addOrReplaceChild("tentacle3_joint4", CubeListBuilder.create().texOffs(40, 118).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 0.0F));

		PartDefinition tentacle4 = tentacles.addOrReplaceChild("tentacle4", CubeListBuilder.create().texOffs(0, 114).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 3.0F, -7.5F, -0.7854F, 0.0F, -2.3562F));

		PartDefinition tentacle4_joint = tentacle4.addOrReplaceChild("tentacle4_joint", CubeListBuilder.create().texOffs(12, 114).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 0.0F));

		PartDefinition tentacle4_joint2 = tentacle4_joint.addOrReplaceChild("tentacle4_joint2", CubeListBuilder.create().texOffs(24, 115).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 0.0F));

		PartDefinition tentacle4_joint3 = tentacle4_joint2.addOrReplaceChild("tentacle4_joint3", CubeListBuilder.create().texOffs(32, 115).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 0.0F));

		PartDefinition tentacle4_joint4 = tentacle4_joint3.addOrReplaceChild("tentacle4_joint4", CubeListBuilder.create().texOffs(40, 118).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 0.0F));

		PartDefinition tentacle5 = tentacles.addOrReplaceChild("tentacle5", CubeListBuilder.create().texOffs(0, 114).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 3.0F, 7.5F, 0.7854F, 0.0F, 2.3562F));

		PartDefinition tentacle5_joint = tentacle5.addOrReplaceChild("tentacle5_joint", CubeListBuilder.create().texOffs(12, 114).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 0.0F));

		PartDefinition tentacle5_joint2 = tentacle5_joint.addOrReplaceChild("tentacle5_joint2", CubeListBuilder.create().texOffs(24, 115).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 0.0F));

		PartDefinition tentacle5_joint3 = tentacle5_joint2.addOrReplaceChild("tentacle5_joint3", CubeListBuilder.create().texOffs(32, 115).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 0.0F));

		PartDefinition tentacle5_joint4 = tentacle5_joint3.addOrReplaceChild("tentacle5_joint4", CubeListBuilder.create().texOffs(40, 118).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 0.0F));

		PartDefinition tentacle6 = tentacles.addOrReplaceChild("tentacle6", CubeListBuilder.create().texOffs(0, 114).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 3.0F, 2.5F, 0.3927F, 0.0F, 2.3562F));

		PartDefinition tentacle6_joint = tentacle6.addOrReplaceChild("tentacle6_joint", CubeListBuilder.create().texOffs(12, 114).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 0.0F));

		PartDefinition tentacle6_joint2 = tentacle6_joint.addOrReplaceChild("tentacle6_joint2", CubeListBuilder.create().texOffs(24, 115).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 0.0F));

		PartDefinition tentacle6_joint3 = tentacle6_joint2.addOrReplaceChild("tentacle6_joint3", CubeListBuilder.create().texOffs(32, 115).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 0.0F));

		PartDefinition tentacle6_joint4 = tentacle6_joint3.addOrReplaceChild("tentacle6_joint4", CubeListBuilder.create().texOffs(40, 118).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 0.0F));

		PartDefinition tentacle7 = tentacles.addOrReplaceChild("tentacle7", CubeListBuilder.create().texOffs(0, 114).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 3.0F, -2.5F, -0.3927F, 0.0F, 2.3562F));

		PartDefinition tentacle7_joint = tentacle7.addOrReplaceChild("tentacle7_joint", CubeListBuilder.create().texOffs(12, 114).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 0.0F));

		PartDefinition tentacle7_joint2 = tentacle7_joint.addOrReplaceChild("tentacle7_joint2", CubeListBuilder.create().texOffs(24, 115).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 0.0F));

		PartDefinition tentacle7_joint3 = tentacle7_joint2.addOrReplaceChild("tentacle7_joint3", CubeListBuilder.create().texOffs(32, 115).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 0.0F));

		PartDefinition tentacle7_joint4 = tentacle7_joint3.addOrReplaceChild("tentacle7_joint4", CubeListBuilder.create().texOffs(40, 118).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 0.0F));

		PartDefinition tentacle8 = tentacles.addOrReplaceChild("tentacle8", CubeListBuilder.create().texOffs(0, 114).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 3.0F, -7.5F, -0.7854F, 0.0F, 2.3562F));

		PartDefinition tentacle8_joint = tentacle8.addOrReplaceChild("tentacle8_joint", CubeListBuilder.create().texOffs(12, 114).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 0.0F));

		PartDefinition tentacle8_joint2 = tentacle8_joint.addOrReplaceChild("tentacle8_joint2", CubeListBuilder.create().texOffs(24, 115).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 0.0F));

		PartDefinition tentacle8_joint3 = tentacle8_joint2.addOrReplaceChild("tentacle8_joint3", CubeListBuilder.create().texOffs(32, 115).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 0.0F));

		PartDefinition tentacle8_joint4 = tentacle8_joint3.addOrReplaceChild("tentacle8_joint4", CubeListBuilder.create().texOffs(40, 118).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 0.0F));

		PartDefinition portal = body.addOrReplaceChild("portal", CubeListBuilder.create().texOffs(0, 68).addBox(-13.0F, -13.5F, 0.0F, 26.0F, 26.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -10.0F, 22.75F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(WatcherBossEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.headRotation(netHeadYaw, headPitch);

		this.animate(entity.idleAnimationState, ITVAnimationDefinitions.WATCHER_BOSS_IDLE, ageInTicks, 1F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		watcher.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void portalRenderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		portal.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return watcher;
	}

	@Override
	public void headRotation(float headYaw, float headPitch) {
		headYaw = Mth.clamp(headYaw, -30F, 30F);
		headPitch = Mth.clamp(headPitch, -25F, 25F);

		this.head.xRot = headYaw * ((float) Math.PI / 180F);
		this.head.yRot = headPitch * ((float) Math.PI / 180F);	}
}