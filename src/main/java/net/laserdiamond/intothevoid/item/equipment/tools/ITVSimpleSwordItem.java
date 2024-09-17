package net.laserdiamond.intothevoid.item.equipment.tools;

import net.laserdiamond.intothevoid.item.ItemTaggable;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple sword item of this mod. Model is auto generated as a handheld item, and only a texture must be supplied
 */
public class ITVSimpleSwordItem extends SwordItem implements ItemTaggable {

    private final List<TagKey<Item>> itemTags;

    public ITVSimpleSwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
        this.itemTags = new ArrayList<>();
    }

    public ITVSimpleSwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties, List<TagKey<Item>> itemTags)
    {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
        this.itemTags = itemTags;
    }

    @Override
    public List<TagKey<Item>> getItemTags() {
        return this.itemTags;
    }
}
