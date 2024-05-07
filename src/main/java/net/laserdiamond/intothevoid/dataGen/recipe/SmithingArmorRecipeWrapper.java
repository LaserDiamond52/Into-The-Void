package net.laserdiamond.intothevoid.dataGen.recipe;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

public record SmithingArmorRecipeWrapper(ItemLike material, ItemLike template, Item item) {
}
