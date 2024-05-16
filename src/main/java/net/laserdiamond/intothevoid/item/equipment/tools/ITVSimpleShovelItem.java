package net.laserdiamond.intothevoid.item.equipment.tools;

import net.laserdiamond.intothevoid.item.ItemTaggable;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;

import java.util.ArrayList;
import java.util.List;

public class ITVSimpleShovelItem extends ShovelItem implements ItemTaggable {

    private final List<TagKey<Item>> itemTags;

    public ITVSimpleShovelItem(Tier pTier, float pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
        this.itemTags = new ArrayList<>();
    }

    public ITVSimpleShovelItem(Tier pTier, float pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties, List<TagKey<Item>> itemTags)
    {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
        this.itemTags = itemTags;
    }

    @Override
    public List<TagKey<Item>> getItemTags() {
        return itemTags;
    }
}
