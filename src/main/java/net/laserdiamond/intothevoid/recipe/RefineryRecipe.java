package net.laserdiamond.intothevoid.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.block.entity.RefineryBlockEntity;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

/**
 * Class that represents the Refinery Recipe
 */
public class RefineryRecipe implements Recipe<SimpleContainer> {

    private final Ingredient inputItems;
    private final ItemStack output;
    private final ResourceLocation id;

    public RefineryRecipe(Ingredient inputItems, ItemStack output, ResourceLocation id) {
        this.inputItems = inputItems;
        this.output = output;
        this.id = id;
    }

    /**
     * Checks if the contents in the inventory/container matches a recipe
     * @param simpleContainer The inventory/container to check
     * @param level The level of the world
     * @return True if a matching recipe is found, false if not. False is also returned if the level is client side
     */
    @Override
    public boolean matches(SimpleContainer simpleContainer, Level level) {
        if (level.isClientSide)
        {
            return false;
        }
        return inputItems.test(simpleContainer.getItem(RefineryBlockEntity.ITEM_INPUT_SLOT));
    }

    /**
     * Assembles the recipe and returns the output item
     * @param simpleContainer The inventory/container containing the recipe
     * @param registryAccess RegistryAccess
     * @return An ItemStack representing the output item
     */
    @Override
    public ItemStack assemble(SimpleContainer simpleContainer, RegistryAccess registryAccess) {
        return output.copy();
    }

    /**
     *
     * @param i width
     * @param i1 height
     * @return
     */
    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return output.copy();
    }

    /**
     * The ID of the recipe
     * @return A resource location to the recipe
     */
    @Override
    public ResourceLocation getId() {
        return id;
    }

    /**
     * Returns the instance of the Recipe Serializer of an unknown type
     * @return The recipe serializer instance for this recipe
     */
    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    /**
     * Returns the instance of the Recipe Type of unknown type
     * @return The recipe type instance for this recipe
     */
    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<RefineryRecipe>
    {
        /**
         * RecipeType instance
         */
        public static final Type INSTANCE = new Type();

        /**
         * Recipe ID as a String
         */
        public static final String ID = "refinery";
    }

    public static class Serializer implements RecipeSerializer<RefineryRecipe>
    {
        /**
         * RecipeSerializer instance
         */
        public static final Serializer INSTANCE = new Serializer();

        /**
         * Recipe ID as a ResourceLocation
         */
        public static final ResourceLocation ID = new ResourceLocation(IntoTheVoid.MODID, "refinery");

        /**
         * Gets the Refinery Recipe from the json file
         * @param resourceLocation The resource location of the recipe
         * @param jsonObject The json object representing the recipe
         * @return An object instance of the recipe (this class)
         */
        @Override
        public RefineryRecipe fromJson(ResourceLocation resourceLocation, JsonObject jsonObject) {

            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(jsonObject, "result")); // Result Item

            Ingredient ingredients = Ingredient.fromJson(GsonHelper.getNonNull(jsonObject, "ingredients")); // Ingredient Item

            return new RefineryRecipe(ingredients, output, resourceLocation);
        }

        /**
         * Synchronizes the recipe from the server to the client
         * @param resourceLocation The resource location of the recipe
         * @param friendlyByteBuf FriendlyByteBuf
         * @return An object instance of the recipe (this class)
         */
        @Override
        public @Nullable RefineryRecipe fromNetwork(ResourceLocation resourceLocation, FriendlyByteBuf friendlyByteBuf) {

            Ingredient input = Ingredient.fromNetwork(friendlyByteBuf); // Ingredient item

            ItemStack output = friendlyByteBuf.readItem(); // Result item
            return new RefineryRecipe(input, output, resourceLocation);
        }

        /**
         * Synchronizes the recipe from the client to the server
         * @param friendlyByteBuf FriendlyByteBuf
         * @param refineryRecipe An object instance of the recipe (this class)
         */
        @Override
        public void toNetwork(FriendlyByteBuf friendlyByteBuf, RefineryRecipe refineryRecipe) {
            refineryRecipe.inputItems.toNetwork(friendlyByteBuf); // Write ingredients to buffer

            friendlyByteBuf.writeItemStack(refineryRecipe.getResultItem(null), false);
        }
    }
}
