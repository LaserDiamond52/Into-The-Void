package net.laserdiamond.intothevoid.dataGen;

import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.blocks.ITVBlocks;
import net.laserdiamond.intothevoid.blocks.ITVOreBlock;
import net.laserdiamond.intothevoid.blocks.ITVSimpleBlock;
import net.laserdiamond.intothevoid.blocks.ITVWoodLogBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
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
            } else if (block instanceof ITVWoodLogBlock itvWoodLogBlock)
            {
                logBlock((itvWoodLogBlock));
                axisBlock(itvWoodLogBlock, blockTexture(itvWoodLogBlock), blockTexture(itvWoodLogBlock));
                if (itvWoodLogBlock.isStripped())
                {
                    //logBlock(itvWoodLogBlock);
                    axisBlock(itvWoodLogBlock, blockTexture(itvWoodLogBlock), new ResourceLocation(IntoTheVoid.MODID, "blocks/" + itvWoodLogBlock + "_top"));
                }
                blockItem(blockRegistryObject);

            }
        }
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject)
    {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void blockItem(RegistryObject<Block> blockRegistryObject)
    {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(IntoTheVoid.MODID + ":blocks/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }
}
