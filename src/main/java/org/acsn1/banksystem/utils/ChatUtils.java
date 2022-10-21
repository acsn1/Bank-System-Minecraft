package org.acsn1.banksystem.utils;

import org.bukkit.ChatColor;

public class ChatUtils {

    public static String color(String msg){
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

}
