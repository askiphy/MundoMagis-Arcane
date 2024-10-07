package ru.bananus.mmarcane.api.registry;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.world.item.Item;
import ru.bananus.mmarcane.api.IMundoAddon;
import ru.bananus.mmarcane.api.spell.ISpell;
import ru.bananus.mmarcane.common.items.AddonSpellbookItem;

import java.util.HashMap;
import java.util.Map;

public class AddonRegistry {
    private static final Map<String, IMundoAddon> addons = new HashMap<String, IMundoAddon>();

    private static final Map<String, Map<String, RegistrySupplier<Item>>> addonSpellbooks = new HashMap<>();

    private static final Map<String, Map<String, ISpell>> addonSpells = new HashMap<>();

    public static IMundoAddon getAddon(String id) {
        return addons.get(id);
    }

    public static void loadAddon(IMundoAddon spell) {
        addons.put(spell.getAddonId(), spell);
        System.out.println("[MMArcane] Addon " + spell.getAddonId() + " loaded.");
    }

    public static Map<String, IMundoAddon> getAddons() {
        return addons;
    }

    public static Map<String, Map<String, RegistrySupplier<Item>>> getAddonSpellbooks() {
        return addonSpellbooks;
    }

    public static ISpell getAddonSpell(String id) {
        for (IMundoAddon addon : addons.values()) {
            for (ISpell spell: addon.getAddonSpells()) {
                if (spell.getSpellData().getSpellId().equals(id)) {
                    return spell;
                }
            }
        }
        return null;
    }

    public static void initAddons() {
        for (IMundoAddon addon: addons.values()) {
            for (ISpell spell: addon.getAddonSpells()) {
                addonSpells.put(addon.getModId(), new HashMap<>());
                addonSpells.get(addon.getModId()).put(spell.getSpellData().getSpellId(), spell);
                SpellRegistry.registerAddonSpell(spell);
                addonSpellbooks.put(addon.getModId(), new HashMap<>());
                addonSpellbooks.get(addon.getModId()).put(spell.getSpellData().getSpellId() + "_spellbook", addon.getItemRegistry().register(spell.getSpellData().getSpellId() + "_spellbook", () -> new AddonSpellbookItem(spell.getSpellData().getSpellId(), addon)));
            }
        }
    }

    public static boolean isAddonSpell(String spellId) {
        for (IMundoAddon addon: AddonRegistry.getAddons().values()) {
            for (ISpell spell: addon.getAddonSpells()) {
                if (spell.getSpellData().getSpellId().equals(spellId)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static IMundoAddon getAddonBySpell(String spellId) {
        for (IMundoAddon addon: getAddons().values()) {
            if (addon.getAddonSpells().contains(getAddonSpell(spellId))) {
                return addon;
            }
        }
        return null;
    }
}
