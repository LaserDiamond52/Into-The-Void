package net.laserdiamond.intothevoid.entity.itv;

import net.laserdiamond.intothevoid.entity.ai.AttackingEntity;
import net.laserdiamond.intothevoid.entity.ai.VoidPirateAttackGoal;
import net.laserdiamond.intothevoid.entity.animations.SetupAnimationState;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class VoidPirateEntity extends Monster implements SetupAnimationState, AttackingEntity {

    private static final EntityDataAccessor<Boolean> ATTACKING = SynchedEntityData.defineId(VoidPirateEntity.class, EntityDataSerializers.BOOLEAN);

    public static final ItemStack DEFAULT_HELD_ITEM = new ItemStack(Items.IRON_SWORD);

    public VoidPirateEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;

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
     * Populates the equipment slots of the mob when initially spawned. Current does not work for this entity
     * @param pRandom RandomSource
     * @param pDifficulty The difficulty of the world
     */
    @Override
    protected void populateDefaultEquipmentSlots(RandomSource pRandom, DifficultyInstance pDifficulty) {
        super.populateDefaultEquipmentSlots(pRandom, pDifficulty);
        this.setItemSlot(EquipmentSlot.MAINHAND, DEFAULT_HELD_ITEM);
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
            --this.idleAnimationTimeout;
        }

        if (this.isAttacking() && attackAnimationTimeout <= 0)
        {
            attackAnimationTimeout = 15; // Length in ticks of animation. Hit happens at 7 ticks
            attackAnimationState.start(this.tickCount);
        } else
        {
            --this.attackAnimationTimeout;
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
    public void setAttacking(boolean attacking)
    {
        this.entityData.set(ATTACKING, attacking);
    }

    @Override
    public boolean isAttacking()
    {
        return this.entityData.get(ATTACKING);
    }

    /**
     * Defines the synched data when the entity is initially loaded
     */
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, false);
    }

    /**
     * Registers the goals and pathfinders for this entity to follow
     */
    @Override
    protected void registerGoals() {

        // Use goal selector to add a goal
        // Lower number = higher priority

        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(1, new VoidPirateAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 0.6D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 3F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(8, new RandomStrollGoal(this, 0.6D));

        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, EnderDragonHatchlingEntity.class, true));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(VoidPirateEntity.class));
    }

    /**
     * The ambient sound of the entity. Randomly plays while the entity is loaded
     * @return A SoundEvent representing the ambient sound
     */
    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.VINDICATOR_AMBIENT;
    }

    /**
     * The hurt sound of the entity. Plays when the entity takes damage
     * @param pDamageSource The source of damage the entity receives
     * @return A SoundEvent representing the hurt sound
     */
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.VINDICATOR_HURT;
    }

    /**
     * The death sound of the entity. Plays when the entity is slain
     * @return A SoundEvent representing the death sound
     */
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.VINDICATOR_DEATH;
    }

    /**
     * Creates all attributes the entity needs
     * @return An AttributeSupplier.Builder containing all the attributes for the entity
     */
    public static AttributeSupplier.Builder createAttributes()
    {
        return Mob.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 35D)
                .add(Attributes.MOVEMENT_SPEED, 0.23D)
                .add(Attributes.ATTACK_DAMAGE, 4D)
                .add(Attributes.ATTACK_KNOCKBACK, 0.1D)
                .add(Attributes.FOLLOW_RANGE, 30D);
    }
}
