package net.laserdiamond.intothevoid.dataGen.tags;

import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.entity.ITVEntities;
import net.laserdiamond.intothevoid.util.ITVTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ITVEntityTagProvider extends EntityTypeTagsProvider {
    public ITVEntityTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookUpProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, lookUpProvider, IntoTheVoid.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        super.addTags(pProvider);

        this.tag(ITVTags.Entities.VOID_PIRATES).add(ITVEntities.VOID_PIRATE.get());

        this.tag(ITVTags.Entities.ENDER_DRAGONS).add(ITVEntities.ENDER_DRAGON_HATCHLING.get()).add(EntityType.ENDER_DRAGON);
    }
}
