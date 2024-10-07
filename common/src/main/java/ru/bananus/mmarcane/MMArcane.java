package ru.bananus.mmarcane;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.ChatEvent;
import dev.architectury.event.events.common.PlayerEvent;
import dev.architectury.registry.level.entity.EntityAttributeRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import ru.bananus.mmarcane.api.registry.AddonRegistry;
import ru.bananus.mmarcane.api.registry.SpellRegistry;
import ru.bananus.mmarcane.api.spell.ISpell;
import ru.bananus.mmarcane.common.spells.*;
import ru.bananus.mmarcane.init.EntityRegistry;
import ru.bananus.mmarcane.init.ItemRegistry;
import ru.bananus.mmarcane.init.NetworkRegistry;
import ru.bananus.mmarcane.init.TabRegistry;
import ru.bananus.mmarcane.utils.StorageUtils;
import ru.bananus.mmarcane.utils.TextUtils;

import java.util.ArrayList;

public class MMArcane
{
	public static final String MODID = "mmarcane";

	public static void init() {
		NetworkRegistry.registerCommon();
		EntityRegistry.registerCommon();
		EntityAttributeRegistry.register(EntityRegistry.WIZARD, () -> Animal.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 20.0D)
				.add(Attributes.ATTACK_DAMAGE, 3.0f)
				.add(Attributes.ATTACK_SPEED, 1.0f)
				.add(Attributes.FLYING_SPEED, 0.4f)
				.add(Attributes.MOVEMENT_SPEED, 0.4f)
		);

		SpellRegistry.registerSpell(new RegenioSpell());
		SpellRegistry.registerSpell(new GodRageSpell());
		SpellRegistry.registerSpell(new TeleportioSpell());
		SpellRegistry.registerSpell(new MinerStrengthSpell());

		TabRegistry.registerCommon();

		AddonRegistry.initAddons();

		ItemRegistry.registerCommon();

		PlayerEvent.PLAYER_JOIN.register((player -> {
			StorageUtils.playerOpenedSpells.put(player, new ArrayList<>());
			StorageUtils.gatherData(player);
		}));

		ChatEvent.RECEIVED.register(((player, component) -> {
			if (player != null) {
				for (ISpell spell: SpellRegistry.getSpells().values()) {
					if (spell.getSpellData().getChatTrigger() != null && component.getString().equalsIgnoreCase(spell.getSpellData().getChatTrigger())) {
						if (player.getMainHandItem().getItem() == ItemRegistry.MAGIC_WAND.get()) {
							if (StorageUtils.playerOpenedSpells.containsKey(player) && StorageUtils.playerOpenedSpells.get(player).contains(spell.getSpellData().getSpellId())) {
								if (StorageUtils.playerWandsLevel.get(player) >= spell.getSpellData().getWandLevel()) {
									spell.cast(player);
									TextUtils.actionBarTranslate(player, "spell.name." + spell.getSpellData().getSpellId());
								} else {
									TextUtils.actionBar(player, Component.translatable(ChatFormatting.YELLOW + "wand.spell.lowLevel").getString());
								}
							} else {
								TextUtils.actionBarTranslate(player, "spell.no_spells.chat");
							}
						}
					}
				}
			}
			return EventResult.pass();
        }));

		PlayerEvent.PLAYER_QUIT.register((StorageUtils::saveData));
	}
}
