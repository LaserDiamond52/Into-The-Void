package net.laserdiamond.intothevoid.util;

import net.laserdiamond.intothevoid.IntoTheVoid;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

/**
 * Class that contains all wood types of this mod
 */
public class ITVWoodTypes {

    /**
     * WoodType object that represnt Purpur Wood
     */
    public static final WoodType PURPUR = WoodType.register(new WoodType(IntoTheVoid.MODID + ":purpur", BlockSetType.OAK));
}
