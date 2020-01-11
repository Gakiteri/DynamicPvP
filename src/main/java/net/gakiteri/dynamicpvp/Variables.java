package net.gakiteri.dynamicpvp;

import net.gakiteri.dynamicpvp.data.DataPlayer;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
public class Variables {

    public static String pluginName;
    public static File dirPlugin;
    public static FileConfiguration config;

    public static Map<UUID, DataPlayer> playerData = new HashMap<>();

}
