package net.laserdiamond.intothevoid.effects.harmfulEffects;

import net.laserdiamond.intothevoid.effects.ITVEffects;
import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public class AbsoluteDamageEffect extends InstantenousMobEffect {
    public AbsoluteDamageEffect(int pColor) {
        super(MobEffectCategory.HARMFUL, pColor);
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity pLivingEntity, int pAmplifier) {
        if (this == ITVEffects.ABSOLUTE_DAMAGE.get())
        {
            pLivingEntity.hurt(pLivingEntity.damageSources().magic(), (float) (6 << pAmplifier));
        }
    }
}
