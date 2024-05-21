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

public class ITVConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_LONSDALEITE_ORE_KEY = registerKey("overworld_lonsdaleite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_METEORITE_LONSDALEITE_ORE_KEY = registerKey("overworld_meteorite_lonsdaleite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> END_LONSDALEITE_ORE_KEY = registerKey("end_lonsdaleite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> END_ENDERITE_ORE_KEY = registerKey("end_enderite_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> PURPUR_TREE_KEY = registerKey("purpur_tree");

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
                .build());

    }

    public static ResourceKey<ConfiguredFeature<?,?>> registerKey(String name)
    {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(IntoTheVoid.MODID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC config)
    {
        context.register(key, new ConfiguredFeature<>(feature, config));
    }
}
