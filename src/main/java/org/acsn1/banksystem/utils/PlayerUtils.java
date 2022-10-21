package org.acsn1.banksystem.utils;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerUtils {

    public static UUID getUUID(String username){
        Player player = Bukkit.getPlayer(username);
        if(player != null){
            return player.getUniqueId();
        } else{
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(username);
            return offlinePlayer.getUniqueId();
        }
    }

}
