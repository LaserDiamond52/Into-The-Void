package net.laserdiamond.intothevoid.worldgen;

import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.block.ITVBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

/**
 * Class that contains the placed features of this mod
 */
public class ITVPlacedFeatures {

    /**
     * A ResourceKey of type "PlacedFeature" that represents the placement of Lonsdaleite Ore in the Overworld
     */
    public static final ResourceKey<PlacedFeature> OVERWORLD_LONSDALEITE_ORE_PLACED_KEY = registerKey("overworld_lonsdaleite_ore_placed");

    /**
     * A ResourceKey of type "PlaceFeature" that represents the placement of Meteorite Lonsdaleite Ore in the Overworld
     */
    public static final ResourceKey<PlacedFeature> OVERWORLD_METEORITE_LONSDALEITE_ORE_PLACED_KEY = registerKey("overworld_meteorite_lonsdaleite_ore_placed");

    /**
     * A ResourceKey of type "PlacedFeature" that represents the placement of Lonsdaleite Ore in the End
     */
    public static final ResourceKey<PlacedFeature> END_LONSDALEITE_ORE_PLACED_KEY = registerKey("end_lonsdaleite_ore_placed");

    /**
     * A ResourceKey of type "PlaceFeature" that represents the placement of Enderite Ore in the End
     */
    public static final ResourceKey<PlacedFeature> END_ENDERITE_ORE_PLACED_KEY = registerKey("end_enderite_ore_placed");

    /**
     * A ResourceKey of type "PlacedFeature" that represents the placement of Purpur Trees
     */
    public static final ResourceKey<PlacedFeature> PURPUR_TREE_PLACED_KEY = registerKey("purpur_tree_placed_key");

    /**
     * Registers all the Placed Features of this mod
     * @param context The BootstapContext of type "PlaceFeature"
     */
    public static void bootstrap(BootstapContext<PlacedFeature> context)
    {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatureHolderGetter = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, OVERWORLD_LONSDALEITE_ORE_PLACED_KEY, configuredFeatureHolderGetter.getOrThrow(ITVConfiguredFeatures.OVERWORLD_LONSDALEITE_ORE_KEY),
                ITVOrePlacements.commonOrePlacement(5, HeightRangePlacement.triangle(VerticalAnchor.absolute(-30), VerticalAnchor.absolute(30))));

        register(context, OVERWORLD_METEORITE_LONSDALEITE_ORE_PLACED_KEY, configuredFeatureHolderGetter.getOrThrow(ITVConfiguredFeatures.OVERWORLD_METEORITE_LONSDALEITE_ORE_KEY),
                ITVOrePlacements.commonOrePlacement(6, HeightRangePlacement.triangle(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(10))));

        register(context, END_LONSDALEITE_ORE_PLACED_KEY, configuredFeatureHolderGetter.getOrThrow(ITVConfiguredFeatures.END_LONSDALEITE_ORE_KEY),
                ITVOrePlacements.commonOrePlacement(7, HeightRangePlacement.triangle(VerticalAnchor.absolute(-50), VerticalAnchor.absolute(50))));

        register(context, END_ENDERITE_ORE_PLACED_KEY, configuredFeatureHolderGetter.getOrThrow(ITVConfiguredFeatures.END_ENDERITE_ORE_KEY),
                ITVOrePlacements.commonOrePlacement(8, HeightRangePlacement.triangle(VerticalAnchor.absolute(-50), VerticalAnchor.absolute(50))));

        register(context, PURPUR_TREE_PLACED_KEY, configuredFeatureHolderGetter.getOrThrow(ITVConfiguredFeatures.PURPUR_TREE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(6, 0.1F, 3), ITVBlocks.PURPUR_SAPLING.get()));
    }

    /**
     * Helper method used to help create the Resource Key for the Placed Feature
     * @param name The name of the PlacedFeature (lowercase and underscores)
     * @return A ResourceKey of type "PlacedFeature" representing the Placed Feature
     */
    private static ResourceKey<PlacedFeature> registerKey(String name)
    {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(IntoTheVoid.MODID, name));
    }

    /**
     * Helper method used to help register the Placed Feature
     * @param context The BootstapContext of type "PlacedFeature"
     * @param key The ResourceKey of the Placed Feature
     * @param config The Configured Features Hold (Holder of type "ConfiguredFeature" containing unknown types
     * @param modifiers The placement modifiers (List of PlacementModifiers)
     */
    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> config, List<PlacementModifier> modifiers)
    {
        context.register(key, new PlacedFeature(config, List.copyOf(modifiers)));
    }
}
