package ru.bananus.mmarcane.fabric;

import net.fabricmc.api.ClientModInitializer;
import ru.bananus.mmarcane.MMArcaneClient;

public class MMArcaneFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MMArcaneClient.init();
    }
}
