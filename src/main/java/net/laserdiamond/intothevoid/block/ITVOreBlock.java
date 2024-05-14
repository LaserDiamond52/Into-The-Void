package net.laserdiamond.intothevoid.block;

import net.minecraft.tags.TagKey;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

/**
 * An ore block that represents an ore block that drops only 1 of its mineral
 */
public class ITVOreBlock extends DropExperienceBlock implements BlockTaggable {

    private final List<TagKey<Block>> blockTags;
    private final RegistryObject<Item> oreDrop;
    private final boolean isSimple;

    public ITVOreBlock(Properties pProperties, IntProvider xpRange, List<TagKey<Block>> blockTags, RegistryObject<Item> oreDrop) {
        super(pProperties, xpRange);
        this.blockTags = blockTags;
        this.isSimple = true;
        this.oreDrop = oreDrop;
    }

    public ITVOreBlock(Properties pProperties, IntProvider xpRange, List<TagKey<Block>> blockTags, RegistryObject<Item> oreDrop, boolean isSimple)
    {
        super(pProperties, xpRange);
        this.blockTags = blockTags;
        this.oreDrop = oreDrop;
        this.isSimple = isSimple;
    }

    @Override
    public List<TagKey<Block>> getBlockTags() {
        return blockTags;
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
