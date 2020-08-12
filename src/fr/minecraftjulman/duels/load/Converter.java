package fr.minecraftjulman.duels.load;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class Converter {
	public static class BukkitSection {
		public static String locationToString(Location location) {
			String str = "";
			
			str += location.getWorld().getName() + ",";
			str += Double.toString(location.getX()) + ",";
			str += Double.toString(location.getY()) + ",";
			str += Double.toString(location.getZ());
			
			return str;
		}
	}
	
	public static class StringSection {
		public static Location toLocation(String string) {
			String[] str = string.split(",");
			
			World world = Bukkit.getWorld(str[0]);
			double X = Double.valueOf(str[1]).doubleValue();
			double Y = Double.valueOf(str[2]).doubleValue();
			double Z = Double.valueOf(str[3]).doubleValue(); 
			
			return new Location(world, X, Y, Z);
		}
	}
}
