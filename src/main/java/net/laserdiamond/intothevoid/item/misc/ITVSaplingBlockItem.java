package net.laserdiamond.intothevoid.item.misc;

import net.laserdiamond.intothevoid.block.ITVSimpleBlockItem;
import net.laserdiamond.intothevoid.item.ItemTaggable;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.List;

/**
 * A sapling block item of this mod
 */
public class ITVSaplingBlockItem extends ITVSimpleBlockItem implements ItemTaggable {

    public ITVSaplingBlockItem(Block pBlock, Properties pProperties) {
        super(pBlock, pProperties);
    }

    public ITVSaplingBlockItem(Block pBlock, Properties pProperties, List<TagKey<Item>> itemTags)
    {
        super(pBlock, pProperties, itemTags);
    }

}
