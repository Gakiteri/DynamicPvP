package net.gakiteri.dynamicpvp.functions;

import net.gakiteri.dynamicpvp.Variables;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

import static org.bukkit.Bukkit.*;

public class MngConf {

    private Plugin plugin = getPluginManager().getPlugin(Variables.pluginName);

    public void save() {

        // Sorts players
        ArrayList<String> playersOn = new ArrayList<>();
        ArrayList<String> playersOff = new ArrayList<>();

        Variables.playerData.forEach((i, o) -> {
            if (o.getPvp()) {
                playersOn.add(i);
            } else {
                playersOff.add(i);
            }
        });

        // Adds config
        Variables.config.set("players.on", playersOn);
        Variables.config.set("players.off", playersOff);

        // Saves to file
        plugin.saveConfig();

    }

}
