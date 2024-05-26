package net.laserdiamond.intothevoid.entity.ai;

import net.laserdiamond.intothevoid.entity.itv.VoidPirateEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

/**
 * The Attack Goal of the Void Pirate
 */
public class VoidPirateAttackGoal extends MeleeAttackGoal {

    private final VoidPirateEntity pirateEntity;
    private int attackDelay = 8;
    private int ticksUntilNextAttack = 8;
    private boolean shouldCountTillNextAttack = false;
    public VoidPirateAttackGoal(PathfinderMob pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
        super(pMob, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
        pirateEntity = (VoidPirateEntity) pMob;
    }

    /**
     * Runs when the entity is spawned or loaded
     */
    @Override
    public void start() {
        super.start();
        attackDelay = 8;
        ticksUntilNextAttack = 8;
    }

    /**
     * Runs for every tick the entity is alive/loaded. Calls the parent method for most functionality
     */
    @Override
    public void tick() {
        super.tick(); // Call parent method
        if (shouldCountTillNextAttack)
        {
            this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
        }
    }

    /**
     * Runs when the entity dies or is unloaded
     */
    @Override
    public void stop() {
        pirateEntity.setAttacking(false);
        super.stop();
    }

    /**
     * Checks if the enemy is in range and that the entity is able to attack them
     * @param pEnemy The enemy of the entity
     * @param pDistToEnemySqr The distance to the entity squared
     */
    @Override
    protected void checkAndPerformAttack(LivingEntity pEnemy, double pDistToEnemySqr) {

        if (isEnemyInRange(pEnemy, pDistToEnemySqr)) // Check if target is in range
        {
            shouldCountTillNextAttack = true;

            if (isTimeToStartAttackAnimation()) // If time to start the animation, entity is ready to attack
            {
                pirateEntity.setAttacking(true);
            }

            if (isTimeToAttack())
            {
                this.mob.getLookControl().setLookAt(pEnemy.getX(), pEnemy.getEyeY(), pEnemy.getZ()); // Look at target
                attackTarget(pEnemy); // Attack!
            }
        } else // Enemy is no longer in range
        {
            // Reset attack
            resetAttackCooldown();
            shouldCountTillNextAttack = false;
            pirateEntity.setAttacking(false);
            pirateEntity.attackAnimationTimeout = 0;
        }
    }

    /**
     * Checks if the enemy is in range of the entity
     * @param target The target of the entity
     * @param distToTargetSqr Distance from the target to the entity
     * @return true if target is in range, false otherwise
     */
    private boolean isEnemyInRange(LivingEntity target, double distToTargetSqr)
    {
        return distToTargetSqr <= this.getAttackReachSqr(target);
    }

    /**
     * Resets the attack cooldown for the entity
     */
    @Override
    protected void resetAttackCooldown() {
        this.ticksUntilNextAttack = this.adjustedTickDelay(attackDelay * 2);
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
     * Checks if it is time to start the attack animation
     * @return True if ticksUntilNextAttack is less than or qual to attackDelay, false otherwise
     */
    protected boolean isTimeToStartAttackAnimation()
    {
        return this.ticksUntilNextAttack <= attackDelay;
    }

    /**
     * Gets the ticks until the next attack
     * @return the ticks until the next attack as an int
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
}
