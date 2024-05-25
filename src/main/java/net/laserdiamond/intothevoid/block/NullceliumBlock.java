package net.laserdiamond.intothevoid.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.lighting.LightEngine;
import net.minecraftforge.common.IPlantable;

import java.util.ArrayList;
import java.util.List;

/**
 * The nullcelium block
 */
public class NullceliumBlock extends Block implements BlockTaggable {

    private final List<TagKey<Block>> blockTags;
    private final boolean allSides;

    public NullceliumBlock(Properties pProperties, boolean allSides) {
        super(pProperties);
        this.blockTags = new ArrayList<>();
        this.allSides = allSides;
    }

    public NullceliumBlock(Properties pProperties, boolean allSides, List<TagKey<Block>> blockTags)
    {
        super(pProperties);
        this.allSides = allSides;
        this.blockTags = blockTags;
    }

    /**
     * Determines if the block is able to sustain plants (flowers, mushrooms, trees, and other types of foliage)
     * @param state The BlockState of the block
     * @param world BlockGetter
     * @param pos The Block Position of the block
     * @param facing The direction the block is facing
     * @param plantable The plantable block being placed
     * @return True if can sustain plants, false otherwise
     */
    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable) {
        return true;
    }

    /**
     * Determines if the block can be Nullcelium
     * @param pState The BlockState of the block
     * @param pReader The LevelReader
     * @param pPos the Position of the block
     * @return True if Nullcelium, false if not
     */
    private static boolean canBeNullcelium(BlockState pState, LevelReader pReader, BlockPos pPos)
    {
        BlockPos abovePos = pPos.above();
        BlockState aboveState = pReader.getBlockState(abovePos);
        int lightIntoBlock = LightEngine.getLightBlockInto(pReader, pState, pPos, aboveState, abovePos, Direction.UP, aboveState.getLightBlock(pReader, abovePos));
        return lightIntoBlock < pReader.getMaxLightLevel();
    }

    /**
     * Changes Nullcelium to Null Sand if a block is present on top of Nullcelium for a duration
     * @param pState The BlockState of the block
     * @param pLevel The ServerLevel of the world
     * @param pPos the position of the block
     * @param pRandom RandomSource
     */
    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (!canBeNullcelium(pState, pLevel, pPos))
        {
            pLevel.setBlockAndUpdate(pPos, ITVBlocks.NULL_SAND.get().defaultBlockState());
        }
    }

    /**
     * Determines if all sides of the block should have the same texture (NO FUNCTIONALITY AT THE MOMENT)
     * @return True if all sides should be the same, false if not
     */
    public boolean getAllSides()
    {
        return allSides;
    }

    @Override
    public List<TagKey<Block>> getBlockTags() {
        return blockTags;
    }
}
