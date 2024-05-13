package net.laserdiamond.intothevoid.entity.projectiles;

import net.laserdiamond.intothevoid.effects.ITVEffects;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.DragonFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.List;

public class DragonborneFireball extends DragonFireball {

    private LivingEntity shooter;

    public DragonborneFireball(Level pLevel, LivingEntity pShooter, double pOffsetX, double pOffsetY, double pOffsetZ)
    {
        super(pLevel, pShooter, pOffsetX, pOffsetY, pOffsetZ);
        this.shooter = pShooter;
    }

    /**
     * When the Dragonborne fireball hits a mob/block. Functionality is mostly the same as the vanilla dragon fireball, except that the absolute damage effect is applied instead of instant harming, and there is impact damage for if a mob is hit wit the fireball
     * @param pResult The HitResult
     */
    @Override
    protected void onHit(HitResult pResult) {
        if (pResult instanceof EntityHitResult entityHitResult)
        {
            if (!this.level().isClientSide)
            {
                Entity hitEntity = entityHitResult.getEntity();
                DamageSource damageSource = this.damageSources().mobAttack(shooter);
                if (hitEntity instanceof LivingEntity hitLivingEntity)
                {
                    hitLivingEntity.hurt(damageSource, 10.0F);
                }
            }
        }
        if (pResult.getType() != HitResult.Type.ENTITY || !this.ownedBy(((EntityHitResult) pResult).getEntity())) // Code here is mostly the same as the vanilla code for the dragon fireball
        {
            if (!this.level().isClientSide)
            {
                List<LivingEntity> targets = this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(4.0,2.0,4.0));
                AreaEffectCloud cloud = new AreaEffectCloud(this.level(), this.getX(), this.getY(), this.getZ());
                Entity owner = this.getOwner();
                if (owner instanceof LivingEntity)
                {
                    cloud.setOwner(((LivingEntity) owner));
                }

                cloud.setParticle(ParticleTypes.DRAGON_BREATH);
                cloud.setRadius(3.0F);
                cloud.setDuration(600);
                cloud.setRadiusPerTick((7.0F - cloud.getRadius()) / (float) cloud.getDuration());
                cloud.addEffect(new MobEffectInstance(ITVEffects.ABSOLUTE_DAMAGE.get(), 1,1));
                if (!targets.isEmpty())
                {
                    for (LivingEntity target : targets)
                    {
                        double distanceToSqr = this.distanceToSqr(target);
                        if (distanceToSqr < 16.0)
                        {
                            cloud.setPos(target.getX(), target.getY(), target.getZ());
                            break;
                        }
                    }
                }
                this.level().levelEvent(2006, this.blockPosition(), this.isSilent() ? -1 : 1);
                this.level().addFreshEntity(cloud);
                this.discard();
            }
        }
    }

    public LivingEntity getShooter() {
        return shooter;
    }

    public void setShooter(LivingEntity shooter) {
        this.shooter = shooter;
    }
}
