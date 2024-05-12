package net.laserdiamond.intothevoid.item.equipment.tools;

import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

/**
 * A class that represents a sword item of this mod. Simple items will have their models data generated
 */
public class ITVSimpleSwordItem extends SwordItem {

    public ITVSimpleSwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }
}
