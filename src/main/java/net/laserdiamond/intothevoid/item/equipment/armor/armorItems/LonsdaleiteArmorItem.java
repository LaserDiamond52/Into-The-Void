package net.laserdiamond.intothevoid.item.equipment.armor.armorItems;

import net.laserdiamond.intothevoid.item.equipment.armor.ITVArmorItem;
import net.laserdiamond.intothevoid.item.equipment.armor.ITVArmorMaterials;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ArmorMaterial;

import java.util.List;
import java.util.UUID;

/**
 * Class that represents a Lonsdaleite Armor Item. Any properties that Lonsdaleite armor should have should be done through here.
 */
public final class LonsdaleiteArmorItem extends ITVArmorItem {

    public LonsdaleiteArmorItem(Type pType, Properties pProperties) {
        super(ITVArmorMaterials.LONSDALEITE, pType, pProperties);
    }

    @Override
    public boolean simpleArmorItem() {
        return true;
    }

    @Override
    protected double[] speedAmount() {
        return new double[]{0.025,0.025,0.025,0.025};
    }
}
