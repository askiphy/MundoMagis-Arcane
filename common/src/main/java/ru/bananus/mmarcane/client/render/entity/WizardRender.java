package ru.bananus.mmarcane.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import ru.bananus.mmarcane.client.model.entity.WizardModel;
import ru.bananus.mmarcane.common.entity.WizardEntity;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class WizardRender extends GeoEntityRenderer<WizardEntity> {
    public WizardRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new WizardModel());
    }
}
