package net.laserdiamond.intothevoid.entity.projectiles.DragonFireballs;

import net.laserdiamond.intothevoid.effects.ITVEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;

import java.util.List;

/**
 * A Dragon Fireball is fired from the Dragonborne Sword
 */
public class DragonborneFireball extends AbstractITVDragonFireball {

    public DragonborneFireball(Level pLevel, LivingEntity pShooter, double pOffsetX, double pOffsetY, double pOffsetZ) {
        super(pLevel, pShooter, pOffsetX, pOffsetY, pOffsetZ);
    }

    @Override
    protected float initialDamage() {
        return 10.0F;
    }

    @Override
    protected List<MobEffectInstance> effects() {
        return List.of(new MobEffectInstance(ITVEffects.ABSOLUTE_DAMAGE.get(), 1, 1));
    }
}
