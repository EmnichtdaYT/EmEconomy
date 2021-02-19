package de.dedad.emeconomy.account;

import de.dedad.emeconomy.EmEconomyMain;

import java.math.BigInteger;
import java.util.UUID;

public abstract class Account {
    private final UUID holderUUID;
    private final EmEconomyMain plugin;

    public Account(UUID holderUUID, EmEconomyMain plugin) {
        this.holderUUID = holderUUID;
        this.plugin = plugin;
    }

    public abstract BigInteger getAmount();

    public abstract void setAmount(BigInteger value);

    public abstract void addAmount(BigInteger value);

    public abstract void subtractAmount(BigInteger value);
    
    public UUID getHolder() {
        return holderUUID;
    }
    
    public EmEconomyMain getPlugin() {
        return plugin;
    }
}
