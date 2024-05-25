package net.laserdiamond.intothevoid.item.equipment.tools;

import net.laserdiamond.intothevoid.item.ItemTaggable;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a complex sword item of this mod. Complex items don't have their models data generated. As such, a model must be supplied for the item
 */
public class ITVComplexSwordItem extends SwordItem implements ItemTaggable {

    private final List<TagKey<Item>> itemTags;

    public ITVComplexSwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
        this.itemTags = new ArrayList<>();
    }

    public ITVComplexSwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties, List<TagKey<Item>> itemTags)
    {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
        this.itemTags = itemTags;
    }

    @Override
    public List<TagKey<Item>> getItemTags() {
        return itemTags;
    }
}
