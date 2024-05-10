package net.laserdiamond.intothevoid.blocks;

import net.minecraft.tags.TagKey;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Properties;

/**
 * An ore block that represents an ore block that drops only 1 of its mineral
 */
public class ITVOreBlock extends DropExperienceBlock {

    private final List<TagKey<Block>> miningTags;
    private final RegistryObject<Item> oreDrop;
    private final boolean isSimple;

    public ITVOreBlock(Properties pProperties, IntProvider xpRange, List<TagKey<Block>> miningTags, RegistryObject<Item> oreDrop) {
        super(pProperties, xpRange);
        this.miningTags = miningTags;
        this.isSimple = true;
        this.oreDrop = oreDrop;
    }

    public ITVOreBlock(Properties pProperties, IntProvider xpRange, List<TagKey<Block>> miningTags, RegistryObject<Item> oreDrop, boolean isSimple)
    {
        super(pProperties, xpRange);
        this.miningTags = miningTags;
        this.oreDrop = oreDrop;
        this.isSimple = isSimple;
    }

    /**
     * A list of the tags that should be applied to the block
     * @return A list of tags that should be applied to the block
     */
    public List<TagKey<Block>> getMiningTags() {
        return miningTags;
    }

    /**
     * The ore drop that will be dropped from mining this ore block (without silk touch)
     * @return an ItemLike object that represents the ore mineral drop
     */
    public RegistryObject<Item> getOreDrop() {
        return oreDrop;
    }

    /**
     * Determines whether the block is a simple block, and should have its model data generated
     * @return True if simple, false if not
     */
    public boolean isSimple() {
        return isSimple;
    }


}
