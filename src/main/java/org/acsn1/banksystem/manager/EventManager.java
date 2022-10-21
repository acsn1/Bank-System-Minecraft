package org.acsn1.banksystem.manager;

import org.acsn1.banksystem.BankSystem;
import org.acsn1.banksystem.event.PlayerListener;
import org.bukkit.Bukkit;

public class EventManager {

    private BankSystem bankSystem;
    public EventManager(BankSystem bankSystem){
        this.bankSystem = bankSystem;
        if(Bukkit.getPluginManager().isPluginEnabled(bankSystem)) {
            this.registerEvents();
        }

    }

    private void registerEvents(){
        Bukkit.getPluginManager().registerEvents(new PlayerListener(bankSystem), bankSystem);
    }

}
