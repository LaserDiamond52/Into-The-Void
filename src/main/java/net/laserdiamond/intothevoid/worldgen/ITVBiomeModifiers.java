package net.laserdiamond.intothevoid.worldgen;

import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.util.ITVTags;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Class that contains all Resource Keys for Biome Modifiers
 */
public class ITVBiomeModifiers {

    /**
     * A ResourceKey of type "BiomeModifier" that adds Lonsdaleite Ore to the overworld
     */
    public static final ResourceKey<BiomeModifier> ADD_OVERWORLD_LONSDALEITE_ORE = registerKey("add_overworld_lonsdaleite_ore");

    /**
     * A ResourceKey of type "BiomeModifier" that adds Meteorite Lonsdaleite Ore
     */
    public static final ResourceKey<BiomeModifier> ADD_METEROITE_LONSDALEITE_ORE = registerKey("add_overworld_meteorite_lonsdaleite_ore");

    /**
     * A ResourceKey of type "BiomeModifier" that adds Lonsdaleite Ore to the End dimension
     */
    public static final ResourceKey<BiomeModifier> ADD_END_LONSDALEITE_ORE = registerKey("add_end_lonsdaleite_ore");

    /**
     * A ResourceKey of type "BiomeModifier" that adds Enderite Ore to the End dimension
     */
    public static final ResourceKey<BiomeModifier> ADD_END_ENDERITE_ORE = registerKey("add_end_enderite_ore");

    /**
     * A ResourceKey of type "BiomeModifier" that adds Purpur Trees
     */
    public static final ResourceKey<BiomeModifier> ADD_PURPUR_TREES = registerKey("add_purpur_trees");

    /**
     * Registers all the Biome Modifier Resource Keys of this mod
     * @param context BootstapContext of type "BiomeModifier"
     */
    public static void boostrap(BootstapContext<BiomeModifier> context)
    {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_OVERWORLD_LONSDALEITE_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ITVPlacedFeatures.OVERWORLD_LONSDALEITE_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES)
        );

        context.register(ADD_METEROITE_LONSDALEITE_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ITVPlacedFeatures.OVERWORLD_METEORITE_LONSDALEITE_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES)
        );

        context.register(ADD_END_LONSDALEITE_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_END),
                HolderSet.direct(placedFeatures.getOrThrow(ITVPlacedFeatures.END_LONSDALEITE_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES)
        );

        context.register(ADD_END_ENDERITE_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_END),
                HolderSet.direct(placedFeatures.getOrThrow(ITVPlacedFeatures.END_ENDERITE_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES)
        );

        context.register(ADD_PURPUR_TREES, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(ITVTags.Biomes.PURPUR_FOREST),
                HolderSet.direct(placedFeatures.getOrThrow(ITVPlacedFeatures.PURPUR_TREE_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION)
        );
    }

    /**
     * Helper method used to help register Biome Modifier Resource Keys
     * @param name The name of the Resource Key (lowercase and underscores)
     * @return a ResourceKey of type "BiomeModifier"
     */
    private static ResourceKey<BiomeModifier> registerKey(String name)
    {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(IntoTheVoid.MODID, name));
    }
}
