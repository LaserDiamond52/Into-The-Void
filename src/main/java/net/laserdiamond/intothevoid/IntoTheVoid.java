package net.laserdiamond.intothevoid;

import com.mojang.logging.LogUtils;
import net.laserdiamond.intothevoid.block.ITVBlocks;
import net.laserdiamond.intothevoid.block.entity.ITVBlockEntities;
import net.laserdiamond.intothevoid.client.ITVKeyBindings;
import net.laserdiamond.intothevoid.effects.ITVEffects;
import net.laserdiamond.intothevoid.entity.ITVEntities;
import net.laserdiamond.intothevoid.entity.client.*;
import net.laserdiamond.intothevoid.item.CreativeTabs;
import net.laserdiamond.intothevoid.item.GKeyAbility;
import net.laserdiamond.intothevoid.item.ITVItems;
import net.laserdiamond.intothevoid.item.ITVSimpleItem;
import net.laserdiamond.intothevoid.item.equipment.tools.dragonborne.DragonborneCooldown;
import net.laserdiamond.intothevoid.network.PacketHandler;
import net.laserdiamond.intothevoid.recipe.ITVRecipes;
import net.laserdiamond.intothevoid.screen.ITVMenuTypes;
import net.laserdiamond.intothevoid.screen.Refinery.RefineryScreen;
import net.laserdiamond.intothevoid.util.ITVWoodTypes;
import net.laserdiamond.intothevoid.worldgen.biome.ITVTerrablender;
import net.laserdiamond.intothevoid.worldgen.biome.surface.ITVSurfaceRules;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.ForgeSpawnEggItem;
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
import terrablender.api.SurfaceRuleManager;

import java.util.ArrayList;
import java.util.List;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(IntoTheVoid.MODID)
public class IntoTheVoid
{
    // Define mod id in a common place for everything to reference
    /**
     * The "Into The Void" Mod ID. This is used as a common place for everything to reference
     */
    public static final String MODID = "into_the_void";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    /**
     * A list of classes that inherit the GKeyAbility interface are added into this list for functionality of the ability key
     */
    public static final List<GKeyAbility> G_KEY_ABILITIES = new ArrayList<>();

    /**
     * This is the main method of this mod. Listeners and Events that were not registered automatically are registers and added here
     */
    public IntoTheVoid()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus(); // Mod event bus used to register events

