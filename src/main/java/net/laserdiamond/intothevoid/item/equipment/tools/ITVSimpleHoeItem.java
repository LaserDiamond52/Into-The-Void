package net.laserdiamond.intothevoid.item.equipment.tools;

import net.laserdiamond.intothevoid.item.ItemTaggable;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple hoe item of this mod. Model is auto generated as a handheld item, and only a texture must be supplied
 */
public class ITVSimpleHoeItem extends HoeItem implements ItemTaggable {

    private final List<TagKey<Item>> itemTags;

    public ITVSimpleHoeItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
        this.itemTags = new ArrayList<>();
    }

    public ITVSimpleHoeItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties, List<TagKey<Item>> itemTags)
    {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
        this.itemTags = itemTags;
    }

    @Override
    public List<TagKey<Item>> getItemTags() {
        return itemTags;
    }
}
