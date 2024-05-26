package net.laserdiamond.intothevoid.dataGen;

import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.block.*;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * Responsible for generating models and states for most blocks of this mod
 */
public class ITVBlocksStateProvider extends BlockStateProvider {

    public ITVBlocksStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, IntoTheVoid.MODID, exFileHelper);
    }

    /**
     * Registers blocks and state for applicable blocks
     */
    @Override
    protected void registerStatesAndModels() {

        for (RegistryObject<Block> blockRegistryObject : ITVBlocks.BLOCKS.getEntries()) // Loop through all blocks
        {
            Block block = blockRegistryObject.get();
            if (block instanceof ITVSimpleBlock)
            {
                blockWithItem(blockRegistryObject);
            } else if (block instanceof ITVOreBlock itvOreBlock)
            {
                if (itvOreBlock.isSimple())
                {
                    blockWithItem(blockRegistryObject);
                }
            } else if (block instanceof ITVLeavesBlock)
            {
                blockWithItem(blockRegistryObject);
            } else if (block instanceof ITVSaplingBlock)
            {
                saplingBlock(blockRegistryObject);
            } else if (block instanceof NullceliumBlock nullceliumBlock)
            {
                if (nullceliumBlock.getAllSides())
                {
                    blockWithItem(blockRegistryObject);
                }
            }
        }
        simpleBlockWithItem(ITVBlocks.REFINERY.get(), new ModelFile.UncheckedModelFile(modLoc("block/refinery"))); // Refinery block
        for (ITVBlocks.WoodBlocks woodBlocks : ITVBlocks.WoodBlocks.values()) // Loop through all wood block types
        {
            RegistryObject<Block> logBlock = woodBlocks.getLogBlock();
            RegistryObject<Block> woodBlock = woodBlocks.getWoodBlock();
            RegistryObject<Block> strippedLogBlock = woodBlocks.getStrippedLogBlock();
            RegistryObject<Block> strippedWoodBlock = woodBlocks.getStrippedWoodBlock();
            RegistryObject<Block> planks = woodBlocks.getPlanks();
            RegistryObject<Block> slab = woodBlocks.getSlab();
            RegistryObject<Block> stairs = woodBlocks.getStairs();
            RegistryObject<Block> pressurePlate = woodBlocks.getPressurePlate();
            RegistryObject<Block> door = woodBlocks.getDoor();
            RegistryObject<Block> trapDoor = woodBlocks.getTrapDoor();
            RegistryObject<Block> fence = woodBlocks.getFence();
            RegistryObject<Block> fenceGate = woodBlocks.getFenceGate();
            RegistryObject<Block> button = woodBlocks.getButton();
            RegistryObject<Block> sign = woodBlocks.getSign();
            RegistryObject<Block> hangingSign = woodBlocks.getHangingSign();
            RegistryObject<Block> wallSign = woodBlocks.getWallSign();
            RegistryObject<Block> wallHangingSign = woodBlocks.getWallHangingSign();

            String strippedLogName = strippedLogBlock.getId().toString().replaceAll(IntoTheVoid.MODID + ":", "");
            String doorName = door.getId().toString().replaceAll(IntoTheVoid.MODID + ":", "");
            String trapDoorName = trapDoor.getId().toString().replaceAll(IntoTheVoid.MODID + ":", "");

            logBlock((RotatedPillarBlock) logBlock.get());
            axisBlock(((RotatedPillarBlock) woodBlock.get()), blockTexture(logBlock.get()), blockTexture(logBlock.get()));

            axisBlock(((RotatedPillarBlock) strippedLogBlock.get()), blockTexture(strippedLogBlock.get()),
                    new ResourceLocation(IntoTheVoid.MODID, "block/" + strippedLogName + "_top"));
            axisBlock(((RotatedPillarBlock) strippedWoodBlock.get()), blockTexture(strippedLogBlock.get()),
                    blockTexture(strippedLogBlock.get()));

            blockItem(logBlock); // Log block
            blockItem(woodBlock); // Wood block
            blockItem(strippedLogBlock); // Stripped Log block
            blockItem(strippedWoodBlock); // Stripped Wood block

            slabBlock(((SlabBlock) slab.get()), blockTexture(planks.get()), blockTexture(planks.get())); // Slab block
            stairsBlock(((StairBlock) stairs.get()), blockTexture(planks.get())); // Stairs block
            pressurePlateBlock(((PressurePlateBlock) pressurePlate.get()), blockTexture(planks.get())); // Pressure Plate block

            doorBlockWithRenderType(((DoorBlock) door.get()), modLoc("block/" + doorName + "_bottom"), modLoc("block/" + doorName + "_top"), "cutout"); // Door block
            trapdoorBlockWithRenderType(((TrapDoorBlock) trapDoor.get()), modLoc("block/" + trapDoorName), true, "cutout"); // Trapdoor block

            fenceBlock(((FenceBlock) fence.get()), blockTexture(planks.get())); // Fence block
            fenceGateBlock(((FenceGateBlock) fenceGate.get()), blockTexture(planks.get())); // Fence Gate block
            buttonBlock(((ButtonBlock) button.get()), blockTexture(planks.get())); // Button block

            signBlock(((StandingSignBlock) sign.get()), ((WallSignBlock) wallSign.get()), // Sign block
                    blockTexture(planks.get()));
            hangingSignBlock(hangingSign.get(), wallHangingSign.get(), // Hanging Sign block
                    blockTexture(planks.get()));


        }
    }

    /**
     * Generates a block state/model of a sapling for the block
     * @param blockRegistryObject The blockRegistryObject
     */
    private void saplingBlock(RegistryObject<Block> blockRegistryObject)
    {
        simpleBlock(blockRegistryObject.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(),
                        blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    /**
     * Generates a block state/model for blocks with all the same sides and an item model of it for the inventory
     * @param blockRegistryObject The blockRegistryObject
     */
    private void blockWithItem(RegistryObject<Block> blockRegistryObject)
    {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    /**
     * Generates a block state/model for block
     * @param blockRegistryObject The blockRegistryObject
     */
    private void blockItem(RegistryObject<Block> blockRegistryObject)
    {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(IntoTheVoid.MODID + ":block/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }

    /**
     * Generates a block state/model of a hanging sign block for the block
     * @param signBlock The sign block
     * @param wallSignBlock The wall sign block
     * @param texture The resource location for the texture
     */
    private void hangingSignBlock(Block signBlock, Block wallSignBlock, ResourceLocation texture)
    {
        ModelFile sign = models().sign(name(signBlock), texture);
        hangingSignBlock(signBlock, wallSignBlock, sign);
    }

    /**
     * Helper method for creating the block state/model for the hanging sign blocks
     * @param signBlock The sign block
     * @param wallSignBlock The wall sign block
     * @param sign The model file of the sign
     */
    private void hangingSignBlock(Block signBlock, Block wallSignBlock, ModelFile sign)
    {
        simpleBlock(signBlock, sign);
        simpleBlock(wallSignBlock, sign);
    }

    /**
     * Helper method for the name of the block
     * @param block The block to get the name of
     * @return A string representing the block's name
     */
    private String name(Block block)
    {
        return key(block).getPath();
    }

    /**
     * Helper method for the resource location of the block
     * @param block The block to get the resource location of
     * @return The resource location of the block
     */
    private ResourceLocation key(Block block)
    {
        return ForgeRegistries.BLOCKS.getKey(block);
    }
}
