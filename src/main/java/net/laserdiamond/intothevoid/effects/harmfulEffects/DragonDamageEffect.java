package net.laserdiamond.intothevoid.effects.harmfulEffects;

import net.laserdiamond.intothevoid.effects.ITVEffects;
import net.laserdiamond.intothevoid.entity.itv.EnderDragonHatchlingEntity;
import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;

public class DragonDamageEffect extends InstantenousMobEffect {

    public DragonDamageEffect(int pColor) {
        super(MobEffectCategory.HARMFUL, pColor);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if (this == ITVEffects.DRAGON_DAMAGE_EFFECT.get())
        {
            if (pLivingEntity instanceof EnderDragon)
            {
                return;
            } else if (pLivingEntity instanceof EnderDragonHatchlingEntity)
            {
                return;
            }
            pLivingEntity.hurt(pLivingEntity.damageSources().dragonBreath(), (float) (6 << pAmplifier));

        }
    }
}
