package net.laserdiamond.intothevoid;

import com.mojang.logging.LogUtils;
import net.laserdiamond.intothevoid.blocks.ITVBlocks;
import net.laserdiamond.intothevoid.item.CreativeTabs;
import net.laserdiamond.intothevoid.item.ITVItems;
import net.laserdiamond.intothevoid.item.ITVSimpleItem;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(IntoTheVoid.MODID)
public class IntoTheVoid
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "into_the_void";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public IntoTheVoid()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ITVItems.register(modEventBus); // Register items in DeferredRegistry for items
        ITVBlocks.register(modEventBus); // Register blocks in DeferredRegistry for blocks
        CreativeTabs.register(modEventBus); // Register Creative Mode Tabs
        registerListeners(modEventBus);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {


    }

    /**
     * Organizes all items/blocks into their respective creative mode tabs
     * @param event BuildCreativeModeTabContentsEvent
     */
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        for (RegistryObject<Item> item : ITVItems.ITEMS.getEntries())
        {
            CreativeTabs.addItemToTab(item, CreativeTabs.MAIN_TAB, event);

            if (item.get() instanceof ArmorItem)
            {
                CreativeTabs.addItemToTab(item, CreativeTabs.GEAR_TAB, event);
            } else if (item.get() instanceof SwordItem)
            {
                CreativeTabs.addItemToTab(item, CreativeTabs.GEAR_TAB, event);
            } else if (item.get() instanceof PickaxeItem)
            {
                CreativeTabs.addItemToTab(item, CreativeTabs.GEAR_TAB, event);
            } else if (item.get() instanceof AxeItem)
            {
                CreativeTabs.addItemToTab(item, CreativeTabs.GEAR_TAB, event);
            } else if (item.get() instanceof ShovelItem)
            {
                CreativeTabs.addItemToTab(item, CreativeTabs.GEAR_TAB, event);
            } else if (item.get() instanceof HoeItem)
            {
                CreativeTabs.addItemToTab(item, CreativeTabs.GEAR_TAB, event);
            } else if (item.get() instanceof ITVSimpleItem)
            {
                CreativeTabs.addItemToTab(item, CreativeTabs.MATERIALS_TAB, event);
            }

        }
        CreativeTabs.addBlockItemsToTab(ITVBlocks.BLOCKS.getEntries(), CreativeTabs.BLOCKS_TAB, event);
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }

    private void registerListeners(IEventBus eventBus)
    {

    }
}
