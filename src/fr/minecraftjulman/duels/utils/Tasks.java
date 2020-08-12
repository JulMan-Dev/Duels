package fr.minecraftjulman.duels.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.bukkit.Location;
import org.bukkit.Particle;

import com.sun.istack.internal.NotNull;

import fr.minecraftjulman.duels.Main;
import fr.minecraftjulman.duels.tasksManagers.Save;

/**
 *  
 * 
 * @author MinecraftJulMan
 *
 */
@SuppressWarnings("deprecation")
public class Tasks {
	@UtilsMethods
	public static final void saveArenas() {
		Main.Console.sendMessage(Main.startMessage + "§r[§eTASKS MANAGER§r]§a Running \"§csaveArenas§a\"...");
		Save.arenas();
		Main.Console.sendMessage(Main.startMessage + "§r[§eTASKS MANAGER§r]§a Ended !");
	}
	
	@UtilsMethods
	public static final int check(Annotation anno, Method[]... list) {
		int count = 0;
		for (Method[] list1 : list) {
			for (Method method : list1) {
				boolean TRUE = false;
				for (Annotation annotation : method.getAnnotations()) {
					if (annotation.equals(anno)) {
						TRUE = true;
						break;
					} else
						continue;
				}
				if (!TRUE) 
					count++;
			}
		}
		return count;
	}
	
	@UtilsMethods
	public static final void generateParticleBlock(@NotNull Location loc, @NotNull Particle particle) {
		Location locBase = new Location(loc.getWorld(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());

		for (double X = 0; X < 1; X += 0.1) {
			locBase.getWorld().spawnParticle(particle, locBase.clone().add(X, 0, 0), 1);
			locBase.getWorld().spawnParticle(particle, locBase.clone().add(X, 1, 0), 1);
			locBase.getWorld().spawnParticle(particle, locBase.clone().add(X, 0, 1), 1);
			locBase.getWorld().spawnParticle(particle, locBase.clone().add(X, 1, 1), 1);
		}

		for (double Y = 0; Y < 1; Y += 0.1) {
			locBase.getWorld().spawnParticle(particle, locBase.clone().add(0, Y, 0), 1);
			locBase.getWorld().spawnParticle(particle, locBase.clone().add(1, Y, 0), 1);
			locBase.getWorld().spawnParticle(particle, locBase.clone().add(0, Y, 1), 1);
			locBase.getWorld().spawnParticle(particle, locBase.clone().add(1, Y, 1), 1);
		}
		
		for (double Z = 0; Z < 1; Z += 0.1) { 
			locBase.getWorld().spawnParticle(particle, locBase.clone().add(0, 0, Z), 1);
			locBase.getWorld().spawnParticle(particle, locBase.clone().add(1, 0, Z), 1);
			locBase.getWorld().spawnParticle(particle, locBase.clone().add(0, 1, Z), 1);
			locBase.getWorld().spawnParticle(particle, locBase.clone().add(1, 1, Z), 1);
		}
	}
}
