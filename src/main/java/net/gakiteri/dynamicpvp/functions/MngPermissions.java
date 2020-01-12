package net.gakiteri.dynamicpvp.functions;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class MngPermissions {

    public static boolean hasPermission(CommandSender sender, String permission, Boolean alert) {
        if (!sender.hasPermission(permission)) {
            if(alert) {
                sender.sendMessage(ChatColor.RED + "No tienes los permisos requeridos para ejecutar este comando");
            }
            return false;
        }
        return true;
    }
}
