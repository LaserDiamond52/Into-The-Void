package net.laserdiamond.intothevoid.dataGen.tags;

import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.block.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

/**
 * The block tag generator of this mod. Creates categories of blocks based on their tags
 */
public class ITVBlockTagGenerator extends BlockTagsProvider {
    public ITVBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, IntoTheVoid.MODID, existingFileHelper);
    }

    /**
     * Adds any and all tags specified by block objects of this mod that implement the "BlockTaggable" interface
     * @param provider the Lookup Provider
     */
    @Override
    protected void addTags(HolderLookup.Provider provider) {

        for (RegistryObject<Block> blockRegistryObject : ITVBlocks.BLOCKS.getEntries()) // Loop through all block entries
        {
            Block block = blockRegistryObject.get();
            if (block instanceof BlockTaggable blockTaggable) // Add block tags
            {
                for (TagKey<Block> tagKey : blockTaggable.getBlockTags())
                {
                    this.tag(tagKey).add(block);
                }
            }

        }
    }
}
