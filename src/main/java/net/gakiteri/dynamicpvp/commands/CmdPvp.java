package net.gakiteri.dynamicpvp.commands;

import net.gakiteri.dynamicpvp.Variables;
import net.gakiteri.dynamicpvp.functions.MngConf;
import net.gakiteri.dynamicpvp.functions.MngPlayers;
import org.bukkit.Server;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CmdPvp implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        UUID uuid = ((Player) sender).getUniqueId();
        Boolean pvp = Variables.playerData.get(uuid).getPvp();

        if (args.length == 0) { // toggle current player /pvp
            if(!this.hasPermission(sender, "dynamic.pvp.set")) {
                return true;
            }
            this.setPvp(uuid, !pvp);
            new MngConf().save();
            if (pvp) {
                sender.sendMessage(ChatColor.GREEN + "PVP desactivado");
            } else {
                sender.sendMessage(ChatColor.GREEN + "PVP activado");
            }
            return true;
        } else if (args[0].equalsIgnoreCase("status")) { //status function /pvp status || /pvp status FoxkDev
            Player player = (Player) sender;
            if(args.length == 2) {
                if(!this.hasPermission(sender, "dynamic.pvp.status.players")) {
                    return true;
                }
                player = MngPlayers.getPlayer(args[1]); //change args[1]
                if(player == null) {
                    sender.sendMessage(ChatColor.RED + "Usuario no existe");
                    return true;
                }
            }
            if(Variables.playerData.get(player.getUniqueId()).getPvp()) {
                sender.sendMessage(ChatColor.BLUE + "PvP activado del jugador " + player.getName());
            }else {
                sender.sendMessage(ChatColor.BLUE + "PvP desactivado del jugador " + player.getName());
            }
            return true;

        } else if (args[0].equalsIgnoreCase("on") || args[1].equalsIgnoreCase("off")) {
            Player player = (Player) sender;
            if(args.length == 2) {
                if(!this.hasPermission(sender, "dynamic.pvp.set.players")) {
                    return true;
                }
                player = MngPlayers.getPlayer(args[1]);
                uuid = player.getUniqueId();
            }
            if (args[0].equalsIgnoreCase("on")) {
                if (!pvp){
                    this.setPvp(uuid, true);
                    new MngConf().save();
                }
                sender.sendMessage(ChatColor.GREEN + "PVP activado del jugador " + player.getName());
                return true;
            } else if (args[0].equalsIgnoreCase("off")) {
                if (pvp) {
                    this.setPvp(uuid, false);
                    new MngConf().save();
                }
                sender.sendMessage(ChatColor.GREEN + "PVP desactivado del jugador " + player.getName());
                return true;
            }
        }
        sender.sendMessage(ChatColor.RED + "Error al ejecutar el comando, comprueba que hayas escrito los valores correctamente");
        return false;
    }

    public boolean hasPermission(CommandSender sender, String permission) {

        if (!sender.hasPermission(permission)) {
            sender.sendMessage(ChatColor.RED + "No tienes los permisos requeridos para ejecutar este comando");
            return true;
        }
        return true;
    }

    public boolean setPvp(UUID uuid, Boolean pvp) {
        Variables.playerData.get(uuid).setPvp(pvp);
        return pvp;
    }

}
