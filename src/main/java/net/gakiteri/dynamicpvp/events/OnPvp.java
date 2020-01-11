package net.gakiteri.dynamicpvp.events;

import net.gakiteri.dynamicpvp.Variables;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class OnPvp implements Listener {

    @EventHandler
    public void onPvp(EntityDamageByEntityEvent event) {

        // Check if entity is a player
        if (event.getEntity() instanceof Player) {
            // Get entity data
            Entity damager = event.getDamager();
            String receiveUUID = ((Player) event.getEntity()).getPlayer().getUniqueId().toString();
            String dealUUID = "";

            // Check what if dealer is Player
            if (damager instanceof Player) {
                dealUUID = ((Player) damager).getPlayer().getUniqueId().toString();
            } else if (damager instanceof Projectile && ((Projectile) damager).getShooter() instanceof Player) {
                dealUUID = ((Player) ((Projectile) damager).getShooter()).getPlayer().getUniqueId().toString();
            } else if (damager instanceof ThrownPotion && ((ThrownPotion) damager).getShooter() instanceof Player) {
                dealUUID = ((Player) ((ThrownPotion) damager).getShooter()).getPlayer().getUniqueId().toString();
            } else if (damager instanceof AreaEffectCloud && ((AreaEffectCloud) damager).getSource() instanceof Player) {
                dealUUID = ((Player) ((AreaEffectCloud) damager).getSource()).getPlayer().getUniqueId().toString();
            } else if (damager instanceof Firework) {
                dealUUID = "firework";
            } else if (damager instanceof LightningStrike) {
                dealUUID = "lighting";
            }

            // Check if damaged is has to be cancelled
            if (!receiveUUID.equals(dealUUID)) {
                if ((receiveUUID.equals("firework") || receiveUUID.equals("lighting")) && (!Variables.playerData.get(receiveUUID).getPvp())) {
                    event.setCancelled(true);
                } else if (!Variables.playerData.get(receiveUUID).getPvp() || !Variables.playerData.get(dealUUID).getPvp()) {
                    event.setCancelled(true);
                }
            }

        }
    }
}
