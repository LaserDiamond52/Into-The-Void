package net.laserdiamond.intothevoid.blocks;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import java.util.List;

/**
 * An ore block that represents an ore block that drops only 1 of its mineral
 */
public class ITVOreBlock extends ITVSimpleBlock {

    private final ItemLike oreDrop;

    public ITVOreBlock(Properties pProperties, List<TagKey<Block>> miningTags, ItemLike oreDrop) {
        super(pProperties, miningTags);
        this.oreDrop = oreDrop;
    }

    /**
     * The ore drop that will be dropped from mining this ore block (without silk touch)
     * @return an ItemLike object that represents the ore mineral drop
     */
    public ItemLike getOreDrop() {
        return oreDrop;
    }
}