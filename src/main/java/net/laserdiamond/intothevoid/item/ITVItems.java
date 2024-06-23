package net.laserdiamond.intothevoid.item;

import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.block.ITVBlocks;
import net.laserdiamond.intothevoid.entity.ITVEntities;
import net.laserdiamond.intothevoid.entity.boat.ITVBoatEntity;
import net.laserdiamond.intothevoid.item.equipment.armor.armorItems.DragonborneArmorItem;
import net.laserdiamond.intothevoid.item.equipment.armor.armorItems.EnderiteArmorItem;
import net.laserdiamond.intothevoid.item.equipment.armor.armorItems.LonsdaleiteArmorItem;
import net.laserdiamond.intothevoid.item.equipment.tools.dragonborne.DragonborneSwordItem;
import net.laserdiamond.intothevoid.item.equipment.tools.enderite.*;
import net.laserdiamond.intothevoid.item.equipment.tools.lonsdaleite.*;
import net.laserdiamond.intothevoid.item.ingredients.ITVSimpleMineralItem;
import net.laserdiamond.intothevoid.item.ingredients.refinedEndCrystal.RefinedEndCrystalItem;
import net.laserdiamond.intothevoid.item.ingredients.smithingTemplates.DragonborneSmithingTemplate;
import net.laserdiamond.intothevoid.item.ingredients.smithingTemplates.EnderiteSmithingTemplate;
import net.laserdiamond.intothevoid.item.misc.ITVBoatItem;
import net.laserdiamond.intothevoid.util.ITVTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
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
    public static final RegistryObject<Item> LONSDALEITE = ITEMS.register("lonsdaleite", () -> new ITVSimpleMineralItem(new Item.Properties(), List.of(ITVTags.Items.REFINERY_INGREDIENT)));

    /**
     * RegistryObject of type "Item" that represents Refined Lonsdaleite
     */
    public static final RegistryObject<Item> REFINED_LONSDALEITE = ITEMS.register("refined_lonsdaleite", () -> new ITVSimpleMineralItem(new Item.Properties(), List.of(ItemTags.BEACON_PAYMENT_ITEMS)));

    /**
     * RegistryObject of type "Item" that represents Enderite
     */
    public static final RegistryObject<Item> ENDERITE = ITEMS.register("enderite", () -> new ITVSimpleMineralItem(new Item.Properties(), List.of(ItemTags.BEACON_PAYMENT_ITEMS)));

    /**
     * RegistryObject of type "Item" that represents an Enderite Upgrade Smithing Template
     */
    public static final RegistryObject<Item> ENDERITE_SMITHING_TEMPLATE = ITEMS.register("enderite_upgrade_smithing_template", () -> new EnderiteSmithingTemplate(new Item.Properties()));

    /**
     * RegistryObject of type "Item" that represents a Dragonborne Upgrade Smithing Template
     */
    public static final RegistryObject<Item> DRAGONBORNE_SMITHING_TEMPLATE = ITEMS.register("dragonborne_upgrade_smithing_template", () -> new DragonborneSmithingTemplate(new Item.Properties()));

    /**
     * RegistryObject of type "Item" that represents an Iron Handle
     */
    public static final RegistryObject<Item> IRON_HANDLE = ITEMS.register("iron_handle", () -> new ITVSimpleItem(new Item.Properties()));

    /**
     * RegistryObject of type "Item" that represents a Dragon Bone
     */
    public static final RegistryObject<Item> DRAGON_BONE = ITEMS.register("dragon_bone", () -> new ITVSimpleItem(new Item.Properties()));

    /**
     * RegistryObject of type "Item" that represents Dragon Hide
     */
    public static final RegistryObject<Item> DRAGON_HIDE = ITEMS.register("dragon_hide", () -> new ITVSimpleItem(new Item.Properties()));

    /**
     * RegistryObject of type "Item" that represents a Refined End Crystal
     */
    public static final RegistryObject<Item> REFINED_END_CRYSTAL = ITEMS.register("refined_end_crystal", () -> new RefinedEndCrystalItem(new Item.Properties(), List.of(ItemTags.TRIM_MATERIALS)));

    public static final RegistryObject<Item> END_CORE = ITEMS.register("end_core", () -> new ITVSimpleItem(new Item.Properties(), List.of(ITVTags.Items.REFINERY_INGREDIENT)));

    // Block Entity

    /**
     * RegistryObject of type "Item" that represents a Purpur Wood Sign
     */
    public static final RegistryObject<Item> PURPUR_WOOD_SIGN = ITEMS.register("purpur_wood_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16), ITVBlocks.PURPUR_WOOD_SIGN.get(), ITVBlocks.PURPUR_WOOD_WALL_SIGN.get()));

    /**
     * RegistryObject of type "Item" that represents a Hanging Purpur Wood Sign
     */
    public static final RegistryObject<Item> PURPUR_WOOD_HANGING_SIGN = ITEMS.register("purpur_wood_hanging_sign",
            () -> new HangingSignItem(ITVBlocks.PURPUR_WOOD_HANGING_SIGN.get(), ITVBlocks.PURPUR_WOOD_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));

    // Spawnables

    /**
     * RegistryObject of type "Item" that represents a Purpur Wood Boat
     */
    public static final RegistryObject<Item> PURPUR_WOOD_BOAT = ITEMS.register("purpur_wood_boat",
            () -> new ITVBoatItem(false, ITVBoatEntity.Type.PURPUR, new Item.Properties()));

    /**
     * RegistryObject of type "Item" that represents a Purpur Wood Boat with a chest
     */
    public static final RegistryObject<Item> PURPUR_WOOD_CHEST_BOAT = ITEMS.register("purpur_wood_chest_boat",
            () -> new ITVBoatItem(true, ITVBoatEntity.Type.PURPUR, new Item.Properties()));

    // Armor

    /**
     * RegistryObject of type "Item" that represents a Lonsdaleite helmet
     */
    public static final RegistryObject<Item> LONSDALEITE_HELMET = ITEMS.register("lonsdaleite_helmet",
            () -> new LonsdaleiteArmorItem(ArmorItem.Type.HELMET, new Item.Properties()));

    /**
     * RegistryObject of type "Item" that represents a Lonsdaleite chestplate
     */
    public static final RegistryObject<Item> LONSDALEITE_CHESTPLATE = ITEMS.register("lonsdaleite_chestplate",
            () -> new LonsdaleiteArmorItem(ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    /**
     * RegistryObject of type "Item" that represents a Lonsdaleite leggings
     */
    public static final RegistryObject<Item> LONSDALEITE_LEGGINGS = ITEMS.register("lonsdaleite_leggings",
            () -> new LonsdaleiteArmorItem(ArmorItem.Type.LEGGINGS, new Item.Properties()));

    /**
     * RegistryObject of type "Item" that represents a Lonsdaleite boots
     */
    public static final RegistryObject<Item> LONSDALEITE_BOOTS = ITEMS.register("lonsdaleite_boots",
            () -> new LonsdaleiteArmorItem(ArmorItem.Type.BOOTS, new Item.Properties()));

    /**
     * RegistryObject of type "Item" that represents an Enderite helmet
     */
    public static final RegistryObject<Item> ENDERITE_HELMET = ITEMS.register("enderite_helmet",
            () -> new EnderiteArmorItem(ArmorItem.Type.HELMET, new Item.Properties()));

    /**
     * RegistryObject of type "Item" that represents an Enderite chestplate
     */
    public static final RegistryObject<Item> ENDERITE_CHESTPLATE = ITEMS.register("enderite_chestplate",
            () -> new EnderiteArmorItem(ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    /**
     * RegistryObject of type "Item" that represents an Enderite leggings
     */
    public static final RegistryObject<Item> ENDERITE_LEGGINGS = ITEMS.register("enderite_leggings",
            () -> new EnderiteArmorItem(ArmorItem.Type.LEGGINGS, new Item.Properties()));

    /**
     * RegistryObject of type "Item" that represents an Enderite boots
     */
    public static final RegistryObject<Item> ENDERITE_BOOTS = ITEMS.register("enderite_boots",
            () -> new EnderiteArmorItem(ArmorItem.Type.BOOTS, new Item.Properties()));

    /**
     * RegistryObject of type "Item" that represents a Dragonborne Helmet
     */
    public static final RegistryObject<Item> DRAGONBORNE_HELMET = ITEMS.register("dragonborne_helmet",
            () -> new DragonborneArmorItem(ArmorItem.Type.HELMET, new Item.Properties()));

    /**
     * RegistryObject of type "Item" that represents a Dragonborne Chestplate
     */
    public static final RegistryObject<Item> DRAGONBORNE_CHESTPLATE = ITEMS.register("dragonborne_chestplate",
            () -> new DragonborneArmorItem(ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    /**
     * RegistryObject of type "Item" that represents Dragonborne Leggings
     */
    public static final RegistryObject<Item> DRAGONBORNE_LEGGINGS = ITEMS.register("dragonborne_leggings",
            () -> new DragonborneArmorItem(ArmorItem.Type.LEGGINGS, new Item.Properties()));

    /**
     * RegistryObject of type "Item" that represents Dragonborne Boots
     */
    public static final RegistryObject<Item> DRAGONBORNE_BOOTS = ITEMS.register("dragonborne_boots",
            () -> new DragonborneArmorItem(ArmorItem.Type.BOOTS, new Item.Properties()));

    // Tools/Weapons

    /**
     * RegistryObject of type "Item" that represents a Lonsdaleite sword
     */
    public static final RegistryObject<Item> LONSDALEITE_SWORD = ITEMS.register("lonsdaleite_sword",
            () -> new LonsdaleiteSwordItem(3, SWORD_SPEED_MODIFIER, new Item.Properties()));

    /**
     * RegistryObject of type "Item" that represents a Lonsdaleite pickaxe
     */
    public static final RegistryObject<Item> LONSDALEITE_PICKAXE = ITEMS.register("lonsdaleite_pickaxe",
            () -> new LonsdaleitePickaxeItem(1, PICKAXE_SPEED_MODIFIER, new Item.Properties()));

    /**
     * RegistryObject of type "Item" that represents a Lonsdaleite axe
     */
    public static final RegistryObject<Item> LONSDALEITE_AXE = ITEMS.register("lonsdaleite_axe",
            () -> new LonsdaleiteAxeItem(6, AXE_SPEED_MODIFIER   , new Item.Properties()));

    /**
     * RegistryObject of type "Item" that represents a Lonsdaleite shovel
     */
    public static final RegistryObject<Item> LONSDALEITE_SHOVEL = ITEMS.register("lonsdaleite_shovel",
            () -> new LonsdaleiteShovelItem(0, SHOVEL_SPEED_MODIFIER, new Item.Properties()));

    /**
     * RegistryObject of type "Item" that represents a Lonsdaleite hoe
     */
    public static final RegistryObject<Item> LONSDALEITE_HOE = ITEMS.register("lonsdaleite_hoe",
            () -> new LonsdaleiteHoeItem(1, HOE_SPEED_MODIFIER, new Item.Properties()));

    /**
     * RegistryObject of type "Item" that represents an Enderite sword
     */
    public static final RegistryObject<Item> ENDERITE_SWORD = ITEMS.register("enderite_sword",
            () -> new EnderiteSwordItem(3, SWORD_SPEED_MODIFIER, new Item.Properties()));

    /**
     * RegistryObject of type "Item" that represents an Enderite pickaxe
     */
    public static final RegistryObject<Item> ENDERITE_PICKAXE = ITEMS.register("enderite_pickaxe",
            () -> new EnderitePickaxeItem(1, PICKAXE_SPEED_MODIFIER, new Item.Properties()));

    /**
     * RegistryObject of type "Item" that represents an Enderite axe
     */
    public static final RegistryObject<Item> ENDERITE_AXE = ITEMS.register("enderite_axe",
            () -> new EnderiteAxeItem(6, AXE_SPEED_MODIFIER, new Item.Properties()));

    /**
     * RegistryObject of type "Item" that represents an Enderite shovel
     */
    public static final RegistryObject<Item> ENDERITE_SHOVEL = ITEMS.register("enderite_shovel",
            () -> new EnderiteShovelItem(0, SHOVEL_SPEED_MODIFIER, new Item.Properties()));

    /**
     * RegistryObject of type "Item" that represents an Enderite hoe
     */
    public static final RegistryObject<Item> ENDERITE_HOE = ITEMS.register("enderite_hoe",
            () -> new EnderiteHoeItem(1, HOE_SPEED_MODIFIER, new Item.Properties()));

    /**
     * RegistryObject of type "Item" that represents a Dragonborne Sword
     */
    public static final RegistryObject<Item> DRAGONBORNE_SWORD = ITEMS.register("dragonborne_sword", () -> new DragonborneSwordItem(7, SWORD_SPEED_MODIFIER, new Item.Properties()));

    // Spawn Eggs
    /**
     * RegistryObject of type "Item" that represents a Void Pirate Spawn Egg
     */
    public static final RegistryObject<Item> VOID_PIRATE_SPAWN_EGG = ITEMS.register("void_pirate_spawn_egg", () -> new ForgeSpawnEggItem(ITVEntities.VOID_PIRATE, 7217035, 3608900, new Item.Properties()));

    /**
     * RegistryObject of type "Item" that represents an Ender Dragon Hatchling Spawn Egg
     */
    public static final RegistryObject<Item> ENDER_DRAGON_HATCHLING_SPAWN_EGG = ITEMS.register("ender_dragon_hatchling_spawn_egg", () -> new ForgeSpawnEggItem(ITVEntities.ENDER_DRAGON_HATCHLING, 197379, 9715891, new Item.Properties()));

    /**
     * RegistryObject of type "Item" that represents an Evolved Endermite Spawn Egg
     */
    public static final RegistryObject<Item> EVOLVED_ENDERMITE_SPAWN_EGG = ITEMS.register("evolved_endermite_spawn_egg", () -> new ForgeSpawnEggItem(ITVEntities.EVOLVED_ENDERMITE_ENTITY, 9830655, 16711860, new Item.Properties()));

    /**
     * A HashMap mapping ore mineral items to their block variants. Used for recipes
     */
    public static final HashMap<RegistryObject<Item>, RegistryObject<Block>> ORE_MATERIAL_TO_BLOCK = new HashMap<>();
    static
    {
        ORE_MATERIAL_TO_BLOCK.put(LONSDALEITE, ITVBlocks.LONSDALEITE_BLOCK);
        ORE_MATERIAL_TO_BLOCK.put(REFINED_LONSDALEITE, ITVBlocks.REFINED_LONSDALEITE_BLOCK);
        ORE_MATERIAL_TO_BLOCK.put(ENDERITE, ITVBlocks.ENDERITE_BLOCK);
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
