package net.gakiteri.dynamicpvp;

import net.gakiteri.dynamicpvp.data.DataPlayer;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Variables {

    public static String pluginName;
    public static File dirPlugin;
    public static FileConfiguration config;

    public static Map<String, DataPlayer> playerData = new HashMap<>();

}
