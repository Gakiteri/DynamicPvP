package net.gakiteri.dynamicpvp.events;

import net.gakiteri.dynamicpvp.Variables;
import net.gakiteri.dynamicpvp.data.DataPlayer;
import net.gakiteri.dynamicpvp.functions.MngConf;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        // Get player data
        Player player = event.getPlayer();
        String uuid = player.getUniqueId().toString();

        // Check if player data was stored
        if (!Variables.playerData.containsKey(uuid)) {
            Variables.playerData.put(uuid, new DataPlayer(false));
            new MngConf().save();
        }
    }
}
