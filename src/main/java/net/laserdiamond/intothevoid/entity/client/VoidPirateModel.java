package net.laserdiamond.intothevoid.entity.client;
// Made with Blockbench 4.10.1
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.laserdiamond.intothevoid.entity.animations.ITVAnimationDefinitions;
import net.laserdiamond.intothevoid.entity.itv.VoidPirateEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

/**
 * Contains the Void Pirate Model and functionality of its animations. Most of this is auto-generated from exporting the model from BlockBench as a .java file
 */
public class VoidPirateModel extends HierarchicalModel<VoidPirateEntity> implements HeadRotation<VoidPirateEntity> {

	private final ModelPart void_pirate, body, head, leftLeg, rightLeg, leftArm, rightArm, torso;

	public HumanoidModel.ArmPose rightArmPose, leftArmPose;

	public VoidPirateModel(ModelPart root) {
		this.void_pirate = root.getChild("void_pirate");
		this.body = void_pirate.getChild("body");
		this.head = body.getChild("head");
		this.leftLeg = body.getChild("left_leg");
		this.rightLeg = body.getChild("right_leg");
		this.leftArm = body.getChild("left_arm");
		this.rightArm = body.getChild("right_arm");
		this.torso = body.getChild("torso");
	}

	/**
	 * Creates the body layers of the model
	 * @return A LayerDefinition of the model
	 */
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition void_pirate = partdefinition.addOrReplaceChild("void_pirate", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = void_pirate.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_leg = body.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(48, 51).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -12.0F, 0.0F));

		PartDefinition right_leg = body.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(32, 51).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -12.0F, 0.0F));

		PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(16, 51).addBox(0.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -22.0F, 0.0F));

		PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(0, 51).addBox(-4.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -22.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 33).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition nose = head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(25, 0).addBox(-1.0F, -27.0F, -6.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition hat = head.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(32, 0).addBox(-4.5F, -39.0F, -4.5F, 9.0F, 5.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(22, 16).addBox(-5.0F, -41.0F, -5.0F, 10.0F, 7.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 26.0F, 0.0F));

		PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -5.5F, -3.5F, 9.0F, 19.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(32, 33).addBox(-4.0F, -5.0F, -3.0F, 8.0F, 12.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -19.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	/**
	 * Responsible for setting up the animations for the model
	 * @param entity The entity
	 * @param limbSwing The limb swing (float)
	 * @param limbSwingAmount The limb swing amount (float)
	 * @param ageInTicks The age in ticks of the entity
	 * @param netHeadYaw The head yaw
	 * @param headPitch The head pitch
	 */
	@Override
	public void setupAnim(VoidPirateEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.headRotation(entity, netHeadYaw, headPitch);

		this.animateWalk(ITVAnimationDefinitions.VOID_PIRATE_WALK, limbSwing, limbSwingAmount, 5F, 10F);
		this.animate(entity.idleAnimationState, ITVAnimationDefinitions.VOID_PIRATE_IDLE, ageInTicks, 1F);
		this.animate(entity.attackAnimationState, ITVAnimationDefinitions.VOID_PIRATE_ATTACK, ageInTicks);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		this.root().render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	/**
	 * The root of the model
	 * @return A ModelPart representing the root of the model
	 */
	@Override
	public ModelPart root() {
		return void_pirate;
	}

	@Override
	public ModelPart head() {
		return this.head;
	}
}