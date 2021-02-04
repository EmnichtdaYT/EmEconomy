package de.dedad.emeconomy.listener;

import de.dedad.emeconomy.EmEconomyMain;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        EmEconomyMain.getPlugin().instanceMap.remove(e.getPlayer().getUniqueId());
    }
}
