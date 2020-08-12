/**
 * 
 */
package fr.minecraftjulman.duels.tasksManagers;

import java.io.FileWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import fr.minecraftjulman.duels.Arena;
import fr.minecraftjulman.duels.Main;
import fr.minecraftjulman.duels.load.Converter;

/**
 * @author MinecraftJulMan
 *
 * @deprecated
 */
public class Save {
	/**
	 * @deprecated
	 */
	@SuppressWarnings("unchecked")
	public static void arenas() {
		try {
			FileWriter writer = new FileWriter(Main.arenasFile);
			JSONArray array = new JSONArray();
			for (Arena arena : Main.arenasList) {
				JSONObject object = new JSONObject();
				object.put("pos1", Converter.BukkitSection.locationToString(arena.pos1));
				object.put("pos2", Converter.BukkitSection.locationToString(arena.pos2));
				JSONObject object2 = new JSONObject();
				object2.put("pos1", Converter.BukkitSection.locationToString(arena.size.pos1));
				object2.put("pos2", Converter.BukkitSection.locationToString(arena.size.pos2));
				object.put("size", object2);
				array.add(object);
			}
			writer.write(array.toJSONString());
			writer.close();
		} catch (Exception e) {}
	}
}
