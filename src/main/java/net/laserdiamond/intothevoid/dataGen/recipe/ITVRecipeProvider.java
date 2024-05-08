package net.laserdiamond.intothevoid.dataGen.recipe;

import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.item.ITVItems;
import net.laserdiamond.intothevoid.item.equipment.armor.ArmorCrafting;
import net.laserdiamond.intothevoid.item.equipment.armor.ArmorSmithing;
import net.laserdiamond.intothevoid.item.equipment.tools.ToolCrafting;
import net.laserdiamond.intothevoid.item.equipment.tools.ToolSmithing;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SmithingTransformRecipe;
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

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer) {

        smeltingRecipe(consumer);
        oreBlockCrafting(consumer);
        armorCrafting(consumer);
        toolCrafting(consumer);

        ironHandle(consumer);
    }

    protected static void smeltingRecipe(Consumer<FinishedRecipe> consumer)
    {

    }

    protected static void oreBlockCrafting(Consumer<FinishedRecipe> consumer)
    {

    }

    protected static void ironHandle(Consumer<FinishedRecipe> consumer)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ITVItems.IRON_HANDLE.get())
                .pattern("X")
                .pattern("X")
                .define('X', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(consumer);
    }

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
