package net.laserdiamond.intothevoid.dataGen.recipe;

import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

/**
 * Class used to help create ore smelting recipes
 * @param result The result item from smelting
 * @param recipeCategory The recipe category
 * @param experience The experience to gain when smelting is complete
 * @param cookingTime The duration in ticks for how long it takes the smelting process to complete
 */
public record OreRecipeResultWrapper (RegistryObject<Item> result, RecipeCategory recipeCategory, float experience, int cookingTime) {
}
