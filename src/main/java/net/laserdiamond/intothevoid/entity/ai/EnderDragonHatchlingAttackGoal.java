package net.laserdiamond.intothevoid.entity.ai;

import net.laserdiamond.intothevoid.entity.itv.EnderDragonHatchlingEntity;
import net.laserdiamond.intothevoid.entity.projectiles.DragonFireballs.DragonHatchlingFireball;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

/**
 * The attack goal of the Ender Dragon Hatchling
 */
public class EnderDragonHatchlingAttackGoal extends Goal {

    private final EnderDragonHatchlingEntity dragonHatchlingEntity;
    private final int maxDistFromTarget;
    private final int attackDelay = 20;
    private final int maxAttackChargeTime = attackDelay * 2;
    private int attackChargeTime = 0;

    public EnderDragonHatchlingAttackGoal(EnderDragonHatchlingEntity dragonHatchlingEntity, int maxDistFromTarget)
    {
        this.dragonHatchlingEntity = dragonHatchlingEntity;
        this.maxDistFromTarget = maxDistFromTarget;
    }

    protected void launchDragonFireball(LivingEntity target)
    {
        Level level = this.dragonHatchlingEntity.level();
        Vec3 dragonView = dragonHatchlingEntity.getLookAngle();
        double x = dragonHatchlingEntity.getX();
        double y = dragonHatchlingEntity.getEyeY();
        double z = dragonHatchlingEntity.getZ();
        DragonHatchlingFireball dragonFireball = new DragonHatchlingFireball(level, dragonHatchlingEntity, dragonView.x, dragonView.y, dragonView.z);
        dragonFireball.setPos(x, y, z);
        level.addFreshEntity(dragonFireball);
    }

    /**
     * Returns if the entity has a target or not
     * @return True if there is a target, false if not
     */
    @Override
    public boolean canUse() {
        return this.dragonHatchlingEntity.getTarget() != null;
    }

    /**
     * Runs when the entity is spawned or loaded
     */
    @Override
    public void start() {
        super.start();
        this.attackChargeTime = 0;
        this.dragonHatchlingEntity.setAggressive(true);
    }

    /**
     * Runs when the entity dies or is unloaded
     */
    @Override
    public void stop() {
        super.stop();
        this.dragonHatchlingEntity.setAttacking(false);
        this.dragonHatchlingEntity.setAggressive(false);
        this.dragonHatchlingEntity.getNavigation().stop();
    }

    /**
     * Runs every tick the entity is alive/loaded. Responsible for most of the AI
     */
    @Override
    public void tick() {
        LivingEntity target = this.dragonHatchlingEntity.getTarget();
        if (target == null)
        {
            this.attackChargeTime = 0;
            this.dragonHatchlingEntity.setAttacking(false);
            return;
        }
        double distToTarget = this.dragonHatchlingEntity.distanceToSqr(target);
        if (distToTarget >= this.maxDistFromTarget)
        {
            this.dragonHatchlingEntity.getNavigation().moveTo(target, 1.2);
        } else
        {
            this.dragonHatchlingEntity.getNavigation().stop();
        }

        if (isEnemyInRange(target))
        {
            this.dragonHatchlingEntity.getLookControl().setLookAt(target.getX(), target.getEyeY(), target.getZ(), 30.0F, 30.0F);
            this.dragonHatchlingEntity.setAttacking(true);
            this.attackChargeTime++;
            if (this.attackChargeTime >= attackDelay)
            {
                if (attackChargeTime == attackDelay)
                {
                    launchDragonFireball(target);
                } else if (this.attackChargeTime >= maxAttackChargeTime)
                {
                    this.attackChargeTime = 0;
                }
            }
        } else if (this.attackChargeTime > 0)
        {
            this.attackChargeTime = 0;
            this.dragonHatchlingEntity.setAttacking(false);
        }
    }

    /**
     * Checks if the target is in range
     * @param target The target of the entity
     * @return True if in range, false if not
     */
    private boolean isEnemyInRange(LivingEntity target)
    {
        return this.dragonHatchlingEntity.distanceToSqr(target) <= 400;
    }
}
