package net.laserdiamond.intothevoid.block;

import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.item.ITVItems;
import net.laserdiamond.intothevoid.util.ITVTags;
import net.laserdiamond.intothevoid.util.ITVWoodTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
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
     * A RegistryObject of type "Block" that represents a Purpur Log
     */
    public static final RegistryObject<Block> PURPUR_LOG = registerSimpleBlock("purpur_log", () -> new ITVWoodLogBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).strength(3F), List.of(BlockTags.NEEDS_IRON_TOOL, BlockTags.MINEABLE_WITH_AXE, BlockTags.LOGS_THAT_BURN, BlockTags.LOGS, ITVTags.Blocks.PURPUR_LOG), false, true, 5,5), List.of(ItemTags.LOGS_THAT_BURN, ItemTags.LOGS, ITVTags.Items.PURPUR_LOG));

    /**
     * A RegistryObject of type "Block" that represents Purpur Wood
     */
    public static final RegistryObject<Block> PURPUR_WOOD = registerSimpleBlock("purpur_wood", () -> new ITVWoodLogBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).strength(3F), List.of(BlockTags.NEEDS_IRON_TOOL, BlockTags.MINEABLE_WITH_AXE, BlockTags.LOGS_THAT_BURN, BlockTags.LOGS, ITVTags.Blocks.PURPUR_LOG), false, true, 5,5), List.of(ItemTags.LOGS_THAT_BURN, ItemTags.LOGS, ITVTags.Items.PURPUR_LOG));

    /**
     * A RegistryObject of type "Block" that represents a Stripped Purpur Log
     */
    public static final RegistryObject<Block> STRIPPED_PURPUR_LOG = registerSimpleBlock("stripped_purpur_log", () -> new ITVWoodLogBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).strength(3F), List.of(BlockTags.NEEDS_IRON_TOOL, BlockTags.MINEABLE_WITH_AXE, BlockTags.LOGS_THAT_BURN, BlockTags.LOGS, ITVTags.Blocks.PURPUR_LOG), true, true, 5, 5), List.of(ItemTags.LOGS_THAT_BURN, ItemTags.LOGS, ITVTags.Items.PURPUR_LOG));

    /**
     * A RegistryObject of type "Block" that represents Stripped Purpur wood
     */
    public static final RegistryObject<Block> STRIPPED_PURPUR_WOOD = registerSimpleBlock("stripped_purpur_wood", () -> new ITVWoodLogBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD).strength(3F), List.of(BlockTags.NEEDS_IRON_TOOL, BlockTags.MINEABLE_WITH_AXE, BlockTags.LOGS_THAT_BURN, BlockTags.LOGS, ITVTags.Blocks.PURPUR_LOG), true, true, 5, 5), List.of(ItemTags.LOGS_THAT_BURN, ItemTags.LOGS, ITVTags.Items.PURPUR_LOG));

    /**
     * A RegistryObject of type "Block" that represents Purpur Planks
     */
    public static final RegistryObject<Block> PURPUR_PLANKS = registerSimpleBlock("purpur_planks", () -> new ITVSelfDropBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).strength(3F), List.of(BlockTags.NEEDS_IRON_TOOL, BlockTags.MINEABLE_WITH_AXE, BlockTags.LOGS_THAT_BURN, BlockTags.PLANKS)), List.of(ItemTags.PLANKS));

    public static final RegistryObject<Block> PURPUR_WOOD_SLAB = registerSimpleBlock("purpur_wood_slab", () -> new ITVSlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB), List.of(BlockTags.NEEDS_IRON_TOOL, BlockTags.MINEABLE_WITH_AXE, BlockTags.WOODEN_SLABS)), List.of(ItemTags.WOODEN_SLABS));
    public static final RegistryObject<Block> PURPUR_WOOD_STAIRS = registerSimpleBlock("purpur_wood_stairs", () -> new ITVStairBlock(() -> PURPUR_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS), List.of(BlockTags.NEEDS_IRON_TOOL, BlockTags.MINEABLE_WITH_AXE, BlockTags.WOODEN_STAIRS)), List.of(ItemTags.WOODEN_STAIRS));
    public static final RegistryObject<Block> PURPUR_WOOD_PRESSURE_PLATE = registerSimpleBlock("purpur_wood_pressure_plate", () -> new ITVPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE), BlockSetType.OAK, List.of(BlockTags.NEEDS_IRON_TOOL, BlockTags.MINEABLE_WITH_AXE, BlockTags.WOODEN_PRESSURE_PLATES)), List.of(ItemTags.WOODEN_PRESSURE_PLATES));
    public static final RegistryObject<Block> PURPUR_WOOD_DOOR = registerSimpleBlock("purpur_wood_door", () -> new ITVDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR), BlockSetType.OAK, List.of(BlockTags.NEEDS_IRON_TOOL, BlockTags.MINEABLE_WITH_AXE, BlockTags.DOORS)), List.of(ItemTags.DOORS));
    public static final RegistryObject<Block> PURPUR_WOOD_TRAPDOOR = registerSimpleBlock("purpur_wood_trapdoor", () -> new ITVTrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR), BlockSetType.OAK, List.of(BlockTags.NEEDS_IRON_TOOL, BlockTags.MINEABLE_WITH_AXE, BlockTags.WOODEN_TRAPDOORS)), List.of(ItemTags.WOODEN_TRAPDOORS));
    public static final RegistryObject<Block> PURPUR_WOOD_FENCE = registerSimpleBlock("purpur_wood_fence", () -> new ITVFenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE), List.of(BlockTags.NEEDS_IRON_TOOL, BlockTags.MINEABLE_WITH_AXE, BlockTags.WOODEN_FENCES)), List.of(ItemTags.WOODEN_FENCES));
    public static final RegistryObject<Block> PURPUR_WOOD_FENCE_GATE = registerSimpleBlock("purpur_wood_fence_gate", () -> new ITVFenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE), SoundEvents.CHERRY_WOOD_FENCE_GATE_OPEN, SoundEvents.CHERRY_WOOD_FENCE_GATE_CLOSE, List.of(BlockTags.NEEDS_IRON_TOOL, BlockTags.MINEABLE_WITH_AXE, BlockTags.FENCE_GATES)), List.of(ItemTags.FENCE_GATES));
    public static final RegistryObject<Block> PURPUR_WOOD_BUTTON = registerSimpleBlock("purpur_wood_button", () -> new ITVButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON), BlockSetType.OAK, 10, true));

    public static final RegistryObject<Block> PURPUR_WOOD_SIGN = BLOCKS.register("purpur_wood_sign",
            () -> new ITVStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), ITVWoodTypes.PURPUR));

    public static final RegistryObject<Block> PURPUR_WOOD_HANGING_SIGN = BLOCKS.register("purpur_wood_hanging_sign",
            () -> new ITVHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_HANGING_SIGN), ITVWoodTypes.PURPUR));

    public static final RegistryObject<Block> PURPUR_WOOD_WALL_SIGN = BLOCKS.register("purpur_wood_wall_sign",
            () -> new ITVWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), ITVWoodTypes.PURPUR));

    public static final RegistryObject<Block> PURPUR_WOOD_WALL_HANGING_SIGN = BLOCKS.register("purpur_wood_wall_hanging_sign",
            () -> new ITVWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_HANGING_SIGN), ITVWoodTypes.PURPUR));

    //public static final RegistryObject<Block> PURPUR_LEAVES = registerSimpleBlock("purpur_leaves", () -> new ITVLeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));

    public static final RegistryObject<Block> REFINERY = registerSimpleBlock("refinery",
            () -> new RefineryBlock(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion(), List.of(BlockTags.MINEABLE_WITH_PICKAXE, Tags.Blocks.NEEDS_WOOD_TOOL)));
    public enum WoodBlocks
    {
        PURPUR (PURPUR_LOG, PURPUR_WOOD, STRIPPED_PURPUR_LOG, STRIPPED_PURPUR_WOOD,
                PURPUR_PLANKS, PURPUR_WOOD_SLAB, PURPUR_WOOD_STAIRS, PURPUR_WOOD_PRESSURE_PLATE,
                PURPUR_WOOD_DOOR, PURPUR_WOOD_TRAPDOOR, PURPUR_WOOD_FENCE, PURPUR_WOOD_FENCE_GATE,
                PURPUR_WOOD_BUTTON, PURPUR_WOOD_SIGN, PURPUR_WOOD_HANGING_SIGN, PURPUR_WOOD_WALL_SIGN,
                PURPUR_WOOD_WALL_HANGING_SIGN, ITVTags.Blocks.PURPUR_LOG, ITVTags.Items.PURPUR_LOG);

        private final RegistryObject<Block> logBlock, woodBlock, strippedLogBlock, strippedWoodBlock,
                planks, slab, stairs, pressurePlate, door, trapDoor, fence, fenceGate, button, sign,
                hangingSign, wallSign, wallHangingSign;
        private final TagKey<Block> blockTagKey;
        private final TagKey<Item> itemTagKey;

        WoodBlocks(RegistryObject<Block> logBlock, RegistryObject<Block> woodBlock, RegistryObject<Block> strippedLogBlock, RegistryObject<Block> strippedWoodBlock,
                   RegistryObject<Block> planks, RegistryObject<Block> slab, RegistryObject<Block> stairs, RegistryObject<Block> pressurePlate,
                   RegistryObject<Block> door, RegistryObject<Block> trapDoor, RegistryObject<Block> fence, RegistryObject<Block> fenceGate,
                   RegistryObject<Block> button, RegistryObject<Block> sign, RegistryObject<Block> hangingSign, RegistryObject<Block> wallSign,
                   RegistryObject<Block> wallHangingSign,TagKey<Block> blockTagKey, TagKey<Item> itemTagKey)
        {
            this.logBlock = logBlock;
            this.woodBlock = woodBlock;
            this.strippedLogBlock = strippedLogBlock;
            this.strippedWoodBlock = strippedWoodBlock;
            this.planks = planks;
            this.slab = slab;
            this.stairs = stairs;
            this.pressurePlate = pressurePlate;
            this.door = door;
            this.trapDoor = trapDoor;
            this.fence = fence;
            this.fenceGate = fenceGate;
            this.button = button;
            this.sign = sign;
            this.hangingSign = hangingSign;
            this.wallSign = wallSign;
            this.wallHangingSign = wallHangingSign;
            this.blockTagKey = blockTagKey;
            this.itemTagKey = itemTagKey;
        }

        public RegistryObject<Block> getLogBlock() {
            return logBlock;
        }

        public RegistryObject<Block> getWoodBlock() {
            return woodBlock;
        }

        public RegistryObject<Block> getStrippedLogBlock() {
            return strippedLogBlock;
        }

        public RegistryObject<Block> getStrippedWoodBlock() {
            return strippedWoodBlock;
        }

        public RegistryObject<Block> getPlanks() {
            return planks;
        }

        public RegistryObject<Block> getSlab() {
            return slab;
        }

        public RegistryObject<Block> getStairs() {
            return stairs;
        }

        public RegistryObject<Block> getPressurePlate() {
            return pressurePlate;
        }

        public RegistryObject<Block> getDoor() {
            return door;
        }

        public RegistryObject<Block> getTrapDoor() {
            return trapDoor;
        }

        public RegistryObject<Block> getFence() {
            return fence;
        }

        public RegistryObject<Block> getFenceGate() {
            return fenceGate;
        }

        public RegistryObject<Block> getButton() {
            return button;
        }

        public RegistryObject<Block> getSign() {
            return sign;
        }

        public RegistryObject<Block> getHangingSign() {
            return hangingSign;
        }

        public RegistryObject<Block> getWallSign() {
            return wallSign;
        }

        public RegistryObject<Block> getWallHangingSign() {
            return wallHangingSign;
        }

        public TagKey<Block> getBlockTagKey() {
            return blockTagKey;
        }

        public TagKey<Item> getItemTagKey() {
            return itemTagKey;
        }
    }

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

    private static <T extends Block>RegistryObject<T> registerSimpleBlock(String name, Supplier<T> block, List<TagKey<Item>> itemTags)
    {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerSimpleBlockItem(name, toReturn, itemTags);
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

    private static<T extends Block>RegistryObject<Item> registerSimpleBlockItem(String name, RegistryObject<T> block, List<TagKey<Item>> itemTags)
    {
        return ITVItems.ITEMS.register(name, () -> new ITVSimpleBlockItem(block.get(), new Item.Properties(), itemTags));
    }

    public static void register(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
    }
}
