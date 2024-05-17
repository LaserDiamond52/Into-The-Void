package net.laserdiamond.intothevoid.item;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ITVComplexItem extends Item implements ItemTaggable {

    private final List<TagKey<Item>> itemTags;
    public ITVComplexItem(Properties pProperties) {
        super(pProperties);
        this.itemTags = new ArrayList<>();
    }

    public ITVComplexItem(Properties pProperties, List<TagKey<Item>> itemTags) {
        super(pProperties);
        this.itemTags = itemTags;
    }

    @Override
    public List<TagKey<Item>> getItemTags() {
        return itemTags;
    }
}
