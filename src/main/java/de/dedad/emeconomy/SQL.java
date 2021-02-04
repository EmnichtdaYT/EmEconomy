package de.dedad.emeconomy;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

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

        config.setJdbcUrl("jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useSSL=" + useSSL + "&authReconnect=true");
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

    // QUERIES

    public boolean economyInDatabase(UUID uuid) {
        try {
            PreparedStatement st;
            ResultSet rs;
            st = getConnection().prepareStatement("SELECT 'uuid' FROM 'economy' WHERE 'uuid' = " + uuid.toString());
            rs = st.executeQuery();
            return rs.next() && rs.getString(1) != null;
        } catch (SQLException x) {
            x.printStackTrace();
        }
        return false;
    }

    public void initEconomy(UUID uuid) {
        try {
            getConnection().prepareCall("initEconomy '" + uuid + "', '" + serverName + "'").execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public BigInteger getBankAmount(UUID uuid) {
        try {
            PreparedStatement st;
            ResultSet rs;
            st = getConnection().prepareStatement("SELECT bank WHERE uuid = '" + uuid + "', server = '" + serverName + "'");
            rs = st.executeQuery();
            while (rs.next()) {

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return BigInteger.valueOf(0);
    }

    public BigInteger getCashAmount(UUID uuid) {
        try {
            PreparedStatement st;
            ResultSet rs;
            st = getConnection().prepareStatement("SELECT pocket WHERE uuid = '" + uuid + "', server = '" + serverName + "'");
            rs = st.executeQuery();
            while (rs.next()) {

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return BigInteger.valueOf(0);
    }

    public void setCashAmount(UUID uuid, BigInteger amount) {

    }

    public void setBankAmount(UUID uuid, BigInteger amount) {

    }

    public void addAmountToCash(UUID uuid, BigInteger amount) {

    }

    public void addAmountToBank(UUID uuid, BigInteger amount) {

    }

    public void subtractAmountFromCash(UUID uuid, BigInteger amount) {

    }

    public void subtractAmountFromBank(UUID uuid, BigInteger amount) {

    }
}
