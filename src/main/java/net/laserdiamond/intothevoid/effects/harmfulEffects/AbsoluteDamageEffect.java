package net.laserdiamond.intothevoid.effects.harmfulEffects;

import net.laserdiamond.intothevoid.effects.ITVEffects;
import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

/**
 * Class that contains the functionality of the Absolute Damage Effect. Similar functionality to that of the Instant Damage effect, but damages ANY mob
 */
public class AbsoluteDamageEffect extends InstantenousMobEffect {
    public AbsoluteDamageEffect(int pColor) {
        super(MobEffectCategory.HARMFUL, pColor);
    }

    /**
     * Runs when the effect is inflicted on a living entity
     * @param pLivingEntity The LivingEntity receiving the effect
     * @param pAmplifier The amplifier of the effect (how strong the effect is)
     */
    @Override
    public void applyEffectTick(@NotNull LivingEntity pLivingEntity, int pAmplifier) {
        if (this == ITVEffects.ABSOLUTE_DAMAGE.get()) // Hurt any living entity that gains this effect (damage received is based on the amplifier)
        {
            pLivingEntity.hurt(pLivingEntity.damageSources().magic(), (float) (6 << pAmplifier));
        }
    }
}
