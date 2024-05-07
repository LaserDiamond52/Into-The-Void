package net.laserdiamond.intothevoid.dataGen.recipe;

import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;

public record SmithingToolRecipeWrapper(ItemLike material, ItemLike template, Item toolItem) {
}
