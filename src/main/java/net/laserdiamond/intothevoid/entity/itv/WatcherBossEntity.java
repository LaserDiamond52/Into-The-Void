package net.laserdiamond.intothevoid.entity.itv;

import net.laserdiamond.intothevoid.effects.ITVEffects;
import net.laserdiamond.intothevoid.entity.ai.AttackingEntity;
import net.laserdiamond.intothevoid.entity.animations.SetupAnimationState;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
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
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class WatcherBossEntity extends WatcherMinionEntity implements AttackingEntity, SetupAnimationState {

    private static final EntityDataAccessor<Boolean> ATTACKING = SynchedEntityData.defineId(WatcherBossEntity.class, EntityDataSerializers.BOOLEAN);

    public WatcherBossEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    @Override
    public void setAttacking(boolean attacking) {
        this.entityData.set(ATTACKING, attacking);
    }

    @Override
    public boolean isAttacking() {
        return this.entityData.get(ATTACKING);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, false);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide)
        {
            setUpAnimationStates();
        }
    }

    @Override
    public void setUpAnimationStates() {
        if (this.idleAnimationTimeout <= 0)
        {
            this.idleAnimationTimeout = this.random.nextInt(80) + 160;
            this.idleAnimationState.start(this.tickCount);
        } else
        {
            this.idleAnimationTimeout--;
        }
    }

    @Override
    protected void registerGoals() {

        this.goalSelector.addGoal(0, new FloatGoal(this));
        // TODO: Attack goal
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 0.0D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 3F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, false));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(WatcherMinionEntity.class));
    }

    @Override
    public void attack(int tick) {
        // TODO: Override this for custom attacks
        //super.attack(tick);
    }

    /**
     * The ambient sound of the entity. Randomly plays while the entity is loaded
     * @return A SoundEvent representing the ambient sound
     */
    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ELDER_GUARDIAN_AMBIENT;
    }

    /**
     * The hurt sound of the entity. Plays when the entity takes damage
     * @param pDamageSource The source of damage the entity receives
     * @return A SoundEvent representing the hurt sound
     */
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.ELDER_GUARDIAN_HURT;
    }

    /**
     * The death sound of the entity. Plays when the entity is slain
     * @return A SoundEvent representing the death sound
     */
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENDER_DRAGON_DEATH;
    }

    /**
     * Determines if the entity is affected by gravity
     * @return True if affected, false otherwise
     */
    @Override
    public boolean isNoGravity() {
        return true;
    }

    /**
     * List of MobEffectInstances the Watcher boss is immune to
     */
    private static final ArrayList<MobEffectInstance> IMMUNE_EFFECTS = new ArrayList<>();
    static
    {
        IMMUNE_EFFECTS.add(new MobEffectInstance(MobEffects.WITHER));
        IMMUNE_EFFECTS.add(new MobEffectInstance(MobEffects.POISON));
        IMMUNE_EFFECTS.add(new MobEffectInstance(MobEffects.HARM));
        IMMUNE_EFFECTS.add(new MobEffectInstance(MobEffects.LEVITATION));
        IMMUNE_EFFECTS.add(new MobEffectInstance(MobEffects.WEAKNESS));
        IMMUNE_EFFECTS.add(new MobEffectInstance(MobEffects.BLINDNESS));
        IMMUNE_EFFECTS.add(new MobEffectInstance(ITVEffects.DRAGON_DAMAGE_EFFECT.get()));
        IMMUNE_EFFECTS.add(new MobEffectInstance(ITVEffects.NECROSIS_EFFECT.get()));
    }

    /**
     * Determines if the entity can be affected by a MobEffectInstance
     * @param pEffectInstance The potion effect being inflicted on the entity
     * @return True if the effect can be inflicted, false otherwise
     */
    @Override
    public boolean canBeAffected(MobEffectInstance pEffectInstance) {

        if (IMMUNE_EFFECTS.contains(pEffectInstance))
        {
            return false;
        }
        return true;
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {

        // TODO: Create cheap custom weapon that can only damage this mob
        // By default, this method should return false

        // This method only returns true if the mob is
        // hit by the custom weapon

        return super.hurt(pSource, pAmount);
    }

    /**
     * Creates all attributes the entity needs
     * @return An AttributeSupplier.Builder containing all the attributes for the entity
     */
    public static AttributeSupplier.Builder createAttributes()
    {
        return Mob.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 800D)
                .add(Attributes.MOVEMENT_SPEED, 0.0D)
                .add(Attributes.ATTACK_DAMAGE, 30D)
                .add(Attributes.ATTACK_KNOCKBACK, 0.3D)
                .add(Attributes.FOLLOW_RANGE, 50D);
    }
}
