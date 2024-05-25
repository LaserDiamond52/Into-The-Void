package net.laserdiamond.intothevoid.dataGen.loot;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;

/**
 * Class responsible for creating all the loot table providers for this mod
 */
public class ITVLootTableProvider {

    public static LootTableProvider create(PackOutput output)
    {
        return new LootTableProvider(output, Set.of(), List.of(
                new LootTableProvider.SubProviderEntry(ITVBlockLootTables::new, LootContextParamSets.BLOCK)
        ));
    }
}
