package net.laserdiamond.intothevoid.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents a simple item of this mod
 */
public class ITVSimpleItem extends Item {
    public ITVSimpleItem(Properties pProperties) {
        super(pProperties);
    }

}
