package net.gakiteri.dynamicpvp;

import net.gakiteri.dynamicpvp.commands.CmdPvp;
import net.gakiteri.dynamicpvp.data.DataPlayer;
import net.gakiteri.dynamicpvp.events.OnPvp;
import net.gakiteri.dynamicpvp.functions.MngFile;
import net.gakiteri.dynamicpvp.events.OnJoin;
import org.bukkit.Server;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Dynamicpvp extends JavaPlugin {

    @Override
    public void onEnable() {

        Server server = getServer();
        PluginManager pluginManager = server.getPluginManager();
        Variables.pluginName = this.getName();

        /** COMMAND REGISTRATION **/
        this.getCommand("pvp").setExecutor(new CmdPvp());

        /** EVENT REGISTRATION **/
        pluginManager.registerEvents(new OnJoin(), this);
        pluginManager.registerEvents(new OnPvp(), this);

        /** GET PLUGIN DIRECTORY **/
        Variables.dirPlugin = MngFile.Path(getDataFolder());

        /** GET CONFIG **/
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        Variables.config = getConfig();

        /** ASSIGNS CONFIG **/
        try {
            Variables.config.getList("players.on").forEach(player -> {
                Variables.playerData.put((Mng) player, new DataPlayer(true));
            });
        } catch (Exception e) {
            getLogger().info("No players have PVP ON");
        }
        try {
            Variables.config.getList("players.off").forEach(player -> {
                Variables.playerData.put((String) player, new DataPlayer(false));
            });
        } catch (Exception e) {
            getLogger().info("No players have PVP OFF");
        }


        // Plugin startup message
        getLogger().info("DynamicPVP plugin initialised");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
