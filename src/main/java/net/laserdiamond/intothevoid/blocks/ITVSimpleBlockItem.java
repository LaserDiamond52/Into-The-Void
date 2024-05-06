package net.laserdiamond.intothevoid.blocks;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

/**
 * Class that represents a simple block item of this mod. Blocks items of this child class will automatically have their model data generated
 */
public final class ITVSimpleBlockItem extends BlockItem {

    public ITVSimpleBlockItem(Block pBlock, Properties pProperties) {
        super(pBlock, pProperties);
    }
}
