package net.laserdiamond.intothevoid.dataGen.recipe;

import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.item.ITVItems;
import net.laserdiamond.intothevoid.item.equipment.EquipmentCrafting;
import net.laserdiamond.intothevoid.item.equipment.EquipmentSmithing;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

public class ITVRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ITVRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    private static final HashMap<Item, Item> CRAFT_ARMOR_RECIPES = new HashMap<>();
    static
    {
        CRAFT_ARMOR_RECIPES.put(ITVItems.LONSDALEITE_HELMET.get(), ITVItems.REFINED_LONSDALEITE.get());
        CRAFT_ARMOR_RECIPES.put(ITVItems.LONSDALEITE_CHESTPLATE.get(), ITVItems.REFINED_LONSDALEITE.get());
        CRAFT_ARMOR_RECIPES.put(ITVItems.LONSDALEITE_LEGGINGS.get(), ITVItems.REFINED_LONSDALEITE.get());
        CRAFT_ARMOR_RECIPES.put(ITVItems.LONSDALEITE_BOOTS.get(), ITVItems.REFINED_LONSDALEITE.get());

    }

    private static final HashMap<Item, CraftToolRecipeWrapper> CRAFT_TOOL_RECIPES = new HashMap<>();
    static
    {
        CraftToolRecipeWrapper lonsdaleiteMaterials = new CraftToolRecipeWrapper(ITVItems.LONSDALEITE.get(), Items.STICK);
        CRAFT_TOOL_RECIPES.put(ITVItems.LONSDALEITE_SWORD.get(), lonsdaleiteMaterials);
        CRAFT_TOOL_RECIPES.put(ITVItems.LONSDALEITE_PICKAXE.get(), lonsdaleiteMaterials);
        CRAFT_TOOL_RECIPES.put(ITVItems.LONSDALEITE_AXE.get(), lonsdaleiteMaterials);
        CRAFT_TOOL_RECIPES.put(ITVItems.LONSDALEITE_SHOVEL.get(), lonsdaleiteMaterials);
        CRAFT_TOOL_RECIPES.put(ITVItems.LONSDALEITE_HOE.get(), lonsdaleiteMaterials);

    }

    private static final HashMap<Item, SmithingArmorRecipeWrapper> SMITHING_ARMOR_RECIPES = new HashMap<>();
    static
    {
        SMITHING_ARMOR_RECIPES.put(ITVItems.ENDERITE_HELMET.get(), new SmithingArmorRecipeWrapper(ITVItems.ENDERITE.get(), null, Items.DIAMOND_HELMET));
        SMITHING_ARMOR_RECIPES.put(ITVItems.ENDERITE_CHESTPLATE.get(), new SmithingArmorRecipeWrapper(ITVItems.ENDERITE.get(), null, Items.DIAMOND_CHESTPLATE));
        SMITHING_ARMOR_RECIPES.put(ITVItems.ENDERITE_LEGGINGS.get(), new SmithingArmorRecipeWrapper(ITVItems.ENDERITE.get(), null, Items.DIAMOND_LEGGINGS));
        SMITHING_ARMOR_RECIPES.put(ITVItems.ENDERITE_BOOTS.get(), new SmithingArmorRecipeWrapper(ITVItems.ENDERITE.get(), null, Items.DIAMOND_BOOTS));

    }

    private static final HashMap<Item, SmithingToolRecipeWrapper> SMITHING_TOOL_RECIPES = new HashMap<>();
    static
    {
        SMITHING_TOOL_RECIPES.put(ITVItems.ENDERITE_SWORD.get(), new SmithingToolRecipeWrapper(ITVItems.ENDERITE.get(), null, Items.DIAMOND_SWORD));
        SMITHING_TOOL_RECIPES.put(ITVItems.ENDERITE_PICKAXE.get(), new SmithingToolRecipeWrapper(ITVItems.ENDERITE.get(), null, Items.DIAMOND_PICKAXE));
        SMITHING_TOOL_RECIPES.put(ITVItems.ENDERITE_AXE.get(), new SmithingToolRecipeWrapper(ITVItems.ENDERITE.get(), null, Items.DIAMOND_AXE));
        SMITHING_TOOL_RECIPES.put(ITVItems.ENDERITE_SHOVEL.get(), new SmithingToolRecipeWrapper(ITVItems.ENDERITE.get(), null, Items.DIAMOND_SHOVEL));
        SMITHING_TOOL_RECIPES.put(ITVItems.ENDERITE_HOE.get(), new SmithingToolRecipeWrapper(ITVItems.ENDERITE.get(), null, Items.DIAMOND_HOE));

    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer) {

        smeltingRecipe(consumer);
        oreBlockCrafting(consumer);
        armorCrafting(consumer);
        toolCrafting(consumer);
    }

    protected static void smeltingRecipe(Consumer<FinishedRecipe> consumer)
    {

    }

    protected static void oreBlockCrafting(Consumer<FinishedRecipe> consumer)
    {

    }

    protected static void armorCrafting(Consumer<FinishedRecipe> consumer)
    {

    }

    protected static void toolCrafting(Consumer<FinishedRecipe> consumer)
    {
        for (RegistryObject<Item> itemRegistryObject : ITVItems.ITEMS.getEntries())
        {
            Item item = itemRegistryObject.get();
            if (item instanceof EquipmentCrafting equipmentCrafting)
            {
                List<ItemLike> materials = equipmentCrafting.materials();
                ItemLike stick = equipmentCrafting.stickMaterial();

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


            } else if (item instanceof EquipmentSmithing equipmentSmithing)
            {
                List<ItemLike> materials = equipmentSmithing.materials();
                ItemLike template = equipmentSmithing.template();
                ItemLike equipmentPiece = equipmentSmithing.equipmentItem();

                for (ItemLike materialItem : materials)
                {
                    if (item instanceof SwordItem swordItem)
                    {

                    } else if (item instanceof PickaxeItem pickaxeItem)
                    {

                    } else if (item instanceof AxeItem axeItem)
                    {

                    } else if (item instanceof ShovelItem shovelItem)
                    {

                    } else if (item instanceof HoeItem hoeItem)
                    {

                    }
                }

            }
        }
    }

    protected static void foodCampFire(Consumer<FinishedRecipe> consumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup)
    {
        oreCooking(consumer, RecipeSerializer.CAMPFIRE_COOKING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_campfire");
    }

    protected static void foodSmoking(Consumer<FinishedRecipe> consumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup)
    {
        oreCooking(consumer, RecipeSerializer.SMOKING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_smoking");
    }

    protected static void smelting(Consumer<FinishedRecipe> consumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup)
    {
        oreCooking(consumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(@NotNull Consumer<FinishedRecipe> consumer, List<ItemLike> pIngredients, @NotNull RecipeCategory pCategory, @NotNull ItemLike pResult, float pExperience, int pCookingTime, @NotNull String pGroup)
    {
        oreCooking(consumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

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
