package net.laserdiamond.intothevoid.item.equipment.armor.armorItems;

import net.laserdiamond.intothevoid.item.equipment.armor.ITVArmorItem;
import net.laserdiamond.intothevoid.item.equipment.armor.ITVArmorMaterials;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ArmorMaterial;

import java.util.List;
import java.util.UUID;

/**
 * Class that represents a Lonsdaleite Armor Item. Any properties that Lonsdaleite armor should have should be done through here.
 */
public class LonsdaleiteArmorItem extends ITVArmorItem {

    public LonsdaleiteArmorItem(Type pType, Properties pProperties) {
        super(ITVArmorMaterials.LONSDALEITE, pType, pProperties);
    }

    @Override
    public boolean simpleArmorItem() {
        return true;
    }

    @Override
    public UUID[] healthUUID() {
        return new UUID[]
        {
                UUID.fromString("723efadf-2813-4395-8d86-b8ac29f6db4e"),
                UUID.fromString("5354cc13-05ff-456c-bc88-1580dfa106a0"),
                UUID.fromString("df4bc350-2d13-4d9c-ae0b-bac331f50b44"),
                UUID.fromString("fe42c411-af7f-4395-92a3-4f9ef96642ae")
        };
    }

    @Override
    public UUID[] meleeDamageUUID() {
        return new UUID[]
        {
                UUID.fromString("cd9d8e20-05c7-4f4e-a59b-3b5f059a2972"),
                UUID.fromString("557fb185-d5fb-424c-b875-02f6088d695c"),
                UUID.fromString("5b1e6d70-3dcf-4828-b5da-44aad9b13b33"),
                UUID.fromString("534afbe1-70b0-4ceb-993f-7d564bf6fc96")
        };
    }

    @Override
    public UUID[] speedUUID() {
        return new UUID[]
        {
                UUID.fromString("d98fd30d-6c3f-4897-b272-81bada6cf4f0"),
                UUID.fromString("fe42963e-f75e-4080-a01a-8fe58df06a14"),
                UUID.fromString("424bcf13-1d73-4ea8-86ef-160cdded141e"),
                UUID.fromString("1049018c-dbd5-4124-800b-8c0bf256a39c")
        };
    }
}
