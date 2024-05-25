package net.laserdiamond.intothevoid.item.equipment.tools;

import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.item.ITVItems;
import net.laserdiamond.intothevoid.util.ITVTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

/**
 * All tool tiers for equipment/tool items of this mod
 */
public class ITVToolTiers {

    /**
     * Tool tier for Lonsdaleite tools
     */
    public static final Tier LONSDALEITE = TierSortingRegistry.registerTier(
            new ForgeTier(5, 2500, 9.0f, 4f, 18,
                    ITVTags.Blocks.NEEDS_LONSDALEITE_TOOL, () -> Ingredient.of(ITVItems.REFINED_LONSDALEITE.get())),
            new ResourceLocation(IntoTheVoid.MODID, "lonsdaleite"), List.of(Tiers.NETHERITE), List.of());

    /**
     * Tool tier for Enderite tools
     */
    public static final Tier ENDERITE = TierSortingRegistry.registerTier(
            new ForgeTier(5, 2650, 9.0f, 4f, 18,
                    ITVTags.Blocks.NEEDS_ENDERITE_TOOL, () -> Ingredient.of(ITVItems.ENDERITE.get())),
            new ResourceLocation(IntoTheVoid.MODID, "enderite"), List.of(Tiers.NETHERITE), List.of());

    /**
     * Tool tier for Dragonborne tools
     */
    public static final Tier DRAGONBORNE = TierSortingRegistry.registerTier(
            new ForgeTier(6, 3350, 11.0f, 5f, 21,
                    ITVTags.Blocks.NEEDS_DRAGONBORNE_TOOL, () -> Ingredient.of(ITVItems.DRAGON_BONE.get(), ITVItems.REFINED_END_CRYSTAL.get())),
            new ResourceLocation(IntoTheVoid.MODID, "dragonborne"), List.of(Tiers.NETHERITE), List.of()
    );
}
