package net.gakiteri.dynamicpvp.commands;
import com.sk89q.worldguard.protection.ApplicableRegionSet;

import net.gakiteri.dynamicpvp.Variables;
import net.gakiteri.dynamicpvp.functions.MngConf;
import net.gakiteri.dynamicpvp.functions.MngPlayers;
import net.gakiteri.dynamicpvp.functions.MngRegions;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

public class CmdPvp implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;
        Player currentPlayer = (Player) sender;

        UUID uuid = currentPlayer.getUniqueId();

        AtomicReference<Boolean> canSetPvp = new AtomicReference<>(true);

        if(!this.hasPermission(sender, "dynamic.pvp.set.inworld", false)) {
            canSetPvp.set(false);
            //get regions activable pvp
            ApplicableRegionSet regions = MngRegions.getRegionsFromPlayer((Player) sender);
            if(regions != null) {
                regions.getRegions().forEach(region -> {
                    Variables.regionsEnabled.forEach(regionEnabled -> {
                        if((region.getOwners().contains(((Player) sender).getUniqueId()) )|| (region.getId().equalsIgnoreCase(regionEnabled))) {
                            canSetPvp.set(true);
                        }
                    });
                });
            }

        }

        Boolean pvp = Variables.playerData.get(uuid).getPvp();
        if (canSetPvp.get() && args.length == 0) { // toggle current player /pvp
            if(!this.hasPermission(sender, "dynamic.pvp.set", true)) {
                return true;
            }
            this.setPvp(uuid, !pvp);
            new MngConf().savePlayers();
            if (pvp) {
                sender.sendMessage(ChatColor.GREEN + "PVP desactivado");
            } else {
                sender.sendMessage(ChatColor.GREEN + "PVP activado");
            }
            return true;
        } else if (args[0].equalsIgnoreCase("status")) { //status function /pvp status || /pvp status FoxkDev
            sender.sendMessage(ChatColor.BLUE + "ENTRA STATUS");
            if (args.length == 2) {
                if (!this.hasPermission(sender, "dynamic.pvp.status.players", true)) {
                    return true;
                }
                player = MngPlayers.getPlayer(args[1]); //change args[1]
                if (player == null) {
                    sender.sendMessage(ChatColor.RED + "Usuario no existe");
                    return true;
                }
            }
            if (Variables.playerData.get(player.getUniqueId()).getPvp()) {
                sender.sendMessage(ChatColor.BLUE + "PvP activado del jugador " + player.getName());
            }else {
                sender.sendMessage(ChatColor.BLUE + "PvP desactivado del jugador " + player.getName());
            }
            return true;

        } else if (args[0].equalsIgnoreCase("on") || args[0].equalsIgnoreCase("off")) {
            sender.sendMessage(ChatColor.BLUE + "ENTRA ON/OFF");
            if(canSetPvp.get()) {
                if (args.length == 2) {
                    if (!this.hasPermission(sender, "dynamic.pvp.set.players", true)) {
                        return true;
                    }
                    player = MngPlayers.getPlayer(args[1]);
                    uuid = player.getUniqueId();
                    pvp = Variables.playerData.get(uuid).getPvp();
                }
                if (args[0].equalsIgnoreCase("on")) {
                    if (!pvp){
                        this.setPvp(uuid, true);
                        new MngConf().savePlayers();
                    }
                    sender.sendMessage(ChatColor.GREEN + "PVP activado del jugador " + player.getName());
                    if(uuid != currentPlayer.getUniqueId()){
                        player.sendMessage(ChatColor.GREEN + "PVP activado por el jugador " + currentPlayer.getName());
                    }
                } else if (args[0].equalsIgnoreCase("off")) {
                    if (pvp) {
                        this.setPvp(uuid, false);
                        new MngConf().savePlayers();
                    }
                    sender.sendMessage(ChatColor.GREEN + "PVP desactivado del jugador " + player.getName());
                    if(uuid != currentPlayer.getUniqueId()){
                        player.sendMessage(ChatColor.GREEN + "PVP desactivado por el jugador " + currentPlayer.getName());
                    }
                }
                return true;
            }

            return true;
        }
        if(!args[0].equalsIgnoreCase("help")) {
            sender.sendMessage(ChatColor.RED + "Error al ejecutar el comando, comprueba que hayas escrito los valores correctamente");
        }
        if(!canSetPvp.get()) {
            sender.sendMessage(ChatColor.RED + "No puedes activar el pvp fuera de un poblado!");
        }
        return false;
    }

    public boolean hasPermission(CommandSender sender, String permission, Boolean alert) {
        if (!sender.hasPermission(permission)) {
            if(alert) {
                sender.sendMessage(ChatColor.RED + "No tienes los permisos requeridos para ejecutar este comando");
            }
            return false;
        }
        return true;
    }

    public boolean setPvp(UUID uuid, Boolean pvp) {
        Variables.playerData.get(uuid).setPvp(pvp);
        return pvp;
    }

}
