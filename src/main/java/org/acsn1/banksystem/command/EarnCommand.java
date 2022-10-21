package org.acsn1.banksystem.command;

import org.acsn1.banksystem.BankSystem;
import org.acsn1.banksystem.object.Bank;
import org.acsn1.banksystem.utils.ChatUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class EarnCommand implements CommandExecutor {

    private BankSystem bankSystem;
    private Map<UUID, Long> cooldown;
    public EarnCommand(BankSystem bankSystem){
        this.bankSystem = bankSystem;
        this.cooldown = new HashMap<>();
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            Player player = (Player) sender;
            Bank bank = bankSystem.getBankManager().getBank(player.getUniqueId());
            long time;
            if(!cooldown.containsKey(player.getUniqueId())){
                time = TimeUnit.MINUTES.toMillis(1) + System.currentTimeMillis();
                cooldown.put(player.getUniqueId(), time);
                bankSystem.getBankManager().earn(bank);

            } else {
                time = cooldown.get(player.getUniqueId());
                if (time > System.currentTimeMillis()){
                    player.sendMessage(ChatUtils.color("&cYou must wait " + TimeUnit.MILLISECONDS.toSeconds(time - System.currentTimeMillis()) + " more seconds."));
                } else{
                    time = TimeUnit.MINUTES.toMillis(1) + System.currentTimeMillis();
                    cooldown.put(player.getUniqueId(), time);
                    bankSystem.getBankManager().earn(bank);

                }
            }
        }

        return false;
    }
}
