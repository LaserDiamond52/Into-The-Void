package net.laserdiamond.intothevoid.dataGen.recipe;

import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;

public record CraftToolRecipeWrapper(ItemLike material, ItemLike stick) {
}
