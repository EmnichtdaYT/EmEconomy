package de.dedad.emeconomy.account;

import java.math.BigInteger;

public abstract class Account {
    private UUID holderUuid;
    private EmEconomyMain pl;
    
    public Account(UUID holderUuid, EmEconomyMain pl){
        this.hoderUuid = holderUuid;
        this.pl = pl;
    }
    
    public abstract BigInteger getAmount();
    public abstract void setAmount(BigInteger value);
    public abstract void addAmount(BigInteger value);
    public abstract void subtractAmount(BigInteger value);
    
    public void getHolder(){
        return hoderUuid;
    }
    
    public void getPl(){
        return pl;
    }
}
