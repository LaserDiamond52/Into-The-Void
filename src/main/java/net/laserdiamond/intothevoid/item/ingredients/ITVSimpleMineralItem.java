package net.laserdiamond.intothevoid.item.ingredients;

import net.laserdiamond.intothevoid.item.ITVSimpleItem;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.List;

public class ITVSimpleMineralItem extends ITVSimpleItem {
    public ITVSimpleMineralItem(Properties pProperties) {
        super(pProperties);
    }

    public ITVSimpleMineralItem(Properties pProperties, List<TagKey<Item>> itemTags) {
        super(pProperties, itemTags);
    }
}
