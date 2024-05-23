package net.laserdiamond.intothevoid.entity.projectiles.DragonFireballs;

import net.laserdiamond.intothevoid.effects.ITVEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import java.util.List;

public class DragonHatchlingFireball extends AbstractITVDragonFireball {

    public DragonHatchlingFireball(Level pLevel, LivingEntity pShooter, double pOffsetX, double pOffsetY, double pOffsetZ) {
        super(pLevel, pShooter, pOffsetX, pOffsetY, pOffsetZ);
    }

    @Override
    protected float initialDamage() {
        return 8.0F;
    }

    @Override
    protected float initialRadius() {
        return 1.5F;
    }

    @Override
    protected int cloudDuration() {
        return 450;
    }

    @Override
    protected List<MobEffectInstance> effects() {
        return List.of(new MobEffectInstance(ITVEffects.DRAGON_DAMAGE_EFFECT.get(), 1, 1));
    }
}
