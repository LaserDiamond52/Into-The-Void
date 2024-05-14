package net.laserdiamond.intothevoid.block;

import net.minecraft.tags.TagKey;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

/**
 * A class that represents an ore block that drops more than 1 of its mineral
 */
public class ITVMultiOreBlock extends ITVOreBlock {

    private final int minCount;
    private final int maxCount;

    public ITVMultiOreBlock(Properties pProperties, IntProvider xpRange, List<TagKey<Block>> miningTags, RegistryObject<Item> oreDrop, int minCount, int maxCount) {
        super(pProperties, xpRange, miningTags, oreDrop);
        this.minCount = minCount;
        this.maxCount = maxCount;
    }

    public ITVMultiOreBlock(Properties pProperties, IntProvider xpRange, List<TagKey<Block>> miningTags, RegistryObject<Item> oreDrop, boolean isSimple, int minCount, int maxCount) {
        super(pProperties, xpRange, miningTags, oreDrop, isSimple);
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
