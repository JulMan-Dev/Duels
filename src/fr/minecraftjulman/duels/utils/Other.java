package fr.minecraftjulman.duels.utils;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.UUID;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import com.sun.istack.internal.NotNull;

public class Other {
	@UtilsMethods
	@Nullable
	public static <T, Y> T getKeyFromValue(@NotNull HashMap<T, Y> map, @NotNull Y value) {
		for (Entry<T, Y> entry : map.entrySet()) {
	        if (Objects.equals(value, entry.getValue())) {
	            return entry.getKey();
	        }
	    }
	    return null;
	}
	
	@UtilsMethods
	@Nullable
	public static Player getPlayerOnline(@NotNull String name) {
		for (Player player : Bukkit.getOnlinePlayers()) 
			if (player.getDisplayName().equalsIgnoreCase(name))
				return player;
		return null;
	}
	
	@UtilsMethods
	@Nullable
	public static Location rayTraceLocation(@NotNull World world, @NotNull Location loc, @NotNull Vector vector, @NotNull double d) {
		try {
			return world.rayTraceBlocks(loc, vector, d).getHitBlock().getLocation();
		} catch (Exception ex) {
			return null;
		}
	}
	
	@UtilsMethods
	@Nullable
	public static Player getPlayerOnline(@NotNull UUID id) {
		for (Player player : Bukkit.getOnlinePlayers())
			if (player.getUniqueId().equals(id))
				return player;
		return null;
	}
}
