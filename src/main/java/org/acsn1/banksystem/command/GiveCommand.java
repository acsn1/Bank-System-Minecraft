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

public class GiveCommand implements CommandExecutor {

    private BankSystem bankSystem;
    public GiveCommand(BankSystem bankSystem){
        this.bankSystem = bankSystem;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            Player player = (Player) sender;

            if(args.length == 0){
                player.sendMessage(ChatUtils.color("&cUsage: /give <target username> <amount>"));
            }
            // args.length = 1 -> ignored

            if(args.length == 2){
                String username = args[0];
                double amount;
                Bank own = bankSystem.getBankManager().getBank(player.getUniqueId());
                try{
                    amount = Double.parseDouble(args[1]);
                }catch(NumberFormatException e){
                    player.sendMessage(ChatUtils.color("&cPlease enter a valid amount of money."));
                    return true;
                }

                //async to prevent lag offline UUID
                new BukkitRunnable(){

                        public void run(){
                            final UUID uuid = PlayerUtils.getUUID(username);
                            bankSystem.getBankManager().createBankProfile(uuid);
                            if(bankSystem.getBankManager().hasBank(uuid)){
                                Bank bank = bankSystem.getBankManager().getBank(uuid);

                                if(amount > 0 && amount <= own.getBalance()) {
                                    bank.addToBalance(amount);
                                    own.removeFromBalance(amount);

                                    player.sendMessage(ChatUtils.color("&aYou've given " + username + " the amount of $" + amount));

                                } else{
                                    player.sendMessage(ChatUtils.color("&cThe amount you give should be positive and smaller or equal to your balance."));
                                }

                            } cancel();

                    }

                }.runTaskAsynchronously(bankSystem);

            }
        }

        return false;
    }
}
