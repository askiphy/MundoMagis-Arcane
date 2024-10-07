package ru.bananus.mmarcane.common.spells;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import ru.bananus.mmarcane.api.spell.ISpell;
import ru.bananus.mmarcane.api.spell.data.SpellData;
import ru.bananus.mmarcane.utils.TimeUtils;

public class TeleportioSpell implements ISpell {
    @Override
    public void cast(Player player) {
        ThrownEnderpearl enderpearl = new ThrownEnderpearl(player.level(), player);
        enderpearl.setItem(new ItemStack(Items.ENDER_PEARL));
        enderpearl.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
        player.level().addFreshEntity(enderpearl);
    }

    @Override
    public SpellData getSpellData() {
        return new SpellData.Builder()
                .spellId("teleportio")
                .wandLevel(1)
                .chatTrigger("teleportio")
                .build();
    }
}
