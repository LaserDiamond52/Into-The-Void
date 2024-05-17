package net.laserdiamond.intothevoid.item.ingredients.refinedEndCrystal;

import net.laserdiamond.intothevoid.item.ItemTaggable;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.RenderUtils;

import java.util.List;
import java.util.function.Consumer;

public class RefinedEndCrystal extends Item implements GeoItem, ItemTaggable {

    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    private final List<TagKey<Item>> itemTags;
    public RefinedEndCrystal(Properties pProperties, List<TagKey<Item>> itemTags) {
        super(pProperties);
        this.itemTags = itemTags;
    }

    private PlayState predicate(AnimationState animationState)
    {
        animationState.getController().setAnimation(RawAnimation.begin().then("refinedEndCrystalAnimation", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController(this, "controller",0, this::predicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public double getTick(Object itemStack) {
        return RenderUtils.getCurrentTick();
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private RefinedEndCrystalRenderer renderer;
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if (this.renderer == null)
                {
                    renderer = new RefinedEndCrystalRenderer();
                }
                return this.renderer;
            }
        });
    }


    @Override
    public List<TagKey<Item>> getItemTags() {
        return itemTags;
    }
}
