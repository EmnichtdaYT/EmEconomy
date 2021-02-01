package de.dedad.emeconomy.account;

import java.math.BigInteger;

public class PrivateBankAccount extends Account {

    private BigInteger amount;

    @Override
    public BigInteger getAmount() {
        return null;
    }

    @Override
    public void setAmount(BigInteger value) {
        this.amount = value;
    }

    @Override
    public void addAmount(BigInteger value) {
        this.amount = value.add(this.amount);
    }

    @Override
    public void subtractAmount(BigInteger value) {
        this.amount = amount.subtract(value);
    }
}
