package org.acsn1.banksystem;

import org.acsn1.banksystem.manager.*;
import org.acsn1.banksystem.utils.ChatUtils;
import org.acsn1.banksystem.utils.ConsoleUtils;
import org.acsn1.banksystem.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class BankSystem extends JavaPlugin {

    private ConfigManager configManager;
    private DatabaseManager databaseManager;
    private BankManager bankManager;
    private CommandManager commandManager;
    private EventManager eventManager;



    @Override
    public void onEnable() {

        //initialize all managers
        bankManager = new BankManager(this);
        configManager = new ConfigManager(this);
        databaseManager = new DatabaseManager(this);
        commandManager = new CommandManager(this);
        eventManager = new EventManager(this);

        //check if it failed to connect to the database,
        //if so the plugin is getting disabled.
        if(this.getDatabaseManager().getConnection() == null){
            disable();
        }

    }

    @Override
    public void onDisable() {

        //save all the banks if the database is on and the connection is established.
        if(this.getConfigManager().isDatabase() && this.getDatabaseManager().getConnection() != null) {
            this.getDatabaseManager().saveBanks();
        }
    }

    public BankManager getBankManager() {
        return this.bankManager;
    }

    public ConfigManager getConfigManager(){
        return this.configManager;
    }

    public DatabaseManager getDatabaseManager() {
        return this.databaseManager;
    }

    //Plugin Disable.
    public void disable(){
        Bukkit.getPluginManager().disablePlugin(this);
    }

}
