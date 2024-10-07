package ru.bananus.mmarcane.common.spells;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import ru.bananus.mmarcane.api.spell.ISpell;
import ru.bananus.mmarcane.api.spell.data.SpellData;
import ru.bananus.mmarcane.utils.TimeUtils;

public class MinerStrengthSpell implements ISpell {
    @Override
    public void cast(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, TimeUtils.secToTicks(20)));
        player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, TimeUtils.secToTicks(20)));
        player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, TimeUtils.secToTicks(20)));
        player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, TimeUtils.secToTicks(20)));
        player.addEffect(new MobEffectInstance(MobEffects.LUCK, TimeUtils.secToTicks(20)));
        player.addEffect(new MobEffectInstance(MobEffects.HUNGER, TimeUtils.secToTicks(20)));
    }

    @Override
    public SpellData getSpellData() {
        return new SpellData.Builder()
                .wandLevel(1)
                .spellId("miner_strength")
                .chatTrigger("miner strength")
                .build();
    }
}
