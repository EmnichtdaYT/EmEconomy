package de.dedad.emeconomy;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.math.BigInteger;
import java.sql.*;
import java.util.UUID;

import static de.dedad.emeconomy.EmEconomyMain.getPlugin;

public class SQL {

    private final HikariDataSource ds;

    private final String serverName;

    public SQL(String hostname, String port, String database, String username, String password, boolean useSSL, String serverName) {
        this.serverName = serverName;

        HikariConfig config = new HikariConfig();

        config.setJdbcUrl("jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useSSL=" + useSSL + "&authReconnect=true");
        config.setUsername(username);
        config.setPassword(password);
        config.addDataSourceProperty("cachePrepStmts", true);
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        ds = new HikariDataSource(config);
        getPlugin().connectionExists = true;
    }

    public Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            getPlugin().getLogger().severe("Die Verbindung zur Datenbank ist fehlgeschlagen.");
            return null;
        }
    }

    // QUERIES

    public boolean economyInDatabase(UUID uuid) {
        try {
            PreparedStatement st;
            ResultSet rs;
            st = getConnection().prepareStatement("SELECT uuid FROM economy WHERE uuid = '" + uuid.toString() + "'");
            rs = st.executeQuery();
            return rs.next() && rs.getString(1) != null;
        } catch (SQLException x) {
            x.printStackTrace();
        }
        return false;
    }

    public void initEconomy(UUID uuid) {
        try {
            CallableStatement st = getConnection().prepareCall("{call initEconomy(?, ?)}");
            st.setString(1, uuid.toString());
            st.setString(2, serverName);
            st.execute();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public BigInteger getBankAmount(UUID uuid) {
        try {
            PreparedStatement st;
            ResultSet rs;
            st = getConnection().prepareStatement("SELECT bank FROM economy WHERE uuid = ?, server = ?");
            st.setString(1, uuid.toString());
            st.setString(2, serverName);
            rs = st.executeQuery();
            while (rs.next()) {
                return BigInteger.valueOf(rs.getBigDecimal("bank").longValue());
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
            st = getConnection().prepareStatement("SELECT pocket FROM economy WHERE uuid = ?, server = ?");
            st.setString(1, uuid.toString());
            st.setString(2, serverName);
            rs = st.executeQuery();
            while (rs.next()) {
                return BigInteger.valueOf(rs.getBigDecimal("pocket").longValue());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return BigInteger.valueOf(0);
    }

    public void setCashAmount(UUID uuid, BigInteger amount) {
        try {
            CallableStatement st = getConnection().prepareCall("{call economySetMoneyPocket(?, ?, ?)}");
            st.setString(1, uuid.toString());
            st.setObject(2, amount);
            st.setString(3, serverName);
            st.execute();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setBankAmount(UUID uuid, BigInteger amount) {
        try {
            CallableStatement st = getConnection().prepareCall("{call economySetMoneyBank(?, ?, ?)}");
            st.setString(1, uuid.toString());
            st.setObject(2, amount);
            st.setString(3, serverName);
            st.execute();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addAmountToCash(UUID uuid, BigInteger amount) {
        try {
            CallableStatement st = getConnection().prepareCall("{call economyAddMoneyPocket(?, ?, ?)}");
            st.setString(1, uuid.toString());
            st.setObject(2, amount);
            st.setString(3, serverName);
            st.execute();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addAmountToBank(UUID uuid, BigInteger amount) {
        try {
            CallableStatement st = getConnection().prepareCall("{call economyAddMoneyToBank(?, ?, ?)}");
            st.setString(1, uuid.toString());
            st.setObject(2, amount);
            st.setString(3, serverName);
            st.execute();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void subtractAmountFromCash(UUID uuid, BigInteger amount) {
        try {
            CallableStatement st = getConnection().prepareCall("{call economyRemoveMoneyPocket(?, ?, ?)}");
            st.setString(1, uuid.toString());
            st.setObject(2, amount);
            st.setString(3, serverName);
            st.execute();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void subtractAmountFromBank(UUID uuid, BigInteger amount) {
        try {
            CallableStatement st = getConnection().prepareCall("{call economyRemoveFromBank(?, ?, ?)}");
            st.setString(1, uuid.toString());
            st.setObject(2, amount);
            st.setString(3, serverName);
            st.execute();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
