package net.laserdiamond.intothevoid.dataGen.recipe;

import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.block.ITVBlocks;
import net.laserdiamond.intothevoid.item.ITVItems;
import net.laserdiamond.intothevoid.item.equipment.armor.ArmorCrafting;
import net.laserdiamond.intothevoid.item.equipment.armor.ArmorSmithing;
import net.laserdiamond.intothevoid.item.equipment.tools.ToolCrafting;
import net.laserdiamond.intothevoid.item.equipment.tools.ToolSmithing;
import net.laserdiamond.intothevoid.item.ingredients.smithingTemplates.ITVSmithingTemplateItem;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

/**
 * The RecipeProvider for this mod. Responsible for creating all the recipes and related advancements for recipes of this mod
 */
public class ITVRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public ITVRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    /**
     * Builds out all the recipes to .json files
     * @param consumer The FinishedRecipe Consumer
     */
    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer) {

        smeltingRecipe(consumer);
        oreMaterialBlockCrafting(consumer);
        armorCrafting(consumer);
        toolCrafting(consumer);
        smithingTemplateRecipes(consumer);

        stickRecipe(consumer, Items.IRON_INGOT, ITVItems.IRON_HANDLE.get());
        woodSetCrafting(consumer);
        endCoreRecipe(consumer);
        refineryRecipe(consumer);
    }

    /**
     * Creates all smithing template recipes for this mod
     * @param consumer The FinishedRecipe Consumer
     */
    protected static void smithingTemplateRecipes(Consumer<FinishedRecipe> consumer)
    {
        for (RegistryObject<Item> itemRegistryObject : ITVItems.ITEMS.getEntries())
        {
            if (itemRegistryObject.get() instanceof ITVSmithingTemplateItem smithingTemplateItem)
            {
                ItemLike materialItem = smithingTemplateItem.materialItem();
                ItemLike mineralItem = smithingTemplateItem.mineralItem();

                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, smithingTemplateItem,2)
                        .pattern("XMX")
                        .pattern("XTX")
                        .pattern("XXX")
                        .define('X', materialItem)
                        .define('M', mineralItem)
                        .define('T', smithingTemplateItem)
                        .unlockedBy(getHasName(smithingTemplateItem), has(smithingTemplateItem))
                        .save(consumer);
            }
        }
    }

    /**
     * Adds all smelting recipes of this mod
     * @param consumer The FinishedRecipe Consumer
     */
    protected static void smeltingRecipe(Consumer<FinishedRecipe> consumer)
    {

    }

    /**
     * Adds all recipes for ore material block crafting, and vice versa
     * @param consumer The FinishedRecipe Consumer
     */
    protected static void oreMaterialBlockCrafting(Consumer<FinishedRecipe> consumer)
    {
        for (RegistryObject<Item> oreMaterialItem : ITVItems.ORE_MATERIAL_TO_BLOCK.keySet())
        {
            RegistryObject<Block> oreMaterialBlock = ITVItems.ORE_MATERIAL_TO_BLOCK.get(oreMaterialItem);

            ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, oreMaterialBlock.get())
                    .requires(oreMaterialItem.get(), 9)
                    .unlockedBy(getHasName(oreMaterialItem.get()), has(oreMaterialItem.get()))
                    .save(consumer);

            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, oreMaterialItem.get(), 9)
                    .requires(oreMaterialBlock.get(), 1)
                    .unlockedBy(getHasName(oreMaterialItem.get()), has(oreMaterialItem.get()))
                    .save(consumer);
        }
    }

    /**
     * Adds the End Core recipe
     * @param consumer The FinishedRecipe Consumer
     */
    protected static void endCoreRecipe(Consumer<FinishedRecipe> consumer)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ITVItems.END_CORE.get())
                .pattern("XXX")
                .pattern("X#X")
                .pattern("XXX")
                .define('X', Items.END_CRYSTAL)
                .define('#', Items.NETHER_STAR)
                .unlockedBy(getHasName(Items.NETHER_STAR), has(Items.NETHER_STAR))
                .save(consumer);
    }

    /**
     * Adds the Refinery block recipe
     * @param consumer The FinishedRecipe Consumer
     */
    protected static void refineryRecipe(Consumer<FinishedRecipe> consumer)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ITVBlocks.REFINERY.get())
                .pattern("GGG")
                .pattern("RIR")
                .pattern("XBX")
                .define('G', Items.GOLD_INGOT)
                .define('R', Items.REPEATER)
                .define('I', Items.IRON_INGOT)
                .define('X', Items.IRON_BLOCK)
                .define('B', Items.BUCKET)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(consumer);

    }

    /**
     * A template for crafting items in the manner of crafting a stick
     * @param consumer The FinishedRecipe consumer
     * @param ingredient The ingredient item
     * @param result The result item
     */
    protected static void stickRecipe(Consumer<FinishedRecipe> consumer, Item ingredient, Item result)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result)
                .pattern("X")
                .pattern("X")
                .define('X', ingredient)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(consumer);
    }

    /**
     * Adds all recipes for armor items that inherit either the ArmorCrafting interface or ArmorSmithing interface
     * <p>Armor items of the ArmorCrafting interface will have recipe formats similar to that of typical vanilla armor sets (ex: Gold, Iron, or Diamond armor)</p>
     * <p>Armor items of the ArmorSmithing interface will have recipe formats similar to that of Netherite Armor (requiring a base armor piece, material item, and template item</p>
     * @param consumer The FinishedRecipe Consumer
     */
    protected static void armorCrafting(Consumer<FinishedRecipe> consumer)
    {
        for (RegistryObject<Item> itemRegistryObject : ITVItems.ITEMS.getEntries())
        {
            Item item = itemRegistryObject.get();

            if (item instanceof ArmorItem armorItem)
            {
                EquipmentSlot slot = armorItem.getEquipmentSlot();
                if (armorItem instanceof ArmorCrafting armorCrafting)
                {
                    List<ItemLike> materials = armorCrafting.materials();

                    for (ItemLike material : materials)
                    {
                        switch (slot)
                        {
                            case HEAD ->
                            {
                                ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, armorItem)
                                        .pattern("XXX")
                                        .pattern("X X")
                                        .define('X', material)
                                        .unlockedBy(getHasName(material), has(material))
                                        .save(consumer);
                            }
                            case CHEST ->
                            {
                                ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, armorItem)
                                        .pattern("X X")
                                        .pattern("XXX")
                                        .pattern("XXX")
                                        .define('X', material)
                                        .unlockedBy(getHasName(material), has(material))
                                        .save(consumer);
                            }
                            case LEGS ->
                            {
                                ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, armorItem)
                                        .pattern("XXX")
                                        .pattern("X X")
                                        .pattern("X X")
                                        .define('X', material)
                                        .unlockedBy(getHasName(material), has(material))
                                        .save(consumer);
                            }
                            case FEET ->
                            {
                                ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, armorItem)
                                        .pattern("X X")
                                        .pattern("X X")
                                        .define('X', material)
                                        .unlockedBy(getHasName(material), has(material))
                                        .save(consumer);
                            }
                        }
                    }
                } else if (armorItem instanceof ArmorSmithing armorSmithing)
                {

                    List<ItemLike> materials = armorSmithing.materials();
                    ItemLike template = armorSmithing.template();
                    ItemLike equipmentItem = armorSmithing.armorPiece(slot);

                    for (ItemLike material : materials)
                    {
                        String currentSmithingPath = armorItem + "_smithing";
                        SmithingTransformRecipeBuilder.smithing(Ingredient.of(template),
                                        Ingredient.of(equipmentItem), Ingredient.of(material),
                                        RecipeCategory.COMBAT, armorItem)
                                .unlocks(getHasName(material), has(material))
                                .save(consumer, new ResourceLocation(IntoTheVoid.MODID, currentSmithingPath));

                    }
                }
            }

        }
    }

    /**
     * Adds all recipes for tool items that inherit either the ToolCrafting interface or ToolSmithing interface
     * <p>Tool items of the ToolCrafting interface will have recipe formats similar to that of typical vanilla toolsets (ex: Gold, Iron, Diamond)</p>
     * <p>Tool items of the ToolSmithing interface will have recipe formats similar to that of Netherite tools (requiring a base tool item, material item, and template item</p>
     * @param consumer The FinishedRecipe Consumer
     */
    protected static void toolCrafting(Consumer<FinishedRecipe> consumer)
    {
        for (RegistryObject<Item> itemRegistryObject : ITVItems.ITEMS.getEntries())
        {
            Item item = itemRegistryObject.get();
            if (item instanceof ToolCrafting toolCrafting)
            {
                List<ItemLike> materials = toolCrafting.materials();
                ItemLike stick = toolCrafting.stickMaterial();

                for (ItemLike materialItem : materials)
                {
                    if (item instanceof SwordItem swordItem)
                    {
                        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, swordItem)
                                .pattern("X")
                                .pattern("X")
                                .pattern("#")
                                .define('X', materialItem)
                                .define('#', stick)
                                .unlockedBy(getHasName(materialItem), has(materialItem))
                                .save(consumer);
                    } else if (item instanceof PickaxeItem pickaxeItem)
                    {
                        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, pickaxeItem)
                                .pattern("XXX")
                                .pattern(" # ")
                                .pattern(" # ")
                                .define('X', materialItem)
                                .define('#', stick)
                                .unlockedBy(getHasName(materialItem), has(materialItem))
                                .save(consumer);
                    } else if (item instanceof AxeItem axeItem)
                    {
                        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, axeItem)
                                .pattern("XX")
                                .pattern("X#")
                                .pattern(" #")
                                .define('X', materialItem)
                                .define('#', stick)
                                .unlockedBy(getHasName(materialItem), has(materialItem))
                                .save(consumer);
                    } else if (item instanceof ShovelItem shovelItem)
                    {
                        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, shovelItem)
                                .pattern("X")
                                .pattern("#")
                                .pattern("#")
                                .define('X', materialItem)
                                .define('#', stick)
                                .unlockedBy(getHasName(materialItem), has(materialItem))
                                .save(consumer);
                    } else if (item instanceof HoeItem hoeItem)
                    {
                        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, hoeItem)
                                .pattern("XX")
                                .pattern(" #")
                                .pattern(" #")
                                .define('X', materialItem)
                                .define('#', stick)
                                .unlockedBy(getHasName(materialItem), has(materialItem))
                                .save(consumer);
                    }
                }


            } else if (item instanceof ToolSmithing toolSmithing)
            {
                List<ItemLike> materials = toolSmithing.materials();
                ItemLike template = toolSmithing.template();
                ItemLike equipmentPiece = toolSmithing.toolItem();

                for (ItemLike materialItem : materials)
                {
                    String currentSmithingPath = item + "_smithing";
                    SmithingTransformRecipeBuilder.smithing(Ingredient.of(template),
                            Ingredient.of(equipmentPiece), Ingredient.of(materialItem),
                            RecipeCategory.COMBAT, item)
                            .unlocks(getHasName(materialItem), has(materialItem))
                            .save(consumer, new ResourceLocation(IntoTheVoid.MODID, currentSmithingPath));
                }
            }
        }
    }

    /**
     * Adds all recipes for new wood types. New wood types must have recipes for all the following items. This includes recipes for the following:
     * <p>Planks</p>
     * <p>Wood</p>
     * <p>Slabs</p>
     * <p>Stairs</p>
     * <p>Pressure Plates</p>
     * <p>Doors + Trapdoors</p>
     * <p>Fences + Fence Gates</p>
     * <p>Buttons</p>
     * <p>Signs (Both standing and hanging)</p>
     * <p>Boats (Both normal and chest)</p>
     * @param consumer The FinishedRecipe Consumer
     */
    protected static void woodSetCrafting(Consumer<FinishedRecipe> consumer)
    {
        for (ITVBlocks.WoodBlocks woodBlocks : ITVBlocks.WoodBlocks.values())
        {
            Block logBlock = woodBlocks.getLogBlock().get();
            Block strippedLogBlock = woodBlocks.getStrippedLogBlock().get();
            Block woodBlock = woodBlocks.getWoodBlock().get();
            Block strippedWoodBlock = woodBlocks.getStrippedWoodBlock().get();
            Block planksBlock = woodBlocks.getPlanks().get();

            TagKey<Item> logItemTag = woodBlocks.getItemTagKey();

            Ingredient plankIngredients = Ingredient.of(planksBlock);
            planksFromLog(consumer, planksBlock, logItemTag, 4);
            woodFromLogs(consumer, woodBlock, logBlock);
            woodFromLogs(consumer, strippedWoodBlock, strippedLogBlock);
            // Slab recipe
            slabBuilder(RecipeCategory.BUILDING_BLOCKS, woodBlocks.getSlab().get(), plankIngredients)
                    .group("wooden_slab")
                    .unlockedBy(getHasName(planksBlock), has(planksBlock))
                    .save(consumer);
            // Stairs recipe
            stairBuilder(woodBlocks.getStairs().get(), plankIngredients)
                    .group("wooden_stairs")
                    .unlockedBy(getHasName(planksBlock), has(planksBlock))
                    .save(consumer);
            // Pressure plate recipe
            pressurePlateBuilder(RecipeCategory.REDSTONE, woodBlocks.getPressurePlate().get(), plankIngredients)
                    .group("wooden_pressure_plate")
                    .unlockedBy(getHasName(planksBlock), has(planksBlock))
                    .save(consumer);
            // Door recipe
            doorBuilder(woodBlocks.getDoor().get(), plankIngredients)
                    .group("wooden_door")
                    .unlockedBy(getHasName(planksBlock), has(planksBlock))
                    .save(consumer);
            // Trapdoor recipe
            trapdoorBuilder(woodBlocks.getTrapDoor().get(), plankIngredients)
                    .group("wooden_trapdoor")
                    .unlockedBy(getHasName(planksBlock), has(planksBlock))
                    .save(consumer);
            // Fence recipe
            fenceBuilder(woodBlocks.getFence().get(), plankIngredients)
                    .group("wooden_fence")
                    .unlockedBy(getHasName(planksBlock), has(planksBlock))
                    .save(consumer);
            // Fence Gate recipe
            fenceGateBuilder(woodBlocks.getFenceGate().get(), plankIngredients)
                    .group("wooden_fence_gate")
                    .unlockedBy(getHasName(planksBlock), has(planksBlock))
                    .save(consumer);
            // Button recipe
            buttonBuilder(woodBlocks.getButton().get(), plankIngredients)
                    .group("wooden_button")
                    .unlockedBy(getHasName(planksBlock), has(planksBlock))
                    .save(consumer);
            // Sign recipe
            signBuilder(woodBlocks.getSign().get(), plankIngredients)
                    .group("wooden_sign")
                    .unlockedBy(getHasName(planksBlock), has(planksBlock))
                    .save(consumer);
            // Hanging Sign recipe
            hangingSign(consumer, woodBlocks.getHangingSign().get(), planksBlock);

            // Boat recipes
            woodenBoat(consumer, woodBlocks.getBoat().get(), planksBlock);
            chestBoat(consumer, woodBlocks.getChestBoat().get(), planksBlock);

        }
    }

    /**
     * Method used to create recipes for campfires
     * @param consumer The FinishedRecipe Consumer
     * @param pIngredients A list of ingredients that can be used to obtain the result item
     * @param pCategory The category the recipe falls under
     * @param pResult The result item
     * @param pExperience The experience gained from completing the recipe
     * @param pCookingTime The duration for how long the recipe takes to cook
     * @param pGroup The name of the result item (use lowercase and underscores)
     */
    protected static void foodCampFire(Consumer<FinishedRecipe> consumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup)
    {
        oreCooking(consumer, RecipeSerializer.CAMPFIRE_COOKING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_campfire");
    }

    /**
     * Method used to create recipes for smokers
     * @param consumer The FinishedRecipe Consumer
     * @param pIngredients A list of ingredients that can be used to obtain the result item
     * @param pCategory The category the recipe falls under
     * @param pResult The result item
     * @param pExperience The experience gained from completing the recipe
     * @param pCookingTime The duration for how long the recipe takes to cook
     * @param pGroup The name of the result item (use lowercase and underscores)
     */
    protected static void foodSmoking(Consumer<FinishedRecipe> consumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup)
    {
        oreCooking(consumer, RecipeSerializer.SMOKING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_smoking");
    }

    /**
     * Method used to create recipes for furnaces
     * @param consumer The FinishedRecipe Consumer
     * @param pIngredients A list of ingredients that can be used to obtain the result item
     * @param pCategory The category the recipe falls under
     * @param pResult The result item
     * @param pExperience The experience gained from completing the recipe
     * @param pCookingTime The duration for how long the recipe takes to cook
     * @param pGroup The name of the result item (use lowercase and underscores)
     */
    protected static void smelting(Consumer<FinishedRecipe> consumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup)
    {
        oreCooking(consumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_smelting");
    }

    /**
     * Method used to create recipes for a blast furnace
     * @param consumer The FinishedRecipe Consumer
     * @param pIngredients A list of ingredients that can be used to obtain the result item
     * @param pCategory The category the recipe falls under
     * @param pResult The result item
     * @param pExperience The experience gained from completing the recipe
     * @param pCookingTime The duration for how long the recipe takes to cook
     * @param pGroup The name of the result item (use lowercase and underscores)
     */
    protected static void oreBlasting(@NotNull Consumer<FinishedRecipe> consumer, List<ItemLike> pIngredients, @NotNull RecipeCategory pCategory, @NotNull ItemLike pResult, float pExperience, int pCookingTime, @NotNull String pGroup)
    {
        oreCooking(consumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    /**
     * Base method used to create smelting/cooking recipes for the Furnace, Blast Furnace, Smoker, and Campfire
     * @param consumer The FinishedRecipe Consumer
     * @param cookingSerializer The Recipe Serializer. Should use BLASTING_RECIPE, SMELTING_RECIPE, SMOKING_RECIPE, or CAMPFIRE_COOKING_RECIPE
     * @param ingredient A list of ingredients that can be used to obtain the result item
     * @param recipeCategory The category the recipe falls under
     * @param result The result item
     * @param experience The experience gained from completing the recipe
     * @param cookingTime The duration for how long the recipe takes to cook
     * @param group The name of the result item (use lowercase and underscores)
     * @param recipeName The recipe type as a string (ex: "_from_blasting")
     */
    protected static void oreCooking(@NotNull Consumer<FinishedRecipe> consumer, @NotNull RecipeSerializer<? extends AbstractCookingRecipe> cookingSerializer, List<ItemLike> ingredient, @NotNull RecipeCategory recipeCategory, @NotNull ItemLike result, float experience, int cookingTime, @NotNull String group, String recipeName)
    {
        for (ItemLike itemLike : ingredient)
        {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemLike), recipeCategory,
                    result, experience, cookingTime, cookingSerializer)
                    .group(group).unlockedBy(getHasName(itemLike), has(itemLike))
                    .save(consumer, IntoTheVoid.MODID + ":" + getItemName(result) + recipeName + "_" + getItemName(itemLike));
        }
    }
}
