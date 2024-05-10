package net.laserdiamond.intothevoid.item.equipment.armor.armorItems;

import net.laserdiamond.intothevoid.item.equipment.armor.ITVArmorItem;
import net.laserdiamond.intothevoid.item.equipment.armor.ITVArmorMaterials;

public final class DragonBorneArmorItem extends ITVArmorItem {
    public DragonBorneArmorItem(Type pType, Properties pProperties) {
        super(ITVArmorMaterials.DRAGONBORNE, pType, pProperties);
    }

    @Override
    protected double[] meleeDamageAmount() {
        return new double[]{5,5,5,5};
    }

    @Override
    public boolean simpleArmorItem() {
        return true;
    }
}
