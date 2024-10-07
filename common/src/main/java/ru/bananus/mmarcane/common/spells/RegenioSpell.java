package ru.bananus.mmarcane.common.spells;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import ru.bananus.mmarcane.api.annotations.MundoSpell;
import ru.bananus.mmarcane.api.spell.ISpell;
import ru.bananus.mmarcane.api.spell.data.SpellData;
import ru.bananus.mmarcane.utils.TimeUtils;

@MundoSpell("regenio")
public class RegenioSpell implements ISpell {

    @Override
    public void cast(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, TimeUtils.secToTicks(10), 1, false, false));
    }

    @Override
    public SpellData getSpellData() {
        return new SpellData.Builder()
                .spellId("regenio")
                .wandLevel(1)
                .chatTrigger("regenio")
                .build();
    }
}
