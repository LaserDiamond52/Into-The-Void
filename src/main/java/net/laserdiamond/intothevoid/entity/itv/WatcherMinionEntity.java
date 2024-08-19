package net.laserdiamond.intothevoid.entity.itv;

import net.laserdiamond.intothevoid.entity.ai.WatcherAttackGoal;
import net.laserdiamond.intothevoid.util.RayTrace;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class WatcherMinionEntity extends Monster {

    private static final EntityDataAccessor<Integer> ATTACK_TARGET_ID = SynchedEntityData.defineId(WatcherMinionEntity.class, EntityDataSerializers.INT);
    private int clientSideAttackTime;
    private LivingEntity clientSideCachedAttackTarget;
    private final double stepIncrement = 0.5;
    private final int distance = 2;
    private final boolean pierceBlocks = false;
    private final boolean pierceEntities = true;

    public WatcherMinionEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACK_TARGET_ID, 0);
    }

    public void setActiveAttackTarget(int activeAttackTarget)
    {
        this.entityData.set(ATTACK_TARGET_ID, activeAttackTarget);
    }

    public boolean hasActiveAttackTarget()
    {
        return (Integer) this.entityData.get(ATTACK_TARGET_ID) != 0;
    }

    @Override
    public void aiStep() {
        if (this.isAlive())
        {
            if (this.hasActiveAttackTarget())
            {
                if (this.level().isClientSide)
                {
                    if (this.clientSideAttackTime < this.getAttackDuration())
                    {
                        this.clientSideAttackTime++;
                    }

                    LivingEntity activeTarget = this.getActiveAttackTarget();
                    if (activeTarget != null)
                    {
                        this.getLookControl().setLookAt(activeTarget, 90.0F, 90.0F);
                        this.getLookControl().tick();
                        /*
                        double attackAnimationScale = (double) this.getAttackAnimationScale(0.0F);
                        double xDiff = activeTarget.getX() - this.getX();
                        double yDiff = activeTarget.getY(0.5) - this.getEyeY();
                        double zDiff = activeTarget.getZ() - this.getZ();
                        double $$8 = Math.sqrt(xDiff * xDiff + yDiff * yDiff + zDiff * zDiff);
                        xDiff /= $$8;
                        yDiff /= $$8;
                        zDiff /= $$8;
                        double random = this.random.nextDouble();

                        while (random < $$8)
                        {
                            random += 1.8 - attackAnimationScale + this.random.nextDouble() * (1.7 - attackAnimationScale);

                            this.level().addParticle(ParticleTypes.REVERSE_PORTAL, this.getX() + xDiff * random, this.getEyeY() + yDiff * random, this.getZ() + zDiff * random, 0.0,0.0,0.0);
                        }

                         */

                        //RayTrace.rayTraceToVec(this.level(), this.getEyePosition(), this.getActiveAttackTarget().position().add(0, 1,0), stepIncrement, Optional.of(e -> !(e instanceof WatcherMinionEntity)), LivingEntity.class, distance, pierceBlocks, pierceEntities, ParticleTypes.REVERSE_PORTAL);
                    }
                } else
                {
                    if (this.getActiveAttackTarget() != null)
                    {
                        Iterable<ServerLevel> serverLevels = this.getServer().getAllLevels();
                        for (ServerLevel serverLevel : serverLevels)
                        {
                            if (this.level().equals(serverLevel))
                            {
                                RayTrace.rayTraceToVecEntities(serverLevel, this.getEyePosition(), this.getActiveAttackTarget().position().add(0,this.getActiveAttackTarget().getBbHeight() / 2,0), stepIncrement, Optional.of(e -> !(e instanceof WatcherMinionEntity || e instanceof WatcherBossEntity)), LivingEntity.class, distance, pierceBlocks, pierceEntities, ParticleTypes.REVERSE_PORTAL);
                            }
                        }
                    }
                }

                this.setYRot(this.yHeadRot);
            }

        }
        super.aiStep();
    }

    public LivingEntity getActiveAttackTarget()
    {
        if (!this.hasActiveAttackTarget())
        {
            return null;
        } else if (this.level().isClientSide)
        {
            if (this.clientSideCachedAttackTarget != null)
            {
                return this.clientSideCachedAttackTarget;
            } else
            {
                Entity storedTarget = this.level().getEntity(((Integer) this.entityData.get(ATTACK_TARGET_ID)));
                if (storedTarget instanceof LivingEntity livingEntity)
                {
                    this.clientSideCachedAttackTarget = livingEntity;
                    return this.clientSideCachedAttackTarget;
                } else
                {
                    return null;
                }
            }
        } else
        {
            return this.getTarget();
        }
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> pKey) {
        super.onSyncedDataUpdated(pKey);
        if (ATTACK_TARGET_ID.equals(pKey))
        {
            this.clientSideAttackTime = 0;
            this.clientSideCachedAttackTarget = null;
        }
    }

    public float getAttackAnimationScale(float pPartialTick)
    {
        return ((float) this.clientSideAttackTime + pPartialTick) / (float) this.getAttackDuration();
    }

    public int getAttackDuration()
    {
        return 60;
    }

    public int getClientSideAttackTime() {
        return clientSideAttackTime;
    }

    @Override
    protected void registerGoals() {

        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(2, new WatcherAttackGoal(this));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 0.7D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 3F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(WatcherMinionEntity.class));
    }

    /**
     * The ambient sound of the entity. Randomly plays while the entity is loaded
     * @return A SoundEvent representing the ambient sound
     */
    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.GUARDIAN_AMBIENT;
    }

    /**
     * The hurt sound of the entity. Plays when the entity takes damage
     * @param pDamageSource The source of damage the entity receives
     * @return A SoundEvent representing the hurt sound
     */
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.GUARDIAN_HURT;
    }

    /**
     * The death sound of the entity. Plays when the entity is slain
     * @return A SoundEvent representing the death sound
     */
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ELDER_GUARDIAN_DEATH;
    }

    /**
     * Determines if the entity is immune to fire damage
     * @return True if the entity is immune, false otherwise
     */
    @Override
    public boolean fireImmune() {
        return true;
    }

    /**
     * Determines if the entity is immune to fall damage
     * @param pFallDistance The distance the entity is falling at
     * @param pMultiplier The damage multiplier
     * @param pSource The source of damage
     * @return True if affected, false otherwise
     */
    @Override
    public boolean causeFallDamage(float pFallDistance, float pMultiplier, DamageSource pSource) {
        return false;
    }

    /**
     * Determines if the entity can be affected by freezing
     * @return True if the entity can be affected by freezing, false otherwise
     */
    @Override
    public boolean canFreeze() {
        return false;
    }

    /**
     * Determines if the entity can drown
     * @param type The fluid type the entity is drowning in
     * @return True if the entity can drown, false otherwise
     */
    @Override
    public boolean canDrownInFluidType(FluidType type) {
        return false;
    }

    /**
     * Creates all attributes the entity needs
     * @return An AttributeSupplier.Builder containing all the attributes for the entity
     */
    public static AttributeSupplier.Builder createAttributes()
    {
        return Mob.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 75D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.ATTACK_DAMAGE, 17D)
                .add(Attributes.ATTACK_KNOCKBACK, 0.15D)
                .add(Attributes.FOLLOW_RANGE, 50D);
    }
}
