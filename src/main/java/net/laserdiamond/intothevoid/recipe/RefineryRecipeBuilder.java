package net.laserdiamond.intothevoid.recipe;

import com.google.gson.JsonObject;
import net.laserdiamond.intothevoid.IntoTheVoid;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

/**
 * Builder class for the Refinery Recipe
 */
public class RefineryRecipeBuilder implements RecipeBuilder {

    private final Ingredient ingredientItem;
    private final Item resultItem;
    private final int resultItemCount;

    private RefineryRecipeBuilder(Ingredient ingredientItem, Item resultItem, int resultItemCount) {
        this.ingredientItem = ingredientItem;
        this.resultItem = resultItem;
        this.resultItemCount = resultItemCount;
    }

    /**
     * Starts a new Refinery Recipe
     * @param ingredient The ingredient items
     * @param resultItem The result item
     * @param resultItemCount The result item count
     * @return {@link RefineryRecipeBuilder}
     */
    public static RefineryRecipeBuilder createRecipe(Ingredient ingredient, Item resultItem, int resultItemCount)
    {
        return new RefineryRecipeBuilder(ingredient, resultItem, resultItemCount);
    }

    @Override
    public RecipeBuilder unlockedBy(String s, CriterionTriggerInstance criterionTriggerInstance) {
        return this;
    }

    @Override
    public RecipeBuilder group(@Nullable String s) {
        return this;
    }

    @Override
    public Item getResult() {
        return resultItem;
    }

    @Override
    public void save(Consumer<FinishedRecipe> consumer, ResourceLocation resourceLocation) {
        consumer.accept(new Result(resourceLocation, this.ingredientItem, this.resultItem, this.resultItemCount));
    }

    /**
     * Refinery Recipe serializer
     */
    static class Result implements FinishedRecipe
    {
        private final ResourceLocation id;
        private final Ingredient ingredient;
        private final Item result;
        private final int resultItemCount;

        public Result(ResourceLocation id, Ingredient ingredient, Item result, int resultItemCount) {
            this.id = id;
            this.ingredient = ingredient;
            this.result = result;
            this.resultItemCount = resultItemCount;
        }

        @Override
        public void serializeRecipeData(JsonObject jsonObject) {

            jsonObject.addProperty("type", IntoTheVoid.MODID + ":refinery");

            jsonObject.add("ingredients", this.ingredient.toJson());

            JsonObject resultObj = new JsonObject();
            resultObj.addProperty("count", this.resultItemCount);
            resultObj.addProperty("item", BuiltInRegistries.ITEM.getKey(this.result).toString());
            jsonObject.add("result", resultObj);
        }

        @Override
        public ResourceLocation getId() {
            return id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return ITVRecipes.REFINERY_SERIALIZER.get();
        }

        @Nullable
        @Override
        public JsonObject serializeAdvancement() {
            return null;
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementId() {
            return null;
        }
    }
}
