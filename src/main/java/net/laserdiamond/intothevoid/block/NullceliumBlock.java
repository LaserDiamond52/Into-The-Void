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

    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable) {
        return true;
    }

    private static boolean canBeNullcelium(BlockState pState, LevelReader pReader, BlockPos pPos)
    {
        BlockPos abovePos = pPos.above();
        BlockState aboveState = pReader.getBlockState(abovePos);
        int lightIntoBlock = LightEngine.getLightBlockInto(pReader, pState, pPos, aboveState, abovePos, Direction.UP, aboveState.getLightBlock(pReader, abovePos));
        return lightIntoBlock < pReader.getMaxLightLevel();
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (!canBeNullcelium(pState, pLevel, pPos))
        {
            pLevel.setBlockAndUpdate(pPos, ITVBlocks.NULL_SAND.get().defaultBlockState());
        }
    }

    public boolean getAllSides()
    {
        return allSides;
    }

    @Override
    public List<TagKey<Block>> getBlockTags() {
        return blockTags;
    }
}
