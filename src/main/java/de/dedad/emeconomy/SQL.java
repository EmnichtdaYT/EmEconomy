package de.dedad.emeconomy;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

import static de.dedad.emeconomy.EmEconomyMain.PLUGIN_NAME;
import static de.dedad.emeconomy.EmEconomyMain.getPlugin;

public class SQL {

    private final HikariDataSource ds;

    private final String database;
    private final String serverName;

    public SQL(String hostname, String port, String database, String username, String password, boolean useSSL, String serverName) {
        this.database = database;
        this.serverName = serverName;

        HikariConfig config = new HikariConfig();

        config.setJdbcUrl("jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useSSL=" + useSSL);
        config.setUsername(username);
        config.setPassword(password);
        config.addDataSourceProperty("cachePrepStmts", true);
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        ds = new HikariDataSource(config);
    }

    public Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            getPlugin().getLogger().severe(PLUGIN_NAME + "Die Verbindung zur Datenbank ist fehlgeschlagen.");
            return null;
        }
    }
}
