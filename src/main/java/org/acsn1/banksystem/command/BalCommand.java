package org.acsn1.banksystem.command;

import org.acsn1.banksystem.BankSystem;
import org.acsn1.banksystem.object.Bank;
import org.acsn1.banksystem.utils.ChatUtils;
import org.acsn1.banksystem.utils.PlayerUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class BalCommand implements CommandExecutor {

    private BankSystem bankSystem;
    public BalCommand(BankSystem bankSystem) {
        this.bankSystem = bankSystem;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 0) {
                Bank bank = bankSystem.getBankManager().getBank(player.getUniqueId());

                double balance = bank.getBalance();

                player.sendMessage(ChatUtils.color("&6Your balance: &e$" + balance));
            }

            if (args.length == 1) {
                String username = args[0];

                //async to prevent lag offline UUID
                new BukkitRunnable() {

                    public void run() {
                        final UUID target = PlayerUtils.getUUID(username);
                        if (!bankSystem.getBankManager().hasBank(target)) {
                            player.sendMessage(ChatUtils.color("&c" + username + " has no balance."));

                        } else {

                            Bank bank = bankSystem.getBankManager().getBank(target);
                            double balance = bank.getBalance();
                            player.sendMessage(ChatUtils.color("&6" + username + "'s balance is: &e$" + balance));

                        } cancel();

                    }

                }.runTaskAsynchronously(bankSystem);


            }

        }
        return false;
    }
}
