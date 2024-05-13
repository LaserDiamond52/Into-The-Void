package net.laserdiamond.intothevoid.item.equipment.armor.armorItems;

import net.laserdiamond.intothevoid.item.equipment.armor.ITVArmorItem;
import net.laserdiamond.intothevoid.item.equipment.armor.ITVArmorMaterials;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

import java.util.List;

public final class DragonborneArmorItem extends ITVArmorItem {
    public DragonborneArmorItem(Type pType, Properties pProperties) {
        super(ITVArmorMaterials.DRAGONBORNE, pType, pProperties);
    }

    @Override
    protected double[] meleeDamageAmount() {
        return new double[]{0.05,0.05,0.05,0.05};
    }

    @Override
    public boolean simpleArmorItem() {
        return true;
    }

    @Override
    public List<MobEffectInstance> armorEffects() {
        //List<MobEffectInstance> effects = new ArrayList<>();
        effects.add(new MobEffectInstance(MobEffects.JUMP, 100, 0, false, false, true));
        effects.add(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 100, 0, false, false, true));
        return super.armorEffects();
    }
}
