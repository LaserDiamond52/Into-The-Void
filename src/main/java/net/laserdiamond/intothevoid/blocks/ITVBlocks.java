package net.laserdiamond.intothevoid.blocks;

import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.item.ITVItems;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.common.Tags;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

public class ITVBlocks {

    /**
     * A DeferredRegister of type "Block" that is used to register blocks
     */
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, IntoTheVoid.MODID);

    /**
     * A RegistryObject of type "Block" that represents a Lonsdaleite Block
     */
    public static final RegistryObject<Block> LONSDALEITE_BLOCK = registerSimpleBlock("lonsdaleite_block", () -> new ITVSelfDropBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).sound(SoundType.METAL), List.of(BlockTags.NEEDS_DIAMOND_TOOL, BlockTags.MINEABLE_WITH_PICKAXE)));

    /**
     * A RegistryObject of type "Block" that represents a Refined Lonsdaleite Block
     */
    public static final RegistryObject<Block> REFINED_LONSDALEITE_BLOCK = registerSimpleBlock("refined_lonsdaleite_block", () -> new ITVSelfDropBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).sound(SoundType.METAL), List.of(BlockTags.NEEDS_DIAMOND_TOOL, BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.BEACON_BASE_BLOCKS)));

    /**
     * A RegistryObject of type "Block" that represents an Enderite Block
     */
    public static final RegistryObject<Block> ENDERITE_BLOCK = registerSimpleBlock("enderite_block", () -> new ITVSelfDropBlock(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK).sound(SoundType.NETHERITE_BLOCK), List.of(BlockTags.NEEDS_DIAMOND_TOOL, BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.BEACON_BASE_BLOCKS)));


    /**
     * A RegistryObject of type "Block" that represents an Enderite Ore block
     */
    public static final RegistryObject<Block> ENDERITE_ORE = registerSimpleBlock("enderite_ore", () -> new ITVOreBlock(BlockBehaviour.Properties.copy(Blocks.ANCIENT_DEBRIS).sound(SoundType.DEEPSLATE), UniformInt.of(11,14), List.of(BlockTags.NEEDS_DIAMOND_TOOL, BlockTags.MINEABLE_WITH_PICKAXE), ITVItems.ENDERITE));

    /**
     * A RegistryObject of type "Block" that represents a Lonsdaleite Ore block
     */
    public static final RegistryObject<Block> LONSDALEITE_ORE = registerSimpleBlock("lonsdaleite_ore", () -> new ITVOreBlock(BlockBehaviour.Properties.copy(Blocks.ANCIENT_DEBRIS).sound(SoundType.STONE), UniformInt.of(8,11), List.of(BlockTags.NEEDS_DIAMOND_TOOL, BlockTags.MINEABLE_WITH_PICKAXE), ITVItems.LONSDALEITE));

    /**
     * A RegistryObject of type "Block" that represents an Endstone Lonsdaleite Ore block
     */
    public static final RegistryObject<Block> ENDSTONE_LONSDALEITE_ORE = registerSimpleBlock("endstone_lonsdaleite_ore", () -> new ITVOreBlock(BlockBehaviour.Properties.copy(Blocks.ANCIENT_DEBRIS).sound(SoundType.STONE), UniformInt.of(10, 12), List.of(BlockTags.NEEDS_DIAMOND_TOOL, BlockTags.MINEABLE_WITH_PICKAXE), ITVItems.LONSDALEITE));

    /**
     * A RegistryObject of type "Block" that represents a Meteorite Lonsdaleite Ore block
     */
    public static final RegistryObject<Block> METEORITE_LONSDALEITE_ORE = registerSimpleBlock("meteorite_lonsdaleite_ore", () -> new ITVOreBlock(BlockBehaviour.Properties.copy(Blocks.ANCIENT_DEBRIS).sound(SoundType.DEEPSLATE), UniformInt.of(20,30), List.of(Tags.Blocks.NEEDS_NETHERITE_TOOL, BlockTags.MINEABLE_WITH_PICKAXE), ITVItems.LONSDALEITE));

    /**
     * A RegistryObject of type "Block" that represents a Chorus Log
     */
    public static final RegistryObject<Block> CHORUS_LOG = registerSimpleBlock("chorus_log", () -> new ITVWoodLogBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).strength(3F), List.of(BlockTags.NEEDS_IRON_TOOL, BlockTags.MINEABLE_WITH_AXE, BlockTags.LOGS_THAT_BURN), false));
    public static final RegistryObject<Block> CHORUS_WOOD = registerSimpleBlock("chorus_wood", () -> new ITVWoodLogBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).strength(3F), List.of(BlockTags.NEEDS_IRON_TOOL, BlockTags.MINEABLE_WITH_AXE, BlockTags.LOGS_THAT_BURN), false));
    public static final RegistryObject<Block> STRIPPED_CHORUS_LOG = registerSimpleBlock("stripped_chorus_log", () -> new ITVWoodLogBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).strength(3F), List.of(BlockTags.NEEDS_IRON_TOOL, BlockTags.MINEABLE_WITH_AXE, BlockTags.LOGS_THAT_BURN), true));
    public static final RegistryObject<Block> STRIPPED_CHORUS_WOOD = registerSimpleBlock("stripped_chorus_wood", () -> new ITVWoodLogBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD).strength(3F), List.of(BlockTags.NEEDS_IRON_TOOL, BlockTags.MINEABLE_WITH_AXE, BlockTags.LOGS_THAT_BURN), true));
    //public static final RegistryObject<Block> CHORUS_PLANKS = registerSimpleBlock("chorus_planks", () -> new ITVSimpleBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).strength(3F)));
    //public static final RegistryObject<Block> CHORUS_LEAVES = registerSimpleBlock("chorus_leaves", () -> new ITVLeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block)
    {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block>RegistryObject<T> registerSimpleBlock(String name, Supplier<T> block)
    {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerSimpleBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block)
    {
        return ITVItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static<T extends Block>RegistryObject<Item> registerSimpleBlockItem(String name, RegistryObject<T> block)
    {
        return ITVItems.ITEMS.register(name, () -> new ITVSimpleBlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
    }
}
