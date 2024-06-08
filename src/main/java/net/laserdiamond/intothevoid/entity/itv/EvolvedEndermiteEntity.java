package net.laserdiamond.intothevoid.entity.itv;

import net.laserdiamond.intothevoid.entity.ai.AttackingEntity;
import net.laserdiamond.intothevoid.entity.ai.EvolvedEndermiteAttackGoal;
import net.laserdiamond.intothevoid.entity.animations.SetupAnimationState;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class EvolvedEndermiteEntity extends Monster implements SetupAnimationState, AttackingEntity {

    private static final EntityDataAccessor<Boolean> IS_ATTACKING = SynchedEntityData.defineId(EvolvedEndermiteEntity.class, EntityDataSerializers.BOOLEAN);

    public EvolvedEndermiteEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public final AnimationState idleAnimationState = new AnimationState(), attackAnimationState = new AnimationState();
    public int idleAnimationTimeout = 0, attackAnimationTimeout = 0;

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

    /**
     * Updates the walk animation
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
        this.entityData.set(IS_ATTACKING, attacking);
    }

    @Override
    public boolean isAttacking() {
        return this.entityData.get(IS_ATTACKING);
    }

    /**
     * Defines the synched data when the entity is initially loaded
     */
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IS_ATTACKING, false);
    }

    @Override
    public void setUpAnimationStates() {

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
     * Registers all the goals and pathfinders for this entity to follow
     */
    @Override
    protected void registerGoals() {

        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(1, new EvolvedEndermiteAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, EnderMan.class, 7.0F, 1.5, 1.7));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 0.6D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 3F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(8, new RandomStrollGoal(this, 1D));

        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Silverfish.class, true));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)));

    }

    /**
     * The ambient sound of the entity. Randomly plays while the entity is loaded
     * @return A SoundEvent representing the ambient sound
     */
    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENDERMITE_AMBIENT;
    }

    /**
     * The hurt sound of the entity. Plays when the entity takes damage
     * @param pDamageSource The source of damage the entity receives
     * @return A SoundEvent representing the hurt sound
     */
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.ENDERMITE_HURT;
    }

    /**
     * The death sound of the entity. Plays when the entity is slain
     * @return A SoundEvent representing the death sound
     */
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENDERMITE_DEATH;
    }

    /**
     * Creates all attributes the entity needs
     * @return An AttributeSupplier.Builder containing all the attributes for the entity
     */
    public static AttributeSupplier.Builder createAttributes()
    {
        return Mob.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 80D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.ATTACK_DAMAGE, 12D)
                .add(Attributes.ATTACK_KNOCKBACK, 0.3D)
                .add(Attributes.FOLLOW_RANGE, 60D);
    }
}