        registerListeners(modEventBus);
        DragonborneCooldown.setupCooldown();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
    }

    /**
     * Registers any Common Setup needed for the Forge Mod Loader
     * <p>Currently registers the surface rules for the Purpur Forest biome</p>
     * @param event FMLCommonSetupEvent
     */
    private void commonSetup(final FMLCommonSetupEvent event)
    {
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, IntoTheVoid.MODID, ITVSurfaceRules.makePurpurForestRules());
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
            } else if (item.get() instanceof ForgeSpawnEggItem)
            {
                CreativeTabs.addItemToTab(item, CreativeTabs.MOBS, event);
            }

        }
        CreativeTabs.addItemToTab(ITVItems.REFINED_END_CRYSTAL, CreativeTabs.MATERIALS_TAB, event);
        CreativeTabs.addBlockItemsToTab(ITVBlocks.BLOCKS.getEntries(), CreativeTabs.BLOCKS_TAB, event);
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call

    /**
     * Runs when the server is starting
     * @param event ServerStaringEvent
     */
    @SuppressWarnings("unused")
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent

    /**
     * Registers all client-side events on the Mod bus
     */
    @SuppressWarnings("unused")
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        /**
         * Runs on client set up. Registers entity renderers for the client
         * @param event FMLClientSetupEvent
         */
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            Sheets.addWoodType(ITVWoodTypes.PURPUR);
            MenuScreens.register(ITVMenuTypes.REFINERY_MENU.get(), RefineryScreen::new);

            EntityRenderers.register(ITVEntities.PURPUR_WOOD_BOAT.get(), pContext -> new ITVBoatRenderer(pContext, false));
            EntityRenderers.register(ITVEntities.PURPUR_WOOD_CHEST_BOAT.get(), pContext -> new ITVBoatRenderer(pContext, true));
            EntityRenderers.register(ITVEntities.VOID_PIRATE.get(), VoidPirateRenderer::new);
            EntityRenderers.register(ITVEntities.ENDER_DRAGON_HATCHLING.get(), EnderDragonHatchlingRenderer::new);
            EntityRenderers.register(ITVEntities.EVOLVED_ENDERMITE_ENTITY.get(), EvolvedEndermiteRenderer::new);
            EntityRenderers.register(ITVEntities.WATCHER_BOSS.get(), WatcherBossRenderer::new);
            EntityRenderers.register(ITVEntities.WATCHER_MINION.get(), WatcherMinionRenderer::new);


            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());

        }

        /**
         * Registers key mappings/bindings of this mod
         * @param event RegisterKeyMappingsEvent
         */
        @SubscribeEvent
        public static void registerKeys(RegisterKeyMappingsEvent event)
        {
            event.register(ITVKeyBindings.INSTANCE.abilityActivate);
        }

        /**
         * Registers block entity renderers for the client
         * @param event EntityRenderersEvent.RegisterRenderers
         */
        @SubscribeEvent
        public static void registerBlockEntityRenderer(EntityRenderersEvent.RegisterRenderers event)
        {
            event.registerBlockEntityRenderer(ITVBlockEntities.ITV_SIGN.get(), SignRenderer::new);
            event.registerBlockEntityRenderer(ITVBlockEntities.ITV_HANGING_SIGN.get(), HangingSignRenderer::new);
        }

        /**
         * Registers the layer definitions of the entities of this mod for the client
         * @param event EntityRenderersEvent.RegisterLayerDefinitions
         */
        @SubscribeEvent
        public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event)
        {
            event.registerLayerDefinition(ITVModelLayers.PURPUR_WOOD_BOAT_LAYER, BoatModel::createBodyModel);
            event.registerLayerDefinition(ITVModelLayers.PURPUR_WOOD_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);
            event.registerLayerDefinition(ITVModelLayers.VOID_PIRATE, VoidPirateModel::createBodyLayer);
            event.registerLayerDefinition(ITVModelLayers.ENDER_DRAGON_HATCHLING, EnderDragonHatchlingModel::createBodyLayer);
            event.registerLayerDefinition(ITVModelLayers.EVOLVED_ENDERMITE, EvolvedEndermiteModel::createBodyLayer);
            event.registerLayerDefinition(ITVModelLayers.WATCHER_BOSS, WatcherModel::createBodyLayer);
            event.registerLayerDefinition(ITVModelLayers.WATCHER_MINION, WatcherModel::createBodyLayer);
        }

        /**
         * Registers the PacketHandler on the client side for this mod
         * @param event FMLCommonSetupEvent
         */
        @SubscribeEvent
        public static void commonSetup(final FMLCommonSetupEvent event)
        {
            PacketHandler.register();
        }
    }

    /**
     * Registers all listeners for use in this mod.
     * @param eventBus The Event Bus of this mod
     */
    private void registerListeners(IEventBus eventBus)
    {
        ITVItems.register(eventBus); // Register items in DeferredRegistry for items
        ITVBlocks.register(eventBus); // Register blocks in DeferredRegistry for blocks
        ITVBlockEntities.register(eventBus); // Register block entities in DeferredRegistry for block entities
        ITVEffects.register(eventBus); // Register mob effects in DeferredRegistry for effects
        CreativeTabs.register(eventBus); // Register Creative Mode Tabs
        ITVMenuTypes.register(eventBus); // Register Menus and GUIs
        ITVRecipes.register(eventBus); // Registers Custom Recipe Serializers
        ITVEntities.register(eventBus); // Registers Entities of this mod
        ITVTerrablender.registerBiomes(); // Registers biomes for use with Terrablender
    }
}
