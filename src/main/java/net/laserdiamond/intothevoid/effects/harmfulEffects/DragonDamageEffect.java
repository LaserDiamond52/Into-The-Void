package net.laserdiamond.intothevoid.effects.harmfulEffects;

import net.laserdiamond.intothevoid.effects.ITVEffects;
import net.laserdiamond.intothevoid.entity.itv.EnderDragonHatchlingEntity;
import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;

/**
 * Class that contains that functionality for the Dragon's Breath Effect. Any entity with the "ender_dragons" tag are immune to this effect
 */
public class DragonDamageEffect extends InstantenousMobEffect {

    public DragonDamageEffect(int pColor) {
        super(MobEffectCategory.HARMFUL, pColor);
    }

    /**
     * Runs when the effect is inflicted on a living entity
     * @param pLivingEntity The LivingEntity receiving the effect
     * @param pAmplifier The amplifier of the effect (how strong the effect is)
     */
    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if (this == ITVEffects.DRAGON_DAMAGE_EFFECT.get())
        {
            // Check if the living entity gaining this effect is either an Ender Dragon or the Hatchling. If so, return the method.
            if (pLivingEntity instanceof EnderDragon)
            {
                return;
            } else if (pLivingEntity instanceof EnderDragonHatchlingEntity)
            {
                return;
            }
            // Otherwise, damage the living entity based on the duration
            pLivingEntity.hurt(pLivingEntity.damageSources().dragonBreath(), (float) (6 << pAmplifier));

        }
    }
}
