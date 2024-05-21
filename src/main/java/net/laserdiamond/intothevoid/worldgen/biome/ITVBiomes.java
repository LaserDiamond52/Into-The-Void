package net.laserdiamond.intothevoid.worldgen.biome;

import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.worldgen.ITVPlacedFeatures;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.biome.EndBiomes;
import net.minecraft.data.worldgen.biome.NetherBiomes;
import net.minecraft.data.worldgen.placement.EndPlacements;
import net.minecraft.data.worldgen.placement.NetherPlacements;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;

public class ITVBiomes {

    public static final ResourceKey<Biome> PURPUR_FOREST = register("purpur_forest_biome");

    public static ResourceKey<Biome> register(String name)
    {
        return ResourceKey.create(Registries.BIOME, new ResourceLocation(IntoTheVoid.MODID, name));
    }

    public static void bootstrap(BootstapContext<Biome> context)
    {
        context.register(PURPUR_FOREST, purpurForestBiome(context));
    }

    public static void purpurForsestGeneration(BiomeGenerationSettings.Builder builder)
    {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
    }

    private static Biome purpurForestBiome(BootstapContext<Biome> context) {

        MobSpawnSettings.Builder mobSpawnerBuilder = new MobSpawnSettings.Builder();
        mobSpawnerBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 6, 4,6));
        mobSpawnerBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.ENDERMITE, 4, 3, 5));

        BiomeGenerationSettings.Builder purpurForestBuilder = new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        purpurForsestGeneration(purpurForestBuilder);
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
