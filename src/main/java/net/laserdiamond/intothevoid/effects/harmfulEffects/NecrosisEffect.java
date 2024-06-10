package net.laserdiamond.intothevoid.effects.harmfulEffects;

import net.laserdiamond.intothevoid.effects.ITVEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class NecrosisEffect extends MobEffect {

    public NecrosisEffect(int pColor) {
        super(MobEffectCategory.HARMFUL, pColor);
        MinecraftForge.EVENT_BUS.register(this);
    }

    /**
     * Necrosis functionality applied every tick
     * @param pLivingEntity The Living Entity affected
     * @param pAmplifier The amplifier of the Necrosis
     */
    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {

        if (this == ITVEffects.NECROSIS_EFFECT.get())
        {
            pLivingEntity.hurt(pLivingEntity.damageSources().magic(), 1.0F);
        }
    }

    /**
     * The rate at which the effect tick is applied. Happens at a similar rate as Wither effect. May remove this in order to make Necrosis less fatal
     * @param pDuration The duration of the Necrosis
     * @param pAmplifier the amplifier of the Necrosis
     * @return
     */
    @Deprecated
    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        int i;
        if (this == ITVEffects.NECROSIS_EFFECT.get())
        {
            i = 40 >> pAmplifier;
            if (i > 0)
            {
                return pDuration % i == 0;
            } else
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Increases the damage the victim will receive from having Necrosis. Runs after the final damage has been calculated
     * @param event LivingDamageEvent (called when a living entity takes damage)
     */
    @SuppressWarnings("unused")
    @SubscribeEvent
    protected void increaseDamage(LivingDamageEvent event)
    {
        LivingEntity victim = event.getEntity();
        MobEffectInstance necrosis = victim.getEffect(ITVEffects.NECROSIS_EFFECT.get());
        if (victim.hasEffect(ITVEffects.NECROSIS_EFFECT.get()) && necrosis != null)
        {
            float damage = event.getAmount();
            float amplifier = necrosis.getAmplifier() + 1;

            float multiplier = 1 + (amplifier / (amplifier + 4));

            event.setAmount(multiplier * damage);
        }
    }

    /**
     * Cancels any healing the victim with receive while Necrosis is active
     * @param event LivingHealEvent (called when a living entity heals)
     */
    @SuppressWarnings("unused")
    @SubscribeEvent
    protected void stopHealing(LivingHealEvent event)
    {
        LivingEntity victim = event.getEntity();

        if (victim.hasEffect(ITVEffects.NECROSIS_EFFECT.get()))
        {
            event.setCanceled(true);
        }
    }

    // TODO: Figure out how to render Necrosis hearts over default ones

}
