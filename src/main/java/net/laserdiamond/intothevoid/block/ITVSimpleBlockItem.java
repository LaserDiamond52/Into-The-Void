package net.laserdiamond.intothevoid.block;

import net.laserdiamond.intothevoid.item.ItemTaggable;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents a simple block item of this mod. Blocks items of this child class will automatically have their model data generated
 */
public class ITVSimpleBlockItem extends BlockItem implements ItemTaggable {

    private final List<TagKey<Item>> itemTags;

    public ITVSimpleBlockItem(Block pBlock, Properties pProperties)
    {
        super(pBlock, pProperties);
        this.itemTags = new ArrayList<>();
    }

    public ITVSimpleBlockItem(Block pBlock, Properties pProperties, List<TagKey<Item>> itemTags) {
        super(pBlock, pProperties);
        this.itemTags = itemTags;
    }

    @Override
    public List<TagKey<Item>> getItemTags() {
        return itemTags;
    }
}
