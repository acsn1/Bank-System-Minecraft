package org.acsn1.banksystem.event;

import org.acsn1.banksystem.BankSystem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {

    private BankSystem bankSystem;
    public PlayerListener(BankSystem bankSystem){
        this.bankSystem = bankSystem;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        bankSystem.getBankManager().createBankProfile(player.getUniqueId());

    }

}
