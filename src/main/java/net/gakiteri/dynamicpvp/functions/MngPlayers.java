package net.gakiteri.dynamicpvp.functions;

import org.bukkit.entity.Player;
import static org.bukkit.Bukkit.getServer;

public class MngPlayers {

    public Player getPlayer(String val) {

        if (val.length() <= 16) {
            try {
                return getServer().getPlayer(val);
            } catch (Exception e) {
                return null;
            }
        } else {

            for (Player player : getServer().getOnlinePlayers()) {
                if (player.getUniqueId().toString().equals(val)) {
                    return player;
                }
            }
        }
        return null;
    }
}
