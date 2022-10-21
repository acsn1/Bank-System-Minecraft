package org.acsn1.banksystem.manager;

import org.acsn1.banksystem.BankSystem;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConfigManager {

    private BankSystem bankSystem;
    private final File file;
    private final YamlConfiguration config;

    public ConfigManager(BankSystem bankSystem){
        this.bankSystem = bankSystem;
        this.file = new File(bankSystem.getDataFolder() + "/config.yml");
        if(!(file.exists())) {
            try{
                file.createNewFile();

            } catch (Exception ex){}
            bankSystem.saveResource("config.yml", true);
        }

        config = YamlConfiguration.loadConfiguration(file);
    }

    public boolean isDatabase(){
        return config.getBoolean("database.enabled");
    }
    public String getURI(){
        return config.getString("database.uri");
    }
    public String getUsername(){
        return config.getString("database.username");
    }
    public String getPassword(){
        return config.getString("database.password");
    }


}
