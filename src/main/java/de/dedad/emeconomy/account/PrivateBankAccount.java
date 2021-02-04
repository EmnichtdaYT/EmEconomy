package de.dedad.emeconomy.account;

import de.dedad.emeconomy.EmEconomyMain;

import java.math.BigInteger;
import java.util.UUID;

public class PrivateBankAccount extends Account {

    public PrivateBankAccount(UUID holderUUID, EmEconomyMain plugin) {
        super(holderUUID, plugin);
    }

    @Override
    public BigInteger getAmount() {
        return super.getPlugin().database.getBankAmount(super.getHolder());
    }

    @Override
    public void setAmount(BigInteger value) {
        super.getPlugin().database.setBankAmount(super.getHolder(), value);
    }

    @Override
    public void addAmount(BigInteger value) {
        super.getPlugin().database.addAmountToBank(super.getHolder(), value);
    }

    @Override
    public void subtractAmount(BigInteger value) {
        super.getPlugin().database.subtractAmountFromBank(super.getHolder(), value);
    }
}
