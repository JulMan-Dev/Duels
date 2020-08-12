package fr.minecraftjulman.duels.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.minecraftjulman.duels.Arena;
import fr.minecraftjulman.duels.Main;

public class ArenaExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if (arg2.toLowerCase().endsWith("arena")) {
			if (arg3.length == 1) {
				String args = arg3[0];
				
				if (args.equalsIgnoreCase("gui")) {
					if (arg0 instanceof Player) {
						Inventory inv = Bukkit.createInventory(null, 36, "§aArena GUI");
					
						ItemStack it1 = new ItemStack(Material.LIME_DYE, 1);
						ItemMeta it1m = it1.getItemMeta();
						it1m.setDisplayName("§aAdd a arena !");
						it1m.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
						it1m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
						it1.setItemMeta(it1m);
					
						ItemStack it2 = new ItemStack(Material.PAPER, 1);
						ItemMeta it2m = it2.getItemMeta();
						it2m.setDisplayName("§aArenas list");
						List<String> list = new ArrayList<String>();
						list.add("");
						int count = 1;
						for (Arena arena : Main.arenasList) {
							list.add("§aArena n°" + Integer.toString(count));
							list.add(" - §aWorld : §3" + arena.pos1.getWorld().getName());
							list.add("");
							count ++;
						}
						list.remove(list.size() - 1);
						it2m.setLore(list);
						it2m.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
						it2m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
						it2.setItemMeta(it2m);
					
						inv.setItem(12, it1);
						inv.setItem(13, it2);
						
						((Player) arg0).openInventory(inv);
					} else arg0.sendMessage("§cYou can't use that !");
				} else if (args.equalsIgnoreCase("add")) {
					if (arg0 instanceof Player) {
						Arena.create((Player) arg0);
					} else arg0.sendMessage("§cYou can't use that !"); 
				}
			}
		}
		return false;
	}

}
