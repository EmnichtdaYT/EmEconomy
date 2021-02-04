package de.dedad.emeconomy.listener;

import de.dedad.emeconomy.EmEconomyMain;
import de.dedad.emeconomy.entity.EconomyPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        if (!EmEconomyMain.getPlugin().instanceMap.containsKey(e.getPlayer().getUniqueId())) {
            EmEconomyMain.getPlugin().instanceMap.put(e.getPlayer().getUniqueId(), new EconomyPlayer(e.getPlayer().getUniqueId()));
        }
        if (!EmEconomyMain.getPlugin().database.economyInDatabase(e.getPlayer().getUniqueId())) {
            EmEconomyMain.getPlugin().database.initEconomy(e.getPlayer().getUniqueId());
        }
    }
}