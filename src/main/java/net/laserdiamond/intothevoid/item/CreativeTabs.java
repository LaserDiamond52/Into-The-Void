package net.laserdiamond.intothevoid.item;

import net.laserdiamond.intothevoid.IntoTheVoid;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collection;

public class CreativeTabs {

    private static final String CREATIVE_TAB_ID = "creative_tab.";

    /**
     * A DeferredRegister of type "CreativeModeTab" that is used to register Creative Mode Tabs
     */
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, IntoTheVoid.MODID);

    /**
     * A RegistryObject of type "CreativeModeTab" that represents the main creative mode tab that includes every item in this mod
     */
    public static final RegistryObject<CreativeModeTab> MAIN_TAB = CREATIVE_TABS.register("into_the_void_tab", () -> CreativeModeTab.builder().icon(() -> new ItemStack(Items.ENDER_EYE.asItem())).title(Component.translatable(CREATIVE_TAB_ID + "into_the_void_tab")).build());

    /**
     * A RegistryObject of type "CreativeModeTab" that represents a creative mode tab that contains all material/crafting ingredients for items in this mod
     */
    public static final RegistryObject<CreativeModeTab> MATERIALS_TAB = CREATIVE_TABS.register("into_the_void_materials", () -> CreativeModeTab.builder().icon(() -> new ItemStack(ITVItems.ENDERITE.get())).title(Component.translatable(CREATIVE_TAB_ID + "into_the_void_materials")).build());

    /**
     * A RegistryObject of type "CreativeModeTab" that represents a creative mode tab that contains all blocks in this mod
     */
    public static final RegistryObject<CreativeModeTab> BLOCKS_TAB = CREATIVE_TABS.register("into_the_void_blocks", () -> CreativeModeTab.builder().icon(() -> new ItemStack(Items.END_PORTAL_FRAME.asItem())).title(Component.translatable(CREATIVE_TAB_ID + "into_the_void_blocks")).build());

    /**
     * A RegistryObject of type "CreativeModeTab" that represents a creative mode tab that contains all equipment/gear items in this mod
     */
    public static final RegistryObject<CreativeModeTab> GEAR_TAB = CREATIVE_TABS.register("into_the_void_gear", () -> CreativeModeTab.builder().icon(() -> new ItemStack(ITVItems.ENDERITE_CHESTPLATE.get())).title(Component.translatable(CREATIVE_TAB_ID + "into_the_void_gear")).build());

    /**
     * A RegistryObject of type "CreativeModeTab" that represents a creative mode tab that contains spawn eggs for all mobs in this mod
     */
    public static final RegistryObject<CreativeModeTab> MOBS = CREATIVE_TABS.register("into_the_void_mobs", () -> CreativeModeTab.builder().icon(() -> new ItemStack(Items.ENDER_DRAGON_SPAWN_EGG)).title(Component.translatable(CREATIVE_TAB_ID + "into_the_void_mobs")).build());

    /**
     * Adds an item to a creative mode tab
     * @param item The RegistryObject of type "Item" to add
     * @param creativeTab The creative mode tab that will receive the item
     * @param event BuildCreativeModeTabContentsEvent
     */
    public static void addItemToTab(RegistryObject<Item> item, RegistryObject<CreativeModeTab> creativeTab, BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == creativeTab.getKey())
        {
            event.accept(item.get());
        }
    }

    /**
     * Adds a collection of items to a creative mode tab
     * @param items A Collection of RegistryObjects of type "Item" to add
     * @param creativeTab The creative mode tab that will receive the items
     * @param event BuildCreativeModeTabContentsEvent
     */
    public static void addItemsToTab(Collection<RegistryObject<Item>> items, RegistryObject<CreativeModeTab> creativeTab, BuildCreativeModeTabContentsEvent event)
    {
        for (RegistryObject<Item> item : items)
        {
            addItemToTab(item, creativeTab, event);
        }
    }

    /**
     * Adds a block to a creative mode tab
     * @param block The RegistryObject of type "Block" to add
     * @param creativeTab The creative mode tab that will receive the block
     * @param event BuildCreativeModeTabContentsEvent
     */
    public static void addBlockItemToTab(RegistryObject<Block> block, RegistryObject<CreativeModeTab> creativeTab, BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == creativeTab.getKey())
        {
            event.accept(block.get());
        }
    }

    /**
     * Adds a Collection of blocks to a creative mode tab
     * @param blocks A Collection of RegistryObjects of type "Blocks" to add
     * @param creativeTab The creative mode tab that will receive the items
     * @param event BuildCreativeModeTabContentsEvent
     */
    public static void addBlockItemsToTab(Collection<RegistryObject<Block>> blocks, RegistryObject<CreativeModeTab> creativeTab, BuildCreativeModeTabContentsEvent event)
    {
        for (RegistryObject<Block> block : blocks)
        {
            addBlockItemToTab(block, creativeTab, event);
        }
    }

    /**
     * Registers all the Creative mode tabs under the CREATIVE_TABS DeferredRegister
     * @param eventBus The mod's event bus
     */
    public static void register(IEventBus eventBus)
    {
        CREATIVE_TABS.register(eventBus);
    }

}
