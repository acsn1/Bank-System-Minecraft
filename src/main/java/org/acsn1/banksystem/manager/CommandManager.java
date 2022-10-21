package org.acsn1.banksystem.manager;

import org.acsn1.banksystem.BankSystem;
import org.acsn1.banksystem.command.BalCommand;
import org.acsn1.banksystem.command.EarnCommand;
import org.acsn1.banksystem.command.GiveCommand;
import org.acsn1.banksystem.command.SetbalCommand;
import org.bukkit.Bukkit;

public class CommandManager {

    private BankSystem bankSystem;
    public CommandManager(BankSystem bankSystem){
        this.bankSystem = bankSystem;
        if(Bukkit.getPluginManager().isPluginEnabled(bankSystem)) {
            this.registerCommands();
        }
    }

    private void registerCommands() {
        bankSystem.getCommand("bal").setExecutor(new BalCommand(bankSystem));
        bankSystem.getCommand("earn").setExecutor(new EarnCommand(bankSystem));
        bankSystem.getCommand("give").setExecutor(new GiveCommand(bankSystem));
        bankSystem.getCommand("setbal").setExecutor(new SetbalCommand(bankSystem));
    }


}
