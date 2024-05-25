package net.laserdiamond.intothevoid.block;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.state.properties.BlockSetType;

import java.util.ArrayList;
import java.util.List;

/**
 * A button block of this mod
 */
public class ITVButtonBlock extends ButtonBlock implements BlockTaggable {

    private final List<TagKey<Block>> blockTags;
    public ITVButtonBlock(Properties pProperties, BlockSetType pType, int pTicksToStayPressed, boolean pArrowsCanPress) {
        super(pProperties, pType, pTicksToStayPressed, pArrowsCanPress);
        this.blockTags = new ArrayList<>();
    }

    public ITVButtonBlock(Properties pProperties, BlockSetType pType, int pTicksToStayPressed, boolean pArrowsCanPress, List<TagKey<Block>> blockTags) {
        super(pProperties, pType, pTicksToStayPressed, pArrowsCanPress);
        this.blockTags = blockTags;
    }

    @Override
    public List<TagKey<Block>> getBlockTags() {
        return blockTags;
    }
}
