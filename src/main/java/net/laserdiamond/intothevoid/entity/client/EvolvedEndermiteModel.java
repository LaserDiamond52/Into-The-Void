package net.laserdiamond.intothevoid.entity.client;// Made with Blockbench 4.10.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.laserdiamond.intothevoid.entity.animations.ITVAnimationDefinitions;
import net.laserdiamond.intothevoid.entity.itv.EvolvedEndermiteEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class EvolvedEndermiteModel<T extends Entity> extends HierarchicalModel<T> implements RotatingHead {

	private final ModelPart evolved_endermite;
	private final ModelPart body;
	private final ModelPart torso;
	private final ModelPart back1;
	private final ModelPart back2;
	private final ModelPart head;
	private final ModelPart eye;
	private final ModelPart fangs;
	private final ModelPart right_fang;
	private final ModelPart lower;
	private final ModelPart left_fang;
	private final ModelPart lower2;
	private final ModelPart legs;
	private final ModelPart right_leg_front;
	private final ModelPart right_leg_front_joint;
	private final ModelPart right_leg_front_lower_joint;
	private final ModelPart right_leg_middle;
	private final ModelPart right_leg_middle_joint;
	private final ModelPart right_leg_middle_lower_joint;
	private final ModelPart right_leg_back;
	private final ModelPart right_leg_back_joint;
	private final ModelPart right_leg_back_lower_joint;
	private final ModelPart left_leg_front;
	private final ModelPart left_leg_front_joint;
	private final ModelPart left_leg_front_lower_joint;
	private final ModelPart left_leg_middle;
	private final ModelPart left_leg_middle_joint;
	private final ModelPart left_leg_middle_lower_joint;
	private final ModelPart left_leg_back;
	private final ModelPart left_leg_back_joint;
	private final ModelPart left_leg_back_lower_joint;

	public EvolvedEndermiteModel(ModelPart root) {
		this.evolved_endermite = root.getChild("evolved_endermite");
		this.body = evolved_endermite.getChild("body");
		this.torso = body.getChild("torso");

		this.back1 = torso.getChild("back1");
		this.back2 = back1.getChild("back2");
		this.head = torso.getChild("head");

		this.eye = head.getChild("eye");
		this.fangs = head.getChild("fangs");
		this.right_fang = fangs.getChild("right_fang");
		this.lower = right_fang.getChild("lower");
		this.left_fang = fangs.getChild("left_fang");
		this.lower2 = left_fang.getChild("lower2");

		this.legs = torso.getChild("legs");

		this.right_leg_front = legs.getChild("right_leg_front");
		this.right_leg_front_joint = right_leg_front.getChild("right_leg_front_joint");
		this.right_leg_front_lower_joint = right_leg_front_joint.getChild("right_leg_front_lower_joint");

		this.right_leg_middle = legs.getChild("right_leg_middle");
		this.right_leg_middle_joint = right_leg_middle.getChild("right_leg_middle_joint");
		this.right_leg_middle_lower_joint = right_leg_middle_joint.getChild("right_leg_middle_lower_joint");

		this.right_leg_back = legs.getChild("right_leg_back");
		this.right_leg_back_joint = right_leg_back.getChild("right_leg_back_joint");
		this.right_leg_back_lower_joint = right_leg_back_joint.getChild("right_leg_back_lower_joint");

		this.left_leg_front = legs.getChild("left_leg_front");
		this.left_leg_front_joint = left_leg_front.getChild("left_leg_front_joint");
		this.left_leg_front_lower_joint = left_leg_front_joint.getChild("left_leg_front_lower_joint");

		this.left_leg_middle = legs.getChild("left_leg_middle");
		this.left_leg_middle_joint = left_leg_middle.getChild("left_leg_middle_joint");
		this.left_leg_middle_lower_joint = left_leg_middle_joint.getChild("left_leg_middle_lower_joint");

		this.left_leg_back = legs.getChild("left_leg_back");
		this.left_leg_back_joint = left_leg_back.getChild("left_leg_back_joint");
		this.left_leg_back_lower_joint = left_leg_back_joint.getChild("left_leg_back_lower_joint");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition evolved_endermite = partdefinition.addOrReplaceChild("evolved_endermite", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = evolved_endermite.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -9.375F, -8.125F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.25F, 3.125F));

		PartDefinition back1 = torso.addOrReplaceChild("back1", CubeListBuilder.create().texOffs(40, 11).addBox(-4.0F, -3.6667F, 0.1667F, 8.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.7083F, 1.2083F));

		PartDefinition back2 = back1.addOrReplaceChild("back2", CubeListBuilder.create().texOffs(44, 4).addBox(-2.0F, -1.6667F, 2.1667F, 4.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition head = torso.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 20).addBox(-4.0F, -3.5F, -5.0F, 8.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.875F, -8.125F));

		PartDefinition eye = head.addOrReplaceChild("eye", CubeListBuilder.create().texOffs(0, 0).addBox(1.5F, -8.25F, 0.75F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 6.75F, -6.0F));

		PartDefinition fangs = head.addOrReplaceChild("fangs", CubeListBuilder.create(), PartPose.offset(-3.0F, 6.5F, -6.0F));

		PartDefinition right_fang = fangs.addOrReplaceChild("right_fang", CubeListBuilder.create().texOffs(1, 35).addBox(-0.375F, -0.5625F, -0.375F, 0.75F, 1.125F, 0.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.375F, -4.4375F, 1.0F, -0.7543F, -0.2443F, -0.2519F));

		PartDefinition lower = right_fang.addOrReplaceChild("lower", CubeListBuilder.create().texOffs(1, 37).addBox(-0.375F, -1.0625F, -0.375F, 0.75F, 1.125F, 0.75F, new CubeDeformation(0.0F))
		.texOffs(1, 39).addBox(-0.25F, -0.0625F, -0.25F, 0.5F, 1.125F, 0.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.4375F, 0.3281F, 0.3491F, 0.0F, 0.0F));

		PartDefinition left_fang = fangs.addOrReplaceChild("left_fang", CubeListBuilder.create().texOffs(1, 35).addBox(-0.375F, -0.5625F, -0.375F, 0.75F, 1.125F, 0.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.375F, -4.4375F, 1.0F, -0.7543F, 0.2443F, 0.2519F));

		PartDefinition lower2 = left_fang.addOrReplaceChild("lower2", CubeListBuilder.create().texOffs(1, 37).addBox(-0.375F, -1.0625F, -0.375F, 0.75F, 1.125F, 0.75F, new CubeDeformation(0.0F))
		.texOffs(1, 39).addBox(-0.25F, -0.0625F, -0.25F, 0.5F, 1.125F, 0.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.4375F, 0.3281F, 0.3491F, 0.0F, 0.0F));

		PartDefinition legs = torso.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, 4.25F, -3.125F));

		PartDefinition right_leg_front = legs.addOrReplaceChild("right_leg_front", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.5F, -4.125F, -2.5F, 0.0F, -0.2618F, 0.0F));

		PartDefinition inner_r1 = right_leg_front.addOrReplaceChild("inner_r1", CubeListBuilder.create().texOffs(0, 44).addBox(-0.5F, -1.375F, -0.75F, 0.875F, 2.875F, 0.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -1.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition right_leg_front_joint = right_leg_front.addOrReplaceChild("right_leg_front_joint", CubeListBuilder.create(), PartPose.offset(-1.75F, -1.75F, 0.0F));

		PartDefinition upper_r1 = right_leg_front_joint.addOrReplaceChild("upper_r1", CubeListBuilder.create().texOffs(4, 42).addBox(-1.0781F, -2.0F, -0.875F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.6094F, 1.5156F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition right_leg_front_lower_joint = right_leg_front_joint.addOrReplaceChild("right_leg_front_lower_joint", CubeListBuilder.create().texOffs(8, 44).addBox(-0.4844F, -0.0156F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 2.875F, -0.375F));

		PartDefinition right_leg_middle = legs.addOrReplaceChild("right_leg_middle", CubeListBuilder.create(), PartPose.offset(-4.5F, -4.125F, 0.5F));

		PartDefinition inner_r2 = right_leg_middle.addOrReplaceChild("inner_r2", CubeListBuilder.create().texOffs(0, 44).addBox(-0.5F, -1.375F, -0.75F, 0.875F, 2.875F, 0.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -1.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition right_leg_middle_joint = right_leg_middle.addOrReplaceChild("right_leg_middle_joint", CubeListBuilder.create(), PartPose.offset(-1.75F, -1.75F, 0.0F));

		PartDefinition upper_r2 = right_leg_middle_joint.addOrReplaceChild("upper_r2", CubeListBuilder.create().texOffs(4, 42).addBox(-1.0781F, -2.0F, -0.875F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.6094F, 1.5156F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition right_leg_middle_lower_joint = right_leg_middle_joint.addOrReplaceChild("right_leg_middle_lower_joint", CubeListBuilder.create().texOffs(8, 44).addBox(-0.4844F, -0.0156F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 2.875F, -0.375F));

		PartDefinition right_leg_back = legs.addOrReplaceChild("right_leg_back", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.5F, -4.125F, 3.125F, 0.0F, 0.2618F, 0.0F));

		PartDefinition inner_r3 = right_leg_back.addOrReplaceChild("inner_r3", CubeListBuilder.create().texOffs(0, 44).addBox(-0.4375F, -1.4375F, -0.375F, 0.875F, 2.875F, 0.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -0.9116F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition right_leg_back_joint = right_leg_back.addOrReplaceChild("right_leg_back_joint", CubeListBuilder.create(), PartPose.offset(-1.75F, -1.75F, 0.375F));

		PartDefinition upper_r3 = right_leg_back_joint.addOrReplaceChild("upper_r3", CubeListBuilder.create().texOffs(4, 42).addBox(-0.5F, -2.5F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.3717F, 1.4604F, -0.375F, 0.0F, 0.0F, 0.7854F));

		PartDefinition right_leg_back_lower_joint = right_leg_back_joint.addOrReplaceChild("right_leg_back_lower_joint", CubeListBuilder.create().texOffs(8, 44).addBox(-0.4844F, -0.0156F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 2.875F, -0.375F));

		PartDefinition left_leg_front = legs.addOrReplaceChild("left_leg_front", CubeListBuilder.create(), PartPose.offsetAndRotation(4.5F, -4.125F, -3.25F, 0.0F, -2.8798F, 0.0F));

		PartDefinition inner_r4 = left_leg_front.addOrReplaceChild("inner_r4", CubeListBuilder.create().texOffs(0, 44).addBox(-0.5F, -1.375F, -0.75F, 0.875F, 2.875F, 0.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -1.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition left_leg_front_joint = left_leg_front.addOrReplaceChild("left_leg_front_joint", CubeListBuilder.create(), PartPose.offset(-1.75F, -1.75F, 0.0F));

		PartDefinition upper_r4 = left_leg_front_joint.addOrReplaceChild("upper_r4", CubeListBuilder.create().texOffs(4, 42).addBox(-0.5F, -2.5F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.3717F, 1.4604F, -0.375F, 0.0F, 3.1416F, 0.7854F));

		PartDefinition left_leg_front_lower_joint = left_leg_front_joint.addOrReplaceChild("left_leg_front_lower_joint", CubeListBuilder.create(), PartPose.offset(-3.0F, 2.875F, -0.375F));

		PartDefinition lower_r1 = left_leg_front_lower_joint.addOrReplaceChild("lower_r1", CubeListBuilder.create().texOffs(8, 44).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0156F, 1.4844F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition left_leg_middle = legs.addOrReplaceChild("left_leg_middle", CubeListBuilder.create(), PartPose.offsetAndRotation(4.5F, -4.125F, 0.125F, 0.0F, 3.1416F, 0.0F));

		PartDefinition inner_r5 = left_leg_middle.addOrReplaceChild("inner_r5", CubeListBuilder.create().texOffs(0, 44).addBox(-0.5F, -1.375F, -0.3594F, 0.875F, 2.875F, 0.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -1.0F, -0.0156F, 0.0F, 0.0F, -0.7854F));

		PartDefinition left_leg_middle_joint = left_leg_middle.addOrReplaceChild("left_leg_middle_joint", CubeListBuilder.create(), PartPose.offset(-1.75F, -1.75F, -0.0156F));

		PartDefinition upper_r5 = left_leg_middle_joint.addOrReplaceChild("upper_r5", CubeListBuilder.create().texOffs(4, 42).addBox(-0.5F, -2.5F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.3717F, 1.4604F, 0.0156F, 0.0F, 3.1416F, 0.7854F));

		PartDefinition left_leg_middle_lower_joint = left_leg_middle_joint.addOrReplaceChild("left_leg_middle_lower_joint", CubeListBuilder.create(), PartPose.offset(-3.0F, 2.875F, 0.0156F));

		PartDefinition lower_r2 = left_leg_middle_lower_joint.addOrReplaceChild("lower_r2", CubeListBuilder.create().texOffs(8, 44).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0156F, 1.4844F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition left_leg_back = legs.addOrReplaceChild("left_leg_back", CubeListBuilder.create(), PartPose.offsetAndRotation(4.5F, -4.125F, 3.125F, 0.0F, 2.8798F, 0.0F));

		PartDefinition inner_r6 = left_leg_back.addOrReplaceChild("inner_r6", CubeListBuilder.create().texOffs(0, 44).addBox(-0.4375F, -1.4375F, -0.375F, 0.875F, 2.875F, 0.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -0.9116F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition left_leg_back_joint = left_leg_back.addOrReplaceChild("left_leg_back_joint", CubeListBuilder.create(), PartPose.offset(-1.75F, -1.75F, 0.375F));

		PartDefinition upper_r6 = left_leg_back_joint.addOrReplaceChild("upper_r6", CubeListBuilder.create().texOffs(4, 42).addBox(-0.5F, -2.5F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.3717F, 1.4604F, -0.375F, 0.0F, 3.1416F, 0.7854F));

		PartDefinition left_leg_back_lower_joint = left_leg_back_joint.addOrReplaceChild("left_leg_back_lower_joint", CubeListBuilder.create(), PartPose.offset(-3.0F, 2.875F, -0.375F));

		PartDefinition lower_r3 = left_leg_back_lower_joint.addOrReplaceChild("lower_r3", CubeListBuilder.create().texOffs(8, 44).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0156F, 1.4844F, 0.0F, 0.0F, 3.1416F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
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
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.headRotation(netHeadYaw, headPitch);

		this.animateWalk(ITVAnimationDefinitions.EVOLVED_ENDERMITE_WALK, limbSwing, limbSwingAmount, 5F, 10F);
		this.animate(((EvolvedEndermiteEntity) entity).idleAnimationState, ITVAnimationDefinitions.EVOLVED_ENDERMITE_IDLE, ageInTicks, 1F);
		this.animate(((EvolvedEndermiteEntity) entity).attackAnimationState, ITVAnimationDefinitions.EVOLVED_ENDERMITE_ATTACK, ageInTicks);
		this.animate(((EvolvedEndermiteEntity) entity).jumpAttackAnimationState, ITVAnimationDefinitions.EVOLVED_ENDERMITE_JUMP_ATTACK, ageInTicks);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		evolved_endermite.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	/**
	 * The root of the model
	 * @return A ModelPart representing the root of the model
	 */
	@Override
	public ModelPart root() {
		return evolved_endermite;
	}

	@Override
	public void headRotation(float headYaw, float headPitch) {
		headYaw = Mth.clamp(headYaw, -30F, 30F);
		headPitch = Mth.clamp(headPitch, -25F, 25F);

		this.head.xRot = headYaw * ((float) Math.PI / 180F);
		this.head.yRot = headPitch * ((float) Math.PI / 180F);
	}
}