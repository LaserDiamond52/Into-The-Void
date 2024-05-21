package net.laserdiamond.intothevoid.dataGen;

import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.block.ITVBlocks;
import net.laserdiamond.intothevoid.block.ITVSaplingBlock;
import net.laserdiamond.intothevoid.item.ITVItems;
import net.laserdiamond.intothevoid.item.ITVSimpleItem;
import net.laserdiamond.intothevoid.item.equipment.armor.ITVArmorItem;
import net.laserdiamond.intothevoid.item.equipment.tools.*;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.*;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedHashMap;

public class ITVItemModelProvider extends ItemModelProvider {
    public ITVItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, IntoTheVoid.MODID, existingFileHelper);
    }

    private static final LinkedHashMap<ResourceKey<TrimMaterial>, Float> TRIM_MATERIALS = new LinkedHashMap<>();
    static
    {
        TRIM_MATERIALS.put(TrimMaterials.QUARTZ, 0.1F);
        TRIM_MATERIALS.put(TrimMaterials.IRON, 0.2F);
        TRIM_MATERIALS.put(TrimMaterials.NETHERITE, 0.3F);
        TRIM_MATERIALS.put(TrimMaterials.REDSTONE, 0.4F);
        TRIM_MATERIALS.put(TrimMaterials.COPPER, 0.5F);
        TRIM_MATERIALS.put(TrimMaterials.GOLD, 0.6F);
        TRIM_MATERIALS.put(TrimMaterials.EMERALD, 0.7F);
        TRIM_MATERIALS.put(TrimMaterials.DIAMOND, 0.8F);
        TRIM_MATERIALS.put(TrimMaterials.LAPIS, 0.9F);
        TRIM_MATERIALS.put(TrimMaterials.AMETHYST, 1.0F);
    }

    @Override
    protected void registerModels()
    {
        for (RegistryObject<Item> item : ITVItems.ITEMS.getEntries())
        {
            if (item.get() instanceof ITVSimpleItem)
            {
                simpleItem(item);

            } else if (item.get() instanceof ITVArmorItem itvArmorItem)
            {
                if (itvArmorItem.simpleArmorItem())
                {
                    trimmedArmorItem(item);
                }

            } else if (item.get() instanceof ITVSimpleSwordItem)
            {
                handheldItem(item);

            } else if (item.get() instanceof ITVSimplePickaxeItem)
            {
                handheldItem(item);

            } else if (item.get() instanceof ITVSimpleAxeItem)
            {
                handheldItem(item);

            } else if (item.get() instanceof ITVSimpleShovelItem)
            {
                handheldItem(item);

            } else if (item.get() instanceof ITVSimpleHoeItem)
            {
                handheldItem(item);

            } else if (item.get() instanceof SignItem)
            {
                simpleItem(item);
            } else if (item.get() instanceof HangingSignItem)
            {
                simpleItem(item);
            }
        }
        for (RegistryObject<Block> blockRegistryObject : ITVBlocks.BLOCKS.getEntries())
        {
            if (blockRegistryObject.get() instanceof ITVSaplingBlock)
            {
                saplingItem(blockRegistryObject);
            }
        }

        for (ITVBlocks.WoodBlocks woodBlocks : ITVBlocks.WoodBlocks.values())
        {
            RegistryObject<Block> planks = woodBlocks.getPlanks();
            RegistryObject<Block> door = woodBlocks.getDoor();
            RegistryObject<Block> trapDoor = woodBlocks.getTrapDoor();
            RegistryObject<Block> fence = woodBlocks.getFence();
            RegistryObject<Block> button = woodBlocks.getButton();

            simpleBlockItem(door);
            fenceItem(fence, planks);
            buttonItem(button, planks);


            slabItem(woodBlocks.getSlab(), planks);
            stairItem(woodBlocks.getStairs(), planks);
            pressurePlateItem(woodBlocks.getPressurePlate(), planks);
            trapDoorItem(trapDoor);
            fenceGateItem(woodBlocks.getFenceGate(), planks);
        }
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> itemRegistryObject)
    {
        return withExistingParent(itemRegistryObject.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(IntoTheVoid.MODID, "item/" + itemRegistryObject.getId().getPath()));
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> itemRegistryObject)
    {
        return withExistingParent(itemRegistryObject.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(IntoTheVoid.MODID, "item/" + itemRegistryObject.getId().getPath()));
    }

    private ItemModelBuilder saplingItem(RegistryObject<Block> block)
    {
        return withExistingParent(block.getId().getPath(),
                new ResourceLocation("item/generated"))
                .texture("layer0", new ResourceLocation(IntoTheVoid.MODID, "block/" + block.getId().getPath()));
    }

    private ItemModelBuilder fenceItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock)
    {
        return this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture", new ResourceLocation(IntoTheVoid.MODID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    private ItemModelBuilder buttonItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock)
    {
        return this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture", new ResourceLocation(IntoTheVoid.MODID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    private ItemModelBuilder slabItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock)
    {
        String path = "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath();
        return this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/slab"))
                .texture("bottom", new ResourceLocation(IntoTheVoid.MODID, path))
                .texture("side", new ResourceLocation(IntoTheVoid.MODID, path))
                .texture("top", new ResourceLocation(IntoTheVoid.MODID, path));
    }

    private ItemModelBuilder stairItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock)
    {
        String path = "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath();
        return this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/stairs"))
                .texture("bottom", new ResourceLocation(IntoTheVoid.MODID, path))
                .texture("side", new ResourceLocation(IntoTheVoid.MODID, path))
                .texture("top", new ResourceLocation(IntoTheVoid.MODID, path));
    }

    private ItemModelBuilder pressurePlateItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock)
    {
        return this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/pressure_plate_up"))
                .texture("texture", new ResourceLocation(IntoTheVoid.MODID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    private ItemModelBuilder trapDoorItem(RegistryObject<Block> block)
    {
        return this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/template_orientable_trapdoor_bottom"))
                .texture("texture", new ResourceLocation(IntoTheVoid.MODID, "block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }

    private ItemModelBuilder fenceGateItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock)
    {
        return this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/template_fence_gate"))
                .texture("texture", new ResourceLocation(IntoTheVoid.MODID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    private ItemModelBuilder simpleBlockItem(RegistryObject<Block> block)
    {
        return this.withExistingParent(block.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(IntoTheVoid.MODID, "item/" + block.getId().getPath()));
    }

    private void trimmedArmorItem(RegistryObject<Item> itemRegistryObject)
    {
        final String MOD_ID = IntoTheVoid.MODID;

        if (itemRegistryObject.get() instanceof ArmorItem armorItem)
        {
            TRIM_MATERIALS.entrySet().forEach(entry ->
            {
                ResourceKey<TrimMaterial> trimMaterial = entry.getKey();
                float trimValue = entry.getValue();

                String armorType = switch (armorItem.getEquipmentSlot())
                {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };

                String armorItemPath = "item/" + armorItem;
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = new ResourceLocation(MOD_ID, armorItemPath);
                ResourceLocation trimResLoc = new ResourceLocation(trimPath); // Minecraft namespace
                ResourceLocation trimNameResLoc = new ResourceLocation(MOD_ID, currentTrimName);

                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc)
                        .texture("layer1", trimResLoc);

                this.withExistingParent(itemRegistryObject.getId().getPath(),
                        mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0", new ResourceLocation(MOD_ID, "item/" + itemRegistryObject.getId().getPath()));
            });
        }
    }


}
