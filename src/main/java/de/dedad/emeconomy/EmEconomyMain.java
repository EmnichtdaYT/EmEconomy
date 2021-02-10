package de.dedad.emeconomy;

import de.dedad.emeconomy.entity.EconomyPlayer;
import de.dedad.emeconomy.listener.JoinListener;
import de.dedad.emeconomy.listener.QuitListener;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public final class EmEconomyMain extends JavaPlugin {

    @Getter
    private static EmEconomyMain plugin;
    public SQL database;
    public HashMap<UUID, EconomyPlayer> instanceMap;
    public String serverRunning = "";
    public boolean connectionExists = false;

    @Override
    public void onEnable() {
        plugin = this;
        this.getLogger().fine("by dedad wird gestartet. Moin!");

        initConfiguration();

        this.getServer().getPluginManager().registerEvents(new JoinListener(), this);
        this.getServer().getPluginManager().registerEvents(new QuitListener(), this);

        if (getConfig().getString("SQL.Database").equals("editme")) {
            this.getLogger().warning("Die SQL-Zugangsdaten wurden noch nicht editiert oder der Server startet zum ersten Mal. Bitte gib deine SQL-Zugangsdaten in der Konfigurationsdatei an.");
            getServer().getPluginManager().disablePlugin(this);
        }

        this.serverRunning = getConfig().getString("EmEconomy.Servername");
        this.database = new SQL(getConfig().getString("SQL.Hostname"), getConfig().getString("SQL.Port"), getConfig().getString("SQL.Database"), getConfig().getString("SQL.Username"), getConfig().getString("SQL.Password"), getConfig().getBoolean("SQL.UseSSL"), getConfig().getString("EmEconomy.Servername"));
    }

    @Override
    public void onDisable() {
        this.getLogger().fine("by dedad wird deaktiviert. Bis denne! :)");
        /*
        if (connectionExists) {
            try {
                this.database.getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

         */
    }

    private void initConfiguration() {
        this.reloadConfig();

        this.getConfig().options().header("EmEconomy Config");
        this.getConfig().options().copyHeader(true);

        this.getConfig().addDefault("SQL.Hostname", "127.0.0.1");
        this.getConfig().addDefault("SQL.Port", "3306");
        this.getConfig().addDefault("SQL.Database", "editme");
        this.getConfig().addDefault("SQL.Username", "editme");
        this.getConfig().addDefault("SQL.Password", "editme");
        this.getConfig().addDefault("SQL.UseSSL", false);
        this.getConfig().addDefault("EmEconomy.Servername", "test");
        this.getConfig().addDefault("EmEconomy.KillerGetsCash", false);

        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
        this.saveDefaultConfig();
    }
}