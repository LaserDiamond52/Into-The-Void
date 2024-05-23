package net.laserdiamond.intothevoid.entity.client;// Made with Blockbench 4.10.1
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.laserdiamond.intothevoid.entity.animations.ITVAnimationDefinitions;
import net.laserdiamond.intothevoid.entity.itv.EnderDragonHatchlingEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class EnderDragonHatchlingModel<T extends Entity> extends HierarchicalModel<T> implements RotatingHead {

	private final ModelPart ender_dragon_hatchling;
	private final ModelPart body;
	private final ModelPart left_hind_leg;
	private final ModelPart left_foot;
	private final ModelPart right_hind_leg;
	private final ModelPart right_foot;
	private final ModelPart torso;
	private final ModelPart tail1;
	private final ModelPart tail2;
	private final ModelPart tail3;
	private final ModelPart torso_main;
	private final ModelPart neck;
	private final ModelPart head;
	private final ModelPart lower_jaw;
	private final ModelPart upper_jaw;
	private final ModelPart left_front_leg;
	private final ModelPart right_front_leg;
	private final ModelPart right_wing;
	private final ModelPart left_wing;

	public EnderDragonHatchlingModel(ModelPart root) {

		this.ender_dragon_hatchling = root.getChild("ender_dragon_hatchling");
		this.body = ender_dragon_hatchling.getChild("body");
		this.left_hind_leg = body.getChild("left_hind_leg");
		this.left_foot = left_hind_leg.getChild("foot");
		this.right_hind_leg = body.getChild("right_hind_leg");
		this.right_foot = right_hind_leg.getChild("foot2");

		this.torso = body.getChild("torso");
		this.torso_main = torso.getChild("torso2");

		this.tail1 = torso.getChild("tail1");
		this.tail2 = tail1.getChild("tail2");
		this.tail3 = tail2.getChild("tail3");

		this.neck = torso.getChild("neck");
		this.head = neck.getChild("head");
		this.lower_jaw = head.getChild("lower_jaw");
		this.upper_jaw = head.getChild("upper_jaw");
		this.left_front_leg = torso.getChild("left_front_leg");
		this.right_front_leg = torso.getChild("right_front_leg");
		this.right_wing = torso.getChild("right_wing");
		this.left_wing = torso.getChild("left_wing");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition ender_dragon_hatchling = partdefinition.addOrReplaceChild("ender_dragon_hatchling", CubeListBuilder.create(), PartPose.offset(0.0F, 3.0F, -7.0F));

		PartDefinition body = ender_dragon_hatchling.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_hind_leg = body.addOrReplaceChild("left_hind_leg", CubeListBuilder.create(), PartPose.offset(-1.5F, 1.5F, 14.0F));

		PartDefinition leg_r1 = left_hind_leg.addOrReplaceChild("leg_r1", CubeListBuilder.create().texOffs(0, 43).addBox(-2.0F, -2.0F, 0.0F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 1.5F, 0.5F, 0.3927F, 0.0F, 0.0F));

		PartDefinition foot = left_hind_leg.addOrReplaceChild("foot", CubeListBuilder.create(), PartPose.offset(0.0F, 3.0F, 2.0F));

		PartDefinition foot_r1 = foot.addOrReplaceChild("foot_r1", CubeListBuilder.create().texOffs(35, 37).addBox(-2.0F, 2.0F, -3.0F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -1.5F, -1.5F, 0.3927F, 0.0F, 0.0F));

		PartDefinition right_hind_leg = body.addOrReplaceChild("right_hind_leg", CubeListBuilder.create(), PartPose.offset(2.5F, 1.5F, 14.0F));

		PartDefinition leg_r2 = right_hind_leg.addOrReplaceChild("leg_r2", CubeListBuilder.create().texOffs(35, 42).addBox(-1.0F, -2.0F, 0.0F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 1.5F, 0.5F, 0.3927F, 0.0F, 0.0F));

		PartDefinition foot2 = right_hind_leg.addOrReplaceChild("foot2", CubeListBuilder.create(), PartPose.offset(0.0F, 3.0F, 2.0F));

		PartDefinition foot_r2 = foot2.addOrReplaceChild("foot_r2", CubeListBuilder.create().texOffs(35, 37).addBox(2.0F, 2.0F, -3.0F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, -1.5F, -1.5F, 0.3927F, 0.0F, 0.0F));

		PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 14.5F));

		PartDefinition tail1 = torso.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(19, 36).addBox(-1.5F, -1.5F, -2.0F, 3.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(0, 20).addBox(-0.5F, -2.5F, 0.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, -4.0F, 0.0F));

		PartDefinition tail2 = tail1.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(38, 25).addBox(-1.0F, -1.5F, 0.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 20).addBox(0.0F, -2.5F, 0.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 0.0F, 3.0F));

		PartDefinition tail3 = tail2.addOrReplaceChild("tail3", CubeListBuilder.create().texOffs(38, 25).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 20).addBox(-0.5F, -2.5F, 0.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 0.0F, 3.0F));

		PartDefinition torso2 = torso.addOrReplaceChild("torso2", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -5.5F, 4.5F, 7.0F, 7.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(43, 16).addBox(0.0F, -7.5F, 6.25F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(43, 16).addBox(0.0F, -7.0F, 10.25F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -14.5F));

		PartDefinition neck = torso.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(22, 25).addBox(-2.0F, -2.5F, -4.5F, 5.0F, 5.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 20).addBox(0.0F, -3.5F, -2.25F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.5F, -10.0F));

		PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 17).addBox(-3.0F, -4.5F, -7.0F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(27, 44).addBox(-2.0F, -6.5F, -4.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(19, 44).addBox(2.0F, -6.5F, -4.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.5F, -2.5F));

		PartDefinition lower_jaw = head.addOrReplaceChild("lower_jaw", CubeListBuilder.create().texOffs(34, 6).addBox(-3.0F, -1.75F, -5.5F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 2.0F, -6.5F));

		PartDefinition upper_jaw = head.addOrReplaceChild("upper_jaw", CubeListBuilder.create().texOffs(0, 31).addBox(-2.5F, -4.25F, -10.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(33, 6).addBox(-1.5F, -5.25F, -9.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(33, 6).addBox(1.5F, -5.25F, -9.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.5F, -2.0F));

		PartDefinition left_front_leg = torso.addOrReplaceChild("left_front_leg", CubeListBuilder.create(), PartPose.offset(-2.5F, 0.0F, -9.5F));

		PartDefinition foot_r3 = left_front_leg.addOrReplaceChild("foot_r3", CubeListBuilder.create().texOffs(11, 42).addBox(-1.0F, -2.0F, -0.5F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 1.5F, -1.5F, -1.0036F, 0.0F, 0.0F));

		PartDefinition right_front_leg = torso.addOrReplaceChild("right_front_leg", CubeListBuilder.create(), PartPose.offset(3.5F, 0.0F, -9.5F));

		PartDefinition foot_r4 = right_front_leg.addOrReplaceChild("foot_r4", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -2.0F, -0.5F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 1.5F, -1.5F, -1.0036F, 0.0F, 0.0F));

		PartDefinition right_wing = torso.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(34, 14).addBox(0.0F, -0.5F, -4.0F, 11.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(18, 0).addBox(0.0F, 0.0F, -3.0F, 11.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -6.5F, -4.5F));

		PartDefinition left_wing = torso.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(15, 17).addBox(-11.0F, 0.0F, -2.5F, 11.0F, 0.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(28, 23).addBox(-11.0F, -0.5F, -3.5F, 11.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -6.5F, -5.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.headRotation(netHeadYaw, headPitch);

		this.animateWalk(ITVAnimationDefinitions.ENDER_DRAGON_HATCHLING_IDLE_WALK, limbSwing, limbSwingAmount, 5F, 10F);
		this.animate(((EnderDragonHatchlingEntity) entity).idleAnimationState, ITVAnimationDefinitions.ENDER_DRAGON_HATCHLING_IDLE_WALK, ageInTicks, 1F);
		this.animate(((EnderDragonHatchlingEntity) entity).attackAnimationState, ITVAnimationDefinitions.ENDER_DRAGON_HATCHLING_ATTACK, ageInTicks);
	}

	@Override
	public void headRotation(float headYaw, float headPitch) {
		headYaw = Mth.clamp(headYaw, -30F, 30F);
		headPitch = Mth.clamp(headPitch, -25F, 25F);

		this.head.xRot = headYaw * ((float) Math.PI / 180F);
		this.head.yRot = headPitch * ((float) Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		ender_dragon_hatchling.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return ender_dragon_hatchling;
	}

}