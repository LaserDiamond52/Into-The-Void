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

import java.util.Set;

public class ITVBlockLootTables extends BlockLootSubProvider {
    public ITVBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {

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
            }
        }
        for (ITVBlocks.WoodBlocks woodBlocks : ITVBlocks.WoodBlocks.values())
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
