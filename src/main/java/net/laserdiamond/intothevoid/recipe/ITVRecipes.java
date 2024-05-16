package net.laserdiamond.intothevoid.recipe;

import net.laserdiamond.intothevoid.IntoTheVoid;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ITVRecipes {

    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, IntoTheVoid.MODID);

    public static final RegistryObject<RecipeSerializer<RefineryRecipe>> REFINERY_SERIALIZER = SERIALIZERS.register("refinery", () -> RefineryRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus)
    {
        SERIALIZERS.register(eventBus);
    }
}
