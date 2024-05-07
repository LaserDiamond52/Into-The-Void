package net.laserdiamond.intothevoid.item.equipment.tools;

import net.minecraft.world.level.ItemLike;

import java.util.List;

/**
 * An interface that helps set smithing recipe ingredients for a tool item
 */
public interface ToolSmithing {

    /**
     * A list of ItemLike objects that represent the valid materials that may be used to help craft the tool item
     * @return A list of ItemLike objects
     */
    List<ItemLike> materials();

    /**
     * An ItemLike object that represents the template item to use for the recipe
     * @return The template item as an ItemLike object
     */
    ItemLike template();

    /**
     * An ItemLike object that represents the tool item to use for the recipe
     * @return The equipment item as an ItemLike object
     */
    ItemLike toolItem();
}
