package ru.bananus.mmarcane.fabric;

import ru.bananus.mmarcane.MMArcane;
import net.fabricmc.api.ModInitializer;

public class MMArcaneFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        MMArcane.init();
    }
}