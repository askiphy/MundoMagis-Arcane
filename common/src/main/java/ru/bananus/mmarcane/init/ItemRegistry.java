package ru.bananus.mmarcane.init;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import ru.bananus.mmarcane.MMArcane;
import ru.bananus.mmarcane.api.registry.AddonRegistry;
import ru.bananus.mmarcane.api.registry.SpellRegistry;
import ru.bananus.mmarcane.api.spell.ISpell;
import ru.bananus.mmarcane.common.items.MagicWandItem;
import ru.bananus.mmarcane.common.items.SpellbookItem;

import java.util.HashMap;
import java.util.Map;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MMArcane.MODID, Registries.ITEM);

    public static final Map<String, RegistrySupplier<Item>> spellBooks = new HashMap<>();

    public static final RegistrySupplier<Item> MAGIC_WAND = ITEMS.register("magic_wand", MagicWandItem::new);

    public static void registerCommon() {
        for (ISpell spell : SpellRegistry.getSpells().values()) {
            if (!SpellRegistry.getAddonSpells().containsKey(spell.getSpellData().getSpellId()))
                spellBooks.put(spell.getSpellData().getSpellId() + "_spellbook", ITEMS.register(spell.getSpellData().getSpellId() + "_spellbook", () -> new SpellbookItem(spell.getSpellData().getSpellId())));
        }

        ITEMS.register();
    }
}
