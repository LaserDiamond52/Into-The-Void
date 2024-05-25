package net.laserdiamond.intothevoid.recipe;

import net.laserdiamond.intothevoid.IntoTheVoid;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * Class that contains all custom recipes of this mod
 */
public class ITVRecipes {

    /**
     * A DeferredRegister of type "RecipeSerializer" of an unknown type that registers all custom recipe types of this mod
     */
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, IntoTheVoid.MODID);

    /**
     * A RegistryObject of type "RecipeSerializer" of type "RefineryRecipe" that represents the Refinery recipe type
     */
    public static final RegistryObject<RecipeSerializer<RefineryRecipe>> REFINERY_SERIALIZER = SERIALIZERS.register("refinery", () -> RefineryRecipe.Serializer.INSTANCE);

    /**
     * Registers all custom recipe types under the DeferredRegister
     * @param eventBus The Event Bus of this mod
     */
    public static void register(IEventBus eventBus)
    {
        SERIALIZERS.register(eventBus);
    }
}
