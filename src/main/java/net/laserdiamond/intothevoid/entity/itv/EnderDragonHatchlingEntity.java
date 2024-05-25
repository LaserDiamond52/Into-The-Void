package net.laserdiamond.intothevoid.entity.itv;

import net.laserdiamond.intothevoid.entity.ai.AttackingEntity;
import net.laserdiamond.intothevoid.entity.ai.EnderDragonHatchlingAttackGoal;
import net.laserdiamond.intothevoid.entity.animations.SetupAnimationState;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.Nullable;

/**
 * The Ender Dragon Hatchling Entity
 */
public class EnderDragonHatchlingEntity extends Monster implements SetupAnimationState, AttackingEntity {

    private static final EntityDataAccessor<Boolean> IS_CHARGING = SynchedEntityData.defineId(EnderDragonHatchlingEntity.class, EntityDataSerializers.BOOLEAN);

    public EnderDragonHatchlingEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public final AnimationState idleAnimationState = new AnimationState(), attackAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0, attackAnimationTimeout = 0;

    /**
     * Runs every tick the entity is loaded
     */
    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide)
        {
            setUpAnimationStates();
        }
    }

    @Override
    public void setUpAnimationStates()
    {
        if (this.idleAnimationTimeout <= 0)
        {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        } else
        {
            this.idleAnimationTimeout--;
        }

        if (this.isAttacking() && attackAnimationTimeout <= 0)
        {
            attackAnimationTimeout = 40;
            attackAnimationState.start(this.tickCount);
        } else
        {
            this.attackAnimationTimeout--;
        }

        if (!this.isAttacking())
        {
            attackAnimationState.stop();
        }
    }

    /**
     * Updates the walking animation for this entity
     * @param pPartialTick
     */
    @Override
    protected void updateWalkAnimation(float pPartialTick) {
        float f;
        if (this.getPose() == Pose.STANDING)
        {
            f = Math.min(pPartialTick * 6F, 1F);
        } else
        {
            f = 0;
        }
        this.walkAnimation.update(f, 0.2F);
    }

    @Override
    public void setAttacking(boolean attacking) {
        this.entityData.set(IS_CHARGING, attacking);
    }

    @Override
    public boolean isAttacking() {
        return this.entityData.get(IS_CHARGING);
    }

    /**
     * Defines the synched data when the entity is initially loaded
     */
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IS_CHARGING, false);
    }

    /**
     * Registers the goals and pathfinders for this entity to follow
     */
    @Override
    protected void registerGoals() {

        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(1, new EnderDragonHatchlingAttackGoal(this, 60));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, VoidPirateEntity.class, 7.0F, 1.5, 1.7));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 0.6D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 3F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(8, new RandomStrollGoal(this, 0.8D));

        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Endermite.class, true));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)));
    }

    /**
     * The ambient sound of the entity. Randomly plays while the entity is loaded
     * @return A SoundEvent representing the ambient sound
     */
    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENDER_DRAGON_AMBIENT;
    }

    /**
     * The hurt sound of the entity. Plays when the entity takes damage
     * @param pDamageSource The source of damage the entity receives
     * @return A SoundEvent representing the hurt sound
     */
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.ENDER_DRAGON_HURT;
    }

    /**
     * The death sound of the entity. Plays when the entity is slain
     * @return A SoundEvent representing the death sound
     */
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENDER_DRAGON_GROWL;
    }

    /**
     * Determines if the entity is immune to fire damage
     * @return True if immune to fire, false if not
     */
    @Override
    public boolean fireImmune() {
        return true;
    }

    /**
     * Determines if the entity can drown in fluids
     * @param type The FluidType the entity would be present in
     * @return True if drowning is applicable, false if not
     */
    @Override
    public boolean canDrownInFluidType(FluidType type) {
        return false;
    }

    /**
     * Determines if the entity can take fall damage
     * @param pFallDistance the distance the entity falls
     * @param pMultiplier The multiplier of the fall damage
     * @param pSource The damage source
     * @return True if fall damage is applicable, false if not
     */
    @Override
    public boolean causeFallDamage(float pFallDistance, float pMultiplier, DamageSource pSource) {
        return false;
    }

    /**
     * Creates all attributes the entity needs
     * @return An AttributeSupplier.Builder containing all the attributes for the entity
     */
    public static AttributeSupplier.Builder createAttributes()
    {
        return Mob.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 40D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.ATTACK_DAMAGE, 8D)
                .add(Attributes.ATTACK_KNOCKBACK, 0.2D)
                .add(Attributes.FOLLOW_RANGE, 50D);
    }
}
