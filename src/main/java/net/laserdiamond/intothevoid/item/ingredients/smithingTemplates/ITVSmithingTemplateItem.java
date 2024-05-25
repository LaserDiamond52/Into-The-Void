package net.laserdiamond.intothevoid.item.ingredients.smithingTemplates;

import net.laserdiamond.intothevoid.item.ITVSimpleItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

/**
 * An abstract object of the smithing templates for this mod. Smithing templates can be duplicated in crafting given that the required ingredients are present
 */
public abstract class ITVSmithingTemplateItem extends ITVSimpleItem {
    public ITVSmithingTemplateItem(Properties pProperties) {
        super(pProperties);
    }

    /**
     * The material item for the recipe. 7 of these will be required to complete the recipe
     * @return An ItemLike object that represents the material item
     */
    public abstract ItemLike materialItem();

    /**
     * The mineral item for the recipe. Only 1 of these will be required to complete the recipe
     * @return An ItemLike object that represents the mineral item
     */
    public abstract ItemLike mineralItem();
}
