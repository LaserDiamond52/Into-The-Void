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
            }
        }

        for (ITVBlocks.WoodTypes woodTypes : ITVBlocks.WoodTypes.values())
        {
            RegistryObject<Block> logBlock = woodTypes.getLogBlock();
            RegistryObject<Block> woodBlock = woodTypes.getWoodBlock();
            RegistryObject<Block> strippedLogBlock = woodTypes.getStrippedLogBlock();
            RegistryObject<Block> strippedWoodBlock = woodTypes.getStrippedWoodBlock();
            String strippedName = woodTypes.getStrippedLogName();

            logBlock((RotatedPillarBlock) logBlock.get());
            axisBlock(((RotatedPillarBlock) woodBlock.get()), blockTexture(logBlock.get()), blockTexture(logBlock.get()));

            axisBlock(((RotatedPillarBlock) strippedLogBlock.get()), blockTexture(strippedLogBlock.get()),
                    new ResourceLocation(IntoTheVoid.MODID, "block/" + strippedName + "_top"));
            axisBlock(((RotatedPillarBlock) strippedWoodBlock.get()), blockTexture(strippedLogBlock.get()),
                    blockTexture(strippedLogBlock.get()));

            blockItem(logBlock);
            blockItem(woodBlock);
            blockItem(strippedLogBlock);
            blockItem(strippedWoodBlock);
        }
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject)
    {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void blockItem(RegistryObject<Block> blockRegistryObject)
    {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(IntoTheVoid.MODID + ":block/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }
}
