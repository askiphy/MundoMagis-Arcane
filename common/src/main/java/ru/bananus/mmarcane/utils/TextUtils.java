package ru.bananus.mmarcane.utils;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

import java.util.Map;

public class TextUtils {
    public static void actionBar(Player player, String text) {
        player.displayClientMessage(Component.literal(text), true);
    }

    public static void actionBarTranslate(Player player, String text) {
        player.displayClientMessage(Component.translatable(text), true);
    }

    public static void actionBarTranslate(Player player, String text, Map<String, String> replace) {
        String newText = text;
        for (String key : replace.keySet()) {
            newText = text.replace(key, replace.get(key));
        }
        actionBarTranslate(player, newText);
    }

    public static void actionBar(Player player, String text, Map<String, String> replace) {
        String newText = text;
        for (String key : replace.keySet()) {
            newText = text.replace(key, replace.get(key));
        }
        actionBar(player, newText);
    }
}
