package net.laserdiamond.intothevoid.item;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.List;

public interface ItemTaggable {

    /**
     * A list of tags that should be applied to the item
     * @return A list of tags that should be applied to the item
     */
    List<TagKey<Item>> getItemTags();
}
