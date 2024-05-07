package net.laserdiamond.intothevoid.item.equipment.armor;

import net.minecraft.world.level.ItemLike;

import java.util.List;

/**
 * An interface that helps set the basic crafting ingredients for an armor item
 */
public interface ArmorCrafting {

    /**
     * A list of ItemLike objects that represent the valid materials that may be used to help craft the equipment item
     * @return A list of ItemLike objects
     */
    List<ItemLike> materials();
}
