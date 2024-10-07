package ru.bananus.mmarcane.client.model.entity;

import net.minecraft.resources.ResourceLocation;
import ru.bananus.mmarcane.MMArcane;
import ru.bananus.mmarcane.common.entity.WizardEntity;
import software.bernie.geckolib.model.GeoModel;

public class WizardModel extends GeoModel<WizardEntity> {
    @Override
    public ResourceLocation getModelResource(WizardEntity animatable) {
        return new ResourceLocation(MMArcane.MODID, "geo/wizard.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(WizardEntity animatable) {
        return new ResourceLocation(MMArcane.MODID, "textures/entity/wizard.png");
    }

    @Override
    public ResourceLocation getAnimationResource(WizardEntity animatable) {
        return new ResourceLocation(MMArcane.MODID, "animations/wizard.animations.json");
    }
}
