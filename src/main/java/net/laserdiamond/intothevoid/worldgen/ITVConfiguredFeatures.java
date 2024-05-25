package net.laserdiamond.intothevoid.worldgen;

import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.block.ITVBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.common.Tags;

import java.util.List;

/**
 * Class that contains all Configured Features of this mod
 */
public class ITVConfiguredFeatures {

    /**
     * A ResourceKey of type "ConfiguredFeature" containing unknown types that represents the key for adding Lonsdaleite Ore to the overworld
     */
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_LONSDALEITE_ORE_KEY = registerKey("overworld_lonsdaleite_ore");

    /**
     * A ResourceKey of type "ConfiguredFeature" containing unknown types that represents the key for adding Meteorite Lonsdaleite Ore to the overworld
     */
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_METEORITE_LONSDALEITE_ORE_KEY = registerKey("overworld_meteorite_lonsdaleite_ore");

    /**
     * A ResourceKey of type "ConfiguredFeature" containing unknown types that represents the key for adding Lonsdaleite Ore to The End
     */
    public static final ResourceKey<ConfiguredFeature<?, ?>> END_LONSDALEITE_ORE_KEY = registerKey("end_lonsdaleite_ore");

    /**
     * A ResourceKey of type "ConfiguredFeature" containing unknown types that represents the key for adding Enderite Ore to The End
     */
    public static final ResourceKey<ConfiguredFeature<?, ?>> END_ENDERITE_ORE_KEY = registerKey("end_enderite_ore");

    /**
     * A ResourceKey of type "ConfiguredFeature" containing unknown types that represents the key for adding Purpur Tree generation
     */
    public static final ResourceKey<ConfiguredFeature<?, ?>> PURPUR_TREE_KEY = registerKey("purpur_tree");

    /**
     * Registers all Configured Features of this mod
     * @param context BootstapContext of type "ConfiguredFeature" containing unknown types
     */
    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context)
    {
        RuleTest stoneReplaceTest = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceTest = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest endstoneReplaceTest = new BlockMatchTest(Blocks.END_STONE);

        List<OreConfiguration.TargetBlockState> overworldLonsdaleiteOres = List.of(
                OreConfiguration.target(stoneReplaceTest, ITVBlocks.LONSDALEITE_ORE.get().defaultBlockState())
        );

        List<OreConfiguration.TargetBlockState> overworldMeteoriteLonsdaleiteOres = List.of(
                OreConfiguration.target(deepslateReplaceTest, ITVBlocks.METEORITE_LONSDALEITE_ORE.get().defaultBlockState())
        );

        register(context, OVERWORLD_LONSDALEITE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldLonsdaleiteOres, 6));
        register(context, OVERWORLD_METEORITE_LONSDALEITE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldMeteoriteLonsdaleiteOres, 4));
        register(context, END_LONSDALEITE_ORE_KEY, Feature.ORE, new OreConfiguration(endstoneReplaceTest, ITVBlocks.ENDSTONE_LONSDALEITE_ORE.get().defaultBlockState(), 10));
        register(context, END_ENDERITE_ORE_KEY, Feature.ORE, new OreConfiguration(endstoneReplaceTest, ITVBlocks.ENDERITE_ORE.get().defaultBlockState(), 3));

        register(context, PURPUR_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ITVBlocks.PURPUR_LOG.get()),
                new StraightTrunkPlacer(5, 4, 3),
                BlockStateProvider.simple(ITVBlocks.PURPUR_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(3), 3),
                new TwoLayersFeatureSize(1, 0, 2))
                .dirt(BlockStateProvider.simple(ITVBlocks.NULL_SAND.get()))
                .build());

    }

    /**
     * Helper method used to help register the Resource Keys for the Configured Features
     * @param name Name of the key (lowercase and underscores)
     * @return A ResourceKey of type "ConfiguredFeature" containing unknown types
     */
    public static ResourceKey<ConfiguredFeature<?,?>> registerKey(String name)
    {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(IntoTheVoid.MODID, name));
    }

    /**
     * Helper method used to registers the ConfiguredFeature
     * @param context The bootstap context
     * @param key The ResourceKey of type "ConfiguredFeature" containing unknown types
     * @param feature The feature type that the ConfiguredFeature adds
     * @param config The Feature Configuration
     * @param <FC> Subclass of the FeatureConfiguration
     * @param <F> Subclass of the Feature of FC
     */
    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC config)
    {
        context.register(key, new ConfiguredFeature<>(feature, config));
    }
}
