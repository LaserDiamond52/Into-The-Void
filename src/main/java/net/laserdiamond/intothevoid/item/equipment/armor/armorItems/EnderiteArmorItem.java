package net.laserdiamond.intothevoid.item.equipment.armor.armorItems;

import net.laserdiamond.intothevoid.item.equipment.armor.ITVArmorItem;
import net.laserdiamond.intothevoid.item.equipment.armor.ITVArmorMaterials;
import net.minecraft.world.effect.MobEffectInstance;

import java.util.List;
import java.util.UUID;

/**
 * Class that represents an Enderite Armor Item. Any properties that Enderite armor should have should be done through here
 */
public class EnderiteArmorItem extends ITVArmorItem {
    public EnderiteArmorItem(Type pType, Properties pProperties) {
        super(ITVArmorMaterials.ENDERITE, pType, pProperties);
    }

    @Override
    public boolean simpleArmorItem() {
        return true;
    }

    @Override
    public UUID[] healthUUID() {
        return new UUID[]
                {
                        UUID.fromString("12b2344d-5162-4907-be9e-70537bf11897"),
                        UUID.fromString("bb341b52-e268-47de-8f11-ad021e7403cb"),
                        UUID.fromString("c0a131d0-dec1-4df3-a72d-6a301f157bab"),
                        UUID.fromString("00cad788-1e12-4b83-88d1-c2c32b9c88c6")
                };
    }

    @Override
    public UUID[] meleeDamageUUID() {
        return new UUID[]
                {
                        UUID.fromString("f6867fe9-dc96-4bcd-8f8a-8d83987d67e1"),
                        UUID.fromString("35b68a04-3157-43dc-9107-0ac2d0fa96ee"),
                        UUID.fromString("69ef1473-6812-4ed5-815c-2297d16524b7"),
                        UUID.fromString("973dbfa1-23bb-49f3-808c-78c19d06864e")
                };
    }

    @Override
    public UUID[] speedUUID() {
        return new UUID[]
                {
                        UUID.fromString("9b34bebd-17a0-4ab3-9e1a-04e9852b25d6"),
                        UUID.fromString("3e232264-b3f9-4a9b-a09a-ad50b73b141c"),
                        UUID.fromString("a75a0f82-1616-4f68-a0ac-ad8fa2e7abf3"),
                        UUID.fromString("56c4714e-f958-4b28-9eac-051f76a5dca2")
                };
    }
}
