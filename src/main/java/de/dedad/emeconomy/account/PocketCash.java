package de.dedad.emeconomy.account;

import de.dedad.emeconomy.EmEconomyMain;

import java.math.BigInteger;
import java.util.UUID;

public class PocketCash extends Account {

    public PocketCash(UUID holderUUID, EmEconomyMain plugin) {
        super(holderUUID, plugin);
    }

    @Override
    public BigInteger getAmount() {
        return super.getPlugin().database.getCashAmount(super.getHolder());
    }

    @Override
    public void setAmount(BigInteger value) {
        super.getPlugin().database.setCashAmount(super.getHolder(), value);
    }

    @Override
    public void addAmount(BigInteger value) {
        super.getPlugin().database.addAmountToCash(super.getHolder(), value);
    }

    @Override
    public void subtractAmount(BigInteger value) {
        super.getPlugin().database.subtractAmountFromCash(super.getHolder(), value);
    }
}
