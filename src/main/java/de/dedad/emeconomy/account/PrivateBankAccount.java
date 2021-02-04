package de.dedad.emeconomy.account;

import java.math.BigInteger;

public class PrivateBankAccount extends Account {

    @Override
    public BigInteger getAmount() {
        return null;
        //return super.getPl().database.getPrivateBankAmount(super.getHolder());
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
