package fr.minecraftjulman.duels.tabs;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import com.sun.istack.internal.NotNull;

public class ArenaTab implements TabCompleter {

	@NotNull
	@Override
	public List<String> onTabComplete(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		List<String> list = new ArrayList<String>();
		
		if (arg3.length == 1) {
			list.add("gui");
			list.add("add");
			list.add("remove");
		} else if (arg3.length == 2) {
			if (arg3[0].equalsIgnoreCase("add")) {
				list.add("next");
				list.add("previous");
			}
		}
		
		return list;
	}
}
