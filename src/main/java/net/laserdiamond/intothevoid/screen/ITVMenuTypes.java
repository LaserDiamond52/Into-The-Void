package net.laserdiamond.intothevoid.screen;

import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.screen.Refinery.RefineryMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * Class that contains all menu types for this mod
 */
public class ITVMenuTypes {

    /**
     * DeferredRegister of type "MenuType" of an unknown type that registers all menu types of this mod
     */
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, IntoTheVoid.MODID);

    /**
     * A RegistryObject of type "MenuType" of type "RefineryMenu" that represents the Refinery menu type
     */
    public static final RegistryObject<MenuType<RefineryMenu>> REFINERY_MENU = registerMenuTypes("refinery_menu", RefineryMenu::new);

    /**
     * Method used to register menu types for this mod
     * @param name The name of the menu (lowercase and underscores)
     * @param factory Method reference to a new object instance of the subclass of the MenuType of the RegistryObject
     * @return A subclass of the AbstractContainerMenu
     * @param <T>
     */
    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuTypes(String name, IContainerFactory<T> factory)
    {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus)
    {
        MENUS.register(eventBus);
    }
}
