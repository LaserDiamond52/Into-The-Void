package net.laserdiamond.intothevoid.item;

import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.item.equipment.armor.armorItems.EnderiteArmorItem;
import net.laserdiamond.intothevoid.item.equipment.armor.armorItems.LonsdaleiteArmorItem;
import net.laserdiamond.intothevoid.item.equipment.tools.ITVToolTiers;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that contains Registries and Lists for all "Into The Void" mod items
 */
public class ITVItems {

    /**
     * A deferred register object of type "Item" that is used to register items
     */
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, IntoTheVoid.MODID);

    // Ingredients/Materials

    /**
     * RegistryObject of type "Item" that represents Lonsdaleite
     */
    public static final RegistryObject<Item> LONSDALEITE = ITVItems.ITEMS.register("lonsdaleite", () -> new ITVSimpleItem(new Item.Properties()));

    /**
     * RegistryObject of type "Item" that represents Refined Lonsdaleite
     */
    public static final RegistryObject<Item> REFINED_LONSDALEITE = ITVItems.ITEMS.register("refined_lonsdaleite", () -> new ITVSimpleItem(new Item.Properties()));

    /**
     * RegistryObject of type "Item" that represents Enderite
     */
    public static final RegistryObject<Item> ENDERITE = ITVItems.ITEMS.register("enderite", () -> new ITVSimpleItem(new Item.Properties()));

    // Armor

    /**
     * RegistryObject of type "Item" that represents a Lonsdaleite helmet
     */
    public static final RegistryObject<Item> LONSDALEITE_HELMET = ITVItems.ITEMS.register("lonsdaleite_helmet",
            () -> new LonsdaleiteArmorItem(ArmorItem.Type.HELMET, new Item.Properties()));

    /**
     * RegistryObject of type "Item" that represents a Lonsdaleite chestplate
     */
    public static final RegistryObject<Item> LONSDALEITE_CHESTPLATE = ITVItems.ITEMS.register("lonsdaleite_chestplate",
            () -> new LonsdaleiteArmorItem(ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    /**
     * RegistryObject of type "Item" that represents a Lonsdaleite leggings
     */
    public static final RegistryObject<Item> LONSDALEITE_LEGGINGS = ITVItems.ITEMS.register("lonsdaleite_leggings",
            () -> new LonsdaleiteArmorItem(ArmorItem.Type.LEGGINGS, new Item.Properties()));

    /**
     * RegistryObject of type "Item" that represents a Lonsdaleite boots
     */
    public static final RegistryObject<Item> LONSDALEITE_BOOTS = ITVItems.ITEMS.register("lonsdaleite_boots",
            () -> new LonsdaleiteArmorItem(ArmorItem.Type.BOOTS, new Item.Properties()));

    /**
     * RegistryObject of type "Item" that represents an Enderite helmet
     */
    public static final RegistryObject<Item> ENDERITE_HELMET = ITVItems.ITEMS.register("enderite_helmet",
            () -> new EnderiteArmorItem(ArmorItem.Type.HELMET, new Item.Properties()));

    /**
     * RegistryObject of type "Item" that represents an Enderite chestplate
     */
    public static final RegistryObject<Item> ENDERITE_CHESTPLATE = ITVItems.ITEMS.register("enderite_chestplate",
            () -> new EnderiteArmorItem(ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    /**
     * RegistryObject of type "Item" that represents an Enderite leggings
     */
    public static final RegistryObject<Item> ENDERITE_LEGGINGS = ITVItems.ITEMS.register("enderite_leggings",
            () -> new EnderiteArmorItem(ArmorItem.Type.LEGGINGS, new Item.Properties()));

    /**
     * RegistryObject of type "Item" that represents an Enderite boots
     */
    public static final RegistryObject<Item> ENDERITE_BOOTS = ITVItems.ITEMS.register("enderite_boots",
            () -> new EnderiteArmorItem(ArmorItem.Type.BOOTS, new Item.Properties()));

    // Tools/Weapons

    public static final RegistryObject<Item> LONSDALEITE_SWORD = ITVItems.ITEMS.register("lonsdaleite_sword",
            () -> new SwordItem(ITVToolTiers.LONSDALEITE, 4, 2, new Item.Properties()));

    public static final RegistryObject<Item> LONSDALEITE_PICKAXE = ITVItems.ITEMS.register("lonsdaleite_pickaxe",
            () -> new PickaxeItem(ITVToolTiers.LONSDALEITE, 1, 1, new Item.Properties()));

    public static final RegistryObject<Item> LONSDALEITE_AXE = ITVItems.ITEMS.register("lonsdaleite_axe",
            () -> new AxeItem(ITVToolTiers.LONSDALEITE, 6, 1, new Item.Properties()));

    public static final RegistryObject<Item> LONSDALEITE_SHOVEL = ITVItems.ITEMS.register("lonsdaleite_shovel",
            () -> new ShovelItem(ITVToolTiers.LONSDALEITE, 0, 1, new Item.Properties()));

    public static final RegistryObject<Item> LONSDALEITE_HOE = ITVItems.ITEMS.register("lonsdaleite_hoe",
            () -> new HoeItem(ITVToolTiers.LONSDALEITE, 1, 1, new Item.Properties()));



    /**
     * A list of Item Registry Objects that are materials
     */
    public static final List<RegistryObject<Item>> MATERIAL_ITEMS = new ArrayList<>();
    static
    {
        MATERIAL_ITEMS.add(LONSDALEITE);
        MATERIAL_ITEMS.add(REFINED_LONSDALEITE);
        MATERIAL_ITEMS.add(ENDERITE);
    }

    /**
     * Registers all the items under the ITEMS DeferredRegister
     * @param eventBus The mod's event bus
     */
    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
