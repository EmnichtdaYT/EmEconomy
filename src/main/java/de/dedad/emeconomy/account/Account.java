package de.dedad.emeconomy.account;

import java.math.BigInteger;

public abstract class Account {
    private UUID holderUuid;
    
    public abstract BigInteger getAmount();
    public abstract void setAmount(BigInteger value);
    public abstract void addAmount(BigInteger value);
    public abstract void subtractAmount(BigInteger value);
    
    public void getHolder(){
        return hoderUuid;
    }
}
