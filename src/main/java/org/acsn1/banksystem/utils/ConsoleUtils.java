package org.acsn1.banksystem.utils;

import org.bukkit.Bukkit;

public class ConsoleUtils {

    public static void show(String msg){
        Bukkit.getConsoleSender().sendMessage(ChatUtils.color(msg));
    }

}
