package fr.minecraftjulman.duels.commands;

import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.minecraftjulman.duels.Arena;
import fr.minecraftjulman.duels.Main;
import fr.minecraftjulman.duels.utils.Other;

public class Duel implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("duel") || label.equalsIgnoreCase("duels:duel")) {
			if (args.length == 0) {
				sender.sendMessage("§cTry \"§e/duel accept§c\", \"§e/duel deny§c\" or \"§e/duel launch <Player>§c\".");
			} else if (args.length == 1) {
				String args0 = args[0].toLowerCase();
				switch (args0) {
				case "accept":
					if (sender instanceof Player) {
						Player player = (Player) sender;
						if (Main.waitingDuels.containsKey(player.getUniqueId()) || Main.waitingDuels.containsValue(player.getUniqueId())) {
							if (Main.waitingDuels.containsKey(player.getUniqueId())) {
								player.sendMessage("§cYou can't accept your duel request !");
							} else {
								if (!Bukkit.getOfflinePlayer(Other.getKeyFromValue(Main.waitingDuels, player.getUniqueId())).isOnline()) {
									player.sendMessage("§cYour opponent is offline !");
								} else {
									for (Entry<UUID, UUID> entry : Main.waitingDuels.entrySet()) {
										if (entry.getValue() == player.getUniqueId()) {
											Arena.startDuel(entry);
										}
									}
								}
							}
						} else {
							player.sendMessage("§cYou haven't got duel request !");
						}
					} else {
						sender.sendMessage("§cYou can't use that !");
					}
					break;

				case "deny":
					if (sender instanceof Player) {
						Player player = (Player) sender;
						if (Main.waitingDuels.containsKey(player.getUniqueId()) || Main.waitingDuels.containsValue(player.getUniqueId())) {
							if (Main.waitingDuels.containsKey(player.getUniqueId())) {
								player.sendMessage("§aDuel cancelled successfully !");
								UUID opposent = Main.waitingDuels.get(player.getUniqueId());
								if (Bukkit.getOfflinePlayer(opposent).isOnline()) {
									Bukkit.getPlayer(opposent).sendMessage("§cDuel cancelled !");
								}
								Main.waitingDuels.remove(player.getUniqueId());
							} else {
								UUID player1 = Other.getKeyFromValue(Main.waitingDuels, player.getUniqueId());
								OfflinePlayer player2 = Bukkit.getOfflinePlayer(player1);
								
								if (player2.isOnline()) 
									player2.getPlayer().sendMessage("§cDuel denied !");
								
								player.sendMessage("§aDuel denied successfully !");
								
								Main.waitingDuels.remove(player1, player.getUniqueId());
							}
						} else {
							player.sendMessage("§cYou haven't got duel request !");
						}
					} else {
						sender.sendMessage("§cYou can't use that !");
					}
					break;
					
				case "launch":
					if (sender instanceof Player) {
						Player player = (Player) sender;
						player.sendMessage("§cYou must includes a player !");
					} else sender.sendMessage("§cYou can't use that !");
					break;
					
				default:
					break;
				}
			} else if (args.length == 2) {
				if (args[0].equalsIgnoreCase("launch")) {
					if (sender instanceof Player) {
						Player player = (Player) sender;
						Player player1 = Other.getPlayerOnline(args[1]);
						if (player1 == null) player.sendMessage("§cThis player isn't connected !");
						else {
							if (Main.waitingDuels.containsKey(player1.getUniqueId()) || Main.waitingDuels.containsValue(player1.getUniqueId()))
								player.sendMessage("§cThis player has ever duel request !");
							else {
								if (player1.getUniqueId() != player.getUniqueId()) { 
									player.sendMessage("§aDuel request sended !");
									player1.sendMessage("§e--- Duel ---");
									player1.sendMessage("§a" + player.getDisplayName() + " send you a duel request !");
									player1.sendMessage("§3Use : §e/duel accept§3 or §e/duel deny§3.");
									player1.sendMessage("§e--- Duel ---");
									Main.waitingDuels.put(player.getUniqueId(), player1.getUniqueId());
								} else {
									player.sendMessage("§cYou can't send at yourself a duel request !");
								}
							} 
						}
					} else {
						sender.sendMessage("§cYou can't use that !");
					}
				}
			}
			return true;
		}
		return false;
	}

}
