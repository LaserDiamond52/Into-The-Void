package net.laserdiamond.intothevoid.entity.ai;

import net.laserdiamond.intothevoid.entity.itv.WatcherMinionEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;

public class WatcherAttackGoal extends Goal {

    private final WatcherMinionEntity watcherMinionEntity;
    private int attackTimer;

    public WatcherAttackGoal(WatcherMinionEntity watcherMinionEntity)
    {
        this.watcherMinionEntity = watcherMinionEntity;

    }

    @Override
    public boolean canUse() {
        LivingEntity target = this.watcherMinionEntity.getTarget();
        return target != null && target.isAlive();
    }

    @Override
    public boolean canContinueToUse() {
        return super.canContinueToUse() && (this.watcherMinionEntity.getTarget() != null);
    }

    @Override
    public void start() {
        this.attackTimer = 0;
        this.watcherMinionEntity.getNavigation().stop();
        LivingEntity target = this.watcherMinionEntity.getTarget();
        if (target != null)
        {
            this.watcherMinionEntity.getLookControl().setLookAt(target, 90.0F, 90.0F);
        }

        this.watcherMinionEntity.hasImpulse = true;
    }

    @Override
    public void stop() {
        this.watcherMinionEntity.setActiveAttackTarget(0);
        this.watcherMinionEntity.setTarget(null);
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        LivingEntity target = this.watcherMinionEntity.getTarget();
        if (target != null)
        {
            this.watcherMinionEntity.getNavigation().stop();
            this.watcherMinionEntity.getLookControl().setLookAt(target, 90F, 90F);
            if (!this.watcherMinionEntity.hasLineOfSight(target))
            {
                this.watcherMinionEntity.setTarget(null);
            } else
            {
                this.attackTimer++;
                if (this.attackTimer == 10)
                {
                    this.watcherMinionEntity.setActiveAttackTarget(target.getId());
                } else if (this.attackTimer >= this.watcherMinionEntity.getAttackDuration())
                {
                    float magicDamage = 2;

                    target.hurt(this.watcherMinionEntity.damageSources().indirectMagic(this.watcherMinionEntity, this.watcherMinionEntity), magicDamage);
                    target.hurt(this.watcherMinionEntity.damageSources().mobAttack(this.watcherMinionEntity), (float) this.watcherMinionEntity.getAttributeValue(Attributes.ATTACK_DAMAGE));
                    this.watcherMinionEntity.setTarget(null);
                }
            }
        }
        super.tick();
    }
}
