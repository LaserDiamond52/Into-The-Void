package net.laserdiamond.intothevoid.entity.projectiles.DragonFireballs;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.DragonFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.List;

/**
 * An abstract class representing a Dragon Fireball for use with this mod. Functionally, this is the same as a typical Dragon Fireball, but a few extra options are more open to customization
 * <li>Initial Damage: Set the initial damage when the Dragon Fireball hits an entity</li>
 * <li>Initial Radius: Set the initial radius when the Dragon Fireball lands</li>
 * <li>Toggleable Area Effect Cloud: Set whether an area effect cloud should spawn after impact. Returning this to false will make this effectively behave similarly to a snowball</li>
 * <li>Cloud Duration: Set the duration that the Area Effect Cloud should last for</li>
 * <li>Status Effects: Choose the status effects that the Area Effect Cloud should apply to targets</li>
 * <li>Particle: Choose the particle that the Area Effect Cloud should spawn</li>
 */
public abstract class AbstractITVDragonFireball extends DragonFireball {

    private final LivingEntity shooter;

    public AbstractITVDragonFireball(Level pLevel, LivingEntity pShooter, double pOffsetX, double pOffsetY, double pOffsetZ)
    {
        super(pLevel, pShooter, pOffsetX, pOffsetY, pOffsetZ);
        this.shooter = pShooter;
    }

    /**
     * The amount of damage the hit entity will take from being hit
     * @return A float denoting how much damage the hit entity will take
     */
    protected float initialDamage()
    {
        return 0F;
    }

    /**
     * The initial radius of the dragon acid
     * @return A float denoting the initial radius of the dragon acid (default is 3)
     */
    protected float initialRadius()
    {
        return 3.0F;
    }

    /**
     * Determines if the Area Effect Cloud should spawn from the Dragon Fireball landing
     * <p>The Area Effect Cloud is responsible for the spawning of the particles and applying status effects in a puddle/cloud-like shape where the Dragon Fireball landed</p>
     * @return True if an Area Effect Cloud should spawn, false if not (default is true)
     */
    protected boolean applyAreaEffectCloud()
    {
        return true;
    }

    /**
     * The amount of time in ticks the area effect will last
     * @return An int denoting the duration the area effect cloud will last in ticks (default is 600 ticks)
     */
    protected int cloudDuration()
    {
        return 600;
    }

    /**
     * The status effects the area effect cloud from the dragon acid will apply to entities in range
     * @return An ArrayList of the MobEffectInstances to apply to entities (default is Instant Harming 1)
     */
    protected List<MobEffectInstance> effects()
    {
        return List.of(new MobEffectInstance(MobEffects.HARM, 1, 1));
    }

    /**
     * The particles that should spawn in the Area Effect Cloud
     * @return A SimpleParticleType of the particle to spawn in the Area Effect Cloud (default is the Dragon Breath particle)
     */
    protected SimpleParticleType particle()
    {
        return ParticleTypes.DRAGON_BREATH;
    }

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
                    if (initialDamage() > 0)
                    {
                        hitLivingEntity.hurt(damageSource, initialDamage());
                    }
                }
            }
        }
        if (pResult.getType() != HitResult.Type.ENTITY || !this.ownedBy(((EntityHitResult) pResult).getEntity())) // Code here is mostly the same as the vanilla code for the dragon fireball
        {
            if (!this.level().isClientSide)
            {
                if (!applyAreaEffectCloud())
                {
                    return;
                }
                List<LivingEntity> targets = this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(4.0,2.0,4.0));
                AreaEffectCloud cloud = new AreaEffectCloud(this.level(), this.getX(), this.getY(), this.getZ());
                Entity owner = this.getOwner();
                if (owner instanceof LivingEntity)
                {
                    cloud.setOwner(((LivingEntity) owner));
                }

                cloud.setParticle(particle());
                cloud.setRadius(initialRadius());
                cloud.setDuration(cloudDuration());
                cloud.setRadiusPerTick((7.0F - cloud.getRadius()) / (float) cloud.getDuration());

                for (MobEffectInstance effectInstance : effects())
                {
                    cloud.addEffect(effectInstance);
                }

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
}
