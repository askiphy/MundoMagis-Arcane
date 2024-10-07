package ru.bananus.mmarcane.init;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import ru.bananus.mmarcane.MMArcane;
import ru.bananus.mmarcane.common.entity.WizardEntity;

public class EntityRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(MMArcane.MODID, Registries.ENTITY_TYPE);

    public static final RegistrySupplier<EntityType<WizardEntity>> WIZARD = ENTITIES.register("wizard", () -> EntityType.Builder.of(WizardEntity::new, MobCategory.MISC)
            .fireImmune()
            .build("wizard")
    );

    public static void registerCommon() {
        ENTITIES.register();
    }
}
