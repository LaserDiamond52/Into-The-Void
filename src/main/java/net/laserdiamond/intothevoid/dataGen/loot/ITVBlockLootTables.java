package net.laserdiamond.intothevoid.dataGen.loot;

import net.laserdiamond.intothevoid.blocks.ITVBlocks;
import net.laserdiamond.intothevoid.blocks.ITVSelfDropBlock;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
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

import java.util.Set;

public class ITVBlockLootTables extends BlockLootSubProvider {
    public ITVBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {

        for (RegistryObject<Block> block : ITVBlocks.BLOCKS.getEntries())
        {
            if (block.get() instanceof ITVSelfDropBlock itvSelfDropBlock)
            {
                this.dropSelf(itvSelfDropBlock);
            }
        }
    }

    @Override
    protected LootTable.Builder createOreDrop(Block pBlock, Item pItem) {
        return super.createOreDrop(pBlock, pItem);
    }

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
