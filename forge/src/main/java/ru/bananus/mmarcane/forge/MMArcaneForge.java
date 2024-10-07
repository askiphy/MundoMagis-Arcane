package ru.bananus.mmarcane.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.loading.FMLEnvironment;
import ru.bananus.mmarcane.MMArcane;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MMArcane.MODID)
public class MMArcaneForge {
    public MMArcaneForge() {
        EventBuses.registerModEventBus(MMArcane.MODID, FMLJavaModLoadingContext.get().getModEventBus());
        MMArcane.init();

        if (FMLEnvironment.dist == Dist.CLIENT)
            new MMArcaneForgeClient();
    }
}