package net.laserdiamond.intothevoid.dataGen;

import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.dataGen.loot.ITVLootTableProvider;
import net.laserdiamond.intothevoid.dataGen.recipe.ITVRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

/**
 * Class that gathers and registers all the data generators
 */
@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = IntoTheVoid.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    /**
     * Listens for a GatherDataEvent and registers all the data generators
     * @param event GatherDataEvent
     */
    @SuppressWarnings("unused")
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event)
    {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookUpProvider = event.getLookupProvider();

        generator.addProvider(event.includeClient(), ITVLootTableProvider.create(packOutput));
        generator.addProvider(event.includeServer(), new ITVRecipeProvider(packOutput));

        generator.addProvider(event.includeClient(), new ITVItemModelProvider(packOutput, existingFileHelper));

        generator.addProvider(event.includeClient(), new ITVBlocksStateProvider(packOutput, existingFileHelper));

        ITVBlockTagGenerator blockTagGenerator = generator.addProvider(event.includeServer(), new ITVBlockTagGenerator(packOutput, lookUpProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new ITVItemTagProvider(packOutput, lookUpProvider, blockTagGenerator.contentsGetter(), existingFileHelper));

        generator.addProvider(event.includeServer(), new ITVWorldGenProvider(packOutput, lookUpProvider));
    }
}
