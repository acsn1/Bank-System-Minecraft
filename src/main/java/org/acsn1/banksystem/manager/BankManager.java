package org.acsn1.banksystem.manager;

import org.acsn1.banksystem.BankSystem;
import org.acsn1.banksystem.object.Bank;

import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class BankManager {

    private BankSystem bankSystem;
    private Set<Bank> BANKS;
    public BankManager(BankSystem bankSystem){
        this.bankSystem = bankSystem;
        this.BANKS = new HashSet<>();
    }

    public void loadBankProfiles(UUID uuid, double balance){
        Bank bank = new Bank(uuid, balance);
        BANKS.add(bank);
    }

    public void createBankProfile(UUID uuid){
        if(hasBank(uuid)) return;

        Bank bank = new Bank(uuid, 0);
        BANKS.add(bank);
    }


    public Bank getBank(UUID uuid){
        Bank bank = null;
        for(Bank banks:this.BANKS){
         if(banks.getHolder().equals(uuid)){
             bank = banks;
         }
        }
        return bank;
    }

    public boolean hasBank(UUID uuid) {

        for (Bank banks : this.BANKS) {
            if(banks.getHolder().equals(uuid)){
                return true;
            }
        }
        return false;
    }


    public void earn(Bank bank) {

        double random = ThreadLocalRandom.current().nextDouble(1, 5);
        DecimalFormat format = new DecimalFormat("#.#");
        //two decimals of double = format.format(random)
        random = Double.parseDouble(format.format(random));

        bank.addToBalance(random);

    }

    public Set<Bank> getBanks(){
        return BANKS;
    }

}
