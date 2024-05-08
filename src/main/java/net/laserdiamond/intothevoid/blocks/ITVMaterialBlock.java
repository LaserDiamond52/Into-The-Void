package net.laserdiamond.intothevoid.blocks;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ITVMaterialBlock extends ITVSimpleBlock {

    private final RegistryObject<Item> itemToDrop;
    public ITVMaterialBlock(Properties pProperties, List<TagKey<Block>> miningTags, RegistryObject<Item> itemToDrop) {
        super(pProperties, miningTags);
        this.itemToDrop = itemToDrop;
    }

    public RegistryObject<Item> getItemToDrop() {
        return itemToDrop;
    }
}
