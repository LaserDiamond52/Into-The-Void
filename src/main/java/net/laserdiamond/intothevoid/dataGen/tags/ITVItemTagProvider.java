package net.laserdiamond.intothevoid.dataGen.tags;

import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.block.*;
import net.laserdiamond.intothevoid.item.ITVItems;
import net.laserdiamond.intothevoid.item.ItemTaggable;
import net.laserdiamond.intothevoid.item.ingredients.smithingTemplates.ITVSmithingTemplateItem;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

/**
 * The item tag provider of this mod
 */
public class ITVItemTagProvider extends ItemTagsProvider {

    public ITVItemTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> providerCompletableFuture, CompletableFuture<TagLookup<Block>> blockTagGenerator, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, providerCompletableFuture, blockTagGenerator, IntoTheVoid.MODID, existingFileHelper);
    }

    /**
     * Adds all relevant tags to items
     * @param provider The Lookup Provider
     */
    @Override
    protected void addTags(HolderLookup.Provider provider) {

        for (RegistryObject<Item> item : ITVItems.ITEMS.getEntries()) // Loop through all items
        {
            if (item.get() instanceof ArmorItem armorItem) // Add armor tag for armor items
            {
                this.tag(ItemTags.TRIMMABLE_ARMOR).add(armorItem);
            } else if (item.get() instanceof ITVSmithingTemplateItem smithingTemplateItem) // Add template tag for smithing templates
            {
                this.tag(ItemTags.TRIM_TEMPLATES).add(smithingTemplateItem);
            } else if (item.get() instanceof ITVSimpleBlockItem itvSimpleBlockItem) // Add item tags for block items
            {
                if (!itvSimpleBlockItem.getItemTags().isEmpty())
                {
                    for (TagKey<Item> tagKey : itvSimpleBlockItem.getItemTags())
                    {
                        this.tag(tagKey).add(itvSimpleBlockItem);
                    }
                }
            }

            if (item.get() instanceof ItemTaggable itemTaggable) // Add any other tags for items
            {
                if (!itemTaggable.getItemTags().isEmpty())
                {
                    for (TagKey<Item> tagKey : itemTaggable.getItemTags())
                    {
                        this.tag(tagKey).add(item.get());
                    }
                }
            }

        }

    }
}
