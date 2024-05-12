package net.laserdiamond.intothevoid.item.equipment.tools;

import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

/**
 * Represents a complex sword item of this mod. Complex items don't have their models data generated, and a model must be supplied for the item
 */
public class ITVComplexSwordItem extends SwordItem {
    public ITVComplexSwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }
}
