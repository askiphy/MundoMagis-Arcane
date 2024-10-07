package ru.bananus.mmarcane.common.spells;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import ru.bananus.mmarcane.api.annotations.MundoSpell;
import ru.bananus.mmarcane.api.spell.ISpell;
import ru.bananus.mmarcane.api.spell.data.SpellData;

@MundoSpell("god_rage")
public class GodRageSpell implements ISpell {

    @Override
    public void cast(Player player) {
        player.displayClientMessage(Component.literal("gg"), true);
    }

    @Override
    public SpellData getSpellData() {
        return new SpellData.Builder()
                .spellId("god_rage")
                .wandLevel(2)
                .chatTrigger("god rage")
                .build();
    }
}
