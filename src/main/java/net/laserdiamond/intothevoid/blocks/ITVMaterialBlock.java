package net.laserdiamond.intothevoid.blocks;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

public final class ITVMaterialBlock extends ITVSimpleBlock {

    private final RegistryObject<Item> itemToDrop;
    public ITVMaterialBlock(Properties pProperties, TagKey<Block> miningTag, RegistryObject<Item> itemToDrop) {
        super(pProperties, miningTag);
        this.itemToDrop = itemToDrop;
    }

    public RegistryObject<Item> getItemToDrop() {
        return itemToDrop;
    }
}
