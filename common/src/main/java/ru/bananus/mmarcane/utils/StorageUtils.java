package ru.bananus.mmarcane.utils;

import com.eliotlash.mclib.math.functions.limit.Min;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtIo;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.storage.LevelResource;
import ru.bananus.mmarcane.MMArcane;
import ru.bananus.mmarcane.api.registry.SpellRegistry;
import ru.bananus.mmarcane.api.storage.JSONSerializer;
import ru.bananus.mmarcane.api.storage.model.MMAStorageModel;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class StorageUtils {

    public static Map<Player, List<String>> playerOpenedSpells = new HashMap<>();

    public static Map<Player, Integer> playerWandsLevel = new HashMap<>();

    public static Map<Player, String> playerCurrSpells = new HashMap<>();

    public static void saveData(Player player) {
        File worldPath = Minecraft.getInstance().gameDirectory;

        if (worldPath != null) {
            File data = new File(worldPath, "mm_data");
            data.mkdir();

            File dataFile = new File(data, player.getStringUUID() + ".json");

            MMAStorageModel model = new MMAStorageModel();
            model.openedSpells = playerOpenedSpells.get(player);
            model.wandLevel = playerWandsLevel.get(player);
            model.currentSpell = playerCurrSpells.get(player);

            JSONSerializer.toJson(dataFile, model);

            System.out.println("Data saved for " + player.getDisplayName().getString());
        }
    }

    public static void gatherData(Player player) {
        File worldPath = Minecraft.getInstance().gameDirectory;

        if (worldPath != null) {
            File data = new File(worldPath, "mm_data");
            data.mkdir();

            File dataFile = new File(data, player.getStringUUID() + ".json");

            if (!dataFile.exists()) return;

            try {
                MMAStorageModel model = JSONSerializer.toJava(dataFile, MMAStorageModel.class);

                playerOpenedSpells.put(player, model.openedSpells);
                playerWandsLevel.put(player, model.wandLevel);
                playerCurrSpells.put(player, model.currentSpell);

                System.out.println("Data gathered for " + player.getDisplayName().getString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static List<String> getOpenedSpells(Player player) {
        return playerOpenedSpells.get(player);
    }
}
