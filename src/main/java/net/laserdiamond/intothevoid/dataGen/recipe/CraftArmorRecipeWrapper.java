package net.laserdiamond.intothevoid.dataGen.recipe;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.level.ItemLike;

public record CraftArmorRecipeWrapper(ItemLike material, ArmorItem armorItem) {
}
