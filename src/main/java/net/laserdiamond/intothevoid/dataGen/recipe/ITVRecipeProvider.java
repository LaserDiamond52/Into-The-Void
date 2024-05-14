package net.laserdiamond.intothevoid.dataGen.recipe;

import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.block.ITVBlocks;
import net.laserdiamond.intothevoid.item.ITVItems;
import net.laserdiamond.intothevoid.item.equipment.armor.ArmorCrafting;
import net.laserdiamond.intothevoid.item.equipment.armor.ArmorSmithing;
import net.laserdiamond.intothevoid.item.equipment.tools.ToolCrafting;
import net.laserdiamond.intothevoid.item.equipment.tools.ToolSmithing;
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

        stickRecipe(consumer, Items.IRON_INGOT, ITVItems.IRON_HANDLE.get());
        woodSetCrafting(consumer);
    }

    protected static void smeltingRecipe(Consumer<FinishedRecipe> consumer)
    {

    }

    protected static void oreBlockCrafting(Consumer<FinishedRecipe> consumer)
    {

    }

    protected static void stickRecipe(Consumer<FinishedRecipe> consumer, Item ingredient, Item result)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result)
                .pattern("X")
                .pattern("X")
                .define('X', ingredient)
                .unlockedBy(getHasName(ingredient), has(ingredient))
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

            // TODO: Boat Recipe

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
