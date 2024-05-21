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

public class ITVBlocksStateProvider extends BlockStateProvider {
    public ITVBlocksStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, IntoTheVoid.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        for (RegistryObject<Block> blockRegistryObject : ITVBlocks.BLOCKS.getEntries())
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
        simpleBlockWithItem(ITVBlocks.REFINERY.get(), new ModelFile.UncheckedModelFile(modLoc("block/refinery")));
        for (ITVBlocks.WoodBlocks woodBlocks : ITVBlocks.WoodBlocks.values())
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

            blockItem(logBlock);
            blockItem(woodBlock);
            blockItem(strippedLogBlock);
            blockItem(strippedWoodBlock);

            slabBlock(((SlabBlock) slab.get()), blockTexture(planks.get()), blockTexture(planks.get()));
            stairsBlock(((StairBlock) stairs.get()), blockTexture(planks.get()));
            pressurePlateBlock(((PressurePlateBlock) pressurePlate.get()), blockTexture(planks.get()));

            doorBlockWithRenderType(((DoorBlock) door.get()), modLoc("block/" + doorName + "_bottom"), modLoc("block/" + doorName + "_top"), "cutout");
            trapdoorBlockWithRenderType(((TrapDoorBlock) trapDoor.get()), modLoc("block/" + trapDoorName), true, "cutout");

            fenceBlock(((FenceBlock) fence.get()), blockTexture(planks.get()));
            fenceGateBlock(((FenceGateBlock) fenceGate.get()), blockTexture(planks.get()));
            buttonBlock(((ButtonBlock) button.get()), blockTexture(planks.get()));

            signBlock(((StandingSignBlock) sign.get()), ((WallSignBlock) wallSign.get()),
                    blockTexture(planks.get()));
            hangingSignBlock(hangingSign.get(), wallHangingSign.get(),
                    blockTexture(planks.get()));


        }
    }

    private void saplingBlock(RegistryObject<Block> blockRegistryObject)
    {
        simpleBlock(blockRegistryObject.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(),
                        blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject)
    {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void blockItem(RegistryObject<Block> blockRegistryObject)
    {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(IntoTheVoid.MODID + ":block/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }

    private void hangingSignBlock(Block signBlock, Block wallSignBlock, ResourceLocation texture)
    {
        ModelFile sign = models().sign(name(signBlock), texture);
        hangingSignBlock(signBlock, wallSignBlock, sign);
    }

    private void hangingSignBlock(Block signBlock, Block wallSignBlock, ModelFile sign)
    {
        simpleBlock(signBlock, sign);
        simpleBlock(wallSignBlock, sign);
    }

    private String name(Block block)
    {
        return key(block).getPath();
    }

    private ResourceLocation key(Block block)
    {
        return ForgeRegistries.BLOCKS.getKey(block);
    }
}
