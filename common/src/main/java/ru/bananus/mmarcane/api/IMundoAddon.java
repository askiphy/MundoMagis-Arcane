package ru.bananus.mmarcane.api;

import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.world.item.Item;
import ru.bananus.mmarcane.api.spell.ISpell;

import java.util.List;

public interface IMundoAddon {
    String getAddonId();

    String getModId();

    DeferredRegister<Item> getItemRegistry();

    List<ISpell> getAddonSpells();
}
