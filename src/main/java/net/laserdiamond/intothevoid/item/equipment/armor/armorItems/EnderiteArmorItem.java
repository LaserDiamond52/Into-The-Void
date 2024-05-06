package net.laserdiamond.intothevoid.item.equipment.armor.armorItems;

import net.laserdiamond.intothevoid.item.equipment.armor.ITVArmorItem;
import net.laserdiamond.intothevoid.item.equipment.armor.ITVArmorMaterials;
import net.minecraft.world.effect.MobEffectInstance;

import java.util.List;
import java.util.UUID;

/**
 * Class that represents an Enderite Armor Item. Any properties that Enderite armor should have should be done through here
 */
public final class EnderiteArmorItem extends ITVArmorItem {

    public EnderiteArmorItem(Type pType, Properties pProperties) {
        super(ITVArmorMaterials.ENDERITE, pType, pProperties);
    }

    @Override
    public boolean simpleArmorItem() {
        return true;
    }

}
