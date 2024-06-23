package net.laserdiamond.intothevoid.worldgen.biome;

import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.worldgen.ITVPlacedFeatures;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;

/**
 * Class that contains all the biomes of this mod
 */
public class ITVBiomes {

    /**
     * A ResourceKey of type "Biome" that represents the Purpur Forest
     */
    public static final ResourceKey<Biome> PURPUR_FOREST = register("purpur_forest_biome");

    /**
     * Helper method used to help register biomes
     * @param name The name of the biome (lowercase and underscores)
     * @return A ResourceKey of type "Biome" that represents the new biome
     */
    public static ResourceKey<Biome> register(String name)
    {
        return ResourceKey.create(Registries.BIOME, new ResourceLocation(IntoTheVoid.MODID, name));
    }

    /**
     * Registers all the biomes
     * @param context The BootstapContext of type "Biome"
     */
    public static void bootstrap(BootstapContext<Biome> context)
    {
        context.register(PURPUR_FOREST, purpurForestBiome(context));
    }

    /**
     * Implements all the features and necessary parts to the Purpur Forest Biome
     * @param context The BootstapContext of type "Biome"
     * @return A new Biome object instance representing the Purpur Forest Biome
     */
    private static Biome purpurForestBiome(BootstapContext<Biome> context) {

        MobSpawnSettings.Builder mobSpawnerBuilder = new MobSpawnSettings.Builder();
        mobSpawnerBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 6, 4,6));
        mobSpawnerBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.ENDERMITE, 4, 3, 5));

        BiomeGenerationSettings.Builder purpurForestBuilder = new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        BiomeDefaultFeatures.addDefaultCarversAndLakes(purpurForestBuilder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(purpurForestBuilder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(purpurForestBuilder);
        purpurForestBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ITVPlacedFeatures.PURPUR_TREE_PLACED_KEY);
        purpurForestBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.MUSHROOM_ISLAND_VEGETATION);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .temperature(0.5F)
                .downfall(0.5F)
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(4159204)
                        .waterFogColor(329011)
                        .fogColor(10518688)
                        .skyColor(0)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .mobSpawnSettings(mobSpawnerBuilder.build())
                .generationSettings(purpurForestBuilder.build())
                .build();

    }
}
