package ru.bananus.mmarcane.client.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.trading.MerchantOffers;

@Environment(EnvType.CLIENT)
public class TradeScreen extends Screen {

    MerchantOffers offers;

    public TradeScreen(MerchantOffers offers) {
        super(Component.empty());
        minecraft = Minecraft.getInstance();
        this.offers = offers;
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void render(GuiGraphics guiGraphics, int i, int j, float f) {
        assert minecraft != null;
        this.renderDirtBackground(guiGraphics);
        guiGraphics.drawCenteredString(minecraft.font, "Tecт", width / 2, height / 2, 0xFFFFFF);
        super.render(guiGraphics, i, j, f);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
