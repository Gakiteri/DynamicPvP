package net.gakiteri.dynamicpvp.commands;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import net.gakiteri.dynamicpvp.Variables;
import net.gakiteri.dynamicpvp.functions.MngConf;
import net.gakiteri.dynamicpvp.functions.MngRegions;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdPvpRegion implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        String regionName = "";
        if (args.length > 1) {
            regionName = args[1];
        }

        switch (args[0]) {
            case "list":
                sender.sendMessage(ChatColor.BLUE + " " + Variables.regionsEnabled.size());
                if(Variables.regionsEnabled.size() == 0) {
                    sender.sendMessage(ChatColor.BLUE + "No hay regiones activas");
                } else {
                    sender.sendMessage(ChatColor.BLUE + "Listado Regiones:");
//                    for (String region: Variables.regionsEnabled) {
//                        sender.sendMessage(ChatColor.BLUE + region.toString());
//                    }
                }
                break;
            case "add":
                if (Variables.regionsEnabled.indexOf(regionName) == -1) {
                    Player player = (Player) sender;
                    ProtectedRegion region = MngRegions.getRegionsFromSearch(regionName, player.getWorld());
                    if (region != null) {
                        Variables.regionsEnabled.add(regionName);
                        new MngConf().saveRegions();
                        sender.sendMessage(ChatColor.GREEN + "Region añadida para activar PVP");
                    } else {
                        sender.sendMessage(ChatColor.RED + "No hay una region con ese nombre");
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "Ya hay una region añadida con ese nombre");
                }

                break;
            case "remove":
                int idx = Variables.regionsEnabled.indexOf(regionName);
                if (idx != -1) {
                    Variables.regionsEnabled.remove(idx);
                    new MngConf().saveRegions();
                    sender.sendMessage(ChatColor.GREEN + "Region Borrada");
                } else {
                    sender.sendMessage(ChatColor.RED + "No hay una region con ese nombre");
                }

                break;
        }
        return true;
    }
}
