package net.laserdiamond.intothevoid.dataGen.loot;

import net.laserdiamond.intothevoid.block.*;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Set;

/**
 * Class responsible for creating all the loot tables for blocks of this mod
 */
public class ITVBlockLootTables extends BlockLootSubProvider {

    public ITVBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    /**
     * A HashMap that maps all leaves to their sapling blocks
     */
    private static final HashMap<Block, Block> LEAVES_SAPLING_MAP = new HashMap<>();
    static
    {
        LEAVES_SAPLING_MAP.put(ITVBlocks.PURPUR_LEAVES.get(), ITVBlocks.PURPUR_SAPLING.get());
    }

    /**
     * Generates all loot tables for blocks
     */
    @Override
    protected void generate() {

        // Most blocks under the block registry
        for (RegistryObject<Block> blockRegistryObject : ITVBlocks.BLOCKS.getEntries())
        {
            Block blockItem = blockRegistryObject.get();
            if (blockItem instanceof ITVSelfDropBlock itvSelfDropBlock)
            {
                this.dropSelf(itvSelfDropBlock);
            } else if (blockItem instanceof ITVMultiOreBlock itvMultiOreBlock)
            {
                this.add(itvMultiOreBlock, block -> createMultiOreDrop(itvMultiOreBlock, itvMultiOreBlock.getOreDrop().get(), itvMultiOreBlock.getMinCount(), itvMultiOreBlock.getMaxCount()));
            } else if (blockItem instanceof ITVOreBlock itvOreBlock)
            {
                this.add(itvOreBlock, block -> createOreDrop(itvOreBlock, itvOreBlock.getOreDrop().get()));
            } else if (blockItem instanceof ITVWoodLogBlock itvWoodLogBlock)
            {
                this.dropSelf(itvWoodLogBlock);
            } else if (blockItem instanceof ITVLeavesBlock itvLeavesBlock)
            {
                Block saplingBlock = LEAVES_SAPLING_MAP.get(itvLeavesBlock);
                this.add(itvLeavesBlock, block -> createLeavesDrops(itvLeavesBlock, saplingBlock, NORMAL_LEAVES_SAPLING_CHANCES));
            } else if (blockItem instanceof ITVSaplingBlock itvSaplingBlock)
            {
                this.dropSelf(itvSaplingBlock);
            } else if (blockItem instanceof NullceliumBlock grassBlock)
            {
                this.dropSelf(grassBlock);
            }
        }
        this.dropSelf(ITVBlocks.REFINERY.get()); // individually call this one (no need to make a separate if-statement branch for this
        for (ITVBlocks.WoodBlocks woodBlocks : ITVBlocks.WoodBlocks.values()) // Loop through all the wood-type blocks
        {
            RegistryObject<Block> slab = woodBlocks.getSlab();
            RegistryObject<Block> stairs = woodBlocks.getStairs();
            RegistryObject<Block> pressurePlate = woodBlocks.getPressurePlate();
            RegistryObject<Block> door = woodBlocks.getDoor();
            RegistryObject<Block> trapDoor = woodBlocks.getTrapDoor();
            RegistryObject<Block> fence = woodBlocks.getFence();
            RegistryObject<Block> fenceGate = woodBlocks.getFenceGate();
            RegistryObject<Block> button = woodBlocks.getButton();

            this.add(slab.get(), block -> createSlabItemTable(slab.get()));
            this.dropSelf(stairs.get());
            this.dropSelf(pressurePlate.get());
            this.add(door.get(), block -> createDoorTable(door.get()));
            this.dropSelf(trapDoor.get());
            this.dropSelf(fence.get());
            this.dropSelf(fenceGate.get());
            this.dropSelf(button.get());

            this.add(woodBlocks.getSign().get(), block -> createSingleItemTable(woodBlocks.getSign().get()));
            this.add(woodBlocks.getHangingSign().get(), block -> createSingleItemTable(woodBlocks.getSign().get()));
            this.add(woodBlocks.getWallSign().get(), block -> createSingleItemTable(woodBlocks.getWallSign().get()));
            this.add(woodBlocks.getWallHangingSign().get(), block -> createSingleItemTable(woodBlocks.getWallSign().get()));
        }

        for (ITVBlocks.StoneBlocks stoneBlocks : ITVBlocks.StoneBlocks.values())
        {
            RegistryObject<Block> baseSlab = stoneBlocks.getBaseSlabBlock();
            RegistryObject<Block> baseStairs = stoneBlocks.getBaseStairBlock();
            RegistryObject<Block> baseButton = stoneBlocks.getBaseButton();
            RegistryObject<Block> pressurePlate = stoneBlocks.getBasePressurePlate();
            RegistryObject<Block> baseWall = stoneBlocks.getBaseWallBlock();

            this.add(baseSlab.get(), block -> createSlabItemTable(baseSlab.get()));
            this.dropSelf(baseStairs.get());
            this.dropSelf(baseButton.get());
            this.dropSelf(pressurePlate.get());
            this.dropSelf(baseWall.get());

            RegistryObject<Block> brickSlab = stoneBlocks.getBrickSlabBlock();
            RegistryObject<Block> brickStairs = stoneBlocks.getBrickStairBlock();
            RegistryObject<Block> brickWall = stoneBlocks.getBrickWallBlock();

            this.add(brickSlab.get(), block -> createSlabItemTable(brickSlab.get()));
            this.dropSelf(brickStairs.get());
            this.dropSelf(brickWall.get());
        }
    }

    /**
     * Creates a loot table for the block that resembles that of an ore block
     * @param pBlock The ore block
     * @param pItem The mineral dropped from the ore block when mined
     * @return LootTable.Builder
     */
    @Override
    protected LootTable.Builder createOreDrop(@NotNull Block pBlock, @NotNull Item pItem) {
        return super.createOreDrop(pBlock, pItem);
    }

    /**
     * Creates a loot table for the block that resembles that of an ore that drops more than 1 of its ore drop
     * @param block The ore block
     * @param item The mineral dropped from the ore block when mined
     * @param minCount The minimum amount of minerals dropped
     * @param maxCount The maximum amount of minerals dropped
     * @return LootTable.Builder
     */
    protected LootTable.Builder createMultiOreDrop(Block block, Item item, float minCount, float maxCount)
    {
        return createSilkTouchDispatchTable(block,
                this.applyExplosionDecay(block, LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minCount, maxCount)))
                        .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ITVBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
