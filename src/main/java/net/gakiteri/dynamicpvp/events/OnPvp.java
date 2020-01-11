package net.gakiteri.dynamicpvp.events;

import net.gakiteri.dynamicpvp.Variables;
import net.gakiteri.dynamicpvp.functions.MngPlayers;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.UUID;

import static org.bukkit.Bukkit.getServer;

public class OnPvp implements Listener {

    @EventHandler
    public void onPvp(EntityDamageByEntityEvent event) {

        // Check if entity is a player
        if (event.getEntity() instanceof Player) {
            // Get entity data
            Entity damager = event.getDamager();
            UUID receiveUUID = ((Player) event.getEntity()).getPlayer().getUniqueId();
            UUID dealUUID = null;
            Boolean fireStrike = false;

            // Check what if dealer is Player
            if (damager instanceof Player) {
                dealUUID = ((Player) damager).getPlayer().getUniqueId();
            } else if (damager instanceof Projectile && ((Projectile) damager).getShooter() instanceof Player) {
                dealUUID = ((Player) ((Projectile) damager).getShooter()).getPlayer().getUniqueId();
            } else if (damager instanceof ThrownPotion && ((ThrownPotion) damager).getShooter() instanceof Player) {
                dealUUID = ((Player) ((ThrownPotion) damager).getShooter()).getPlayer().getUniqueId();
            } else if (damager instanceof AreaEffectCloud && ((AreaEffectCloud) damager).getSource() instanceof Player) {
                dealUUID = ((Player) ((AreaEffectCloud) damager).getSource()).getPlayer().getUniqueId();
            } else if (damager instanceof Firework) {
                fireStrike = true;
            } else if (damager instanceof LightningStrike) {
                fireStrike = true;
            }

            // Check if damaged is has to be cancelled
            if (!receiveUUID.equals(dealUUID)) {
                if (!fireStrike && (!Variables.playerData.get(receiveUUID).getPvp())) {
                    event.setCancelled(true);
                } else if (!Variables.playerData.get(receiveUUID).getPvp() || !Variables.playerData.get(dealUUID).getPvp()) {
                    event.setCancelled(true);
                }
                if (!Variables.playerData.get(dealUUID).getPvp()) {
                    getServer().getPlayer(dealUUID).spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.RED + "No tienes el PVP activado"));
                } else if (!Variables.playerData.get(receiveUUID).getPvp()) {
                    getServer().getPlayer(dealUUID).spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.RED + ((Player) event.getEntity()).getPlayer().getName() + " tiene el PVP desactivado"));
                }
            }

        }
    }
}
