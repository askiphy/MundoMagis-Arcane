package ru.bananus.mmarcane.common.items;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import ru.bananus.mmarcane.api.registry.SpellRegistry;
import ru.bananus.mmarcane.init.TabRegistry;
import ru.bananus.mmarcane.utils.StorageUtils;
import ru.bananus.mmarcane.utils.TextUtils;

import java.util.List;

public class MagicWandItem extends Item {

    public MagicWandItem() {
        super(new Item.Properties().arch$tab(TabRegistry.MMARCANE_MAIN));
    }

    int spellId = 0;

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag) {
        Player player = Minecraft.getInstance().player;
        if (player != null) {
            if (!StorageUtils.playerWandsLevel.containsKey(player)) {
                StorageUtils.playerWandsLevel.put(player, 1);
            }
            if (!StorageUtils.playerCurrSpells.containsKey(player) && StorageUtils.playerOpenedSpells.get(player) != null && !StorageUtils.playerOpenedSpells.get(player).isEmpty()) {
                StorageUtils.playerCurrSpells.put(player, StorageUtils.playerOpenedSpells.get(player).get(0));
            }
            int wandLevel = StorageUtils.playerWandsLevel.get(player);
            list.add(Component.translatable("wand.level.desc").append(Component.literal(ChatFormatting.YELLOW + String.valueOf(wandLevel))));
            if (StorageUtils.playerCurrSpells.containsKey(player)) {
                String spell = StorageUtils.playerCurrSpells.get(player);

                list.add(Component.translatable("wand.currSpell.desc").append(Component.translatable("spell.name." + spell).withStyle(ChatFormatting.YELLOW)));
            } else {
                list.add(Component.translatable("wand.currSpell.desc").append(Component.translatable("wand.currSpell.noSpell").withStyle(ChatFormatting.YELLOW)));
            }
        }
        super.appendHoverText(itemStack, level, list, tooltipFlag);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        if (!Screen.hasShiftDown()) {
            if (!StorageUtils.playerCurrSpells.containsKey(player) && !StorageUtils.playerOpenedSpells.get(player).isEmpty()) {
                StorageUtils.playerCurrSpells.put(player, StorageUtils.playerOpenedSpells.get(player).get(0));
            } else {
                if (!level.isClientSide()) {
                    if (StorageUtils.playerCurrSpells.containsKey(player)) {
                        if (StorageUtils.playerWandsLevel.get(player) >= SpellRegistry.getSpell(StorageUtils.playerCurrSpells.get(player)).getSpellData().getWandLevel()) {
                            SpellRegistry.getSpell(StorageUtils.playerCurrSpells.get(player)).cast(player);
                        } else {
                            TextUtils.actionBar(player, Component.translatable(ChatFormatting.YELLOW + "wand.spell.lowLevel").getString());
                        }
                    } else {
                        TextUtils.actionBarTranslate(player, "spell.no_spells");
                    }
                }
            }
        } else {
            if (!StorageUtils.playerCurrSpells.containsKey(player) && !StorageUtils.playerOpenedSpells.get(player).isEmpty()) {
                StorageUtils.playerCurrSpells.put(player, StorageUtils.playerOpenedSpells.get(player).get(0));
            } else {
                TextUtils.actionBarTranslate(player, "spell.no_spells");
            }

            if (!player.level().isClientSide && interactionHand==InteractionHand.MAIN_HAND) {
                if ((spellId + 1) >= StorageUtils.playerOpenedSpells.get(player).size())
                    spellId = 0;
                else
                    spellId++;
                StorageUtils.playerCurrSpells.put(player, StorageUtils.playerOpenedSpells.get(player).get(spellId));
                TextUtils.actionBarTranslate(player, "spell.name." + SpellRegistry.getSpells().get(StorageUtils.playerOpenedSpells.get(player).get(spellId)).getSpellData().getSpellId());
            }
        }
        return super.use(level, player, interactionHand);
    }
}
