package net.laserdiamond.intothevoid.dataGen;

import com.electronwill.nightconfig.toml.TomlWriter;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.dataGen.loot.ITVLootTableProvider;
import net.laserdiamond.intothevoid.dataGen.recipe.ITVRecipeProvider;
import net.laserdiamond.intothevoid.dataGen.tags.ITVBiomeTagProvider;
import net.laserdiamond.intothevoid.dataGen.tags.ITVBlockTagGenerator;
import net.laserdiamond.intothevoid.dataGen.tags.ITVEntityTagProvider;
import net.laserdiamond.intothevoid.dataGen.tags.ITVItemTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.core.config.yaml.YamlConfiguration;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
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

        generator.addProvider(event.includeClient(), ITVLootTableProvider.create(packOutput)); // Loot Table Provider
        generator.addProvider(event.includeServer(), new ITVRecipeProvider(packOutput)); // Recipe Provider

        generator.addProvider(event.includeClient(), new ITVItemModelProvider(packOutput, existingFileHelper)); // Item Model Provider

        generator.addProvider(event.includeClient(), new ITVBlocksStateProvider(packOutput, existingFileHelper)); // Blocks State Provider

        ITVBlockTagGenerator blockTagGenerator = generator.addProvider(event.includeServer(), new ITVBlockTagGenerator(packOutput, lookUpProvider, existingFileHelper)); // Block Tag Generator
        generator.addProvider(event.includeServer(), new ITVItemTagProvider(packOutput, lookUpProvider, blockTagGenerator.contentsGetter(), existingFileHelper)); // Item Tag Generator
        generator.addProvider(event.includeServer(), new ITVBiomeTagProvider(packOutput, lookUpProvider, existingFileHelper)); // Biome Tag Generator (doesn't work for biomes of this mod at the moment)

        generator.addProvider(event.includeServer(), new ITVEntityTagProvider(packOutput, lookUpProvider, existingFileHelper)); // Entity Tag Provider
        generator.addProvider(event.includeServer(), new ITVWorldGenProvider(packOutput, lookUpProvider)); // World Gen Provider

        /*
        ResourceLocation loc = new ResourceLocation(IntoTheVoid.MODID, "recipes/test_file.json");
        try {
            InputStream inputStream = Minecraft.getInstance().getResourceManager().getResource(loc).get().open();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            Gson gson = new Gson();
            JsonElement je = gson.fromJson(reader, JsonElement.class);
            JsonObject object = je.getAsJsonObject();
        } catch (IOException e)
        {

        }

         */

        ExistingFileHelper.ResourceType testResourceType = new ExistingFileHelper.ResourceType(PackType.CLIENT_RESOURCES, ".json", "recipes");
        PackOutput.PathProvider dataPackOutput = generator.getPackOutput().createPathProvider(PackOutput.Target.DATA_PACK, "recipes");

        dataPackOutput.json(new ResourceLocation("test.json"));

        String desktopPath = System.getProperty("user.home") + "/Desktop/";

        JsonObject object = new JsonObject();
        JsonObject testObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();

        Path currentRelativePath = Paths.get("");
        String absoluteRelativePathString = currentRelativePath.toAbsolutePath().toString();
        String relativePathString = currentRelativePath.toString();


        /*
        try {
            File file = new File("test");
            file.mkdirs();
            FileWriter fileWriter = new FileWriter(file + File.separator + "test_json_file.json");

            object.addProperty("test", "value");
            object.addProperty("test2", "value2");

            testObject.addProperty("inner", "value");
            jsonArray.add(testObject);
            object.add("test3", jsonArray);

            fileWriter.write(object.toString());
            fileWriter.close();


            //File desktopFile = new File("myFile.txt");

            //desktopFile.createNewFile();

            File testFile = new File("myFile.txt");
            testFile.createNewFile();

        } catch (IOException e) {
            //throw new RuntimeException(e);
            System.out.println("FAILED CREATING AND WRITING TO FILE");
            e.printStackTrace();
        }
        */

    }
}
