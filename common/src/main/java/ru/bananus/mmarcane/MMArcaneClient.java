package ru.bananus.mmarcane;

import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import ru.bananus.mmarcane.client.render.entity.WizardRender;
import ru.bananus.mmarcane.init.EntityRegistry;

public class MMArcaneClient {
    public static void init() {
        EntityRendererRegistry.register(EntityRegistry.WIZARD, WizardRender::new);
    }
}
