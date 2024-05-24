package net.laserdiamond.intothevoid.util;

import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.item.ITVItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;


public class ITVTags {

    public static class Blocks
    {
        public static final TagKey<Block> NEEDS_LONSDALEITE_TOOL = tag("needs_lonsdaleite_tool");
        public static final TagKey<Block> NEEDS_ENDERITE_TOOL = tag("needs_enderite_tool");
        public static final TagKey<Block> NEEDS_DRAGONBORNE_TOOL = tag("needs_dragonborne_tool");
        public static final TagKey<Block> PURPUR_LOG = tag("purpur_logs");

        private static TagKey<Block> tag(String name)
        {
            return BlockTags.create(new ResourceLocation(IntoTheVoid.MODID, name));
        }
    }

    public static class Items
    {
        public static final TagKey<Item> PURPUR_LOG = tag("purpur_logs");
        public static final TagKey<Item> REFINERY_INGREDIENT = tag("refinery_ingredient");

        private static TagKey<Item> tag(String name)
        {
            return ItemTags.create(new ResourceLocation(IntoTheVoid.MODID, name));
        }
    }

    public static class Biomes
    {
        public static final TagKey<Biome> PURPUR_FOREST = tag("is_purpur_forest");

        private static TagKey<Biome> tag(String name)
        {
            return TagKey.create(Registries.BIOME, new ResourceLocation(IntoTheVoid.MODID, name));
        }
    }

    public static class Entities
    {
        public static final TagKey<EntityType<?>> VOID_PIRATES = tag("void_pirate");
        public static final TagKey<EntityType<?>> ENDER_DRAGONS = tag("ender_dragons");
        private static TagKey<EntityType<?>> tag(String name)
        {
            return TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation(IntoTheVoid.MODID, name));
        }
    }
}
