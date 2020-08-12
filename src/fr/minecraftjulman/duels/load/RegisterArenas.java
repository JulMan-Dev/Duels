package fr.minecraftjulman.duels.load;

import java.io.FileReader;
import java.util.function.Consumer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import fr.minecraftjulman.duels.Arena;
import fr.minecraftjulman.duels.Main;
import fr.minecraftjulman.duels.Rectengle3D;

public class RegisterArenas {
	@SuppressWarnings("unchecked")
	public void registerStart() throws Throwable {
		FileReader reader = new FileReader(Main.arenasFile);
		JSONArray array = (JSONArray) new JSONParser().parse(reader);
		
		array.forEach(new Consumer<JSONObject>() {
			@Override
			public void accept(JSONObject t) {
				Arena arena = new Arena(
					Converter.StringSection.toLocation((String) t.get("pos1")), 
					Converter.StringSection.toLocation((String) t.get("pos2")), 
					new Rectengle3D(
						Converter.StringSection.toLocation((String) ((JSONObject) t.get("size")).get("pos1")), 
						Converter.StringSection.toLocation((String) ((JSONObject) t.get("size")).get("pos2"))
					)
				);
				Main.arenasList.add(arena);
			}
		});
	}
}
