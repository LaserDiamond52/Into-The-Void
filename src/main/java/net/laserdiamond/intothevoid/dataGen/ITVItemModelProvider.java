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
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedHashMap;

/**
 * Class that data generates item models
 */
public class ITVItemModelProvider extends ItemModelProvider {
    public ITVItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, IntoTheVoid.MODID, existingFileHelper);
    }

    /**
     * HashMap that maps Armor Trim Materials to their float value. This is used for helping with generation of the armor model files
     */
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

    /**
     * Creates all the item models for the appropriate items based on inheritance (similar to the BlockStateProvider for this mod)
     */
    @Override
    protected void registerModels()
    {
        for (RegistryObject<Item> item : ITVItems.ITEMS.getEntries()) // Loop through all items
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
            } else if (item.get() instanceof ForgeSpawnEggItem)
            {
                spawnEggItem(item);
            }
        }
        for (RegistryObject<Block> blockRegistryObject : ITVBlocks.BLOCKS.getEntries())
        {
            if (blockRegistryObject.get() instanceof ITVSaplingBlock)
            {
                saplingItem(blockRegistryObject); // Item model for sapling blocks
            }
        }

        for (ITVBlocks.WoodBlocks woodBlocks : ITVBlocks.WoodBlocks.values()) // Loop through wood types
        {
            RegistryObject<Block> planks = woodBlocks.getPlanks();
            RegistryObject<Block> door = woodBlocks.getDoor();
            RegistryObject<Block> trapDoor = woodBlocks.getTrapDoor();
            RegistryObject<Block> fence = woodBlocks.getFence();
            RegistryObject<Block> button = woodBlocks.getButton();

            simpleBlockItem(door); // Door
            fenceItem(fence, planks); // Fence
            buttonItem(button, planks); // Fence Gate


            slabItem(woodBlocks.getSlab(), planks); // Slab
            stairItem(woodBlocks.getStairs(), planks); // Stairs
            pressurePlateItem(woodBlocks.getPressurePlate(), planks); // Pressure Plate
            trapDoorItem(trapDoor); // Trapdoor
            fenceGateItem(woodBlocks.getFenceGate(), planks); // Fence Gate
        }
    }

    /**
     * Creates a simple model for the item
     * @param itemRegistryObject The item to make the model for
     */
    private void simpleItem(RegistryObject<Item> itemRegistryObject)
    {
        withExistingParent(itemRegistryObject.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(IntoTheVoid.MODID, "item/" + itemRegistryObject.getId().getPath()));
    }

    /**
     * Creates a handheld model for the item. Typically used for tools/weapons
     * @param itemRegistryObject The item to make the model for
     */
    private void handheldItem(RegistryObject<Item> itemRegistryObject)
    {
        withExistingParent(itemRegistryObject.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(IntoTheVoid.MODID, "item/" + itemRegistryObject.getId().getPath()));
    }

    /**
     * Creates a spawn egg model for the item. Typically used only for spawn eggs
     * @param itemRegistryObject The item to make the model for
     */
    private void spawnEggItem(RegistryObject<Item> itemRegistryObject)
    {
        withExistingParent(itemRegistryObject.getId().getPath(),
                new ResourceLocation("item/template_spawn_egg"));
    }

    /**
     * Creates a sapling model for the item. Typically used for saplings
     * @param block The block to make the item model for
     */
    private void saplingItem(RegistryObject<Block> block)
    {
        withExistingParent(block.getId().getPath(),
                new ResourceLocation("item/generated"))
                .texture("layer0", new ResourceLocation(IntoTheVoid.MODID, "block/" + block.getId().getPath()));
    }

    /**
     * Creates a fence model for the item. Typically used for Fences
     * @param block The fence block
     * @param baseBlock The block to use the texture of (typically uses the plank block of the wood type)
     */
    private void fenceItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock)
    {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture", new ResourceLocation(IntoTheVoid.MODID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    /**
     * Creates a button model for the item. Typically used for buttons
     * @param block The button block
     * @param baseBlock The block to use the texture of (typically uses the plank block of the wood type if a wood button)
     */
    private void buttonItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock)
    {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture", new ResourceLocation(IntoTheVoid.MODID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    /**
     * Creates a slab model for the item. Typically used for slabs
     * @param block The slab block
     * @param baseBlock The block to use the texture of (typically uses the plank block of the wood type if a wood slab)
     */
    private void slabItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock)
    {
        String path = "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath();
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/slab"))
                .texture("bottom", new ResourceLocation(IntoTheVoid.MODID, path))
                .texture("side", new ResourceLocation(IntoTheVoid.MODID, path))
                .texture("top", new ResourceLocation(IntoTheVoid.MODID, path));
    }

    /**
     * Creates a stair model for the item. Typically used for stairs
     * @param block The stair block
     * @param baseBlock The block to use the texture of (typically uses the plank block of the wood type if a wood stair)
     */
    private void stairItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock)
    {
        String path = "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath();
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/stairs"))
                .texture("bottom", new ResourceLocation(IntoTheVoid.MODID, path))
                .texture("side", new ResourceLocation(IntoTheVoid.MODID, path))
                .texture("top", new ResourceLocation(IntoTheVoid.MODID, path));
    }

    /**
     * Creates a pressure plate model for the item. Typically used for stairs
     * @param block The pressure plate block
     * @param baseBlock The block to use the texture of (typically uses the plank block of the wood type if a wood pressure plate)
     */
    private void pressurePlateItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock)
    {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/pressure_plate_up"))
                .texture("texture", new ResourceLocation(IntoTheVoid.MODID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    /**
     * Creates a trapdoor model for the item. Typically used for trapdoors
     * @param block The trapdoor block
     */
    private void trapDoorItem(RegistryObject<Block> block)
    {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/template_orientable_trapdoor_bottom"))
                .texture("texture", new ResourceLocation(IntoTheVoid.MODID, "block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }

    /**
     * Creates a fence gate model for the item. Typically used for fence gates
     * @param block The fence gate block
     * @param baseBlock The block to use the texture of (typically uses the plank block of the wood type if a wood fence gate)
     */
    private void fenceGateItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock)
    {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/template_fence_gate"))
                .texture("texture", new ResourceLocation(IntoTheVoid.MODID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    /**
     * Creates a simple block item model for the item. Used for the door block
     * @param block The door block
     */
    private void simpleBlockItem(RegistryObject<Block> block)
    {
        this.withExistingParent(block.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(IntoTheVoid.MODID, "item/" + block.getId().getPath()));
    }

    /**
     * Creates item models for armor items. Generates a model for each trim material that can be applied to an armor piece
     * @param itemRegistryObject The armor item. If any other item type is used in this method, nothing will happen
     */
    private void trimmedArmorItem(RegistryObject<Item> itemRegistryObject)
    {
        final String MOD_ID = IntoTheVoid.MODID;

        if (itemRegistryObject.get() instanceof ArmorItem armorItem)
        {
            TRIM_MATERIALS.entrySet().forEach(entry -> // For each entry in the TRIM_MATERIALS HashMap, we do this:
            {
                ResourceKey<TrimMaterial> trimMaterial = entry.getKey(); // Trim Material resource key
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

                // Used to help the ExistingFileHelper acknowledge that this texture exists
                // Helps to avoid an IllegalArgumentException
                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                getBuilder(currentTrimName) // Armor model file for trimmed armor piece
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc)
                        .texture("layer1", trimResLoc);

                this.withExistingParent(itemRegistryObject.getId().getPath(), // Armor model file for armor without trim
                        mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0", new ResourceLocation(MOD_ID, "item/" + itemRegistryObject.getId().getPath()));
            });
        }
    }


}
