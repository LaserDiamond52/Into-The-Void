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

    private final NonNullList<Ingredient> inputItems;
    private final ItemStack output;
    private final ResourceLocation id;

    public RefineryRecipe(NonNullList<Ingredient> inputItems, ItemStack output, ResourceLocation id) {
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
        return inputItems.get(RefineryBlockEntity.ITEM_INPUT_SLOT).test(simpleContainer.getItem(RefineryBlockEntity.ITEM_INPUT_SLOT));
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

            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(jsonObject, "result"));

            JsonArray ingredients = GsonHelper.getAsJsonArray(jsonObject, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(1, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++)
            {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new RefineryRecipe(inputs, output, resourceLocation);
        }

        /**
         * Synchronizes the recipe from the server to the client
         * @param resourceLocation The resource location of the recipe
         * @param friendlyByteBuf FriendlyByteBuf
         * @return An object instance of the recipe (this class)
         */
        @Override
        public @Nullable RefineryRecipe fromNetwork(ResourceLocation resourceLocation, FriendlyByteBuf friendlyByteBuf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(friendlyByteBuf.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++)
            {
                inputs.set(i, Ingredient.fromNetwork(friendlyByteBuf));
            }
            ItemStack output = friendlyByteBuf.readItem();
            return new RefineryRecipe(inputs, output, resourceLocation);
        }

        /**
         * Synchronizes the recipe from the client to the server
         * @param friendlyByteBuf FriendlyByteBuf
         * @param refineryRecipe An object instance of the recipe (this class)
         */
        @Override
        public void toNetwork(FriendlyByteBuf friendlyByteBuf, RefineryRecipe refineryRecipe) {
            friendlyByteBuf.writeInt(refineryRecipe.inputItems.size());

            for (Ingredient ingredient : refineryRecipe.getIngredients())
            {
                ingredient.toNetwork(friendlyByteBuf);
            }

            friendlyByteBuf.writeItemStack(refineryRecipe.getResultItem(null), false);
        }
    }
}
