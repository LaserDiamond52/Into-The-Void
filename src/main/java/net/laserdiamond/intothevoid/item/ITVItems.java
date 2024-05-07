package net.laserdiamond.intothevoid.item;

import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.item.equipment.armor.armorItems.EnderiteArmorItem;
import net.laserdiamond.intothevoid.item.equipment.armor.armorItems.LonsdaleiteArmorItem;
import net.laserdiamond.intothevoid.item.equipment.tools.*;
import net.laserdiamond.intothevoid.item.equipment.tools.enderite.*;
import net.laserdiamond.intothevoid.item.equipment.tools.lonsdaleite.*;
import net.laserdiamond.intothevoid.item.ingredients.ITVSimpleIngredientItem;
import net.laserdiamond.intothevoid.item.ingredients.ITVSimpleMineralItem;
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

    private static final float SWORD_SPEED_MODIFIER = -2.4f;
    private static final float PICKAXE_SPEED_MODIFIER = -2.8f;
    private static final float AXE_SPEED_MODIFIER = -3f;
    private static final float SHOVEL_SPEED_MODIFIER = -3f;
    private static final float HOE_SPEED_MODIFIER = 0f;


    /**
     * A deferred register object of type "Item" that is used to register items
     */
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, IntoTheVoid.MODID);

    // Ingredients/Materials

    /**
     * RegistryObject of type "Item" that represents Lonsdaleite
     */
    public static final RegistryObject<Item> LONSDALEITE = ITVItems.ITEMS.register("lonsdaleite", () -> new ITVSimpleMineralItem(new Item.Properties()));

    /**
     * RegistryObject of type "Item" that represents Refined Lonsdaleite
     */
    public static final RegistryObject<Item> REFINED_LONSDALEITE = ITVItems.ITEMS.register("refined_lonsdaleite", () -> new ITVSimpleMineralItem(new Item.Properties()));

    /**
     * RegistryObject of type "Item" that represents Enderite
     */
    public static final RegistryObject<Item> ENDERITE = ITVItems.ITEMS.register("enderite", () -> new ITVSimpleMineralItem(new Item.Properties()));

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

    /**
     * RegistryObject of type "Item" that represents a Lonsdaleite sword
     */
    public static final RegistryObject<Item> LONSDALEITE_SWORD = ITVItems.ITEMS.register("lonsdaleite_sword",
            () -> new LonsdaleiteSwordItem(3, SWORD_SPEED_MODIFIER, new Item.Properties()));

    /**
     * RegistryObject of type "Item" that represents a Lonsdaleite pickaxe
     */
    public static final RegistryObject<Item> LONSDALEITE_PICKAXE = ITVItems.ITEMS.register("lonsdaleite_pickaxe",
            () -> new LonsdaleitePickaxeItem(1, PICKAXE_SPEED_MODIFIER, new Item.Properties()));

    /**
     * RegistryObject of type "Item" that represents a Lonsdaleite axe
     */
    public static final RegistryObject<Item> LONSDALEITE_AXE = ITVItems.ITEMS.register("lonsdaleite_axe",
            () -> new LonsdaleiteAxeItem(6, AXE_SPEED_MODIFIER   , new Item.Properties()));

    /**
     * RegistryObject of type "Item" that represents a Lonsdaleite shovel
     */
    public static final RegistryObject<Item> LONSDALEITE_SHOVEL = ITVItems.ITEMS.register("lonsdaleite_shovel",
            () -> new LonsdaleiteShovelItem(0, SHOVEL_SPEED_MODIFIER, new Item.Properties()));

    /**
     * RegistryObject of type "Item" that represents a Lonsdaleite hoe
     */
    public static final RegistryObject<Item> LONSDALEITE_HOE = ITVItems.ITEMS.register("lonsdaleite_hoe",
            () -> new LonsdaleiteHoeItem(1, HOE_SPEED_MODIFIER, new Item.Properties()));

    /**
     * RegistryObject of type "Item" that represents an Enderite sword
     */
    public static final RegistryObject<Item> ENDERITE_SWORD = ITVItems.ITEMS.register("enderite_sword",
            () -> new EnderiteSwordItem(3, SWORD_SPEED_MODIFIER, new Item.Properties()));

    /**
     * RegistryObject of type "Item" that represents an Enderite pickaxe
     */
    public static final RegistryObject<Item> ENDERITE_PICKAXE = ITVItems.ITEMS.register("enderite_pickaxe",
            () -> new EnderitePickaxeItem(1, PICKAXE_SPEED_MODIFIER, new Item.Properties()));

    /**
     * RegistryObject of type "Item" that represents an Enderite axe
     */
    public static final RegistryObject<Item> ENDERITE_AXE = ITVItems.ITEMS.register("enderite_axe",
            () -> new EnderiteAxeItem(6, AXE_SPEED_MODIFIER, new Item.Properties()));

    /**
     * RegistryObject of type "Item" that represents an Enderite shovel
     */
    public static final RegistryObject<Item> ENDERITE_SHOVEL = ITVItems.ITEMS.register("enderite_shovel",
            () -> new EnderiteShovelItem(0, SHOVEL_SPEED_MODIFIER, new Item.Properties()));

    /**
     * RegistryObject of type "Item" that represents an Enderite hoe
     */
    public static final RegistryObject<Item> ENDERITE_HOE = ITVItems.ITEMS.register("enderite_hoe",
            () -> new EnderiteHoeItem(1, HOE_SPEED_MODIFIER, new Item.Properties()));



    /**
     * Registers all the items under the ITEMS DeferredRegister
     * @param eventBus The mod's event bus
     */
    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
