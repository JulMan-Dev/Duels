package fr.minecraftjulman.duels.tabs;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class DuelTab implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		List<String> list = new ArrayList<String>();
		
		if (args.length == 1) {
			list.add("accept");
			list.add("deny");
			list.add("launch");
		} else if (args.length == 2) {
			if (args[0].equalsIgnoreCase("launch")) {
				for (Player player : Bukkit.getOnlinePlayers()) 
					list.add(player.getDisplayName());
			}
		}
		
		return list;
	}

}
