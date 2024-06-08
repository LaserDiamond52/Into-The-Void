package net.laserdiamond.intothevoid.entity.ai;

import net.laserdiamond.intothevoid.entity.itv.EvolvedEndermiteEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class EvolvedEndermiteAttackGoal extends MeleeAttackGoal {

    private final EvolvedEndermiteEntity endermiteEntity;
    private int attackDelay = 25;
    private int ticksUntilNextAttack = 25;
    private boolean shouldCountUntilNextAttack = false;
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
    }

    /**
     * Runs when the entity is alive/loaded. Calls the parent method for most functionality
     */
    @Override
    public void tick() {
        super.tick();
        if (shouldCountUntilNextAttack)
        {
            this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
        }
    }

    /**
     * Runs when the entity dies or is unloaded
     */
    @Override
    public void stop() {
        endermiteEntity.setAttacking(false);
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
     * Resets the attack cooldown for the entity
     */
    @Override
    protected void resetAttackCooldown() {
        this.ticksUntilNextAttack = this.adjustedTickDelay(attackDelay + 17);
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
     * @return True if ticksUntilNextAttack is less than or equal to attackDelay, false otherwise
     */
    protected boolean isTimeToStartAttackAnimation()
    {
        return this.ticksUntilNextAttack <= attackDelay;
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
}
