package ru.bananus.mmarcane.common.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import ru.bananus.mmarcane.api.IMundoAddon;
import ru.bananus.mmarcane.api.registry.AddonRegistry;
import ru.bananus.mmarcane.api.registry.SpellRegistry;
import ru.bananus.mmarcane.init.TabRegistry;
import ru.bananus.mmarcane.utils.StorageUtils;
import ru.bananus.mmarcane.utils.TextUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AddonSpellbookItem extends Item {
    public AddonSpellbookItem(String spellId, IMundoAddon addon) {
        super(new Properties().arch$tab(TabRegistry.MMARCANE_ADDONS));
        this.spellId = spellId;
        this.addon = addon;
    }

    String spellId;

    IMundoAddon addon;

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag) {
        list.add(Component.translatable("spellbook.wand.needLevel").append(Component.literal(String.valueOf(Objects.requireNonNull(AddonRegistry.getAddonSpell(spellId)).getSpellData().getWandLevel())).withStyle(ChatFormatting.YELLOW)));
        list.add(Component.translatable("spellbook.addon.desc").append(Component.translatable("addon." + addon.getAddonId())).withStyle(ChatFormatting.YELLOW));
        super.appendHoverText(itemStack, level, list, tooltipFlag);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        if (!level.isClientSide() && interactionHand == InteractionHand.MAIN_HAND) {
            if (!StorageUtils.playerOpenedSpells.get(player).contains(spellId)) {
                StorageUtils.playerWandsLevel.putIfAbsent(player, 1);
                if (StorageUtils.playerWandsLevel.get(player) >= SpellRegistry.getSpell(spellId).getSpellData().getWandLevel()) {
                    StorageUtils.playerOpenedSpells.get(player).add(spellId);
                    TextUtils.actionBar(player, Component.translatable("spell.open").getString(), Map.of("[spell]", Component.translatable("spell.name." + spellId).getString()));
                } else {
                    TextUtils.actionBar(player, Component.translatable("wand.spell.lowLevel").getString());
                }
            } else {
                TextUtils.actionBar(player, Component.translatable("spell.opened_before").getString());
            }
        }
        return super.use(level, player, interactionHand);
    }
}
