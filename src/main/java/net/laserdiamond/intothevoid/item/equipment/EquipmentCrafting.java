package net.laserdiamond.intothevoid.item.equipment;

import net.minecraft.world.level.ItemLike;

import java.util.List;

/**
 * An interface that helps set the basic crafting ingredients for an equipment item
 */
public interface EquipmentCrafting {

    /**
     * A list of ItemLike objects that represent the valid materials that may be used to help craft the equipment item
     * @return A list of ItemLike objects
     */
    List<ItemLike> materials();

    /**
     * An ItemLike object that represents the "stick" item that will be used to help craft the tool item
     * @return The stick item as an ItemLike object
     */
    ItemLike stickMaterial();
}
