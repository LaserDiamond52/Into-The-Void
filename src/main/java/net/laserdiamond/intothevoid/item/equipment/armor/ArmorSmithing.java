package net.laserdiamond.intothevoid.item.equipment.armor;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.level.ItemLike;

import java.util.List;

/**
 * An interface that helps set smithing recipe ingredients for an armor item
 */
public interface ArmorSmithing {

    /**
     * A list of ItemLike objects that represent the valid materials that may be used to help craft the armor item
     * @return A list of ItemLike objects
     */
    List<ItemLike> materials();

    /**
     * An ItemLike object that represents the template item to use for the recipe
     * @return The template item as an ItemLike object
     */
    ItemLike template();

    /**
     * An ItemLike object that represents the armor item to use for the recipe
     * @param slot The equipment slot that the armor piece can be placed in
     * @return The armor item as an ItemLike object
     */
    ItemLike armorPiece(EquipmentSlot slot);
}
