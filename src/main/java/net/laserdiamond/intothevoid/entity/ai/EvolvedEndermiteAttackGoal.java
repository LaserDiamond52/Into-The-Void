package net.laserdiamond.intothevoid.entity.ai;

import net.laserdiamond.intothevoid.effects.ITVEffects;
import net.laserdiamond.intothevoid.entity.itv.EvolvedEndermiteEntity;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class EvolvedEndermiteAttackGoal extends MeleeAttackGoal {

    private final EvolvedEndermiteEntity endermiteEntity;
    private int attackDelay = 25;
    private int ticksUntilNextAttack = 25;
    private int jumpAttackDelay = 20;
    private int ticksUntilNextJumpAttack = 20;
    private boolean shouldCountUntilNextAttack = false;
    private boolean shouldCountUntilNextJumpAttack = false;

    public EvolvedEndermiteAttackGoal(PathfinderMob pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
        super(pMob, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
        endermiteEntity = (EvolvedEndermiteEntity) pMob;
    }

    /**
     * Runs when the entity is spawned or loaded
     */
    @Override
    public void start() {
        super.start();
        attackDelay = 25;
        ticksUntilNextAttack = 25;
        jumpAttackDelay = 20;
        ticksUntilNextJumpAttack = 20;
    }

    /**
     * Runs when the entity is alive/loaded. Calls the parent method for most functionality
     */
    @Override
    public void tick() {
        super.tick();
        Level level = endermiteEntity.level();
        if (shouldCountUntilNextAttack)
        {
            this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
        }

        if (shouldCountUntilNextJumpAttack)
        {
            this.ticksUntilNextJumpAttack = Math.max(this.ticksUntilNextJumpAttack - 1, 0);
        }

        if (endermiteEntity.isJumpAttacking())
        {
            for (Entity entity : level.getEntities(endermiteEntity, endermiteEntity.getBoundingBox().expandTowards(5.0D, 5.0D, 5.0D)))
            {
                if (entity instanceof LivingEntity livingEntity)
                {
                    if (!(livingEntity instanceof EvolvedEndermiteEntity))
                    {
                        livingEntity.addEffect(new MobEffectInstance(ITVEffects.NECROSIS_EFFECT.get(), 200, 0), endermiteEntity);
                    }
                }
            }
        }
    }

    /**
     * Runs when the entity dies or is unloaded
     */
    @Override
    public void stop() {
        endermiteEntity.setAttacking(false);
        endermiteEntity.setJumpAttacking(false);
        super.stop();
    }

    /**
     * checks if the enemy is in range and that the entity is able to attack them
     * @param pEnemy The enemy of the entity
     * @param pDistToEnemySqr The distance to the entity squared
     */
    @Override
    protected void checkAndPerformAttack(LivingEntity pEnemy, double pDistToEnemySqr) {

        if (isEnemyInRange(pEnemy, pDistToEnemySqr))
        {
            shouldCountUntilNextAttack = true;

            if (isTimeToStartAttackAnimation())
            {
                endermiteEntity.setAttacking(true);
            }

            if (isTimeToAttack())
            {
                this.mob.getLookControl().setLookAt(pEnemy.getX(), pEnemy.getEyeY(), pEnemy.getZ());
                attackTarget(pEnemy);
            }
        } else
        {
            resetAttackCooldown();
            endermiteEntity.setAttacking(false);
            shouldCountUntilNextAttack = false;
            endermiteEntity.attackAnimationTimeout = 0;

            if (isEnemyInJumpingRange(pEnemy))
            {
                shouldCountUntilNextJumpAttack = true;

                if (isTimeToStartJumpAnimation())
                {
                    endermiteEntity.setJumpAttacking(true);
                }

                if (isTimeToJumpAttack())
                {
                    jumpAttackTarget(pEnemy);
                }
            } else
            {
                resetJumpAttackCooldown();
                endermiteEntity.setJumpAttacking(false);
                shouldCountUntilNextJumpAttack = false;
                endermiteEntity.jumpAttackAnimationTimeout = 0;
            }
        }
    }

    /**
     * Checks if the enemy is in range of the entity
     * @param target The target of the entity
     * @param distToTargetSqr Distance from the target to teh entity
     * @return true if the target is in range, false otherwise
     */
    private boolean isEnemyInRange(LivingEntity target, double distToTargetSqr)
    {
        return distToTargetSqr <= this.getAttackReachSqr(target);
    }

    /**
     * Checks if the enemy is in jumping range of the entity
     * @param target The target of the entity
     * @return True if in range, false otherwise
     */
    private boolean isEnemyInJumpingRange(LivingEntity target)
    {
        double distToTarget = this.endermiteEntity.distanceToSqr(target);
        return distToTarget <= 1200 && distToTarget >= 40;
    }

    /**
     * Resets the attack cooldown for the entity
     */
    @Override
    protected void resetAttackCooldown() {
        this.ticksUntilNextAttack = this.adjustedTickDelay(attackDelay + 17);
    }

    /**
     * Resets the jump attack cooldown for the entity
     */
    protected void resetJumpAttackCooldown()
    {
        this.ticksUntilNextJumpAttack = this.adjustedTickDelay(jumpAttackDelay + 40);
    }

    /**
     * Checks if it is time for the entity to attack
     * @return True if ticksUntilNextAttack is less than or equal to 0, false otherwise
     */
    @Override
    protected boolean isTimeToAttack() {
        return this.ticksUntilNextAttack <= 0;
    }

    /**
     * Checks if it is time for the entity to jump attack
     * @return True if ticksUntilNextJumpAttack is less than or equal to 0, false otherwise
     */
    protected boolean isTimeToJumpAttack()
    {
        return this.ticksUntilNextJumpAttack <= 0;
    }

    /**
     * Checks if it is time to start the attack animation
     * @return True if ticksUntilNextAttack is less than or equal to attackDelay, false otherwise
     */
    protected boolean isTimeToStartAttackAnimation()
    {
        return this.ticksUntilNextAttack <= attackDelay;
    }

    /**
     * Checks if it is time to start the jump attack animation
     * @return True if ticksUntilNextJumpAttack is less than or equal to jumpAttackDelay, false otherwise
     */
    protected boolean isTimeToStartJumpAnimation()
    {
        return this.ticksUntilNextJumpAttack <= jumpAttackDelay;
    }

    /**
     * Gets the ticks until the next attack
     * @return The ticks until the next attack as an int
     */
    @Override
    public int getTicksUntilNextAttack() {
        return this.ticksUntilNextAttack;
    }

    /**
     * Attacks the target
     * @param target The living entity to attack
     */
    protected void attackTarget(LivingEntity target)
    {
        this.resetAttackCooldown();
        this.mob.swing(InteractionHand.MAIN_HAND);
        this.mob.doHurtTarget(target);
    }

    /**
     * Performs a jump attack towards the target
     * @param target The living entity to attack
     */
    protected void jumpAttackTarget(LivingEntity target)
    {
        this.resetJumpAttackCooldown();

        target.sendSystemMessage(Component.literal("Jumping towards target!"));
        Vec3 endermiteEntityLookAngle = endermiteEntity.getLookAngle();
        double x = endermiteEntityLookAngle.x;
        double z = endermiteEntityLookAngle.z;

        final double jumpStrength = 3.0;
        Vec3 deltaMovement = endermiteEntity.getDeltaMovement();
        Vec3 launchDirection = (new Vec3(x, 0.0, z)).normalize().scale(jumpStrength);
        endermiteEntity.setDeltaMovement(deltaMovement.x / 2.0 + launchDirection.x, Math.min(0.4, deltaMovement.y / 2.0 + jumpStrength), deltaMovement.z / 2.0 + launchDirection.z);


        endermiteEntity.level().playSound(null, endermiteEntity.getOnPos(), SoundEvents.WITHER_SHOOT, SoundSource.HOSTILE, 5, 5);
    }
}
