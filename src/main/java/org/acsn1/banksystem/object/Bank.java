package org.acsn1.banksystem.object;

import java.util.UUID;

public class Bank {

    private final UUID uuid;
    private double balance;

    public Bank(UUID uuid, double balance){
        this.uuid = uuid;
        this.balance = balance;

        //a player should never have negative balance.
        //So whenever the constructor gets called,
        //if the input of balance is negative, it gets replaced with zero.
        if(balance < 0) {
            this.balance = 0;
        }

    }

    public double getBalance(){
        return this.balance;
    }

    public UUID getHolder(){
        return this.uuid;
    }

    public void setBalance(double amount){
        this.balance = amount;
    }

    public void addToBalance(double amount){
        this.balance+=amount;
    }

    public void removeFromBalance(double amount){
        if(this.balance - amount < 0) {
            this.balance = 0;
        } else {
            this.balance-=amount;
        }
    }

}
