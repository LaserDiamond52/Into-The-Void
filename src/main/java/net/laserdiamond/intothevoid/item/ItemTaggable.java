package net.laserdiamond.intothevoid.item;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.List;

/**
 * Interface used to help add items tags to items
 */
public interface ItemTaggable  {

    /**
     * A list of tags that should be applied to the item
     * @return A list of tags that should be applied to the item
     */
    List<TagKey<Item>> getItemTags();
}
