package net.laserdiamond.intothevoid.blocks;

import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.item.ITVItems;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ITVBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, IntoTheVoid.MODID);
    public static final RegistryObject<Block> LONSDALEITE_BLOCK = registerSimpleBlock("lonsdaleite_block", () -> new ITVSelfDropBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).sound(SoundType.METAL), BlockTags.NEEDS_DIAMOND_TOOL));
    public static final RegistryObject<Block> REFINED_LONSDALEITE_BLOCK = registerSimpleBlock("refined_lonsdaleite_block", () -> new ITVSelfDropBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).sound(SoundType.METAL), BlockTags.NEEDS_DIAMOND_TOOL));
    public static final RegistryObject<Block> ENDERITE_BLOCK = registerSimpleBlock("enderite_block", () -> new ITVSelfDropBlock(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK).sound(SoundType.NETHERITE_BLOCK), BlockTags.NEEDS_DIAMOND_TOOL));

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
