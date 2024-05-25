package net.laserdiamond.intothevoid.item;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents a complex item of this mod. A model must be supplied for the item as opposed to the simple item, which automatically has its model generated
 */
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
