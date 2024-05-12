package net.laserdiamond.intothevoid.util;

import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.item.ITVItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;



public class ITVTags {

    public static class Blocks
    {
        public static final TagKey<Block> NEEDS_LONSDALEITE_TOOL = tag("needs_lonsdaleite_tool");
        public static final TagKey<Block> NEEDS_ENDERITE_TOOL = tag("needs_enderite_tool");
        public static final TagKey<Block> NEEDS_DRAGONBORNE_TOOL = tag("needs_dragonborne_tool");

        private static TagKey<Block> tag(String name)
        {
            return BlockTags.create(new ResourceLocation(IntoTheVoid.MODID, name));
        }
    }

    public static class Items
    {

        private static TagKey<Item> tag(String name)
        {
            return ItemTags.create(new ResourceLocation(IntoTheVoid.MODID, name));
        }
    }
}
