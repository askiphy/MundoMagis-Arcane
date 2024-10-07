package ru.bananus.mmarcane.network;

import dev.architectury.networking.NetworkManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import ru.bananus.mmarcane.client.gui.TradeScreen;
import ru.bananus.mmarcane.init.ItemRegistry;

import java.util.Collection;
import java.util.function.Supplier;

public class S2CShowTradeScreenPacket {

    MerchantOffers offers = new MerchantOffers();

    public S2CShowTradeScreenPacket(FriendlyByteBuf buf) {
        this();
    }

    public S2CShowTradeScreenPacket() {
    }

    public void encode(FriendlyByteBuf buf) {

    }

    public void apply(Supplier<NetworkManager.PacketContext> contextSupplier) {
        offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 16), new ItemStack(ItemRegistry.MAGIC_WAND.get(), 1), 1, 1, 1));

        open();
    }

    @Environment(EnvType.CLIENT)
    private void open() {
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.setScreen(new TradeScreen(offers));
    }
}
