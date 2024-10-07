package ru.bananus.mmarcane.api.spell;

import net.minecraft.world.entity.player.Player;
import ru.bananus.mmarcane.api.spell.data.SpellData;

public interface ISpell {
    void cast(Player player);

    SpellData getSpellData();
}
