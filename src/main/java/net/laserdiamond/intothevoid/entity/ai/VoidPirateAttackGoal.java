package net.laserdiamond.intothevoid.entity.ai;

import net.laserdiamond.intothevoid.entity.itv.VoidPirateEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class VoidPirateAttackGoal extends MeleeAttackGoal {

    private final VoidPirateEntity pirateEntity;
    private int attackDelay = 8;
    private int ticksUntilNextAttack = 8;
    private boolean shouldCountTillNextAttack = false;
    public VoidPirateAttackGoal(PathfinderMob pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
        super(pMob, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
        pirateEntity = (VoidPirateEntity) pMob;
    }

    @Override
    public void start() {
        super.start();
        attackDelay = 8;
        ticksUntilNextAttack = 8;
    }

    @Override
    public void tick() {
        super.tick();
        if (shouldCountTillNextAttack)
        {
            this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
        }
    }

    @Override
    public void stop() {
        pirateEntity.setAttacking(false);
        super.stop();
    }

    @Override
    protected void checkAndPerformAttack(LivingEntity pEnemy, double pDistToEnemySqr) {

        if (isEnemyInRange(pEnemy, pDistToEnemySqr))
        {
            shouldCountTillNextAttack = true;

            if (isTimeToStartAttackAnimation())
            {
                pirateEntity.setAttacking(true);
            }

            if (isTimeToAttack())
            {
                this.mob.getLookControl().setLookAt(pEnemy.getX(), pEnemy.getEyeY(), pEnemy.getZ());
                attackTarget(pEnemy);
            }
        } else
        {
            resetAttackCooldown();
            shouldCountTillNextAttack = false;
            pirateEntity.setAttacking(false);
            pirateEntity.attackAnimationTimeout = 0;
        }
    }

    private boolean isEnemyInRange(LivingEntity target, double distToTargetSqr)
    {
        return distToTargetSqr <= this.getAttackReachSqr(target);
    }

    @Override
    protected void resetAttackCooldown() {
        this.ticksUntilNextAttack = this.adjustedTickDelay(attackDelay * 2);
    }

    @Override
    protected boolean isTimeToAttack() {
        return this.ticksUntilNextAttack <= 0;
    }

    protected boolean isTimeToStartAttackAnimation()
    {
        return this.ticksUntilNextAttack <= attackDelay;
    }

    @Override
    public int getTicksUntilNextAttack() {
        return this.ticksUntilNextAttack;
    }

    protected void attackTarget(LivingEntity target)
    {
        this.resetAttackCooldown();
        this.mob.swing(InteractionHand.MAIN_HAND);
        this.mob.doHurtTarget(target);
    }
}
