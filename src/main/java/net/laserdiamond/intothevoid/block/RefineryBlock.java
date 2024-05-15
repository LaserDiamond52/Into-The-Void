package net.laserdiamond.intothevoid.block;

import net.laserdiamond.intothevoid.block.entity.ITVBlockEntities;
import net.laserdiamond.intothevoid.block.entity.RefineryBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RefineryBlock extends BaseEntityBlock implements BlockTaggable {

    public static final VoxelShape SHAPE = Block.box(0,0,0,16,25,16);
    private final List<TagKey<Block>> blockTags;

    public RefineryBlock(Properties pProperties, List<TagKey<Block>> blockTags) {
        super(pProperties);
        this.blockTags = blockTags;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new RefineryBlockEntity(blockPos, blockState);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {
        if (pState.getBlock() != pNewState.getBlock())
        {
            BlockEntity be = pLevel.getBlockEntity(pPos);
            if (be instanceof RefineryBlockEntity refineryBlockEntity)
            {
                refineryBlockEntity.drops();
            }
        }

        super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide)
        {
            BlockEntity be = pLevel.getBlockEntity(pPos);
            if (be instanceof RefineryBlockEntity refineryBlockEntity)
            {
                NetworkHooks.openScreen((ServerPlayer) pPlayer, refineryBlockEntity, pPos);
            } else
            {
                throw new IllegalStateException("!!EXCEPTION: MISSING CONTAINER PROVIDER!!");
            }
        }
        return InteractionResult.sidedSuccess(pLevel.isClientSide);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if (pLevel.isClientSide)
        {
            return null;
        }

        return createTickerHelper(pBlockEntityType, ITVBlockEntities.REFINERY.get(), (level, blockPos, blockState, refineryBlockEntity) -> refineryBlockEntity.tick(level, blockPos, blockState));
    }

    @Override
    public List<TagKey<Block>> getBlockTags() {
        return blockTags;
    }
}
