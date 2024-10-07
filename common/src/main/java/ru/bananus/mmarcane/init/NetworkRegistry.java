package ru.bananus.mmarcane.init;

import dev.architectury.networking.NetworkChannel;
import net.minecraft.resources.ResourceLocation;
import ru.bananus.mmarcane.MMArcane;
import ru.bananus.mmarcane.network.S2CShowTradeScreenPacket;

public class NetworkRegistry {
    public static final NetworkChannel CHANNEL = NetworkChannel.create(new ResourceLocation(MMArcane.MODID, "networking"));

    public static void registerCommon() {
        CHANNEL.register(S2CShowTradeScreenPacket.class, S2CShowTradeScreenPacket::encode, S2CShowTradeScreenPacket::new, S2CShowTradeScreenPacket::apply);
    }
}
