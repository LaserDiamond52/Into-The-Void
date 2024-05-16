package net.laserdiamond.intothevoid.dataGen;

import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.block.*;
import net.laserdiamond.intothevoid.item.ITVItems;
import net.laserdiamond.intothevoid.item.ItemTaggable;
import net.laserdiamond.intothevoid.item.ingredients.ITVSmithingTemplateItem;
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

public class ITVItemTagProvider extends ItemTagsProvider {

    public ITVItemTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> providerCompletableFuture, CompletableFuture<TagLookup<Block>> blockTagGenerator, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, providerCompletableFuture, blockTagGenerator, IntoTheVoid.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        for (RegistryObject<Item> item : ITVItems.ITEMS.getEntries())
        {
            if (item.get() instanceof ArmorItem armorItem)
            {
                this.tag(ItemTags.TRIMMABLE_ARMOR).add(armorItem);
            } else if (item.get() instanceof ITVSmithingTemplateItem smithingTemplateItem)
            {
                this.tag(ItemTags.TRIM_TEMPLATES).add(smithingTemplateItem);
            } else if (item.get() instanceof ITVSimpleBlockItem itvSimpleBlockItem)
            {
                if (!itvSimpleBlockItem.getItemTags().isEmpty())
                {
                    for (TagKey<Item> tagKey : itvSimpleBlockItem.getItemTags())
                    {
                        this.tag(tagKey).add(itvSimpleBlockItem);
                    }
                }
            }

            if (item.get() instanceof ItemTaggable itemTaggable)
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
