package net.gakiteri.dynamicpvp.functions;

import net.gakiteri.dynamicpvp.Variables;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.UUID;

import static org.bukkit.Bukkit.*;

public class MngConf {

    private Plugin plugin = getPluginManager().getPlugin(Variables.pluginName);

    public void save() {

        // Sorts players
        ArrayList<UUID> playersOn = new ArrayList<>();
        ArrayList<UUID> playersOff = new ArrayList<>();

        Variables.playerData.forEach((i, o) -> {
            if (o.getPvp()) {
                playersOn.add(i.toString());
            } else {
                playersOff.add(i.toString());
            }
        });

        // Adds config
        Variables.config.set("players.on", playersOn);
        Variables.config.set("players.off", playersOff);

        // Saves to file
        plugin.saveConfig();

    }

}
