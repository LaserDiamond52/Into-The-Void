package net.laserdiamond.intothevoid.blocks;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import java.util.List;

/**
 * A class that represents an ore block that drops more than 1 of its mineral
 */
public class ITVMultiOreBlock extends ITVOreBlock {

    private final int minCount;
    private final int maxCount;
    public ITVMultiOreBlock(Properties pProperties, List<TagKey<Block>> miningTags, ItemLike oreDrop, int minCount, int maxCount) {
        super(pProperties, miningTags, oreDrop);
        this.minCount = minCount;
        this.maxCount = maxCount;
    }

    /**
     * Gets the minimum count of the mineral that can be dropped when mining this ore
     * @return The minimum count of the drop as an integer
     */
    public int getMinCount() {
        return minCount;
    }

    /**
     * Gets the maximum count of the mineral that can be dropped when mining this ore
     * @return The maximum count of the drop as an integer
     */
    public int getMaxCount() {
        return maxCount;
    }
}
