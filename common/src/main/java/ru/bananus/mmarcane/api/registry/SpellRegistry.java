package ru.bananus.mmarcane.api.registry;

import ru.bananus.mmarcane.api.spell.ISpell;

import java.util.*;

public class SpellRegistry {

    private static final Map<String, ISpell> spells = new HashMap<String, ISpell>();

    private static final Map<String, ISpell> addonSpells = new HashMap<String, ISpell>();

    public static ISpell getSpell(String id) {
        return spells.get(id);
    }

    public static ISpell getAddonSpell(String id) {
        return addonSpells.get(id);
    }

    public static void registerSpell(ISpell spell) {
        spells.put(spell.getSpellData().getSpellId(), spell);
    }

    public static void registerAddonSpell(ISpell spell) {
        spells.put(spell.getSpellData().getSpellId(), spell);
        addonSpells.put(spell.getSpellData().getSpellId(), spell);
    }

    public static Map<String, ISpell> getSpells() {
        return spells;
    }

    public static Map<String, ISpell> getAddonSpells() {
        return addonSpells;
    }
}
