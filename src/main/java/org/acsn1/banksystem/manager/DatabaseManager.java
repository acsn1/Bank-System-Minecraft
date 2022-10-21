package org.acsn1.banksystem.manager;

import org.acsn1.banksystem.BankSystem;
import org.acsn1.banksystem.object.Bank;
import org.acsn1.banksystem.utils.ConsoleUtils;
import org.acsn1.banksystem.utils.MessageUtils;
import org.bukkit.Bukkit;

import java.sql.*;
import java.util.UUID;

public class DatabaseManager {

    private BankSystem bankSystem;
    private Connection connection;
    public DatabaseManager(BankSystem bankSystem){
        this.bankSystem = bankSystem;
        if(bankSystem.getConfigManager().isDatabase()) {
            this.loadDriver();
            this.connect();
        } else{

            ConsoleUtils.show(MessageUtils.database_offline);
            bankSystem.disable();
        }
    }

    public void loadDriver(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException ignored){}
    }

    public void connect(){

        try{

            String uri = "jdbc:" + bankSystem.getConfigManager().getURI();
            String username = bankSystem.getConfigManager().getUsername();
            String password = bankSystem.getConfigManager().getPassword();

            connection = DriverManager.getConnection(uri, username, password);
            Statement statement = connection.createStatement();
            String sql ="CREATE TABLE IF NOT EXISTS banks(uuid varchar(36) primary key, balance double)";
            statement.execute(sql);
            statement.close();


            ConsoleUtils.show(MessageUtils.database_success);
            this.loadBanks();

        } catch(SQLException ex){
            ConsoleUtils.show(MessageUtils.database_error);
        }
    }

    public void loadBanks(){

        if(!Bukkit.getPluginManager().isPluginEnabled(bankSystem)) return;

        try{
            String sql = "SELECT * FROM `banks`";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while(result.next()){
                String uuid = result.getString("uuid");
                double balance = result.getDouble("balance");

                bankSystem.getBankManager().loadBankProfiles(UUID.fromString(uuid), balance);

            }

            statement.close();

        } catch(SQLException ignored){}

    }

    public void saveBanks() {


        if(!bankSystem.getConfigManager().isDatabase() || connection == null) {
            return;
        }

        try{
            String sql = "REPLACE banks(uuid,balance) VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            for (Bank banks:bankSystem.getBankManager().getBanks()){
                statement.setString(1, banks.getHolder().toString());
                statement.setDouble(2, banks.getBalance());
                statement.executeUpdate();
            }
            statement.close();
            connection.close();
        }catch(SQLException ignored){}
    }

    public Connection getConnection(){
        return this.connection;
    }


}
