package ru.bananus.mmarcane.init;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import ru.bananus.mmarcane.MMArcane;

public class TabRegistry {
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(MMArcane.MODID, Registries.CREATIVE_MODE_TAB);


    public static final RegistrySupplier<CreativeModeTab> MMARCANE_MAIN = TABS.register(
            "mmarcane_main",
            () -> CreativeTabRegistry.create(
                    Component.translatable("tab.mmarcane.main"),
                    () -> new ItemStack(ItemRegistry.MAGIC_WAND.get())
            )
    );

    public static final RegistrySupplier<CreativeModeTab> MMARCANE_SPELLS = TABS.register(
            "mmarcane_spells",
            () -> CreativeTabRegistry.create(
                    Component.translatable("tab.mmarcane.spells"),
                    () -> new ItemStack(ItemRegistry.MAGIC_WAND.get())
            )
    );

    public static final RegistrySupplier<CreativeModeTab> MMARCANE_ADDONS = TABS.register(
            "mmarcane_addons",
            () -> CreativeTabRegistry.create(
                    Component.literal("MundoMagis: Arcane -> Addons"),
                    () -> new ItemStack(ItemRegistry.MAGIC_WAND.get())
            )
    );

    public static void registerCommon() {
        TABS.register();
    }
}
