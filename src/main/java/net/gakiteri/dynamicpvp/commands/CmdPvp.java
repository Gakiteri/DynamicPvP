package net.gakiteri.dynamicpvp.commands;

import net.gakiteri.dynamicpvp.Variables;
import net.gakiteri.dynamicpvp.functions.MngConf;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdPvp implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
/*
        if (!sender.hasPermission("dynamic.player")) {
            sender.sendMessage(ChatColor.RED + "No tienes los permisos requeridos para ejecutar este comando");
            return true;
        }
*/
        String uuid = ((Player) sender).getUniqueId().toString();
        Boolean pvp = Variables.playerData.get(uuid).getPvp();

        if (args.length == 0 && perm set) {
            Variables.playerData.get(uuid).setPvp(!pvp);
            new MngConf().save();
            if (pvp) {
                sender.sendMessage(ChatColor.GREEN + "PVP desactivado");
            } else {
                sender.sendMessage(ChatColor.GREEN + "PVP activado");
            }
            return true;
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("on")) {
                if (pvp) {
                    sender.sendMessage(ChatColor.RED + "Ya tienes PVP activado");
                    return true;
                } else {
                    Variables.playerData.get(uuid).setPvp(true);
                    sender.sendMessage(ChatColor.GREEN + "PVP activado");
                    new MngConf().save();
                    return true;
                }
            } else if (args[0].equalsIgnoreCase("off")) {
                if (pvp) {
                    Variables.playerData.get(uuid).setPvp(false);
                    sender.sendMessage(ChatColor.GREEN + "PVP desactivado");
                    new MngConf().save();
                    return true;
                } else {
                    sender.sendMessage(ChatColor.RED + "Ya tienes PVP desactivado");
                    return true;
                }
            } else if (args[0].equalsIgnoreCase("status")) {
                if (Variables.playerData.get(((Player) sender).getUniqueId().toString()).getPvp()) {
                    sender.sendMessage(ChatColor.BLUE + "Tienes el PVP activado");
                } else {
                    sender.sendMessage(ChatColor.BLUE + "Tienes el PVP desactivado");
                }
            }
        } else if (args.length == 2) {
            // status de jugador X
            // set de jugador X
        }


        sender.sendMessage(ChatColor.RED + "Error al ejecutar el comando, comprueba que hayas escrito los valores correctamente");

        return false;
    }
}
