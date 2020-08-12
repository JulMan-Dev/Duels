package fr.minecraftjulman.duels;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.common.collect.HashBasedTable;

import fr.minecraftjulman.duels.commands.ArenaExecutor;
import fr.minecraftjulman.duels.commands.Duel;
import fr.minecraftjulman.duels.load.RegisterArenas;
import fr.minecraftjulman.duels.tabs.ArenaTab;
import fr.minecraftjulman.duels.tabs.DuelTab;
import fr.minecraftjulman.duels.utils.Tasks;

public class Main extends JavaPlugin {
	public static final long VERSION_ID = 0;
	public static final ConsoleCommandSender Console = Bukkit.getConsoleSender();
	public static final HashMap<UUID, UUID> waitingDuels = new HashMap<>();
	public static HashBasedTable<Entry<UUID, UUID>, Arena, Entry<ItemStack[], ItemStack[]>> playingDuels;
	public static final List<Arena> arenasList = new ArrayList<Arena>();
	public static final String startMessage = "[§eDuels§r] ";
	public static final HashMap<UUID, Entry<Location, Location>> ArenaDisigner = new HashMap<UUID, Entry<Location, Location>>();
	public static File arenasFile;
	
	@Override
	public void onLoad() {
		Console.sendMessage(startMessage + "§aLoading...");
		playingDuels = HashBasedTable.create();
		Console.sendMessage(startMessage + "§a > Loading arenas...");
		getDataFolder().mkdir();
		File file = new File(getDataFolder(), "arenas.json");
		if (!file.exists()) {
			try {
				FileWriter writer = new FileWriter(file);
				JSONArray array = new JSONArray();
				writer.write(array.toJSONString());
				arenasFile = file;
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else arenasFile = file;
		try {
			new RegisterArenas().registerStart();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		Console.sendMessage(startMessage + "§a  > Arenas count : §3" + Integer.toString(arenasList.size()));
		Console.sendMessage(startMessage + "§a > Starting plugin...");
	}
	
	@Override
	public void onEnable() {
		this.getCommand("duel").setExecutor(new Duel());
		this.getCommand("duel").setTabCompleter(new DuelTab());
		this.getCommand("arena").setExecutor(new ArenaExecutor());
		this.getCommand("arena").setTabCompleter(new ArenaTab());
		
		
		Bukkit.getPluginManager().registerEvents(new EventListener(), this);
		new WandSelector().runTaskTimer(this, 500, 0);
		
		Console.sendMessage(startMessage + "§aPlugin ready !");
		
		checkUpdate();
	}
	
	private void checkUpdate() {
		try {
			URL url = new URL("https://minecraftjulman.github.io/download/versions.json");

			JSONObject jo = (JSONObject) new JSONParser().parse(new InputStreamReader(url.openStream()));
			JSONObject jo1 = (JSONObject) jo.get("duels");
			long id = (long) jo1.get("id");
			if (id > VERSION_ID) { 
				Console.sendMessage(" -=- [§eDuels§r] [§eUpdater§r] A new update is available (" + ((String) ((JSONObject) jo.get("duels")).get("name")) + ") ! Go to \"§3https://minecraftjulman.github.io§r\" for download the update. -=-");
				for (Player player : Bukkit.getOnlinePlayers())
					if (player.isOp())
						player.sendMessage("[§cDuels§r] [§cUpdater§r] §9A new update is available (" + ((String) ((JSONObject) jo.get("duels")).get("name")) + "§9) ! Go to \"§3https://minecraftjulman.github.io§9\" for download the update.");
			}
			
		} catch (Throwable e) {}
	}

	@Override
	public void onDisable() {
		Tasks.saveArenas();
		
		Console.sendMessage(startMessage + "§aPlugin disabled !");
	}
}
